package uz.cosinus.restaurantorderingsystem.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uz.cosinus.restaurantorderingsystem.dto.createDto.MailDto;
import uz.cosinus.restaurantorderingsystem.service.orderTableService.OrderTableService;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledNotification {
    private final OrderTableService orderTableService;
    private final MailService mailService;

    /**
     * bu method ogohlantiradi kitobini olib ketishi un
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void checkSubmissionDate(String email) {
        MailDto mailDto = new MailDto("Today is the last day you can pick up your reserved book. Otherwise it will not be booked for you.", booking.getUser().getEmail());
        mailService.sendMail(mailDto);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void removeBooking(){
        bookingService.delete();
    }



}
