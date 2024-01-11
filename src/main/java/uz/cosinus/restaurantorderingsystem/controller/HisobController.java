package uz.cosinus.restaurantorderingsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.FloorResponseDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.HisobResponseDto;
import uz.cosinus.restaurantorderingsystem.service.hisobService.HisobService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hisob")
public class HisobController {
    private final HisobService hisobService;

    @Operation(
            description = "This method get total amount of table.",
            method = "GET method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"OFINSAND", "USER"})
    )
    @PreAuthorize("hasAuthority('USER') or hasAuthority('OFINSAND')")
    @GetMapping("/getHisobOfTable")
    public ResponseEntity<HisobResponseDto> getHisobOfTable(@RequestParam Integer tableNumber, @RequestParam Integer floorNumber) {
        return ResponseEntity.ok(hisobService.getHisobOfTable(tableNumber, floorNumber));
    }

    @Operation(
            description = "It indicates the total amount of food consumed in 1 day. ",
            method = "GET method is supported",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"ADMIN"})
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/dayTotalShot")
    public ResponseEntity<Double> dayTotalShot(@RequestParam LocalDateTime localDateTime) {
        return ResponseEntity.ok(hisobService.dayTotalShot(localDateTime));
    }


}
