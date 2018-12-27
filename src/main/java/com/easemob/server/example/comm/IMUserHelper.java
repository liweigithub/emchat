package com.easemob.server.example.comm;

import com.easemob.server.example.api.IMUserAPI;
import com.easemob.server.example.comm.body.IMUserBody;
import com.easemob.server.example.comm.body.ModifyNicknameBody;
import com.easemob.server.example.comm.body.ResetPasswordBody;
import com.easemob.server.example.comm.wrapper.BodyWrapper;
import com.easemob.server.example.comm.wrapper.ResponseWrapper;

/**
 * @author wstv 环信用户 帮助类
 */
public class IMUserHelper
{
    public static Boolean createSingle(Integer user_id, String user_password, String nickname)
    {
        EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
        IMUserAPI user = (IMUserAPI) factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
        BodyWrapper userBody = new IMUserBody(generateIMUserName(user_id), user_password, nickname);
        ResponseWrapper responseWrapper = (ResponseWrapper) user.createNewIMUserSingle(userBody);
        if(responseWrapper.getResponseStatus().intValue() == 200)
        {
            return true;
        }
        //如果已经存在可能返回400
        if(responseWrapper.getResponseStatus().intValue() == 400)
        {
            return modifyPassword(user_id, user_password) && modifyNickName(user_id, nickname);
        }
        return false;
    }
    
    public static Boolean modifyPassword(Integer user_id, String user_password)
    {
        EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
        IMUserAPI user = (IMUserAPI) factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
        ResetPasswordBody resetPasswordBody = new ResetPasswordBody(user_password);
        ResponseWrapper responseWrapper = (ResponseWrapper) user.modifyIMUserPasswordWithAdminToken(generateIMUserName(user_id), resetPasswordBody);
        if(responseWrapper.getResponseStatus().intValue() == 200)
        {
            return true;
        }
        return false;
    }
    
    public static Boolean modifyNickName(Integer user_id, String nickname)
    {
        if (nickname == null || "".equals(nickname)){
            return false;
        }
        EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
        IMUserAPI user = (IMUserAPI) factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
        ModifyNicknameBody modifyNicknameBody = new ModifyNicknameBody(nickname);
        ResponseWrapper responseWrapper = (ResponseWrapper) user.modifyIMUserNickNameWithAdminToken(generateIMUserName(user_id), modifyNicknameBody);
        if(responseWrapper.getResponseStatus().intValue() == 200)
        {
            return true;
        }
        return false;
    }
    
    private static String generateIMUserName(Integer user_id)
    {
       return "";
    }
}
