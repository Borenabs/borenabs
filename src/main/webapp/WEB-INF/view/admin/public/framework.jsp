<%--
  Created by IntelliJ IDEA.
  User: Lan
  Date: 2019/9/4
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>博客后台</title>
	<link rel="stylesheet" href="/layui/css/layui.css">
	<rapid:block name="header-style"></rapid:block>
	<rapid:block name="header-script"></rapid:block>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<div class="layui-logo">borenabs博客</div>
		<!-- 头部区域（可配合layui已有的水平导航） -->
		<ul class="layui-nav layui-layout-left">
			<li class="layui-nav-item"><a href="">前台</a></li>
			<li class="layui-nav-item">
				<a href="javascript:;">新建</a>
				<dl class="layui-nav-child">
					<dd><a href="">文章</a></dd>
					<dd><a href="">页面</a></dd>
					<dd><a href="">分类</a></dd>
					<dd><a href="">公告</a></dd>
					<dd><a href="">链接</a></dd>
				</dl>
			</li>
		</ul>
		<ul class="layui-nav layui-layout-right">
			<li class="layui-nav-item">
				<a href="javascript:;">
					<img src="http://t.cn/RCzsdCq" class="layui-nav-img">
					动感超哥
				</a>
				<dl class="layui-nav-child">
					<dd><a href="">基本资料</a></dd>
				</dl>
			</li>
			<li class="layui-nav-item"><a href="">退了</a></li>
		</ul>
	</div>

	<div class="layui-side layui-bg-black">
		<div class="layui-side-scroll">
			<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			<ul class="layui-nav layui-nav-tree"  lay-filter="test">
				<li class="layui-nav-item layui-nav-itemed">
					<a class="" href="javascript:;">文章</a>
					<dl class="layui-nav-child">
						<dd><a href="/article/index">全部文章</a></dd>
						<dd><a href="javascript:;">写文章</a></dd>
						<dd><a href="/admin/category">全部分类</a></dd>
						<dd><a href="javascript:;">全部标签</a></dd>
					</dl>
				</li>
				<li class="layui-nav-item"><a href="/admin/comment">评论</a></li>
				<li class="layui-nav-item">
					<a href="javascript:;">公告</a>
					<dl class="layui-nav-child">
						<dd><a href="/admin/notice">全部公告</a></dd>
						<dd><a href="/admin/notice/insert">添加公告</a></dd>
					</dl>
				</li>
				<li class="layui-nav-item">
					<a href="javascript:;">用户</a>
					<dl class="layui-nav-child">
						<dd><a href="/admin/user">全部用户</a></dd>
						<dd><a href="/admin/user/insert">添加用户</a></dd>
					</dl>
				</li>

			</ul>
		</div>
	</div>

	<div class="layui-body">
		<!-- 内容主体区域 -->
		<div style="padding: 15px;">
			<rapid:block name="content">

			</rapid:block>
		</div>
	</div>
<script src="/layui/layui.all.js"></script>
<script>
    //一般直接写在一个js文件中
    layui.use(['layer', 'form'], function(){
        var layer = layui.layer
            ,form = layui.form;
    });
</script>
	<rapid:block name="footer-script">

	</rapid:block>
</body>
</html>
