package uz.cosinus.restaurantorderingsystem.dto.responseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.cosinus.restaurantorderingsystem.entities.FoodEntity;
import uz.cosinus.restaurantorderingsystem.enums.FoodStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderFoodResponseDto {
    private UUID id;
    private List<FoodResponseDto> foods;

    private Integer floorNumber;
    private Integer tableNumber;
    private LocalDateTime createdDate;
    private FoodStatus foodStatus;
}
