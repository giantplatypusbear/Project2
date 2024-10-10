/**
 * Enum representing the available appointment timeslots in the RU Clinic Scheduler.
 * Each timeslot is associated with a specific hour and minute.
 *
 * @ Rithi and Shaili
 */
public enum Timeslot {
    // Enum constants representing different timeslots with their respective hours and minutes
    SLOT1(9, 0),
    SLOT2(10, 45),
    SLOT3(11, 15),
    SLOT4(13, 30),
    SLOT5(15, 0),
    SLOT6(16, 15);

    // Fields
    private final int hour;   // The hour of the timeslot
    private final int minute; // The minute of the timeslot

    /**
     * Constructor to initialize the timeslot with a specific hour and minute.
     *
     * @param hour the hour of the timeslot
     * @param minute the minute of the timeslot
     */
    Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Gets the hour of the timeslot.
     *
     * @return the hour of the timeslot
     */
    public int getHour() {
        return hour;
    }

    /**
     * Gets the minute of the timeslot.
     *
     * @return the minute of the timeslot
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Provides a string representation of the timeslot in HH:MM format.
     *
     * @return the formatted string of the timeslot (e.g., "09:00")
     */
    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);  // Format the time as HH:MM
    }
}
