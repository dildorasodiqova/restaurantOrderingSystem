package uz.cosinus.restaurantorderingsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.cosinus.restaurantorderingsystem.dto.createDto.FloorCreateDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.FloorResponseDto;
import uz.cosinus.restaurantorderingsystem.service.floorService.FloorService;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/floors")
public class FloorController {
    private final FloorService floorService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody FloorCreateDto createDto) {
        return ResponseEntity.ok(floorService.create(createDto));
    }


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('OFINSAND')")
    @GetMapping("/findById/{floorId}")
    public ResponseEntity<FloorResponseDto> findById(@PathVariable UUID floorId) {
        return ResponseEntity.ok(floorService.getById(floorId));
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('OFINSAND')")
    @GetMapping("/get-all")
    public List<FloorResponseDto> getAll() {
        return floorService.getAll();
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/disActiveOrActive")
    public ResponseEntity<String> disActiveOrActive(@RequestParam Integer floorNumber, @RequestParam boolean trueOrFalse ) {
        return ResponseEntity.ok(floorService.disActiveOrActive(floorNumber,trueOrFalse));
    }



}
