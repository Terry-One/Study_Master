<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+
    	request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>系统信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>首页</title>
	<link rel="stylesheet" href="<%=basePath %>/static/css/layui.css"  media="all">
	<link rel="stylesheet" href="<%=basePath %>/static/css/bootstrap.min.css"  media="all">
</head>
<body class="layui-layout-body">

	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
	    	<div class="layui-logo">
	    		<img src="<%=basePath %>/static/images/logo_hj.png">
	    	</div>
	    
		    <!-- 头部左侧区域（可配合layui已有的水平导航） -->
		    <ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a href="<%=basePath %>/study/index">首页</a></li>
				<li class="layui-nav-item"><a href="<%=basePath %>/study/user?name=${USER_SESSION.name}">用户</a></li>
				<li class="layui-nav-item">
					<a href="javascript:;">其它系统</a>
					<dl class="layui-nav-child">
				    	<dd><a href="<%=basePath %>/study/manager">权限管理</a></dd>
					</dl>
				</li>
		    </ul>
		    
		    <!-- 头部右侧区域 -->
		    <ul class="layui-nav layui-layout-right">
		      	<li class="layui-nav-item">
		        	<a href="javascript:;">
		          		<img src="<%=basePath %>/static/images/face/10.gif" class="layui-nav-img">
		          		${USER_SESSION.name}
		        	</a>
		        	<dl class="layui-nav-child">
		          		<dd><a href="<%=basePath %>/study/user?name=${USER_SESSION.name}">基本资料</a></dd>
		        	</dl>
		     	</li>
		      	<li class="layui-nav-item"><a href="<%=basePath %>/logout">注销</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed"><a class=""
						href="javascript:;">代码库</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="<%=basePath%>/study/code/code-info">代码库信息概览</a>
							</dd>
							<dd>
								<a href="<%=basePath%>/study/code/code-manager">代码库管理</a>
							</dd>
						</dl>
					</li>
					
					<li class="layui-nav-item layui-nav-itemed"><a class=""
						href="javascript:;">镜像库</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="<%=basePath%>/study/mirror/mirrot-info">镜像库信息概览</a>
							</dd>
							<dd>
								<a href="<%=basePath%>/study/mirror/mirror-manager">软件库管理</a>
							</dd>
						</dl>
					</li>
					
					<li class="layui-nav-item layui-nav-itemed"><a class=""
						href="javascript:;">软件库</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="<%=basePath%>/study/software/software-info">软件库信息概览</a>
							</dd>
							<dd>
								<a href="<%=basePath%>/study/software/software-manager">软件库管理</a>
							</dd>
						</dl>
					</li>

					<li class="layui-nav-item"><a href="javascript:;">视频库</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="<%=basePath%>/study/video/video-info">视频库信息概览</a>
							</dd>
							<dd>
								<a href="<%=basePath%>/study/video/video-manager">视频库管理</a>
							</dd>
							<dd>
								<a href="<%=basePath%>/movie/video/video-online">在线视频</a>
							</dd>
						</dl>
					</li>

					<li class="layui-nav-item"><a href="javascript:;">文档库</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="<%=basePath%>/study/doc/doc-info">文档库信息概览</a>
							</dd>
							<dd>
								<a href="<%=basePath%>/study/doc/doc-manager">文档库管理</a>
							</dd>
							<dd>
								<a href="<%=basePath%>/movie/doc/doc-edit">在线文档</a>
							</dd>
						</dl>
					</li>
				</ul>
			</div>
		</div>

		<div class="layui-body" style="background:#D1EEEE;">
	    	<!-- 内容主体区域 -->
 			<div style="background-size:cover;padding: 15px;  color:#DC143C" ALIGN="center">
 			
				<h2>用户信息页面</h2>
    				
    				
  			</div>
  			<br>
  			<div class="layui-row" >
	    		<div class="layui-row">
	    			<div class="layui-col-md2">
	    				<p align="right" style="font: '微软雅黑'; font-size: large">
	    				昵称：&nbsp;&nbsp;&nbsp;
	    				</p>
	    			</div>
	    			<div class="layui-col-md5" >
	    				<p style="font: '微软雅黑'; font-size: large">
	    				${user.nickname}
	    				</p>
	    			</div>
	    		</div>
	    		<div class="layui-row">
	    			<div class="layui-col-md2">
	    				<p align="right" style="font: '微软雅黑'; font-size: large">
	    				性别：&nbsp;&nbsp;&nbsp;
	    				</p>
	    			</div>
	    			<div class="layui-col-md5" >
	    				<p style="font: '微软雅黑'; font-size: large">
	    				${user.sex}
	    				</p>
	    			</div>
	    		</div>
	    		<div class="layui-row">
	    			<div class="layui-col-md2">
	    				<p align="right" style="font: '微软雅黑'; font-size: large">
	    				年龄：&nbsp;&nbsp;&nbsp;
	    				</p>
	    			</div>
	    			<div class="layui-col-md5" >
	    				<p style="font: '微软雅黑'; font-size: large">
	    				${user.age}
	    				</p>
	    			</div>
	    		</div>
	    		<div class="layui-row">
	    			<div class="layui-col-md2">
	    				<p align="right" style="font: '微软雅黑'; font-size: large">
	    				电话：&nbsp;&nbsp;&nbsp;
	    				</p>
	    			</div>
	    			<div class="layui-col-md5" >
	    				<p style="font: '微软雅黑'; font-size: large">
	    				${user.phone}
	    				</p>
	    			</div>
	    		</div>
	    		<div class="layui-row">
	    			<div class="layui-col-md2">
	    				<p align="right" style="font: '微软雅黑'; font-size: large">
	    				电子邮箱：&nbsp;&nbsp;&nbsp;
	    				</p>
	    			</div>
	    			<div class="layui-col-md5" >
	    				<p style="font: '微软雅黑'; font-size: large">
	    				${user.email}
	    				</p>
	    			</div>
	    		</div>
	    		<div class="layui-row">
	    			<div class="layui-col-md2"  align="right" >
	    				<a href="movie#" class="layui-btn" 
									data-toggle="modal" 
									data-target="#editUserDialog" 
									onclick="selectByName('${USER_SESSION.name}')">修改个人信息</a>
					</div>
					<div class="layui-col-md5" >
					</div>
				</div>
		    </div>
  		</div>
  		
  		<!-- 修改部分 -->
		<div class="modal fade" id="editUserDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">修改个人信息</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="edit_user_form">
							<div class="form-group">
								<label for="edit_name" class="col-sm-2 control-label">账号</label>
								<div class="col-sm-4">
									<input type="hidden" class="form-control" id="edit_name" name="name">
								</div>
								<label for="edit_paaswd" class="col-sm-2 control-label">密码</label>
								<div class="col-sm-4"> 
									<input type="hidden" class="form-control" id="edit_paaswd" name="paaswd">
								</div>
							</div>
							<div class="form-group">
								<label for="edit_nickname" class="col-sm-2 control-label">昵称</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="edit_nickname" placeholder="昵称" name="nickname">
								</div>
								<label for="edit_sex" class="col-sm-2 control-label">性别</label>
								<div class="col-sm-4"> 
									<select class="form-control" id="edit_sex" name="sex">
										<option value="sex" selected="selected">--请选择--</option>
										<option value="1">男</option>
										<option value="2">女</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="edit_age" class="col-sm-2 control-label">年龄</label>
								<div class="col-sm-4">
									<input type="number" class="form-control" id="edit_age" placeholder="年龄" name="age">
								</div>
								<label for="edit_phone" class="col-sm-2 control-label">手机</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="edit_phone" placeholder="绑定手机号" name="phone">
								</div>
							</div>
							<div class="form-group">
								<label for="edit_email" class="col-sm-2 control-label">电子邮箱</label>
								<div class="col-sm-4">
									<input type="emial" class="form-control" id="edit_email" placeholder="电子邮箱" name="email">
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="layui-btn layui-btn-normal" data-dismiss="modal">关闭</button>
						<button type="button" class="layui-btn layui-btn-normal" onclick="updUser()">更新信息</button>
					</div>
				</div>
			</div>
		</div>
  		
	  	<!-- 底部固定区域 -->
	  	<div class="layui-footer">
	    	© Welcome to my world！
	  	</div>
	</div>

	<!-- jQuery -->
	<script src="<%=basePath %>/static/jquery-1.11.3.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="<%=basePath %>/static/bootstrap.min.js"></script>
	<!-- layui Core JavaScript -->
	<script src="<%=basePath %>/static/layui.all.js"></script>
	
	<script type="text/javascript">
		/* 下拉框的js */
		layui.use('element', function(){
		  var element = layui.element;
		  
		});
		
		
		/* 根据name获取需要修改的员工信息  */ 		  
		function selectByName(name) {
		    $.ajax({
		        type:"get",
		        url:"getuser",
		        data:{"name":name},
		        success:function(data) {
		        	console.log(data);
		        	$("#edit_name").val(data.name);
		        	$("#edit_nickname").val(data.nickname);
		        	$("#edit_sex").val(data.sex);
		        	$("#edit_age").val(data.age);
		        	$("#edit_phone").val(data.phone);
		        	$("#edit_email").val(data.email);
		        }
		    });
		}
		
		/* 执行修改操作 */
		function updUser() {
			$.post("user.data",
			 $("#edit_user_form").serialize(),
			  function(data){
				console.log(data)
				if(data == "OK"){
					alert("个人信息更新成功！");
					window.location.reload();
				}else{
					alert("个人信息更新失败！");
					window.location.reload();
				}
			});
		}
	</script>

</body>
</html>