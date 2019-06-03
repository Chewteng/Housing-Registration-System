/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package houseresistrationsystem;

/**
 *
 * @author Chew Teng
 */
public class House {
    
    private String ownerName,houseLoc;
    private double housePrice;

    House(){
        
    }
    
    House(String houseName, String houseLoc, double housePrice){

        this.ownerName = houseName;
        this.houseLoc = houseLoc;
        this.housePrice = housePrice;
    }

    public String getOwnerName(){
        return ownerName;
    }

    public String getHouseLoc(){
        return houseLoc;
    }
    
    public double getHousePrice(){
        return housePrice;
    }
    
    void setNewName(String newOwnerName){
        ownerName = newOwnerName; 
    }
    
    void setNewLoc(String newHouseLoc){
        houseLoc =  newHouseLoc;
    }   
        
    void setNewPrice(double newHousePrice){
        housePrice = newHousePrice; 
    }
    
}
