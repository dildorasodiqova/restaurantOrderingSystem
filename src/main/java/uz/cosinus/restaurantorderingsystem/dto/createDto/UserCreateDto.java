package uz.cosinus.restaurantorderingsystem.dto.createDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreateDto {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
}
