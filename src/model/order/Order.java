package model.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import model.common.Identifiable;

public class Order implements Identifiable {
    private final UUID id = UUID.randomUUID();
    private ArrayList<OrderItem> positions = new ArrayList<>();
    private LocalDateTime order_time;
    private LocalDateTime schedule_time = null;
    private OrderStatus order_status;

    public Order() {
        this.order_time = LocalDateTime.now();
        this.order_status = OrderStatus.PENDING;
    }

    public boolean addPosition(OrderItem position) {
        positions.add(position); //TODO(проверять на олинаковые пиццы и добавлять )
        return true;
    }

    public void removePosition(OrderItem position) {
        positions.remove(position);
    }

    public ArrayList<OrderItem> getPositions() {
        return positions;
    }

    public LocalDateTime getOrderTime() {
        return order_time;
    }
    
    public LocalDateTime getScheduleTime() {
        return schedule_time;
    }

    public void setScheduleTime(LocalDateTime schedule_time) {
        this.schedule_time = schedule_time;
        this.order_status = OrderStatus.SCHEDULED;
    }
    
    public boolean isScheduled() {
        return schedule_time != null;
    }

    public OrderStatus getStatus() {
        return order_status;
    }

    public void pay() {
        this.order_status = OrderStatus.PAID;
    }

    public double calculateCost() {
        double all_cost = 0;

        for (OrderItem position : positions) {
            all_cost += position.calculateCost();
        }

        return all_cost;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(String.format("Заказ id: %s\n", id.toString()));

        sb.append(String.format("Статус: %s\n", order_status.getName()));

        sb.append(String.format("Время заказа: %s\n", order_time.toString()));

        if (isScheduled()) {
            sb.append(String.format("Отложен до: %s\n", schedule_time.toString()));
        }

        sb.append(String.format("Позиции:\n"));

        for (OrderItem position : positions) {
            sb.append(String.format("    %s\n", position.toString()));
        }

        sb.append(String.format("К оплате: %.2fтнг", calculateCost()));
        
        return sb.toString();
    }

}
