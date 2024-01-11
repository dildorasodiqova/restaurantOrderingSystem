package uz.cosinus.restaurantorderingsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.cosinus.restaurantorderingsystem.dto.createDto.OrderTableCreateDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.OrderTableResponseDto;
import uz.cosinus.restaurantorderingsystem.service.orderTableService.OrderTableService;

import java.security.Principal;
import java.util.List;
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


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('OFINSAND')")
    @GetMapping("/findById/{orderTableId}")
    public ResponseEntity<OrderTableResponseDto> findById(@PathVariable UUID orderTableId) {
        return ResponseEntity.ok(orderTableService.getById(orderTableId));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get-all/{floorId}")
    public List<OrderTableResponseDto> getAll(
            @PathVariable UUID floorId,
            @RequestParam(value = "page", defaultValue = "0")
            int page,
            @RequestParam(value = "size", defaultValue = "5")
            int size
    ) {
        return orderTableService.getAll(page, size);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/cancel/{orderTableId}")
    public String disActive(@PathVariable UUID orderTableId) {
        return orderTableService.cancelOrderTable(orderTableId);
    }





}
