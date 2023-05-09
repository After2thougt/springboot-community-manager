package com.bee.manager.dao;


import com.bee.manager.dto.PermissionDTO;
import com.bee.manager.dto.PostDTO;
import com.bee.manager.dto.PostSearchDTO;
import com.bee.manager.pojo.Post;
import org.apache.ibatis.annotations.Param;
import tk.mapper.MyMapper;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface BBSPostMapper extends MyMapper<Post> {

    List<PostDTO> getPostList(PostSearchDTO postSearchDTO);

    List<PostDTO> getPostListByPId(@Param("postId") Long postId);

    int updatePostStatus(@Param("postId") Long postId, @Param("postStatus") Integer postStatus);

    int updatePost(Post post);

    int updatePostContent(Post post);



}