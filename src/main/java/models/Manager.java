package main.java.models;

import main.java.shared.composition.AgencyDetails;
import main.java.shared.composition.PersonDetails;

public class Manager extends Entity {
    PersonDetails personDetails;
    AgencyDetails agencyDetails;

    public Manager(int id, AgencyDetails agencyDetails, PersonDetails personDetails) {
        super(id);
        this.agencyDetails = agencyDetails;
        this.personDetails = personDetails;
    }

    public String getAgencyName() {
        return agencyDetails.getAgencyName();
    }

    public String getFirstName() {
        return personDetails.getFirstName();
    }

    public String getLastName() {
        return personDetails.getLastName();
    }

    public String getPhone() {
        return personDetails.getPhone();
    }

    @Override
    public String displayInformation() {
        return "Manager "
                + "[ID: " + id
                + ", Agency: " + getAgencyName()
                + ", First Name: " + getFirstName()
                + ", Last Name: " + getLastName()
                + ", Phone: " + getPhone() + "]";
    }
}
