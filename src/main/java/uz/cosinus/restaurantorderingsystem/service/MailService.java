package uz.cosinus.restaurantorderingsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uz.cosinus.restaurantorderingsystem.dto.createDto.MailDto;


@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    public void sendMail(MailDto dto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(dto.getEmail());
        simpleMailMessage.setText(dto.getMessage());
        mailSender.send(simpleMailMessage);
    }

}
