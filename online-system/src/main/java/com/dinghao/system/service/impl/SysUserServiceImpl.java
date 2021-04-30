package com.dinghao.system.service.impl;

import com.dinghao.common.constant.UserConstants;
import com.dinghao.common.core.domain.entity.SysUser;
import com.dinghao.common.utils.StringUtils;
import com.dinghao.system.domain.SysUserPost;
import com.dinghao.system.domain.SysUserRole;
import com.dinghao.system.mapper.SysUserMapper;
import com.dinghao.system.mapper.SysUserPostMapper;
import com.dinghao.system.mapper.SysUserRoleMapper;
import com.dinghao.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户 业务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysUserServiceImpl implements ISysUserService
{
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysUserPostMapper sysUserPostMapper;




    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByUserName(String userName)
    {
        return userMapper.selectUserByUserName(userName);
    }

    /**
     * 检查用户名是否唯一
     * @param userName
     * @return
     */
    @Override
    public String checkUserNameUnique(String userName) {
        int count = userMapper.checkUserNameUnique(userName);
        if (count > 0)
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 检查手机号是否唯一
     * @param sysUser
     * @return
     */
    @Override
    public String checkPhoneUnique(SysUser sysUser) {
        Long userId = StringUtils.isNull(sysUser.getUserId()) ? -1L : sysUser.getUserId();
        SysUser info = userMapper.checkPhoneUnique(sysUser.getPhonenumber());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 检查油箱是否唯一
     * @param sysUser
     * @return
     */
    @Override
    public String checkEmailUnique(SysUser sysUser) {
        Long userId = StringUtils.isNull(sysUser.getUserId()) ? -1L : sysUser.getUserId();
        SysUser info = userMapper.checkEmailUnique(sysUser.getEmail());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 添加用户
     * @param sysUser
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insertUser(SysUser sysUser) {

        // 新增用户信息
        int rows = userMapper.insertUser(sysUser);
        // 新增用户岗位关联
        insertUserPost(sysUser);
        // 新增用户与角色管理
        insertUserRole(sysUser);
        return rows;
    }

    /**
     * 获取用户列表
     * @param user
     * @return
     */
    @Override
    public List<SysUser> selectUserList(SysUser user) {

        return userMapper.selectUserList(user);
    }

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserById(Long userId) {
        return userMapper.selectUserById(userId);
    }

    /**
     * 新增用户角色信息
     * @param sysUser
     */
    private void insertUserRole(SysUser sysUser) {
        Long[] roles = sysUser.getRoleIds();
        if (StringUtils.isNotNull(roles))
        {
            // 新增用户与角色管理
            List<SysUserRole> list = new ArrayList<SysUserRole>();
            for (Long roleId : roles)
            {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(sysUser.getUserId());
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if (list.size() > 0)
            {
                userRoleMapper.batchUserRole(list);
            }
        }
    }

    /**
     * 新增用户与岗位关联
     * @param sysUser
     */
    private void insertUserPost(SysUser sysUser) {
        Long[] posts = sysUser.getPostIds();
        if (StringUtils.isNotNull(posts))
        {
            // 新增用户与岗位管理
            List<SysUserPost> list = new ArrayList<SysUserPost>();
            for (Long postId : posts)
            {
                SysUserPost up = new SysUserPost();
                up.setUserId(sysUser.getUserId());
                up.setPostId(postId);
                list.add(up);
            }
            if (list.size() > 0)
            {
                sysUserPostMapper.batchUserPost(list);
            }
        }
    }


}
