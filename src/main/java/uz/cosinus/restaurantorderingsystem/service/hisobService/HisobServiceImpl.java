package uz.cosinus.restaurantorderingsystem.service.hisobService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.*;
import uz.cosinus.restaurantorderingsystem.entities.HisobEntity;
import uz.cosinus.restaurantorderingsystem.enums.FoodStatus;
import uz.cosinus.restaurantorderingsystem.repository.HisobRepository;
import uz.cosinus.restaurantorderingsystem.service.orderOfFoodService.OrderOfFoodService;
import uz.cosinus.restaurantorderingsystem.service.orderTableService.OrderTableService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HisobServiceImpl implements HisobService {
    private final OrderOfFoodService orderOfFoodService;
    private final HisobRepository hisobRepository;

    @Override
    public HisobResponseDto getHisobOfTable(Integer tableNumber , Integer floorNumber) {
        List<FoodInfoForHisob> names = new ArrayList<>();
        Double totalAmount = 0D;
        OrderFoodResponseDto orderFood = orderOfFoodService.getOrderByTable(tableNumber,floorNumber);
        List<FoodResponseDto> foods = orderFood.getFoods();
        for (FoodResponseDto food : foods) {
            totalAmount += food.getPrice();
            names.add(new FoodInfoForHisob(food.getName(), food.getPrice()));

            orderOfFoodService.remove(orderFood.getId());
            hisobRepository.save(new HisobEntity(orderFood.getId(), food.getPrice()));
        }
        return new HisobResponseDto(names, totalAmount, orderFood.getTableNumber());
    }

    @Override
    public Double dayTotalShot(LocalDateTime localDateTime) {
        return hisobRepository.dayTotalShot(localDateTime);
    }
}
