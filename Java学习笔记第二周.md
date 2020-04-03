[TOC]

2020-4-3

# 三、快速注释

快捷键：选定 + ctrl + /
# 四、方法重载（overload）
* 同名的方法，不同的参数（顺序/个数/类型）
* 必须在同一个类中

# 五、可变个数参数的方法
* 例：

```java
public void printInfo(String... args){		//String... args / int... i
	for(int i = 0;i < args.length;i++){	//迭代
		System.out.println(args[i]);
	}
}
```
* 使用这种方法，调用的时候没有参数也可以不填

# 六、JVM内存模型
JVM内存：
* 栈：基础数据类型（包括对象在堆中的地址）

  java的方法运行在栈内存中，在运行方法时会动态进栈、出栈

* 堆：所有的对象（包括自定义的对象和字符串对象）

* 方法区：所有的class和static变量

**即对象的地址存在栈中，对象的值存在堆中**

## Java对象的实例化过程

![1585227632798](C:\Users\wj\AppData\Roaming\Typora\typora-user-images\1585227632798.png)

# 七、类实例化的对象也可以做形参（传递对象的地址）

其实就是类似C里的结构体指针作形参：
传进来了地址，方法里变量的值改变，方法外对应的变量也会改变。

# 八、包(package)
## 1.包的概念
* 类似于文件夹，方便管理
* 不同包下允许两个class文件重名
* 用“.”来指明包的层级
* **包通常用小写单词，类名首字母通常大写**
## 2.引用关键字import
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
## 3.JRE System Library
JDK中内置的包，包括java.lang、java.awt等等等等

# 九、面向对象特征之一：封装和隐藏
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

# 十、访问修饰符
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

# 十一、关键字this
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

# 十二、JavaBean
JavaBean是一种Java语言协程的可重用组件，是指符合如下标准的Java类：

 - 类是**公共**的（**public**）
 - 有一个**无参**的公共的构造器
 - 有**私有**的属性（**private**），且有**对应的get**、**set**方法

# 十三、高级类的特性

goto(P24)

# 十四、面向对象特征之二：继承（extends）
## 1.类继承语法
```java
public class PersonChild extends Person(){
	System.out.println(age);
}
```
子类通过继承（关键字extends）的方式实现继承父类的方法和属性。

## 2.继承的意义
当多个类存在**相同属性和行为**时（子类），将这些内容抽取到单独一个类（父类）中，那么多个类无需再定义这些属性和行为，只要继承那个类即可。

 - **提高了代码的复用性**
 - 继承的出现**让类与类之间产生了关系**，**提供了多态的前提**
 - 继承的前提是要**注重代码的逻辑性**哦（不要仅为了获取其他类中某个功能而去继承） 
 - 概括来说：**子类不是父类的子集，而是对父类的扩展**

## 3.类继承的规则

 - **子类不能直接访问父类中私有的(private)成员变量和方法**
 - **Java只支持单继承，即一个子类只能有一个父类，不支持多重继承（多个父类）**
 - **Java支持多层继承，即一个子类同时也是其他类的父类**
 - **子类并不会继承父类的构造器，子类的构造器必须通过关键字super调用父类的构造器**

## 4.父子类与访问修饰符的关系
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

# 十五、方法的重写（override）
## 1.定义
定义：在子类中可以根据需要对从父类继承来的方法进行改造，也称方法的重置、覆盖。在程序执行时，子类的方法将覆盖父类的方法。

## 2.要求

 - **重写方法必须要和被重写方法具有相同的方法名称、参数列表和返回值类型**（即方法体外不允许改动）**只重新编写方法体内的代码**
 - **重写方法不能使用比被重写方法更严格的访问权限**
 - **重写和被重写的方法同时为static或非static**
 ## 3.快捷键
 快捷键：alt + / 提示补全

# 十六、关键字super
**super是Java中调用父类的指定操作的关键字**：

 - 父类的属性
 - 父类的方法
 - 父类的构造器

需要注意的是：
 - **子父类出现同名成员时可用super进行区分；**
 - **super的追溯不仅限于直接父类**（多层继承追溯）；
 - super和this的用法相像，this代表本类对象的引用，super代表父类的内存空间的标识。
## *1.调用父类的构造器
注意：
- **子类中所有的构造器默认都会访问/加载父类中空参数的构造器**
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



## *2.关键字this和super的区别

| No.  | 区别点         | this                                                         | super                                        |
| ---- | -------------- | ------------------------------------------------------------ | -------------------------------------------- |
| 1    | **访问属性**   | **访问本类中的属性；如果本类中没有此属性，则从父类中继续查找**（找不到你的就去你爸那儿找） | **访问父类中的属性**                         |
| 2    | **调用方法**   | **访问本类中的方法**                                         | **直接访问父类中的方法**                     |
| 3    | **调用构造器** | **调用本类构造器，必须放在构造器的首行**                     | **调用父类构造器，必须放在子类构造器的首行** |
| 4    | **特殊**       | **表示当前对象**                                             | **无**                                       |



# *十七、面向对象特征之三：多态性

身为**面向对象中最重要的概念**，多态性在Java中有两种体现：

1. **方法的重载**（本类中的同名方法）和**重写**（子类对父类方法的覆盖）

2. **对象的多态性**——可以直接应用在**抽象类**和**接口**上

   如：

   ```java
      Person p = new Person();
   
      Person e = new Student();		//Person类型的变量e，指向Student类型的对象
   ```

   

**子类可看成是特殊的父类，所以父类类型的引用可以指向子类的对象，称为：向上转型（P19.2.1）**

**引用类型变量：用类名声明的变量**

- 一个引用类型变量如果声明为父类类型变量，却引用的是子类的对象，则该变量不能再访问子类中添加的属性和方法（e不能再访问Student类中的变量），即属性是父类类型变量。



## *1.虚拟方法调用

```java
Person e = new Student();

e.getInfo();
```

上面的e.getInfo()调用的是Person中的Student中的getInfo()呢？

答案是Student中的getInfo()方法。



### *1.1编译时类型和运行时类型/动态绑定

- 属性是在编译时确定的，编译时e为Person类对象，则属性为Person类对象；

- 方法~~的调用~~是在运行时确定的，所以调用的是Student类的方法。

  

## *2.多态小结（迷）

- 前提：

  1. 需要存在继承（**父子类**）或者实现关系

  2. 要有覆盖操作（**子类重写父类的方法**）

     

- 成员方法的多态性/动态绑定（前提：是子类对父类方法的重写）：
  1. 编译时：引用变量所属的类中是否有所调用的方法（Person）
  2. 运行时：调用实际对象所属的类中的**重写**方法（Student）



- 成员变量（P25.2.2.1）：
  	1. 不具备多态性，只看引用变量所属的类（Person）



