<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="js/register.js"></script>
<script type="text/javascript" src="js/register.js"></script>
<title>Insert title here</title>

</head>
<body>
	<div class="dvChild">
	<form name="form0" id="form0" action="/WEB32/fileuploadServlet2" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>
					上传头像
				</td>
				<td>
					<input type="file" name="filename"><br/>
				</td>
			</tr>
			<tr>
				<td>
					姓名
				</td>
				<td>
					<input type="text" name="username" id="username" onfocusout="verify(username)"><br/>
				</td>
				<td>
				<span id="usernames"></span>
				</td>
			</tr>
			<tr>
				<td>
					年龄
				</td>
				<td>
					<input type="text" name="age" id="age"><br/>
				</td>
				<td>
				<span id="ages"></span>
				</td>
			</tr>
			<tr>
				<td>
					密码
				</td>
				<td>
					<input type="password" name="password" id="password" onfocusout="verify(password)"><br/>
				</td>
				<td>
				<span id="passwords"></span>
				</td>
			</tr>
			<tr>
				<td>
					电话号码
				</td>
				<td>
					<input type="number" name="phone" id="phone"><br/>
				</td>
				<td id="phones"></td>
			</tr>
			<tr>
				<td>
					个人简介
				</td>
				<td>
					<input type="text" name="intro" id="intro"><br/>
				</td>
				<td id="intros"></td>
			</tr>
			<tr>
				<td>
					上支效力的队伍
				</td>
				<td>
					<input type="text" name="lastTeam" id="lastTeam"><br/>
				</td>
				<td id="lastTeams"></td>
			</tr>
			<tr>
				<td>
					加入lpl的时间
				</td>
				<td>
					<input type="text" name="joinTime" id="joinTime"><br/>
				</td>
				<td id="joinTimes"></td>
			</tr>
			<tr>
				<td>
					邮箱
				</td>
				<td>
					<input type="email" name="email" id="email"><br/>
				</td>
				<td>
					<button id="btnGetVcode" type="button"  >获取验证码</button>
				</td>
				<td id="emails"></td>
			</tr>
			<tr height="35px">
				<td>
					验证码
				</td>
				<td>
					<input type="text" name="vcode" id="vcode" placeholder="输入验证码"><br/>
				</td>
				<td id="message">
				
				</td>
				<td></td>
			</tr>
		</table>
		<a target="_self">
			<button type="button" id="btnVerify" style="cursor:pointer">验证</button>
			</a>
			<input type="submit" value="提交注册信息" id="btnSubmit"  />
			
			<input type="button" value="已经有账号？" style="height:21px;width:100px;" onclick="javascript:window.location.href='http://localhost:8080/WEB32/jsp/login.jsp'" />
		
	</form>
	</div>
	
	<script type="text/javascript">
             
            var ID = "";
            function verify(ID) {
            //设置一个ID用来传参
                switch (ID) {
                //根据传过来的input的"ID"判断进行不同的正则表达式验证
                    //用户名验证
                    case username:
                        //用户名的正则表达式
                        var UserNameRule = /^[A-Z][a-zA-Z0-9_]{6,20}$/;
                        //调用下面的编写的js方法,传入input的id和正则表达式
                        reg("username", UserNameRule);
                        break;
 
                    case password:
                        //密码验证
                        var pwdRule = /^[A-Z][a-zA-Z0-9_]{8,15}$/;
                        reg("password", pwdRule);
                        break;
 
                    case affirmPwd:
                        //密码确认验证
                        var affirmPwdRule = /^[A-Z][a-zA-Z0-9_]{8,15}$/;
                        reg("affirmPwd", affirmPwdRule);
                        break;
 
                    case email:
                        //邮箱验证
                        var emailRule = /^[A-z0-9]+@[a-z0-9]+.com$/;
                        reg("email", emailRule);
                        break;
 
                    case phone:
                        // 手机号验证
                        var cellPhoneRule = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
                        reg("phone", cellPhoneRule);
                        break;
 
                    case identityCard:
                        //身份证号验证
                        var identityCardRule = /^([1-9]){1}[0-9]{17}|[1-9]{1}[0-9]{16}(x|X)?$/;
                        reg("identityCard", identityCardRule);
                        break;
 
                    case site:
                        //地址验证
                        var siteRule = /^[\u4e00-\u9fa5]+[\u4E00-\u9FA5A-Za-z0-9_]+$/;
                        reg("site", siteRule);
                        break;
                    //设置特殊情况
                    default:
                        alert("操作错误！请关闭网页")
                        break;
                }
            }
            //提交验证判断是否都符合正则表达式
            function submit(){ 
            	var form0 = document.getElementById("form0");
            	
            //获取所有的span标签
                var a = document.getElementsByTagName("span");
                var str = "";      
                var btnSubmit = document.getElementById("btnSubmit");
            //循环获取span的内容
                for (var i = 0; i < a.length; i++) {     
                    str+=a[i].innerText;                
                }   
                if(str == "√√"){
                	btnSubmit.disabled = false;
                    alert("输入正确");
                }else{
                	btnSubmit.disabled = true;
                    alert("输入错误");
                }
            	
            }       
        </script>
        
        
	
	<!-- 引入jQuery -->
    <script type="text/javascript" src="js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="js/jsEmail.js"></script>

</body>
</html>