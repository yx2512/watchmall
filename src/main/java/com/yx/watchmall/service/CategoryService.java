package com.yx.watchmall.service;

import com.yx.watchmall.enums.ResponseEnum;
import com.yx.watchmall.mallConst.MallConst;
import com.yx.watchmall.pojo.Category;
import com.yx.watchmall.repository.CategoryRepository;
import com.yx.watchmall.vo.CategoryVo;
import com.yx.watchmall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository repository) {
        categoryRepository = repository;
    }

    public ResponseVo<List<CategoryVo>> findAllCategories() {
        final Iterable<Category> categories = categoryRepository.findByStatus(MallConst.NORMAL_STATUS);
        final Spliterator<Category> spliterator = categories.spliterator();
        final List<CategoryVo> categoryVoList = StreamSupport.stream(spliterator, false)
                .filter(e -> e.getParentId().equals(MallConst.ROOT_CATEGORY_PARENT_ID))
                .map(this::category2CategoryVo)
                .sorted(Comparator.comparing(CategoryVo::getSortOrder))
                .collect(Collectors.toList());
        findSubCategories(categoryVoList, categories);
        return ResponseVo.success(categoryVoList);
    }

    public ResponseVo<CategoryVo> findCategoryById(@RequestParam(value = "id", required = true) Integer categoryId) {
        final Iterable<Category> categories = categoryRepository.findAll();
        final Spliterator<Category> categorySpliterator = categories.spliterator();
        final Optional<Category> optionalCategory = StreamSupport.stream(categorySpliterator, false).filter(e -> e.getId().equals(categoryId))
                .findFirst();
        if(optionalCategory.isEmpty()) {
            return ResponseVo.error(ResponseEnum.NO_SUCH_CATEGORY);
        }
        CategoryVo categoryVo = this.category2CategoryVo(optionalCategory.get());
        findSubCategories(categoryVo,categories);
        return ResponseVo.success(categoryVo);
    }

    public CategoryVo category2CategoryVo(Category category) {
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        return categoryVo;
    }

    public void findSubCategories(CategoryVo categoryVo, Iterable<Category> categories) {
        List<CategoryVo> subCategoryVoList = new ArrayList<>();
        for(Category subCategory:categories) {
            if(categoryVo.getId().equals(subCategory.getParentId())) {
                subCategoryVoList.add(category2CategoryVo(subCategory));
            }
        }
        categoryVo.setSubCategories(subCategoryVoList);
        findSubCategories(subCategoryVoList,categories);
    }

    public void findSubCategories(List<CategoryVo> categoryVoList, Iterable<Category> categories) {
        for(CategoryVo categoryVo : categoryVoList) {
            List<CategoryVo> subCategoryVoList = new ArrayList<>();
            for(Category subCategory : categories) {
                if(categoryVo.getId().equals(subCategory.getParentId())) {
                    subCategoryVoList.add(category2CategoryVo(subCategory));
                }
            }
            subCategoryVoList.sort(Comparator.comparing(CategoryVo::getSortOrder));
            categoryVo.setSubCategories(subCategoryVoList);
            findSubCategories(subCategoryVoList, categories);
        }
    }

    public Set<Integer> findSubcategoryId(Integer categoryId) {
        Set<Integer> categorySet = new HashSet<>();
        final Iterable<Category> categories = categoryRepository.findAll();
        categorySet.add(categoryId);
        findSubcategoryId(categoryId, categorySet, categories);
        return categorySet;
    }

    public void findSubcategoryId(Integer categoryId, Set<Integer> categorySet, Iterable<Category> categories) {
        for(Category category : categories) {
            if(categoryId.equals(category.getParentId())) {
                categorySet.add(category.getId());
                findSubcategoryId(category.getId(),categorySet,categories);
            }
        }
//        final Spliterator<Category> categorySpliterator = categories.spliterator();
//        final List<Integer> subCategories = StreamSupport.stream(categorySpliterator, false)
//                .filter(e -> e.getParentId().equals(categoryId))
//                .map(Category::getId)
//                .collect(Collectors.toList());
//        categorySet.addAll(subCategories);
//
//        for(Integer subCategory : subCategories) {
//            findSubcategoryId(subCategory, categorySet, categories);
//        }
    }
}
