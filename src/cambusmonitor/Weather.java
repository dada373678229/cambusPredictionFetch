/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cambusmonitor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author yanhaohu
 */
public class Weather extends Thread{
    private String filename;
    private PrintWriter writer;
    private String time;
    public Weather(){
        
    }
    public void run(){
        try {
            Date d = new Date();
            SimpleDateFormat fn = new SimpleDateFormat ("yyyy.MM.dd'weather'");
            SimpleDateFormat tm = new SimpleDateFormat ("hhmmss");
            time = tm.format(d);
            filename = fn.format(d);
            writer = new PrintWriter ("/Users/yanhaohu/Desktop/output/"+filename+".txt", "UTF-8");
            while(true){
                if (d.getHours() == 2){
                    break;
                }
                URL JsonURL = null;
                try {
                    JsonURL = new URL("http://api.wunderground.com/api/4fd6e9b1874ba8a5/conditions/q/IA/IOWA_CITY.json");
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    InputStream Json = JsonURL.openStream();
                    InputStreamReader J = new InputStreamReader(Json);
                    JSONParser jsonParser = new JSONParser();
                    JSONObject jsonObject = (JSONObject) jsonParser.parse(J);
                    JSONObject current = (JSONObject) jsonObject.get("current_observation");
                    double temperature = (double) current.get("temp_f");
                    String weather = (String) current.get("weather");
                    String visibility = (String) current.get("visibility_mi");
                    String rain1h = (String) current.get("precip_1hr_in");
                    String rainday = (String) current.get("precip_today_in");
                    writer.println(time+","+temperature+","+weather+","+visibility+","+rain1h+","+rainday);
                    
                } catch (        IOException | ParseException ex) {
                    Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    Thread.sleep(1200000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
                }
            }//end while
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
