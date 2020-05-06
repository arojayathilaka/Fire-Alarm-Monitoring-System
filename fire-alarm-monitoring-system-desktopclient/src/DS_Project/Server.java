/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DS_Project;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Kesara
 */
public class Server extends UnicastRemoteObject implements RMI_Interface {

    private static HttpURLConnection connection;
     
    Sensors s1 = new Sensors(0,false,"*",0,0);

    public Server() throws RemoteException {
        super();
        
    }

    //implementation of the getAllSensors method in RMI_Interface
    @Override
    public List getAllSensors() throws RemoteException {
        
        BufferedReader reader;
        String line;
        StringBuffer response = new StringBuffer();

        try {
            URL url = new URL("http://localhost:8080/rest/webapi/sensors");
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
            }

            System.out.println(status);
            
            List<Sensors> list = new ArrayList<>();

            JSONArray sensors = new JSONArray(response.toString());
            for (int i = 0; i < sensors.length(); i++) {
                JSONObject sensor = sensors.getJSONObject(i);
                int id = sensor.getInt("id");
                boolean isActive = sensor.getBoolean("active");
                String location = sensor.getString("location");
                int smokeLvl = sensor.getInt("smokeLvl");
                int co2Lvl = sensor.getInt("CO2Lvl");

                list.add(new Sensors(id, isActive, location, smokeLvl, co2Lvl));

            }

            return list;

        } catch (MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }

