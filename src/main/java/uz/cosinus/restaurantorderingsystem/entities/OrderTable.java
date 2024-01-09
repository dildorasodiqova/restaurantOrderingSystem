package uz.cosinus.restaurantorderingsystem.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "orderTables")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderTable extends BaseEntity {
    @ManyToOne(cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private TableEntity table;

    @ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    private UserEntity user;

    private Integer intendedOfPerson;
    private LocalDateTime timeBooked; /// bu vaqtga band qilgani

}
