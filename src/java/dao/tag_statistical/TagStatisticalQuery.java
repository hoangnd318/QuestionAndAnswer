/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.tag_statistical;

import java.util.ArrayList;
import model.Tag;

/**
 *
 * @author nguye
 */
public class TagStatisticalQuery implements TagStatisticalQueryInterface{
    private TagStatisticalQueryFacade tsqf;
    public TagStatisticalQuery(TagStatisticalQueryFacade tsqf){
        this.tsqf = tsqf;
    }
    
    @Override
    public int addNewStatistical(int tag_id) {
        return this.tsqf.update(TagStatisticalQueryFacade.ADD_STATISTICAL, tag_id);
    }

    @Override
    public ArrayList<Tag> getStatistical() {
        return this.tsqf.find(TagStatisticalQueryFacade.FIND_TREND, null);
    }

    @Override
    public ArrayList<Tag> getTagPopular(int page) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(page);
        return this.tsqf.find(TagStatisticalQueryFacade.FIND_ALL_SORT_POPULAR,key);
    }

    @Override
    public ArrayList<Tag> getTagName(int page) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(page);
        return this.tsqf.find(TagStatisticalQueryFacade.FIND_ALL_SORT_NAME,key);
    }

    @Override
    public ArrayList<Tag> getTagCreateTime(int page) {
        ArrayList<Object> key = new ArrayList<>();
        key.add(page);
        return this.tsqf.find(TagStatisticalQueryFacade.FIND_ALL_SORT_CREATE_TIME,key);
    }

    @Override
    public int countTag() {
        return this.tsqf.countTag();
    }
    
}
