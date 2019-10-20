<%--
  Created by IntelliJ IDEA.
  User: Lan
  Date: 2019/9/16
  Time: 23:41
  To change this template use File | Settings | File Templates.
--%>
<%--
    博客顶部部分
    包括：顶部菜单，主要菜单(包括搜索按钮)，面包屑
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<%--导航 start--%>
<header id="masthead" class="site-header">
	<%--主要菜单 satrt--%>
	<div id="menu-box">
		<div id="top-menu">
                <span class="nav-search">
                    <i class="fa fa-search"></i>
                </span>
			<!-- .logo-site -->
			<div id="site-nav-wrap">
				<div id="sidr-close">
					<a href="#sidr-close" class="toggle-sidr-close">×</a>
				</div>
				<nav id="site-nav" class="main-nav">
					<a href="#sidr-main" id="navigation-toggle" class="bars">
						<i class="fa fa-bars"></i>
					</a>
					<div class="menu-pcmenu-container">
						<ul id="menu-pcmenu" class="down-menu nav-menu sf-js-enabled sf-arrows">

							<li>
								<a href="/">
									<i class="fa-home fa"></i>
									<span class="font-text">首页</span>
								</a>
							</li>

							<c:forEach items="${categoryList}" var="category">
								<c:if test="${category.categoryPid==0}">
									<li>
										<a href="/category/${category.categoryId}">
											<i class="${category.categoryIcon}"></i>
											<span class="font-text">${category.categoryName}&nbsp;</span>
										</a>
										<ul class="sub-menu">
											<c:forEach items="${categoryList}" var="cate">
												<c:if test="${cate.categoryPid==category.categoryId}">
													<li>
														<a href="/category/${cate.categoryId}" target="_blank">${cate.categoryName}</a>
													</li>
												</c:if>
											</c:forEach>
										</ul>
									</li>
								</c:if>
							</c:forEach>
							<%--主要菜单其余部分--%>
							<c:forEach items="${menuList}" var="m">
								<c:if test="${m.menuLevel == 2}">
									<li>
										<a href="${m.menuUrl}">
											<i class="${m.menuIcon}"></i>
											<span class="font-text">${m.menuName}&nbsp;</span>
										</a>
									</li>
								</c:if>
							</c:forEach>
							<c:if test="${sessionScope.user!=null}">
								<li>
									<a href="/admin/article">后台</a>
								</li>
							</c:if>

						</ul>
					</div>
				</nav>
			</div>
			<div class="clear"></div>
		</div><!-- #top-menu -->
	</div><!-- #menu-box -->
	<%--主要菜单 satrt--%>

</header><!-- #masthead -->
<%--导航 end start--%>

<rapid:block name="breadcrumb"></rapid:block>