package com.yx.watchmall;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yx.watchmall.form.RegisterForm;
import com.yx.watchmall.form.ShipmentForm;
import com.yx.watchmall.pojo.Shipment;
import com.yx.watchmall.pojo.User;
import com.yx.watchmall.repository.ShipmentRepository;
import com.yx.watchmall.service.CategoryService;
import com.yx.watchmall.service.ProductService;
import com.yx.watchmall.service.ShipmentService;
import com.yx.watchmall.service.UserService;
import com.yx.watchmall.vo.CategoryVo;
import com.yx.watchmall.vo.ProductConciseVo;
import com.yx.watchmall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

import java.util.List;
@Slf4j
@SpringBootTest
public class WatchmallApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ShipmentService shipmentService;

    @Autowired
    ProductService productService;

    @Test
    void contextLoads() {

    }

    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void setObjectMapper() {
        objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Test
    void testLogin() {
        userService.login("admin@watchmall.com","233");
    }

    @Test
    void testRegister() {
        RegisterForm registerForm = new RegisterForm();
        registerForm.setUsername("jerry");
        registerForm.setPassword("jerry");
        registerForm.setEmail("jerry@watchmall.com");
        registerForm.setPhone("8765432110");
        final User user = new User();
        BeanUtils.copyProperties(registerForm,user);
        userService.register(user);
    }

    @Test
    void testCategories() {
        final ResponseVo<List<CategoryVo>> allCategories = categoryService.findAllCategories();
        log.info(allCategories.toString());
    }

    @Test
    void testCategory() {
        final ResponseVo<CategoryVo> category = categoryService.findCategoryById(16);
        log.info(category.toString());
    }

    @Test
    void testFindAllShipmentsByUserId() throws JsonProcessingException {
        final ResponseVo<Page<Shipment>> byUserId = shipmentService.findByUserId(2,1,10);
        log.info(objectMapper.writeValueAsString(byUserId));
    }

    @Test
    void testUpdateShipment() throws JsonProcessingException {
        final ShipmentForm shipmentForm = new ShipmentForm();
        shipmentForm.setReceiverAddr("200 Cumberland Ave");
        shipmentForm.setReceiverApt("APT1503");
        shipmentForm.setReceiverCity("West Lafayette");
        shipmentForm.setReceiverMobile("7657753952");
        shipmentForm.setReceiverName("Yu Xia");
        shipmentForm.setReceiverState("AZ");
        shipmentForm.setReceiverZip("47906");
        final ResponseVo responseVo = shipmentService.update(3, 1, shipmentForm);
        log.info(objectMapper.writeValueAsString(responseVo));
    }

    @Test
    void testDeleteShipment() throws JsonProcessingException {
        final ResponseVo responseVo = shipmentService.delete(2, 1);
        log.info(objectMapper.writeValueAsString(responseVo));
    }

    @Test
    void testListAllProducts() throws JsonProcessingException {
        final ResponseVo<Page<ProductConciseVo>> pageResponseVo = productService.listAllProducts(null, "4_7", "-1", "-1", 0, 10);
        objectMapper.writeValueAsString(pageResponseVo);
    }
}
