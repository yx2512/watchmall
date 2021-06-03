package com.yx.watchmall.service;

import com.yx.watchmall.pojo.Feature;
import com.yx.watchmall.repository.FeatureRepository;
import com.yx.watchmall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureService {

    private FeatureRepository featureRepository;

    @Autowired
    public void setFeatureRepository(FeatureRepository repository) {
        featureRepository = repository;
    }

    public ResponseVo<List<Feature>> listAllFeatures() {
        final List<Feature> features = featureRepository.findAll();
        features.forEach(e->{e.setCreateTime(null);e.setUpdateTime(null);});
        return ResponseVo.success(features);
    }
}
