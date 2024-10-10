/**
 * Enum representing the different medical specialties in the RU Clinic Scheduler.
 * Each specialty is associated with a specific charge for services.
 *
 * @ Rithi and Shaili
 */
public enum Specialty {
    // Enum constants representing medical specialties with their respective charges
    FAMILY(250),
    PEDIATRICIAN(300),
    ALLERGIST(350);

    // Field
    private final int charge;  // The charge associated with the specialty

    /**
     * Constructor to initialize the specialty with a specific charge.
     *
     * @param charge the charge for the specialty
     */
    Specialty(int charge) {
        this.charge = charge;
    }

    /**
     * Gets the charge associated with the specialty.
     *
     * @return the charge for the specialty
     */
    public int getCharge() {
        return charge;
    }

    /**
     * Provides a string representation of the specialty.
     *
     * @return the string representation of the specialty (the name of the enum constant)
     */
    @Override
    public String toString() {
        return String.format("%s", name());  // Returns the name of the enum constant (e.g., "FAMILY")
    }
}
