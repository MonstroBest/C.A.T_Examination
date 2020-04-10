[TOC]



### 2020-4-4进度

P286 - p320

p286 12：42

<img src="C:\Users\wj\AppData\Roaming\Typora\typora-user-images\1585975446885.png" alt="1585975446885" style="zoom: 50%;" />

p288 13：44

p289 13：51

p290 14：03

p291 14：44

p292 15：05

p293 15：26

p294 16：28

p295 16：39

p296 16：45

p297 17：10

p298 18：47

p299 18：56

p300 19：08

p301 19：15

p303 19：22

p304 19：51

p305 10：55

p306 11：04

p307 13：31

p308 13：45

p310 16：07

p311 16：26

p312 17：27

p313 18：48

![1586083770275](C:\Users\wj\AppData\Roaming\Typora\typora-user-images\1586083770275.png)

p318 17：58

p319 18：50

# 一、MySQL使用

## 1.MySQL安装与配置

花了半个早上+中午的时间把安装配置（应该？）搞定了，中间很多波折心态也一度小崩，果然每学一项新技能，配置都是最难的

这里就不记录安装历程了

然后上bilibili大学找教程视频看

## 2.启动/停止MySQL服务

- 方式一：

任务管理器 — 服务 — MySQL57-右键

- 方式二：

cmd（管理员身份运行）— net start mysql57 — net stop mysql57

## 3.客户端登陆

打开MySQL Server Command Line Client - buaichixueli19

或打开cmd — mysql -uroot -p — buaichixueli19

## 4.常见sql命令

sql命令要以;结尾

以及

大小写竟然是不区分的（默认，可换字符集更改）

库名、表名、字段名建议大写

支持换行写命令

mysql>

show databases; : 有哪些数据库

use mysql;使用mysql库show tables;显示表

show tables from sys；显示sys库里的表（但是还在mysql库里）

select database();查看当前数据库

create table stuinfo(创建表

stuid int，

stuname varchar(20),可变字符

gender char,单个字符

borndate datetime);

desc stuinfo;查看表的结构

select * from stuinfo;获取stuinfo库里的所有列

set names utf8;设置字符集gbk以使用中文

insert into stuinfo values(1,'sb','男','2020-4-1')；在stuinfo表内插入一行信息

update stuinfo set borndate=‘1900-01-01 01：01：59’ where stuid = 2；修改stuinfo表中的borndate为x在stuid=2那一行

delete from stuinfo where gender = '男';删掉男性那一行

alter table stuinfo add column email varchar(20);修改表的结构,加一列20可变字符的email

drop table stuinfo；干掉表（？）表没了

exit；退出客户端，或者ctrl+c

#sjalsd单行注释

-- sjalsd单行注释

/*sdjflaj

sdjls*/多行注释

# 二、SQL语句分类

- DDL（Data Definition Language）:数据定义语言，用来定义数据库记录对象：库、表、列等（数据）
- *DML（Data Manipulation Language）：数据操作语言，用来定义数据库记录（数据）,增删查改
- DCL（Data Control Language）:数据控制语言，用来定义访问权限和安全级别

例如创建新用户，少用

- *DQL（Data Query Language）：数据查询语言，用来查询记录（数据）

例如select

# 三、图形化界面客户端

用了SQLyog

分类存储设计思想

关系型数据结构

# 四、DQL介绍

## 1.基础查询语句select

### 语法：
```mysql
-- select 查询列表 from 类名；
```

### 特点：
1、查询的结果集是虚拟表
2、select 查询列表 类似于System.out.println()即打印

select后面跟的查询列表可以有多个，用逗号隔开
例如：select 字段1，字段2，表达式 from 表；

3、执行顺序

```mysql
SELECT first_name FROM employees;
```

①FROM字句
②SELECT字句

4、查询列表可以是：字段、表达式、常量、函数等

### 1.1查询常量
```mysql
SELECT 100;
```



### 1.2查询表达式
```mysql
SELECT 100%3;
```



### 1.3查询单个字段
```sql
SELECT last_name FROM employees;
```



### 1.4查询多个字段
#### 快捷键F12可以快速换行对齐
```sql
SELECT
  first_name,
  salary,
  hiredate
FROM
  employees;
```



### 1.5查询所有字段
```sql
SELECT * FROM employees;
```

### 1.6查询函数（调用函数，获取返回值）
```sql
SELECT DATABASE();
SELECT VERSION();
SELECT USER();
```

### 1.7起别名AS
#方式一：使用as关键字

```sql
SELECT USER() AS 用户名；
SELECT USER() AS '用户名'；
SELECT USER() AS "用户名"；

SELECT last_name AS "姓 名" FROM employees;
```

#方式二：使用空格

```sql
SELECT USER() 用户名；
SELECT USER() '用户名'；
SELECT USER() "用户名"；

SELECT last_name "姓 名" FROM employees;
```

### 1.8拼接查询关键字CONCAT
-- 需求：查询 first_name 和 last_name 拼接成的全名，最终起别名为：姓 名

#方案一：使用＋（×）
SELECT first_name+last_name AS "姓 名"
FROM employees;

#方案二：使用concat拼接函数

```sql
SELECT CONCAT(first_name,last_name) AS "姓 名"
FROM employees;
```

