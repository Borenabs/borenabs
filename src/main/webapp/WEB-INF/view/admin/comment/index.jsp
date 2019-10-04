<%--
  Created by IntelliJ IDEA.
  User: Lan
  Date: 2019/9/6
  Time: 22:21
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
		.layui-table {
			margin-top: 0;
		}
	</style>
</rapid:override>
<rapid:override name="content">
	<blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
              <a href="/admin">首页</a>
              <a><cite>评论列表</cite></a>
        </span>
	</blockquote>
	<div class="layui-tab layui-tab-card">
		<table class="layui-table" lay-even lay-skin="nob">
			<colgroup>
				<col width="100">
				<col width="300">
				<col width=200">
				<col width="150">
				<col width="50">
			</colgroup>
			<thead>
			<tr>
				<th>作者</th>
				<th>评论内容</th>
				<th>回复至</th>
				<th>提交于</th>
				<th>ID</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${pageInfo.list}" var="c">
				<tr>
					<%--用户头像,用户名--%>
					<td>
						<img src="${c.commentAuthorAvatar}" alt="" width="64px">
						<strong>${c.commentAuthorName}</strong>
					</td>
					<td class="dashboard-comment-wrap">
							${c.commentContent}
						<div class="row-actions">
                                     <span class="">
                                        <a href="/admin/comment/reply/${c.commentId}">
                                            回复
                                        </a>
                                     </span>
							<span class="">
                                        <a href="/admin/comment/edit/${c.commentId}">编辑</a>
                                     </span>
							<span class=""> |
                                        <a href="javascript:void(0)" onclick="deleteComment(${c.commentId})">删除</a>
                                     </span>
						</div>
					</td>
					<td>
						<a href="/article/${c.articleId}" target="_blank">${c.articleTitle}</a>
					</td>
					<td>
						<fmt:formatDate value="${c.commentCreateTime}" pattern="yyyy年MM月dd日 HH:dd:ss"/>
					</td>
					<td>${c.commentId}</td>
					<td>
						<span></span>
					</td>
					<td>
						<span></span>
					</td>


				</tr>
			</c:forEach>
			</tbody>

		</table>
		<div id="nav" style="">
			<%@ include file="../public/paging.jsp" %>
		</div>
		<%--<div id="demo3"></div>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
			<legend>自定义首页、尾页、上一页、下一页文本</legend>
		</fieldset>--%>
	</div>
	<%--<script>
	layui.use(['laypage', 'layer'], function(){
	var laypage = layui.laypage
	,layer = layui.layer;
        //自定义首页、尾页、上一页、下一页文本
        laypage.render({
            elem: 'demo3'
            ,count: 100
            ,first: '首页'
            ,last: '尾页'
            ,prev: '<em>←</em>'
            ,next: '<em>→</em>'
        });
        //调用分页
        laypage.render({
            elem: 'demo20'
            ,count: data.length
            ,jump: function(obj){
                //模拟渲染
                document.getElementById('biuuu_city_list').innerHTML = function(){
                    var arr = []
                        ,thisData = data.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);
                    layui.each(thisData, function(index, item){
                        arr.push('<li>'+ item +'</li>');
                    });
                    return arr.join('');
                }();
            }
        });
    });
	</script>--%>
</rapid:override>
<jsp:include page="../public/framework.jsp" />