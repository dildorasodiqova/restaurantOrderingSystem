package uz.cosinus.restaurantorderingsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.cosinus.restaurantorderingsystem.enums.FoodStatus;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "orderOfFood")
public class OrderOfFoodEntity extends BaseEntity {
    @ManyToMany(cascade = CascadeType., fetch = FetchType.EAGER)
    private List<FoodEntity> food;

    private Integer floorNumber;
    private Integer tableNumber;
    private FoodStatus foodStatus = FoodStatus.IN_PREPARATION;
}