- 总结—多态的条件：
  1. 继承关系
  2. 重写父类方法
  3. 父类引用指向子类对象

## 3.多态性应用举例

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



## 4.instanceof操作符

- B instanceof A：检验B是否为类A的对象（或子类对象），返回值为boolean型

- 如果B所属的类和类A不是父子类关系，则编译错误。



# 十八、Object类



## 1.Object——根父类

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



## 2.Object类的主要方法

- public Object()：构造方法

- public boolean equals(Obeject obj)：比较是否为同一对象（是否引用同一对象）

  ```java
  System.out.println(p.equals(a));
  ```

  

- public int hashCode()：获取Hash码

- public String toString()：打印对象的内存地址

### 2.2.toString方法

- Object的toString方法输出当前对象的内存地址

- 如果你想要输出类的其他信息（如基本数据类型），可以改写toString方法
- 打印(print)对象相当于执行打印m.toString()

# 十九、对象类型转换（Casting）

## 1.基本数据类型的转换

- 自动类型转换：小的数据类型—>大的数据类型
- 强制类型转换：大的数据类型—>小的数据类型

```java
int I;

long L;

L = I;				//小赋值给大

I = (int)L;			//大(强制转换)赋值给小
```



## 2.Java对象的强制类型转换（造型？转型？）

- 从子类到父类的类型转换可以自动进行
- 从父类到子类的类型转换必须通过造型实现
- 无继承关系的引用类型间的转换是非法的

### 2.1向上转型（upcasting）&& 向下转型（downcast）

```java
Person p = new Person();

Student s = new Student();

p = s;					//子类赋值给父类

s = (Student)p;			//父类(强制转换)赋值给子类
```



```java
String s = "hello";

Object o = s;			//子类到父类的转换：自动类型转换

s = (String)o;			//父类到子类的转换：需造型
```



# 二十、==操作符与equals方法

## 1.操作符==

- 比较基本数据类型：比较变量值，变量值相同返回true，反之false
- 比较对象类型：比较内存地址，指向同一个对象返回ture，反之false
- ==两边的数据类型必须兼容（继承关系）（可自动类型转换的基本数据类型除外），否则编译出错
- String不属于基本数据类型噢



## 2.equals方法

所有类（因为都是Object的子类）都有equals()方法，可以对其重写。

- 只能比较引用类型
- 作用与==相同，比较是否指向同一对象
- 特例：类File、String、Date和包装类，equals比较类型和内容，而不考虑是否同一对象（equals方法被改写）



# 二十一、String对象的创建

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

# 二十二、包装类（Wrapper）

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

## 1.装箱与拆箱

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

## 2.包装类的用法

包装类使得**基本数据类型的数据转化成类**，从而**能够调用类中的方法**：

```java
Integer t = new Integer(500);

String s = t.toString();

//通过.toString方法，将t的值500转换成字符串，再初始化给s
```

## 3.包装类的意义

**基本数据的包装类的意义在于，实现基本数据类型和字符串的相互转换。**

# 二十三、字符串与基本数据类型的相互转换

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



# 二十四、高级类特性

## 1.关键字static

### 1.1适用范围

可修饰**属性**、**方法**、**代码块**、**内部类**

### 1.2被修饰后的成员的特点

1. **随着类的加载而加载**
2. **不需要创建对象才能调用**
3. **修饰的成员被所有对象共享**
4. **访问权限允许时，可以直接调用**

### 1.3实例变量与类变量/静态变量

- 类中的变量是**实例变量/成员变量**（P25.2），不能被同一个类的不同对象共享，每一个对象的实例变量没有关联（**实例化后才能用**）

- 而static变量称为**类变量 / 静态变量**（P25.2.4），是类的一部分，一个类中的所有实例化对象共享一个类变量（**不实例化也能用**）（通过类名.方法调用）

### 1.4类属性、类方法的设计思想

- 设计类时，分析哪些方法、属性**不因对象的不同而不同**，将这些类设置为类(static)方法、类属性。
- 若一个**方法与调用者无关**，那么声明该方法为类方法，在**调用方法时就省去了创建对象这一步**，很方便。
- 设计成工具类
- 使用类属性时要慎重，因为一改类变量，所有对象中都会改变

### 1.5注意事项

- **static方法内部不能有this / super（因为不需要实例化就能访问static方法）**
- **重载方法需要同时为static或不为static**



### 1.6判断是否为空字符串的方法

```java
public static boolean isEmpty(String s){
	boolean flag = false;
	if(s != null && != s.equals("")){
    //字符串不是空指针或字符串不为空
		flag = true;
​		//...
	}
    return flag;
}
```

**通常将该方法放在一个工具类中，设置成类方法，今后就可以多次直接调用**

- ~~方法 **Utils.isEmpty(flag)** 就能输出是否为空字符串~~



## 2.单例设计模式（singleton）

设计模式就是编程中解决问题的思路。

- 所谓类的单例设计模式，就是在整个软件系统运行过程中，这个**类只被实例化一次**，以后只调用这一个实例。
- （所有实例都指向统一对象？）

- **节省了大量内存资源和耗时**

### 2.1饿汉式单例模式

*“一次性做完，来了就能吃”*

