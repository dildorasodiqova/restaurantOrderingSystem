package uz.cosinus.restaurantorderingsystem.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uz.cosinus.restaurantorderingsystem.dto.createDto.MailDto;
import uz.cosinus.restaurantorderingsystem.entities.OrderTableEntity;
import uz.cosinus.restaurantorderingsystem.repository.OrderTableRepository;
import uz.cosinus.restaurantorderingsystem.service.orderTableService.OrderTableService;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledNotification {
    private final OrderTableService orderTableService;
    private final MailService mailService;
    private final OrderTableRepository orderTableRepository;

    /**
     * bu method agar user tabel zkas qilib kelishilgan vaqtda kelamasa . bu zakas otkaz qilinadi. va emailiga habar jo'natiladi.
     */
    @Scheduled(cron = "0 0 */2 * * *") // Har 2 soatda bir
    public void cleanupOldOrders() {
        LocalDateTime twoHoursAgo = LocalDateTime.now().minusHours(2);
        List<OrderTableEntity> oldOrders = orderTableService.findByOrderTimeBeforeAndFoodIsNull(twoHoursAgo);

        for (OrderTableEntity order : oldOrders) {
            MailDto mailDto = new MailDto("Your reservation for this table has been canceled because you did not arrive at the agreed time.", order.getUser().getEmail());
            mailService.sendMail(mailDto);

            orderTableRepository.delete(order);
        }
    }



}
