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

            <li <c:if test="${param.index==2}">class="active"</c:if>>
                <a href="#">
                    <i class="fa fa-home"></i>
                    <span class="nav-label">结算管理</span>
                    <span class="fa arrow"></span>
                </a>
                <ul class="nav nav-second-level">
                    <li>
                        <a class="J_menuItem" onclick="redirect(wxUserUrl+'?index=2')" <c:if test="${param.index==2}">style="color: white;"</c:if>>结算</a>
                    </li>
                </ul>
            </li>

            <li <c:if test="${param.index==3}">class="active"</c:if>>
                <a href="#">
                    <i class="fa fa-home"></i>
                    <span class="nav-label">赠送管理</span>
                    <span class="fa arrow"></span>
                </a>
                <ul class="nav nav-second-level">
                    <li>
                        <a class="J_menuItem" onclick="redirect(wxUserUrl+'?index=3')" <c:if test="${param.index==3}">style="color: white;"</c:if>>赠送</a>
                    </li>
                </ul>
            </li>


        </ul>
</div>
    </div>
</nav>