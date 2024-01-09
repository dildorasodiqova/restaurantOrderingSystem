package uz.cosinus.restaurantorderingsystem.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tables")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TableEntity extends BaseEntity{
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FloorEntity floor;

    private Integer tableNumber;
    private Integer countOfChair;

}
