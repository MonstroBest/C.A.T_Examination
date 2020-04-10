[TOC]

#JDBC

# 一、什么是JDBC
#Java Database Connectivity 
#Java和数据库的连接技术，Java应用程序访问数据库的规范

#规范：抽象类或接口

# 二、JDBC的使用步骤

## #类的加载时期

类的加载时机分为这么几种：

1》new对象（编译器加载）

2》加载子类

3》调用类中的静态成员

4》通过反射（运行期加载）

## 1.【连接数据库】加载驱动

### //加载mysql驱动

```mysql
Class.forName("com.mysql.jdbc.Driver");
```

## 2.【连接数据库】获取连接

### //使用properties存储信息

**Properties类与getProperty方法**

text文件名jdbc.properties---->下面用A代替

> user=
> password=
> url=jdbc:mysql://localhost:3306/myemployees
> driver=com.mysql.jdbc.Driver



```java
Properties info = new Properties();
info.load(new FileInputStream("A"));

String user = info.getProperty("user");
String password = info.getProperty("password");
String url = info.getProperty("url");
String driver = info.getProperty("driver");
```

### //获取连接

```java
Connection connection = (Connection) DriverManager.getConnection(url,user,password);
```



## 3.【案例】登录验证与预编译PreparedStatement

### //编写sql语句

```java
String sql = "select count(*) from admin where username=? and password=?";
```

？：占位符

### //获取PreparedStatement命令对象

```
PreparedStatement statement = (PreparedStatement) connection.prepareStatement;
```

### //设置占位符的值

```java
statement.setString(1,username);
statement.setString(2,pwd);
```

### //执行sql命令

**增删改：**

```java
int update = statement.executeUpdate();
```

**#执行增删改，返回受影响的行数**

**查：**

```java
ResultSet set = statement.executeQuery();
//光标
```

**#执行查询，返回结果集**

**返回提醒：**

```java
if(set.next()){//返回值:true/false
	//set.next()：将光标移向下一行
    //此时光标移到了第一行
​	int count = set.getInt(1);
	//索引1（第一列）（光标指向的那一行）的值
​	System.out.println(count>0?"登录成功":"登陆失败");

}
```

### //关闭

```java
set.close();//关闭结果集对象
statement.close();//关闭预编译对象
connection.close();//关闭连接对象
```

# 三、封装JDBCUtils类

**用以获取连接和关闭连接：**

# 四、事务

# 五、批处理

- addBatch()：添加需要批量处理的SQL语句或参数
- executeBatch()：执行批量处理语句
- clearBatch()：清空批处理包的语句

两种批量执行SQL语句的情况：

- 多条SQL语句的批量处理

- 一个SQL语句的批量传参





















