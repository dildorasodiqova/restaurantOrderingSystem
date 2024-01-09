package uz.cosinus.restaurantorderingsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.cosinus.restaurantorderingsystem.entities.TableEntity;

import java.util.UUID;

public interface TableRepository extends JpaRepository<TableEntity , UUID> {
    int countAllBy();
    Page<TableEntity> findAllByFloor_IdAndIsActiveTrue(UUID floor_id, Pageable pageable);
}
