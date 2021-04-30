package com.dinghao.system.service.impl;


import com.dinghao.common.constant.UserConstants;
import com.dinghao.common.exception.CustomException;
import com.dinghao.common.utils.StringUtils;
import com.dinghao.system.domain.SysPost;
import com.dinghao.system.mapper.SysPostMapper;
import com.dinghao.system.mapper.SysUserPostMapper;
import com.dinghao.system.service.ISysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 岗位信息 服务层处理
 * 
 * @author dinghao
 */
@Service
public class SysPostServiceImpl implements ISysPostService
{
    @Autowired
    private SysPostMapper postMapper;

    @Autowired
    private SysUserPostMapper userPostMapper;

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    @Override
    public List<SysPost> selectPostAll() {
        return postMapper.selectPostAll();
    }
    /**
     * 根据用户ID获取岗位选择框列表
     *
     * @param userId 用户ID
     * @return 选中岗位ID列表
     */
    @Override
    public List<Integer> selectPostListByUserId(Long userId) {
        return postMapper.selectPostListByUserId(userId);
    }

    @Override
    public List<SysPost> selectPostList(SysPost post) {
        return postMapper.selectPostList(post);
    }

    @Override
    public SysPost selectPostById(Long postId) {
        return postMapper.selectPostById(postId);
    }

    /**
     * 校验岗位是否一致
     * @param post
     * @return
     */
    @Override
    public String checkPostNameUnique(SysPost post) {
        Long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
        SysPost info = postMapper.checkPostNameUnique(post.getPostName());
        if (StringUtils.isNotNull(info) && info.getPostId().longValue() != postId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
    /**
     * 校验岗位编码是否唯一
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public String checkPostCodeUnique(SysPost post) {
        Long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
        SysPost info = postMapper.checkPostCodeUnique(post.getPostCode());
        if (StringUtils.isNotNull(info) && info.getPostId().longValue() != postId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增岗位
     * @param post
     * @return
     */
    @Override
    public Integer insertPost(SysPost post) {
        return postMapper.insertPost(post);
    }

    /**
     * 修改岗位
     * @param post
     * @return
     */
    @Override
    public Integer updatePost(SysPost post) {
        return postMapper.updatePost(post);
    }

    /**
     * 删除
     * @param postIds
     * @return
     */
    @Override
    public Integer deletePostByIds(Long[] postIds) {
        for (Long postId : postIds)
        {
            SysPost post = selectPostById(postId);
            if (countUserPostById(postId) > 0)
            {
                throw new CustomException(String.format("%1$s已分配,不能删除", post.getPostName()));
            }
        }
        return postMapper.deletePostByIds(postIds);
    }

    private Integer countUserPostById(Long postId) {
        return userPostMapper.countUserPostById(postId);
    }
}
