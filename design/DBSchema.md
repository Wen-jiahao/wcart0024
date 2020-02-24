# Administrator
| 字段  | 类型  | 约束  |  说明 |
|---|---|---|---|
| administrator_id  | int  | 主键 自增 | Id  |
| username | varchar(20)  | 非空，唯一索引 | 用户名  |
| real_name  | varchar(20)  | 索引 | 真实姓名  |
| email  | varchar(100)  | 唯一索引 | 邮箱  |
| encrypted_password  | varchar(100)  | 非空 | 加密密码  |
| status  | tinyint  | 非空，默认启用  | 状态（0禁用，1启用）  |
| create_time  | datetime  |  非空 | 创建时间  |
| avatar_url  | varchar(100)  |   | 头像地址 |

# Address
| 字段  | 类型  | 约束  |  说明 |
|---|---|---|---|
| address_id  | int  | 主键 自增 | Id  |
| customer_id | int  | 非空，索引，外键 | 客户Id  |
| receiver_name  | varchar(20)  | 非空 | 收货人  |
| receiver_mobile  | varchar(20)  | 非空 | 收货人手机  |
| content  | varchar(100)  | 非空 | 地址内容  |
| tag  | varchar(20)  | 非空 | 标签  |

# Product
| 字段  | 类型  | 约束  |  说明 |
|---|---|---|---|
| product_id  | int  | 主键 自增 | 商品Id  |
| product_name | varchar(20)  | 非空,唯一索引 | 商品名称  |
| product_code | varchar(20)  | 非空,唯一索引 | 商品代号  |
| discount | double  |  | 打折(0.01-0.99)  |
| price | decimal  | 非空 | 价格  |
| main_pic_url | varchar(100)  | 非空 | 主图  |
| status  | tinyint  | 非空，默认1上架  | 状态(0.下架,1,上架,2.待定|)  |
| stock_quantity | int  | 非空,默认0 | 库存量  |
| stock_order | int  | 非空 | 排序  |
| reword_points | int  |  | 积分  |
| product_abstract | varchar(300)  | 非空 | 摘要  |

 

# ProductDetail
| 字段  | 类型  | 约束  |  说明 |
|---|---|---|---|
| product_id  | int  | 外键 自增 | 商品Id  |
| description | text  | 非空  | 描述  |
| ohter_pic_urls | varchar(2000)  | 非空 | 幅图  |

# OrderHistory
| 字段  | 类型  | 约束  |  说明 |
|---|---|---|---|
| order_history_id  | bigint  | 主键 自增 | Id  |
| order_id | int  | 非空，索引，外键 | 订单Id  |
| time  | datetime  | 非空 | 历史订单时间  |
| comment  | varchar(300)  |  | 备注  |
| order_status  | tinyint | 非空 | 订单状态  |
| customer_notified  | bit | 非空 | 通知用户  |

# Order
| 字段  | 类型  | 约束  |  说明 |
|---|---|---|---|
| order_id  | bigint  | 主键 自增 | 订单Id  |
| customer_id | int  | 非空，索引，外键 | 客户Id  |
| status  | tinyint  | 非空，默认启用  | 订单状态（0待处理、1处理中、2待发货、3已发货、4待签收、5已签收、6待支付、7已支付、8取消、9拒绝、10完成、11待评价、12已评价）  |
| total_price  | decimal  | 非空 | 总金额  |
| reword_pints  | int  |  | 积分  |
| create_time  | datetime  | 非空 | 创建时间  |
| update_time  | datetime  | 非空 | 修改时间  |

# OrderDetail
| 字段  | 类型  | 约束  |  说明 |
|---|---|---|---|
| order_id  | bigint  | 外键 | 订单Id  |
| ship_method | tinyint  | 非空  | 邮寄方式  |
| ship_address | varchar(100)  | 非空  | 寄送地址  |
| ship_price | double  | 非空  | 寄送价格  |
| pay_method | smallint  | 非空  | 邮寄方式  |
| invoice_address | varchar(300)  | 非空 | 法票地址  |
| invoice_price | double  | 非空 | 法票价格  |
| comment | varchar(300)  |  | 备注  |
| order_products | text  |  | 内容(商品对象数组json)  |
| produce_id | int  | 非空 | 商品id  |
| product_code | varchar(20)  | 非空,唯一索引 | 商品代号  |
| product_name | varchar(20)  | 非空 | 商品名称  |
| quantity | int  | 非空 | 商品数量  |
| unit_price | double  | 非空 | 单价  |
| unit_reword_points | int  |  | 单个商品积分  |


# customer
| 字段  | 类型  | 约束  |  说明 |
|---|---|---|---|
| customer_id | int  | 非空，自增，主键 | 客户Id  |
| username  | varchar(30)  | 非空 | 客户名  |
| real_name  | varchar(20)  | 索引,非空 | 真实姓名  |
| avatar_url  | varchar(100)  |  | 头像  |
| encrypted_password  | varchar(20)  | 非空,唯一 | 加密密码  |
| mobile  | varchar(20)  | 非空，唯一索引 | 客户手机  |
| mobile_verified  | bit  | 默认false | 手机验证  |
| email  | varchar(20)  | 唯一索引 | 邮箱  |
| email_verified  | bit  | 默认false | 邮箱验证  |
| status  | tinyint  | 非空 | 	状态（0禁用、1启用、2不安全）  |
| create_time  | datetime  | 非空 | 创建时间  |
| default_address_id  | int  | 外键 | 默认地址id  |
| reword_points  | int  | 非空,默认0 | 积分  |
| news_subscribe  | bit  | 默认false | 是否新闻订阅  |

# return
| 字段  | 类型  | 约束  |  说明 |
|---|---|---|---|
| retrun_id  | int  | 主键 自增 | Id  |
| order_id  | bigint  | 非空,外键,索引 | 订单id  |
| customer_id  | int  | 非空,索引外键 | 客户id  |
| customer_name  | varchar(20)  | 非空 | 客户名称  |
| mobile  | varchar(20)  | 非空 | 客户手机  |
| status  | tinyint  | 非空 | 状态（0等待取货、1正在处理、完成）  |
| order_time  | datetime  | 非空 | 订单时间  |
| return_action  | tinyint  | 非空 | 处理方式（0退货、1换货、2修理）  |
| product_name | varchar(20)  | 非空，索引 | 商品名  |
| product_code  | varchar(20)  | 非空 | 商品编号  |
| quantity  | int  | 非空 | 总数量  |
| reason  | tinyint  | 非空 | 	退货原因（0发货过期、1订单错误、2收到错误商品、3质量问题）  |
| opened  | bit | 非空 | 是否开封  |
| comment  | varchar(300)  |  | 备注  |
| email  | varchar(100)  | 非空,唯一索引 | 邮箱  |
| create_time  | datetime  | 非空 | 创建时间  |
| update_time  | datetime  | 非空 | 修改时间  |




# returnHistory
| 字段  | 类型  | 约束  |  说明 |
|---|---|---|---|
| return_history_id  | int  | 主键 自增 | Id  |
| retrun_id  | tinyint  | 索引,非空,外键 | 退货Id  |
| time  | datetime  | 非空 | 退货时间  |
| return_status  | tinyint  | 非空 | 退货状态  |
| comment  | varchar(300)  |  | 备注  |
| customer_notify  | bit  | 非空 | 是否通知用户  |






