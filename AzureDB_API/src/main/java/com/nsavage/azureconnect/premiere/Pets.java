package com.nsavage.azureconnect.premiere;

public class Pets {
    private float PetId;
    private float OwnerId;
    private String Name;
    private String Breed;

    // setter and getters
    public float getPetId() {
        return PetId;
    }

    public void setPetId(float PetId) {
        this.PetId = PetId;
    }

    public float getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(float OwnerId) {
        this.OwnerId = OwnerId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }   

    public String getBreed() {
        return Breed;
    }           

    public void setBreed(String Breed) {
        this.Breed = Breed;
    }

    // constructor

    public Pets(float petid, float ownerid, String name, String breed) {
        this.PetId = petid;
        this.OwnerId = ownerid;
        this.Name = name;
        this.Breed = breed;
        
    }

    public Pets() {
    }

    @Override
    public String toString() {
        return "Pets{" + "PetId=" + PetId + ", OwnerId=" + OwnerId + ", Name=" + Name + ", Breed=" + Breed + '}';
    }
    
}
