package uz.cosinus.restaurantorderingsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.cosinus.restaurantorderingsystem.dto.createDto.TableCreateDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.TableResponseDto;
import uz.cosinus.restaurantorderingsystem.service.tableService.TableService;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/table")
public class TableController {
    private final TableService tableService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create/{floorId}")
    public ResponseEntity<String> createOrderTable(
                                                   @RequestBody TableCreateDto createDto,
                                                   @PathVariable UUID floorId) {
        return ResponseEntity.ok(tableService.create( floorId, createDto));
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('OFINSAND')")
    @GetMapping("/findById/{tableId}")
    public ResponseEntity<TableResponseDto> findById(@PathVariable UUID tableId) {
        return ResponseEntity.ok(tableService.getById(tableId));
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('OFINSAND')")
    @GetMapping("/get-all/{floorId}")
    public List<TableResponseDto> getAll(
            @PathVariable UUID floorId,
            @RequestParam(value = "page", defaultValue = "0")
            int page,
            @RequestParam(value = "size", defaultValue = "5")
            int size
            ) {
        return tableService.getAll(page, size, floorId);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/disActive/{tableId}")
    public String disActive(@PathVariable UUID tableId) {
        return tableService.disActive(tableId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/active/{tableId}")
    public String delete(@PathVariable UUID tableId) {
        return tableService.active(tableId);
    }





}

