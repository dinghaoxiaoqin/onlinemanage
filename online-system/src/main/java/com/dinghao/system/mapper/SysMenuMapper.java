package com.dinghao.system.mapper;




import com.dinghao.common.core.domain.entity.SysMenu;
import org.apache.ibatis.annotations.Param;



import java.util.List;

/**
 * 菜单表 数据层
 *
 * @author ruoyi
 */
public interface SysMenuMapper
{


    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public List<String> selectMenuPermsByUserId(Long userId);


    SysMenu checkMenuNameUnique(@Param("menuName") String menuName, @Param("parentId") Long parentId);

    Integer insertMenu(SysMenu menu);

    List<SysMenu> selectMenuList(SysMenu menu);

    List<SysMenu> selectMenuListByUserId(SysMenu menu);

    List<Integer> selectMenuListByRoleId(@Param("roleId") Long roleId);

    SysMenu selectMenuById(@Param("menuId") Long menuId);

    List<SysMenu> selectMenuTreeAll();

    List<SysMenu> selectMenuTreeByUserId(Long userId);

    Integer hasChildByMenuId(@Param("menuId") Long menuId);

    Integer checkMenuExistRole(@Param("menuId") Long menuId);

    Integer deleteMenuById(@Param("menuId") Long menuId);
}
