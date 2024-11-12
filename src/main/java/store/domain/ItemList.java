package store.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ItemList {
    private final List<Item> items;

    public ItemList() {
        this.items = new ArrayList<>();
    }

    public List<Item> saveItems() {
        try {
            List<String> productsLines = Files.readAllLines(Paths.get("src/main/resources/products.md"));
            for (String line : productsLines.subList(1, productsLines.size())) {
                String[] item = line.split(",");
                addItem(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    public void addItem(String[] item) {
        String itemName = item[0];
        int itemPrice = Integer.parseInt(item[1]);
        int itemQuantity = Integer.parseInt(item[2]);
        if (!item[3].equals("null")) {
            String itemPromotion = item[3];
            try {
                items.add(new Item(itemName, itemPrice, itemQuantity, savePromotions(itemPromotion)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            items.add(new Item(itemName, itemPrice, itemQuantity, null));
        }

    }

    private ItemPromotion savePromotions(String itemPromotion) {
        ItemPromotion itemPromotionData = null;
        try {
            List<String> promotionsLines = Files.readAllLines(Paths.get("src/main/resources/promotions.md"));
            for (String line : promotionsLines.subList(1, promotionsLines.size())) {
                String[] promotion = line.split(",");
                if (promotion[0].equals(itemPromotion)) {
                    itemPromotionData = new ItemPromotion(promotion[0], Integer.parseInt(promotion[1]), Integer.parseInt(promotion[2]), LocalDate.parse(promotion[3]), LocalDate.parse(promotion[4]));
                }
            }
        } catch (Exception e) {
            System.out.println("오류 발생");
            e.printStackTrace();
        }
        return itemPromotionData;
    }

    public List<Item> getItems() {
        return items;
    }
}