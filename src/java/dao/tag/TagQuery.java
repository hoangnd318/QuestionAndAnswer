/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.tag;

import java.util.ArrayList;
import model.Tag;

/**
 *
 * @author nguye
 */
public class TagQuery implements TagQueryInterface{
    
    private TagQueryFacade tagQueryFacade;
    
    public TagQuery(TagQueryFacade tagQueryFacade){
        this.tagQueryFacade = tagQueryFacade;
    }

    @Override
    public ArrayList<Tag> findAll() {
        return this.tagQueryFacade.find(TagQueryFacade.FIND_ALL, null);
    }

    @Override
    public Tag findById(int id) {
        return this.tagQueryFacade.find(TagQueryFacade.FIND_BY_ID, id).get(0);
    }

    @Override
    public ArrayList<Tag> findByName(String name) {
        return this.tagQueryFacade.find(TagQueryFacade.FIND_BY_NAME, name);
    }

    @Override
    public ArrayList<Tag> findLikeName(String name) {
        return this.tagQueryFacade.find(TagQueryFacade.FIND_LIKE_NAME, name);
    }

    @Override
    public int addNewTag(Tag tag) {
        return this.tagQueryFacade.update(TagQueryFacade.ADD_NEW_TAG, tag);
    }
        
}
