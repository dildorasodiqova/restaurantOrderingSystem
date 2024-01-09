package uz.cosinus.restaurantorderingsystem.dto.createDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.cosinus.restaurantorderingsystem.entities.FloorEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TableCreateDto {
    private Integer tableNumber;
    private Integer countOfChair; // 4 ta stoli bor deydi.
    private Integer count;  /// bu yani shunaqa stoldan nechta qoshish kerakligi yani admin bitta zalga 15 ta 4 ta stuli bor stol qoshmoqchi shuni bittalab qoshmasligi uchun.
}
