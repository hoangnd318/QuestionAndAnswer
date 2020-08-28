/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.tfidf_dictionary;

/**
 *
 * @author nguye
 */
public class TFIDFDictionaryConnection {
    private static TFIDFDictionaryQueryInterface instance = null;
    
    public static TFIDFDictionaryQueryInterface getTFIDFDictionaryConnection(){
        if (instance == null) {
            instance = new TFIDFDictionaryQuery(new TFIDFDictionaryQueryFacade());
        }
        return instance;
    }
}
