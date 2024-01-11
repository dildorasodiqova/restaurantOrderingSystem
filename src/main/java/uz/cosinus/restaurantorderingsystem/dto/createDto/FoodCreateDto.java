package uz.cosinus.restaurantorderingsystem.dto.createDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodCreateDto {
    private String name;
    private Double price;
    private String description;
    private String timeToGetReady;
}