```java
public class Single(){

​	private Single(){

		//创建私有的构造器，外部不能实例化
    
​	}

​	private static Single single = new Single();
    	//创建私有的类变量，注意这个single

​	public static Single getInstance(){
    
		return single;
		//返回私有类变量的方法，通过这个方法来实例化
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



### 2.2懒汉式单例模式

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

## 3.类的成员之四：初始化块

> 类的成员前三是啥？属性、方法、构造器！

> 然后就是现在学的初始化块~

### 3.1初始化块的作用

初始化块其实就是一段代码块，用来：**对Java对象进行初始化**

```java
public class Person{

​	String name;

​	public Person(){

​		this.name = "法外狂徒"

​		System.out.println("构造器方法");

​	}
	//
​	{//非静态代码块

​		System.out.println("非静态代码块");

​	}

}
```

### *3.2程序执行顺序

- 声明成员变量（默认值）

- 显式初始化

- 执行静态代码块（里面要求属性和方法都是静态的）

- 执行非静态代码块

- 执行构造器

  > Tips：静态代码块只加载一次哦（被所有对象共享嘛）

## *4.匿名内部类

> 没有类名的子类

```java
//某个类内部
//这是一个Person类的匿名子类
Person p = new Person(){
    //用代码块代替构造方法
    //进行初始化
    {
        super.name = "厄斐琉斯";
    }
    
​	public void test{	//重写Person类方法

​		System.out.println("===这是匿名内部类===");

​	}

}//匿名内部类
```

~~老师说虽然还不知道有什么用但是存在即合理......~~

（P24.10）

> 匿名内部类没有类名，不能使用显式的new方法创建对象，这个时候就需要初始化块

## 5.关键字final

- **final修饰的类不能被继承**
- **final修饰的方法不能被子类重写**
- **final修饰的变量称为常量，名称大写，必须显式赋值且不能被修改**
- *final static* ——**全局常量**

## *6.抽象类（abstract class）

抽象类——设计得很抽象，以至于没有具体实例（？）

### 6.1关键字abstract

- 用**abstract**关键字来修饰的**类**称作**抽象类**

- 用**abstract**关键字来修饰的**方法**称作**抽象方法**

  - **抽象方法只有方法的声明，没有方法的实现**

  - **抽象方法以分号结束：**

    ```java
    abstract int abstractMethod(int a);
    ```

- **含有抽象方法的类必须被声明为抽象类**

### 6.2抽象类

- 抽象类可以有构造方法
- **抽象类不能被实例化**（因为什么都没有hh）
- **抽象类作为父类被继承，抽象类的子类必须重写父类的抽象方法，并提供方法体，如果没有重写全部抽象方法，仍然（必须）是抽象类**
- **不能用abstract修饰属性、private私有方法、构造器、static静态方法、final方法**

## 7.模板方法设计模式（TemplateMethod）

抽象类就体现了模板方法设计模式。

- 一部分功能是确定的，一部分功能是不定的，就可以让不确定的那部分留给子类实现
- 父类仅提供通用方法，一些不确定的方法留给子类实现

## *8.更彻底的抽象：接口（interface）

有时候子类必须从多个父类继承属性和方法，但是Java又不支持**多重继承**，这时候就要用到**接口**：

### 8.1什么是接口

- 接口是**抽象方法和常量值**的定义的集合
- **接口是一种特殊的抽象类，只包含常量和方法的定义，没有变量和方法的实现**
- **一个类只能继承一个父类，但是可以实现多个接口**
- **接口也可以继承其他接口**

### 8.2接口的特点

- **用*interface*定义**

```java
public interface method{

​	int i = 1;	//默认public static final,必须显式赋值

​	void test();	//默认为public abstract,抽象方法

}
```

- **所有成员变量默认是由*public static final*修饰的（默认为静态常量）**
- **所有方法默认是由*public abstract*修饰的（默认为抽象方法）**
- **没有构造器**
- 采用**多层继承机制**

### 8.3实现接口

通过***implements***实现单个/多个接口

```java
public class TestInImpl implements TestIn1,TestIn2{
//实现两个接口
​	public void test1(){

​		//接口抽象方法

​	}

​	public void test2(){

		//接口抽象方法

​	}

}
```

- **实现接口的类中必须实现接口中所有抽象方法，才能实例化，不然仍然是（要定义为）抽象类**

- **接口的作用是被实现类实现（面向接口编程）**

- **与继承关系类似，接口与实现类之间存在多态性（多态又来了....复习多态去）**

  ```java
  Cooking c = new SCTeacher();	//接口引用对象
  
  c.fry();	//使用接口方法
  
  //体现了对象的多态
  
  Sing s = new SCTeacher();
  
  s.sing();
  ```

- 定义接口&子类：

  ```java
  public class TestInImpl extends Person implements TestIn1,TestIn2{
  
  	public void test1(){
  ​		//实现接口抽象方法
  	}
  
  	public void test2(){
  ​		//实现接口抽象方法
  	}
  
  }
  ```

### 8.4接口存在的意义

~~用Java描述“会打篮球的歌手是个练习生”：~~

~~Person类<-(继承)-Practicer类-(实现)->BasketballPlaying接口&&Singing接口~~

### 8.5抽象类与接口的辨析

- **抽象类是对于一类事物的高度抽象，既有属性也有方法**
- **接口是对于一系列动作的抽象，对方法**

## 9.工厂模式

> 工厂模式是设计模式中应用最为广泛的模式，它通过面向对象的手法，将所要创建的具体对象的创建工作延迟到了子类，从而提供了一种拓展的策略，较好的解决了这种紧耦合的关系。
>
> 真正的开发工作中，每个开发人员写部分代码，合起来就是一个完整项目。

### 9.1.工厂方法（FactoryMethod）

###### P79day9 6

## 10.类的成员之五：内部类

- Java中，允许一个类（内部类）在另一个类（外部类）的内部定义，这就是内部类
- 内部类可以声明为final
- 和外部类不同，内部类可以声明private或protected
- 内部类可以声明为static，但就不能使用外部类非static的成员
- 内部类可以被声明为abstract，可以被其他类继承
- 内部类B调用外部类A的属性：A.this.i
- 内部类调用自身属性：this.i
- 外部类用自己内部类的方法要先创建对象：new A().method();

### *10.1内部类的作用

- **内部类主要解决Java不能多重继承的问题**
- 即一个类不能继承多个父类，就用自身的内部类去继承

#### 10.1.1内部类和接口的区别？

- 接口只有方法和常量，而且没有方法的实现，实现接口的类要把接口里的方法全部重写才能实例化对象



# 二十五、面向对象总结

## 1.类

### 1.1外部类

外部类只有两种修饰符：public和default(默认)

### 1.2内部类



### 1.3final类

不能被继承

### 1.4static类

内部类

## 2.类成员之一：属性

### *2.1成员变量/实例变量

对象内方法外的变量称为成员变量，可以在对象中任意地方被访问

### *2.2局部变量

方法中的变量

局部变量和成员变量可以重名，作用范围重叠时取就近原则

#### *2.2.1成员变量和局部变量的区别

- 都包含在对象内

- 作用域不同
- 局部变量没有默认初始值，而成员变量有默认的初始值
- 声明周期不同
  - 局部变量在方法调用完毕时就被销毁
  - 全局变量在对象消失时被销毁

### 2.3final属性/常量

称为常量，显式赋值后不能被修改

### 2.4static属性/静态变量/类变量

静态变量在类加载时创建

## 3.类成员之二：方法

### 3.1方法的重载

一个类中允许出现多个同名且修饰词相同、接收参数不同的类

### 3.2final方法

不能被重写

## 4.类成员之三：构造器

### 4.1构造器的重载

## 5.类成员之四：初始化块

### 5.1 静态代码块

## 6.类成员之五：内部类

### 6.1成员内部类

- 写在类里
- 解决多重继承

### 6.2局部/匿名内部类

写在方法里的

## 7.设计模式

### 7.1单例设计模式

#### 7.1.1懒汉式单例模式

#### 7.2.1饿汉式单例模式

## 8.抽象类（abstract）

## 9.特殊抽象类——接口（interface）

- 解决多重继承
- 属性：*public static final*
- 方法：*public abstract*

### 9.1接口和成员内部类的区别

## 10.面向对象的特征

### 10.1封装和隐藏

#### 10.1.1修饰符package

#### 10.1.2修饰符import

##### 10.1.2.1Java常用包

#### 10.1.3修饰符

### 10.2继承

#### 10.2.1关键字super

调用父类的属性方法

##### 10.2.1.1子类实例化过程

#### 10.2.2关键字extends

支持单继承

### 10.3多态

#### 10.3.1向上/向下转型

#### 10.3.2 instanceof

## 11.Object类

所有类的根父类

### 11.1String类

#### 11.1.1 equals()方法

#### 11.1.2 toString()方法

### 11.2包装类

#### 11.2.1装拆箱

#### 11.2.2 toString()方法

#### 11.2.3 intValue()方法

#### 11.2.4 Integer.parseInt()

## 12.JVM内存模型

# 二十六、Java异常

- 程序执行中发生的不正常情况称为异常

## 1.Java异常分类

1. **Error**

JVM系统内部错误、栈溢出、内存溢出等（比较严重）

2. **Exception**

网络连接中断、错误的类型转换、分母为0、数组越界、空指针访问、读取不存在的文件、连接不存在的URL等（编程性错误）

## 2.捕获异常

### 2.1异常处理机制

在编写程序时，经常要在可能出现错误的地方加上检测的代码：

**Java采用异常处理机制，将异常处理的程序代码集中在一起，与正常的程序代码分开，使得程序简洁，易于维护。**

> 程序员只能处理Exception，对Error无能为力

#### 2.1.1异常捕获try-catch

- 用***try{}catch(Excepetion e){e.printStackTrace();}***括住一段有可能出现错误的代码块:

```java
try{
	//System.out.println(1);
​	{}	//异常语句
	//System.out.println(2);
    //则只会输出134，2不会被输出
}catch(Excepetion e){	//catch可以写多个
	e.printStackTrace();
	//或
	System.out.println(e.getMessage());

	//System.out.println(3);
}finally{	//可选
    //System.out.println(4);
}
```

- **Exception是所有异常的父类**
- *getMessage()*方法是用来得到有关异常事件的信息
- *printStackTrace()*方法用来跟踪异常事件发生时执行堆栈的内容
- *try catch*是为了防止程序可能出现的异常，**只会捕获第一个出现的异常**
- finally块是捕获异常体系中的最后一段，不管是否发生异常都会执行

## 3.抛出异常

### 3.1声明抛出异常

声明抛出异常是Java中处理异常的第二种方式：

```java
public class Test1{

​	public static void main(String[] args){
    
​		B b = newB();

​		try{					//调用方法处捕获处理异常
​			b.test();
​		}catch(Exception e){
​			e.printStackTrace();
​		}
    
​	}

}

