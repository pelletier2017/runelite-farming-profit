package runelite.farmingprofit;

import net.runelite.api.Item;
import net.runelite.api.ItemContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private Map<Integer, Integer> itemMap;
    public Inventory(ItemContainer itemContainer) {
        itemMap = new HashMap<>();
        for (Item item : itemContainer.getItems()) {
            int id = item.getId();
            int newQuantity = item.getQuantity();
            int oldQuantity = itemMap.getOrDefault(id, 0);
            itemMap.put(id, oldQuantity + newQuantity);
        }
    }

    public int quantityOfItem(int itemId) {
        return itemMap.getOrDefault(itemId, 0);
    }

    public static List<Item> itemsAdded(Inventory oldInventory, Inventory newInventory) {
        if (newInventory == null) {
            throw new IllegalArgumentException("Unable to add items from null inventory");
        }

        List<Item> itemsAdded = new ArrayList<>();
        for (int itemId : newInventory.itemMap.keySet()) {
            int oldQuantity;
            if (oldInventory == null) {
                oldQuantity = 0;
            } else {
                oldQuantity = oldInventory.quantityOfItem(itemId);
            }
            int newQuantity = newInventory.quantityOfItem(itemId);

            int newlyAddedQuantity = newQuantity - oldQuantity;
            if (newlyAddedQuantity > 0) {
                itemsAdded.add(new Item(itemId, newlyAddedQuantity));
            }
        }
        return itemsAdded;
    }
}