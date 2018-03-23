<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="sidebar-collapse">
    <div class="sidebar-collapse" style="width: auto; height: 100%;">
    
        <ul class="nav metismenu" id="side-menu" style="display: block;">
            <li class="nav-header">
                <div class="dropdown profile-element"> <span>
                            <%--<img alt="image" class="img-circle" src="/plus/H+/img/profile_small.jpg" />--%>
                             </span>
                    	<a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear">
                                <span class="block m-t-xs"></span>
                                <span class="text-muted text-xs block">
                                    <strong class="font-bold" style="font-size: 20px;color: white;">${sessionAccount.username}<b class="caret"></b></strong>
                                </span>
                            </span>
                    	</a>
                    	<!-- <ul class="dropdown-menu animated fadeInRight m-t-xs">
                             <li><a class="J_menuItem" onclick="updatePassword()">修改密码</a> </li>
                         </ul> -->
                </div>
                <div class="logo-element">
                    ${sessionAccount.username}
                </div>
            </li>

            <li <c:if test="${param.index==1 || param.index==2 || param.index==3}">class="active"</c:if>>
                <a href="#">
                    <i class="fa fa-home"></i>
                    <span class="nav-label">商家信息管理</span>
                    <span class="fa arrow"></span>
                </a>
                <ul class="nav nav-second-level">
                    <li>
                        <a class="J_menuItem" onclick="redirect(businessEditPage+'?index=1')" <c:if test="${param.index==1}">style="color: white;"</c:if>>商家信息管理</a>
                    </li>
                    <li>
                        <a class="J_menuItem" onclick="redirect(brandUrl+'?index=2')" <c:if test="${param.index==2}">style="color: white;"</c:if>>今日销售额</a>
                    </li>
                    <li>
                        <a class="J_menuItem" onclick="redirect(businessNoticeUrl+'?index=3')" <c:if test="${param.index==3}">style="color: white;"</c:if>>公告信息</a>
                    </li>

                </ul>
            </li>

            <li <c:if test="${param.index==4 || param.index==5}">class="active"</c:if>>
                <a href="#">
                    <i class="fa fa-home"></i>
                    <span class="nav-label">财务报表</span>
                    <span class="fa arrow"></span>
                </a>
                <ul class="nav nav-second-level">
                    <li>
                        <a class="J_menuItem" onclick="redirect(wxUserUrl+'?index=4')" <c:if test="${param.index==4}">style="color: white;"</c:if>>销售报表</a>
                    </li>
                    <li>
                        <a class="J_menuItem" onclick="redirect(wxUserUrl+'?index=5')" <c:if test="${param.index==5}">style="color: white;"</c:if>>领取记录</a>
                    </li>
                </ul>
            </li>


        </ul>
</div>
    </div>
</nav>