package epam.nazaruk.final_project.db;

import epam.nazaruk.final_project.db.entity.User;

public enum Role {
    ADMIN,
    GUEST,
    CLIENT,
    MASTER;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId-1];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
