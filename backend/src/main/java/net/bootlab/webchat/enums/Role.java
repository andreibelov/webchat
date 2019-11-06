package net.bootlab.webchat.enums;

public enum Role {

    USER_ROLE("user_role"),
    GUEST_ROLE("guest_role"),
    ADMIN_ROLE("admin_role");

    private String name;

    Role(final String roleName) {
        this.name = roleName;
    }

    @Override
    public String toString() {
        return name;
    }
}
