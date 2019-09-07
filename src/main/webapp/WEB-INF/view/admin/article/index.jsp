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

<rapid:override name="content">
	<blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
          <a href="/admin">首页</a>
          <a><cite>文章列表</cite></a>
        </span>
	</blockquote>

	<div class="layui-tab layui-tab-card">
		<form id="articleForm" method="post">
			<input type="hidden" name="currentUrl" id="currentUrl" value="">
			<table class="layui-table">
				<colgroup>
					<col width="300">
					<col width="150">
					<col width="100">
					<col width="150">
					<col width="100">
					<col width="50">
				</colgroup>
				<thead>
				<tr>
					<th>标题</th>
					<th>所属分类</th>
					<th>状态</th>
					<th>发布时间</th>
					<th>操作</th>
					<th>id</th>
				</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							${example.articleTitle}
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</rapid:override>
<jsp:include page="../public/framework.jsp" />
