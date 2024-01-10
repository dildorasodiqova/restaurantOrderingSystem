package uz.cosinus.restaurantorderingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.cosinus.restaurantorderingsystem.entities.OrderTableEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface OrderTableRepository extends JpaRepository<OrderTableEntity, UUID> {
    // yani shu vaqt orasida order bo'lmasa.  o'sha stolni order qila oladi.
    // bunday qilganimni sababi kimdur band qilmoqchi bo'lgan tablega kimdur yarim soat avval o'tirishga zakas qilgan bo'lsa
    // aniqki ular yarim soatda turib ketisha olmaydi. agar u aytagn vaqtdan yarim soat keyin order bor bo'lsa hozirgi user turib keta olmaydi. shuning uchin yarim soat avvalgi vaqtni va yarim soat kengi vaqtni tanladim.
    boolean existsAllByTimeBookedBetweenAndTableId(LocalDateTime timeBooked, LocalDateTime timeBooked2, UUID table_id);

}
