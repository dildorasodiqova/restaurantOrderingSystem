package uz.cosinus.restaurantorderingsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.cosinus.restaurantorderingsystem.service.tableService.TableService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/table")
public class TableController {
    private final TableService tableService;
}

