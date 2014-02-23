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
import java.util.Date;
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
public class getUpcomingBus {
    //
    private String[] arrival = new String[3];
    
    public void track() throws FileNotFoundException, UnsupportedEncodingException{
       
      String temp = "";//get time; //temp time+data
      PrintWriter writer = new PrintWriter ("/Users/yanhaohu/Desktop/text.txt", "UTF-8");
      int sec;
      Date day=new Date();
      sec=day.getMinutes();
      while(true){    
         
          
            Date d = new Date();
            String Time = d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
            
            if (d.getMinutes()-sec>=1){
                break;
            }
            
        int H = d.getHours();
        if (H == 2){
            break;
        }//Break after time
            String output ="Red,";
            try {
                //get API xml file
                URL xmlURL=new URL("http://api.ebongo.org/buslocation?agency=uiowa&route=red&api_key=xApBvduHbU8SRYvc74hJa7jO70Xx4XNO");
                InputStream xml = xmlURL.openStream();
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                Document doc = docBuilder.parse (xml);
                xml.close();

                // normalize text representation
                doc.getDocumentElement ().normalize ();
                System.out.println ("Root element of the doc is " + 
                     doc.getDocumentElement().getNodeName());
                output=output+doc.getDocumentElement().getNodeName()+",";


                NodeList listOfPredictions = doc.getElementsByTagName("bus");
                int totalPredictions = listOfPredictions.getLength();
                System.out.println("Total no of predictions : " + totalPredictions);

                //loop through all prediction (buses)
                for(int s=0; s<listOfPredictions.getLength() ; s++){

                    Node firstPredictionNode = listOfPredictions.item(s);
                    if(firstPredictionNode.getNodeType() == Node.ELEMENT_NODE){

                        Element firstPredictionElement = (Element)firstPredictionNode;

                        //-------
                        NodeList tagList = firstPredictionElement.getElementsByTagName("id");
                        Element tagElement = (Element)tagList.item(0);

                        NodeList textTagList = tagElement.getChildNodes();
                        System.out.println("id : " + 
                               ((Node)textTagList.item(0)).getNodeValue().trim());
                        output=output+((Node)textTagList.item(0)).getNodeValue().trim()+",";

                        //-------
                        NodeList agencyList = firstPredictionElement.getElementsByTagName("lat");
                        Element agencyElement = (Element)agencyList.item(0);

                        NodeList textAgencyList = agencyElement.getChildNodes();
                        System.out.println("lat : " + 
                               ((Node)textAgencyList.item(0)).getNodeValue().trim());
                        output=output+((Node)textAgencyList.item(0)).getNodeValue().trim()+",";

                        //-------
                        NodeList minutesList = firstPredictionElement.getElementsByTagName("lng");
                        Element minutesElement = (Element)minutesList.item(0);

                        NodeList textMinList = minutesElement.getChildNodes();
                        System.out.println("lng : " + 
                               ((Node)textMinList.item(0)).getNodeValue().trim());
                        output = output +((Node)textMinList.item(0)).getNodeValue().trim()+",";
                        //------

                        NodeList headingList = firstPredictionElement.getElementsByTagName("heading");
                        Element headingElement = (Element)headingList.item(0);

                        NodeList textHeadingList = headingElement.getChildNodes();
                        System.out.println("heading : " + 
                               ((Node)textHeadingList.item(0)).getNodeValue().trim());
                        output = output + ((Node)textHeadingList.item(0)).getNodeValue().trim()+",";
                    }
                }
            }
            catch (SAXParseException err) {
            System.out.println ("** Parsing error" + ", line " 
                 + err.getLineNumber () + ", uri " + err.getSystemId ());
            System.out.println(" " + err.getMessage ());
            }
            catch (SAXException e) {
            Exception x = e.getException ();
            ((x == null) ? e : x).printStackTrace ();
            }
            catch (Throwable t) {
            t.printStackTrace ();
            }
            System.out.println(temp.equals(output));
            if (! temp.equals(output)){
                writer.println(Time);
                writer.println(output);
            }//if (temp!= current) write
            temp = output;
            System.out.print(output);
            output="";   
        }//out while
            writer.close();//close 
        
    }
    
    public void getBus(){
        
    }
}
