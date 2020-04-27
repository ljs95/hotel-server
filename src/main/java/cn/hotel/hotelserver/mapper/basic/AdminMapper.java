package cn.hotel.hotelserver.mapper.basic;

import cn.hotel.hotelserver.model.basic.Admin;
import cn.hotel.hotelserver.vo.basic.AdminPagination;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 管理员mapper类
 *
 * @author Johnson
 * @date 2020/4/1
 */
public interface AdminMapper {

    /**
     * 添加管理员
     * @param admin
     */
    Integer insert(Admin admin);

    /**
     * 根据账号查询用户
     * @param username
     * @return
     */
    Admin loadUserByUsername(String username);

    /**
     * 分页查询
     * @param pagination
     * @return
     */
    IPage<Admin> table(Page<Admin> page, AdminPagination pagination);

    /**
     * 更新管理员
     * @param admin
     */
    void update(Admin admin);

    /**
     * 根据id查询管理员
     * @param id
     * @return
     */
    Admin findById(Integer id);

    /**
     * 删除管理员
     * @param id
     */
    void delete(Integer id);

    /**
     * 添加管理员角色
     * @param roleIds
     */
    void insertAdminRole(Integer adminId, List<Integer> roleIds);

    /**
     * 删除管理员角色
     * @param id
     */
    void deleteAdminRole(Integer id);
}
