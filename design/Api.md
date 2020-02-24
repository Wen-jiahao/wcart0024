

​        **商品添加**  

URL: /api/product/add

Method：POST 

RequestBody:

```
{
    "product_name": "华为",
    "product_abstract" :"sfsdhbfshdbfhbsbf"
}
```

返回实例

```
{    "stateCode": 200,    "state": "添加成功"  } 
```

  **修改商品**

URL: /api/product/update？productid={productid}

Method： PUT 

Request Field  

| 字段      | 类型 | 描述     |
| --------- | ---- | -------- |
| productid | int  | 商品类型 |

返回实例

```
{    "stateCode": 200,    "state": "修改成功"  } 
```

 **后台管理端-客户列表页**

URL：/customer/search?page=${page}&size=${size}

Method ：GET

requestparam：

| 参数 | 类型 | 是否必须 | 说明   |
| ---- | ---- | -------- | ------ |
| page | int  | 是       | 默认0  |
| size | int  | 是       | 默认20 |

```
{
"list":[
    {
        "customerId":1234,
       "customer_name":"Alex Chan",
        "email": “cjf@bw.com”,
        "status": 1，
​		"dateAdded": "2020-01-24"，
​    },
​    {
        "customerId":12345,
       "customer_name":"Alex Chan",
        "email": “cjf@bw.com”,
        "status": 1，
​		"dateAdded": "2020-01-24"，
​    }
	"total":10
]
}

```

**用户注册**

URL：/customer/register

Method ：POST

requestparam：

| 字段               | 类型    | 说明         |
| ------------------ | ------- | ------------ |
| realName           | varchar | 真实姓名     |
| email              | varchar | 邮件         |
| mobile             | int     | 手机号       |
| encrypted_password | varchar | 加密密码     |
| newsSubscribed     | char    | 是否订阅新闻 |
|                    |         |              |

返回实例

{    "stateCode": 200,    "state": "注册成功（失败）"  } 



**用户登录****

URL：/customer/login

Method ：GET

requestparam：

| 字段               | 类型    | 说明     |
| ------------------ | ------- | -------- |
| email              | varchar | 邮件     |
| encrypted_password | varchar | 加密密码 |

返回实例

{    "stateCode": 200,    "state": "登录成功（失败）"  } 

**忘记密码**

URL：/customer/forget

Method：post

requestparam：

| 字段          | 类型   | 说明     |
| ------------- | ------ | -------- |
| emailVerified | String | 邮箱验证 |

**我的账户信息**

URL：/customer/get?customerId=${customerId}

Method ：GET

requestparam：



| 字段       | 类型 | 说明   |
| ---------- | ---- | ------ |
| customerId | int  | 用户id |

ResponseBody:

```
[
    {
        "realName":"张三",
        "email": “cjf@bw.com”,
        "mobile": 1234556,
    },
   {
        "realName":"张三",
        "email": “cjf@bw.com”,
        "mobile": 1234556,
    }
]
```

**修改密码**

URL: /api/password/update?password={password}?passwordConfrim={passwordConfrim}

Method： PUT 

Request Field  



| 字段            | 类型    | 说明     |
| --------------- | ------- | -------- |
| password        | varchar | 密码     |
| passwordConfrim | varchar | 确认密码 |

**用户地址列表页**

URL: /api/address/get?customerId={customerId}

Method： get

Request Field  

| 字段       | 类型 | 说明   |
| ---------- | ---- | ------ |
| customerId | int  | 用户id |
|            |      |        |

ResponseBody:

```
[
    {
        "addressId":123,
        "receiverName": “计算的”,
        "receiver_mobile": 1234556,
         "content":"陕西省第三方"
    },
  	{
        "addressId":123,
        "receiverName": “计算的”,
        "receiverMobile": 1234556,
         "content":"陕西省第三方"
    }
]
```

**新添地址**

URL: /api/address/add

Method： POST

Request Field  

| 字段            | 类型   | 说明     |
| --------------- | ------ | -------- |
| receiverName    | String | 地址     |
| receiver_mobile | int    | 电话     |
| content         | String | 地址内容 |

​        **地址修改**  

URL: /api/address/update？addressId={addressId}

Method： PUT

Request Field  

| 字段      | 类型 | 说明   |
| --------- | ---- | ------ |
| addressId | int  | 地址id |
|           |      |        |
|           |      |        |

[
    {
        "addressId":123,
        "receiverName": “计算的”,
        "receiver_mobile": 1234556,
         "content":"陕西省第三方"
    },
  	{
        "addressId":123,
        "receiverName": “计算的”,
        "receiverMobile": 1234556,
         "content":"陕西省第三方"
    }
]



## 2.1 查询面试列表（面试时间降序）



URL: /interview/search?keyword={keyword}&onlyme={onlyme}&time={time}  
Method：GET  

ResponseBody:  
```json
[
    {
        "interviewId":1234,
        "company": "华为",
        "studentId": 234,
        "studentName": "cjf",
        "time": "2019-12-12T00:00:00.000+0000",
        "timestamp": 1575448390345,
        "status": 1
    },
    {
        "interviewId":1234,
        "company": "华为",
        "studentId": 234,
        "studentName": "cjf",
        "time": "2019-12-12T00:00:00.000+0000",
        "timestamp": 1575448390345,
        "status": 1
    }
]

```

Request Field  

