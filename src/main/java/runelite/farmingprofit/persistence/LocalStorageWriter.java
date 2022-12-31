package runelite.farmingprofit.persistence;

public class LocalStorageWriter {

    // TODO write to subdirectory under runelite like lootTracker using account hash as subdirectory too
    // periodically flush in-memory collection to file
    // create backup of each file to account for issues that come up with unfinished writing
         // if file is unreadable, attempt to read backup, if that works then copy backup to overwrite real copy

}
