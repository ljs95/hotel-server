package cn.hotel.hotelserver.model.basic;

import cn.hotel.hotelserver.model.basic.abstracts.AbstractRole;

import java.util.List;

/**
* @author Johnson
* @date 2020/3/22
*/
public class Role extends AbstractRole {

    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
