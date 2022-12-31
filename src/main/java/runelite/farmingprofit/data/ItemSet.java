package runelite.farmingprofit.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

@Getter
@AllArgsConstructor
public class ItemSet {
    private final Map<Integer, Integer> itemToQuantityMap = new HashMap<>();
    private final long timeLowerBoundMs;
    private final long timeUpperBoundMs;

    public ItemSet(SortedMap<Long, FarmingRecord> matchingRecords, long timeLowerBoundMs, long timeUpperBoundMs, boolean isHarvested) {
        this.timeLowerBoundMs = timeLowerBoundMs;
        this.timeUpperBoundMs = timeUpperBoundMs;

        for (FarmingRecord record : matchingRecords.values()) {
            if (record.isHarvested() == isHarvested) {
                int itemId = record.getItemId();
                int oldQuantity = itemToQuantityMap.getOrDefault(itemId, 0);

                itemToQuantityMap.put(itemId, oldQuantity + record.getQuantity());
            }
        }
    }
}
