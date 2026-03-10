package kz.hxncus.mc.dynamicshop.mapper;

import kz.hxncus.mc.dynamicshop.domain.Item;
import kz.hxncus.mc.dynamicshop.model.ItemDTO;
import org.bukkit.Material;

public class ItemMapper {
    public static ItemDTO toDTO(Item item) {
        if (item == null){
            return null;
        }
        return new ItemDTO(0, item.getType().name(), item.getPrice());

    }

    public static Item toItem(ItemDTO dto){
        if (dto == null){
            return null;
        }
        return new Item (Material.valueOf(dto.getName()), dto.getPrice());
    }

}
