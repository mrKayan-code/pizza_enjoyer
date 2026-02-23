package model.order;

public enum OrderStatus {
    PENDING("Не завершен"), RECIEVED("Принят"), SCHEDULED("Отложен"), PAID("Оплачен");

    String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
