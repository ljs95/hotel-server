package cn.hotel.hotelserver.vo.basic;

import cn.hotel.hotelserver.vo.Pagination;

/**
 * 角色分页查询类
 * @author Johnson
 * @date 2020/04/01/ 09:27:02
 */
public class RolePagination extends Pagination {

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
