package uz.cosinus.restaurantorderingsystem.service.chairService;

import uz.cosinus.restaurantorderingsystem.dto.createDto.ChairCreateDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.ChairResponseDto;

import java.util.List;
import java.util.UUID;

public interface ChairService {
    String create(ChairCreateDto dto);
    List<ChairResponseDto> chairsOfTable(UUID tableId);
}
