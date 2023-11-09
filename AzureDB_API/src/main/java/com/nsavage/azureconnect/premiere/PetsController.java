package com.nsavage.azureconnect.premiere;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PetsController {

    private String connectionString= "jdbc:sqlserver://nicoleipremiere.database.windows.net:1433;database=premiere;user={username}@nicoleipremiere;password={password Here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    
    @GetMapping("/premiere")
    public ResponseEntity<List<Pets>> allPets(){
        List<Pets> response = new ArrayList<>();

        Pets apet = new Pets();

        try{
            Connection con = DriverManager.getConnection(connectionString);
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM Pets";
            ResultSet rs = stmt.executeQuery(SQL);
            while(rs.next()){
                apet = new Pets();
                apet.setPetId(rs.getFloat("PetId"));
                apet.setOwnerId(rs.getFloat("OwnerId"));
                apet.setName(rs.getString("Name"));
                apet.setBreed(rs.getString("Breed"));
                response.add(apet);
            }
            con.close();
        }catch(SQLException sex){
            apet.setName("SQL Exception");
            apet.setBreed(sex.toString());
            response.add(apet);
            return new ResponseEntity<List<Pets>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<List<Pets>>(response, HttpStatus.OK);

    }

    @GetMapping("/owners")
    public ResponseEntity<List<Owners>> allOwners() {
        List<Owners> response = new ArrayList<>();

        Owners owners = new Owners();

        try{
            Connection con = DriverManager.getConnection(connectionString);
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM Owners";
            ResultSet rs = stmt.executeQuery(SQL);
            while(rs.next()){
                owners = new Owners();
                owners.setOwnerID(rs.getInt("OwnerId"));
                owners.setFirstName(rs.getString("FirstName"));
                owners.setLastName(rs.getString("LastName"));
                owners.setPhone(rs.getString("Phone"));
                owners.setEmail(rs.getString("Email"));
                owners.setAddress(rs.getString("Address"));
                
                
                response.add(owners);
            }
            con.close();
        }catch(SQLException sex){
            owners.setFirstName("SQL Exception");
            owners.setLastName(sex.toString());
            response.add(owners);
            return new ResponseEntity<List<Owners>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<List<Owners>>(response, HttpStatus.OK);
    }

    @PostMapping(value="/premiere")
    public ResponseEntity<String> postNewPet(@RequestBody Pets newPet){
        String response = newPet.getBreed() + " added";
        String SQL = "";
        try{
            Connection con = DriverManager.getConnection(connectionString);
            Statement stmt = con.createStatement();
            SQL = String.format("INSERT INTO Pets (OwnerId, Name, Breed) VALUES (%f, '%s', '%s')", 
            newPet.getOwnerId(), 
            newPet.getName(), 
            newPet.getBreed());
            stmt.execute(SQL);
        }catch (Exception e){
            response = e.getMessage() + " -- " + SQL;
            return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }


    @PutMapping(value="/premiere")
    public ResponseEntity<String> updatedRecord(@RequestBody PutClass data){
        String response = data.getPrimaryKey() + " updated";
        String SQL="";
        try{
            Connection con = DriverManager.getConnection(connectionString);
            Statement stmt = con.createStatement();
            SQL = String.format("UPDATE Pets SET %s = '%s' WHERE PetId = '%s'", 
            data.getFieldName(), 
            data.getValue(), 
            data.getPrimaryKey());

            stmt.execute(SQL);
        }catch (Exception e){
            response = e.getMessage() + " -- " + SQL;
            return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

}