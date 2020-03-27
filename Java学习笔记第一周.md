[TOC]

2020.03.27

# markdown赛高

md的排版看着就很舒服

然后在这里把自己学到的概念都记录下来，有空就可以来温习啦

# 快速注释

快捷键：选定 + ctrl + /
# 方法重载（overload）
* 同名的方法，不同的参数（顺序/个数/类型）
* 必须在同一个类中

# 可变个数参数的方法
* 例：

```java
public void printInfo(String... args){		//String... args / int... i
	for(int i = 0;i < args.length;i++){	//迭代
		System.out.println(args[i]);
	}
}
```
* 使用这种方法，调用的时候没有参数也可以不填

# JVM内存模型
JVM内存：
* 栈：基础数据类型（包括对象在堆中的地址）

  java的方法运行在栈内存中，在运行方法时会动态进栈、出栈

* 堆：所有的对象（包括自定义的对象和字符串对象）

* 方法区：所有的class和static变量

**即对象的地址存在栈中，对象的值存在堆中**

## Java对象的实例化过程

![1585227632798](C:\Users\wj\AppData\Roaming\Typora\typora-user-images\1585227632798.png)

# 类实例化的对象也可以做形参（传递对象的地址）

其实就是类似C里的结构体指针作形参：
传进来了地址，方法里变量的值改变，方法外对应的变量也会改变。

# 包(package)
## 包的概念
* 类似于文件夹，方便管理
* 不同包下允许两个class文件重名
* 用“.”来指明包的层级
* **包通常用小写单词，类名首字母通常大写**
## 引用关键字import
* 举个栗子：包a里有类Person.java，包b下的包c里也有类Person.java，现在我要在包d的Test.java中调用包c中的Person.java，怎么辨别？
只需要使用import：

```java
import b.c.Person;
Person p = new Person();
```
* 当然，这样也是可以的：

```java
b.c.Person p = new b.c.Person();
```

* 或者可以简单粗暴地把包c下的所有文件都引用：

```java
import b.c.*;
Person p = new Person();
```
* 如果使用同一个包下的类，import可以省略
## JRE System Library
JDK中内置的包，包括java.lang、java.awt等等等等

# 面向对象特征之一：封装和隐藏
把类的属性开放出来，让调用者随意使用：

```java
//class Person
public int age;
```

```java
//class Test 
Person p = new Person();
p.age = -100;
```
若是想不让调用者随意使用：做封装和隐藏处理：

```java
//class Person
private int age;
public void setAge(int a){		
	if(a<=150 && a>=0){
		System.out.println("年龄是"+a);
	}else{
		System.out.println("年龄输入错误啦，检查检查。")
	}
}

```

```java
//class Test
Person p = new Person();
p.setAge(18);
```
上面的方法与**域访问器**类似，都是封装特性的体现：

 - 把属性设置声明为私有类型(关键字private)
 - 通过编写public类型的setXxx()、getXxx()方法来设置属性和获取属性。
 - （快捷键：右键-sourse-general setter and getter）

# 访问修饰符
下面这些关键字在Java中被称为**权限修饰符**，置于类的成员定义前，用来限制对象对该类成员的访问权限：
| 修饰符    | 类内部 | 同一个包 | 子类 | 其他地方 |
| --------- | ------ | -------- | ---- | -------- |
| private   | √      |          |      |          |
| default   | √      | √        |      |          |
| protected | √      | √        | √    |          |
| public    | √      | √        | √    | √        |
(不加修饰符，系统默认为default类型)

需要注意的地方：
 - 对于class的权限修饰，只能使用public或default（即缺省）
 - 同个文件中可包含多个类，但只能出现一个public类，其他类只能是缺省类

# 关键字this
观察下面是如何实现区分同名的形参和成员变量的：
```java
public class Person{
	public Person(int age,int name){
		this.age = age;
		this.name = name;
	}
	public int age;
	public String name;
	public void setName(String name){
		this.name = name;
	}
}
```
没错，就是用了`this`！字面意思，this表示类对象本身。

 - 合理使用`this`能增强代码的阅读性噢。
 - 需要注意的是，this不能在static修饰的方法里使用。（具体原因问百度）
 - `this`可以调用成员变量，也可以调用方法。
 - `this`还可以用来调用构造方法：


