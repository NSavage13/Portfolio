package com.nsavage.azureconnect.premiere;
import java.util.Objects;

public class Employees {
    private int EmployeeID;
    private String FirstName;
    private String LastName;
    private String Position;
    private String Phone;
    private String Email;

    public Employees() {
    }

    public Employees(int EmployeeID, String FirstName, String LastName, String Position, String Phone, String Email) {
        this.EmployeeID = EmployeeID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Position = Position;
        this.Phone = Phone;
        this.Email = Email;
    }

    public int getEmployeeID() {
        return this.EmployeeID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
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

    public String getPosition() {
        return this.Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
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

    public Employees EmployeeID(int EmployeeID) {
        setEmployeeID(EmployeeID);
        return this;
    }

    public Employees FirstName(String FirstName) {
        setFirstName(FirstName);
        return this;
    }

    public Employees LastName(String LastName) {
        setLastName(LastName);
        return this;
    }

    public Employees Position(String Position) {
        setPosition(Position);
        return this;
    }

    public Employees Phone(String Phone) {
        setPhone(Phone);
        return this;
    }

    public Employees Email(String Email) {
        setEmail(Email);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Employees)) {
            return false;
        }
        Employees employees = (Employees) o;
        return EmployeeID == employees.EmployeeID && Objects.equals(FirstName, employees.FirstName) && Objects.equals(LastName, employees.LastName) && Objects.equals(Position, employees.Position) && Objects.equals(Phone, employees.Phone) && Objects.equals(Email, employees.Email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(EmployeeID, FirstName, LastName, Position, Phone, Email);
    }

    @Override
    public String toString() {
        return "{" +
            " EmployeeID='" + getEmployeeID() + "'" +
            ", FirstName='" + getFirstName() + "'" +
            ", LastName='" + getLastName() + "'" +
            ", Position='" + getPosition() + "'" +
            ", Phone='" + getPhone() + "'" +
            ", Email='" + getEmail() + "'" +
            "}";
    }

    // Add constructors, getters, and setters here
}
