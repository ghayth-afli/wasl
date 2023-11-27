package com.mzo.wasl.service;

import com.mzo.wasl.repository.ReviewRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    ReviewRepositroy reviewRepositroy;
}
