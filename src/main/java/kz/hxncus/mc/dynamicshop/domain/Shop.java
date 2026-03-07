package kz.hxncus.mc.dynamicshop.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Shop {
    private String id;
    private List<Item> items;
}
