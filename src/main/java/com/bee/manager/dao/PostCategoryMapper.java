package com.bee.manager.dao;

import com.bee.manager.pojo.PostCategory;
import tk.mapper.MyMapper;

import java.util.List;

public interface PostCategoryMapper extends MyMapper<PostCategory> {

    List<PostCategory> getPostCategories();

}
