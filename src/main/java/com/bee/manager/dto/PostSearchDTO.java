package com.bee.manager.dto;

import lombok.Data;

/**
 * 帖子搜索DTO
 *
 */
@Data
public class PostSearchDTO {

    private String postTitle;

    private String startTime;

    private String endTime;
}
