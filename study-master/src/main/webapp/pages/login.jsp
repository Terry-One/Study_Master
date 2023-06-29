<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
	<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+
    	request.getServerPort()+path;
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
<meta content="MSHTML 6.00.2600.0" name=GENERATOR>
<title>登录页面</title>
	<link href="<%= basePath %>/static/css/layui.css" type=text/css rel=stylesheet>
	<link rel="stylesheet" href="<%=basePath %>/static/css/bootstrap.min.css"  media="all">
	<script src="<%=basePath %>/static/jquery-1.11.3.min.js"></script>
	<script src="<%=basePath %>/static/bootstrap.min.js"></script>
</head>
<body background="<%=basePath %>/static/images/backgroud.png" 
			style="background-size:cover;">
	<div ALIGN="center">
		<table border="0" width="440px" cellspacing="0" cellpadding="0" id="table1">
			<tr>
				<td height="193"></td>
			</tr>
			<tr>
				<td class="login_msg" width="400" align="center">
					<!-- margin:0px auto; 控制当前标签居中 -->
					<fieldset style="width: auto; margin: 0px auto;">
						<legend>
							<font style="font-size:25px;" face="宋体" color="#fff">
							学习系统
							</font>
						</legend>
							<font color="red">
								<%-- 提示信息--%>
								<span id="message">${msg}</span>
							</font>

						<form action="<%= basePath %>/login" method="post" 
									onsubmit="return check()">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<br />
								<label align="right" style="font: '微软雅黑'; font-size: large" 
									class="col-sm-4 control-label">账&nbsp;&nbsp;号</label>
								<div class="col-sm-6">
									<input class="form-control" 
										id="name" type="text" name="name" placeholder="请输入账号"/>
									</div>
							<br /><br /><br>
								<label align="right" style="font: '微软雅黑'; font-size: large" 
									class="col-sm-4 control-label">密&nbsp;&nbsp;码</label>
								<div class="col-sm-6">
									<input class="form-control" 
										id="passwd" type="password" name="passwd" placeholder="请输入密码"/>
								</div>
							<br /><br />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<center>
								<input class="layui-btn layui-btn-normal" 
									type="submit" value="登录" />
								&nbsp;&nbsp;
								<input class="layui-btn layui-btn-normal" 
									type="reset" value="重置" />
								&nbsp;&nbsp;
								<a class="layui-btn layui-btn-normal" href="login#" 
								data-toggle="modal" 
								data-target="#newUserDialog" onclick="clearData()">注册</a>
							</center>
						</form>
					</fieldset>
				</td>
			</tr>
		</table>
	</div>
	
	
	<!-- 注册部分 -->
	<div class="modal fade" id="newUserDialog" tabindex="-1" role="dialog" 
				aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新建用户信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="new_user_form">
						<div class="form-group">
							<label for="new_name" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="new_name" 
								placeholder="用户名长度为5-8" name="name">
							</div>
							<label for="new_passwd" class="col-sm-2 control-label">密  码</label>
							<div class="col-sm-4">
								<input type="password" class="form-control" id="new_passwd" 
								name="passwd" placeholder="密码需超过8个字符">
							</div>
						</div>
						
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="layui-btn layui-btn-normal" 
							data-dismiss="modal">关闭</button>
					<button type="button" class="layui-btn layui-btn-normal" 
							onclick="sign_in()">创建</button>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	
		// 判断是登录账号和密码是否为空
		function check(){
			var name = $("#name").val();
			var passwd = $("#passwd").val();
			if(name=="" || passwd==""){
				$("#message").text("账号或密码不能为空！");
				return false;
			}
			return true;
		}
	
		/* 清空新建用户窗口中的数据 */
		function clearData() { 
			$("#new_name").val("");
			$("#new_passwd").val("");
		}
		
		/* 创建新用户  */
		function sign_in() {
			$.post("join",
			$("#new_user_form").serialize(),function(data){
				console.log(data);
				if(data == "OK"){
					alert("用户创建成功！");
					window.location.reload();
				}else if(data == "nullError"){
					alert("用户名或密码不能为空，请重新注册！");
					window.location.reload();
				}else if(data == "nameFormatError"){
					alert("用户名字符格式错误，请重新注册！");
					window.location.reload();
				}else if(data == "nameError"){
					alert("用户名已存在，请重新注册！");
					window.location.reload();
				}else {
					alert("密码格式错误，请重新注册！");
					window.location.reload();
				}
			});
		}
	
	</script>
	
	
</body>
</html>