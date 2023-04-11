package hr.java.production.model;

import hr.java.production.enumeration.GradoviEnum;

import java.io.Serializable;
import java.util.Objects;

/**
 * class address
 */
public class Address implements Serializable {

    private GradoviEnum city;
    private String street, houseNumber;

    /**
     * builder pattern
     */
    public static class Builder {
        private String street, houseNumber;
        private GradoviEnum city;

        public Builder(String street) {
            this.street=street;
        }

        public Builder atHouseNumber (String houseNumber){
            this.houseNumber=houseNumber;
            return this;
        }

        public Builder inCity(GradoviEnum city) {
            this.city=city;
            return this;
        }

        public Address build () {
            Address address=new Address();
            address.street=this.street;
            address.houseNumber=this.houseNumber;
            address.city=this.city;

            return address;
        }

    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public GradoviEnum getCity() {
        return city;
    }

    public void setCity(GradoviEnum city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return city == address.city && Objects.equals(street, address.street) && Objects.equals(houseNumber, address.houseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, houseNumber);
    }
}
