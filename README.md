##环信服务端SDK-DEMO
   
##### 使用方式
  
  将此mode依赖到需要使用的项目里
	  
  此sdk里面有一个 示例：config.properties [[将此文件复制到您的项目]] src resource下 
   
##### 请求协议 http or  https（需要证书）
    API_PROTOCAL = http
##### 环信请求地址（根据实际业务选择）
##### IM接口地址：a1.easemob.com
##### 客服接口地址：a1-vip5.easemob.com
    API_HOST = a1.easemob.com 
##### 环信组织名
    API_ORG = xxx
##### 环信应用名称
    API_APP = xxx
##### clientid
    APP_CLIENT_ID = xxx
##### Secret
    APP_CLIENT_SECRET = xxx
##### rest接口请求方式 httpclient / jersey
    APP_IMP_LIB = httpclient
##### 证书路径
    CACERT_FILE_PATH = C:/Users/wstv/Desktop/xiaohongma.keystore
##### 证书密码
    CACERT_FILE_PASSWORD = xxx
    
    
#生成证书方式

##### step 1. 下载证书文件
打开浏览器，输入网址https://a1.easemob.com/status ，点击网址框左侧的锁形状，点击`详细信息`->`View certificate`->`复制到文件`，直到导出证书文件。

##### step 2. 使用keytool（
    jdk默认有keytool 配置环境变量即可 %JAVA_HOME%\bin\keytool.exe）工具创建证书库

    Keytool –genkey –alias "certificate" –keyalg "RSA" –keystore "test.keystore"
    
这里的test.keystore是生成的证书库,可以指定生成目录比如d:\test.keystore,这样就可以在d盘找到刚才生成的证书库了

alias：证书名称

keyalg：生成证书采用的算法，如RSA

keystore：证书库名称

##### step 3. 将证书导入到证书库中
    Keytool –import –keystore test.keystore –file C:\Users\wstv\Desktop\certificate.cer

  
  
###API  
    IMUserAPI.java
    
### 使用工具类
    IMUserHelper
