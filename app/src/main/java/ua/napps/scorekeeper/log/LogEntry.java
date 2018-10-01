package ua.napps.scorekeeper.log;

import ua.napps.scorekeeper.counters.Counter;

/**
 * Simple Object that holds all attributes of a log entry
 */
public class LogEntry {

    public Counter counter;
    public LogType type;
    public int value;
    public long timestamp;

    /**
     * Creates simple log entry with given type and value for current timestamp
     * @param type - {@link LogType}to use for entry
     * @param value - {@link Integer} value for entry
     */
    public LogEntry(Counter counter, LogType type, int value){
        this.counter = counter;
        this.type = type;
        this.value = value;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * Custom toString for easy debugging
     * @return string representation of {@link LogEntry} Object
     */
    @Override
    public String toString(){
        return type.toString() + " - " + value + " - " + timestamp;
    }

}
