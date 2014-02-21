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
public class upComingBus {
    private String tag;
    private String agency;
    private int prediction;
    private String direction;
    
    public upComingBus(){
        tag=null;
        agency=null;
        prediction=-1;
        direction=null;
    }
    
    public void setTag(String tag){
        this.tag=tag;
    }
    
    public String getTag(){
        return tag;
    }
    
    public void setAgency(String agency){
        this.agency=agency;
    }
    
    public String getAgency(){
        return agency;
    }
    
    public void setPrediction(int prediction){
        this.prediction=prediction;
    }
    
    public int getPrediction(){
        return prediction;
    }
    
    public void setDirection(String direction){
        this.direction=direction;
    }
    
    public String getDirection(){
        return direction;
    }
}
