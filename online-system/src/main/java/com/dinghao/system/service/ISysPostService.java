package com.dinghao.system.service;


import com.dinghao.system.domain.SysPost;

import java.util.List;

/**
 * 岗位信息 服务层
 * 
 * @author dinghao
 */
public interface ISysPostService
{

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    List<SysPost> selectPostAll();


    /**
     * 根据用户ID获取岗位选择框列表
     *
     * @param userId 用户ID
     * @return 选中岗位ID列表
     */
     List<Integer> selectPostListByUserId(Long userId);
    /**
     * 查询岗位信息集合
     *
     * @param post 岗位信息
     * @return 岗位列表
     */
    List<SysPost> selectPostList(SysPost post);

    SysPost selectPostById(Long postId);

    String checkPostNameUnique(SysPost post);

    String checkPostCodeUnique(SysPost post);

    Integer insertPost(SysPost post);

    Integer updatePost(SysPost post);

    Integer deletePostByIds(Long[] postIds);
}
