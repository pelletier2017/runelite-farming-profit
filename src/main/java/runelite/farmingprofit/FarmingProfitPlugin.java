package runelite.farmingprofit;

import com.google.inject.Provides;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
        name = "Example"
)
public class FarmingProfitPlugin extends Plugin {
    // TODO edge case: player starts harvesting one herb then harvests a different herb without harvesting to completion
    // TODO edge case: start harvesting, drop some herbs, pick them up
    // TODO edge case: player harvests but never finishes harvesting, then kills an enemy for an herb, use time stamps to tell when harvesting should be done
    //     the amount of time would be the max amount of time a person could be harvesting with 1 click, could test with empty inventory and count ticks on snape grass
    // TODO edge case: see if theres any bugs by opening GE cost thing, death item checker, or looting bag to dupe farming profit
    // TODO edge case: double harvesting then you try to click away but get stuck (easy to do on seaweed)

    @Inject
    private Client client;

    @Inject
    private FarmingProfitConfig config;

    @Inject
    private FarmingActionListener farmingActionListener;

    @Override
    protected void startUp() throws Exception {
        log.info("Example started!");
    }

    @Override
    protected void shutDown() throws Exception {
        log.info("Example stopped!");
    }

//    @Subscribe
//    public void onGameTick(GameTick event) {
//        farmingManager.onGameTick(event);
//    }

//    @Subscribe
//    public void onFocusChanged(FocusChanged event) {
//        log.info("onFocusChanged");
//    }

//    @Subscribe
//    public void onWidgetLoaded(WidgetLoaded event) {
//        log.info("onWidgetLoaded");
//    }

//    @Subscribe
//    public void onGameStateChanged(GameStateChanged event) {
//        log.info("onGameStateChanged");
//    }

//    @Subscribe
//    public void onVarbitChanged(VarbitChanged event) {
//        log.info("onVarbitChanged");
//    }

//    @Subscribe
//    public void onAnimationChanged(AnimationChanged event) {
//        log.info("onAnimationChanged");
//    }

//    @Subscribe
//    public void onInteractingChanged(InteractingChanged event) {
//        log.info("onInteractingChanged");
//        log.info("getSource=" + event.getSource());
//        log.info("getSource=" + event.getTarget());
//        log.info("getTarget().getName()=" + event.getTarget().getName());
//        log.info("getTarget().getOverheadText()=" + event.getTarget().getOverheadText());
//        log.info("getSource().getName()=" + event.getSource().getName());
//        log.info("getSource().getOverheadText()=" + event.getSource().getOverheadText());
//    }

//    @Subscribe
//    public void onMenuShouldLeftClick(MenuShouldLeftClick event) {
//        log.info("onMenuShouldLeftClick");
//    }

    @Subscribe
    public void onMenuOptionClicked(MenuOptionClicked event) {
        farmingActionListener.onMenuOptionClicked(event);
        // UI idea for this https://github.com/AdrianLeeElder/runelite-bank-history-plugin
        // todo loot logger https://github.com/TheStonedTurtle/Loot-Logger
        //    use GE value, UI, persistence

        // TODO persistence
        // TODO GE value
        // TODO write code to generate large test data to load from
        // TODO chat command (daily, weekly, monthly, yearly)
        // TODO track limpwurts (including those that fall on the ground when inventory is full)
        // TODO track compost
        // TODO track seed packs
        // TODO track hespori
        // TODO track hespori iasor type plants
    }

    @Subscribe
    public void onChatMessage(ChatMessage event) {
        farmingActionListener.onChatMessage(event);
    }

    @Subscribe
    public void onItemContainerChanged(ItemContainerChanged event) {
        farmingActionListener.onItemContainerChanged(event);
    }

    @Provides
    FarmingProfitConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(FarmingProfitConfig.class);
    }
}