#### 1.8.1 IFNULL

-- 注意，拼接时有一个为null，所得也为null,应使用IFNULL(不空,空):

```sql
SELECT CONCAT(first_name,','last_name,','IFNULL(commission_pct,'')) FROM employees;
```

### 1.9 + 在Java和MySQL中的区别

**JAVA中+的作用：**

1、加法运算
	200+1.5		'a'+3
2、拼接符
	至少有一个操作数为字符串
	"hello"+100
	

**MySQL中+的作用：**

1、加号运算

①两个操作数都是数值型
100+1.5

**②其中一个操作数为字符型**
**将字符型数据强制转换成数值型，**
**如果无法转换，直接当作0处理**
**'sb'+100   ==》  100**

**③其中一个操作数为null**
**结果也为null**




### 1.10去重关键字DISTINCT

#需求：查询员工涉及到的部门编号有哪些

```mysql
SELECT DISTINCT department_id FROM employees;
```



### 1.11查看表的结构DESC

```
DESC employees;
SHOW COLUMNS FROM employees;
```



## 2.条件查询语句where

### 语法：

select 查询列表
from 表名
where 筛选条件;

**执行顺序：**
**①from字句**
**②where字句**
**③select字句**

```
select last_name,first_name from employees where salary>20000;
```



### 特点：

#### 1、按关系表达式筛选

关系运算符：> < >= <= = <>(!=)

#### 2、按逻辑表达式筛选

逻辑运算符：and(&&) or(||) not(!) 
-- AND:第一个和第二个条件都成立
-- OR:第一个或第二个条件成立 

#### 3、模糊查询

like、in、between and、is null



### 2.1按关系表达式筛选

#案例1：查询部门编号不是100的员工信息

```
SELECT * FROM employees WHERE department_id <> 100;
```

#案例2：查询工资小于15000的姓名、工资

```
 SELECT
  last_name,
  first_name,
  salary
FROM
  employees
WHERE salary < 15000;
```



### 2.2按逻辑表达式筛选：

#案例1：查询部门编号不是50-100之间员工姓名、部门编号、邮箱

```
SELECT last_name,department_id,email
FROM employees
WHERE NOT(department_id <= 50 AND department_id >= 100);
```

#案例2：查询奖金率>0.03或者员工编号在60-110之间的员工信息

```
SELECT *
FROM employees
WHERE commission_pct>0.03 OR (employee_id >=60 AND employee_id <=110);
```



### 2.3模糊查询like/in

#### #1、like/not like

/*
一般和通配符搭配使用：对字符型数据进行部分匹配查询
常见的通配符：
_ 任意单个字符
% 任意n个字符

*/

##### ESCAPE '$': 设置为转义字符

#案例1：查询姓名中包含字符a的员工信息

```
SELECT *
FROM employees
WHERE last_name LIKE '%a%';
```

#案例2：查询姓名中包含第二个字符为_的员工信息

```mysql
SELECT *
FROM employees
WHERE last_name LIKE '__$_%' ESCAPE '$';
```



#### #2、in/not in

/*
功能：查询某字段的值是否属于指定的列表之内

a in(常量值1,常量值2,...)
a not in(常量值1,常量值2,...)

*/

#案例1：查询部门编号是30/50/90的员工名、部门编号

```
SELECT last_name,department_id
FROM employees
WHERE department_id IN(30,50,90);
```

#案例2：查询工种编号不是SH_CLERK或IT_PROG的员工信息

```
SELECT *
FROM employees
WHERE job_id NOT IN('SH_CLERK','IT_PROG');
```



### 2.4范围查询between and

/*
判断某个字段的值是否介于xx之间(>=a and <=b)

between A and B
*/

#案例1：查询部门编号是30-90之间的部门编号、员工姓名

```
SELECT department_id
FROM employees
WHERE department_id BETWEEN 30 AND 90;
```

#案例2：查询年薪不是100000-200000之间的员工姓名、工资、年薪

```
SELECT first_name,last_name,salary,salary*12*(1+IFNULL(commission_pct,0)) 年薪
FROM employees
WHERE salary*12*(1+IFNULL(commission_pct,0)) NOT BETWEEN 100000 AND 200000;
```



### 2.5空查询is null

#案例1：查询没有奖金的员工信息

```
SELECT *
FROM employees			-- =只能判断普通数值，不能判断NULL
WHERE commission_pct IS NULL;	-- 不能这样写：xxx = NULL
```

​				-- 都能判断的安全等于：<=>
​				

### 2.6排序查询ORDER BY
/*
语法：

select 查询列表
from 表名
【where 筛选条件】
order by 排序列表

执行顺序：

①from子句
②where子句
③select子句
④order by子句

#### #1升序ASC降序DESC

特点：

1、排序列表可以是单个字段、多个字段、表达式、函数、列数、以及以上的组合
2、升序，通过 asc	（默认）
3、降序，通过desc

*/

#一、按单个字段排序

#案例1：将员工编号大于120的员工按工资的降序进行排序

```
SELECT * 
FROM employees 
WHERE employee_id>120
ORDER BY salary DESC;
```

#二、按表达式排序

#案例1：对有奖金的员工，按年薪排序

