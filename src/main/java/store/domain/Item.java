package store.domain;

public class Item {
    private final String itemName;
    private final int itemPrice;
    private final int itemQuantity;
    private final String itemPromotion;
    private final String itemEtc;

    public Item(String itemName, int itemPrice, int itemQuantity, String itemPromotion, String itemEtc) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.itemPromotion = itemPromotion;
        this.itemEtc= itemEtc;
    }
}
