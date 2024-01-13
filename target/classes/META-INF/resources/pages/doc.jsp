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
		<div class="layui-body" style="background:#D1EEEE; color:#DC143C">
	    	<!-- 内容主体区域 -->
 			<div style="background-size:cover;padding: 15px;" ALIGN="center">
    				<br>
    			<!-- 查询部分 -->
				<div class="layui-row">
					<div class="layui-col-md5">
						<form class="layui-form" method="get" action="<%=basePath %>/study/doc">
							<div class="layui-col-md10">
								<input type="text" id="all_doc" name="all_doc" 
								placeholder="请输入查询的文档名" class="layui-input"/>
							</div>
							<button type="submit" class="layui-btn layui-btn-normal">查询</button>
						</form>
					</div>
			 	</div>
			 	<div class="layui-row">
			 		<div class="layui-col-md5">
			 			<div class="layui-col-md10">
							<form class="form-horizontal" enctype="multipart/form-data" id="upload-doc">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="请选择文件" /> 
									<span class="input-group-btn"> 
										<label for="forexIO_file" class="form-control btn btn-info"> 选择文件</label> 
										<input id="forexIO_file" type="file" name="files" onchange="set_placeholder(this)" style="display: none"/>
									</span>
								</div>
								<button type="button" class="form-control btn btn-info" 
								onclick="upload_doc()">上传</button>
							</form>
						</div>
					</div>
				</div>
				
				<!-- 查询结果表格显示 -->
				<table class="layui-table">
					<thead>
						<tr>
							<th>文档库ID</th>
							<th>文档名</th>
							<th>上传时间</th>
							<th>更新时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${all_doc}" var="emp">
						  <tr>
							<td>${emp.doc_id}</td>
							<td>${emp.doc_name}</td>
							<td>${emp.upload_time}</td>
							<td>${emp.update_time}</td>
							<td>
								<a href="index#" class="layui-btn" 
									onclick="delEmployee(${emp.doc_id})">删除</a>
								<a href="index#" class="layui-btn" 
									data-toggle="modal" 
									data-target="#editEmpDialog" onclick="selectById(${emp.doc_id})">修改</a>
							</td>
						  </tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	  	<!-- 底部固定区域 -->
	  	<div class="layui-footer">
	    	© Welcome to my world！
	  	</div>
	</div>

	<!-- jQuery -->
	<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="<%=basePath %>/static/bootstrap.min.js"></script>
	<!-- layui Core JavaScript -->
	<script src="<%=basePath %>/static/layui.all.js"></script>
	
	<script type="text/javascript">
		/* 下拉框的js */
		layui.use('element', function(){
		  var element = layui.element;
		  
		});
		function set_placeholder(e) {
	        var filename = $(e).val();
	        if (filename) {
	            var idx = filename.lastIndexOf("\\");
	            filename = filename.substring(idx + 1);
	            $(e).parent().prev().prop("placeholder", filename);
	        }
	    }
		/* 上传文档  */
		function upload_doc() {
			$.ajax({
				type: "POST",
				url: "doc/upload",
				data: new FormData($('#upload-doc')[0]),
				async: false,
				processData: false,
			    contentType: false,
			    cache: false,
				success: function(res) {
					console.log(res);
					if(res == 1){
						alert("上传成功！");
						window.location.reload();
					}else if(res == 0){
						alert("上传失败！");
						window.location.reload();
					}else {
						alert("上传文件为空！");
						window.location.reload();
					}
				},
				error: function() {
					alert("异常");
				}
			});
		}
	</script>

</body>
</html>