```
SELECT *,salary*12*(1+commission_pct) 年薪
FROM employees
WHERE commission_pct IS NOT NULL
ORDER BY 年薪 ASC;
```

#四、按函数的结果排序

#### #2按LENGTH()方法进行排序

#案例1：按名字的字数长度进行升序

```
SELECT last_name
FROM employees
ORDER BY LENGTH(last_name); --LENGTH()方法
```

#五、按多个字段进行排序

#案例1：查询员工的姓名、工资、部门编号，先按工资排序，再按部门编号排序

```
SELECT last_name,salary,department_id
FROM employees
ORDER BY salary ASC,department_id DESC;
```

#六、按列数排序

```
SELECT * FROM employees
ORDER BY 2;	-- 按第二列进行排序
```



## 3.常见函数

/*
函数：类似Java中的方法

1、自定义方法
2、调用方法

常见函数：

    单行函数：
    
    字符函数
    	CONCAT
    	SUBSTR
    	LENGTH
    	CHAR_LENGTH
    	UPPER/LOWER
    	TRIM
    	LEFT/RIGHT
    	LPAD/RPAD
    	INSTR
    	STRCMP
    数学函数
    	ABS
    	CEIL
    	FLOOR
    	ROUND
    	TRUNCATE
    	MOD
    日期函数
    	NOW
    	CURTIME
    	CURDATE
    	DATEDIFF
    	DATE_FORMAT
    	STR_TO_DATE
    流程控制函数
    	IF
    	CASE
    
    聚合函数：	
*/

### 3.1字符函数

#### 3.1.1 CONCAT 拼接字符

```
SELECT CONCAT('hello,',first_name,last_name) FROM employees;
```



#### 3.1.2 LENGTH 获取字节长度

```
SELECT LENGTH('hello,sb');
```



#### 3.1.3 CHAR_LENGTH 获取字符长度

```
SELECT CHAR_LENGTH('hello,sb');
```



#### 3.1.4 SUBSTR 截取子串
```
SELECT SUBSTR('love u',1,4);	
```


--substr(str,起始索引，结束索引)：从字符串索引1位置开始，到4结束截取
--substr(str,起始索引)

#### 3.1.5 INSTR 获取字符第一次出现的索引

```
SELECT INSTR('三打白骨精aaa白骨精bb白骨精','白骨精');
```



#### 3.1.6 TRIM 去前后指定的字符，默认是去掉空格

```
SELECT TRIM(' 虚 竹     ') AS 去掉前后空格;
SELECT TRIM('x' FROM 'xxxxx虚xxxxx竹xxxxxxxxx') AS 去掉前后字符;
```



#### 3.1.7 LPAD/RPAD 左填充/右填充

```
SELECT LPAD('木婉清',10,'a');
```



#### 3.1.8 UPPER/LOWER 变大写/变小写

#案例：查询员工表的姓名，要求格式：姓首字符大写，其他字符小写，
#名所有字符大写，且姓和名之间用_分割，最后起别名output。

```
SELECT UPPER(SUBSTR(first_name,1,1));
SELECT UPPER(SUBSTR(last_name));

SELECT CONCAT(UPPER(SUBSTR(first_name,1,1)),LOWER(SUBSTR(last_name,2)),'_',UPPER(last_name)) 'OUTPUT'
FROM employees;
```



#### 3.1.9 STRCMP (按字典顺序)比较两个字符大小

```
SELECT STRCMP('abc','aca');
```



#### 3.1.10 LEFT/RIGHT 截取子串

```
SELECT LEFT('鸠摩智',1);
SELECT RIGHT('鸠摩智',1);
```



### 3.2 数学函数

#### 3.2.1 ABS 绝对值
```
SELECT ABS(-2.4);
```



#### 3.2.2 CEIL 向上取整
```
SELECT CEIL(1.09);
```



#### 3.2.3 FLOOR 向下取整
```
SELECT FLOOR(-1.09);
```



#### 3.2.4 ROUND 四舍五入保留n位小数
```
SELECT ROUND(1.098);
SELECT ROUND(1.098,3);
```



#### 3.2.5 TRUNCATE 截断

```
SELECT TRUNCATE(1.8712355,2);
```



#### 3.2.6 MOD 取余
```
SELECT MOD(-10,3); -- 被除数的正负决定结果的正负
```



### 3.3 日期函数

#### 3.3.1 NOW 当前日期时间
```
SELECT NOW();
```



#### 3.3.2 CURDATE 当前日期
```
SELECT CURDATE();
```



#### 3.3.3 CURTIME 当前时间
```
SELECT CURTIME();
```



#### 3.3.4 DETEDIFF 两个日期之差
```
SELECT DATEDIFF('2001-7-20','2020-4-2');
```



#### 3.3.5 DATE_FORMAT 

将DATETIME类型的值转按指定格式转换成时间字符串

```
SELECT DATE_FORMAT('2001-7-20','%Y年%m月%d日 %H小时%i分钟%s秒') 出生日期;
```



#### 3.3.6 STR_TO_DATE 

将时间字符串按照指定格式转换成DATETIME类型的值

```
SELECT STR_TO_DATE('3/15 1998','%m/%d %Y');
```



### 3.4 流程控制函数

#### 3.4.1 IF函数