```java
this();		
//等同于调用构造方法 public Person(){}
```

> 使用this()必须放在构造器的首行，且使用this调用本类其他的构造器，保证至少有一个构造器是不用this的（也就是构造器不能自己调用自己）。

# JavaBean
JavaBean是一种Java语言协程的可重用组件，是指符合如下标准的Java类：

 - 类是**公共**的（**public**）
 - 有一个**无参**的公共的构造器
 - 有**私有**的属性（**private**），且有**对应的get**、**set**方法

# 高级类的特性
# 面向对象特征之二：继承（extends）
## 类继承语法
```java
public class PersonChild extends Person(){
	System.out.println(age);
}
```
子类通过继承（关键字extends）的方式实现继承父类的方法和属性。

## 继承的意义
当多个类存在**相同属性和行为**时（子类），将这些内容抽取到单独一个类（父类）中，那么多个类无需再定义这些属性和行为，只要继承那个类即可。

 - **提高了代码的复用性**
 - 继承的出现**让类与类之间产生了关系**，**提供了多态的前提**
 - 继承的前提是要**注重代码的逻辑性**哦（不要仅为了获取其他类中某个功能而去继承） 
 - 概括来说：**子类不是父类的子集，而是对父类的扩展**

## 类继承的规则

 - **子类不能直接访问父类中私有的(private)成员变量和方法**
 - **Java只支持单继承，即一个子类只能有一个父类**
 - **Java支持多层继承，即一个子类同时也是其他类的父类**
 - **子类并不会继承父类的构造器，子类的构造器必须通过关键字super调用父类的构造器**

## 父子类与访问修饰符的关系
上面我们学了访问修饰符的访问权限范围：
| 修饰符    | 同一个类 | 同一个包 | 子类 | 其他地方 |
| --------- | -------- | -------- | ---- | -------- |
| private   | √        |          |      |          |
| default   | √        | √        |      |          |
| protected | √        | √        | √    |          |
| public    | √        | √        | √    | √        |

> 注：同一个包指的是同层的文件，包中的包不在同层文件的范围内。

上表表明：

 - 如果父类和子类在同一个包下，那么对于父类的成员，只要不是 private，都可以使用；
 - 如果不在同一个包下，子类只能使用父类中 protected 和 public 类的成员。

# 方法的重写（override）
## 定义
定义：在子类中可以根据需要对从父类继承来的方法进行改造，也称方法的重置、覆盖。在程序执行时，子类的方法将覆盖父类的方法。

## 要求

 - **重写方法必须要和被重写方法具有相同的方法名称、参数列表和返回值类型**（即方法体外不允许改动）**只重新编写方法体内的代码**
 - **重写方法不能使用比被重写方法更严格的访问权限**
 - **重写和被重写的方法同时为static或非static**
 ## 快捷键
 快捷键：alt + / 提示补全

# 关键字super
**super是Java中调用父类的指定操作的关键字**：

 - 父类的属性
 - 父类的方法
 - 父类的构造器

需要注意的是：
 - **子父类出现同名成员时可用super进行区分；**
 - **super的追溯不仅限于直接父类**（多层继承追溯）；
 - super和this的用法相像，this代表本类对象的引用，super代表父类的内存空间的标识。
## 调用父类的构造器
注意：
- **子类中所有的构造器默认都会访问父类中空参数的构造器**
- **当父类中没有空参数的构造器时，子类的构造器必须通过this或者super语句指定调用本类或者父类中相应的构造器，且必须放在构造器的第一行**
- **即如果子类构造器没有显式调用父类或本类的构造器，且父类中又没有无参的构造器，则编译出错**

如：

```java
public class ManKind{

​	public ManKind(int sex,int salary){

​		this.sex = sex;

​		this.salary = salary;

​	}

​	int sex;

​	int salary;

}
```



