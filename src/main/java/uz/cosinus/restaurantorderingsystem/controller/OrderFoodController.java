package uz.cosinus.restaurantorderingsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.cosinus.restaurantorderingsystem.dto.createDto.FloorCreateDto;
import uz.cosinus.restaurantorderingsystem.dto.createDto.OrderFoodCreateDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.OrderFoodResponseDto;
import uz.cosinus.restaurantorderingsystem.service.orderOfFoodService.OrderOfFoodService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orderFood")
public class OrderFoodController {
    private final OrderOfFoodService orderOfFoodService;

    @PreAuthorize("hasAuthority('USER') or hasAuthority('OFINSAND')")
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody OrderFoodCreateDto createDto) {
        return ResponseEntity.ok(orderOfFoodService.create(createDto));
    }


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('OFINSAND')")
    @GetMapping("/getById/{floorId}")
    public ResponseEntity<OrderFoodResponseDto> getById(@PathVariable UUID floorId) {
        return ResponseEntity.ok(orderOfFoodService.getById(floorId));
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('OFINSAND')")
    @GetMapping("/orderOfTable/{orderTableId}")
    public ResponseEntity<OrderFoodResponseDto> orderOfTable(@PathVariable UUID orderTableId) {
        return ResponseEntity.ok(orderOfFoodService.orderOfTable(orderTableId));
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/cancel/{orderTableId}")
    public ResponseEntity<String> disActive(@RequestParam String foodName, @RequestParam UUID orderId) {
        return ResponseEntity.ok(orderOfFoodService.cancelFood(foodName, orderId));
    }

    @PreAuthorize("hasAuthority('OFINSAND')")
    @DeleteMapping("/updateFoodStatus/{orderTableId}")
    public ResponseEntity<String> updateFoodStatus(@RequestParam UUID orderId, @RequestParam String statusType) {
        return ResponseEntity.ok(orderOfFoodService.updateFoodStatus(orderId , statusType));
    }


}
