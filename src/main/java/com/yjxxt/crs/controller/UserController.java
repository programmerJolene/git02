package com.yjxxt.crs.controller;

import com.yjxxt.crs.base.BaseController;
import com.yjxxt.crs.base.ResultInfo;
import com.yjxxt.crs.bean.User;
import com.yjxxt.crs.model.UserModel;
import com.yjxxt.crs.query.UserQuery;
import com.yjxxt.crs.service.PermissionService;
import com.yjxxt.crs.service.UserService;
import com.yjxxt.crs.utils.AssertUtil;
import com.yjxxt.crs.utils.LoginUserUtil;
import com.yjxxt.crs.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;


    /**
     * 用户登录
     *
     * @param userName
     * @param userPwd  * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public ResultInfo userLogin(String userName, String userPwd) {

        ResultInfo resultInfo = new ResultInfo();

        // 调用Service层的登录方法，得到返回的用户对象
        UserModel userModel = userService.userLogin(userName, userPwd);
        /**
         * 登录成功后，有两种处理：
         * 1. 将用户的登录信息存入 Session （ 问题：重启服务器，Session 失效，客户端
         需要重复登录 ）
         * 2. 将用户信息返回给客户端，由客户端（Cookie）保存
         */
        // 将返回的UserModel对象设置到 ResultInfo 对象中
        resultInfo.setResult(userModel);

        return resultInfo;
    }


    /**
     * 用户信息
     */
    @RequestMapping("toSettingPage")
    public String toSetting(HttpServletRequest req) {

        // 获取用户id
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(req);

        // 调用方法，根据id查询用户
        User user = userService.selectByPrimaryKey(userId);

        // 存储
        req.setAttribute("user", user);

        // 请求转发
        return "user/setting";
    }

    /**
     * 用户信息修改
     */

    @RequestMapping("setting")
    @ResponseBody
    public ResultInfo sayUpdate(User user) {

        ResultInfo resultInfo = new ResultInfo();

        // 调用Service层的修改信息的方法，
        userService.updateByPrimaryKeySelective(user);

        return resultInfo;
    }

    /**
     * 用户密码修改
     *
     * @param request
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @return
     */
    @PostMapping("updatePassword")
    @ResponseBody
    public ResultInfo updateUserPassword(HttpServletRequest request, String oldPassword, String newPassword, String confirmPassword) {

        ResultInfo resultInfo = new ResultInfo();

        // 获取userId
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        // 调用Service层的密码修改方法
        userService.updateUserPassword(userId, oldPassword, newPassword, confirmPassword);

        return resultInfo;
    }

    // 在 UserController 控制层，添加对应的视图转发方法
    @RequestMapping("toPasswordPage")
    public String toPasswordPage() {
        return "user/password";
    }



    /**
     * 多条件分页查询用户 (BaseService 中有对应的方法)
     *      @param query
     *      @return
     * */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryUserByParams(UserQuery query){
        return userService.queryUserByParams(query);
    }

    // 视图转发
    @RequestMapping("user")
    public String userIndex(){
        return "user/user";
    }

    /**
     * 添加用户
     *
     * */
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveUser(User user){

        userService.saveUser(user);
        return success("用户添加成功");
    }

    // 视图转发
    @RequestMapping("addOrUpdateUserPage")
    public String addOrUpdateUserPage(Integer id, Model model){
        if(null != id){
            model.addAttribute("user",userService.selectByPrimaryKey(id));
        }
        return "user/add_update";
    }





}
