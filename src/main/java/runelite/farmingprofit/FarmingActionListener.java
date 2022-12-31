package runelite.farmingprofit;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.InventoryID;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.events.MenuOptionClicked;
import runelite.farmingprofit.persistence.FarmingTracker;

import java.util.*;

@Slf4j
public class FarmingActionListener {

    private static final List<String> CHAT_MESSAGES_STARTING_HARVEST = Arrays.asList(
            "You begin to harvest the herb patch.",
            "You begin to harvest the allotment."
    );

    private static final List<String> CHAT_MESSAGES_STOPPING_HARVEST = Arrays.asList(
            "The herb patch is now empty.",
            "The allotment is now empty."
    );

    // options that would add anything to your inventory that is not harvesting
    private static final List<String> MENU_OPTIONS_STOPPING_HARVEST = Arrays.asList(
            "Take",
            "Use",
            "Drop",
            "Remove",
            "Remove-1",
            "Remove-5",
            "Remove-X",
            "Remove-All",
            "Banknotes",
            "Exchange",
            "Talk-to",
            "Pickpocket",
            "Pay",
            "Clear",
            "Inspect",
            "Store-1",
            "Store-5",
            "Store-X",
            "Store-All"
    );

    private boolean isHarvesting = false;
    private Inventory recentInventory = null;

    private FarmingTracker recorder = new FarmingTracker();

//    public FarmingManager(FarmingRecorder recorder) {
//        this.recorder = recorder;
//    }

    public void onChatMessage(ChatMessage event) {
        if (!event.getType().equals(ChatMessageType.SPAM)) {
            return;
        }

        String message = event.getMessage();
        if (CHAT_MESSAGES_STARTING_HARVEST.contains(message)) {
            startHarvesting();
        } else if (CHAT_MESSAGES_STOPPING_HARVEST.contains(message)) {
            stopHarvesting();
        } else {
            log.info("Did not recognize message=" + event.getMessage());
        }
    }

    public void onItemContainerChanged(ItemContainerChanged event) {
        log.info("item container changed");
        // TODO check if extra harvested stuff fell on the ground (like limpwurts) using itemSpawned or maybe lootspawned
        // you would be harvesting, stuff falls under your character, and its valid items that can fall on the ground

        if (event.getContainerId() == InventoryID.INVENTORY.getId()) {
            handleInventoryChanged(event.getItemContainer());
        }
    }

    private void handleInventoryChanged(ItemContainer itemContainer) {
        Inventory oldInventory = recentInventory;
        Inventory newInventory = new Inventory(itemContainer);

        List<Item> itemsAdded = Inventory.itemsAdded(oldInventory, newInventory);
        for (Item item : itemsAdded) {
            if (isHarvesting) {
                recorder.harvested(item.getId(), item.getQuantity());
            }
        }
        recentInventory = newInventory;
    }

    public void onMenuOptionClicked(MenuOptionClicked event) {
        if (MENU_OPTIONS_STOPPING_HARVEST.contains(event.getMenuOption())) {
            stopHarvesting();
        } else {
            log.info("click else");
        }
    }

    private void startHarvesting() {
        log.info("Start harvesting");
        isHarvesting = true;
    }

    private void stopHarvesting() {
        // todo ADD TIMEOUT FOR HARVESTING
        // todo COMPOST TRICK, MAKE SURE IT STILL WORKS
        log.info("Stop harvesting");
        isHarvesting = false;
    }
}
