/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.group.GroupConnection;
import dao.group_follow.GroupFollowConnection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Group;
import model.ResponseRequest;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AccountServiceFactory;
import service.AccountServiceInterface;

/**
 *
 * @author nguye
 */
@Controller
@RequestMapping(value = "", method = RequestMethod.GET)
public class GroupApiCtr {

    AccountServiceFactory accountServiceFactory = new AccountServiceFactory();
    AccountServiceInterface accountServiceInterface = accountServiceFactory.getAccountService(
            AccountServiceFactory.USER_ACCOUNT_SERVICE
    );

    //them theo doi group
    @RequestMapping(value = "groups/addfollow", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String groupsAddFollow(HttpServletRequest request, HttpSession session) {
        ResponseRequest responseRequest = null;

        User user = accountServiceInterface.checkLogin(session, request);
        if (user != null) {
            int g_id = Integer.parseInt(request.getParameter("g_id"));
            GroupFollowConnection.getAnswerConnection().addFollow(g_id, user.getIdUser(), user.getType());
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "Lưu thành công!");
        } else {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "Đã xảy ra lỗi, lưu câu hỏi không thành công!");
        }

        String result = new Gson().toJson(responseRequest);
        return result;
    }

    //bo theo doi
    @RequestMapping(value = "groups/unfollow", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String groupsUnfollow(HttpServletRequest request, HttpSession session) {
        ResponseRequest responseRequest = null;

        User user = accountServiceInterface.checkLogin(session, request);
        if (user != null) {
            int g_id = Integer.parseInt(request.getParameter("g_id"));
            GroupFollowConnection.getAnswerConnection().unFollow(g_id, user.getIdUser());
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "Lưu thành công!");
        } else {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "Đã xảy ra lỗi, lưu câu hỏi không thành công!");
        }

        String result = new Gson().toJson(responseRequest);
        return result;
    }
    
    @RequestMapping(value = "groups/create/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String groupsCreateSave(HttpServletRequest request, HttpSession session) {
        //phan hoi
        ResponseRequest responseRequest = null;
        ArrayList<ResponseRequest> responseRequests = new ArrayList<>();
        //cac thong tin
        User user = accountServiceInterface.checkLogin(session, request);
        if (user != null) {
            try {
                String g_name = request.getParameter("g_name");
                String g_description = request.getParameter("g_description");
                //
                Group group = new Group();
                group.setName(g_name);
                group.setDescription(g_description);

                //
                Integer group_id = GroupConnection.getGroupConnection().createGroup(group);
                
                responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "Lưu thành công!");
                responseRequests.add(responseRequest);
                responseRequest = new ResponseRequest("id", group_id.toString());
                responseRequests.add(responseRequest);
                //them follow
                GroupFollowConnection.getAnswerConnection().addFollow(group_id, user.getIdUser(), "admin");

            } catch (Exception e) {
                responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "Đã xảy ra lỗi, lưu câu hỏi không thành công!");
                responseRequests.add(responseRequest);
            }
        } else {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "Đã xảy ra lỗi, lưu câu hỏi không thành công!");
            responseRequests.add(responseRequest);
        }
        String result = new Gson().toJson(responseRequests);
        return result;
    }
}
