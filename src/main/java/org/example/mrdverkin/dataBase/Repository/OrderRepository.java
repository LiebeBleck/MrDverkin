package org.example.mrdverkin.dataBase.Repository;

import lombok.Data;
import org.example.mrdverkin.dataBase.Entitys.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT 70 - COALESCE(SUM(o.quantity), 0) FROM Order o WHERE o.dateOrder = :data")
    int numberOfDoorsToInstallation(@Param("data") LocalDate data);
}
