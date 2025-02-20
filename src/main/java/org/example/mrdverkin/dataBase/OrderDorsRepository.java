package org.example.mrdverkin.dataBase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDorsRepository extends JpaRepository<OrderDoors,Long> {
    @Query("select door from OrderDoors")
    List<Door> findAllDoors();
}
