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
public interface TagQueryInterface {
    public ArrayList<Tag> findAll();
    public Tag findById(int id);
    public ArrayList<Tag> findByName(String name);
    public ArrayList<Tag> findLikeName(String name);
    public int addNewTag(Tag tag);
}
