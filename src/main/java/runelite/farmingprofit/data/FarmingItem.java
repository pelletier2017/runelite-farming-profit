package runelite.farmingprofit.data;

import net.runelite.api.ItemID;

import java.util.Optional;

public enum FarmingItem {
    GUAM_SEED("Guam leaf seed", ItemID.GUAM_SEED),
    MARRENTILL_SEED("Marrentill seed", ItemID.MARRENTILL_SEED),
    TARROMIN_SEED("Tarromin seed", ItemID.TARROMIN_SEED),
    HARRALANDER_SEED("Harralander seed", ItemID.HARRALANDER_SEED),
    RANARR_WEED_SEED("Ranarr weed seed", ItemID.RANARR_SEED),
    TOADFLAX_SEED("Toadflax seed", ItemID.TOADFLAX_SEED),
    IRIT_SEED("Irit leaf seed", ItemID.IRIT_SEED),
    AVANTOE_SEED("Avantoe seed", ItemID.AVANTOE_SEED),
    KWUARM_SEED("Kwuarm seed", ItemID.KWUARM_SEED),
    SNAPDRAGON_SEED("Snapdragon seed", ItemID.SNAPDRAGON_SEED),
    CADANTINE_SEED("Cadentine seed", ItemID.CADANTINE_SEED),
    LANTADYME_SEED("Lantadyme seed", ItemID.LANTADYME_SEED),
    DWARF_WEED_SEED("Dwarf weed seed", ItemID.DWARF_WEED_SEED),
    TORSTOL_SEED("Torstol seed", ItemID.TORSTOL_SEED),

    GUAM("Guam leaf", ItemID.GRIMY_GUAM_LEAF),
    MARRENTILL("Marrentill", ItemID.GRIMY_MARRENTILL),
    TARROMIN("Tarromin", ItemID.GRIMY_TARROMIN),
    HARRALANDER("Harralander", ItemID.GRIMY_HARRALANDER),
    RANARR_WEED("Ranarr weed", ItemID.GRIMY_RANARR_WEED),
    TOADFLAX("Toadflax", ItemID.GRIMY_TOADFLAX),
    IRIT("Irit leaf", ItemID.IRIT_LEAF),
    AVANTOE("Avantoe", ItemID.GRIMY_AVANTOE),
    KWUARM("Kwuarm", ItemID.GRIMY_KWUARM),
    SNAPDRAGON("Snapdragon", ItemID.GRIMY_SNAPDRAGON),
    CADANTINE("Cadentine", ItemID.GRIMY_CADANTINE),
    LANTADYME("Lantadyme", ItemID.GRIMY_LANTADYME),
    DWARF_WEED("Dwarf weed", ItemID.GRIMY_DWARF_WEED),
    TORSTOL("Torstol", ItemID.GRIMY_TORSTOL),
    ;

    private String commonName;
    private int itemId;

    FarmingItem(String commonName, int itemId) {
        this.commonName = commonName;
        this.itemId = itemId;
    }

    public int getId() {
        return itemId;
    }

    public String getCommonName() {
        return commonName;
    }

    static Optional<FarmingItem> fromId(int itemId) {
        for (FarmingItem farmingItem : values()) {
            if (farmingItem.getId() == itemId) {
                return Optional.of(farmingItem);
            }
        }
        return Optional.empty();
    }

    static Optional<FarmingItem> fromCommonName(String commonName) {
        for (FarmingItem farmingItem : values()) {
            if (farmingItem.getCommonName().equals(commonName)) {
                return Optional.of(farmingItem);
            }
        }
        return Optional.empty();
    }
}
