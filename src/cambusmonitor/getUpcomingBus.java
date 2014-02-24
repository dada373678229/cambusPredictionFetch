/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cambusmonitor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 
/**
 *
 * @author Zhaowei
 */
public class getUpcomingBus extends Thread{
    //Bus indicates the route
    private String Bus;
    //constructor
    public getUpcomingBus(String bus){
        this.Bus=bus;
    }
    
    public void run()
    {
        Date day=new Date();
        Calendar C = Calendar.getInstance();
        
        //current minute, for testing purpose
        int Min;
        Min=day.getMinutes();
      
        String filename = generateFilename();
        //System.out.println(filename);
        
        //temp stores previous location
        String temp = "";
        PrintWriter writer;
      
        try{
            writer = new PrintWriter ("/Users/Zhaowei/Desktop/output/"+filename+Bus+".txt", "UTF-8");
            //continously fetch data
            while(true){
                Date d = new Date();
                String Time = generateTime();
                //for test purpose, only run for 1 minutes
                if (d.getMinutes()-Min>=1){
                    break;
                }
                //run as late as 2 am
                int H = d.getHours();
                if (H == 2){
                    break;
                }
                //String output =Bus+",";
                try {
                    //get API xml file
                    URL xmlURL=new URL("http://api.ebongo.org/buslocation?agency=uiowa&route="+Bus+"&api_key=xApBvduHbU8SRYvc74hJa7jO70Xx4XNO");
                    InputStream xml = xmlURL.openStream();
                    DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                    Document doc = docBuilder.parse (xml);
                    xml.close();

                    // normalize text representation
                    doc.getDocumentElement ().normalize ();
                    System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());
                    //output=output+doc.getDocumentElement().getNodeName()+",";


                    NodeList listOfPredictions = doc.getElementsByTagName("bus");
                    int totalPredictions = listOfPredictions.getLength();
                    System.out.println("Total no of predictions : " + totalPredictions);

                    //loop through all prediction (buses)
                    for(int s=0; s<listOfPredictions.getLength() ; s++){
                        String output =Bus+",";
                        Node firstPredictionNode = listOfPredictions.item(s);
                        if(firstPredictionNode.getNodeType() == Node.ELEMENT_NODE){

                            Element firstPredictionElement = (Element)firstPredictionNode;

                            //get id
                            NodeList idList = firstPredictionElement.getElementsByTagName("id");
                            Element idElement = (Element)idList.item(0);

                            NodeList textIdList = idElement.getChildNodes();
                            System.out.println("id : " + ((Node)textIdList.item(0)).getNodeValue().trim());
                            output=output+((Node)textIdList.item(0)).getNodeValue().trim()+",";
                            
                            //System.out.println("output has only id : "+ output);

                            //get lat
                            NodeList latList = firstPredictionElement.getElementsByTagName("lat");
                            Element latElement = (Element)latList.item(0);

                            NodeList textLatList = latElement.getChildNodes();
                            System.out.println("lat : " + ((Node)textLatList.item(0)).getNodeValue().trim());
                            output=output+((Node)textLatList.item(0)).getNodeValue().trim()+",";

                            //get lng
                            NodeList lngList = firstPredictionElement.getElementsByTagName("lng");
                            Element lngElement = (Element)lngList.item(0);

                            NodeList textLngList = lngElement.getChildNodes();
                            System.out.println("lng : " + ((Node)textLngList.item(0)).getNodeValue().trim());
                            output = output +((Node)textLngList.item(0)).getNodeValue().trim()+",";
                        
                            //get heading
                            NodeList headingList = firstPredictionElement.getElementsByTagName("heading");
                            Element headingElement = (Element)headingList.item(0);

                            NodeList textHeadingList = headingElement.getChildNodes();
                            System.out.println("heading : " + ((Node)textHeadingList.item(0)).getNodeValue().trim());
                            output = output + ((Node)textHeadingList.item(0)).getNodeValue().trim();
                        }
                        System.out.println("temp: "+temp);
                        System.out.println("output: "+output);
                        //if the location changes, wirte to file
                        if (! temp.equals(output)){
                            writer.print(Time);
                            writer.println(output);    
                        }
                        //set previous location to current location
                        temp = output;
                        //System.out.print(output);
                        //output="";
                        
                    }
                }
                catch (SAXParseException err) {
                    System.out.println ("** Parsing error" + ", line " + err.getLineNumber () + ", uri " + err.getSystemId ());
                    System.out.println(" " + err.getMessage ());
                }
                catch (SAXException e) {
                    Exception x = e.getException ();
                    ((x == null) ? e : x).printStackTrace ();
                }
                catch (Throwable t) {
                    t.printStackTrace ();
                }
                //System.out.println("output : "+ output);
                
                //if the location changes, wirte to file
                //if (! temp.equals(output)){
                    //writer.print(Time);
                    //writer.println(output);    
                //}
                //set previous location to current location
                //temp = output;
                //System.out.print(output);
                //output="";
                Thread.sleep(1000);
            }
            writer.close();
        }
        catch (FileNotFoundException e){}
        catch (UnsupportedEncodingException e){}
        catch (InterruptedException ex) {
            Logger.getLogger(getUpcomingBus.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    //generate filename
    private String generateFilename(){
        String filename;
        Calendar C = Calendar.getInstance();
        String month=Integer.toString(C.get(Calendar.MONTH)+1);
        String date=Integer.toString(C.get(Calendar.DAY_OF_MONTH));
        
        if (month.length()==1){
            month="0"+month;
        }
        if (date.length()==1){
            date = "0"+date;
        }
        filename =""+C.get(Calendar.YEAR)+month+date;
        return filename;
    }
    //generate time
    private String generateTime(){
        String time;
        Date d = new Date();
        String H = ""+ d.getHours();
        String M = ""+d.getMinutes();
        String S = ""+d.getSeconds();
        if (d.getHours()<10){
            H = "0"+d.getHours();
        }
        if (d.getMinutes()<10){
            M = "0"+d.getMinutes();
        }
        if (d.getSeconds()<10){
            S = "0"+d.getSeconds();
        }
        time = H+M+S+",";
        return time;   
    }
}