```
SELECT IF(100>9,'1','0');
```

#案例：如果有奖金，显示最终奖金；如果没有，则显示0

```
SELECT IF(commission_pct IS NULL,0,salary * 12 * commission_pct)
FROM employees;
```



#### 3.4.2 CASE函数

#情况1：类似switch，进行等值判断

```
SELECT 列
CASE 表达式
WHEN 值1 THAN 结果1
WHEN 值2 THAN 结果2
...
ELSE 结果n
END 起别名
FROM 表；
```

#情况2：类似于多重IF语句，实现区间判断

```
SELECT 列
CASE
WHEN 条件1 THAN 结果1
WHEN 条件2 THAN 结果2
...
ELSE 结果n
END
FROM 表;
```

#案例：如果工资>20000，显示A；工资>10000,显示C；否则显示D

#案例：如果工资>20000，显示A；工资>10000,显示C；否则显示D

### 3.5 分组函数
/*

说明：用于实现将一组数据进行统计计算，最终得到一个值

#### 3.5.1 常用分组函数：

sum(字段名)：求和
avg(字段名)：求平均数
max(字段名)：求最大值
min(字段名)：求最小值
count(字段名)：计算非空字段值的个数

*/

#案例1：查询员工信息表中，所有员工的工资和、工资平均值、
#最高工资、最低工资、领工资的员工个数 

```
SELECT SUM(salary),AVG(salary),MAX(salary),MIN(salary),COUNT(salary)
FROM employees;
```

#案例二：添加筛选条件
#查询工资大于两千五的人数，查询有领导的人数

```
SELECT COUNT(salary) FROM employees WHERE salary>2500;
SELECT COUNT(manager_id) FROM employees;
```



#### 3.5.2 COUNT的补充介绍*

##### #1、统计结果集的行数

```
SELECT COUNT(*) FROM employees; -- 统计不为空的行数
SELECT COUNT(*) FROM employees WHERE department_id = 30;

SELECT COUNT(1) FROM employees; -- 统计不为空的行数
```

##### #2、搭配DISTINCT实现去重的统计

#需求：查询有员工的部门个数

```
SELECT COUNT(DISTINCT department_id) FROM employees;
```

#思考：每个部门的总工资、平均工资
#回答：用到分组查询

#GROUP BY 分组查询

```
SELECT SUM(salary),department_id
FROM employees
GROUP BY department_id;
```



## 4.分组查询group by
/*

select 查询列表
from 表名
where 筛选条件
group by 分组列表

特点：
①查询列表往往是分组函数和被分组的字段
②
*/

### 4.1 简单的分组
#案例1：查询每个工种的员工的平均工资

```
SELECT AVG(salary),job_id
FROM employees
GROUP BY job_id;
```

#案例2：查询每个领导的手下人数

```
SELECT COUNT(*),manager_id
FROM employees
WHERE manager_id IS NOT NULL
GROUP BY manager_id;
```



### 4.2 实现分组前的筛选



#案例1：查询邮箱中包含a字符的每个部门的最高工资

```
SELECT department_id,MAX(salary) 最高工资
FROM employees
WHERE email LIKE '%a%'
GROUP BY department_id;
```

#案例2：查询每个领导手下有奖金的员工的平均工资

```sql
SELECT manager_id,AVG(salary) AS 平均工资
FROM employees
WHERE commission_pct IS NOT NULL
GROUP BY manager_id;
```

### 4.3 实现分组后的筛选

#案例1：查询哪个部门的员工个数大于五

```
SELECT COUNT(*) 员工个数,department_id
FROM employees
GROUP BY department_id
HAVING COUNT(*)>5;
```

**-- 执行顺序：from-where-select-group by-having**

#案例2：每个工种 有奖金的员工 的最高工资>12000 的 工种编号和最高工资

```
SELECT job_id,MAX(salary)
FROM employees
WHERE commission_pct IS NOT NULL
GROUP BY job_id
HAVING MAX(salary)>12000;
```


#案例3：领导编号>102的领导手下大于5000的最低工资

```
SELECT MIN(salary) 最低工资,manager_id 
-- 按照id分组，那id要先select出来
FROM employees
WHERE manager_id>102
GROUP BY manager_id
HAVING MIN(salary)>5000;
```

#4、实现分组后筛选+排序
#案例：每个工种有奖金的员工的最高工资>6000的工种编号
#和最高工资，按最高工资升序排列

```
SELECT job_id,MAX(salary)
FROM employees
WHERE commission_pct IS NOT NULL
GROUP BY commission_pct
HAVING MAX(salary)>6000
ORDER BY MAX(salary) ASC;
```

#按多个字段分组

#案例：查询每个工种每个部门的最低工资，并按最低工资排序
-- 工种部门均相同的为一组，即工种相同部门不同的不在同一组

```
SELECT job_id,department_id,MIN(salary)
FROM employees
WHERE 
GROUP BY job_id,department_id -- 无先后顺序
ORDER BY MIN(salary)DESC;
```

## 5.连接查询

