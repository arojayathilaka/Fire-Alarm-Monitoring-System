/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DS_Project;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Kesara
 */
public interface RMI_Interface extends Remote{
    
    //declare a method to get all the sensor details at 15 seconds intervals

    /**
     *
     * @return
     * @throws RemoteException
     */
    public List<Sensors> getAllSensors() throws RemoteException;
    
    //declare a method to add sensors
    public void addSensors(int id, boolean active, String location, int smokeLevel, int co2Level) throws RemoteException;
    
    //decalre a method to update sensors
    public void updateSensors(int id, boolean active, String location, int smokeLevel, int co2Level) throws RemoteException;
    
    //declare a method to display an alert
    public List<Sensors> displayAlert() throws RemoteException;
    
    //declare a method to send status of sensors regarding CO2 and smoke levels 
    public void sendStatus() throws RemoteException;
}
