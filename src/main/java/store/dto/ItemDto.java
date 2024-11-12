package store.dto;

public class ItemDto {
    private final String itemName;
    private final int itemPrice;
    private final int itemQuantity;
    private final String itemPromotion;


    public ItemDto(String itemName, int itemPrice, int itemQuantity, String itemPromotion) {
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
        return itemPromotion;
    }
}
