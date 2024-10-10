/**
 * Represents a billing statement for a patient in the RU Clinic Scheduler.
 * The statement includes the patient's profile and the total amount charged.
 *
 * @ Rithi and Shaili
 */
public class BillingStatement {
    // Fields
    private Profile patientProfile;  // The patient's profile
    private double totalAmount;      // The total amount charged in the billing statement

    /**
     * Constructor to create a billing statement with an initial amount.
     *
     * @param patientProfile the profile of the patient
     * @param initialAmount the initial amount for the billing statement
     */
    public BillingStatement(Profile patientProfile, double initialAmount) {
        this.patientProfile = patientProfile;
        this.totalAmount = initialAmount;
    }

    /**
     * Gets the patient's profile for this billing statement.
     *
     * @return the patient's profile
     */
    public Profile getPatientProfile() {
        return patientProfile;
    }

    /**
     * Gets the total amount charged in this billing statement.
     *
     * @return the total amount charged
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * Adds a specified amount to the total amount in the billing statement.
     *
     * @param amount the amount to add to the total
     */
    public void addAmount(double amount) {
        this.totalAmount += amount;
    }

    /**
     * Provides a string representation of the billing statement.
     * Format: patientProfile: $totalAmount
     *
     * @return the string representation of the billing statement
     */
    @Override
    public String toString() {
        return String.format("%s: $%.2f", patientProfile, totalAmount);
    }
}