| 字段     |     类型 |   描述   |
| :--------------: | :--------:| :------: |
| keyword   | String   | 关键字，暂时支持只公司名    |
| onlyme   | Boolean   | 只看自己    |
| time   | Long   | 时间戳    |

Response Field  

| 字段     |     类型 |   描述   |
| :--------------: | :--------:| :------: |
| interviewId   | Integer   | 面试Id    |
| company   | String   | 公司名    |
| studentId   | String   | 学生Id    |
| studentName   | String   | 面试学生    |
| time   | Long   | 面试时间, 按照时间降序    |
| status   | byte   | 面试状态    |


## 2.2 创建面试

URL: /interview/create  
Request Content-Type: application/json(默认)  
Method：POST  

RequestBody:  
```json
{
    "company": "华为",
    "address": "上海徐家汇",
    "time": 1575448390345
}

```

ResponseBody:  
```json
123456

```

Request Field  

| 字段     |     类型 |   描述   |
| :--------------: | :--------:| :------: |
| company   | String   | 公司名    |
| address   | String   | 公司地址    |
| time   | Long   | 面试时间戳    |

Response Field  

| 字段     |     类型 |   描述   |
| :--------------: | :--------:| :------: |
|    | Integer   | 面试Id    |


## 3.1 笔试题上传

URL: /examphoto/upload  
Method：POST  
Request Content-Type: multipart/formdata  
RequestParam: interviewId
RequestParam: examphotos  

ResponseBody:  
```json
[
    "http://xxx.com/xxx1.jpg",
    "http://xxx.com/xxx2.jpg"
]

```

Request Field  

| 字段     |     类型 |   描述   |
| :--------------: | :--------:| :------: |
| interviewId   | Integer   | 面试Id    |
| examphotos   | String   | 上传文件key    |

Response Field  

| 字段     |     类型 |   描述   |
| :--------------: | :--------:| :------: |
|    | Array(String)   | 上传图片后Urls    |

## 0.1 图片上传

URL: /image/upload  
Method：POST  
Request Content-Type: multipart/formdata  
RequestParam: image

ResponseBody:  
```json
[
    "http://xxx.com/xxx1.jpg"
]

```

Request Field  

| 字段     |     类型 |   描述   |
| :--------------: | :--------:| :------: |
| image   | String   | 上传文件key    |

Response Field  

| 字段     |     类型 |   描述   |
| :--------------: | :--------:| :------: |
|    | String   | 上传图片后Url    |


## 0.2 管理员登录
URL: /administrator/login?username={username}&passwprd={password}  
Method：GET  


ResponseBody:  
```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c

```

Request Field  

| 字段     |     类型 |   描述   |
| :--------------: | :--------:| :------: |
| username   | String   | 用户名    |
| password   | String   | 密码    |

Response Field  

| 字段     |     类型 |   描述   |
| :--------------: | :--------:| :------: |
|    | String   | jwt token    |

## 0.3 管理员获取Profile

URL: /administrator/getProfile 
Method：GET  

RequestHeader：
jcartToken ： eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c

ResponseBody:  
```json
{
    "administratorId" :123,
    "username": "华为",
    "realName": "华为",
    "email": "1575448390345。com",
    "avatarUrl": "http://xxx.com/xxx1.jpg",
    "status" :1,
    "createTimestamp": 1233455
}

```

Request Header  

| 字段     |     类型 |   描述   |
| :--------------: | :--------:| :------: |
| jcartToken   | String   | jwt token    |


Response Field  

| 字段     |     类型 |   描述   |
| :--------------: | :--------:| :------: |
| administratorId   | Integer   | jwt token    |
| username   | String   | 用户名    |
| realName   | String   | 真实名    |
| email   | String   | 邮箱    |
| avatarUrl   | String   | 头像    |
| status   | Byte   | 状态    |
| createTimestamp   | Long   | 时间戳    |

## 0.4 管理员跟新Profile



## 0.5 管理员获取密码重置密码

## 0.6 管理员重置密码

## 0.7 管理员搜索列表

## 0.8 管理员回显

## 0.8 管理员创建

## 0.8 管理员跟新

## 1.0 商品列表
URL：/api/search?page=${page}&size=${size}

Method ：GET

Request Field:

| 参数 | 类型 | 是否必须 | 说明   |
| ---- | ---- | -------- | ------ |
| page | int  | 是       | 默认0  |
| size | int  | 是       | 默认20 |



ResponseBody:  

```json
{
"list":[
    {
        "productId":1234,
        "mainPicUrl": "/images/points/4444005.jpg",
        "productName": "Apple Cinema 30",
        "discount": 0.01,
​		"productCode": "product 01",
​        "price": 2.3,
​        "stockQuantity": 988,
​        "status": 1
​    },
​    {
        "productId":1234,
        "mainPicUrl": "/images/points/4444005.jpg",
        "productName": "Apple Cinema 30",
        "discount": 0.99,
​		"productCode": "product 01",
​        "price": 2.3,
​        "stockQuantity": 988,
​        "status": 1
​    }
	
],
"total" :10
}

```

Response Field  

| 字段     |     类型 |   描述   |
| :--------------: | :--------:| :------: |
| productId   | int   |   商品id  |
| mainPicUrl   | String   | 头像    |
| productName   | String   | 商品名    |
| discount   | float   | 打折    |
| productCode   | String   | 编号    |
| stockQuantity   | int   | 库存    |
| status   | Byte   |  状态   |
| total   | int   | 条数    |






