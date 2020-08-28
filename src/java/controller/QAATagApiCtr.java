/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.tag.TagQuery;
import dao.tag.TagQueryFacade;
import dao.tag.TagQueryInterface;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import model.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author nguye
 */
@Controller
@RequestMapping(value = "qanda", method = RequestMethod.GET)
public class QAATagApiCtr {
    
    //tim kiem tag
    @RequestMapping(value = "searchTag", method = RequestMethod.POST)
    @ResponseBody
    public String serachTag(HttpServletRequest request) {
        String key = request.getParameter("key");
        TagQueryInterface tagQueryInterface = new TagQuery(new TagQueryFacade());
        ArrayList<Tag> tags = tagQueryInterface.findLikeName(key);
        String result = new Gson().toJson(tags);
        return result;
    }
}
