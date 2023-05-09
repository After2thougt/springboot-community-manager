package com.bee.manager.service;


import com.bee.manager.dto.PostDTO;
import com.bee.manager.dto.PostSearchDTO;
import com.bee.manager.pojo.BaseAdminRole;
import com.bee.manager.pojo.BaseAdminUser;
import com.bee.manager.pojo.Post;
import com.bee.manager.response.PageDataResult;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface PostService {


    /**
     *
     * 功能描述: 获取权限菜单列表
     *
     */
    PageDataResult getPostList(PostSearchDTO postSearch, Integer pageNum, Integer pageSize);


    Map<String,Object> updatePost(Post post);

    Map<String, Object> delPost(Long postId,Integer postStatus);


    Map<String,Object> recoverPost(Long postId, Integer postStatus);

    int updatePostContent(Post post);


}
