package uz.cosinus.restaurantorderingsystem.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity(name = "hisob")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HisobEntity extends BaseEntity {
    private List<UUID> foodsId;
    private

}
