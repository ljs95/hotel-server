package cn.hotel.hotelserver.mapper.basic;

import cn.hotel.hotelserver.model.basic.Role;
import cn.hotel.hotelserver.vo.basic.RolePagination;

import java.util.List;

/**
 * 角色mapper类
 *
 * @author Johnson
 * @date 2020/4/1
 */
public interface RoleMapper {

    /**
     * 添加角色
     * @param role
     */
    Integer insert(Role role);

    /**
     * 根据管理员id查询角色列表
     * @param adminId
     * @return List
     */
    List<Role> getRolesByAdminId(Integer adminId);

    /**
     * 分页查询
     * @param pagination
     * @return List
     */
    List<Role> table(RolePagination pagination);

    /**
     * 分页查询统计
     * @param pagination
     * @return
     */
    Integer tableCount(RolePagination pagination);

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    Role findById(Integer id);

    /**
     * 更新角色
     * @param role
     */
    void update(Role role);

    /**
     * 查询所有角色
     * @return
     */
    List<Role> all();

    /**
     * 删除角色
     * @param id
     */
    void delete(Integer id);

    /**
     * 根绝角色id删除管理员角色中间表数据
     * @param id
     */
    void deleteAdminRoleByRoleId(Integer id);
}
