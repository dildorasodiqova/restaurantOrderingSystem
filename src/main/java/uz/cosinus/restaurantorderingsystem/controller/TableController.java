package uz.cosinus.restaurantorderingsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.cosinus.restaurantorderingsystem.dto.createDto.TableCreateDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.TableResponseDto;
import uz.cosinus.restaurantorderingsystem.service.tableService.TableService;

import java.time.LocalDateTime;
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



    @Operation(
            description = "This method return a free tables  between 2 date.",
            method = "GET method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"USER"})
    )
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getFreeTable/{floorId}")
    public List<TableResponseDto> getFreeTable(
            @PathVariable UUID floorId,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate,
            @RequestParam(value = "page", defaultValue = "0")
            int page,
            @RequestParam(value = "size", defaultValue = "5")
            int size
    ) {
        return tableService.getFreeTable(startDate, endDate, page, size, floorId);
    }



    @Operation(
            description = "This method dis active  a table info.",
            method = "PUT method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"ADMIN"})
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/disActive/{tableId}")
    public String disActive(@PathVariable UUID tableId) {
        return tableService.disActive(tableId);
    }


    @Operation(
            description = "This method activates the table data .",
            method = "PUT method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"ADMIN"})
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/active/{tableId}")
    public String delete(@PathVariable UUID tableId) {
        return tableService.active(tableId);
    }





}

