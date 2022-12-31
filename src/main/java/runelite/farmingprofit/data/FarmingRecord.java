package runelite.farmingprofit.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

// TODO merge hourly/daily/weekly/monthly records to reduce space

@Getter
@AllArgsConstructor
public class FarmingRecord {

    private long timeMillis;

    private int itemId;

    private int quantity;

    private boolean isHarvested;

}
