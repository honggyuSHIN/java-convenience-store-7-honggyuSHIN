package store.enums;

public class PromotionEnum {
    public static final String TWO_PLUS_ONE = "탄산2+1";
    public static final String MD = "MD추천상품";
    public static final String FLASHING_SALE = "반짝할인";

    public static String NON_PROMOTION_FULL_QUANTITY="NON_PROMOTION_FULL_QUANTITY";
    public static String NON_PROMOTION_INSUFFICIENT_QUANTITY="NON_PROMOTION_INSUFFICIENT_QUANTITY";
    public static String YES_PROMOTION_FULL_QUANTITY_FULL_CONDITION="YES_PROMOTION_FULL_QUANTITY_FULL_CONDITION";
    public static String YES_PROMOTION_INSUFFICIENT_QUANTITY="YES_PROMOTION_INSUFFICIENT_QUANTITY";
    public static String YES_PROMOTION_INSUFFICIENT_CONDITION="YES_PROMOTION_INSUFFICIENT_CONDITION";

    String message;

    public String getMessage() {
        return message;
    }
}
