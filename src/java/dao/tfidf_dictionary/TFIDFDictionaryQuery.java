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
public class TFIDFDictionaryQuery implements TFIDFDictionaryQueryInterface{

    private TFIDFDictionaryQueryFacade tfidf;
    public TFIDFDictionaryQuery(TFIDFDictionaryQueryFacade tfidf){
        this.tfidf = tfidf;
    }
    
    @Override
    public boolean checkExisDoc(String doc) {
        return this.tfidf.check(doc);
    }

    @Override
    public boolean addDocToDictionary(String doc) {
        return this.tfidf.update(doc);
    }    

    @Override
    public List<String> getDictionary() {
        return this.tfidf.get();
    }
}