```java
public class Kids extends ManKind{

​	public Kids(int sex,int salary){

​		super(sex,salary);

​	}

}
```



## 关键字this和super的区别

| No.  | 区别点         | this                                                         | super                                        |
| ---- | -------------- | ------------------------------------------------------------ | -------------------------------------------- |
| 1    | **访问属性**   | **访问本类中的属性；如果本类中没有此属性，则从父类中继续查找**（找不到你的就去你爸那儿找） | **访问父类中的属性**                         |
| 2    | **调用方法**   | **访问本类中的方法**                                         | **直接访问父类中的方法**                     |
| 3    | **调用构造器** | **调用本类构造器，必须放在构造器的首行**                     | **调用父类构造器，必须放在子类构造器的首行** |
| 4    | **特殊**       | **表示当前对象**                                             | **无**                                       |



# 面向对象特征之三：多态性

身为面向对象中最重要的概念，多态性在Java中有两种体现：

1. 方法的重载（本类中的同名方法）和重写（子类对父类方法的覆盖）

2. 对象的多态性——可以直接应用在抽象类和接口上

   如：

   ```java
      Person p = new Person();
   
      Person e = new Student();		//Person类型的变量e，指向Student类型的对象
   ```

   

**子类可看成是特殊的父类，所以父类类型的引用可以指向子类的对象，称为：向上转型**

**引用类型变量：用类名声明的变量**

- 一个引用类型变量如果声明为父类类型变量，却引用的是子类的对象，则该变量不能再访问子类中添加的属性和方法（e不能再访问Student类中的变量），即属性是父类类型变量。



## 虚拟方法调用

```java
Person e = new Student();

e.getInfo();
```

上面的e.getInfo()调用的是Person中的Student中的getInfo()呢？

答案是Student中的getInfo()方法。



### 编译时类型和运行时类型/动态绑定

- 属性是在编译时确定的，编译时e为Person类对象，则属性为Person类对象；

- 方法的调用是在运行时确定的，所以调用的是Student类的方法。

  

## 多态小结（迷）

- 前提：

  1. 需要存在继承（**父子类**）或者实现关系

  2. 要有覆盖操作（**子类重写父类的方法**）

     

- 成员方法的多态性/动态绑定（前提：是子类对父类方法的重写）：
  1. 编译时：引用变量所属的类中是否有所调用的方法（Person）
  2. 运行时：调用实际对象所属的类中的**重写**方法（Student）



- 成员变量：
  	1. 不具备多态性，只看引用变量所属的类（Person）



- 总结—多态的条件：
  1. 继承关系
  2. 重写父类方法
  3. 父类引用指向子类对象

## 多态性应用举例

方法声明的形参为父类类型，可以使用子类的对象作为实参调用该方法：

```java
public class Test{

​	public void method(Person a){

​	//...

​	e.getInfo();

}

​	public static void main(String args[]){

​		Test t = new Test();

​		Student m = new Student();

​		t.method(m);	//子类的对象m传给父类类型的参数e

​	}

}
```



## instanceof操作符

- B instanceof A：检验B是否为类A的对象（或子类对象），返回值为boolean型

- 如果B所属的类和类A不是父子类关系，则编译错误。



# Object类



## Object——根父类

- Object类是所有Java类的基类（根父类）

- 所有类均是Object类的子类，均能使用Oject类的方法

- 如果在类的声明中没有使用extends关键字指明其父类，则默认父类为Object类

  

  ## 方法可设置参数为Object类，用以接收类型不确定的类

  ```java
  public class Test{
  
  ​	public void method(Object obj){		//形参设置为接收Object类
  
  ​		
  
  	}
  
  	public static void main(String[] args){
  
  ​		Test t = new Test();
  		Person p = new Person();
  		t.method(p);					//可以传进任意类
  		
  	}
  
  }
  ```



## Object类的主要方法

- public Object()：构造方法

- public boolean equals(Obeject obj)：比较是否为同一对象（是否引用同一对象）

  ```java
  System.out.println(p.equals(a));
  ```

  

