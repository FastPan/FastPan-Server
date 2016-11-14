这个是我的send的账号和密码
EcoKz_test_slQj9I

1TeCeyHK8YtKdkfe

//将登陆返回的值放入session中
session.setAttribute("user", user);

//将验证码放入session中
request.getSession(true).setAttribute("pictureCode", randomString);

1.发送邮件接口

url : http://127.0.0.1:8080/fastpan/user/sendEmail

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
        "nickName": "PBI",
        "sex": 1
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

url : http://127.0.0.1:8080/fastpan/user/checkAccount

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
    "result": {
        "userId": 1,
        "email": "1171084616@qq.com",
        "password": "e99a18c428cb38d5f260853678922e03",
        "nickName": "PBI",
        "roleId": 0,
        "createTime": 1478596283000,
        "lastLoginTime": null,
        "updateTime": null,
        "sex": 1
    }
}

5.生成图片验证码

url : http://127.0.0.1:8080/fastpan/user/pictureCode

参数

{
    "requestContext": {"width":30,"height":18}
}

结果

图片一张

6.验证图片验证码

url : http://127.0.0.1:8080/fastpan/user/testPictureCode

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
        "code":"M6RNHB"
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
        "updateTime": null,
        "sex": 1
    }
}