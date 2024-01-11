package uz.cosinus.restaurantorderingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.cosinus.restaurantorderingsystem.entities.FloorEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FloorRepository extends JpaRepository<FloorEntity, UUID> {
    Boolean existsAllByNumber(Integer number);
    List<FloorEntity> findAllByIsActiveTrue();
    Optional<FloorEntity> findByNumber(Integer number);
    @Transactional
    @Modifying
    @Query("UPDATE floor f SET f.isActive = :trueOrFalse WHERE f.number = :floorNumber")
    int disActiveOrActive(Integer floorNumber, Boolean trueOrFalse);
}
