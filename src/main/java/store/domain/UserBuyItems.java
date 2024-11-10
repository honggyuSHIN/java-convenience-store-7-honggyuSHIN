package store.domain;

public class UserBuyItems {
    private final String itemName;
    private final int itemQuantity;
    private final String itemPromotionType;
    private final int itemPrice;


    public UserBuyItems(String itemName, int itemQuantity, String itemPromotionType, int itemPrice) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemPromotionType = itemPromotionType;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public String getItemPromotionType() {
        return itemPromotionType;
    }

    public int getItemPrice() {
        return itemPrice;
    }
}
