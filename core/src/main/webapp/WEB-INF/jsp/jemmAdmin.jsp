<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<!--[if IE 7]><html class="ie7" id="ie7" lang="en"><![endif]-->
<!--[if IE 8]><html class="ie8" id="ie8" lang="en"><![endif]-->
<!--[if IE 9]><html class="ie9" id="ie9" lang="en"><![endif]-->
<!--[if !IE]><!--><html lang="en"><!--<![endif]-->
<%@ include file="header.html"%>
<body>

<div class="page">
    <div class="page-container">

        <%@ include file="menu.jsp"%>

        <div class="content-wrapper" id="content">
            <div class="content-container">
                <div class="content clearFix">

                       
                    <div class="content-main">


                        <div class="source-wr">
                            <div class="title-bg">
                                <div class="title-bg-name">Groups Detail</div>
                            </div>

                           <!--  <div class="create-btn-wrapper">
                                <a href="addSourcesForm" class="task-new"><i class="icon-plus"></i>Create Topic</a>
                            </div> -->

                            <div class="source-container">
                            <table class="source-table" id="groupsTable">
                            <thead>
                            <tr class="source-header">
                                <th>GroupName</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Country</th>
                                <th>created On</th>
                                <th>Last Login at</th>
                                <th>View Detail</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="group" items="${allGroups}">
                            <tr>
                                <td>
                                    <div class="source-visible">${group.groupName}</div>
                                </td>
                                <td>
                                    <div class="source-visible">${group.firstName} ${group.lastName}</div>
                                </td>                                
                                <td>
                                    <div class="source-visible">
                                        ${group.email}                                       
                                    </div>
                                </td>
                                <td>
                                    <div class="source-visible">
                                        ${group.regionName}                                       
                                    </div>
                                </td>
                                 <td>
                                    <div class="source-visible">
                                    <jsp:useBean id="createdAt" class="java.util.Date" />
                                    <jsp:setProperty name="createdAt" property="time" value="${group.createdAt}" />
                                    <fmt:formatDate pattern="dd MMM yyyy" value="${createdAt}" var="displayDate"/> 
                                    ${displayDate}                                                                              
                                    </div>
                                </td>
                                 <td>
                                    <div class="source-visible">
                                    <c:if test="${not empty group.lastLoginAt}">
                                    <jsp:useBean id="lastLoginAt" class="java.util.Date" />
                                    <jsp:setProperty name="lastLoginAt" property="time" value="${group.lastLoginAt}" />
                                    <fmt:formatDate pattern="dd MMM yyyy" value="${lastLoginAt}" var="lastLogin"/> 
                                   		${lastLogin}
                                    </c:if>                                    
                                  		                                                                                                                 
                                    </div>
                                </td> 
                                <td>
                                    <div class="source-actions">
                                        <!-- <a href="#">Show</a> -->
                                        <a id="editSource" href="viewGroup?id=${group.groupId}">View</a>
                                    </div>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                            </div>

                </div>

            </div>
          </div>
        </div>
      </div><!-- /.content-wrapper -->
    </div>
</div><!-- /.page -->

<%@ include file="scripts.html"%>
<script type='text/javascript' src="resources/scripts/<fmt:message key='jquery.tablesorter.js'/>"></script>
</body>
</html>