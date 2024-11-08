package store.service;

import store.domain.Item;
import store.domain.ItemList;
import store.dto.ItemDto;

import java.util.List;


public class ItemService {

    public List<ItemDto> saveItemList() {
        ItemList itemList = new ItemList();
        List<Item> items = itemList.saveItems();
        return items.stream().map(item -> {
            return new ItemDto(item.getItemName(), item.getItemPrice(), item.getItemQuantity(), item.getItemPromotion());
        }).toList();

    }
}
