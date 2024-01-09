package uz.cosinus.restaurantorderingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.cosinus.restaurantorderingsystem.entities.ChairEntity;

import java.util.List;
import java.util.UUID;

public interface ChairRepository extends JpaRepository<ChairEntity, UUID> {
   List<ChairEntity> findAllByTableIdAndIsActiveTrue(UUID table_id);

}
