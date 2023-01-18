package com.epam.bolotin.periodicals.model.db.entity;

/**
 * @author: Viacheslav Bolotin
 * @date: 20.12.2022
 */
public enum UserRole {
    ADMIN, CLIENT;

    public static UserRole getRole(User user) {
        int roleId = user.getUserRoleId();
        return UserRole.values()[--roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