/*
含义：又称多表查询

笛卡尔乘积现象：表1 有m行，表2有n行，结果=m*n行

发生原因：没有有效的连接条件
如何避免：添加有效的连接条件

分类：
	按年代分类：
	SQL92标准：仅仅支持内连接
	SQL99标准【推荐】：支持内联外联交叉连接（除了全外连接）
	

	按功能分类：
		内连接
			等值连接
			非等值连接
			自连接
		外连接
			左外连接
			右外连接
			全外连接
		交叉连接
*/

```
SELECT * `name`,boyName 
FROM beauty,boys
WHERE beauty.boyfriend_id = boys.id;
```

### 5.1 内连接

/**

语法：
	select 查询列表
	from 表名1 别名1，表名2 别名2，....
	where 等值连接的连接条件
**特点：**
	**1.为了解决多表中的字段名重名问题，往往为表起别名**
	**2.表的顺序无要求**

**①多表等值连接的结果为多表的交集部分**
**②n表连接，至少需要n-1各连接条件**
**③多表的顺序没有要求**
**④一般需要为表起别名**
**⑤可以搭配前面介绍的所有子句使用，比如排序、分组、筛选**

*/

#### #等值连接

#①简单的两表连接

USE myemployees;
#案例：查询员工名和部门名

```
SELECT last_name,department_name
FROM employees e,departments d
WHERE e.department_id = d.department_name;
```

#②添加筛选条件

#案例1：查询部门编号>100的部门名和所在的城市名

```
SELECT department_name,city
FROM departments d,locations l
WHERE d.`location_id` = l.`location_id`
AND d.`department_id`>100;
```

#③添加分组+筛选

#案例：查询每个城市的部门个数

```
SELECT COUNT(*) 部门个数,l.`city`
FROM departments d,locations l
WHERE d.`location_id` = l.`location_id`
GROUP BY l.city;
```

#实现三表连接

#案例：查询员工名、部门名和所在的城市

```
SELECT last_name,department_name,city
FROM employees e,departments d ,locations l
WHERE e.`department_id` = d.`department_id`
AND d.`location_id` = l.`location_id`;
```

#### #非等值连接

#案例1：查询员工的工资和工资级别

```
SELECT salary,grade_level
FROM employees e,job_grades g
WHERE e.salary BETWEEN g.`lowest_sal` AND g.`highest_sal`;
```

#### #自连接

**把一张表看成两张表查询**

#案例：查询员工名和领导的名称

```
SELECT m.employee_id,e.last_name,e.manager_id,m.`last_name`
FROM employees e,employees m
WHERE manager_id = employee_id;
```

### 5.2 外连接

/*

**说明：查询结果为主表中所有的记录，如果从表有匹配项，则**
**显示匹配项；如果从表中没有匹配项，则显示null**

**应用场景：一般用于查询主表中有但从表中没有的记录**

**特点：**
**1、外连接分主从表，两表的顺序不能任意调换**
2、左连接的话，左边为主表
   右连接的话，右边为主表


语法：

```mysql
select 查询列表
from 表1 别名
left/right/full 【outer】 join 表2 别名 -- 左边主表/右边主表
on 连接条件
where 筛选条件;
```

*/

USE girls;
#案例1：查询所有女生记录，以及对应的男生，没有则显示null

```
#左连接
SELECT b.*,bo.*
FROM beauty b
LEFT OUTER JOIN boys bo
ON b.boyfriend_id = bo.id;
```

-- 筛选从表没有匹配列的情况
#案例2：哪个女生没有男生

#右连接

```
SELECT bo.*,b.*
FROM boys bo
RIGHT JOIN beauty b 
ON b.boyfriend_id = bo.id
WHERE bo.id IS NULL;
```

#案例3：查询那个部门没有员工，并显示其部门编号和部门名

```
SELECT d.`department_id`,d.`department_name`
FROM departments d
LEFT JOIN employees e
ON d.`department_id` = e.`department_id`
WHERE e.`department_id` = NULL; -- e.employee_id=null
```

#### #除非包含在子查询中，否则where条件语句里不得出现聚合函数（分组函数）

#### #外连接查询例题

#案例1：查询编号>3的女神的男朋友信息，如果有则列出
#详细，如果没有，用null填充

```
SELECT b.id,bo.*
FROM boys bo
RIGHT JOIN beauty b
ON bo.id = b.boyfriend_id
WHERE b.id>3;
```

#案例2：查询那个城市没有部门

```
SELECT l.`city`
FROM locations l
LEFT JOIN departments d
ON l.`location_id` = d.`location_id`
WHERE d.`department_id` = NULL;
```

#案例3：查询部门名为SAL或IT 的员工信息

```
SELECT d.`department_name`,e.*
FROM departments d
LEFT JOIN employees e
ON d.`department_id` = e.`department_id`
WHERE d.`department_name` = 'SAL' 
OR d.`department_name` = 'IT';
```



## 6.子查询

/*
说明：当一个查询语句中又嵌套了另一个完整的select语句，
则被嵌套的select语句称为子查询或内查询
外面的select查询称为外查询

分类：

按子查询出现的位置进行分类：

1、select后面
	要求：子查询的结果为单行单列（标量子查询）
2、from后面
	要求：子查询的结果可以为多行多列
3、where或having后面♥
	要求：子查询的结果必须为单列
		单行子查询
		多行子查询
4、exists后面
	要求：子查询结果必须为单列（相关子查询）

**特点**：**
	**1、子查询放在条件中**
	**2、子查询一般放在小括号中**
	**3、子查询的执行优于主查询**
	**4、单行子查询对应了 单行操作符：> < >= <>**
	   多行子查询对应了 多行操作符：any in some all
	  

### 6.1 单行子查询 

*/

