package uz.cosinus.restaurantorderingsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.cosinus.restaurantorderingsystem.dto.createDto.OrderTableCreateDto;
import uz.cosinus.restaurantorderingsystem.service.orderTableService.OrderTableService;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orderTable")
public class OrderTableController {
    private final OrderTableService orderTableService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/createOrderTable/{tableId}")
    public ResponseEntity<String> createOrderTable(Principal principal,
                                                   @RequestBody OrderTableCreateDto createDto,
                                                   @PathVariable UUID tableId) {
        return ResponseEntity.ok(orderTableService.createOrderTable(principal.getName(), createDto, tableId));
    }
}
