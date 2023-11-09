package com.nsavage.azureconnect.premiere;
import java.util.Objects;

public class VisitServices {
    private int VisitServiceID;
    private int VisitID;
    private int ServiceID;

    public VisitServices() {
    }

    public VisitServices(int VisitServiceID, int VisitID, int ServiceID) {
        this.VisitServiceID = VisitServiceID;
        this.VisitID = VisitID;
        this.ServiceID = ServiceID;
    }

    public int getVisitServiceID() {
        return this.VisitServiceID;
    }

    public void setVisitServiceID(int VisitServiceID) {
        this.VisitServiceID = VisitServiceID;
    }

    public int getVisitID() {
        return this.VisitID;
    }

    public void setVisitID(int VisitID) {
        this.VisitID = VisitID;
    }

    public int getServiceID() {
        return this.ServiceID;
    }

    public void setServiceID(int ServiceID) {
        this.ServiceID = ServiceID;
    }

    public VisitServices VisitServiceID(int VisitServiceID) {
        setVisitServiceID(VisitServiceID);
        return this;
    }

    public VisitServices VisitID(int VisitID) {
        setVisitID(VisitID);
        return this;
    }

    public VisitServices ServiceID(int ServiceID) {
        setServiceID(ServiceID);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof VisitServices)) {
            return false;
        }
        VisitServices visitServices = (VisitServices) o;
        return VisitServiceID == visitServices.VisitServiceID && VisitID == visitServices.VisitID && ServiceID == visitServices.ServiceID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(VisitServiceID, VisitID, ServiceID);
    }

    @Override
    public String toString() {
        return "{" +
            " VisitServiceID='" + getVisitServiceID() + "'" +
            ", VisitID='" + getVisitID() + "'" +
            ", ServiceID='" + getServiceID() + "'" +
            "}";
    }

    
}
