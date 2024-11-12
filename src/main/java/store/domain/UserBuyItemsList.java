package store.domain;

import java.util.ArrayList;
import java.util.List;

public class UserBuyItemsList {
    List<UserBuyItems> userBuyItemsList;

    public UserBuyItemsList() {
        this.userBuyItemsList = new ArrayList<>();
    }

    public void addUserBuyItems(UserBuyItems userBuyItems) {
        userBuyItemsList.add(userBuyItems);
    }

    public List<UserBuyItems> getUserBuyItemsList() {
        return userBuyItemsList;
    }
}
