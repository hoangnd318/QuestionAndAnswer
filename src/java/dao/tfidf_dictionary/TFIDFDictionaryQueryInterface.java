/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.tfidf_dictionary;

import java.util.List;

/**
 *
 * @author nguye
 */
public interface TFIDFDictionaryQueryInterface {

    public boolean checkExisDoc(String doc);

    public boolean addDocToDictionary(String doc);
    
    public List<String> getDictionary();
}
