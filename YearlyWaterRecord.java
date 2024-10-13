/**
 * A record class used to create objects for storing and representing water-related statistics
 *
 * @author Raingsey Tevy
 * @version 2024-10-13
 *
 * @param isoYear string of the iso codes for the country and the year, e.g. "KHM2000"
 * @param basicPlusPct double that shows the percentage of the country's population with basic or better access to water
 * @param limitedPct double that shows the percentage of the country with limited access to water sources
 * @param unimprovedPct double that shows the percentage of the country using unimproved water sources
 * @param surfacePct double that shows the percentage of the country using surface water sources, e.g. lakes, rivers
 */
public record YearlyWaterRecord(
        String isoYear,
        double basicPlusPct,
        double limitedPct,
        double unimprovedPct,
        double surfacePct) implements Comparable<YearlyWaterRecord> {

    /**
     * Compares two yearly water record objects
     *
     * @param other the object to be compared.
     * @return an integer value showing the results of the comparison
     */
    @Override
    public int compareTo(YearlyWaterRecord other) {
        return this.isoYear.compareTo(other.isoYear);
    }

    /**
     * Compares two yearly water record objects with each other to see if they're equal
     *
     * @param other   the reference object with which to compare.
     * @return a true or false value
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) { //check for null, check for yearly water record object
            return false;
        } else if (this == other) {
            return true;
        } else {
            YearlyWaterRecord otherRecord = (YearlyWaterRecord) other;
            return compareTo(otherRecord) == 0;
        }
    }

}
