package store.service;

import store.domain.Item;
import store.enums.PromotionEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemCheckService {
    public List<Item> checkNameMatching(Map.Entry<String, Integer> purchaseItem, List<Item> items) {
        List<Item> itemsMatchingName = new ArrayList<>();
        for (Item item : items) {
            if (item.getItemName().equals(purchaseItem.getKey())) {
                
                itemsMatchingName.add(item);
            }
        }
        return itemsMatchingName;
    }

    public boolean checkPromotionPossible(Map.Entry<String, Integer> purchaseItem, List<Item> items) {
        boolean isPromotionPossible = false;
        for (Item item : items) {
            if (item.getItemPromotionName() != null) {
                if (item.getItemQuantity() > 0) {
                    isPromotionPossible=true;
                }
            }
        }
        return isPromotionPossible;
    }

    public boolean checkPromotionItemsCondition(Map.Entry<String, Integer> purchaseItem, List<Item> items) {
        boolean isPromotionPossible = false;
        for (Item item : items) {
            if (item.getItemPromotionName() != null) {
                isPromotionPossible = checkPromotionCondition(purchaseItem, item);
            }
        }
        return isPromotionPossible;
    }

    public boolean checkPromotionCondition(Map.Entry<String, Integer> purchaseItem, Item item) {
        if (item.getItemPromotionName().equals(PromotionEnum.TWO_PLUS_ONE)) {
            if (purchaseItem.getValue() % 3 == 0) {
                return true;
            }
        }
        if (item.getItemPromotionName().equals(PromotionEnum.MD)) {
            if (purchaseItem.getValue() % 2 == 0) {
                return true;
            }
        }
        if (item.getItemPromotionName().equals(PromotionEnum.FLASHING_SALE)) {
            if (purchaseItem.getValue() % 2 == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean checkQuantity(Map.Entry<String, Integer> purchaseItem, List<Item> items) {
        boolean itemQuantityPossible = false;
        for (Item item : items) {
            itemQuantityPossible = (item.getItemQuantity()>=purchaseItem.getValue());
        }
        return itemQuantityPossible;
    }
}