package uz.cosinus.restaurantorderingsystem.service.hisobService;

import org.springframework.web.bind.annotation.RequestParam;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.HisobResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface HisobService {
    HisobResponseDto getHisobOfTable(Integer tableNumber,  Integer floorNumber);

    Double dayTotalShot(LocalDateTime localDateTime);

}
