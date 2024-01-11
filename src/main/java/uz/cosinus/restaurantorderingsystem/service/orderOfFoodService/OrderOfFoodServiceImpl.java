package uz.cosinus.restaurantorderingsystem.service.orderOfFoodService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.cosinus.restaurantorderingsystem.dto.createDto.OrderFoodCreateDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.FoodResponseDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.OrderFoodResponseDto;
import uz.cosinus.restaurantorderingsystem.entities.FoodEntity;
import uz.cosinus.restaurantorderingsystem.entities.OrderOfFoodEntity;
import uz.cosinus.restaurantorderingsystem.entities.OrderTableEntity;
import uz.cosinus.restaurantorderingsystem.enums.FoodStatus;
import uz.cosinus.restaurantorderingsystem.exception.DataNotFoundException;
import uz.cosinus.restaurantorderingsystem.repository.OrderFoodRepository;
import uz.cosinus.restaurantorderingsystem.repository.OrderTableRepository;
import uz.cosinus.restaurantorderingsystem.service.foodService.FoodService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class OrderOfFoodServiceImpl implements OrderOfFoodService{
    private final OrderFoodRepository orderFoodRepository;
    private final OrderTableRepository orderTableRepository;
    private final FoodService foodService;
    private final ModelMapper modelMapper;

    @Override
    public String create(OrderFoodCreateDto dto) {
        OrderOfFoodEntity parse = parse(dto);
        orderFoodRepository.save(parse);
        return "Successfully";
    }

    @Override
    public OrderFoodResponseDto getById(UUID orderTableId) {
        OrderOfFoodEntity orderFoodNotFound = orderFoodRepository.findById(orderTableId).orElseThrow(() -> new DataNotFoundException("Order food not found"));
        return parse(orderFoodNotFound);
    }

    @Override
    public OrderFoodResponseDto orderOfTable(UUID orderTableId) {
        OrderTableEntity table = orderTableRepository.findById(orderTableId).orElseThrow(()->new DataNotFoundException("order table not found"));
        OrderOfFoodEntity order = orderFoodRepository.findAllByIsActiveTrueAndFloorNumberAndTableNumber(table.getTable().getFloor().getNumber(), table.getTable().getTableNumber())
                .orElseThrow(()-> new DataNotFoundException("Order not fount"));

        List<FoodResponseDto> food = foodService.parse(order.getFood());
        return new OrderFoodResponseDto(order.getId(), food, order.getFloorNumber(), order.getTableNumber(),order.getCreatedDate(), order.getFoodStatus());
    }

    @Override
    public boolean findById(UUID orderTableId) {
        OrderTableEntity table = orderTableRepository.findById(orderTableId).orElseThrow(()->new DataNotFoundException("order table not found"));
        Optional<OrderOfFoodEntity> order = orderFoodRepository.findAllByIsActiveTrueAndFloorNumberAndTableNumber(table.getTable().getFloor().getNumber(), table.getTable().getTableNumber());
        return order.isPresent();
    }

    @Override
    public String cancelFood(String foodName, UUID orderId) {
        OrderOfFoodEntity order = orderFoodRepository.findById(orderId)
                .orElseThrow(() -> new DataNotFoundException("Order not found"));

        List<FoodEntity> foodList = order.getFood();
        FoodEntity foodToRemove = null;

        for (FoodEntity food : foodList) {
            if (food.getName().equals(foodName)) {
                foodToRemove = food;
                break;
            }
        }

        if (foodToRemove != null) {
            foodList.remove(foodToRemove);
            orderFoodRepository.save(order);
            return "Food cancelled successfully";
        } else {
            return "Food not found in the order";
        }
    }

    @Override
    public OrderFoodResponseDto getOrderByTable(Integer tableNumber, Integer floorNumber) {
      OrderOfFoodEntity order = orderFoodRepository.findAllByIsActiveTrueAndFloorNumberAndTableNumber(tableNumber, floorNumber)
                .orElseThrow(()-> new DataNotFoundException("Order not fount"));
      return parse(order);
    }

    @Override
    public String updateFoodStatus(UUID orderId, String statusType) {
        OrderOfFoodEntity order = orderFoodRepository.findById(orderId)
                .orElseThrow(()-> new DataNotFoundException("Order not fount"));
        FoodStatus foodStatus = FoodStatus.valueOf(statusType);
        order.setFoodStatus(foodStatus);
        orderFoodRepository.save(order);
        return "SuccessFully";
    }

    @Override
    public void remove(UUID id) {
        OrderOfFoodEntity order = orderFoodRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("Order not fount"));
        orderFoodRepository.delete(order);
    }


    private OrderOfFoodEntity parse(OrderFoodCreateDto dto){
        return modelMapper.map(dto, OrderOfFoodEntity.class);
    }
    private OrderFoodResponseDto parse(OrderOfFoodEntity entity){
        List<FoodResponseDto> food = foodService.parse(entity.getFood());
        return new OrderFoodResponseDto(entity.getId(), food, entity.getFloorNumber(), entity.getTableNumber(), entity.getCreatedDate(), entity.getFoodStatus());

    }
}
