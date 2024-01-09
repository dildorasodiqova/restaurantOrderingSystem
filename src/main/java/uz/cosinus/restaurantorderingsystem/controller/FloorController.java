package uz.cosinus.restaurantorderingsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.cosinus.restaurantorderingsystem.service.floorService.FloorService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/floors")
public class FloorController {
    private final FloorService floorService;
}
