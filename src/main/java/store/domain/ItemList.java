package store.domain;

import java.util.ArrayList;
import java.util.List;

public class ItemList {
    private final List<Item> items;

    public ItemList() {
        this.items = new ArrayList<>();
    }

    public List<Item> saveItems() {
        items.add(new Item("콜라", 1000, 10, "탄산2+1", null));
        items.add(new Item("콜라", 1000, 10, null, null));
        items.add(new Item("사이다", 1000, 8, "탄산2+1", null));
        items.add(new Item("사이다", 1000, 7, null, null));
        items.add(new Item("오렌지주스", 1800, 9, null, "MD추천상품"));
        items.add(new Item("오렌지주스", 1800, 0, null, null));
        items.add(new Item("탄산수", 1200, 5, "탄산2+1", null));
        items.add(new Item("탄산수", 1200, 0, null, null));
        items.add(new Item("물", 500, 10, null, null));
        items.add(new Item("비타민워터", 1500, 6, null, null));
        items.add(new Item("감자칩", 1500, 5, null, "반짝할인"));
        items.add(new Item("감자칩", 1500, 5, null, null));
        items.add(new Item("초코바", 1200, 5, null, "MD추천상품"));
        items.add(new Item("초코바", 1200, 5, null, null));
        items.add(new Item("에너지바", 2000, 5, null, null));
        items.add(new Item("정식도시락", 6400, 8, null, null));
        items.add(new Item("컵라면", 1700, 1, null, "MD추천상품"));
        items.add(new Item("컵라면", 1700, 10, null, null));

        return items;
    }
}
