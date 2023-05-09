package com.bee.manager.service.impl;

import com.bee.manager.dao.PostCategoryMapper;
import com.bee.manager.pojo.PostCategory;
import com.bee.manager.service.PostCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCategoryServiceImpl implements PostCategoryService {

    @Autowired
    private PostCategoryMapper bbsPostCategoryMapper;

    @Override
    public List<PostCategory> getPostCategories() {
        return bbsPostCategoryMapper.getPostCategories();
    }


}
