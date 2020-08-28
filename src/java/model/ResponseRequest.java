/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author nguye
 */
public class ResponseRequest {
    public static String RESPONSE_ERROR = "ERROR";
    public static String RESPONSE_ACCESS = "ACCESS";
    
    private String type;
    private String depscription;

    public ResponseRequest(String type, String depscription) {
        this.type = type;
        this.depscription = depscription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    

    public String getDepscription() {
        return depscription;
    }

    public void setDepscription(String depscription) {
        this.depscription = depscription;
    }
    
    
}
