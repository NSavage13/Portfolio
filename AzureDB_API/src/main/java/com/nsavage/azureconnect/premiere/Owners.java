package com.nsavage.azureconnect.premiere;
import java.util.Objects;

public class Owners {
    private int OwnerID;
    private String FirstName;
    private String LastName;
    private String Phone;
    private String Email;
    private String Address;

    public Owners() {
    }

    public Owners(int OwnerID, String FirstName, String LastName, String Phone, String Email, String Address) {
        this.OwnerID = OwnerID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Phone = Phone;
        this.Email = Email;
        this.Address = Address;
    }

    public int getOwnerID() {
        return this.OwnerID;
    }

    public void setOwnerID(int OwnerID) {
        this.OwnerID = OwnerID;
    }

    public String getFirstName() {
        return this.FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return this.LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getPhone() {
        return this.Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAddress() {
        return this.Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public Owners OwnerID(int OwnerID) {
        setOwnerID(OwnerID);
        return this;
    }

    public Owners FirstName(String FirstName) {
        setFirstName(FirstName);
        return this;
    }

    public Owners LastName(String LastName) {
        setLastName(LastName);
        return this;
    }

    public Owners Phone(String Phone) {
        setPhone(Phone);
        return this;
    }

    public Owners Email(String Email) {
        setEmail(Email);
        return this;
    }

    public Owners Address(String Address) {
        setAddress(Address);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Owners)) {
            return false;
        }
        Owners owners = (Owners) o;
        return OwnerID == owners.OwnerID && Objects.equals(FirstName, owners.FirstName) && Objects.equals(LastName, owners.LastName) && Objects.equals(Phone, owners.Phone) && Objects.equals(Email, owners.Email) && Objects.equals(Address, owners.Address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(OwnerID, FirstName, LastName, Phone, Email, Address);
    }

    @Override
    public String toString() {
        return "Owner ID: " + OwnerID + "\n" +
                "Name: " + FirstName + " " + LastName + "\n" +
                "Address: " + Address + "\n" +
                "Phone: " + Phone + "\n" +
                "Email: " + Email + "\n";
    }

}
