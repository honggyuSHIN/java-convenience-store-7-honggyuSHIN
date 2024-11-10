package store.domain;

public class Item {
    private final String itemName;
    private final int itemPrice;
    private int itemQuantity;
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

    public String getItemPromotionName() {
        if(itemPromotion == null) {
            return null;
        }
        return itemPromotion.getPromotionName();
    }

    public ItemPromotion getItemPromotion() {
        return itemPromotion;
    }

    public void buyItem(Integer quantity) {
        if (itemQuantity < quantity) {
            throw new IllegalArgumentException("구매할 수량이 재고보다 많습니다.");
        }
        itemQuantity -= quantity;
    }


}