- public int hashCode()：获取Hash码

- public String toString()：打印对象的内存地址



# 对象类型转换（Casting）

## 基本数据类型的转换

- 自动类型转换：小的数据类型—>大的数据类型
- 强制类型转换：大的数据类型—>小的数据类型

```java
int I;

long L;

L = I;				//小赋值给大

I = (int)L;			//大(强制转换)赋值给小
```



## Java对象的强制类型转换（造型）

- 从子类到父类的类型转换可以自动进行
- 从父类到子类的类型转换必须通过造型实现
- 无继承关系的引用类型间的转换是非法的

```java
Person p = new Person();

Student s = new Student();

p = s;					//子类赋值给父类

s = (Student)p;			//父类(强制转换)赋值给子类
```

```
String s = "hello";

Object o = s;			//子类到父类的转换：自动类型转换

s = (String)o;			//父类到子类的转换：需造型
```



# ==操作符与equals方法

## 操作符==

- 比较基本数据类型：比较变量值，变量值相同返回true，反之false
- 比较对象类型：比较内存地址，指向同一个对象返回ture，反之false
- ==两边的数据类型必须兼容（继承关系）（可自动类型转换的基本数据类型除外），否则编译出错
- String不属于基本数据类型噢



## equals方法

所有类（因为都是Object的子类）都有equals()方法，可以对其重写。

- 只能比较引用类型
- 作用与==相同，比较是否指向同一对象
- 特例：类File、String、Date和包装类，equals比较类型和内容，而不考虑是否同一对象（equals方法被改写）



# String对象的创建

<img src="C:\Users\wj\AppData\Roaming\Typora\typora-user-images\1585293832652.png" alt="1585293832652" style="zoom:200%;" />

1. 字面量创建String对象

   - 查找字符串常量池中有无"abc"对象
   - 无：在常量池中添加"abc"对象，返回引用地址给s1
   - 有：返回相同的引用地址给s2
   - 即相同值的两个String对象s1、s2地址相同
   - 省内存（因为不用额外创建对象）

2. new创建String对象

   - 查找字符串常量池中有无"abc"对象
   - 无：在常量池中添加"abc"对象，在堆中创建对象s3，返回指向堆中s3的引用地址
   - 有：在堆中创建对象s4，返回指向堆中s4的引用地址

   - 即值相同的两个String对象s3、s4地址不同

###### mark:P68day8 2

# 包装类（Wrapper）

八大包装类：

| 基本数据类型 | 包装类    |
| :----------: | --------- |
|   boolean    | Boolean   |
|     byte     | Byte      |
|    short     | Short     |
|     int      | Integer   |
|     long     | Long      |
|     char     | Character |
|    float     | Float     |
|    double    | Double    |

## 装箱与拆箱

- 基本数据类型包装成包装类的实例 称为 **装箱**

  ```java
  int i = 500;
  
  Integer t = new Integer(i);
  
  //通过包装类的构造器Integer实现装箱
  ```

  

- 获得包装类对象中包装的基本数据类型 称为 **拆箱**

```java
Integer i = new Integer();

int i1 = i.intValue();

System.out.println(i1);
//通过i.intValue()获取包装内的基本数据类型值
```

- JDK1.5后支持**自动装拆箱**：

  ```java
  Integer i = 112;
  //自动装箱
  int i1 = i;
  //自动拆箱
  ```

## 包装类的用法

包装类使得**基本数据类型的数据转化成类**，从而**能够调用类中的方法**：

```java
Integer t = new Integer(500);

String s = t.toString();

//通过.toString方法，将t的值500转换成字符串，再初始化给s
```

**基本数据的包装类的意义在于，实现基本数据类型和字符串的相互转换。**

# 字符串与基本数据类型的相互转换

- **字符串转换成基本数据类型**：

  1. 通过**包装类的构造器**实现

     ```java
     int i = new Integer("12");
     ```

     

  2. 通过包装类的**parseXxx(String s)**的方法实现

     ```java
     float f = Float.parseFloat("12.1");
     ```

     

