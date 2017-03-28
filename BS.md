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

11.修改头像 （已经变成GET请求）   头像上传接口已完成

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

17 上传头像已经变动了

##管理员接口

下面的接口有部分接口加了GET方法

18.获取用户列表

http://127.0.0.1:8080/fastpan/manageUser/getUserList

入参

{
    "requestContext": {
        "pageNum":1,
        "pageSize":10
    }
}

返回值

{
    "code": 0,
    "message": "",
    "success": true,
    "result": {
        "total": 6,
        "list": [
            {
                "userId": "470daddf52b74ddd90d012faa6c4e8c3",
                "email": "1043244426@qq.com",
                "password": "31528198109743225ff9d0cf04d1fdd1",
                "nickName": "呵呵",
                "roleId": 1,
                "createTime": 1480384455000,
                "lastLoginTime": 1490488429000,
                "updateTime": 1490488677000,
                "image": "../images/1490488666062abc.jpg"
            },
            {
                "userId": "1",
                "email": "1171084616@qq.com",
                "password": "e99a18c428cb38d5f260853678922e03",
                "nickName": "PBI",
                "roleId": 1,
                "createTime": 1478596283000,
                "lastLoginTime": 1480405977000,
                "updateTime": null,
                "image": null
            },
            {
                "userId": "2",
                "email": "117108@qq.com",
                "password": "e99a18c428cb38d5f260853678922e03",
                "nickName": "你好",
                "roleId": 1,
                "createTime": 1479915320000,
                "lastLoginTime": null,
                "updateTime": null,
                "image": null
            },
            {
                "userId": "3",
                "email": "1171084617@qq.com",
                "password": "e99a18c428cb38d5f260853678922e03",
                "nickName": "安慰安慰",
                "roleId": 1,
                "createTime": 1479915393000,
                "lastLoginTime": null,
                "updateTime": null,
                "image": null
            },
            {
                "userId": "4",
                "email": "1171084618@qq.com",
                "password": "e99a18c428cb38d5f260853678922e03",
                "nickName": "安慰安慰",
                "roleId": 1,
                "createTime": 1479915454000,
                "lastLoginTime": null,
                "updateTime": null,
                "image": null
            },
            {
                "userId": "6028a875a2a547138097d789ec7ecfe0",
                "email": "390284188@qq.com",
                "password": "e99a18c428cb38d5f260853678922e03",
                "nickName": "呵呵",
                "roleId": 1,
                "createTime": 1480400686000,
                "lastLoginTime": null,
                "updateTime": null,
                "image": null
            }
        ],
        "pageNum": 1,
        "pageSize": 10,
        "pages": 1,
        "size": 6
    }
}

19.更新用户信息

http://127.0.0.1:8080/fastpan/manageUser/updateUserInfor

入参

{
    "requestContext": {
                "userId": "3",
                "roleId": 2
            }
}

结果

{
    "code": 0,
    "message": "",
    "success": true,
    "result": "更新成功"
}

20.获取单条用户信息

http://127.0.0.1:8080/fastpan/manageUser/getUserInfor

参数

{
    "requestContext": {
                "userId": "3"
            }
}

结果

{
    "code": 0,
    "message": "",
    "success": true,
    "result": {
        "userId": "3",
        "email": "1171084617@qq.com",
        "password": "e99a18c428cb38d5f260853678922e03",
        "nickName": "安慰安慰",
        "roleId": 2,
        "createTime": 1479915393000,
        "lastLoginTime": null,
        "updateTime": 1490525334000,
        "image": null
    }
}

21.获取权限列表详情

http://127.0.0.1:8080/fastpan/manageRole/getRoleList  get

结果

{
    "code": 0,
    "message": "",
    "success": true,
    "result": [
        {
            "roleId": 1,
            "level": 0,
            "roleName": "普通用户"
        },
        {
            "roleId": 2,
            "level": 1,
            "roleName": "会员"
        },
        {
            "roleId": 3,
            "level": 99,
            "roleName": "已冻结"
        }
    ]
}


