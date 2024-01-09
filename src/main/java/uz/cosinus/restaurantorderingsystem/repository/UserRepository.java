package uz.cosinus.restaurantorderingsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.cosinus.restaurantorderingsystem.entities.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Page<UserEntity> findAllByIsActiveTrue(PageRequest pageRequest);
    Optional<UserEntity> findByEmail(String email);
}
