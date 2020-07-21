package cn.hotel.mainserver.pageination.basic;

import cn.hotel.mainserver.model.basic.Role;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 角色分页查询类
 * @author Johnson
 * @date 2020/04/01/ 09:27:02
 */
public class RolePage extends Page<Role> {

    private String name;

    private String alias;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
