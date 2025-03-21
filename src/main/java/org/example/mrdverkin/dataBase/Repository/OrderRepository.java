package org.example.mrdverkin.dataBase.Repository;

import org.example.mrdverkin.dataBase.Entitys.Installer;
import org.example.mrdverkin.dataBase.Entitys.Order;
import org.example.mrdverkin.dataBase.Entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT 70 - COALESCE(SUM(o.inDoorQuantity), 0) FROM Order o WHERE o.dateOrder = :data")
    int numberOfInDoorsToInstallation(@Param("data") LocalDate data);

    @Query("SELECT 2 - COALESCE(SUM(o.frontDoorQuantity), 0) FROM Order o WHERE o.dateOrder = :data")
    int numberOfFrontDoorsToInstallation(@Param("data") LocalDate data);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Order o set o.installer = :newInstaller where o.id = :orderId")
    void updateInstaller(@Param("newInstaller") Installer installer, @Param("orderId") Long orderId);

    @Query(value = "SELECT o FROM Order o WHERE o.installer IS null")
    List<Order> findByInstallerNull();

    @Query(value = "SELECT o FROM Order o WHERE o.user = :actualUser")
    List<Order> findOrdersByUser(@Param("actualUser") User user);

}
