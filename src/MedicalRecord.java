/**
 * Represents a medical record in the RU Clinic Scheduler.
 * Stores an array of Patient objects and dynamically grows as needed.
 *
 * @ Rithi and Shaili
 */
public class MedicalRecord {
    private Patient[] patients;  // Array of patient objects
    private int size;            // Number of patients currently in the record

    private static final int INITIAL_CAPACITY = 4;  // Initial capacity of the patient array

    /**
     * Constructor to create an empty medical record with an initial capacity.
     */
    public MedicalRecord() {
        patients = new Patient[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Adds a new patient to the medical record. Dynamically grows the array if the current capacity is reached.
     * @param newPatient the patient to add
     */
    public void add(Patient newPatient) {
        if (size == patients.length) {
            grow();  // Grow the array if it's full
        }
        patients[size++] = newPatient;
    }

    /**
     * Finds a patient in the medical record by their profile.
     * @param profile the profile to search for
     * @return the patient if found, or null if not found
     */
    public Patient findPatient(Profile profile) {
        for (int i = 0; i < size; i++) {
            if (patients[i].getProfile().equals(profile)) {
                return patients[i];  // Found the patient
            }
        }
        return null;  // Patient not found
    }

    /**
     * Grows the patient array by increasing its capacity by 4.
     * Manually copies the elements from the old array to the new array.
     */
    private void grow() {
        Patient[] newPatients = new Patient[patients.length + INITIAL_CAPACITY];
        for (int i = 0; i < patients.length; i++) {
            newPatients[i] = patients[i];  // Manual copy
        }
        patients = newPatients;
    }
}
