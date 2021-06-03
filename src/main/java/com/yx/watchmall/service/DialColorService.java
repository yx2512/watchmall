package com.yx.watchmall.service;

import com.yx.watchmall.pojo.DialColor;
import com.yx.watchmall.repository.DialRepository;
import com.yx.watchmall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DialColorService {

    private DialRepository dialRepository;

    @Autowired
    public void setDialRepository(DialRepository repository) {
        dialRepository = repository;
    }

    public ResponseVo<List<DialColor>> listAllDialColors() {
        final List<DialColor> dialColors = dialRepository.findAll();
        dialColors.forEach(e->{e.setCreateTime(null);e.setUpdateTime(null);});
        return ResponseVo.success(dialColors);
    }
}
