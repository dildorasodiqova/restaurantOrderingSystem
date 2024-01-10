package uz.cosinus.restaurantorderingsystem.service.orderTableService;

import uz.cosinus.restaurantorderingsystem.dto.createDto.OrderTableCreateDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.UserResponseDto;

import java.util.UUID;

public interface OrderTableService {
    String createOrderTable(String name, OrderTableCreateDto createDto, UUID tableId);

}
