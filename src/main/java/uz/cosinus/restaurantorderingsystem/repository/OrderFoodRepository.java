package uz.cosinus.restaurantorderingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.cosinus.restaurantorderingsystem.entities.OrderOfFoodEntity;

import java.util.Optional;
import java.util.UUID;

public interface OrderFoodRepository extends JpaRepository<OrderOfFoodEntity, UUID> {

    Optional<OrderOfFoodEntity> findAllByIsActiveTrueAndFloorNumberAndTableNumber(Integer floorNumber, Integer tableNumber);

}
