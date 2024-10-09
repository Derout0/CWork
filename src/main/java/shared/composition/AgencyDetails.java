package main.java.shared.composition;

public class AgencyDetails {
    private String agencyName;

    public AgencyDetails(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public String displayInformation() {
        return "Agency: " + agencyName;
    }
}