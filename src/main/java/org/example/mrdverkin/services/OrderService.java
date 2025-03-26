package org.example.mrdverkin.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.mrdverkin.dataBase.Entitys.Order;
import org.example.mrdverkin.dataBase.Repository.InstallerRepository;
import org.example.mrdverkin.dataBase.Repository.OrderRepository;
import org.example.mrdverkin.dto.OrderAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InstallerRepository installerRepository;
    @Autowired
    private SellerService sellerService;

    public void deleteOrderById(Long id){
      orderRepository.deleteById(id);
    }

    public Order findOrderById(Long id){return orderRepository.findByOrderId(id);}

    public BindingResult updateOrder(Long id, OrderAttribute orderAttribute, BindingResult bindingResult) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();



            // Обновляем поля заказа
            existingOrder.setFullName(orderAttribute.getFullName());
            existingOrder.setAddress(orderAttribute.getAddress());
            existingOrder.setPhone(orderAttribute.getPhone());
            existingOrder.setMessageMainInstaller(orderAttribute.getMessageMainInstaller());
            existingOrder.setMessageMainInstaller(orderAttribute.getMessageMainInstaller());
            existingOrder.setDateOrder(orderAttribute.getDateOrder());
            existingOrder.setFrontDoorQuantity(orderAttribute.getFrontDoorQuantity());
            existingOrder.setInDoorQuantity(orderAttribute.getInDoorQuantity());
            if (orderAttribute.getInstallerName() != null) {
                existingOrder.setInstaller(installerRepository.findByName(orderAttribute.getInstallerName()));
            }
            sellerService.check(bindingResult,existingOrder);
            if (!bindingResult.hasErrors()) {
                orderRepository.save(existingOrder); // Сохраняем изменения
            }
        } else {
            throw new EntityNotFoundException("Заказ с ID " + id + " не найден");
        }
        return bindingResult;
    }
}
