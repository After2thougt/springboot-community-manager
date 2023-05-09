package com.bee.manager.controller.system;


import com.bee.manager.dto.PostDTO;
import com.bee.manager.dto.PostSearchDTO;
import com.bee.manager.pojo.BaseAdminRole;
import com.bee.manager.pojo.BaseAdminUser;
import com.bee.manager.pojo.Post;
import com.bee.manager.pojo.PostCategory;
import com.bee.manager.response.PageDataResult;
import com.bee.manager.service.PostCategoryService;
import com.bee.manager.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("post")
public class PostController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private PostService postService;

    @Autowired
    private PostCategoryService postCategoryService;

    @RequestMapping("/postManage")
    public String postManage() {
        logger.info("进入帖子管理");
        return "/post/postManage";
    }

    /**
     * 获取帖子
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getPostList", method = RequestMethod.GET)
    @ResponseBody
    public PageDataResult postList(@RequestParam("pageNum") Integer pageNum,
                                   @RequestParam("pageSize") Integer pageSize,
                                   PostSearchDTO postSearch){
        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }

            pdr = postService.getPostList(postSearch,pageNum ,pageSize);
            logger.info("帖子列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("帖子列表查询异常", e);
        }
        return pdr;
    }

    @PostMapping("setPost")
    @ResponseBody
    public Map<String,Object> setPost(Post post) {

        Map<String,Object> data = new HashMap();
        // 修改
        data = postService.updatePost(post);

        return data;
    }


    /**
     * 删除/恢复
     * @param postId
     * @param postStatus
     * @return
     */
    @PostMapping("updatePostStatus")
    @ResponseBody
    public Map<String, Object> updatePostStatus(@RequestParam(value = "postId") Long postId,
                                                @RequestParam(value = "postStatus") Integer postStatus) {
        logger.info("删除/恢复帖子 id:" + postId+" status:"+postStatus);

        Map<String, Object> data = new HashMap<>();
        if(postStatus == 0){
            //删帖
            data = postService.delPost(postId,postStatus);
        }else{
            data = postService.recoverPost(postId,postStatus);
        }
        return data;
    }

    @GetMapping("getPostCategories")
    @ResponseBody
    public List<PostCategory> getPostCategories(){
        logger.info("获取分类列表");
        return postCategoryService.getPostCategories();
    }



}
