package Beans;

import java.math.BigDecimal;

/**
 *
 * @author DTI
 */
public class Country {

    private String countryId;
    private String countryName;
    private BigDecimal regionId;

    /**
     * @return the countryId
     */
    public String getCountryId() {
        return countryId;
    }

    /**
     * @param countryId the countryId to set
     */
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    /**
     * @return the countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName the countryName to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return the regionId
     */
    public BigDecimal getRegionId() {
        return regionId;
    }

    /**
     * @param regionId the regionId to set
     */
    public void setRegionId(BigDecimal regionId) {
        this.regionId = regionId;
    }

}
