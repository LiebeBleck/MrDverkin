package org.example.mrdverkin.dataBase.Repository;

import org.example.mrdverkin.dataBase.Entitys.Installer;
import org.example.mrdverkin.dataBase.Entitys.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InstallerRepository extends JpaRepository<Installer, Long> {
    @Query(value = "SELECT i FROM Installer i where i.fullName = :fullSelectName")
    Installer findByName(@Param("fullSelectName")String name);

    Installer findInstallersById(Long id);
}
