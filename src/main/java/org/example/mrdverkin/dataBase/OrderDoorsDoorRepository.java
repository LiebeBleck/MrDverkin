package org.example.mrdverkin.dataBase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDoorsDoorRepository extends JpaRepository<OrderDoorsDoor, Long> {
    @Query("select door from OrderDoorsDoor")
    List<Door> findAllDoors();
    @Query("SELECT o FROM OrderDoorsDoor o ORDER BY o.id DESC LIMIT 1")
    OrderDoorsDoor findLastOrderDoors();

}