class B{

​	int i;

​	public void test() throws Exception{
	//使用throws在代码处抛出异常，在调用方法处捕获处理
​		B b = null;
​		System.out.println(b.i);
​	}

}
```

- ***throws Exception***

- **子类重写父类的方法时，重写方法不能抛出比被重写方法范围更大的异常类型，如父类*throw NullPointerException*,则子类不能*throw Exception*（范围更大）**

### 3.2人工抛出异常

Java异常类对象除在程序执行过程中出现异常时由系统自动生成并抛出，也可根据需要人工创建并抛出：

```java
try{
​	Test.test(-100);
}catch(Exception e){
​	e.printStackTrace();
}
```



```java
test() throws Exception{
    {}
    {throw new Exception("...");}
}
```

### 3.3创建并使用用户自定义异常类

```java
class MyException extends Exception{
​	public MyException(String msg){
​		super(msg);
​	}
}
```

```java
test() throws MyException{
    {}
    {throw new MyException("...");}
}
```

- Java提供的异常的类一般是够用的，只有很少见的特殊的情况下，可能需要自己编写异常类

> 所以自定义方法作为了解就够了

# 二十七、集合

## 1.Java集合概述

- **Java集合类存放于java.util包中，是一个用来存放对象的容器**
  - **集合只能存放对象**，如果存基本数据类型，会自动转换成包装类后再放入集合
  - **集合存放的是对象的引用，**对象本身仍在堆内存中
  - 集合可以**存放不同类型、不限数量的数据类型**

- **Java集合分为Set、List、Map三大体系**
  - Set：无序、不可重复的集合
  - List：有序、可重复的集合
  - Map：具有映射关系的集合

- JDK5之后，增加了**泛型**，Java集合可以记住容器中对象的数据类型

## 2.HashSet集合

### 2.1HashSet介绍

- HashSet是Set接口的典型实现。大多数时候set集合指的就是HashSet

- HashSet按Hash算法来存储集合中的元素，因此具有很好的存取和查找性能

### 2.2HashSet的特点

- **不能保证元素的排列顺序（无序，不”先来后到“）**依据hashCode值决定存储位置
- **不可重复**（指hashCode()返回值不相同）
- **不是线程安全的**
- **集合元素可以是null**

### 2.3HashSet实现接口

- HashSet类实现的是set接口

- set接口继承的是Collection接口

### 2.4HashSet操作

```
import java.util.Set;
import java.util.HashSet;
```

```
public class Test{

​	public static void main(String[] args){

​		Set set = new HashSet();
​    	//等价于
​    	Set<Object> set1 = new HashSet<Object>();
​    
​    	set.add(1);//添加元素
​    	set.add("1");//(Object e)
​    	set.add(null);//可存null
​    	set.add(true);//可存boolean值
​    	System.out.println(set);//输出集合
​    	set.remove(1);//移除元素
​    	System.out.println(set,contains(1));//返回boolean值，判断元素是否存在
​		set.clear();//清空集合
​    	System.out.println(set.size());//集合的元素个数
​    
​    	//使用迭代器遍历集合
​    	Iterator it = set.iterator();//迭代器
​    	while(it.hasNext()){//有下一个
​            System.out.println(it.next());//输出
​        }
​    
​    	//用for each迭代集合，推荐使用
​    	for(Object obj : set){//在for过程中，把每一个set元素取出来赋值给obj
​            System.out.println(obj);
​        }
​    	
​    	//如果想要让集合只存相同类型的对象，使用泛型
​    	set<String> set2 = new HashSet<String>();//指定String为集合的泛型->集合只能存String类数据
​    	set1.add("abc");
​    	
​	}

}
```



### 2.5判断两个元素相等的标准

- **HashSet集合判断两个元素相等的标准：两个对象通过equals()方法比较相等，并且两个对象的hashCode()方法返回值也相等**

- 一般情况下，两个对象通过equals()方法返回true，对象的hashCode值也相等



## 3.TreeSet集合

### 3.1TreeSet介绍

TreeSet是SortedSet接口的实现类。

TreeSet可以确保集合元素处于排序状态。

TreeSet支持两种排序：自然排序和定制排序。

TreeSet默认情况下采用自然排序。

### 3.2TreeSet实现接口

- TreeSet类实现NavigableSet接口
- NavigableSet接口继承SortedSet接口
- SortedSet接口继承Set接口
- Set接口继承Collection接口

### 3.3TreeSet操作

```java
import java.util.Set;
import java.util.TreeSet;
```

```java
public class Test{
	public static void main(String[] args){
		Set<Integer> set = new TreeSet<Integer>();
		
		//TreeSet自然排序
		set.add(3);
		set.add(1);
		set.add(2);
        //有序输出123
		System.out.println(set);
		
		set.remove(3);//移除元素
		set.contains(3);//判断集合中元素是否存在
		set.clear();//清空集合
        
        
    	//使用迭代器遍历集合
    	Iterator<Integer> it = set.iterator();//迭代器
    	while(it.hasNext()){//有下一个
            System.out.println(it.next());//输出
        }
    
    	//用for each迭代集合，推荐使用
    	for(Integer i : set){//在for过程中，把每一个set元素取出来赋值给i
            System.out.println(i);
        }
        
	}
}
```

### 3.4自然排序

**TreeSet会调用集合元素的*compareTo(Object obj)*方法来比较元素之间的大小关系，然后将集合元素按升序排列**

- 如果this>obj,返回正数1
- 如果this<obj,返回负数-1
- 如果this=obj,返回0，认为这两个数相等
- **必须放入同类型的对象**，否则可能会发生类型转换异常（**可以使用泛型来进行限制**）

### 3.5定制排序

用到Comparator接口

```java
class Person implements Comparator<Person>{	//存到TreeSet中并按照年龄排序
	int age;
	String name;
    public Person(){
        //无参构造器
    }
    
