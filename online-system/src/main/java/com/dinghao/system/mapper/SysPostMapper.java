package com.dinghao.system.mapper;


import com.dinghao.system.domain.SysPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 岗位信息 数据层
 * 
 * @author dinghao
 */
public interface SysPostMapper
{
    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    List<SysPost> selectPostAll();

    List<Integer> selectPostListByUserId(@Param("userId") Long userId);

    SysPost selectPostById(@Param("postId") Long postId);

    SysPost checkPostNameUnique(@Param("postName") String postName);

    List<SysPost> selectPostList(SysPost post);

    SysPost checkPostCodeUnique(@Param("postCode") String postCode);

    Integer insertPost(SysPost post);

    Integer updatePost(SysPost post);

    Integer deletePostByIds(@Param("postIds") Long[] postIds);
}
