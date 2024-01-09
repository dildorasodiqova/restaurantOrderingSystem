package uz.cosinus.restaurantorderingsystem.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderTableResponseDto {
    private UUID orderId;
    private Integer intendedOfPerson;
    private LocalDateTime timeBooked;
    private UserResponseDto user;
    private TableResponseDto table;
    private LocalDateTime createdDate;
}