#案例1：查询和Zlotkey相同部门的员工姓名和工资

```
SELECT e.`last_name`,e.`salary`
FROM employees e
WHERE e.`department_id` = (
	SELECT e.`department_id`
	FROM employees
	WHERE last_name	= 'Zlotkey'
);
```

#案例2：查询工资比公司平均工资高的员工的员工号，
#姓名和工资

```
SELECT employee_id,e.`last_name`,e.`salary`
FROM employees e
WHERE e.`salary` >(
	SELECT AVG(salary)
	FROM employees
);
```

#案例3：查询各部门的最低工资，筛选看哪个部门的
#最低工资>部门编号为50的部门的员工的最低工资

```
SELECT MIN(e.`salary`),e.`department_id`
FROM employees e
GROUP BY e.`department_id`
HAVING MIN(e.`salary`) >(
	SELECT MIN(salary)
	FROM employees m
	WHERE m.`department_id` = 50
);
```

### 6.2 多行子查询

/*

in♥:查询某个字段是否在指定列表内 
	x ???? in(select,select,select)
any/some:判断某字段的值是否满足其中任意一个
	x>any(select,select,select)
     == x>min()	
	x=any()
     == x=in()	
all:判断某字段的值是否满足里面所有的
	x>all(select,select,select)


*/

#案例1：返回location_id是1400或1700的部门的所有员工姓名

```
SELECT e.`last_name`
FROM employees e
WHERE d.`department_id` IN(
	SELECT DISTINCT d.`department_id`
	FROM departments d
	WHERE d.`location_id` IN(1400,1700)
);
```

#案例2：返回其他部门中比job_id为IT_prog的部门的
#任一工资低的员工的员工号、姓名、job_id、salary

```sql
SELECT e.employee_id,e.`last_name`,e.`job_id`,e.`salary`
FROM employees e
WHERE e.`salary` < ANY(
	SELECT DISTINCT salary
	FROM employees e2
	WHERE e2.`job_id` = 'IT_prog'
);
```



#二、放在select后面

#案例：查询部门编号是50的员工个数

```
SELECT (
	SELECT COUNT(*)
	FROM employees
	WHERE department_id = 50
) 员工个数;
```

#三、放在from后面（表子查询）

#案例：查询每个部门的平均工资的工资级别

```
SELECT dep_ag.department_id,dep_ag.ag,g.grade
FROM sal_grade g
JOIN (
	SELECT AVG(salary) ag
	FROM employees
	GROUP BY departemnt_id;
) dep_ag 
ON dep_ag.ag BETWEEN g.min_salary AND g.max_salary;
```

### 6.3 EXISTS

#四、放在exists后面

#案例1：查询有无名字叫”大傻逼“的员工信息

```
SELECT EXISTS(
	SELECT *
	FROM employees
	WHERE last_name = '大傻逼'

) 有无大傻逼 ;
```

## 7.分页查询limit

/*
应用场景：

当页面上的数据，一页显示不全，则需要分页显示
逻辑：
分页查询的sql命令请求数据库服务器-->服务器响应查询
到的多条数据-->前台


语法：

select 查询列表
from 表1 别名
join 表2 别名
on 连接条件
where 筛选条件
group by 分组
having 分组后筛选
order by 排序列表

limit 起始条目，显示的条目数;



**执行顺序：**

**1.from子句**
**2.join子句**
**3.on子句**
**4.where子句**
**5.group by子句**
**6.having子句**
**7.select子句**
**8.order by子句**
**9.limit子句**



**#特点：**
**①起始条目索引，如果不写，则默认从0位置开始**
**②limit后面支持两个参数**
**参数一：显示的起始条目索引**
**参数二：显示的条目数**


公式：

假如要显示的页数是page，每页显示的条目数为size

```mysql
select * 
from employees
limit (page-1)*size,size;
```

*/

#案例1：查询员工信息表的前五条

```mysql
SELECT e.*
FROM employees e
LIMIT 0,5;
```

## 8.联合查询

/*
**说明：当**查询结果来自于多张表，但多张表之间没有关联，**
      这个时候往往用联合查询**

语法：
select 查询列表
from 表1
where 筛选条件
union
select 查询列表
from 表2
where 筛选条件

**特点：**

**1、多条待联合的查询语句列数必须一致，查询类型、字段意义**
**最好一致**

**2、**

### #union实现去重查询

   **union all实现全部查询，包含重复性**

*/

#案例：查询所有国家的年龄大于20岁的用户信息

SELECT * FROM chinese WHERE age>20
UNION
SELECT * FROM usa WHERE uage>20;


#案例3：防止UNION自动去重：union all 支持重复

```
SELECT 1,'范冰冰' 
UNION
SELECT 1,'范冰冰' 
UNION ALL
SELECT 1,'范冰冰' ;
```

# 五、DDL语句

/*
**说明：Data Define Language数据定义语言，用于对数据库和表的管理和操作**

*/

## 1.库的管理


