package com.nsavage.azureconnect.premiere;
import java.util.Objects;

public class Services {
    private int ServiceID;
    private String ServiceName;
    private String Description;
    private double Cost;

    public Services() {
    }

    public Services(int ServiceID, String ServiceName, String Description, double Cost) {
        this.ServiceID = ServiceID;
        this.ServiceName = ServiceName;
        this.Description = Description;
        this.Cost = Cost;
    }

    public int getServiceID() {
        return this.ServiceID;
    }

    public void setServiceID(int ServiceID) {
        this.ServiceID = ServiceID;
    }

    public String getServiceName() {
        return this.ServiceName;
    }

    public void setServiceName(String ServiceName) {
        this.ServiceName = ServiceName;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public double getCost() {
        return this.Cost;
    }

    public void setCost(double Cost) {
        this.Cost = Cost;
    }

    public Services ServiceID(int ServiceID) {
        setServiceID(ServiceID);
        return this;
    }

    public Services ServiceName(String ServiceName) {
        setServiceName(ServiceName);
        return this;
    }

    public Services Description(String Description) {
        setDescription(Description);
        return this;
    }

    public Services Cost(double Cost) {
        setCost(Cost);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Services)) {
            return false;
        }
        Services services = (Services) o;
        return ServiceID == services.ServiceID && Objects.equals(ServiceName, services.ServiceName) && Objects.equals(Description, services.Description) && Cost == services.Cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ServiceID, ServiceName, Description, Cost);
    }

    @Override
    public String toString() {
        return "{" +
            " ServiceID='" + getServiceID() + "'" +
            ", ServiceName='" + getServiceName() + "'" +
            ", Description='" + getDescription() + "'" +
            ", Cost='" + getCost() + "'" +
            "}";
    }

    // Add constructors, getters, and setters here
}
