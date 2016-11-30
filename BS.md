这个是我的send的账号和密码
EcoKz_test_slQj9I

1TeCeyHK8YtKdkfe

//将登陆返回的值放入session中
session.setAttribute("user", user);

//将图片验证码放入session中
request.getSession(true).setAttribute("pictureCode", randomString);

//将邮件验证码放入session中
session.setAttribute("EmailCode", number);

1.发送邮件接口

url : http://127.0.0.1:8080/fastpan/verify/sendEmail

参数

{
    "requestContext":"524329278@qq.com" 
}

返回值

{
    "code": 0,
    "message": "",
    "success": true,
    "result": "8FKB6U"
}

2.用户注册接口

url : http://127.0.0.1:8080/fastpan/user/register

参数

{
    "requestContext": {
        "email": "1171084616@qq.com",
        "password": "abc123",
        "nickName": "PBI"
    }
}

返回值

{
    "code": 0,
    "message": "",
    "success": true,
    "result": 1
}

3.邮箱验证接口

url : http://127.0.0.1:8080/fastpan/verify/checkAccount

参数

{
    "requestContext":"1171084616@qq.com" 
}

返回值

{
    "code": 0,
    "message": "",
    "success": true,
    "result": true
}

4.登录接口

url : http://127.0.0.1:8080/fastpan/user/loginAccount

参数

{
    "requestContext": {
        "email": "1171084616@qq.com",
        "password": "abc123"
    }
}

结果

{
    "code": 0,
    "message": "",
    "success": true,
    "result": "登录成功"
}

5.生成图片验证码

url : http://127.0.0.1:8080/fastpan/verify/pictureCode

参数


  width=30   height=18 改了变成get提交了


结果

图片一张

6.验证图片验证码

url : http://127.0.0.1:8080/fastpan/verify/testPictureCode

参数

{
    "requestContext":"5TY28Q"
}

结果

{"code":0,"message":"","success":true,"result":null}

7.登录接口添加验证码

url : http://127.0.0.1:8080/fastpan/user/loginCode

参数

{
    "requestContext": {
        "email": "1171084616@qq.com",
        "password": "abc123",
        "code":"PW9RKM"
    }
}

结果

{
    "code": 0,
    "message": "",
    "success": true,
    "result": {
        "userId": 1,
        "email": "1171084616@qq.com",
        "password": "e99a18c428cb38d5f260853678922e03",
        "nickName": "PBI",
        "roleId": 0,
        "createTime": 1478596283000,
        "lastLoginTime": null,
        "updateTime": null
    }
}

8.验证邮箱发送验证码

url : http://127.0.0.1:8080/fastpan/verify/testEmailCode

参数

{
    "requestContext":"N9M11E"
}

结果

{
    "code": 0,
    "message": "",
    "success": true,
    "result": "验证码正确"
}

9.注册接口添加验证码

url : http://127.0.0.1:8080/fastpan/user/registerCode

参数

{
    "requestContext": {
        "email": "1171084616@qq.com",
        "password": "abc123",
        "nickName": "PBI",
        "code":"PW9RKM"
    }
}

结果

{
    "code": 0,
    "message": "",
    "success": true,
    "result": 1
}

10. 查询用户信息

url： http://127.0.0.1:8080/fastpan/user/getPersonInfor
参数

get请求

结果

{
    "code": 0,
    "message": "",
    "success": true,
    "result": {
        "userId": "1",
        "email": "1171084616@qq.com",
        "password": "e99a18c428cb38d5f260853678922e03",
        "nickName": "PBI",
        "roleId": 0,
        "createTime": 1478596283000,
        "lastLoginTime": 1480405977000,
        "updateTime": null,
        "image": null
    }
}

11.修改头像 

url: http://127.0.0.1:8080/fastpan/user/updateImage

参数

{
    "requestContext": "wqweqweq"
}

结果

{
    "code": 0,
    "message": "",
    "success": true,
    "result": "更新头像成功"
}

12.修改昵称

url: http://127.0.0.1:8080/fastpan/user/updateNickName

参数

{
    "requestContext": "天天"
}

结果

{
    "code": 0,
    "message": "",
    "success": true,
    "result": "更新昵称成功"
}

13.修改密码

url：http://127.0.0.1:8080/fastpan/user/updatePassword

参数

{
    "requestContext": {
        "password": "abc123",
        "newPassword": "111111"
    }
}

结果

{
    "code": 0,
    "message": "",
    "success": true,
    "result": "更新密码成功"
}

14.修改邮箱第一步

url: http://127.0.0.1:8080/fastpan/user/updateEmail

参数

{
    "requestContext": {
        "email": "390284187@qq.com",
        "newEmail": "1043244426@qq.com",
        "code": "78oow7"
    }
}

结果

{
    "code": 0,
    "message": "",
    "success": true,
    "result": null
}

15.修改邮箱第二步

url：http://127.0.0.1:8080/fastpan/user/updateEmail2

参数

{
    "requestContext":"M4ZG51" 
}

结果

{
    "code": 0,
    "message": "",
    "success": true,
    "result": "更新邮箱成功"
}

16.忘记密码

url: http://127.0.0.1:8080/fastpan/user/forgetPassEmail

参数

{
    "requestContext": {
        "email": "1043244426@qq.com",
        "password": "123456",
        "code": "FIU2Y0"
    }
}

结果

{
    "code": 0,
    "message": "",
    "success": true,
    "result": "更新密码成功"
}