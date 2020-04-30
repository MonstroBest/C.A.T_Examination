[TOC]

# 一、网页

## 网页

- 网站是许多网页的集合，网页是构成网站的基本元素
- 网页由图片、链接、文字、声音、视频等元素组成

## HTML

- HTML：超文本标记语言，描述网页的标记标签

## 浏览器

- > 浏览器内核：渲染引擎，负责读取网页内容，整理讯息，计算网页的显示方式并显示内容

## Web标准

- 构成：结构HTML、表现CSS、行为JavaScript

## 标签

# 二、HTML

## HTML语法规范

- 由一对尖括号包围
- 大部分成对出现（双标签）
- 少部分单标签

### 标签关系

- 包含关系
- 并列关系

## HTML基本结构标签

### 骨架标签

| 标签名  | 定义     | 说明               |
| ------- | -------- | ------------------ |
| <html>  |          | 根标签             |
| <head>  | 文档头部 | 要有title标签      |
| <title> | 文档标题 | 网页标题           |
| <body>  | 文档主体 | 包含文档的所有内容 |

## 开发工具

VSCode

### VSCode工具生成骨架标签新增代码

- **<!DOCTYPE>**：文档类型声明，HTML5版本显示

- **<html lang="en/zh-CN/fr">：**当前网页是英文/中文/法文网页
- **<meta charset="UTF-8">：**字符集，万国码

# 三、HTML常用标签

## 1. 标题标签 

- **<h1> - <h6>**

## 2. 段落和换行标签

- **<p> - </p>**

- <br />

## 3. 文本格式化标签

- **< strong> - </strong>**
- **< em> - </em>**

- <del> - <del>
- < ins> - </ins>

## 4. 盒子类布局标签

- < div> 分区标签，大盒子 
- < span> 跨距标签，小盒子

## 5. 图像标签

- <img src="图像URL" />

- src：文件的路径

- 图像标签的其他属性：

| 属性   | 属性值   | 说明                                   |
| ------ | -------- | -------------------------------------- |
| src    | 图片路径 | 必须有                                 |
| alt    | 文本     | 替换文本，图像不能正常显示时显示的文字 |
| title  | 文本     | 提示文本，鼠标放在图像上，显示的文字   |
| width  | 像素     | 设置图像的宽度                         |
| height | 像素     | 设置图像的高度                         |
| border | 像素     | 设置图像的边框粗细                     |

- <img src="" border="10" height="300" />：键值对形式，属性间没有先后顺序

## 6. 路径

### 6.1 目录文件夹和根目录

- 目录文件夹：普通文件夹
- 目录文件夹的第一层就是根目录

###  6.2 相对路径

- **“以引用文件所在位置为参考基础建立的目录路径”**

- 

- | 路径       | 符号 | 说明                           |
  | ---------- | ---- | ------------------------------ |
  | 同一级路径 |      | <img src="baidu.gif" />        |
  | 下一级路径 | /    | <img src="images/baidu.gif" /> |
  | 上一级路径 | ../  | <img src="../baidu.gif" />     |

### 6.3 绝对路径

- **“目录下的绝对位置，从盘符开始读起”**
- 引用其他网址的图片：
- <img src="http://www.itcast.cn/2018czgw/images/logo.png" />

## 7. 超链接标签

- < a> </a>

| 属性   | 作用                                                         |
| ------ | ------------------------------------------------------------ |
| href   | 用于指定链接目标的url地址                                    |
| target | 指定链接页面的打开方式，___self为默认值，___blank为在新窗口中打开方式 |

- 外部链接
- 内部链接：同一目录下的链接

```html
<h4>空链接：#</h4>
<a href="#" blank="_blank">公司地址</a>
```

- 空链接：

  ```html
  <h4>空链接：#</h4>
  <a href="#" blank="_blank">公司地址</a>
  ```

- 下载链接：

```html
<h5>下载压缩包zip、exe等</h5>
<a href="img.zip">下载压缩包</a>
```

- 网页元素链接：文本、图像、表格、音频、视频等

