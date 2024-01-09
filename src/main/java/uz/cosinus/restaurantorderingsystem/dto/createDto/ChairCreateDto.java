package uz.cosinus.restaurantorderingsystem.dto.createDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.cosinus.restaurantorderingsystem.entities.TableEntity;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChairCreateDto {
    private UUID tableId;
    private int count;
}
