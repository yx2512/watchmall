package com.yx.watchmall.controller;

import com.yx.watchmall.pojo.DialColor;
import com.yx.watchmall.pojo.Feature;
import com.yx.watchmall.service.DialColorService;
import com.yx.watchmall.service.FeatureService;
import com.yx.watchmall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AttributesController {

    private FeatureService featureService;
    private DialColorService dialColorService;

    @Autowired
    public void setDialColorService(DialColorService service) {
        dialColorService = service;
    }

    @Autowired
    public void setFeatureService(FeatureService service) {
        featureService = service;
    }

    @GetMapping("/features")
    public ResponseVo<List<Feature>> listAllFeatures() {
        return featureService.listAllFeatures();
    }

    @GetMapping("/dial_colors")
    public ResponseVo<List<DialColor>> listAllDialColor() {
        return dialColorService.listAllDialColors();
    }
}
