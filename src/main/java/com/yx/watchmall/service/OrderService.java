package com.yx.watchmall.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yx.watchmall.enums.OrderStatusEnum;
import com.yx.watchmall.enums.ResponseEnum;
import com.yx.watchmall.mallConst.MallConst;
import com.yx.watchmall.pojo.*;
import com.yx.watchmall.repository.OrderItemRepository;
import com.yx.watchmall.repository.OrderRepository;
import com.yx.watchmall.repository.ProductRepository;
import com.yx.watchmall.repository.ShipmentRepository;
import com.yx.watchmall.util.OrderNoUtil;
import com.yx.watchmall.vo.OrderItemVo;
import com.yx.watchmall.vo.OrderVo;
import com.yx.watchmall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService {
    private CartService cartService;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private ShipmentRepository shipmentRepository;

    @Autowired
    public void setShipmentRepository(ShipmentRepository repository) {
        shipmentRepository = repository;
    }

    @Autowired
    public void setOrderRepository(OrderRepository repository) {
        orderRepository = repository;
    }

    @Autowired
    public void setOrderItemRepository(OrderItemRepository repository) {
        orderItemRepository = repository;
    }

    @Autowired
    public void setProductRepository(ProductRepository repository) {
        productRepository = repository;
    }

    @Autowired
    public void setCartService(CartService service) {
        cartService = service;
    }

    @Transactional
    public ResponseVo<OrderVo> placeOrder(Integer userId, Integer shippingId) throws JsonProcessingException {
        List<CartProduct> cartProductList = cartService.listForCartProduct(userId).stream().filter(CartProduct::getSelected).collect(Collectors.toList());
        Set<Integer> cartProductsIds = cartProductList.stream().map(CartProduct::getProductId).collect(Collectors.toSet());
        if(cartProductsIds.isEmpty()) {
            return ResponseVo.error(ResponseEnum.CART_SELECTED_EMPTY);
        }

        Optional<Shipment> shipmentOptional = shipmentRepository.findByIdAndUserId(shippingId, userId);
        if(shipmentOptional.isEmpty()) {
            return ResponseVo.error(ResponseEnum.NO_CORRESPONDING_SHIPPING.getCode(),"Cannot find shipping address with ID: " + shippingId);
        }

        Shipment shipment = shipmentOptional.get();
        List<Product> products = productRepository.findAllById(cartProductsIds);
        Map<Integer, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, product -> product));

        List<OrderItem> orderItemList = new ArrayList<>();
        Long orderNum = generateOrderNum();
        for(CartProduct each : cartProductList) {
            Product product = productMap.get(each.getProductId());
            if(product == null) {
                return ResponseVo.error(ResponseEnum.NO_SUCH_PRODUCT.getCode(),each.getProductId() + " does not exist");
            }

            if(!product.getStatus().equals(MallConst.NORMAL_STATUS)) {
                return ResponseVo.error(ResponseEnum.PRODUCT_UNAVAILABLE.getCode(),each.getProductId() + " not available");
            }

            if(product.getStatus() < each.getQuantity()) {
                return ResponseVo.error(ResponseEnum.LIMITED_STOCK.getCode(),each.getProductId() + "No enough stock for " + each.getProductId());
            }

            OrderItem orderItem = buildOrderItem(userId, orderNum, each.getQuantity(), product);
            orderItemList.add(orderItem);

            product.setStock(product.getStock() - each.getQuantity());
            productRepository.save(product);
        }

        Order order = buildOrder(userId, orderNum, shippingId, orderItemList);

        Order save = orderRepository.save(order);
        if(save.getId() == null) {
            throw new RuntimeException(String.format("order %d creation failed",order.getOrderNum()));
        }

        orderItemRepository.saveAll(orderItemList);
        for(CartProduct cartProduct : cartProductList) {
            cartService.delete(userId,cartProduct.getProductId());
        }

        OrderVo orderVo = buildOrderVo(order, orderItemList, shipment);
        return ResponseVo.success(orderVo);
    }

    public ResponseVo<Page<OrderVo>> listOrders(Integer userId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<Order> orderList = orderRepository.findAllByUserId(userId);
        if(pageable.getOffset() > orderList.size()) {
            pageable = PageRequest.of(0,pageSize);
        }

        List<Order> subOrderList = orderList.subList((int) pageable.getOffset(), (int) Math.min(orderList.size(), pageable.getOffset() + pageSize));

        Set<Long> orderNums = subOrderList.stream().map(Order::getOrderNum).collect(Collectors.toSet());
        List<OrderItem> subOrderItemList = orderItemRepository.findAllByOrderNumIn(orderNums);
        Map<Long, List<OrderItem>> subOrderItemMap = subOrderItemList.stream().collect(Collectors.groupingBy(OrderItem::getOrderNum));

        Set<Integer> subShippingIds = subOrderList.stream().map(Order::getShippingId).collect(Collectors.toSet());
        Iterable<Shipment> subShipmentIterable = shipmentRepository.findAllById(subShippingIds);
        Spliterator<Shipment> subShipmentSpliterator = subShipmentIterable.spliterator();
        Map<Integer, Shipment> subShipmentMap = StreamSupport.stream(subShipmentSpliterator, false).collect(Collectors.toMap(Shipment::getId, shipment -> shipment));

        List<OrderVo> orderVoList = new ArrayList<>();
        for(Order order : subOrderList) {
            OrderVo orderVo = buildOrderVo(order, subOrderItemMap.get(order.getOrderNum()), subShipmentMap.get(order.getShippingId()));
            orderVoList.add(orderVo);
        }

        Page<OrderVo> orderVoPage = new PageImpl<>(orderVoList,pageable,orderList.size());
        return ResponseVo.success(orderVoPage);
    }

    public ResponseVo<OrderVo> detail(Integer userId, Long orderNum) {
        Optional<Order> orderOptional = orderRepository.findByOrderNum(orderNum);
        if(orderOptional.isEmpty() || !userId.equals(orderOptional.get().getUserId() )) {
            return ResponseVo.error(ResponseEnum.NO_SUCH_ORDER.getCode(),"No such order with No. " + orderNum);
        }

        Order order = orderOptional.get();
        Set<Long> orderNumSet = new HashSet<>();
        orderNumSet.add(order.getOrderNum());
        List<OrderItem> orderItemList = orderItemRepository.findAllByOrderNumIn(orderNumSet);

        Optional<Shipment> shipmentOptional = shipmentRepository.findById(order.getShippingId());
        if(shipmentOptional.isEmpty()) {
            return ResponseVo.error(ResponseEnum.NO_CORRESPONDING_SHIPPING.getCode(),"No corresponding shipping with ID" + order.getShippingId());
        }
        OrderVo orderVo = buildOrderVo(order, orderItemList, shipmentOptional.get());
        return ResponseVo.success(orderVo);
    }

    public ResponseVo cancel(Integer userId, Long orderNum) {
        Optional<Order> orderOptional = orderRepository.findByOrderNum(orderNum);
        if(orderOptional.isEmpty()) {
            return ResponseVo.error(ResponseEnum.NO_SUCH_ORDER);
        }
        Order order = orderOptional.get();
        if(!order.getStatus().equals(OrderStatusEnum.NO_PAY.getCode())) {
            return ResponseVo.error(ResponseEnum.ORDER_STATUS_ERROR.getCode(), "Cannot cancel order " + order.getOrderNum());
        }
        order.setStatus(OrderStatusEnum.CANCELED.getCode());
        order.setClosedTime(new Date());
        orderRepository.save(order);
        return ResponseVo.success();
    }

    public ResponseVo paid(Long orderNum) {
        Optional<Order> orderOptional = orderRepository.findByOrderNum(orderNum);
        if(orderOptional.isEmpty()) {
            return ResponseVo.error(ResponseEnum.NO_SUCH_ORDER);
        }

        Order order = orderOptional.get();
        if(!order.getStatus().equals(OrderStatusEnum.NO_PAY.getCode())) {
            return ResponseVo.error(ResponseEnum.ORDER_STATUS_ERROR.getCode(), "Cannot change status of order " + order.getOrderNum() + "to paid");
        }

        order.setStatus(OrderStatusEnum.PAID.getCode());
        order.setPaymentTime(new Date());
        orderRepository.save(order);
        return ResponseVo.success();
    }

    private Long generateOrderNum() {
        return OrderNoUtil.nextOrderNo();
    }

    private OrderItem buildOrderItem(Integer userId, Long orderNum, Integer quantity, Product product) {
        OrderItem orderItem = new OrderItem();
        orderItem.setUserId(userId);
        orderItem.setOrderNum(orderNum);
        orderItem.setProductId(product.getId());
        orderItem.setDescription(product.getDescription());
        orderItem.setUnitPrice(product.getPrice());
        orderItem.setQuantity(quantity);
        orderItem.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        return orderItem;
    }

    private Order buildOrder(Integer userId, Long orderNum, Integer shippingId, List<OrderItem> orderItemList) {
        BigDecimal payment = orderItemList.stream().map(OrderItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderNum(orderNum);
        order.setShippingId(shippingId);
        order.setPayment(payment);
        order.setStatus(OrderStatusEnum.NO_PAY.getCode());
        order.setPostage(getPostage());
        return order;
    }

    private OrderVo buildOrderVo(Order order, List<OrderItem> orderItemList, Shipment shipment) {
        OrderVo orderVo = new OrderVo();
        BeanUtils.copyProperties(order,orderVo);

        List<OrderItemVo> orderItemVoList = orderItemList.stream().map(e -> {
            OrderItemVo orderItemVo = new OrderItemVo();
            BeanUtils.copyProperties(e, orderItemVo);
            return orderItemVo;
        }).collect(Collectors.toList());
        orderVo.setOrderItemVoList(orderItemVoList);

        if(shipment != null) {
            orderVo.setShippingId(shipment.getId());
            orderVo.setShipping(shipment);
        }
        return orderVo;
    }

    private Integer getPostage() {
        return new Random().nextInt(50);
    }
}
