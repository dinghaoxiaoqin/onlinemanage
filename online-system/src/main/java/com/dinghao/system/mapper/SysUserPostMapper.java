package com.dinghao.system.mapper;


import com.dinghao.system.domain.SysUserPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户与岗位关联表 数据层
 * 
 * @author ruoyi
 */
public interface SysUserPostMapper
{


    /**
     * 批量新增用户岗位信息
     * 
     * @param userPostList 用户角色列表
     * @return 结果
     */
    public int batchUserPost(List<SysUserPost> userPostList);

    Integer countUserPostById(@Param("postId") Long postId);
}
