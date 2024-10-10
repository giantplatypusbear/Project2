/**
 * Enum representing different clinic locations in the RU Clinic Scheduler.
 * Each location is associated with a city, county, and zip code.
 *
 * @ Rithi and Shaili
 */
public enum Location {
    // Enum instances representing clinic locations
    BRIDGEWATER("Somerset", "08807"),
    EDISON("Middlesex", "08817"),
    PISCATAWAY("Middlesex", "08854"),
    PRINCETON("Mercer", "08542"),
    MORRISTOWN("Morris", "07960"),
    CLARK("Union", "07066");

    // Fields
    private final String county;  // The county where the location is
    private final String zip;     // The zip code of the location

    /**
     * Constructor to create a Location enum instance with a county and zip code.
     *
     * @param county the county where the location is
     * @param zip the zip code of the location
     */
    Location(String county, String zip) {
        this.county = county;
        this.zip = zip;
    }

    /**
     * Gets the city name (the enum instance name) for the location.
     *
     * @return the name of the city
     */
    public String getCity() {
        return name();  // The name of the enum instance (city)
    }

    /**
     * Gets the county where the location is.
     *
     * @return the county of the location
     */
    public String getCounty() {
        return county;
    }

    /**
     * Gets the zip code of the location.
     *
     * @return the zip code of the location
     */
    public String getZip() {
        return zip;
    }

    /**
     * Provides a string representation of the location.
     * Format: City, County Zip
     *
     * @return the string representation of the location
     */
    @Override
    public String toString() {
        return String.format("%s, %s %s", name(), county, zip);
    }
}