- **基本数据类型转换成字符串**：

  1. 调用字符串重载的**valueOf()**方法实现

     ```java
     int i = 0;
     
     String istr = String.ValueOf(i);
     //valueOf()
     ```

     

  2. 直接转

     ```java
     String istr = 5 + "";
     //5 + ""
     ```



## toString方法

- Object的toString方法输出当前对象的内存地址

- 如果你想要输出类的其他信息（如基本数据类型），可以改写toString方法
- 打印(print)对象相当于执行打印m.toString()



# 高级类特性

## 关键字static

### 适用范围

可修饰**属性**、**方法**、**代码块**、**内部类**

### 被修饰后的成员的特点

1. **随着类的加载而加载**
2. **不需要创建对象才能调用**
3. **修饰的成员被所有对象共享**
4. **访问权限允许时，可以直接调用**

### 实例变量与类变量/静态变量

- 类中的变量是**实例变量**，不能被同一个类的不同对象共享，每一个对象的实例变量没有关联（**实例化后才能用**）

- 而static变量称为**类变量 / 静态变量**，是类的一部分，一个类中的所有实例化对象共享一个类变量（**不实例化也能用**）（通过类名.方法调用）

### 类属性、类方法的设计思想

- 设计类时，分析哪些方法、属性**不因对象的不同而不同**，将这些类设置为类(static)方法、类属性。
- 若一个**方法与调用者无关**，那么声明该方法为类方法，在**调用方法时就省去了创建对象这一步**，很方便。
- 设计成工具类
- 使用类属性时要慎重，因为一改类变量，所有对象中都会改变

### 注意事项

- **static方法内部不能有this / super（因为不需要实例化就能访问static方法）**
- **重载方法需要同时为static或不为static**



### 判断是否为空字符串的方法

```java
public static boolean isEmpty(String s){
	boolean flag = false;
	if(s != null && != s.equals("")){
		flag = true;
​		//...
	}
    return flag;
}
```

通常将该方法放在一个工具类中，设置成类方法，今后就可以多次直接调用

- 方法 **Utils.isEmpty(flag)** 就能输出是否为空字符串



## 单例设计模式

设计模式就是编程中解决问题的思路。

- 所谓类的单例设计模式，就是在整个软件系统运行过程中，这个**类只被实例化一次**，以后只调用这一个实例。

- **节省了大量内存资源和耗时**

### 饿汉式单例模式

*“一次性做完，来了就能吃”*

```java
public class single(){

​	private single(){

		//创建私有的构造器，外部不能实例化
    
​	}

​	private static Single single = new Single();
    	//创建私有的类变量，注意这个single

​	public static Single getInstance(){
    
		return single;
		//返回私有类变量的方法
​	}

}
```

```java
Single s1 = Single.getInstance();
Single s2 = Single.getInstance();
		//都指向上面的single
```



- 构造方法私有化（private），调用这个类的人不能直接使用new来创建对象
- 构造私有的static类型的类变量
- 创建public static getInstance()方法



### 懒汉式单例模式

*“来了我才做，一次性做完”*

```java
public class single(){

	private single(){

​		//创建私有的构造器，让外部不能实例化

	}

	private static Single single1 = null;
		//创建私有的类变量,赋值null，“还没做菜”

	public static Single getInstance(){
        
		if(single1 == null){
            //还没有被实例化，"第一个客人来了"
            single1 = new Single();
            //实例化，"做菜"
        }
        
​		return single1;
​		//返回私有类变量的方法
	}
	
}
```

```java
Single s = Single.getInstance();
//取得同一个single1对象
Single s1 = Single.getInstance();
```



- **懒汉式和饿汉式的区别，就是创建唯一对象的时机**



视频注：

> *暂时懒汉式还存在线程安全问题，讲到多线程时，可修复*

## 类的成员之四：初始化块

类的成员前三是啥？

## 关键字final

## 抽象类（abstract class）

## 更彻底的抽象：接口（interface）

## 类的成员之五：内部类