###
    com.easemob.server.example.comm.IMUserHelper{
        public static Boolean createSingle
        public static Boolean modifyPassword
        public static Boolean modifyNickName
    }
    
     EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
     IMUserAPI user = (IMUserAPI) factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
    List<IMUserBody> users = new ArrayList<IMUserBody>();
    users.add(new IMUserBody("sqx_813", "e10adc3949ba59abbe56e057f20f883e", "test813"));
    users.add(new IMUserBody("sqx_815", "e10adc3949ba59abbe56e057f20f883e", "test815"));
    BodyWrapper usersBody = new IMUsersBody(users);
    user.createNewIMUserBatch(usersBody);
    
    
    /**
    	 * 注册IM用户[单个]  <br>
    	 * POST
    	 * 
    	 * @param payload
    	 *            <code>{"username":"${用户名}","password":"${密码}", "nickname":"${昵称值}"}</code>
    	 * @return
    	 */
    	Object createNewIMUserSingle(Object payload);
    
    	/**
    	 * 注册IM用户[批量] <br>
    	 * POST
    	 * 
    	 * @param payload
    	 *            <code>[{"username":"${用户名1}","password":"${密码}"},…,{"username":"${用户名2}","password":"${密码}"}]</code>
    	 * @return
    	 */
    	Object createNewIMUserBatch(Object payload);
    
    	/**
    	 * 获取IM用户[单个] <br>
    	 * GET
    	 * 
    	 * @param userName
    	 *            用戶名或用戶ID
    	 * @return
    	 */
    	Object getIMUserByUserName(String userName);
    
    	/**
    	 * 获取IM用户[批量]，参数为空时默认返回最早创建的10个用户 <br>
    	 * GET
    	 * 
    	 * @param limit
    	 *            单页获取数量
    	 * @param cursor
    	 *            游标，大于单页记录时会产生
    	 * @return
    	 */
    	Object getIMUsersBatch(Long limit, String cursor);
    
    	/**
    	 * 删除IM用户[单个] <br>
    	 * DELETE
    	 * 
    	 * @param userName
    	 *            用戶名或用戶ID
    	 * @return
    	 */
    	Object deleteIMUserByUserName(String userName);
    
    	/**
    	 * 删除IM用户[批量]，随机删除 <br>
    	 * DELETE
    	 * 
    	 * @param limit
    	 *            删除数量，建议100-500
    	 * @return
    	 */
    	Object deleteIMUserBatch(Long limit, String cursor);
    
    	/**
    	 * 重置IM用户密码 <br>
    	 * PUT
    	 * 
    	 * @param userName
    	 *            用戶名或用戶ID
    	 * @param payload
    	 *            <code>{"newpassword" : "${新密码指定的字符串}"}</code>
    	 * @return
    	 */
    	Object modifyIMUserPasswordWithAdminToken(String userName, Object payload);
    
    	/**
    	 * 修改用户昵称 <br>
    	 * PUT
    	 * 
    	 * @param userName
    	 *            用戶名或用戶ID
    	 * @param payload
    	 *            <code>{"nickname" : "${昵称值}"}</code>
    	 * @return
    	 */
    	Object modifyIMUserNickNameWithAdminToken(String userName, Object payload);
    
    	/**
    	 * 给IM用户的添加好友 <br>
    	 * POST
    	 * 
    	 * @param userName
    	 *            用戶名或用戶ID
    	 * @param friendName
    	 *            好友用戶名或用戶ID
    	 * @return
    	 */
    	Object addFriendSingle(String userName, String friendName);
    
    	/**
    	 * 解除IM用户的好友关系 <br>
    	 * DELETE
    	 * 
    	 * @param userName
    	 *            用戶名或用戶ID
    	 * @param friendName
    	 *            好友用戶名或用戶ID
    	 * @return
    	 */
    	Object deleteFriendSingle(String userName, String friendName);
    
    	/**
    	 * 查看某个IM用户的好友信息 <br>
    	 * GET
    	 * 
    	 * @param userName
    	 *            用戶名或用戶ID
    	 * @return
    	 */
    	Object getFriends(String userName);
    
    	/**
    	 * 获取IM用户的黑名单 <br>
    	 * GET
    	 * 
    	 * @param userName
    	 *            用戶名或用戶ID
    	 * @return
    	 */
    	Object getBlackList(String userName);
    
    	/**
    	 * 往IM用户的黑名单中加人 <br>
    	 * POST
    	 * 
    	 * @param userName
    	 *            用戶名或用戶ID
    	 * @param payload
    	 *            <code>{"usernames":["5cxhactgdj", "mh2kbjyop1"]}</code>
    	 * @return
    	 */
    	Object addToBlackList(String userName, Object payload);
    
    	/**
    	 * 从IM用户的黑名单中减人 <br>
    	 * DELETE
    	 * 
    	 * @param userName
    	 *            用戶名或用戶ID
    	 * @param blackListName
    	 *            黑名单用戶名或用戶ID
    	 * @return
    	 */
    	Object removeFromBlackList(String userName, String blackListName);
    
    	/**
    	 * 查看用户在线状态 <br>
    	 * GET
    	 * 
    	 * @param userName
    	 *            用戶名或用戶ID
    	 * @return
    	 */
    	Object getIMUserStatus(String userName);
    
    	/**
    	 * 查询离线消息数 <br>
    	 * GET
    	 * 
    	 * @param userName
    	 *            用戶名或用戶ID
    	 * @return
    	 */
    	Object getOfflineMsgCount(String userName);
    
    	/**
    	 * 查询某条离线消息状态 <br>
    	 * GET
    	 * 
    	 * @param userName
    	 *            用戶名或用戶ID
    	 * @param msgId
    	 *            消息ID
    	 * @return
    	 */
    	Object getSpecifiedOfflineMsgStatus(String userName, String msgId);
    
    	/**
    	 * 用户账号禁用 <br>
    	 * POST
    	 * 
    	 * @param userName
    	 *            用戶名或用戶ID
    	 * @return
    	 */
    	Object deactivateIMUser(String userName);
    
    	/**
    	 * 用户账号解禁 <br>
    	 * POST
    	 * 
    	 * @param userName
    	 *            用戶名或用戶ID
    	 * @return
    	 */
    	Object activateIMUser(String userName);
    
    	/**
    	 * 强制用户下线 <br>
    	 * GET
    	 * 
    	 * @param userName
    	 *            用戶名或用戶ID
    	 * @return
    	 */
    	Object disconnectIMUser(String userName);
    
    	/**
    	 * 获取用户参与的群组 <br>
    	 * GET
    	 * 
    	 * @param userName
    	 *            用戶名或用戶ID
    	 * @return
    	 * @see http://docs.easemob.com/doku.php?id=start:100serverintegration:
    	 *      60groupmgmt
    	 */
    	Object getIMUserAllChatGroups(String userName);
    
    	/**
    	 * 获取用户所有参与的聊天室 <br>
    	 * GET
    	 * 
    	 * @param userName
    	 *            用戶名或用戶ID
    	 * @return
    	 * @see http://docs.easemob.com/doku.php?id=start:100serverintegration:
    	 *      70chatroommgmt
    	 */
    	Object getIMUserAllChatRooms(String userName);



