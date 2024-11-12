package store.service;

import store.domain.Item;
import store.domain.ItemList;
import store.dto.ItemDto;
import store.view.InputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ItemService {

    public List<Item> saveItemList(ItemList itemList) {
        List<Item> items = itemList.saveItems();
        return items;
    }

    public List<Item> getItems(ItemList itemList) {
        List<Item> items = itemList.getItems();
        return items;
    }

    public List<ItemDto> getItemsDto(List<Item> items) {
        return items.stream().map(item -> {
            return new ItemDto(item.getItemName(), item.getItemPrice(), item.getItemQuantity(), item.getItemPromotionName());
        }).toList();
    }

    public Map<String, Integer> productInput() {
        String productNames = InputView.inputItemName();
        Map<String, Integer> productNameData = parseItem(productNames);
        return productNameData;
    }

    public Map<String, Integer> parseItem(String productNames) {
        Map<String, Integer> productList = new HashMap<>();
        productNames = productNames.replaceAll("[\\[\\]]", "");
        String[] products = productNames.split(",");
        for (String product : products) {
            String[] productInfo = product.split("-");
            productList.put(productInfo[0], Integer.parseInt(productInfo[1]));
        }
        return productList;
    }

    public void buyItem(Item matchingItem, Map.Entry<String, Integer> entry) {
        matchingItem.buyItem(entry.getValue());
    }

    public void buyItemCustom(Item matchingItem, Map.Entry<String, Integer> entry, int quantity) {
        matchingItem.buyItem(quantity);
    }

    public void buyPromotionAndGeneralItem(String userChoice, int insufficientQuantity, List<Item> itemsMatchingName, Map.Entry<String, Integer> entry) {
        Item promotionItem = itemsMatchingName.get(0);
        Item nonPromotionItem = itemsMatchingName.get(1);
        buyItemCustom(promotionItem, entry, promotionItem.getItemQuantity());
        buyItemCustom(nonPromotionItem, entry, insufficientQuantity);
    }
}
