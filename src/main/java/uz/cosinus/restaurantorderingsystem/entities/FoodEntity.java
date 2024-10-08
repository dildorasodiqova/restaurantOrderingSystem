package uz.cosinus.restaurantorderingsystem.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Double discountPercentage = 0.0;
}
