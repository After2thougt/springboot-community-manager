package com.bee.manager.service.impl;

import com.bee.manager.dao.BBSPostMapper;
import com.bee.manager.dao.BaseAdminUserMapper;
import com.bee.manager.dao.PostCategoryMapper;
import com.bee.manager.dto.PostDTO;
import com.bee.manager.dto.PostSearchDTO;
import com.bee.manager.pojo.BaseAdminUser;
import com.bee.manager.pojo.Post;
import com.bee.manager.pojo.PostCategory;
import com.bee.manager.response.PageDataResult;
import com.bee.manager.service.PostService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PostServiceImpl
 *
 */
@Service
public class PostServiceImpl implements PostService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BBSPostMapper bbsPostMapper;

    @Autowired
    private BaseAdminUserMapper baseAdminUserMapper;

    private PostCategoryMapper postCategoryMapper;


    /**
     * 获取帖子列表
     *
     * @param postSearch
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageDataResult getPostList(PostSearchDTO postSearch, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<PostDTO> posts = bbsPostMapper.getPostList(postSearch);

        PageHelper.startPage(pageNum, pageSize);

        if(posts.size() != 0){
            PageInfo<PostDTO> pageInfo = new PageInfo<>(posts);
            pageDataResult.setList(posts);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }
        return pageDataResult;
    }

    @Override
    public Map<String, Object> updatePost(Post post) {
        Map<String,Object> data = new HashMap();

        try{
            Date date = new Date();
            Timestamp newDate = new Timestamp(date.getTime());
            post.setUpdateTime(newDate);

            int result = bbsPostMapper.updatePost(post);
            if(result == 0){
                data.put("code",0);
                data.put("msg","更新失败");
                return data;
            }
            data.put("code",1);
            data.put("msg","更新成功");
        }catch (Exception e) {
            e.printStackTrace();
            return data;
        }
        return data;
    }

    @Override
    public Map<String, Object> delPost(Long postId, Integer postStatus) {
        Map<String, Object> data = new HashMap<>();
        try {
            int result = bbsPostMapper.updatePostStatus(postId,postStatus);
            if(result == 0){
                data.put("code",0);
                data.put("msg","操作失败");
            }
            data.put("code",1);
            data.put("msg","操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("操作异常", e);
        }
        return data;
    }

    @Override
    public Map<String, Object> recoverPost(Long postId, Integer postStatus) {
        Map<String, Object> data = new HashMap<>();
        try {
            int result = bbsPostMapper.updatePostStatus(postId,postStatus);
            if(result == 0){
                data.put("code",0);
                data.put("msg","操作失败");
            }
            data.put("code",1);
            data.put("msg","操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("操作异常", e);
        }
        return data;
    }

    @Override
    public int updatePostContent(Post post) {

        Long publishUserId = post.getPublishUserId();

        BaseAdminUser user = baseAdminUserMapper.getUserByUserId(publishUserId);

        if (user==null||user.getUserStatus().intValue()==0) {
            return 0;
        }

        PostCategory postCategory = postCategoryMapper.selectByPrimaryKey(post.getPostCategoryId());

        if (postCategory == null) {
            return 0;
        }
        return bbsPostMapper.updatePostContent(post);
    }


}