```html
<a href="http://www.baidu.com"><img src="img.jpg"/></a>
```

- 锚点链接：快速定位到页面的某个位置
  - 在链接文本的href属性中，设置属性值为#名字的形式
  - 找到目标位置标签，里面添加一个id属性=刚才的名字

```html
<a href="#live">个人生活</a><br />

...

<h3 id="live">个人生活</h3>
```

## 8. 注释标签和特殊字符

```html
<!-- 这是注释 -->
<!-- 快捷键：ctrl+/ -->
&nbsp;<!-- 空格符 -->
&gt;&lt;<!-- 大于、小于号 -->
&amp;<!-- 和号 -->
&yen;<!-- 人民币 -->
&copy;<!-- 版权 -->
&reg;<!-- 注册商标 -->
&deg;<!-- 摄氏度 -->
&plusmn;<!-- 正负号 -->
&times;<!-- 乘号 -->
&divide;<!-- 除号 -->
&sup2;<!-- 平方2 -->
&sup3;<!-- 立方3 -->
```

## 9. 表格标签

```
<table>
	<tr><!-- 定义表格的行 -->
		<td> 定义单元格（没有列的概念） </td>
		...
	</tr>
</table>
```

## 10. 表头单元格标签

加粗居中显示

```html
<table>
	<tr><!-- 定义表格的行 -->
        <th> 姓名 </th>
		...
	</tr>
</table>
```

## 11.表格属性

表格属性写到table标签里：

| 属性名      | 属性值            | 描述                                  |
| ----------- | ----------------- | ------------------------------------- |
| align       | left/right/center | 表格相对周围元素的对齐方式            |
| border      | “”/1              | 是否拥有边框                          |
| cellpadding | 像素值            | 单元边沿与其内容之间的空白，默认1像素 |
| cellspacing | 像素值            | 单元格之间的空白，默认2像素           |
| width       | 像素值/百分比     | 规定表格的宽度                        |

## 12. 表格结构标签

将表格分割成表格头部<thread>和表格主体<tbody>两大部分:

```html
<table>

<thread>
</thread>

<tbody>
</tbody>

</table>
```

## 13. 合并单元格

#### 合并单元格的方式

- 跨行合并：rowspan="合并单元格的个数"
- 跨列合并：colspan=“合并单元格”

#### 目标单元格

- 按向下合并和向右合并原则，选择目标单元格进行设置

#### 三步

- 确定合并对象
- 找到目标单元格进行设置，如<td colspan="2"></td>

- 删除多余的单元格

## 14. 无序列表标签

> 列表用来布局

```html
<ul>	<!-- ul里只能包含li -->
    <li>
    	列表项1
    	<!-- li相当于一个容器，中间可以放任何元素 -->
    </li>
    <li>列表项2</li>
    <li>列表项3</li>
    ...
</ul>
```

## 15. 有序列表标签

```html
<ol>
	<li>刘德华</li>
	<li>流鼻涕</li>
	...
</ol>
```

## 16. 自定义列表

```html
<dl>	<!-- dl里只能有dt和dd -->
	<dt>名词1</dt>
    <dt>名词1的解释1</dt>
    <dt>名词1的解释2</dt>
    ...
    <dt>名词2</dt>
    <dt>名词2的解释1</dt>
    <dt>名词2的解释2</dt>
    ...
</dl>
```

## 17. 表单标签

表单用于收集用户信息，分为：

- 表单域：包含表单元素的区域，<form>定义表单域，把表单元素信息提交给服务器
- 表单控件（元素）
- 提示信息

### 17.1 表单域

```html
<form action="接收数据的服务器程序的url地址" method="get/post,表单数据的提交方式" name="表单名称">
```

### 17.2 表单元素

#### a. input输入表单元素

```html
<input type="属性值" name="名称" value="值" checked="首次加载被选中" maxlength="输入字符的最大长度" />
```

