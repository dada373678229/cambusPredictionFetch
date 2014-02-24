/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cambusmonitor;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Zhaowei
 */
public class CambusMonitor {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        // TODO code application logic here
        
        getUpcomingBus Red = new getUpcomingBus("red");
        getUpcomingBus Blue = new getUpcomingBus("blue");
        getUpcomingBus Studio = new getUpcomingBus("studart");
        getUpcomingBus Hawkdorm= new getUpcomingBus("hawkdorm");
//        getUpcomingBus Blue = new getUpcomingBus("blue");
//        getUpcomingBus Blue = new getUpcomingBus("blue");
        //getUpcomingBus Blue = new getUpcomingBus("blue");
        //getUpcomingBus Blue = new getUpcomingBus("blue");
        //getUpcomingBus Blue = new getUpcomingBus("blue");
        //getUpcomingBus Blue = new getUpcomingBus("blue");
        
        Red.start();
        Blue.start();
        Studio.start();
        Hawkdorm.start();
        
    }
    
}
