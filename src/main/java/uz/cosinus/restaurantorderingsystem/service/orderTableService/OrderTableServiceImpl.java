package uz.cosinus.restaurantorderingsystem.service.orderTableService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import uz.cosinus.restaurantorderingsystem.repository.OrderTableRepository;

@Service
@RequiredArgsConstructor
public class OrderTableServiceImpl implements OrderTableService{
    private final OrderTableRepository orderTableRepository;
}
