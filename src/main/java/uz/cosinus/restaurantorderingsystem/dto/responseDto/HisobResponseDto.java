package uz.cosinus.restaurantorderingsystem.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HisobResponseDto {
    private List<FoodInfoForHisob> foodNames;
    private Double totalAmount;
    private Integer tableNumber;
}
