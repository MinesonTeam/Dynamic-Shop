package kz.hxncus.mc.dynamicshop.mapper;

import kz.hxncus.mc.dynamicshop.domain.Item;
import kz.hxncus.mc.dynamicshop.domain.Shop;
import kz.hxncus.mc.dynamicshop.model.ItemDTO;
import kz.hxncus.mc.dynamicshop.model.ShopDTO;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

public class ShopMapper {
    public static ShopDTO toDTO(@NonNull Shop shop) {

        List<ItemDTO> items = shop.getItems() == null
                ? null
                : shop.getItems()
                    .stream()
                    .map(ItemMapper::toDTO)
                    .collect(Collectors.toList());
        return new ShopDTO(
                Long.parseLong(shop.getId()),
                shop.getId(),
                items
        );
    }

    public static Shop toShop(@NonNull ShopDTO dto) {


        List<Item> items = dto.getItems() == null
                ?null
                :dto.getItems()

                    .stream()
                    .map(ItemMapper::toItem)
                    .collect(Collectors.toList());

        return new Shop(
                String.valueOf(dto.getId()),
                items
        );
    }
}
