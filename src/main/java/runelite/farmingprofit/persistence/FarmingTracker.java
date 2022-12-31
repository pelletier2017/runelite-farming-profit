package runelite.farmingprofit.persistence;

import lombok.extern.slf4j.Slf4j;
import runelite.farmingprofit.data.FarmingRecord;
import runelite.farmingprofit.data.ItemSet;

import java.util.SortedMap;
import java.util.TreeMap;

@Slf4j
public class FarmingTracker {

    TreeMap<Long, FarmingRecord> records = new TreeMap<>();

    public void harvested(int itemId, int quantity) {
        long time = System.currentTimeMillis();
        log.info("Harvested id=" + itemId + " quantity=" + quantity + " time=" + time);
        // TODO add GE value
        records.put(time, new FarmingRecord(time, itemId, quantity, true));
    }

    public void consumed(int itemId, int quantity) {
        long time = System.currentTimeMillis();
        log.info("Consumed id=" + itemId + " quantity=" + quantity + " time=" + time);
        records.put(time, new FarmingRecord(time, itemId, quantity, false));
    }

    public ItemSet getConsumedLog(long timeLowerBoundMs, long timeUpperBoundMs) {
        return getLog(timeLowerBoundMs, timeUpperBoundMs, false);
    }

    public ItemSet getHarvestedLog(long timeLowerBoundMs, long timeUpperBoundMs) {
        return getLog(timeLowerBoundMs, timeUpperBoundMs, true);
    }

    // TODO maybe cache these requests because they are long over a big data set?
    //   only matters if this gets called often, maybe just a bunch of calls to the daily one since it changes?
    private ItemSet getLog(long timeLowerBoundMs, long timeUpperBoundMs, boolean isHarvested) {
        SortedMap<Long, FarmingRecord> matchingRecords = records.subMap(timeLowerBoundMs, timeUpperBoundMs);
        return new ItemSet(matchingRecords, timeLowerBoundMs, timeUpperBoundMs, isHarvested);
    }

//    private String timeString() {
//        long currentTimeMillis = System.currentTimeMillis();
//        Instant instance = Instant.ofEpochMilli(currentTimeMillis);
//        LocalDateTime localDateTime = LocalDateTime.ofInstant(instance, ZoneId.of("Asia/Kolkata"));
//
////        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instance, ZoneId.of("Asia/Kolkata"));
//        return String.format("%04d%02d%02d", localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth());
//    }

}
