package uz.cosinus.restaurantorderingsystem.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.cosinus.restaurantorderingsystem.enums.FoodStatus;

@Entity(name = "foods")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodEntity extends BaseEntity{
    private String name;
    private Double price;
    private String description;
    private String timeToGetReady;
}
