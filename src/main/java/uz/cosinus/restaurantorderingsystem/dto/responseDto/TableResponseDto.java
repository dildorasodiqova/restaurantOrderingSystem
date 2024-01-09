package uz.cosinus.restaurantorderingsystem.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.cosinus.restaurantorderingsystem.entities.FloorEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TableResponseDto {
    private UUID tableId;
    private Integer floorNumber;
    private Integer tableNumber;
    private Integer countOfChair;
    private LocalDateTime createdDate;
}
