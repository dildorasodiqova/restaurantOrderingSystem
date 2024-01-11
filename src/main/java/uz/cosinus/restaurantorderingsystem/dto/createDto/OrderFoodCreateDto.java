package uz.cosinus.restaurantorderingsystem.dto.createDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.cosinus.restaurantorderingsystem.entities.FoodEntity;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderFoodCreateDto {
    private List<FoodEntity> food;

    private Integer floorNumber;
    private Integer tableNumber;
}
