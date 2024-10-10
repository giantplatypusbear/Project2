/**
 * Enum representing medical providers in the RU Clinic Scheduler.
 * Each provider has an associated location and specialty.
 *
 * @ Rithi and Shaili
 */
public enum Provider {
    // Enum instances representing the providers and their associated locations and specialties
    PATEL(Location.BRIDGEWATER, Specialty.FAMILY),
    LIM(Location.BRIDGEWATER, Specialty.PEDIATRICIAN),
    ZIMNES(Location.CLARK, Specialty.FAMILY),
    HARPER(Location.CLARK, Specialty.FAMILY),
    KAUR(Location.PRINCETON, Specialty.ALLERGIST),
    TAYLOR(Location.PISCATAWAY, Specialty.PEDIATRICIAN),
    RAMESH(Location.MORRISTOWN, Specialty.ALLERGIST),
    CERAVOLO(Location.EDISON, Specialty.PEDIATRICIAN);

    // Fields
    private final Location location;    // The location where the provider practices
    private final Specialty specialty;  // The provider's medical specialty

    /**
     * Constructor to initialize the provider with a location and specialty.
     *
     * @param location the location where the provider practices
     * @param specialty the medical specialty of the provider
     */
    Provider(Location location, Specialty specialty) {
        this.location = location;
        this.specialty = specialty;
    }

    /**
     * Gets the location where the provider practices.
     *
     * @return the location of the provider
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Gets the provider's medical specialty.
     *
     * @return the specialty of the provider
     */
    public Specialty getSpecialty() {
        return specialty;
    }

    /**
     * Gets the name of the provider.
     *
     * @return the provider's name (the enum constant name)
     */
    public String getName() {
        return name();  // Returns the enum constant name (e.g., "PATEL")
    }

    /**
     * Provides a string representation of the provider.
     * Format: ProviderName, Location, Specialty
     *
     * @return the string representation of the provider
     */
    @Override
    public String toString() {
        return String.format("%s, %s, %s", getName(), location, specialty);
    }
}