#------------------------库的管理---------------------#

#一、如何创建数据库
CREATE DATABASE stuDB;
CREATE DATABASE IF NOT EXISTS studb; -- 建议

#二、如何删除数据库
DROP DATABASE stuDB;
DROP DATABASE IF EXISTS stuDB;

## 2.表的管理

#------------------------表的管理---------------------#

### #创建表♥

#### ##语法：

```
CREATE TABLE 表名(
	字段名 字段类型 【字段约束】,
	字段名 字段类型 【字段约束】,
	字段名 字段类型 【字段约束】,
		......
	字段名 字段类型 【字段约束】
);
```

案例：

```
CREATE TABLE IF NOT EXISTS stuinfo(
	stuid INT,
	stuname VARCHAR(20),
	stuname CHAR,
	email VARCHAR(20),
	borndate DATETIME
);
```

DESC stuinfo; -- 查看表的结构

/*

#### ##列常用类型：

​	int:整型，int(10)，显示宽度为10
​	double/float：浮点型,double(5,2)表示最多五位，
​		      必须有两位小数
​	decimal：浮点型，在表示钱方面使用该类型，
​	         因为不会出现精度缺失问题
​	char：固定长度字符串类型，char(4)范围是0-255
​	      效率高，不写n默认为1，开辟空间固定
​	varchar：可变长度字符串类型，varchar(n)
​		 根据实际存储决定开辟的空间，效率低
​	text：字符串类型，表示存储较长文本
​	blob：二进制型 字节类型 //存储jpg mp3 avi
​	date：日期类型，格式为：yyyy-MM-dd
​	time：时间类型，格式为：hh:mm:ss
​	timestamp/datetime：日期加时间，
​			    yyyyMMdd hhmmss
​	datetime:保存范围1900-1-1~xxxx年 占8字节
​	timestamp:保存范围1970-1-1~2038-12-31 占4字节

#### ##常见约束

#@常见约束

#说明：用于限制表中字段的数据的，从而进一步保证数据表
#中的数据是一致的、准确的、可靠的

#六大约束：
NOT NULL 非空		用于限制该字段为必填项
DEFAULT 默认		用于限制该字段没有显式插入值，
			直接显式默认值
PRIMARY KEY 主键♥	用于限制该字段不能重复，设置
			为主列键的字段默认不能为空
			一行信息中设置多个字段为主键：
			单个字段可以重复，这多个字段
			不能同时重复
UNIQUE 唯一		用于限制该字段值不能重复
			字段可以为空
			一个表可以设置多个
CHECK 检查		用于限制该字段的值必须满足
			指定条件【mysql中不支持check约束】
			CHECK(age BETWEEN 1 AND 100) 
FOREIGN KEY 外键	用于限制两个表的关系，要求
			外键列的值必须来自于主表的
			关联列
			要求：
			①主表的关联列和从表的关联列的
			类型必须一致，意思一样，名称无要求
			②主表的关联列要求必须是主键

#案例：添加约束

```
CREATE TABLE IF NOT EXISTS stuinfo(
	stuid INT PRIMARY KEY, -- 主键约束
	stuname VARCHAR(20) UNIQUE NOT NULL, 
	stuname CHAR DEFAULT '男',
	email VARCHAR(20),
	borndate DATETIME
	major_id INT,
	CONSTRAINT fk_stuinfo_major FOREIGN KEY
	(major_id) REFERENCES major(id) -- 外键约束
);
```

### #修改表

#语法：
ALTER TABLE 表名 ADD|MODIFY|CHANGE|DROP COLUMN 字段名 字段类型 字段约束

#1.修改表名

ALTER TABLE stuinfo RENAME TO students;

#2.添加字段

ALTER TABLE students ADD COLUMN borndate TIMESTAMP NOT NULL;

#3.修改字段名

ALTER TABLE students CHANGE COLUMN borndate birthday DATETIME null；

#4.修改字段类型

ALTER TABLE students MODIFY COLUMN birthday TIMESTAMP;

#5.删除字段

ALTER TABLE students DROP COLUMN birthday;





### #删除表

DROP TABLE IF EXISTS students;

### #复制表

#仅仅复制表的结构

CREATE TABLE newTable LIKE stuinfo;

#复制表的结构+数据

CREATE TABLE newTable2 SELECT * FROM girls.beauty;

#案例：复制employees表中的last_name,department_id,
#salary字段到新表emp表，但不复制数据

CREATE TABLE emp SELECT last_name,department_id,salary 
FROM myemployees.`employees`
WHERE 1=2; -- 必没有数据成立了

# 六、DML

/*

DML（Data Manipulation Language）
数据操纵语言：insert update delete
**对表中数据的增删改**



*/

## 1.数据的插入

### #方式一：values

语法：

插入单行
insert into 表名(字段名1，字段名2，...) 
values (值1，值2，...);

-- value:插入单行	values:插入多行

插入多行
insert into 表名(字段名1，字段名2，...) 
values (值1，值2，...),(值1，值2，...),...,(值1，值2，...);

**特点：**

**①字段和值列表一一对应，包含类型、约束等必须匹配**

**②数值型的值，不用单引号，**
**非数值型的值，必须使用单引号**

**③字段顺序无要求**



*/

### #方式二：set