| 属性值   | 描述                                                         |
| -------- | ------------------------------------------------------------ |
| button   | 定义可点击按钮（多数情况下 用于通过JavaScript启动脚本）      |
| checkbox | 定义复选框                                                   |
| file     | 定义输入字段和浏览按钮，供文件上传                           |
| hidden   | 定义隐藏的输入字段                                           |
| image    | 定义图像形式的提交按钮                                       |
| password | 定义密码字段（***）                                          |
| radio    | 定义单选按钮                                                 |
| reset    | 定义重置按钮，重置按钮会清除表单中的所有数据                 |
| submit   | 定义提交按钮，提交按钮会把表单数据发送到服务器               |
| text     | 定义单行的输入字段，用户可在其中输入文本，默认宽度为20个字符 |

```html
<form action="xxx.php" method="post">
	账号<input type="text" name="username" value="请输入用户名" maxlength="10" />
    <!-- 清除的是所有input元素 -->
    清除<input type="reset" value="清除" />
	密码<input type="password" name="password" value="" maxlength="20" />
    男<input type="radio" name="sex" value="男" />
    女<input type="radio" name="sex" value="女" />
    爱好<input type="checkbox" name="hobby" value="吃饭" checked="checked" />
    提交<input type="submit" value="注册" />
    <!-- 提交value -->
    按钮<input type="button" value="获取短信验证码" />
    文件域<input type="file" />
    
</form>
```

#### b. select下拉表单标签

```html
<select>
	<option>选项一</option>
    <option select="selected">选项二（设置默认选定）</option>
    <option>选项三</option>
</select>
```

#### c. textarea文本域表单标签

```html
<textarea rows="3" cols="20">文字内容</textarea>
```



## 18. 标注标签

- <label>：点字也能获得光标

```html
<label for="sex">男</label>
<input type="radio" id="sex" />
```

## 19. 查阅文档

w3school / https://developer.mozilla.org/zh-CN/



# 四、CSS

- CSS：层叠样式表

- CSS也是一种标记语言

- CSS样式规则：选择器以及一条或多条声明

- <style></style>：写在<head></head>之中

```html
<head>
    <style>
    p {
    	color: green
    }
    </style>
</head>
```

- 格式：给谁改样式 { 改什么样式; ... }
- 代码风格：紧凑型/展开型格式，小写规范，空格规范

##  CSS选择器

选择器，选择标签

### 基础选择器

- 标签选择器：只能改某一类标签（所有）
- 类选择器：用class属性

```css
<head>
    <style>
		.red {
	    	color: red;
		}
		.star-sing {
    		color: green;
		}
    </style>
</head>

<body>
	<ul>
		<li class="red">A</li>
		<li>B</li>
		<li>C</li>
	</ul>
</body>
```

- id选择器
- 通配符选择器





# 五、JavaScript

## 1. JavaScript

运行在客户端的脚本语言，不需要编译

- 浏览器分成两部分：
  - 渲染引擎：内核，解析HTML与CSS
  - JS引擎：JS解释器，用来读取网页中的JavaScript代码，对其处理后运行

- JS的组成：
  - ECMAScript： JavaScript语法
  - DOM：文档对象模型
  - BOM：浏览器对象模型

## 2. 

- 行内式的JS，直接写到元素内部
- 内嵌式的JS
- 外部式的JS： JS文件

```html
<script src="my.js"></script>
```

- JS中推荐用单引号

- JS注释：ctrl+/，shift+alt+a

## 3. JavaScript输入输出语句

```html
<script>
    prompt('请输入您的年龄');//输入框
    alert('结果是');//弹出警示框
    console.log('我是bug');//控制台输出
</script>
```

## 4. 变量

- 用于存放数据的容器，通过变量名获取数据、修改数据
- 使用变量：1.声明         2.赋值

```javascript
<script>
    var myname = prompt('请输入您的名字'),
	    address = '州广';
	alert(myname);
</script>
```

- ![1588169427131](C:\Users\wj\AppData\Roaming\Typora\typora-user-images\1588169427131.png)

- ![1588169535295](C:\Users\wj\AppData\Roaming\Typora\typora-user-images\1588169535295.png)

- JS 弱类型语言，自动确定数据类型
- var