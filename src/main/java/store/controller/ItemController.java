package store.controller;

import store.dto.ItemDto;
import store.service.ItemService;
import store.view.InputView;
import store.view.OutputView;

import java.util.List;

public class ItemController {
    InputView inputView;
    OutputView outputView;

    public ItemController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ItemService itemService = new ItemService();
        List<ItemDto> items = itemService.saveItemList();
        OutputView.printItemList(items);
    }
}
