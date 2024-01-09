package uz.cosinus.restaurantorderingsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.cosinus.restaurantorderingsystem.service.orderTableService.OrderTableService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orderTable")
public class OrderTableController {
    private final OrderTableService orderTableService;
}
