package main.java.models;

import main.java.shared.composition.AgencyDetails;
import main.java.shared.composition.PersonDetails;

public class Client extends Entity {
    PersonDetails personDetails;
    AgencyDetails agencyDetails;
    private String address;
    private Integer tourId;

    public Client(int id, Integer tourId, String address, PersonDetails personDetails, AgencyDetails agencyDetails) {
        super(id);
        this.personDetails = personDetails;
        this.agencyDetails = agencyDetails;
        this.address = address;
        this.tourId = tourId;
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

    public String getAddress() {
        return address;
    }

    public Integer getTourId() {
        return tourId;
    }

    @Override
    public String displayInformation() {
        return "Client " +
                "[ID: " + id
                + ", Agency: " + getAgencyName()
                + ", First Name: " + getFirstName()
                + ", Last Name: " + getLastName()
                + ", Phone: " + getPhone()
                + ", Address: " + getAddress()
                + ", Tour ID: " + getTourId()
                + "]";
    }
}