语法：
insert into 表名
set 列名=值，列名=值，...


两种方式对比：

1、方式一支持插入多行，方式二只支持插入单行
2、方式一支持子查询，方式二不支持
	#查找id为26、名叫大傻逼，电话号码为111的人
	insert into beauty(id,name,phone)
	select 26,'大傻逼','111';
*/

#案例：

```
INSERT INTO stuinfo(stuid,stuname,stugender,email,age,majorid)
VALUES(1,'吴倩','男','1@qq.com',12,1);
```

-- 记得添加的数据要遵守约束的限制

#案例2：可为空字段stugender如何插入null

```
INSERT INTO stuinfo(stuid,stuname,email,age,majorid)
VALUES(2,'sb','2@qq.com',17,2);
```

#或

```
INSERT INTO stuinfo(stuid,stuname,stugender,email,age,majorid)
VALUES(2,'sb',NULL,'2@qq.com',17,2);
```

#或

```
INSERT INTO stuinfo(stuid,stuname,stugender,email,age,majorid)
VALUES(2,'sb',NULL,'2@qq.com',17,2);
```

#案例3：默认字段如何插入

```
INSERT INTO stuinfo(stuid,stuname,stugender,email,age,majorid)
VALUES(2,'sb',DEFAULT,'2@qq.com',17,2);
```

#或
#和案例2的方案1一样的做法

#或
#和案例2的方案1一样的做法

#案例4：可以省略字段名

```
INSERT INTO stuinfo
VALUES(2,'sb',NULL,'2@qq.com',17,2);
```

-- 这种情况下就要和表里列的顺序一一对应了

-- 这种情况下就要和表里列的顺序一一对应了

/*

### #补充：自增长列

1、自增长列要求必须设置在一个键上，比如主键或唯一键
2、自增长列要求数据类型为数值型
3、一个表至多有一个自增长列

create table grade_info(
	gradeID int primary key auto_increment,
	gradeName varchar(20)
);

INSERT INTO grade_info 
VALUES(NULL,'一年级'),(NULL,'退学'),(null,'三年级');

*/



## 2.修改语句

/*

### #修改单表的记录

**语法及执行顺序：**
**update 表名	①**
**set 列=新值，列=新值，...	②**
**where 筛选条件;		③**

### #修改多表的记录【补充】

语法：
sql92语法：
update 表1 别名，表2 别名
set 列=值，...
where 连接条件
and 筛选条件;

sql99语法：
update 表1 别名
inner|left|right join 表2 别名
on 连接添加
set 列=值,...
where 筛选条件

*/

#1.修改单表的记录
#案例1：修改beauty表中姓唐的女神的电话为110

UPDATE beauty SET phone='110'
WHERE NAME LIKE '唐%';

#案例2：修改boys表中id为2的名称为张飞，魅力值10
UPDATE boys SET boyname='张飞',usercp=10
WHERE id=2;

#2.修改多表的记录
#案例1：修改张无忌的女朋友的手机号为120

UPDATE boys b
INNER JOIN beauty b
ON bo.id=b.boyfriend_id
SET b.phone = '120'
WHERE bo.name='张无忌';

#案例2：修改没有男朋友的女生的男朋友编号都为2号

UPDATE boys bo
RIGHT INNER JOIN beauty b 
ON bo.id=b.boyfriend_id
SET b.boyfriend_id = 2
WHERE b/boyfriend_id IS NULL;



## 3.数据的删除

/*

### #方式1：delete语句

	语法：delete from 表名 where 筛选条件;

### #方式2：truncate语句

	语法：truncate table 表名;

*/

#案例1：删除姓李的所有信息

```
DELETE FROM stuinfo WHERE stuname LIKE '李%';
```


#案例2：删除表中所有数据

```
TRUNCATE TABLE stuinfo;
```

### #delete和truncate的区别

#1.delete可以添加where条件

truncate不能添加where条件，一次性清除所有数据

#2.truncate的效率较高
#3.如果删除带增长列等待表，

使用delete删除后，重新插入数据，记录从断点处开始

使用truncate删除后，重新插入数据，记录从1开始

#4.delete删除数据，会返回受影响的行数

truncate删除数据，不返回受影响的行数

#5.delete删除数据，可以支持事务回滚

truncate删除数据，不支持事务回滚

## 4.事务

/*
事务概述：
	一个事务是由一条或多条sql语句构成，同时执行成功
	或同时执行失败

分类：

隐式事务：没有明显的开启和结束标记
		比如dml语句：insert、update、delete

显式事务：具有明显的开始和结束标记
		一般由多条sql语句组成

	步骤：
	取消事务自动开启的功能
	
	开始事务
	编写事务需要的sql语句（1/多）
	结束事务
*/


SHOW VARIABLES LIKE '%auto%'

### #演示事务的使用步骤

#1、取消事务自动开启
SET autocommit = 0;

#2、开启事务
START TRANSACTION;

#3、编写事务的sql语句

#将张三丰的钱+500
UPDATE stuinfo SET balance = balance-500 WHERE stuid=1;

#将灭绝的钱+500
UPDATE stuinfo SET balance = balance+500 WHERE stuid=2;

#结束事务

#提交
COMMIT;

#回滚
ROLLBACK;