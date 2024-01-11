package uz.cosinus.restaurantorderingsystem.service.orderTableService;

import uz.cosinus.restaurantorderingsystem.dto.createDto.OrderTableCreateDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.OrderTableResponseDto;
import uz.cosinus.restaurantorderingsystem.entities.OrderTableEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderTableService {
    String createOrderTable(String name, OrderTableCreateDto createDto, UUID tableId);

    OrderTableResponseDto getById(UUID orderTableId);
    List<OrderTableEntity> findByOrderTimeBeforeAndFoodIsNull(LocalDateTime twoHoursAgo);
    String cancelOrderTable(UUID orderId);
    List<OrderTableResponseDto> getAll(int page, int size);

    boolean findOrderOfTable(UUID tableId);

}
