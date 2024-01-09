package uz.cosinus.restaurantorderingsystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "orderOfFood")
public class OrderOfFoodEntity extends BaseEntity {
    @ManyToMany()
    private List<FoodEntity> food;
}