    public Person(String name,int age){
        this.name = name;
        this.age = age;
        //有参构造器
    }
    
    //重写Comparator接口的compare方法
    //比较对象年龄大小的方法
    //年龄正序排列
	public int compare(Person o1,Person o2){
		if(o1.age > o2.age){
			return 1;
		}else if(o1.age < o2.age){
            return -1;
        }else{
            return 0;
        }
	}
}
```

```java
public class Test{
	public static void main(String[] args){
		Person p1 = new Person("张三"，22)；
        Person p1 = new Person("李四"，20)；
        Person p3 = new Person("帅哥"，18)；
        Person p4 = new Person("Monstro"，28)；
        Set<Person> set = new TreeSet<Person>(new Person());
        
        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);
        
        for(Person p : set){
            System.out.println(p.name + "   " + p.age)
        }
	}
}
```

## 4.List与ArrayList

### 4.1List集合概述

- List代表一个元素有序、且可重复的集合，集合中的每个元素都有其对应的顺序索引

- List**允许使用重复元素**，可以通过索引来访问指定位置的集合元素

- List默认按元素的添加顺序来设置元素的索引

- List集合里添加了一些根据索引来操作集合元素的方法

### 4.2List实现接口

- ArrayList类实现List接口
- List接口继承Collection接口

### 4.3List操作

```java
import java.util.ArrayList;
import java.util.List;
```

```java
public class Test{
	public static void main(String[] args){
        List<String> list = new ArrayList<String>();
        
        list.add("b");//第一个，索引下标0
        list.add("d");
        list.add("a");
        list.add("c");//第四个，索引下标3
        list.add("a");//允许重复元素，索引下标5
        
        System.out.println(list);//按顺序存储
        System.out.println(list.get(2));//通过索引访问指定位置的集合元素
        
        lsit.add(1,"f");//在第一个位置插入f
        
        List<String> list2 = new ArrayList<String>();
        list2.add("123");
        list2.add("456");
        list.addAll(2,list2);//在list的2位置插入集合list2
        
        System.out.println(list.indexOf"a");//获取指定元素在集合中第一次出现的索引下标
        System.out.println(list.lastIndexOf"a");//获取指定元素在集合中最后一次出现的索引下标
        
        list.remove(2);//移除2位置的元素
        list.set(1,"ff");//将位置1的元素改成ff
        List<String> sublist = list.subList(2,4);//截取位置2到3的元素形成一个新的集合（[fromIndex,toIndex)）
        System.out.println(list.size());//集合的长度
        
    }
}
```

### 4.4ArrayList和Vector

> ArrayList和Vector是List接口的两个典型实现

区别：

- Vector年代久远，建议使用ArrayList
- ArrayList线程不安全，但也不推荐使用Vector

- > Vector是古老的集合，通常建议使用ArrayList

- ArrayList是线程不安全的

- 即使为保证List集合线程安全，也不推荐使用Vector

## 5.Map集合

### 5.1Map集合概述

Map是用于保存具有映射关系的数据：

- Map集合中保存两组值，一组值用于保存Map里的Key，一组值用于保存Map里的Value
- Map中的Key和Value都可以是任何引用类型的数据
- Map中的Key不允许重复（即任意两个Key用equals()方法比较返回false）
- Key和Value单向一对一（映射，通过key找到唯一的value）

### 5.2Map接口与HashMap类



### 5.3Map操作

```java
import java.util.HashMap;
import java.util.Map;
```

```java
public class Test{
	public static void main(String[] args){
		Map<String,Integer> map = HashMap<String,Integer>();
		map.put("b",1);//添加元素
		map.put("c",2);//Key必须互不相同
		map.put("e",2);//Value可以相同
        map.remove("e");//根据Key移除键值对
        map.clear();//清空集合
        
        
        System.out.println(map.get("b"));//根据Key取Value
		System.out.println(map.size());//map集合的长度
        System.out.println(map.containsKey("b"));//判断当前map集合是否包含指定的Key
        System.out.println(map.containsValue);//判断当前map集合是否包含指定的Value
        
        map.keySet();//获取map集合的Key集合
        map.values();//获取map集合的所有Value值
        
        //通过map.keySet()方法遍历map集合
        for(String key : keys){
            System.out.println("key: " + key + ", value: " + map.get(key));
        }
        //通过map.entrySet()方法遍历map集合,
        Set<Entry<String,Integer>> entrys = map.entrySet();
        for(Entry<String,Integer> en : entrys){
            System.out.println("key: " + en.getKey() + ", value: " + en.getValue());
        }
	}
}
```



### 5.3HashMap与Hashtable

HashMap和Hahtable是Map接口的两个典型实现类

区别：

- Hashtable年代久远，不建议使用
- HashMap线程不安全
- Hashtable线程安全，但仍不建议使用
- HashMap不允许使用null作为key和value，而Hashtable允许

相同点

- HashSet集合不保证元素顺序，HashMap和Hashtable也不保证key-value对的顺序
- HashMap和Hashtable判断两个Key相等的标准是：两个Key通过equals()方法返回true，hashCode值也相等

### 5.4 TreeMap

### 

TreeMap存储Key-Value对时，根据Key对Key-Value对进行排序。TreeMap可以保证所有的Key-Value对处于有序状态。

>  一般使用map集合，不会使用过于复杂的对象做Key。

```java
public class Test{
	public static void main(String[] args){
        //TreeMap的自然排序是字典排序
		Map<Integer,String> map = new TreeMap<Integer,String>();
        map.put(4,"a");
        map.put(2,"a");
        map.put(3,"a");
        map.put(1,"a");
        
        Map<String,String> map = new TreeMap<String,String>();
        map.put("a","a");
        map.put("b","b");
        map.put("ab","a");
        map.put("1","b");
        map.put("10","f");
​    }
}
```



## 6.操作集合的工具类：Collections

### 6.1Collections概述

- **Collections是一个操作Set、List和Map等集合的工具类**
- Collections中提供了 大量方法对集合元素进行排序、查询和修改等操作，还提供了对集合对象设置不可变、对集合对象实现同步控制等方法

### 6.2Collections操作

```
import java.util.List;
import java.util.
```



```java
public class Test{
	public static void main(String[] args){
        
		List<String> list = new ArrayList<String>();
        
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("cd");
        list.add("1");
        list.add("d");
        
        System.out.println(list);
        Collections.reverse(list);//反转List中元素的顺序
        Collections.shuffle(list);//对List中元素进行随机排序
        Collections.sort(list);//对List中元素进行字典升序排序
        
        Collections.swap(list,0,4);//调换List中两个元素
        System.out.println(Collections.frequency(list,"d"));//返回指定集合中指定元素的出现次数
        Collections.replaceAll(list,"a","aa");//使用新值替换list中所有旧值
        
        System.out.println(Collections.max(list));//返回list中最大元素
        System.out.println(Collections.min(list));//返回list中最小元素
        
        ----------------------------------------------------------------------------------------
        
        //创建四个年龄不同的Student对象
        Student s1 = new Student(38,"廖牛牛");
        Student s2 = new Student(8,"廖狗狗");
        Student s3 = new Student(14,"廖猪猪");
        Student s4 = new Student(18,"廖蠢蠢");
        
        List<Student> stus = new ArrayList<Student>();//创建List集合
        stus.add(s1);
        stus.add(s2);
        stus.add(s3);
        stus.add(s4);
        
        /*
        for(Student stu : stus){
            System.out.println(stu.name + "," + stu.age);
        }//遍历观察排序前的顺序:按照装入集合的顺序排序
        */
        
        //Collections.sort(List,Comparator)方法
        Collections.sort(stus,new Student());//按照指定的Comparator产生的顺序(就是重写的compare方法)对List集合元素进行排序
        
        Student smax = Collections.max(stus,new Student());//通过自定义的compare方法输出年龄最大的对象
        Student smin = Collections.min(stus,new Student());//通过自定义的compare方法输出年龄最小的对象
        
        ----------------------------------------------------------------------------------------
            
        //同步控制(没学线程,只做了解)
        //Collections类中提供了多个synchronizedXxx()方法,可使将指定集合包装成线程同步的集合,从而可以解决多线程并发访问集合时的线程安全问题
            
    }
}
```

```java
class Student implements Comparator<Student>{//继承Comparator接口,目的是重写compare方法
	int age;
	String name;
	
