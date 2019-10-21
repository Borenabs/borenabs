<%--
  Created by IntelliJ IDEA.
  User: CG
  Date: 2019/9/9
  Time: 17:08
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
    <%-- 主要展示功能
    添加
    列出说有的标签
    即一个页面包含两个功能
    --%>
    <%--头部展示--%>
    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
              <a href="/admin">首页</a>
              <a href="/admin/tag">标签列表</a>
              <a><cite>编辑标签</cite></a>
        </span>
    </blockquote>
    <%--利用网格--%>
    <div class="layui-row">
        <div class="layui-col-md4">
            <form class="layui-form" id="tagFrom" method="post" action="/admin/tag/editSubmit">
                    <%--添加标签
                    标签名，不能为空
                    标签描述
                    --%>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <strong>添加标签</strong>
                    </div>
                    <input type="hidden" name="tagId" value="${tag.tagId}" />
                    <div class="layui-input-block">
                        名称 </span>
                        <input type="text" name="tagName" value="${tag.tagName}" placeholder="请输入标签名称" autocomplete="off" class="layui-input" required>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        标签描述
                        <input type="text" name="tagDescription" value="${tag.tagDescription}" placeholder="请输入标签描述" autocomplete="off" class="layui-input" >
                    </div>
                    <br>
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-filter="formDemo" type="submit">编辑</button>
                    </div>
                </div>
            </form>
            <blockquote class="layui-elem-quote layui-quote-nm">
                温馨提示：
                <ul>
                    <li>1、标签名必选，建议不要太长</li>
                    <li>2、标签名勿重复</li>
                </ul>
            </blockquote>
        </div>
        <div class="layui-col-md8">
            <table class="layui-table" >
                <colgroup>
                    <col width="300">
                    <col width="50">
                    <col width="100">
                    <col width="50">
                </colgroup>
                <thead>
                <tr>
                    <th>名称</th>
                    <th>文章数</th>
                    <th>操作</th>
                    <th>ID</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${tagList}" var="c">

                    <tr>
                        <td>
                                <%--重定向到用户页面--%>
                            <a href="" target="_blank">${c.tagName}</a>
                        </td>
                        <td >
                                <%--重定向到用户页面--%>
                            <a href="" target="_blank"  lay-data="{sort:true}">${c.count}</a>
                        </td>
                        <td>
                            <a href="/admin/tag/edit/${c.tagId}" class="layui-btn layui-btn-sm">编辑</a>
                            <c:if test="${c.count==0}">
                                <a href="/admin/tag/delete/${c.tagId}" class="layui-btn layui-btn-danger layui-btn-sm" onclick="return confirmDelete()">删除</a>
                            </c:if>
                        </td>
                        <td >${c.tagId}</td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
            <blockquote class="layui-elem-quote layui-quote-nm">
                温馨提示：
                <ul>
                    <li>如果该标签包含文章，将不可删除</li>
                </ul>
            </blockquote>
        </div>
    </div>
</rapid:override>
<jsp:include page="../public/framework.jsp" />
