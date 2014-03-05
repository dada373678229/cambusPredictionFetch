/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cambusmonitor;


/**
 *
 * @author Zhaowei
 */
public class CambusMonitor {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Weather weather = new Weather();
        
        getUpcomingBus Red = new getUpcomingBus("uiowa","red");
        getUpcomingBus Blue = new getUpcomingBus("uiowa","blue");
        getUpcomingBus Studio = new getUpcomingBus("uiowa","studart");
        getUpcomingBus Hawkdorm= new getUpcomingBus("uiowa","hawkdorm");
        getUpcomingBus hawkexpress= new getUpcomingBus("uiowa","hawkexpress");
        getUpcomingBus interdorm= new getUpcomingBus("uiowa","interdorm");
        getUpcomingBus mayflower= new getUpcomingBus("uiowa","mayflower");
        getUpcomingBus mts= new getUpcomingBus("uiowa","mts");
        getUpcomingBus pentnight= new getUpcomingBus("uiowa","pentnight");
        getUpcomingBus pentacrest= new getUpcomingBus("uiowa","pentacrest");
        
        getUpcomingBus seventhave= new getUpcomingBus("iowa-city","seventhave");
        getUpcomingBus brdwy= new getUpcomingBus("iowa-city","brdwy");
        getUpcomingBus brdwynw= new getUpcomingBus("iowa-city","brdwynw");
        getUpcomingBus crosspark= new getUpcomingBus("iowa-city","crosspark");
        getUpcomingBus manville= new getUpcomingBus("iowa-city","manville");
        
        getUpcomingBus tenthst= new getUpcomingBus("coralville","tenthst");
        getUpcomingBus amexpress= new getUpcomingBus("coralville","amexpress");
        getUpcomingBus amnorthlib= new getUpcomingBus("coralville","amnorthlib");
        getUpcomingBus lantern= new getUpcomingBus("coralville","lantern");
        
        
        weather.start();
        Red.start();
        Blue.start();
        Studio.start();
        Hawkdorm.start();
        hawkexpress.start();
        interdorm.start();
        mayflower.start();
        mts.start();
        pentnight.start();
        pentacrest.start();
        seventhave.start();
        brdwy.start();
        brdwynw.start();
        crosspark.start();
        manville.start();
        tenthst.start();
        amexpress.start();
        amnorthlib.start();
        lantern.start();
        
    }
    
}
