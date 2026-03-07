package kz.hxncus.mc.dynamicshop.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.Material;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Item {
    private Material type;
    private double price;

    public double getBuyPrice() {
        return price * 1.01;
    }

    public double getSellPrice() {
        return price / 1.01;
    }

    public void increasePrice() {
        price *= 1.005;
    }

    public void decreasePrice() {
        price /= 1.005;
    }
}
