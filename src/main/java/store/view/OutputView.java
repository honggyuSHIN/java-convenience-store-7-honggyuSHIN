package store.view;

import store.dto.ItemDto;

import java.util.List;

public class OutputView {
    public static void printItemList(List<ItemDto> items) {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.");

        for (ItemDto item : items) {
            String promotion = item.getItemPromotion() != null ? item.getItemPromotion() : "";
            String etc = item.getItemEtc() != null ? item.getItemEtc() : "";

            System.out.printf("%s %d %d개 %s %s%n", item.getItemName(), item.getItemPrice(), item.getItemQuantity(), promotion, etc);
        }
    }
}