	public Student(){
	
	}
	
	public Student(int age,String name){
		this.age = age;
		this.name = name;
	}
	
	public int compare(Student o1,Stuent o2){//重写compare方法,通过返回值达到排序年龄的目的
		if(o1.age > o2.age){
			return 1;
		}else if(o1.age < o2.age){
            return -1;
        }else{
            return 0;
        }
}
```



# 二十七、泛型

## 1.为什么要有泛型Generic？

为了保证集合安全，满足限制集合类型的需求

> 注意，Java中的泛型，只在编译阶段有效（运行前就发现错误了,例如 list.add(true); 就是在编译期），泛型信息不会进入到运行阶段

## 2.泛型的使用

### 2.1泛型类

- 对象实例化时不指定泛型，默认为Object
- 泛型不同的引用不能相互赋值

```java
class A<T>{//规定输入类型为T
	//此处的泛型T可以任意取名，一般大写
    //一般使用T，意为type
    private T key;
    
    public void setKey(T key){
        this.key = key;
    }
    
    public T getKey(){
        return this.key;//注意：返回的是包装类
    }
}
```



```java
public class Test{
	A<String> a1 = new A<String>();//在new A的对象时指定泛型为String类
	a1.setKey("xxx");//对象使用setKey(T key)方法中的key形参就是String
	String s = a1.getKey();//T getKey()，new对象时确定返回值是String类型
    
    A a2 = new A();//不指定泛型，默认为Object类型
    a2.setKey(new Object());
    Object obj = a2.getKey();
    
    //同样的类，在new对象时泛型指定了不同的数据类型，则这些对象不能互相赋值
}
```

### 2.2泛型接口

```java
public class Test{
	public static void main(String[] args){
        //接口未指定泛型，则声明对象时需要指定泛型
		B1<Object> b1 = new B1(Object);
        B1<Integer> b11 = new B1(Integer);
        
        //接口已指定泛型，则声明对象时需不要指定泛型
		B2 b2 = new B2;
        B2 b22 = new B2;
	}
}
```

```java
interface IB<T>{//定义泛型接口
	T test(T t);
}
```

```java
//未传入泛型实参时，与泛型类的定义相同，在声明类时，需要将泛型类的声明也一起加到类中
//例如 class B1 implements IB<T>{} 就是错误的，应该改为 class B1<T> implements IB<T>{}
class B1<T> implements IB<T>{//实现接口
	//实现接口中的抽象方法
    public T test(T t){
        
    }
}

class B1<String> implements IB<String>{//指定了接口的泛型的具体数据类型，则这个类实现接口所有方法的位置都要泛型替换实际的具体数据类型
	//实现接口中的抽象方法
    public String test(String t){
        
    }
}
```

### 2.3泛型方法

- 泛型方法在调用前没有固定的数据类型
- 泛型方法会在调用时确定泛型的数据类型

```java
class Cc<E>{//泛型类
    private E e;
       
    //无返回值的泛型方法
	public <T> void test(T s){//泛型void方法
		T t = s;//使用泛型
        System.out.println(this.e);//类上定义的泛型可以在普通方法中使用，例外：在静态方法中不能使用
        //静态方法中，只能使用静态方法自己定义的泛型
	}
    
