package kz.hxncus.mc.dynamicshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ShopDTO {
    private final long id;
    private String name;
    private List<ItemDTO> items;
}
