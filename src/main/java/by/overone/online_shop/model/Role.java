package by.overone.online_shop.model;

public enum Role {
    CUSTOMER("CUSTOMER"),
    ADMINISTRATOR("ADMINISTRATOR"),
    ADMIN("ADMIN");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
