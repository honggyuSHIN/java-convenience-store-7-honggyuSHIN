package store.service;

import store.domain.UserBuyItems;
import store.domain.UserBuyItemsList;

public class ItemReciptService {
    public UserBuyItemsList makeRecipt() {
        return new UserBuyItemsList();
    }

    public void addRecipt(UserBuyItemsList userBuyItemsList, UserBuyItems userBuyItems) {
        userBuyItemsList.addUserBuyItems(userBuyItems);
    }

}
