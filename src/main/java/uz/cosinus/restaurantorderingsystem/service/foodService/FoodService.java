package uz.cosinus.restaurantorderingsystem.service.foodService;

import uz.cosinus.restaurantorderingsystem.dto.createDto.FoodCreateDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.FoodResponseDto;
import uz.cosinus.restaurantorderingsystem.entities.FoodEntity;

import java.util.List;
import java.util.UUID;

public interface FoodService {
    String create(FoodCreateDto dto);
    String disActiveOrActive(UUID foodId, Boolean trueOrFalse);
    List<FoodResponseDto> getAll(int page, int size0);

    String update(FoodCreateDto dto, UUID foodId);
    List<FoodResponseDto> parse(List<FoodEntity> all);

    FoodResponseDto getById(UUID foodId);
}
