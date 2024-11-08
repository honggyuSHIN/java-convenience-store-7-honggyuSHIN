package store.domain;

import java.time.LocalDate;

public class Item {
    private final String itemName;
    private final int itemPrice;
    private final int itemQuantity;
    private final ItemPromotion itemPromotion;


    public Item(String itemName, int itemPrice, int itemQuantity, ItemPromotion itemPromotion) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.itemPromotion = itemPromotion;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public String getItemPromotion() {
        if(itemPromotion == null) {
            return null;
        }
        return itemPromotion.getPromotionName();
    }


}
