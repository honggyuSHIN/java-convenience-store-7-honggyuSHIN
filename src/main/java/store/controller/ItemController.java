package store.controller;

import store.domain.Item;
import store.domain.ItemList;
import store.domain.UserBuyItems;
import store.domain.UserBuyItemsList;
import store.dto.ItemDto;
import store.dto.UserBuyItemsDto;
import store.enums.PromotionEnum;
import store.service.ItemCheckService;
import store.service.ItemReciptService;
import store.service.ItemService;
import store.view.InputView;
import store.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemController {
    private final ItemService itemService;
    private final ItemCheckService itemCheckService;
    private final ItemReciptService itemReciptService;

    public ItemController(ItemService itemService, ItemCheckService itemcheckService, ItemReciptService itemReciptService) {
        this.itemService = itemService;
        this.itemCheckService = itemcheckService;
        this.itemReciptService = itemReciptService;
    }

    public void start() {
        List<Item> items = itemSave();
        UserBuyItemsList userBuyItemsList = itemReciptService.makeRecipt();
        run(items, userBuyItemsList);
    }

    public void run(List<Item> items, UserBuyItemsList userBuyItemsList) {
        List<ItemDto> itemsDto=itemService.getItemsDto(items);
        OutputView.printItemList(itemsDto);
        boolean isMembership = false;
        Map<String, Integer> userRequestProducts = itemService.productInput();
        for(Map.Entry<String, Integer> entry : userRequestProducts.entrySet()) {
            List<Item> itemsMatchingName = itemCheckService.checkNameMatching(entry, items);
            System.out.println("itemsMatchingName.size() = " + itemsMatchingName.size());
            String nowItemSituation = matchItems(itemsMatchingName, entry);
            System.out.println("nowItemSituation = " + nowItemSituation);
            if (nowItemSituation == PromotionEnum.NON_PROMOTION_FULL_QUANTITY) {

                isMembership = buyGeneralItem(nowItemSituation, itemsMatchingName, entry, userBuyItemsList);
            }
            if (nowItemSituation == PromotionEnum.NON_PROMOTION_INSUFFICIENT_QUANTITY) {
                OutputView.printInsufficientQuantity(itemsMatchingName.get(0).getItemName(), itemsMatchingName.get(0).getItemQuantity());
            }
            if(nowItemSituation==PromotionEnum.YES_PROMOTION_FULL_QUANTITY_FULL_CONDITION){
                buySufficientPromotionCondition(itemsMatchingName, entry, userBuyItemsList);
            }
            if(nowItemSituation==PromotionEnum.YES_PROMOTION_INSUFFICIENT_QUANTITY){

                int insufficientQuantity = entry.getValue()-itemsMatchingName.get(0).getItemQuantity();
                String userChoice = InputView.printInsufficientPromotionQuantity(itemsMatchingName.get(0).getItemName(), insufficientQuantity);
                isMembership = buyInsufficientQuantity(userChoice ,insufficientQuantity, itemsMatchingName, entry , userBuyItemsList);
            }
            if(nowItemSituation==PromotionEnum.YES_PROMOTION_INSUFFICIENT_CONDITION){
                try {
                    isMembership = buyInsufficientCondition(itemsMatchingName, entry, userBuyItemsList);
                } catch (IllegalArgumentException e) {
                    System.out.println("죄송합니다. 프로모션 조건에 맞지 않습니다.");
                }
            }
        }
        List<UserBuyItemsDto> userBuyItemsDtos = new ArrayList<>();
        for(UserBuyItems userBuyItems : userBuyItemsList.getUserBuyItemsList()){
            userBuyItemsDtos.add(new UserBuyItemsDto(userBuyItems.getItemName(), userBuyItems.getItemQuantity(), userBuyItems.getItemPromotionType(), userBuyItems.getItemPrice()));
        }

        OutputView.printRecipt(userBuyItemsDtos, isMembership);
        endingMessage(items, userBuyItemsList);
    }

    private boolean buyInsufficientCondition(List<Item> itemsMatchingName, Map.Entry<String, Integer> entry, UserBuyItemsList userBuyItemsList) {
        boolean isMembership = false;
        int split = itemsMatchingName.get(0).getItemPromotion().getBuyItemCount() + itemsMatchingName.get(0).getItemPromotion().getFreeItemCount();
        if (entry.getValue() % split == itemsMatchingName.get(0).getItemPromotion().getBuyItemCount()) {
            if (InputView.askPromotionOneMore(entry.getKey()).equalsIgnoreCase("y")) {
                isMembership = true;
                itemService.buyItemCustom(itemsMatchingName.get(0), entry, entry.getValue() + 1);
                itemReciptService.addRecipt(userBuyItemsList, new UserBuyItems(itemsMatchingName.get(0).getItemName(), entry.getValue() + 1, itemsMatchingName.get(0).getItemPromotionName(), itemsMatchingName.get(0).getItemPrice()));
                return isMembership;
            }
        }
        itemService.buyItem(itemsMatchingName.get(0), entry);
        itemReciptService.addRecipt(userBuyItemsList, new UserBuyItems(itemsMatchingName.get(0).getItemName(), entry.getValue(), itemsMatchingName.get(0).getItemPromotionName(), itemsMatchingName.get(0).getItemPrice()));
        return isMembership;
    }

    private void buySufficientPromotionCondition(List<Item> itemsMatchingName, Map.Entry<String, Integer> entry, UserBuyItemsList userBuyItemsList) {
        itemService.buyItem(itemsMatchingName.get(0), entry);
        itemReciptService.addRecipt(userBuyItemsList, new UserBuyItems(itemsMatchingName.get(0).getItemName(), entry.getValue(),itemsMatchingName.get(0).getItemPromotionName(), itemsMatchingName.get(0).getItemPrice()));
    }

    public boolean buyInsufficientQuantity(String userChoice, int insufficientQuantity, List<Item> itemsMatchingName, Map.Entry<String, Integer> entry, UserBuyItemsList userBuyItemsList) {
        boolean isMembership = false;
        if (userChoice.equalsIgnoreCase("y")) {
            isMembership = true;
            itemService.buyPromotionAndGeneralItem(userChoice, insufficientQuantity, itemsMatchingName, entry);
            String membership = InputView.askAboutMembership();
            itemReciptService.addRecipt(userBuyItemsList, new UserBuyItems(itemsMatchingName.get(0).getItemName(), entry.getValue(), itemsMatchingName.get(0).getItemPromotionName(), itemsMatchingName.get(0).getItemPrice()));
        }
        if (userChoice.equalsIgnoreCase("n")) {
            itemService.buyItem(itemsMatchingName.get(0), entry);
            itemReciptService.addRecipt(userBuyItemsList, new UserBuyItems(itemsMatchingName.get(0).getItemName(), entry.getValue(), itemsMatchingName.get(0).getItemPromotionName(), itemsMatchingName.get(0).getItemPrice()));
        }
        return isMembership;
    }

    public List<Item> itemSave() {
        ItemList itemList=new ItemList();
        return itemService.saveItemList(itemList);
    }

    public String matchItems(List<Item> itemsMatchingName, Map.Entry<String, Integer> entry) {
        boolean possiblePromotion = itemCheckService.checkPromotionPossible(entry, itemsMatchingName);
        boolean itemQuantityPossible = itemCheckService.checkQuantity(entry, itemsMatchingName);
        boolean promotionCondition = itemCheckService.checkPromotionItemsCondition(entry, itemsMatchingName);
        String situation = checkPromotionOrGeneral(entry, itemsMatchingName, possiblePromotion, itemQuantityPossible, promotionCondition);
        return situation;
    }

    public String checkPromotionOrGeneral(Map.Entry<String, Integer> entry, List<Item> itemsMatchingName, boolean possiblePromotion, boolean promotionQuantity, boolean promotionCondition) {
        if (possiblePromotion == false) {
            if (promotionQuantity == true) {
                return PromotionEnum.NON_PROMOTION_FULL_QUANTITY;
            }
            return PromotionEnum.NON_PROMOTION_INSUFFICIENT_QUANTITY;
        }
        if (promotionQuantity == true && promotionCondition == true) {
            return PromotionEnum.YES_PROMOTION_FULL_QUANTITY_FULL_CONDITION;
        }
        if (promotionQuantity == false) {
            return PromotionEnum.YES_PROMOTION_INSUFFICIENT_QUANTITY;
        }
        if (promotionCondition == false) {
            return PromotionEnum.YES_PROMOTION_INSUFFICIENT_CONDITION;
        }
        return null;
    }

    private boolean buyGeneralItem(String nowItemSituation, List<Item> itemsMatchingName, Map.Entry<String, Integer> entry, UserBuyItemsList userBuyItemsList) {
        boolean isMembership = false;
        if (itemsMatchingName.size() == 1) {
            if (nowItemSituation == PromotionEnum.NON_PROMOTION_FULL_QUANTITY) {
                if (InputView.askAboutMembership().equalsIgnoreCase("y")) {
                    isMembership = true;
                    itemService.buyItem(itemsMatchingName.get(0), entry);
                    itemReciptService.addRecipt(userBuyItemsList, new UserBuyItems(itemsMatchingName.get(0).getItemName(), entry.getValue(), itemsMatchingName.get(0).getItemPromotionName(), itemsMatchingName.get(0).getItemPrice()));
                    return isMembership;
                }
            }
        }
        if (nowItemSituation == PromotionEnum.NON_PROMOTION_FULL_QUANTITY) {
            if (InputView.askAboutMembership().equalsIgnoreCase("y")) {
                itemService.buyItem(itemsMatchingName.get(1), entry);
                itemReciptService.addRecipt(userBuyItemsList, new UserBuyItems(itemsMatchingName.get(1).getItemName(), entry.getValue(), itemsMatchingName.get(1).getItemPromotionName(), itemsMatchingName.get(1).getItemPrice()));
                return isMembership;
            }
        }
        return isMembership;
    }

    public void endingMessage(List<Item> items, UserBuyItemsList userBuyItemsList) {
        if (InputView.printEndingMessage().equalsIgnoreCase("y")) {
            run(items, userBuyItemsList);
        }
    }
}
