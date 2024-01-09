package uz.cosinus.restaurantorderingsystem.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "floor")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FloorEntity extends BaseEntity { // bu qavat qoshadi. agar bino bir necha qavatli bo'lsa qavsi qavatdan stol buyurtma qilishini aytishi va admin stol qoshishi uchun
    private Integer  number;
    private String description;
}
