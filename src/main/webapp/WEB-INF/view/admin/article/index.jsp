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
				<c:forEach items="${pageInfo.list}" var="a">
					<tr>
						<td>
							<a href="/article/${a.articleId}"
							   target="_blank">
									${a.articleTitle}

							</a></td>
						<td>
							<c:forEach items="${a.categoryList}" var="c">
								<a href="/category/${c.categoryId}"
								   target="_blank">${c.categoryName}</a>
								&nbsp;
							</c:forEach>
						</td>
						<td>
							<c:choose>
								<c:when test="${a.articleStatus == 1}">
									<a href="${pageContext.request.contextPath}/admin/article?status=1">
										<span style="color:#5FB878;">已发布</span>
									</a>
								</c:when>
								<c:otherwise>
									<a href="${pageContext.request.contextPath}/admin/article?status=0">
										<span style="color:#FF5722;">草稿</span>
									</a>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<fmt:formatDate value="${a.articleCreateTime}"
											pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<a href="/admin/article/edit/${a.articleId}"
							   class="layui-btn layui-btn-mini">编辑</a>
							<a href="javascript:void(0)"
							   onclick="deleteArticle(${a.articleId})"
							   class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
						</td>
						<td>${a.articleId}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</form>
		<%@ include file="../public/paging.jsp" %>
	</div>
</rapid:override>
<jsp:include page="../public/framework.jsp" />
