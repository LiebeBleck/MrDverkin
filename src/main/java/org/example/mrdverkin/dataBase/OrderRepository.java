package org.example.mrdverkin.dataBase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT * FROM \"order\" o WHERE o.date_order BETWEEN CURRENT_DATE AND CURRENT_DATE + INTERVAL '7' DAY", nativeQuery = true)
    List<Order> findOrdersOlderThan7Days();

}
