package uz.cosinus.restaurantorderingsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.cosinus.restaurantorderingsystem.dto.createDto.FoodCreateDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.FoodResponseDto;
import uz.cosinus.restaurantorderingsystem.service.foodService.FoodService;

import java.util.List;
import java.util.UUID;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/food")
public class FoodController {
    private final FoodService foodService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody FoodCreateDto createDto) {
        return ResponseEntity.ok(foodService.create(createDto));
    }


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('OFINSAND')")
    @GetMapping("/findById/{foodId}")
    public ResponseEntity<FoodResponseDto> findById(@PathVariable UUID foodId) {
        return ResponseEntity.ok(foodService.getById(foodId));
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('OFINSAND')")
    @GetMapping("/get-all")
    public List<FoodResponseDto> getAll(@RequestParam(value = "page", defaultValue = "0")
                                            int page,
                                        @RequestParam(value = "size", defaultValue = "5")
                                            int size) {
        return foodService.getAll(page, size);
    }


    @Operation(
            description = "This method dis active or active  a food status",
            method = "PUT method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"ADMIN"})
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/disActiveOrActive/{foodId}")
    public ResponseEntity<String> disActiveOrActive(@PathVariable UUID foodId, @RequestParam boolean trueOrFalse ) {
        return ResponseEntity.ok(foodService.disActiveOrActive(foodId,trueOrFalse));
    }



    @Operation(
            description = "This method update a food info. ",
            method = "PUT method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"ADMIN"})
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/updateFood/{foodId}")
    public ResponseEntity<String> updateFood(@PathVariable UUID foodId, @RequestBody FoodCreateDto dto) {
        return ResponseEntity.ok(foodService.update(dto, foodId));
    }


    @Operation(
            description = "this method changes the discount for meals. ",
            method = "PUT method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"ADMIN"})
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/updateDiscountPercentage/{foodId}")
    public ResponseEntity<String> updateDiscountPercentage(@PathVariable UUID foodId, @RequestBody Double percentage) {
        return ResponseEntity.ok(foodService.updateDiscountPercentage(foodId, percentage));
    }



}
