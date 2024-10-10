/**
 * Represents a date in the RU Clinic Scheduler.
 * Includes methods to validate the date and perform various date-related comparisons.
 *
 * @authors Rithi and Shaili
 */
public class Date implements Comparable<Date> {
    private int year;   // Year of the date
    private int month;  // Month of the date
    private int day;    // Day of the date

    // Constants for date validation
    private static final int[] DAYS_IN_MONTH = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };  // Days in each month
    private static final int FEBRUARY = 2;
    private static final int QUADRENNIAL = 4;
    private static final int CENTENNIAL = 100;
    private static final int QUATERCENTENNIAL = 400;

    /**
     * Constructor to create a Date object.
     *
     * @param year the year of the date
     * @param month the month of the date
     * @param day the day of the date
     */
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Gets the year of the date.
     * @return the year of the date
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the month of the date.
     * @return the month of the date
     */
    public int getMonth() {
        return month;
    }

    /**
     * Gets the day of the date.
     * @return the day of the date
     */
    public int getDay() {
        return day;
    }

    /**
     * Checks if the date is a valid calendar date.
     * @return true if the date is valid, false otherwise
     */
    public boolean isValid() {
        // Check if month is valid
        if (month < 1 || month > 12) {
            return false;
        }

        // Check for leap year
        boolean isLeapYear = (year % QUADRENNIAL == 0 && year % CENTENNIAL != 0) || (year % QUATERCENTENNIAL == 0);

        // Get the number of days in the given month
        int daysInMonth = (month == FEBRUARY && isLeapYear) ? 29 : DAYS_IN_MONTH[month - 1];

        // Check if the day is valid for the given month
        return day >= 1 && day <= daysInMonth;
    }

    /**
     * Compares this date to another date.
     * @param other the date to compare to
     * @return a negative integer, zero, or a positive integer if this date is earlier, equal, or later than the other date
     */
    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) {
            return this.year - other.year;  // Compare by year
        }
        if (this.month != other.month) {
            return this.month - other.month;  // Compare by month
        }
        return this.day - other.day;  // Compare by day
    }

    /**
     * Checks if two Date objects are equal.
     * @param obj the object to compare
     * @return true if the dates are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Date other = (Date) obj;
        return year == other.year && month == other.month && day == other.day;
    }

    /**
     * Returns a string representation of the date.
     * Format: mm/dd/yyyy
     * @return the string representation of the date
     */
    @Override
    public String toString() {
        return String.format("%d/%d/%04d", month, day, year);
    }

    /**
     * Main method for testing the isValid() method.
     * Tests different cases to validate date logic.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Date date1 = new Date(2021, 4, 31); // Invalid date (April 31st)
        System.out.println(date1.isValid()); // Expected: false

        Date date2 = new Date(2020, 2, 29); // Valid leap year date
        System.out.println(date2.isValid()); // Expected: true
    }

    public boolean isWeekend(
            Date appointmentDate) {
        return false;
    }

    public boolean isWithinSixMonths(
            Date appointmentDate) {
        return false;
    }

    public boolean isBeforeToday() {
        return false;
    }

    public boolean isAfterToday() {
        return false;
    }
}
