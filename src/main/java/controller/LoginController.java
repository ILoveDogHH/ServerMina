package controller;

import cmdReturn.CmdReturn;
import com.alibaba.fastjson.JSONArray;
import org.apache.mina.core.session.IoSession;

/**
 * 登录请求操作
 */
public class LoginController {



    public static JSONArray userLogin(int uid, JSONArray params, IoSession ioSession){
        System.out.println("方法请求成功");
        return CmdReturn.success("成功");
    }


    public static JSONArray userLogin1(int uid, JSONArray params, IoSession ioSession){
        System.out.println("方法请求成功");
        return CmdReturn.success("成功");
    }




    public static JSONArray userLogin2(String uid, JSONArray params, IoSession ioSession){
        System.out.println("方法请求成功");
        return CmdReturn.success("成功");
    }








}
