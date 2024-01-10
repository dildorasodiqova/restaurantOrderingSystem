package uz.cosinus.restaurantorderingsystem.service.orderTableService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.cosinus.restaurantorderingsystem.dto.createDto.MailDto;
import uz.cosinus.restaurantorderingsystem.dto.createDto.OrderTableCreateDto;
import uz.cosinus.restaurantorderingsystem.entities.OrderTableEntity;
import uz.cosinus.restaurantorderingsystem.entities.TableEntity;
import uz.cosinus.restaurantorderingsystem.entities.UserEntity;
import uz.cosinus.restaurantorderingsystem.exception.BadRequestException;
import uz.cosinus.restaurantorderingsystem.repository.OrderTableRepository;
import uz.cosinus.restaurantorderingsystem.service.MailService;
import uz.cosinus.restaurantorderingsystem.service.tableService.TableService;
import uz.cosinus.restaurantorderingsystem.service.userService.UserService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderTableServiceImpl implements OrderTableService{
    private final OrderTableRepository orderTableRepository;
    private final UserService userService;
    private final TableService tableService;
    private final MailService mailService;


    @Override
    public String createOrderTable(String name, OrderTableCreateDto createDto, UUID tableId) {
        UserEntity user = userService.findById(UUID.fromString(name));
        TableEntity table = tableService.finById(tableId);

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




}
