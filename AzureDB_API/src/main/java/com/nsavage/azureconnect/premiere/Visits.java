package com.nsavage.azureconnect.premiere;

import java.sql.Date;
import java.util.Objects;

public class Visits {
    private int VisitID;
    private int PetID;
    private int EmployeeID;
    private Date Date;
    private String Notes;

    public Visits() {
    }

    public Visits(int VisitID, int PetID, int EmployeeID, Date Date, String Notes) {
        this.VisitID = VisitID;
        this.PetID = PetID;
        this.EmployeeID = EmployeeID;
        this.Date = Date;
        this.Notes = Notes;
    }

    public int getVisitID() {
        return this.VisitID;
    }

    public void setVisitID(int VisitID) {
        this.VisitID = VisitID;
    }

    public int getPetID() {
        return this.PetID;
    }

    public void setPetID(int PetID) {
        this.PetID = PetID;
    }

    public int getEmployeeID() {
        return this.EmployeeID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public Date getDate() {
        return this.Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public String getNotes() {
        return this.Notes;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }

    public Visits VisitID(int VisitID) {
        setVisitID(VisitID);
        return this;
    }

    public Visits PetID(int PetID) {
        setPetID(PetID);
        return this;
    }

    public Visits EmployeeID(int EmployeeID) {
        setEmployeeID(EmployeeID);
        return this;
    }

    public Visits Date(Date Date) {
        setDate(Date);
        return this;
    }

    public Visits Notes(String Notes) {
        setNotes(Notes);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Visits)) {
            return false;
        }
        Visits visits = (Visits) o;
        return VisitID == visits.VisitID && PetID == visits.PetID && EmployeeID == visits.EmployeeID && Objects.equals(Date, visits.Date) && Objects.equals(Notes, visits.Notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(VisitID, PetID, EmployeeID, Date, Notes);
    }

    @Override
    public String toString() {
        return "{" +
            " VisitID='" + getVisitID() + "'" +
            ", PetID='" + getPetID() + "'" +
            ", EmployeeID='" + getEmployeeID() + "'" +
            ", Date='" + getDate() + "'" +
            ", Notes='" + getNotes() + "'" +
            "}";
    }

    
}
