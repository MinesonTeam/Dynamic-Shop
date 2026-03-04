package kz.hxncus.mc.dynamicshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemDTO {
    private final long id;
    private String name;
    private double price;
}
