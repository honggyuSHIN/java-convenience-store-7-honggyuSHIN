package store;

import store.controller.ItemController;
import store.service.ItemCheckService;
import store.service.ItemReciptService;
import store.service.ItemService;
import store.view.InputView;
import store.view.OutputView;

public class Application {
    public static void main(String[] args) {

        ItemService itemService = new ItemService();
        ItemCheckService itemCheckService = new ItemCheckService();
        ItemReciptService itemReciptService = new ItemReciptService();
        ItemController itemController = new ItemController(itemService, itemCheckService, itemReciptService);
        itemController.start();



    }
}
