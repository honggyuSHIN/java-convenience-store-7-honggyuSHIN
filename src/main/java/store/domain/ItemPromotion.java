package store.domain;

import java.time.LocalDate;

public class ItemPromotion {
    private final String promotionName;
    private final int buyItemCount;
    private final int freeItemCount;
    private final LocalDate promotionStartDate;
    private final LocalDate promotionEndDate;

    public ItemPromotion(String promotionName, int buyItemCount, int freeItemCount, LocalDate promotionStartDate, LocalDate promotionEndDate) {
        this.promotionName = promotionName;
        this.buyItemCount = buyItemCount;
        this.freeItemCount = freeItemCount;
        this.promotionStartDate = promotionStartDate;
        this.promotionEndDate = promotionEndDate;
    }

    public String getPromotionName() {
        return promotionName;
    }
}
