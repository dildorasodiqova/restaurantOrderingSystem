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
public class UserResponseDto {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String userRole;
    private LocalDateTime createdDate;
}
