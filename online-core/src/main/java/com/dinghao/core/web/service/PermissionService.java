package com.dinghao.core.web.service;

import com.dinghao.common.core.domain.model.LoginUser;
import com.dinghao.common.utils.ServletUtils;
import com.dinghao.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * @author dinghao 自定义权限认证 ss取自SpringSecurity首字母
 */
@Service("ss")
public class PermissionService {

    /**
     * 所有权限标识
     */
    private static final String ALL_PERMISSION = "*:*:*";
    /**
     * 管理员角色权限标识
     */
    private static final String SUPER_ADMIN = "admin";
    /**
     * 角色分界
     */
    private static final String ROLE_DELIMETER = ",";
    /**
     * 权限分界
     */
    private static final String PERMISSION_DELIMETER = ",";

    @Autowired
    private TokenService tokenService;

    /**
     * 验证用户是否具有某权限
     */
    public boolean hasPermi(String permission){
        if (StringUtils.isEmpty(permission))
        {
            return false;
        }
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getPermissions()))
        {
            return false;
        }
        return hasPermissions(loginUser.getPermissions(), permission);

    }


    /**
     * 判断是否包含权限
     *
     * @param permissions 权限列表
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    private boolean hasPermissions(Set<String> permissions, String permission)
    {
        return permissions.contains(ALL_PERMISSION) || permissions.contains(StringUtils.trim(permission));
    }
}
