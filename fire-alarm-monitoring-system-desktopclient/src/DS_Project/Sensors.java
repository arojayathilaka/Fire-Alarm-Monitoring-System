
package DS_Project;

import java.io.Serializable;

//create sensor attributes and setters and getters

public class Sensors implements Serializable{
    
    private boolean active;
    private String location;
    private int smokeLvl;
    private int CO2Lvl;
    private int id;
    
    public Sensors(int id,boolean active, String location, int smokeLvl, int CO2Lvl){
        this.id = id;
        this.active = active;
        this.location = location;
        this.smokeLvl = smokeLvl;
        this.CO2Lvl = CO2Lvl;
        
    
    }
    
    public Sensors(int id,String location){
        this.id = id;
        this.location = location;
    }
    
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSmokeLvl() {
        return smokeLvl;
    }

    public void setSmokeLvl(int smokeLvl) {
        this.smokeLvl = smokeLvl;
    }
    
    public int getCO2Lvl() {
        return CO2Lvl;
    }

    public void setCO2Lvl(int CO2Lvl) {
        this.CO2Lvl = CO2Lvl;
    }
    
    public String toString() {
        return "> "+this.id+this.active+this.location+this.smokeLvl+this.CO2Lvl;
    }
    
}
