package org.example.mrdverkin.dataBase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDoorsDoorRepository extends JpaRepository<OrderDoors, Long> {
    @Query("select door from OrderDoors")
    List<Door> findAllDoors();
    @Query("SELECT o FROM OrderDoors o ORDER BY o.id DESC LIMIT 1")
    OrderDoors findLastOrderDoors();

}
