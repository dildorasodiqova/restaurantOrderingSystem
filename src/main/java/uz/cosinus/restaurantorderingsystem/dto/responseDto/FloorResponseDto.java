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
public class FloorResponseDto {
    private UUID floorId;
    private Integer  number;
    private String description;
    private LocalDateTime createdDate;
}
