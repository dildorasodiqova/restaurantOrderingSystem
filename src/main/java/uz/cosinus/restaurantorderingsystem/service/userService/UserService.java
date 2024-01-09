package uz.cosinus.restaurantorderingsystem.service.userService;

import uz.cosinus.restaurantorderingsystem.dto.createDto.*;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.JwtResponse;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.UserResponseDto;
import uz.cosinus.restaurantorderingsystem.entities.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {

    String deleteUser(UUID userId);
    UserResponseDto signUp(UserCreateDto dto);
    void emailSend(UserEntity userEntity);
    UserEntity findById(UUID userId);
    UserResponseDto getById(UUID userId);
    List<UserResponseDto> getAll(Integer page, Integer size);
    String getAccessToken(String refreshToken, UUID userId);
    JwtResponse signIn(VerifyDtoP verifyDtoP);
    String getVerificationCode(String email);
    UserResponseDto verify(VerifyDto verifyDto);
    SubjectDto verifyToken(String token);
    String forgetPassword(ForgetDto forgetDto);

}
