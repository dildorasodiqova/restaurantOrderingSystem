package uz.cosinus.restaurantorderingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.cosinus.restaurantorderingsystem.entities.FloorEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FloorRepository extends JpaRepository<FloorEntity, UUID> {
    Boolean existsAllByNumber(Integer number);
    List<FloorEntity> findAllByIsActiveTrue();
    Optional<FloorEntity> findByNumber(Integer number);
}