    public static <T> void test(T t){//静态方法的泛型
        //静态方法中，只能使用静态方法自己定义的泛型
        System.out.println(t);      
	}
	
    //有返回值的泛型方法 
	public <T> test1(T s){//泛型T方法，返回值为T类型
		return s;
	}
	
    //形参为可变参数的泛型方法
	public <T> void test2(T... strs){//可变参数类型的泛型方法
		for(T s : strs){//遍历strs数组，一一赋值给s
			System.out.println(s);//并打印s
		}
	}
}
```

```java
public class Test{
	public static void main(String[] args){
		Cc<Object> c = new Cc<Object>();
        //泛型方法，在调用前没有固定的数据类型
		//在调用时，传入的参数是什么类型，泛型就会固定为什么类型
        String s = c.test("xxx");//
        Boolean b = c.test1(true);//传递的参数是Boolean，泛型就顾定成Boolean，返回值就是Boolean
        
	}
}
```

### 2.4泛型通配符

- 不确定集合中元素具体的数据类型
- 使用？表示所有类型

```java
public class Test{
    public static void main(String[] args){
        Dd d = new Dd();
        List<String> l1 = new ArrayList<String>();
        d.test(l1);
    }
}
```

```java
class Dd{
	public void test(List<?> list){//test方法需要一个list集合的参数，但是不确定list集合存的数的数据类型是什么
	}
}
```

#### 2.4.1有限制的通配符

限制形参List中的元素类型

```java
public class Test{
    public static void main(String[] args){
        List<C1> lc = new ArrayList<C1>();
    	d.test1(lc);//C1
        List<D1> ld = new ArrayList<C1>();
    	d.test1(ld);//C1子类
        
    	d.test2(lc);//C1
        List<A1> la = new ArrayList<A1>();
    	d.test2(la);//C1父类
        
        List<IAImpl> lia = new ArrayList<IAImpl>();
        d.test3(lia);//IA实现类
    }
}
```

```java
class Dd{
    public void test1(List<? extends C1> list);{//list参数的元素数据类型是C1及其子类
        
    }
    public void test2(List<? super C1> list);{//list参数的元素数据类型是C1及其父类类
        
    }
    public void test3(List<? extends IA> list);{//list参数的元素数据类型是IA的实现类
        
    }
}

class A1{
    //父类
}
class B1 extends A1{
    //继承A1的子类
}
class C1 extends B1{
    //继承B1的子类
}
class D1 extends C1{
    //继承C1的子类
}
interface IA{
    //接口
}
class IAImpl implements IA{
    
} 
```

# 二十八、枚举

某些情况下，一个类的对象是有限而固定的（例如季节：春夏秋冬）

## 1.使用enum定义枚举类

### 1.1枚举类和普通类的区别

- **使用enum定义的枚举类默认继承了java.lang.Enum类**
- **枚举类的构造器只能使用private访问控制符**
- **枚举类的所有实例必须在枚举类中显式列出，列出的实例系统会自动添加 public static final修饰**
- **所有的枚举类都提供了一个values方法，该方法可以很方便地遍历所有的枚举值**

### 1.2单例模式；实现接口的枚举类

- 枚举类也可以实现一个或多个接口
- 若需要每个枚举值在调用实现的接口方法呈现出不同的行为方式，则可以让每个枚举值分别实现该方法

```java
public class Test{
	public static void main(String[] args){
        Season.SPRING spring = Season.SPRING;//获取枚举对象
        spring.showInfo();//调用枚举方法
        
        Season.SPRING spring1 = Season.SPRING;//获取枚举对象
        System.out.println(spring.equals(spring1));//返回true，说明每次执行Season.Spring获得的是相同的对象
        //单例模式，枚举类中的每个枚举都是单例
        
        spring.test();
    }
}
```

```java
eunm Season implements{//枚举类实现接口
    public void test(){
            System.out.println("实现ITest方法 的test()方法");
        }
    
	SPRING("春天","？"),
	SUMMER("夏天","，"),//每一个枚举
	AUTUMN("秋天","！"),//相当于调用有参私有构造方法
	WINTER("冬天","。");//传两个实参
	
	private final String name;
    private final String desc;
    
    private Season(String name,String desc){//构造器
        this.name = name;
        this.desc = desc;
    }
    
    public void showInfo(){
        System.out.println(this.name = ":" + this.desc);
    }
}
```

```java
interface ITest{
	void test();
}
```

### 1.3枚举类的方法



## 2.注解（Annotation）

> 从JD5.0开始，Java增加了对元数据的支持，也就是注释，有利于在源文件中嵌入一些补充信息。

### 2.1基本的Annotation

**使用Annotation时要在其前面增加@符号，并把该Annotation当成一个修饰符使用，修饰它支持的程序元素**

- **@Override：重写父类方法**
- **@Deprecated：用以表示某个程序元素已过时**（方法名自动被划删除线，表示不推荐用）
- **@SuppressWarnings：抑制编译器警告**（消除警告）

### 2.2自定义Annotation

- **使用@interface关键字来定义新的Annotation类型**

```java
class Test{
    @TestAnn(id=100,desc"姓名")//添加注解和注解属性
	String name;
}
```



```java
@Target(ElementType.FIELD)//声明这个注解类是给其他类的属性做注解
@Retention(RetentionPolicy.RUNTIME)//定义注解的生命周期
@Documented//将注解放到文档中
@interface TestAnn{
	//编写注解属性
    public int id() default 0;//当注解不填的时候默认值为0
    public String desc() default "";//当注解不填的时候默认值为""
}
```

# 二十九、IO流

## 1.java.io.File类的使用

### 1.1File类

- **File能新建、删除、重命名文件和目录，但File不能访问文件内容本身。如果需要访问文件内容本身，则需要使用输入/输出流。**
- **File对象可以作为参数传递给流的构造函数**

```java
import java.io.File;
```

- 访问文件名：

```java
public class Test{
	public static void main(String[] args){
		File f = new File("D:\\test\\abc\\tt.txt");//对象f代表tt.txt文件
        File f1 = new File("D:/test/abc/tt.txt");//对象f1也代表tt.txt文件 
        File f2 = new File("D:\\test","\\abc\\tt.txt");//对象f2也代表tt.txt
        File f3 = new File("D:"+ File.separator + "\\test\\abc\\tt.txt");//对象f3也代表tt.txt
        File f4 = new File("D:\\test\\abc");//对象f4代表此目录
        File f5 = new File("src/day12/Test.java");//使用相对路径创建File对象
        System.out.println(f.getName());//获取文件名
        System.out.println(f4.getName());//获取当前的文件夹名称
        System.out.println(f5.getPath());//获取文件或者文件夹的绝对路径
        System.out.println(f5.getAbsolutePath());//获取文件或者文件夹的相对路径
        System.out.println(f5;//返回一个用当前文件的相对路径构建的File对象
        System.out.println(f5.getAbsoluteFile());//返回一个用当前文件的绝对路径构建的File对象
        System.out.println(f5.getParent());//返回当前文件/文件夹的父级路径
        f.renameTo(new File("D:\\test\\abc\\tt1.txt"));//当前文件/文件夹重命名
```

- 文件检测

​        

```java
File f6 = new File("D:\\test\\abc");
        System.out.println(f6.exists());//判断文件/文件夹是否存在
File f7 = new File("D:\\test\\abc\\tt1.txt");
//右键文件-属性，可以调整可读可写状态
System.out.println(f7.canWrite());//判断文件是否可写
System.out.println(f7.canRead());//判断文件是否可读
System.out.println(f7.isFile());//判断当前File对象是不是文件
System.out.println(f7.isDirectory());//判断当前File对象是不是文件夹
System.out.println(f7.lastModified());//获取文件的最后修改时间，返回毫秒数
System.out.println(f7.length());//返回文件的长度，以字节数为单位
```

- 文件操作相关

```java
		File f8 = new File("D:\\test\\abc\\tt2.txt");
		System.out.println(f8.exists());//判断文件是否存在
		if(!f8.exists()){//如果文件不存在
        	try{
            	f8.createNewFile();//创建新文件
        	}catch(IOException e){
            	e.printStackTrace();
        	}        
    	}
		f8.delete();//删除文件
		File f9 = new File("D:\\test\\abc\\cc");
		f9.mkdir();//创建单层目录
		File f10 = new File("D:\\test\\abc\\a\\b\\c");
		f9.mkdirs();//创建多层目录
		File f11 = new File("D:\\test");
		String[] fl = f11.list();
		for(String s : f1){
            System.out.println(s);//返回当前文件夹下所有子集的名称，包括文件/文件夹
        }
        File[] fs = f11.listFiles();      	   for(fILE f : fs){
            System.out.println(ff);//返回当前文件夹下所有子集的File对象，包括文件/文件夹
        }
	}
}
```

### 1.2File类递归遍历文件

###### mark:P100day12 3



## 2.IO原理以及流的分类

### 2.1Java IO原理: input&output

- **io流用来处理设备之间的数据传输。**
- **Java程序中，对于数据的输入/输出操作以”流(stream)“的方式进行**
- **java.io包下提供了各种"流"类和接口,用以获取不同种类的数据,并通过标准的方法输入或输出数据**

### 2.2流的分类

- 按操作**数据单位**不同分为：**字节流(8 bit) / 字符流(16bit)**
- 按数据流的**流向**不同分为：**输入流 / 输出流**
- 按流的**角色**的不同分为：**节点流 / 处理流**

|  抽象基类  |    字节流    | 字符流 |
| :--------: | :----------: | :----: |
| **输入流** | InputStream  | Reader |
| **输出流** | OutputStream | Writer |

- Java的IO流涉及40多类，都是由如上四个抽象基类派生出的

## 3.文件流

数据流的读写基于文件的操作

### 3.1文件字节输入流

```java
import java.io.FileInputStream;
```

```java
public static void testFileInputStream(){//文件字节输入流
        FileInputStream in = new FileInputStream("D:/testabc/tt1.txt");
        byte[] b = new byte[10];//设置一个byte数组，接受读取的文件的内容
        int len = 0；//设置读取数据的长度
        //in.read(b);//in.read()方法有一个返回值，读取数据的长度，如果读取到最后一个数据，还会向后读一个，这时候返回值就是-1
        //也就意味着，当in.read的返回值是-1的时候，整个文件就读取完毕了
        while(len = in.read(b) != -1){
            System.out.println(new String(b,0,len));//缓冲数据的数组，从第0个位置读起，总共转换len个数据，转换为字符串再输出
        }
        in.close();//注意，流在使用完毕后要关闭
}  
```

### 3.2文件字节输出流

```java
public static void testFileOuputStream(){//文件字节输出流
    try{
        FileOutputStream out = new FileOutputStream("D:/test/abc/tt4.txt");//指定向tt4.txt输出数据
    	String str = "Urgot";
    	out.write(str.getBytes());//转换成Byte数组，write是把数据写到内存中
        out.flush();//把内存的数据刷写到硬盘
        out.close();//使用完毕，关闭流
    }catch(Exception e){
        e.printStackTrace();
    }
}
```



```java
public class Test{
	public static void main(String[] args){
		Test.testFileOutputStream();
	}
}
```

### 3.3文件字节流练习

编写一个程序，把一个文件复制到指定文件夹下

```java
import java.io.FileInputStream;
import java.io.FileNotFoundException;//异常捕获需要
import java.io.FileOutputStream;
```

```java
public class Test{//字节流使用二进制
	public static void main(String[] args){
		Test.copyFile("D:/test/abc/tt1.txt","D:/test/abc/cc/tt11.txt");//调用下面写的文件复制方法，压缩包也可以
        //复制图片也可以，这个100byte是每次只读100byte传多次？
	}
}
```

```java
public static void copyFile(String inPath,String outPath){//设置参数就方便传入了
	try{
   		FileInputStream in = new FileInputStream(inPath);//读取的源文件（读入程序）
        FileOutputStream out = new FileOutputStream(outPath);//赋值的文件（程序输出）
        byte[] b = new byte[100];
        
        int len = 0;
        
        while(len = in.read(b) != -1){
            out.write(b,0,len);//写入缓冲数组b，从数组0位置开始写，获取数组总共len长度
        }
        
        out.flush();//把写到内存的数据刷到硬盘
        out.close();//记得关闭流！！
        in.close();//记得关闭流！！
        
    }catch (Exception e){
        e.printStackTrace();
    }
}
```

### 3.4文件字符输入流

## 4.缓冲流

数据流的读写基于内存的操作

## 5.其他流合集

### 5.1转换流

InputStreamReader/OutputStreamWriter

### 5.2标准输入/输出流



```java
public class Test{
	public static void main(String[] args){
        try{
            Test.testSystemInput();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void testSystemInput() throws Exception{
        //创建一个接收键盘输入数据的输入流
        InputStreamReader is = new InputStreamReader(System.in);
        //把输入流放到缓冲流里
        BufferedReader br = new BufferedReader(is);
        
        String str = "";//定义一个临时接收数据的字符串
        
        while((str = br.readLine()) != null){
            System.out.println(str);
        }
    }
}
```



### 5.3打印流

PrintStream / PrintWriter

System.out.println

### 5.4对象流

把一个对象转化为一个数据流进行读写

涉及序列化、反序列化

### 5.5随机存取文件流

RandomAccessFile

### 5.6数据流