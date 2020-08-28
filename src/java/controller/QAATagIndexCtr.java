/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.tag_statistical.TagStatisticalConnection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author nguye
 */
@Controller
@RequestMapping(value = "qanda", method = RequestMethod.GET)
public class QAATagIndexCtr {

    @RequestMapping(value = "tags")
    public String tagIndex(
            HttpServletRequest request,
            ModelMap model,
            HttpSession session
    ) {
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        String typeSort = "noi-bat";
        if (request.getParameter("sort") != null) {
            typeSort = request.getParameter("sort");
        }
        if (typeSort == null || typeSort.compareTo("noi-bat") == 0) {
            setContent(
                    model,
                    TagStatisticalConnection.getTagStatisticalConnection().countTag(),
                    TagStatisticalConnection.getTagStatisticalConnection().getTagPopular(page)
            );
        } else if (typeSort.compareTo("theo-ten") == 0) {
            setContent(
                    model,
                    TagStatisticalConnection.getTagStatisticalConnection().countTag(),
                    TagStatisticalConnection.getTagStatisticalConnection().getTagName(page)
            );
        } else if (typeSort.compareTo("moi-nhat") == 0) {
            setContent(
                    model,
                    TagStatisticalConnection.getTagStatisticalConnection().countTag(),
                    TagStatisticalConnection.getTagStatisticalConnection().getTagCreateTime(page)
            );
        }
        
        model.addAttribute("page_current", page);
        model.addAttribute(
                "tag_statistical",
                TagStatisticalConnection.getTagStatisticalConnection().getStatistical()
        );
        model.addAttribute("type_sort", typeSort);
        return "view/frontend/templates/qanda/qanda_tag_index";
    }

    public void setContent(ModelMap model, int countTag, ArrayList<Tag> tags) {
        model.addAttribute(
                "tags",
                tags
        );
        model.addAttribute("tag_count", countTag);
        int pageTotal = 0;
        if (countTag % 21 == 0) {
            pageTotal = countTag / 21;
        } else {
            pageTotal = (int) (countTag / 21);
            pageTotal++;
        }
        model.addAttribute("page_total", pageTotal);
    }
}
