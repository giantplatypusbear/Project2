/**
 * Represents a visit in the RU Clinic Scheduler.
 * A visit is associated with an appointment and may link to the next visit in the list (forming a linked list).
 *
 * Each visit holds an appointment and a reference to the next visit in the list.
 * This structure is used to maintain a history of visits for a patient.
 *
 * @ Rithi and Shaili
 */
public class Visit {
    // Fields
    private Appointment appointment;  // The appointment for this visit
    private Visit next;               // Reference to the next visit in the linked list

    /**
     * Constructor to create a visit associated with a given appointment.
     * The next visit is initialized as null (end of the list).
     *
     * @param appointment the appointment for this visit
     */
    public Visit(Appointment appointment) {
        this.appointment = appointment;
        this.next = null; // Initialize the next visit as null (end of the list)
    }

    /**
     * Gets the appointment associated with this visit.
     *
     * @return the appointment for this visit
     */
    public Appointment getAppointment() {
        return appointment;
    }

    /**
     * Gets the next visit in the list.
     *
     * @return the next visit, or null if this is the last visit
     */
    public Visit getNext() {
        return next;
    }

    /**
     * Sets the next visit in the list.
     *
     * @param next the next visit to set
     */
    public void setNext(Visit next) {
        this.next = next;
    }

    /**
     * Provides a string representation of the visit, which is the string representation of the associated appointment.
     *
     * @return the string representation of the visit
     */
    @Override
    public String toString() {
        return appointment.toString();
    }
}
