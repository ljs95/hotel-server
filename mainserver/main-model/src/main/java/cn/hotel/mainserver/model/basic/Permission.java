package cn.hotel.mainserver.model.basic;

import cn.hotel.mainserver.model.basic.abstracts.AbstractPermission;

import java.util.List;

public class Permission extends AbstractPermission {

    private List<Permission> children;

    private List<Role> roles;

    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}