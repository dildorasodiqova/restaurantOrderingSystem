package uz.cosinus.restaurantorderingsystem.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodResponseDto {
     private UUID id;
     private String name;
     private Double price;
     private String description;
     private String timeToGetReady;
     private Boolean active;
     private LocalDateTime createdDate;



}
