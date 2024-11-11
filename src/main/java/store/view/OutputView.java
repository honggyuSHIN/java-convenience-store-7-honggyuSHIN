package store.view;

import store.dto.ItemDto;
import store.dto.UserBuyItemsDto;

import java.text.NumberFormat;
import java.util.ArrayList;
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

    public static void printInsufficientQuantity(String nonpromotionName, Integer nonpromotionQuantity) {
        System.out.printf("현재 %s 제품의 재고는 %d개입니다. 죄송합니다.\n", nonpromotionName, nonpromotionQuantity);
    }

    public static void printRecipt(List<UserBuyItemsDto> userBuyItemsDtos, boolean isMembership) {
        System.out.println("==============W 편의점================");
        System.out.printf("%-10s\t%-5s\t%-10s%n", "상품명", "수량", "금액");
        int totalQuantity = 0;
        int totalPrice = 0;
        int eventDiscount = 0;
        int membershipDiscount = 0;
        List<UserBuyItemsDto> freeItems = new ArrayList<>();
        for (UserBuyItemsDto item : userBuyItemsDtos) {
            System.out.printf("%-10s\t%-5d\t%,d%n", item.getItemName(), item.getItemQuantity(), item.getItemPrice() * item.getItemQuantity());
            totalQuantity += item.getItemQuantity();
            totalPrice += item.getItemPrice() * item.getItemQuantity();
            if (item.getItemPromotionType() != null) {
                freeItems.add(item);
                if (item.getItemPromotionType().equals("탄산2+1")) {
                    eventDiscount += item.getItemPrice() * item.getItemQuantity() / 3;
                    continue;
                }
                eventDiscount += item.getItemPrice() * item.getItemQuantity() / 2;
                continue;
            }
            if (item.getItemPromotionType() == null) {
                if (isMembership) {
                    membershipDiscount += item.getItemPrice() * item.getItemQuantity() / 10;
                }
            }
        }
        System.out.println("=============증정===============");
        for (UserBuyItemsDto freeItem : freeItems) {
            if (freeItem.getItemPromotionType().equals("탄산2+1")) {
                System.out.printf("%-10s\t%-5d\t%,d%n", freeItem.getItemName(), freeItem.getItemQuantity() / 3, freeItem.getItemPrice());
                continue;
            }
            System.out.printf("%-10s\t%-5d\t%,d%n", freeItem.getItemName(), freeItem.getItemQuantity() / 2, freeItem.getItemPrice());
        }
        System.out.println("================================");
        System.out.printf("%-10s\t%-5d\t%,d%n", "총구매액", totalQuantity, totalPrice);
        System.out.printf("%-10s\t%-5s\t%,d%n", "행사할인", "", eventDiscount);
        System.out.printf("%-10s\t%-5s\t%,d%n", "멤버십할인", "\t\t", membershipDiscount);
        System.out.printf("%-10s\t\t%,d%n", "내실돈", totalPrice - eventDiscount - membershipDiscount);
    }
}