22.增加权限

http://127.0.0.1:8080/fastpan/manageRole/insertRoleInfor

入参

{
    "requestContext": {
            "roleId": 4,
            "level": 2,
            "roleName": "超级会员"
        }
}

结果

{
    "code": 0,
    "message": "",
    "success": true,
    "result": "更新成功"
}

23.获取文件列表

http://127.0.0.1:8080/fastpan/manageFile/getFileList

入参

{
    "requestContext": {
        "pageNum":1,
        "pageSize":10
    }
}

结果

{
    "code": 0,
    "message": "",
    "success": true,
    "result": {
        "total": 12,
        "list": [
            {
                "fileId": "71bf58b57d914bb28d3c840b785d7eb8",
                "fileName": "2b9b4271e23c69f9d121b59476968eaa",
                "fileSize": 40121176,
                "fileUrl": "f:\\filetemp\\2017\\03\\15\\09\\07\\26\\2b9b4271e23c69f9d121b59476968eaa\\619457c6a49942b790eb014552d8c6f3\\2b9b4271e23c69f9d121b59476968eaadll",
                "fileMd5": "2b9b4271e23c69f9d121b59476968eaa",
                "fileType": 0,
                "fileState": 0,
                "createTime": 1489540048000,
                "updateTime": 1489540048000
            },
            {
                "fileId": "eb40a5372f3a456dbe88ec2557ed4bb4",
                "fileName": "25df9058ce6211a53d6e2a5729ac387e",
                "fileSize": 302791,
                "fileUrl": "f:\\filetemp\\2017\\02\\08\\15\\21\\03\\25df9058ce6211a53d6e2a5729ac387e\\619457c6a49942b790eb014552d8c6f3\\25df9058ce6211a53d6e2a5729ac387epak",
                "fileMd5": "25df9058ce6211a53d6e2a5729ac387e",
                "fileType": 0,
                "fileState": 0,
                "createTime": 1486538464000,
                "updateTime": 1486538464000
            },
            {
                "fileId": "5cb75cd2a52442b7b91ec9d02331638e",
                "fileName": "0b9b1ac1839f3a0423eae242313c1b12",
                "fileSize": 332024,
                "fileUrl": "f:\\filetemp\\2017\\02\\08\\15\\21\\02\\0b9b1ac1839f3a0423eae242313c1b12\\619457c6a49942b790eb014552d8c6f3\\0b9b1ac1839f3a0423eae242313c1b12pak",
                "fileMd5": "0b9b1ac1839f3a0423eae242313c1b12",
                "fileType": 0,
                "fileState": 0,
                "createTime": 1486538463000,
                "updateTime": 1486538463000
            },
            {
                "fileId": "60cee45406b845c59a61bd7a4d769362",
                "fileName": "765e495738a86edfbc94567c221f9b17",
                "fileSize": 336877,
                "fileUrl": "f:\\filetemp\\2017\\02\\08\\15\\21\\02\\765e495738a86edfbc94567c221f9b17\\619457c6a49942b790eb014552d8c6f3\\765e495738a86edfbc94567c221f9b17pak",
                "fileMd5": "765e495738a86edfbc94567c221f9b17",
                "fileType": 0,
                "fileState": 0,
                "createTime": 1486538463000,
                "updateTime": 1486538463000
            },
            {
                "fileId": "5431670aa9f640cead5a64e455e8eba7",
                "fileName": "e82780c1820432cbcc1cb98d9b118079",
                "fileSize": 312378,
                "fileUrl": "f:\\filetemp\\2017\\02\\08\\15\\18\\04\\e82780c1820432cbcc1cb98d9b118079\\619457c6a49942b790eb014552d8c6f3\\e82780c1820432cbcc1cb98d9b118079pak",
                "fileMd5": "e82780c1820432cbcc1cb98d9b118079",
                "fileType": 0,
                "fileState": 0,
                "createTime": 1486538285000,
                "updateTime": 1486538285000
            },
            {
                "fileId": "05c5cd68e4e344ef82edce303b02e5b1",
                "fileName": "f8425ada9eb82ef45fabf844f00dba80",
                "fileSize": 327281,
                "fileUrl": "f:\\filetemp\\2017\\02\\08\\15\\15\\07\\f8425ada9eb82ef45fabf844f00dba80\\619457c6a49942b790eb014552d8c6f3\\f8425ada9eb82ef45fabf844f00dba80pak",
                "fileMd5": "f8425ada9eb82ef45fabf844f00dba80",
                "fileType": 0,
                "fileState": 0,
                "createTime": 1486538108000,
                "updateTime": 1486538108000
            },
            {
                "fileId": "82f8cca08e184fb4ae7db6837782d00d",
                "fileName": "628dadb6150e3aafbc10639c24c3c94a",
                "fileSize": 272964,
                "fileUrl": "f:\\filetemp\\2017\\02\\08\\15\\10\\55\\628dadb6150e3aafbc10639c24c3c94a\\619457c6a49942b790eb014552d8c6f3\\628dadb6150e3aafbc10639c24c3c94apak",
                "fileMd5": "628dadb6150e3aafbc10639c24c3c94a",
                "fileType": 0,
                "fileState": 0,
                "createTime": 1486537856000,
                "updateTime": 1486537856000
            },
            {
                "fileId": "409576f132c54f228edaf84397b90886",
                "fileName": "be3da6aeb6a41e85f74252381a635d36",
                "fileSize": 271920,
                "fileUrl": "f:\\filetemp\\2017\\02\\08\\15\\09\\27\\be3da6aeb6a41e85f74252381a635d36\\619457c6a49942b790eb014552d8c6f3\\be3da6aeb6a41e85f74252381a635d36pak",
                "fileMd5": "be3da6aeb6a41e85f74252381a635d36",
                "fileType": 0,
                "fileState": 0,
                "createTime": 1486537768000,
                "updateTime": 1486537768000
            },
            {
                "fileId": "fdb008e0a4cc45e8afdb98bd530cd3d6",
                "fileName": "38f14a9837b5e8d0606fbdd9dc646863",
                "fileSize": 503455,
                "fileUrl": "f:\\filetemp\\2017\\02\\08\\15\\07\\52\\38f14a9837b5e8d0606fbdd9dc646863\\619457c6a49942b790eb014552d8c6f3\\38f14a9837b5e8d0606fbdd9dc646863pak",
                "fileMd5": "38f14a9837b5e8d0606fbdd9dc646863",
                "fileType": 0,
                "fileState": 0,
                "createTime": 1486537673000,
                "updateTime": 1486537673000
            },
            {
                "fileId": "291286b8dd5b4729a4b8b4985c39178a",
                "fileName": "7377bb1e984424fc14c2b8d6a79a9f14",
                "fileSize": 296589,
                "fileUrl": "f:\\filetemp\\2017\\01\\14\\15\\46\\43\\7377bb1e984424fc14c2b8d6a79a9f14\\619457c6a49942b790eb014552d8c6f3\\7377bb1e984424fc14c2b8d6a79a9f14pak",
                "fileMd5": "7377bb1e984424fc14c2b8d6a79a9f14",
                "fileType": 0,
                "fileState": 0,
                "createTime": 1484380004000,
                "updateTime": 1484380004000
            }
        ],
        "pageNum": 1,
        "pageSize": 10,
        "pages": 2,
        "size": 10
    }
}

24.修改文件信息

http://127.0.0.1:8080/fastpan/manageFile/updateFileInfor

入参

{
    "requestContext": {
         "fileId": "71bf58b57d914bb28d3c840b785d7eb8",
         "fileState": -1
    }
}

结果

{
    "code": 0,
    "message": "",
    "success": true,
    "result": "更新成功"
}

25.删除文件

http://127.0.0.1:8080/fastpan/manageFile/deleteFile

入参

{
    "requestContext": {
         "fileId": "71bf58b57d914bb28d3c840b785d7eb8"
    }
}