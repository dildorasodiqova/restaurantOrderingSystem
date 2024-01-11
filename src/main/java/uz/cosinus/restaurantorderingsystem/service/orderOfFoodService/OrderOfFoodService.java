package uz.cosinus.restaurantorderingsystem.service.orderOfFoodService;

import uz.cosinus.restaurantorderingsystem.dto.createDto.OrderFoodCreateDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.OrderFoodResponseDto;

import java.util.UUID;

public interface OrderOfFoodService {
    String create(OrderFoodCreateDto dto);
    OrderFoodResponseDto orderOfTable(UUID orderTableId);
    OrderFoodResponseDto getById(UUID orderFoodId);
    boolean findById(UUID orderTableId);

    String cancelFood(String foodName,UUID orderId);

    OrderFoodResponseDto getOrderByTable(Integer tableNumber , Integer floorNumber);

    String updateFoodStatus(UUID orderId, String statusType);
}
