/**
 * Represents a profile in the RU Clinic Scheduler.
 * A profile contains a patient's first name, last name, and date of birth.
 *
 * @authors Rithi and Shaili
 */
public class Profile implements Comparable<Profile> {
    private String fname;  // First name of the patient
    private String lname;  // Last name of the patient
    private Date dob;      // Date of birth of the patient

    /**
     * Constructor to create a profile with a first name, last name, and date of birth.
     *
     * @param fname the first name of the patient
     * @param lname the last name of the patient
     * @param dob the date of birth of the patient
     */
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * Gets the first name of the patient.
     * @return the first name of the patient
     */
    public String getFirstName() {
        return fname;
    }

    /**
     * Gets the last name of the patient.
     * @return the last name of the patient
     */
    public String getLastName() {
        return lname;
    }

    /**
     * Gets the date of birth of the patient.
     * @return the date of birth of the patient
     */
    public Date getDateOfBirth() {
        return dob;
    }

    /**
     * Checks if two Profile objects are equal.
     * Profiles are considered equal if the first name, last name, and date of birth match (case insensitive for names).
     * @param obj the object to compare
     * @return true if the profiles are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Profile other = (Profile) obj;
        return fname.equalsIgnoreCase(other.fname) &&
                lname.equalsIgnoreCase(other.lname) &&
                dob.equals(other.dob);
    }

    /**
     * Compares this profile to another profile for sorting.
     * Comparison is based on last name, then first name, and finally date of birth.
     * @param other the profile to compare to
     * @return a negative integer, zero, or a positive integer based on comparison
     */
    @Override
    public int compareTo(Profile other) {
        int lnameComparison = this.lname.compareToIgnoreCase(other.lname);
        if (lnameComparison != 0) return lnameComparison;

        int fnameComparison = this.fname.compareToIgnoreCase(other.fname);
        if (fnameComparison != 0) return fnameComparison;

        return this.dob.compareTo(other.dob);
    }

    /**
     * Provides a string representation of the profile.
     * Format: FirstName LastName DateOfBirth
     * @return the string representation of the profile
     */
    @Override
    public String toString() {
        return String.format("%s %s %s", fname, lname, dob.toString());
    }

    /**
     * Main method for testing the compareTo() method.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Profile profile1 = new Profile("John", "Doe", new Date(1990, 5, 1));
        Profile profile2 = new Profile("Jane", "Doe", new Date(1989, 12, 1));

        System.out.println(profile1.compareTo(profile2));  // Expected: 1 (John comes after Jane)
    }
}
