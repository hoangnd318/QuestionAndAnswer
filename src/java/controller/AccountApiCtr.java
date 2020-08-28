/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.account.AccountConnection;
import dao.course.CourseConnection;
import dao.department.DepartmentConnection;
import dao.person.PersonConnection;
import dao.user.UserConnection;
import dao.user_account.UserAccountConnection;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Course;
import model.Department;
import model.Person;
import model.ResponseRequest;
import model.User;
import model.UserAccount;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AccountServiceFactory;
import service.AccountServiceInterface;
import service.ConvertTimestamp;

/**
 *
 * @author nguye
 */
@Controller
@RequestMapping(value = "qanda", method = RequestMethod.GET)
public class AccountApiCtr {

    AccountServiceFactory accountServiceFactory = new AccountServiceFactory();
    AccountServiceInterface accountServiceInterface = accountServiceFactory.getAccountService(
            AccountServiceFactory.USER_ACCOUNT_SERVICE
    );

    //xac thuc dang nhap
    @RequestMapping(value = "checkLoginToAction", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String checkLoginToAction(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = accountServiceInterface.checkLogin(session, request);
        ResponseRequest responseRequest = null;
        String action = request.getParameter("action");
        if (user == null) {
            if (action.compareTo("add-question") == 0) {
                responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "Bạn phải đăng nhập để đặt câu hỏi!");
            } else if (action.compareTo("add-answer") == 0) {
                responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "Bạn phải đăng nhập để gửi câu trả lời!");
            }
        } else {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "ACCESS!");
        }
        ArrayList<ResponseRequest> responseRequests = new ArrayList<>();
        responseRequests.add(responseRequest);
        String result = new Gson().toJson(responseRequests);
        return result;
    }

    //kiem tra dang nhap
    @RequestMapping(value = "checkLogin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String checkLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = AccountConnection.getAccountConnection().checkLogin(username, password);
        ResponseRequest responseRequest = null;
        if (user == null) {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "Tài khoản hoặc mật khẩu nhập vào không đúng!");
        } else {
            accountServiceInterface.login(user, session, request);
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "Đăng nhập thành công!");
        }
        ArrayList<ResponseRequest> responseRequests = new ArrayList<>();
        responseRequests.add(responseRequest);
        String result = new Gson().toJson(responseRequests);
        return result;
    }

    //tao user moi
    @RequestMapping(value = "register/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String registerSave(HttpServletRequest request, HttpSession session) {
        ResponseRequest responseRequest = null;
        //thong tin tai khoan
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = new Account();
        account.setEmail(email);
        account.setUsername(username);
        account.setPassword(password);
        int account_id = AccountConnection.getAccountConnection().createNewAccount(account);
        //tao tai khoan
        UserAccount userAccount = new UserAccount();
        userAccount.setId(account_id);
        int user_account_id = UserAccountConnection.getUserAccountConnection().createNewUserAccount(userAccount);
        userAccount.setIdUserAccount(user_account_id);
        //thong tin ng dung
        String firstname = request.getParameter("firstname");
        String midname = request.getParameter("midname");
        String lastname = request.getParameter("lastname");
        int b_d = Integer.parseInt(request.getParameter("b_d"));
        int b_m = Integer.parseInt(request.getParameter("b_m"));
        int b_y = Integer.parseInt(request.getParameter("b_y"));
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        Person person = new Person();
        person.setFirstname(firstname);
        person.setLastname(lastname);
        person.setMidname(midname);
        person.setAddress(address);
        person.setGender(gender);
        ConvertTimestamp ct = new ConvertTimestamp();
        Timestamp birthday = ct.convertDay(b_d, b_m, b_y);
        person.setBirthday(birthday);
        int person_id = PersonConnection.getPersonConnection().createNewPerson(person);
        person.setId(person_id);
        //thong tin user bo sung
        String studen_code = request.getParameter("studen_code");
        int department_id = Integer.parseInt(request.getParameter("department"));
        Department department = DepartmentConnection.getDepartmentConnection().findById(department_id);
        int course_id = Integer.parseInt(request.getParameter("course"));
        Course course = CourseConnection.getCourseConnection().findById(course_id);
        User user = new User();
        user.setPointQA(0);
        user.setStudentCode(studen_code);
        user.setDepartment(department);
        user.setCourse(course);
        user.setAccount(userAccount);
        user.setId(person_id);
        user.setType("SV");
        int user_id = UserConnection.getUserConnection().createNewUser(user);
        //dang nhap
        accountServiceInterface.login(
                UserConnection.getUserConnection().findById(user_id),
                session,
                request
        );
        responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "access");
        String result = new Gson().toJson(responseRequest);
        return result;
    }

    //chinh sua
    @RequestMapping(value = "register/edit/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String registerEditSave(HttpServletRequest request, HttpSession session) {
        ResponseRequest responseRequest = null;
        User userCurrent = accountServiceInterface.checkLogin(session, request);
        //thong tin tai khoan
        String email = request.getParameter("email");
        Account account = AccountConnection.getAccountConnection().findByEmail(userCurrent.getAccount().getEmail());
        account.setEmail(email);
        int account_id = AccountConnection.getAccountConnection().updateEmail(account);

        //thong tin ng dung
        String firstname = request.getParameter("firstname");
        String midname = request.getParameter("midname");
        String lastname = request.getParameter("lastname");
        int b_d = Integer.parseInt(request.getParameter("b_d"));
        int b_m = Integer.parseInt(request.getParameter("b_m"));
        int b_y = Integer.parseInt(request.getParameter("b_y"));
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        Person person = PersonConnection.getPersonConnection().findById(userCurrent.getId());
        person.setFirstname(firstname);
        person.setLastname(lastname);
        person.setMidname(midname);
        person.setAddress(address);
        person.setGender(gender);
        ConvertTimestamp ct = new ConvertTimestamp();
        Timestamp birthday = ct.convertDay(b_d, b_m, b_y);
        person.setBirthday(birthday);
        PersonConnection.getPersonConnection().update(person);

        //thong tin user bo sung
        if (userCurrent.getType().compareTo("SV") == 0) {
            String studen_code = request.getParameter("studen_code");
            int department_id = Integer.parseInt(request.getParameter("department"));
            Department department = DepartmentConnection.getDepartmentConnection().findById(department_id);
            int course_id = Integer.parseInt(request.getParameter("course"));
            Course course = CourseConnection.getCourseConnection().findById(course_id);
            //sua user
            userCurrent.setStudentCode(studen_code);
            userCurrent.setDepartment(department);
            userCurrent.setCourse(course);
        }
        UserConnection.getUserConnection().updateProfileSV(userCurrent);
        accountServiceInterface.login(
                UserConnection.getUserConnection().findById(userCurrent.getIdUser()),
                session,
                request
        );
        responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "access");
        String result = new Gson().toJson(responseRequest);
        return result;
    }

    //doi mat khau
    @RequestMapping(value = "account/password/edit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String accountPasswordEdit(HttpServletRequest request, HttpSession session) {
        ResponseRequest responseRequest = null;
        User userCurrent = accountServiceInterface.checkLogin(session, request);
        //thong tin tai khoan
        Account account = AccountConnection.getAccountConnection().findByEmail(userCurrent.getAccount().getEmail());
        String password = request.getParameter("password");
        account.setPassword(password);
        int account_id = AccountConnection.getAccountConnection().updatePassword(account);

        accountServiceInterface.login(
                UserConnection.getUserConnection().findById(userCurrent.getIdUser()),
                session,
                request
        );
        responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "access");
        String result = new Gson().toJson(responseRequest);
        return result;
    }

    //check trung email
    @RequestMapping(value = "account/checkEmail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String checkEmail(HttpServletRequest request, HttpSession session) {
        ResponseRequest responseRequest = null;
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        int flag = 0;
        if (AccountConnection.getAccountConnection().checkEmail(email).size() != 0) {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "Email đã tồn tại");
            flag = 1;
        }
        if (AccountConnection.getAccountConnection().checkUsername(username).size() != 0) {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ERROR, "Username đã tồn tại");
            flag = 1;
        }
        if (flag == 0) {
            responseRequest = new ResponseRequest(ResponseRequest.RESPONSE_ACCESS, "access");
        }
        String result = new Gson().toJson(responseRequest);
        return result;
    }
}