        return null;
        


    }

    
    //implementation of the addSensors method in RMI_Interface
    @Override
    public void addSensors(int id, boolean active, String location, int smokeLevel, int co2Level) throws RemoteException {

        s1 = new Sensors(id,active,location,smokeLevel,co2Level);
        System.out.println(s1);
        
        if(s1.getId() == 0){
            System.out.println("No sensor added");
        }
        else{
            Gson json = new Gson();
            String res = json.toJson(s1);

            System.out.println(res);
        
        
        try {
            URL url = new URL("http://localhost:8080/rest/webapi/sensors/add");
            Map<String, String> params = new LinkedHashMap<>();
            params.put("Sensors", res);

            byte[] postDataBytes = res.getBytes("UTF-8");

            System.out.println(postDataBytes);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            System.out.println(postDataBytes);
            
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;) {
                sb.append((char) c);
            }

            String response = sb.toString();

            System.out.println(response);

            JSONObject myResponse = new JSONObject(response.toString());
            System.out.println("result after Reading JSON Response");
            System.out.println("origin- " + myResponse.getString("origin"));
            System.out.println("url- " + myResponse.getString("url"));

            JSONObject form_data = myResponse.getJSONObject("form");
            System.out.println("id= " + form_data.getString("id"));
            System.out.println("active= " + form_data.getString("isActive"));
            System.out.println("location= " + form_data.getString("location"));
            System.out.println("smoke= " + form_data.getString("smokeLvl"));
            System.out.println("co2= " + form_data.getString("CO2Lvl"));

        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        }
        

    }
    
    

    @Override
        public void updateSensors(int id, boolean active, String location, int smokelevel, int co2level) throws RemoteException {
       
        s1 = new Sensors(id, active, location, smokelevel, co2level);
        System.out.println(s1);

        if (s1.getId() == 0) {
            System.out.println("No sensor added.");
        } else {
            Gson json = new Gson();
            String res = json.toJson(s1);

            System.out.println(res);

            try {
                URL url = new URL("http://localhost:8080/rest/webapi/sensors/update");
                Map<String, String> params = new LinkedHashMap<>();
                params.put("Sensors", res);

                byte[] postDataBytes = res.getBytes("UTF-8");

                System.out.println(postDataBytes);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("PUT");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
                conn.setDoOutput(true);
                conn.getOutputStream().write(postDataBytes);

                System.out.println(postDataBytes);

                Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                for (int c; (c = in.read()) >= 0;) {
                    sb.append((char) c);
                }

                String response = sb.toString();

                System.out.println(response);

                JSONObject myResponse = new JSONObject(response.toString());
                System.out.println("result after Reading JSON Response");
                System.out.println("origin- " + myResponse.getString("origin"));
                System.out.println("url- " + myResponse.getString("url"));

                JSONObject form_data = myResponse.getJSONObject("form");
                System.out.println("id= " + form_data.getString("id"));
                System.out.println("active= " + form_data.getString("isActive"));
                System.out.println("location= " + form_data.getString("location"));
                System.out.println("smoke= " + form_data.getString("smokeLvl"));
                System.out.println("co2= " + form_data.getString("CO2Lvl"));

            } catch (MalformedURLException e) {
                System.out.println(e.getMessage());
            } catch (UnsupportedEncodingException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
    }

    @Override
    public List<Sensors> displayAlert() throws RemoteException {
        
        BufferedReader reader;
        String line;
        StringBuffer response = new StringBuffer();

        try {
            URL url = new URL("http://localhost:8080/rest/webapi/sensors");
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
            }

            System.out.println(status);
            

            List<Sensors> list = new ArrayList<>();

            JSONArray sensors = new JSONArray(response.toString());
            
            for (int i = 0; i < sensors.length(); i++) {
                JSONObject sensor = sensors.getJSONObject(i);
                int id = sensor.getInt("id");
                boolean isActive = sensor.getBoolean("active");
                String location = sensor.getString("location");
                int smokeLvl = sensor.getInt("smokeLvl");
                int co2Lvl = sensor.getInt("CO2Lvl");
                
                if(smokeLvl > 5 || co2Lvl > 5){
                   list.add(new Sensors(id, isActive, location, smokeLvl, co2Lvl)); 
                }

            }

            return list;

        } catch (MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }

        return null;

    }

    @Override
    public void sendStatus() throws RemoteException {
        
        BufferedReader reader;
        String line;
        StringBuffer response = new StringBuffer();
        List<Sensors> list = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:8080/rest/webapi/sensors");
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
            }

            System.out.println(status);
            
            JSONArray sensors = new JSONArray(response.toString());
            for (int i = 0; i < sensors.length(); i++) {
                JSONObject sensor = sensors.getJSONObject(i);
                int id = sensor.getInt("id");
                boolean isActive = sensor.getBoolean("active");
                String location = sensor.getString("location");
                int smokeLvl = sensor.getInt("smokeLvl");
                int co2Lvl = sensor.getInt("CO2Lvl");

                list.add(new Sensors(id, isActive, location, smokeLvl, co2Lvl));

            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.disconnect();
        }

        int count = 0;
        for(Sensors s : list) {
            if(s.getCO2Lvl()> 5 || s.getSmokeLvl() > 5) {
                count++;
            }
        }
        
        boolean sendEmail;
        if(count > 0){
            sendEmail = true;
        } else {
            sendEmail = false;
        }
        
        Emails e1 = new Emails(sendEmail);
        System.out.println(e1);

            Gson json = new Gson();
            String res = json.toJson(e1);

            System.out.println(res);

            try {
                URL url = new URL("http://localhost:8080/rest/webapi/emails/update");
                Map<String, String> params = new LinkedHashMap<>();
                params.put("Emails", res);

                byte[] postDataBytes = res.getBytes("UTF-8");

                System.out.println(postDataBytes);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("PUT");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
                conn.setDoOutput(true);
                conn.getOutputStream().write(postDataBytes);

                System.out.println(postDataBytes);

                Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                for (int c; (c = in.read()) >= 0;) {
                    sb.append((char) c);
                }

                String r = sb.toString();

                System.out.println(r);

                JSONObject myResponse = new JSONObject(r.toString());
                System.out.println("result after Reading JSON Response");
                System.out.println("origin- " + myResponse.getString("origin"));
                System.out.println("url- " + myResponse.getString("url"));           
             
            } catch (MalformedURLException e) {
                System.out.println(e.getMessage());
            } catch (UnsupportedEncodingException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        
        
        
    }
    
    //RMI server check the sensor status every 15 seconds to get uptodate readings
    Timer timer = new Timer();

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            try {
                getAllSensors();
                sendStatus();
                displayAlert();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    };
    
    public void start() {
        timer.scheduleAtFixedRate(task, 15000, 15000);
    }
    
    
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

            Server server = new Server();

            reg.rebind("rmi://localhost/server", server);
            System.out.println("Server is running...");
            
            server.start();
            
          

        } catch (RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
