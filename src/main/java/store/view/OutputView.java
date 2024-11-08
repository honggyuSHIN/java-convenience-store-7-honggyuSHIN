package store.view;

import store.dto.ItemDto;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class OutputView {
    public static void printItemList(List<ItemDto> items) {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.\n");

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA);

        for (ItemDto item : items) {
            if (item.getItemPromotion() == null) {
                System.out.printf("%s %s원 %d개 %n", item.getItemName(), numberFormat.format(item.getItemPrice()), item.getItemQuantity());
            } else {
                System.out.printf("%s %s원 %d개 %s %n", item.getItemName(), numberFormat.format(item.getItemPrice()), item.getItemQuantity(), item.getItemPromotion());

            }
        }
    }
}
