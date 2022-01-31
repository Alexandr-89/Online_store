package by.overone.online_shop.model;

public enum Status {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
