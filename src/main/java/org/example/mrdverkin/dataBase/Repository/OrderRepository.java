package org.example.mrdverkin.dataBase.Repository;

import lombok.Data;
import org.example.mrdverkin.dataBase.Entitys.Installer;
import org.example.mrdverkin.dataBase.Entitys.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT 70 - COALESCE(SUM(o.quantity), 0) FROM Order o WHERE o.dateOrder = :data")
    int numberOfDoorsToInstallation(@Param("data") LocalDate data);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Order o set o.installer = :newInstaller where o.id = 1 ")
    void updateInstaller(@Param("newInstaller") Installer installer);

    @Query(value = "SELECT o FROM Order o WHERE o.installer IS null")
    List<Order> findByInstallerNull();
}
