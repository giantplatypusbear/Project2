/**
 * Represents a patient in the RU Clinic Scheduler.
 * Each patient has a profile and a linked list of completed visits (appointments).
 *
 * @author Your Name
 */
public class Patient implements Comparable<Patient> {
    // Fields
    private Profile profile;   // Profile containing patient's first name, last name, and date of birth
    private Visit visits;      // Head of the linked list of visits (completed appointments)

    /**
     * Constructor to create a new patient with a given profile.
     * Initially, the patient has no completed visits.
     *
     * @param profile the profile of the patient
     */
    public Patient(Profile profile) {
        this.profile = profile;
        this.visits = null;  // Initially, no visits are completed
    }

    /**
     * Gets the patient's profile.
     *
     * @return the profile of the patient
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Adds a completed visit (appointment) to the linked list of visits.
     * The visit is added to the beginning of the list.
     *
     * @param appointment the appointment representing the completed visit
     */
    public void addVisit(Appointment appointment) {
        Visit newVisit = new Visit(appointment);
        newVisit.setNext(visits);  // Insert the new visit at the beginning of the linked list
        visits = newVisit;         // Update the head of the list
    }

    /**
     * Calculates the total charge based on all completed visits.
     * Each visit is charged according to the provider's specialty.
     *
     * @return the total charge for all completed visits
     */
    public int charge() {
        int totalCharge = 0;
        Visit current = visits;

        // Traverse the linked list of visits and sum up the charges
        while (current != null) {
            totalCharge += current.getAppointment().getProvider().getSpecialty().getCharge();
            current = current.getNext();  // Move to the next visit in the list
        }

        return totalCharge;
    }

    /**
     * Checks if two Patient objects are equal.
     * Patients are considered equal if their profiles are the same.
     *
     * @param obj the object to compare
     * @return true if the patients are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Patient other = (Patient) obj;
        return profile.equals(other.profile);  // Patients are considered equal if their profiles match
    }

    /**
     * Compares this patient to another patient for sorting.
     * The comparison is delegated to the Profile class.
     *
     * @param other the patient to compare to
     * @return a negative integer, zero, or a positive integer as this patient is less than, equal to, or greater than the specified patient
     */
    @Override
    public int compareTo(Patient other) {
        return this.profile.compareTo(other.profile);  // Delegate comparison to the Profile class
    }

    /**
     * Provides a string representation of the patient.
     * The representation is based on the profile's toString() method.
     *
     * @return the string representation of the patient
     */
    @Override
    public String toString() {
        return profile.toString();  // The profile's toString() method handles the representation
    }
}