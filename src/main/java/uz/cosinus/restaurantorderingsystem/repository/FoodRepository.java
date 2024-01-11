package uz.cosinus.restaurantorderingsystem.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.cosinus.restaurantorderingsystem.entities.FoodEntity;

import java.util.List;
import java.util.UUID;

public interface FoodRepository extends JpaRepository<FoodEntity, UUID> {
    @Transactional
    @Modifying
    @Query("UPDATE foods f SET f.isActive = :trueOrFalse WHERE f.id = :foodId")
    int disActiveOrActive(UUID foodId, Boolean trueOrFalse);

    List<FoodEntity> getAllByIsActiveTrue(PageRequest pageRequest);

    @Transactional
    @Modifying
    @Query("UPDATE foods f SET f.name = :name, f.description = :description, f.price = :price WHERE f.id = :foodId")
    int updateFood(@Param("name") String name,
                   @Param("description") String description,
                   @Param("price") Double price,
                   @Param("foodId") UUID foodId);

    boolean existsAllByName(String name);

    boolean existsAllByNameAndId(String name, UUID id);
}
