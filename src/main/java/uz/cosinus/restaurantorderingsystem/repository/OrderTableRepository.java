package uz.cosinus.restaurantorderingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.cosinus.restaurantorderingsystem.entities.OrderTable;

import java.util.UUID;

public interface OrderTableRepository extends JpaRepository<OrderTable , UUID> {

}
