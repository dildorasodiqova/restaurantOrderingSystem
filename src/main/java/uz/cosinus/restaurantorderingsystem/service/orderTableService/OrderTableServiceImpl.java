package uz.cosinus.restaurantorderingsystem.service.orderTableService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.cosinus.restaurantorderingsystem.dto.createDto.MailDto;
import uz.cosinus.restaurantorderingsystem.dto.createDto.OrderTableCreateDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.OrderTableResponseDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.TableResponseDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.UserResponseDto;
import uz.cosinus.restaurantorderingsystem.entities.OrderTableEntity;
import uz.cosinus.restaurantorderingsystem.entities.TableEntity;
import uz.cosinus.restaurantorderingsystem.entities.UserEntity;
import uz.cosinus.restaurantorderingsystem.exception.BadRequestException;
import uz.cosinus.restaurantorderingsystem.exception.DataNotFoundException;
import uz.cosinus.restaurantorderingsystem.repository.OrderTableRepository;
import uz.cosinus.restaurantorderingsystem.service.MailService;
import uz.cosinus.restaurantorderingsystem.service.orderOfFoodService.OrderOfFoodService;
import uz.cosinus.restaurantorderingsystem.service.tableService.TableService;
import uz.cosinus.restaurantorderingsystem.service.userService.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class  OrderTableServiceImpl implements OrderTableService{
    private final OrderTableRepository orderTableRepository;
    private final UserService userService;
    private final TableService tableService;
    private final MailService mailService;
    private final OrderOfFoodService orderOfFoodService;


    @Override
    public String createOrderTable(String name, OrderTableCreateDto createDto, UUID tableId) {
        UserEntity user = userService.findById(UUID.fromString(name));
        TableEntity table = tableService.finById(tableId);

        if (table.getCountOfChair() < createDto.getIntendedOfPerson()){
            throw new BadRequestException("This table is intended for " + table.getCountOfChair() + " people. Please select another table");
        }

        LocalDateTime timeBooked = createDto.getTimeBooked();
        LocalDateTime startDateTime = timeBooked.minusMinutes(30);  // Yarim soat avval
        LocalDateTime endDateTime = timeBooked.plusMinutes(30);  // Yarim soat keyin

        boolean existsOrderTableDuringTime = orderTableRepository.existsAllByTimeBookedBetweenAndTableId(startDateTime, endDateTime, tableId );

        if (existsOrderTableDuringTime) {
            throw new BadRequestException("This table is currently occupied by another user. Please choose another time");
        }
        orderTableRepository.save(new OrderTableEntity(table, user, createDto.getIntendedOfPerson(), timeBooked));

        MailDto mailDto = new MailDto("The table you selected has been booked just for you. Please arrive on time",user.getEmail());
        mailService.sendMail(mailDto);
        return "Successfully";
    }


    @Override
    public OrderTableResponseDto getById(UUID orderTableId) {
        OrderTableEntity byId = orderTableRepository.findById(orderTableId).orElseThrow(()-> new DataNotFoundException("This order not found"));
        UserResponseDto user = userService.getById(byId.getUser().getId());
        TableResponseDto table = tableService.getById(byId.getTable().getId());
        return new OrderTableResponseDto(byId.getId(), byId.getIntendedOfPerson(), byId.getTimeBooked(),user,table, byId.getCreatedDate()  );
    }

    @Override
    public List<OrderTableEntity> findByOrderTimeBeforeAndFoodIsNull(LocalDateTime twoHoursAgo) {
        List<OrderTableEntity> list =  new ArrayList<>();
        List<OrderTableEntity> all = orderTableRepository.findAllByCreatedDate(twoHoursAgo);
        for (OrderTableEntity table : all) {
            if (!orderOfFoodService.findById(table.getId())){  /// yani shu tableda haliham food zakas qilinmagan demak u odam kelmagan. shunda uni remove qilishimiz kerak. orderlardan
                list.add(table);
            }
        }
        return list;
    }

    @Override
    public String cancelOrderTable(UUID orderId) {
        OrderTableEntity byId = orderTableRepository.findById(orderId).orElseThrow(()-> new DataNotFoundException("This order not found"));
        orderTableRepository.delete(byId);
        return "Deleted";
    }

    @Override
    public List<OrderTableResponseDto> getAll(int page, int size) {
        PageRequest pageRequest =  PageRequest.of(page, size);
        List<OrderTableEntity> aTrue = orderTableRepository.findAllByIsActiveTrue(pageRequest);
        return parse(aTrue);
    }



    private List<OrderTableResponseDto> parse(List<OrderTableEntity> aTrue){
        List<OrderTableResponseDto> list = new ArrayList<>();
        for (OrderTableEntity o : aTrue) {
            UserResponseDto user = userService.getById(o.getUser().getId());
            TableResponseDto table = tableService.getById(o.getTable().getId());
            list.add(new OrderTableResponseDto(o.getId(), o.getIntendedOfPerson(), o.getTimeBooked(), user, table, o.getCreatedDate()));
        }
        return list;
    }


}
