package uz.cosinus.restaurantorderingsystem.service.floorService;

import uz.cosinus.restaurantorderingsystem.dto.createDto.FloorCreateDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.FloorResponseDto;
import uz.cosinus.restaurantorderingsystem.entities.FloorEntity;

import java.util.List;
import java.util.UUID;

public interface FloorService {
    String create(FloorCreateDto dto);
    List<FloorResponseDto> getAll();
    String disActive(Integer floorNumber);

    FloorEntity findById(UUID floorId);
}
