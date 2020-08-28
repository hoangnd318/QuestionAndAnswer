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
public interface TagStatisticalQueryInterface {
    public int addNewStatistical(int tag_id);
    public ArrayList<Tag> getStatistical();
    public ArrayList<Tag> getTagPopular(int page);
    public ArrayList<Tag> getTagName(int page);
    public ArrayList<Tag> getTagCreateTime(int page);
    public int countTag();
}
