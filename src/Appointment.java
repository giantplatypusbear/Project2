/**
 * Represents an Appointment object in the RU Clinic Scheduler.
 * Each appointment has a date, timeslot, patient, and provider.
 *
 * @ Rithi and Shaili
 */
public class Appointment implements Comparable<Appointment> {
    // Fields
    private Date date;           // The date of the appointment
    private Timeslot timeslot;   // The timeslot of the appointment
    private Profile patient;     // The patient's profile
    private Provider provider;   // The provider of the appointment

    /**
     * Constructor to create an Appointment with the specified date, timeslot, patient, and provider.
     *
     * @param date the date of the appointment
     * @param timeslot the timeslot of the appointment
     * @param patient the patient involved in the appointment
     * @param provider the provider for the appointment
     */
    public Appointment(Date date, Timeslot timeslot, Profile patient, Provider provider) {
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }

    /**
     * Gets the date of the appointment.
     *
     * @return the date of the appointment
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets the timeslot of the appointment.
     *
     * @return the timeslot of the appointment
     */
    public Timeslot getTimeslot() {
        return timeslot;
    }

    /**
     * Gets the patient's profile.
     *
     * @return the patient's profile
     */
    public Profile getPatient() {
        return patient;
    }

    /**
     * Gets the provider of the appointment.
     *
     * @return the provider of the appointment
     */
    public Provider getProvider() {
        return provider;
    }

    /**
     * Checks if two appointments are equal.
     * Appointments are considered equal if they have the same date, timeslot, and patient.
     *
     * @param obj the object to compare
     * @return true if the appointments are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Appointment other = (Appointment) obj;
        return date.equals(other.date) &&
                timeslot.equals(other.timeslot) &&
                patient.equals(other.patient);
    }

    /**
     * Compares this appointment to another appointment for sorting.
     * Comparison is first done by date, then timeslot, and then patient.
     *
     * @param other the appointment to compare to
     * @return a negative integer, zero, or a positive integer if this appointment is less than,
     *         equal to, or greater than the specified appointment
     */
    @Override
    public int compareTo(Appointment other) {
        int dateComparison = this.date.compareTo(other.date); // Compare by date
        if (dateComparison != 0) return dateComparison;

        int timeslotComparison = this.timeslot.compareTo(other.timeslot); // Compare by timeslot if dates are equal
        if (timeslotComparison != 0) return timeslotComparison;

        return this.patient.compareTo(other.patient); // Compare by patient if both date and timeslot are equal
    }

    /**
     * Converts a timeslot to a string format of hour:minute AM/PM.
     *
     * @param timeslot the timeslot to convert
     * @return the formatted timeslot as a string
     */
    public static String forTimeslot(Timeslot timeslot) {
        int hour = timeslot.getHour();   // Get the hour from timeslot
        int minute = timeslot.getMinute(); // Get the minute from timeslot
        String amPm = (hour >= 12) ? "PM" : "AM"; // Determine AM/PM
        if (hour > 12) {
            hour -= 12;  // Convert to 12-hour format
        }
        return String.format("%d:%02d %s", hour, minute, amPm); // Return formatted time
    }

    /**
     * Provides a string representation of the appointment.
     * Format: date timeslot patient [provider name, city, county, zip, specialty]
     *
     * @return the string representation of the appointment
     */
    @Override
    public String toString() {
        return String.format("%s %s %s [%s, %s, %s %s, %s]",
                date,
                forTimeslot(timeslot),
                patient,
                provider.getName(),
                provider.getLocation().getCity(),
                provider.getLocation().getCounty(),
                provider.getLocation().getZip(),
                provider.getSpecialty());
    }
}
