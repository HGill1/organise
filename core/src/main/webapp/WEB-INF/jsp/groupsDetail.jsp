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
                                <div class="title-bg-name">Detail for ${group.name}</div>
                            </div>

                           <!--  <div class="create-btn-wrapper">
                                <a href="addSourcesForm" class="task-new"><i class="icon-plus"></i>Create Topic</a>
                            </div> -->

                            <div class="source-container">
                            
                            <div class="source-visible">No of users : ${usersCount}</div>
                            <div class="source-visible">No of Tasks Created : ${tasksCount}</div>
                            <div class="source-visible">No of Tasks Completed : ${tasksCompleted}</div>
                                
                            </div>

                </div>

            </div>
          </div>
        </div>
      </div><!-- /.content-wrapper -->
    </div>
</div><!-- /.page -->

<%@ include file="scripts.html"%>
</body>
</html>