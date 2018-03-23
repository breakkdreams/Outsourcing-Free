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

            <input type="hidden" value="${option}" id="optionStr">
            <c:forEach items="${listMenu}" var="menuList" varStatus="status">
                <c:set value="" var="sum" />
                <c:set value="0" var="choose" />
                <c:if test="${menuList.erId==0}">
                    <c:forEach items="${listMenu}" var="men2">
                        <c:if test="${men2.erId==menuList.pid}">
                            <c:set var="sum">${sum},${men2.url}</c:set>
                        </c:if>
                    </c:forEach>
                    <c:set value="${fn:split(sum, ',') }" var="str1" />
                    <c:forEach items="${ str1 }" var="s">
                        <c:if test="${fn:contains(option,s)==true}">
                            <c:set var="choose" value="1"/>
                        </c:if>
                    </c:forEach>
                    <li <c:if test="${choose==1}">class="active"</c:if>  >
                        <a href="#">
                            <i class="fa ${menuList.tubiao}"></i>
                            <span class="nav-label">${menuList.pName}</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level collapse" >
                            <c:forEach items="${listMenu}" var="men">
                                <c:set value="0" var="second_choose" />
                                <c:if test="${men.erId==menuList.pid}">
                                    <c:if test="${fn:contains(option,men.url)==true}">
                                        <c:set var="second_choose" value="1"/>
                                    </c:if>
                                    <li><a class="J_menuItem" href="javascript:void(0)"
                                            <c:if test="${second_choose==1}">style="color: white;"</c:if>
                                        onclick="redirect(${men.url})"
                                    >${men.pName}</a></li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </li>
                </c:if>
            </c:forEach>

        </ul>
</div>
    </div>
</nav>

<script type="text/javascript" src="JspJsCss/managerJsp/js/left.js"></script>

						