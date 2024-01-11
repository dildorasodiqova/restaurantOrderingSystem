package uz.cosinus.restaurantorderingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.cosinus.restaurantorderingsystem.entities.HisobEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface HisobRepository extends JpaRepository<HisobEntity, UUID> {

    @Query("SELECT COALESCE(SUM(h.price), 0) FROM hisob h WHERE DATE(h.createdDate) = DATE(:currentDate)")
    Double dayTotalShot(@Param("currentDate") LocalDateTime currentDate);
}
