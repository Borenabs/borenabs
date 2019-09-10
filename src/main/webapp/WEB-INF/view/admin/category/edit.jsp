<%--
  Created by IntelliJ IDEA.
  User: Lan
  Date: 2019/9/7
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<rapid:override name="header-style">
	<style>
		/*覆盖 layui*/
		.layui-input-block {
			margin:0px 10px;
		}
		.layui-table {
			margin-top: 0;
		}
		.layui-col-md4 {
			padding:10px;
		}
		.layui-col-md8 {
			padding:10px;
		}
		.layui-btn {
			margin: 2px 0!important;
		}
	</style>
</rapid:override>
<rapid:override name="content">
	<blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
              <a href="/admin">首页</a>
              <a href="/admin/category">分类列表</a>
              <a><cite>编辑分类</cite></a>
        </span>
	</blockquote>
	<div class="layui-row">
		<div class="layui-col-md4">
			<form class="layui-form"  method="post" id="myForm" action="/admin/category/editSubmit">
				<%--修改失败,可能是页面没有分类id--%>
				<input type="hidden" name="categoryId" id="categoryId" value="${category.categoryId}">
				<div class="layui-form-item">
					<div class="layui-input-block">
						<strong>编辑分类</strong>
					</div>
					<div class="layui-input-block">
						名称 <span style="color: #FF5722; ">*</span>
						<input type="text" name="categoryName" value="${category.categoryName}" placeholder="名称" autocomplete="off" class="layui-input" required>
					</div>
					<br>
					<div class="layui-input-block">
						父节点 <span style="color: #FF5722; ">*</span>
						<select name="categoryPid" class="layui-input" required>
							<c:forEach items="${categoryList}" var="c">
								<c:choose>
									<c:when test="${c.categoryId==category.categoryPid}" >
										<option value="${c.categoryId}" selected>${c.categoryName}</option>
									</c:when>
									<c:otherwise>
										<c:if test="${c.categoryPid==0}">
											<option value="${c.categoryId}">${c.categoryName}</option>
										</c:if>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
					<br>
					<div class="layui-input-block">
						分类描述
						<input type="text" name="categoryDescription" value="${category.categoryDescription}" placeholder="请输入分类描述" autocomplete="off" class="layui-input" >
					</div>
					<br>
					<div class="layui-input-block">
						图标样式
						<input type="text" name="categoryIcon" value="${category.categoryIcon}" placeholder="请输入图标样式,如 fa fa-coffee" autocomplete="off" class="layui-input" >
					</div>
					<br>
					<div class="layui-input-block">
						<button class="layui-btn" lay-filter="formDemo" type="submit">修改</button>
					</div>
				</div>
			</form>
		</div>
		<div class="layui-col-md8" >
			<table class="layui-table" >
				<colgroup>
					<col width="300">
					<col width="100">
					<col width="100">
					<col width="100">
					<col width="50">
					<col width="50">
				</colgroup>
				<thead>
				<tr>
					<th>名称</th>
					<th>文章数</th>
					<th>操作</th>
					<th>ID</th>
					<th>pid</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${categoryList}" var="c">
					<c:if test="${c.categoryPid==0}">
						<tr>
							<td>
								<a href="/category/${c.categoryId}" target="_blank">${c.categoryName}</a>
							</td>
							<td>
								<a href="/category/${c.categoryId}" target="_blank">${c.articleCount}</a>
							</td>
							<td>
								<a href="/admin/category/edit/${c.categoryId}" class="layui-btn layui-btn-sm">编辑</a>
									<%--禁止删除有文章的分类--%>
								<c:if test="${c.articleCount==0}">
									<a href="/admin/category/delete/${c.categoryId}" class="layui-btn layui-btn-danger layui-btn-sm" onclick="return confirmDelete()">删除</a>
								</c:if>
							</td>
							<td>${c.categoryId}</td>
							<td>${c.categoryPid}</td>
						</tr>
						<c:forEach items="${categoryList}" var="c2">
							<c:if test="${c2.categoryPid==c.categoryId}">
								<tr>
									<td>
										<a href="/category/${c2.categoryId}" target="_blank">——${c2.categoryName}</a>
									</td>
									<td>
										<a href="/category/${c2.categoryId}" target="_blank">${c2.articleCount}</a>
									</td>
									<td>
										<a href="/admin/category/edit/${c2.categoryId}" class="layui-btn layui-btn-sm">编辑</a>
										<c:if test="${c2.articleCount==0}">
											<a href="/admin/category/delete/${c2.categoryId}" class="layui-btn layui-btn-danger layui-btn-sm" onclick="return confirmDelete()">删除</a>
										</c:if>
									</td>
									<td class="cate-parent">${c2.categoryId}</td>
									<td>${c2.categoryPid}</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


				</c:forEach>
				</tbody>
			</table>
			<blockquote class="layui-elem-quote layui-quote-nm">
				温馨提示：
				<ul>
					<li>分类最多只有两级，一级分类pid=0，二级分类pid=其父节点id</li>
					<li>如果该分类包含文章，将不可删除</li>
				</ul>
			</blockquote>
		</div>
	</div>
</rapid:override>
<jsp:include page="../public/framework.jsp" />
