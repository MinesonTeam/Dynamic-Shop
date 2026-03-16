package kz.hxncus.mc.dynamicshop.mapper;

import kz.hxncus.mc.dynamicshop.domain.Item;
import kz.hxncus.mc.dynamicshop.model.ItemDTO;
import lombok.NonNull;
import org.bukkit.Material;

public class ItemMapper {
    public static ItemDTO toDTO(@NonNull Item item) {

        return new ItemDTO(0, item.getType().name(), item.getPrice());

    }

    public static Item toItem(@NonNull ItemDTO dto){

        return new Item (Material.valueOf(dto.getName()), dto.getPrice());
    }

}
