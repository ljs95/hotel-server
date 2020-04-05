package cn.hotel.hotelserver.model.basic;

import cn.hotel.hotelserver.model.basic.abstracts.AbstractPermission;

import java.util.List;

public class Permission extends AbstractPermission {

    private List<Permission> children;

    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }
}