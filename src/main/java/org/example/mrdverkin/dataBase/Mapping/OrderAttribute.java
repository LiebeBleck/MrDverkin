package org.example.mrdverkin.dataBase.Mapping;

import lombok.Data;
import org.example.mrdverkin.dataBase.Entitys.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class OrderAttribute {
    private Long id;
    private String fullName;
    private String address;
    private String phone;
    private LocalDate dateOrder;
    private int frontDoorQuantity;
    private int inDoorQuantity;
    private String installerName;
    private String userName;

    // Метод для преобразования Order в OrderAttribute
    public static OrderAttribute fromOrder(Order order) {
        OrderAttribute orderAttr = new OrderAttribute();
        orderAttr.setId(order.getId());
        orderAttr.setFullName(order.getFullName());
        orderAttr.setAddress(order.getAddress());
        orderAttr.setPhone(order.getPhone());
        orderAttr.setDateOrder(order.getDateOrder());
        orderAttr.setFrontDoorQuantity(order.getFrontDoorQuantity());
        orderAttr.setInDoorQuantity(order.getInDoorQuantity());
        if (order.getUser() != null) {
            orderAttr.setUserName(order.getUser().getFullName());
        }
        if (order.getInstaller() != null) {
            orderAttr.setInstallerName(order.getInstaller().getFullName());
        }
        return orderAttr;
    }

    // Метод для преобразования списка Order в список OrderAttribute
    public static List<OrderAttribute> fromOrderList(List<Order> orders) {
        return orders.stream()
                .map(OrderAttribute::fromOrder)
                .collect(Collectors.toList());
    }
}
