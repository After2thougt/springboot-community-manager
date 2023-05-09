package com.bee.manager.service;


import com.bee.manager.pojo.PostCategory;

import java.util.List;

public interface PostCategoryService {
    /**
     * 获取分类列表
     *
     * @return
     */
    List<PostCategory> getPostCategories();

}
