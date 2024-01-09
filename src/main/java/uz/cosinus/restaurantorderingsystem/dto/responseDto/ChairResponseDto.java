package uz.cosinus.restaurantorderingsystem.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChairResponseDto {
    private UUID chairId;
    private UUID tableId;
    private Integer tableNumber;
    private LocalDateTime createdDate;
}
