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
<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge; chrome=1">
    <link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700" rel="stylesheet" type="text/css">
    <link href="resources/scripts/css/japp/<fmt:message key='jquery-ui-1.8.23.custom.css'/>" rel="stylesheet" type="text/css">
    <link href="resources/scripts/css/<fmt:message key='selectBox.css'/>" rel="stylesheet" type="text/css">
    <link href="resources/scripts/css/<fmt:message key='farbtastic.css'/>" rel="stylesheet" type="text/css">
    <link href="resources/css/<fmt:message key='common.css'/>" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/png" href="resources/images/Jemm_bullet.png" />
    <style type="text/css">
    label.error { width: 250px; display: block; color: red; padding-left: 10px; } 
    </style>
    
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js"></script>
   
</head>
<body>

<div class="page">
    <div class="page-container">

       <div class="header-wrapper" id="header">
            <div class="header-container">
                <div class="header">
                    <div class="header-left">
                        <div class="logo">
                            <a href="#"></a>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /.header-wrapper -->

        <div class="content-wrapper" id="content">
            <div class="content-container">
                <div class="content">



                    <div class="content-main">

                        
					<div class="form-errors">${msgUser}</div>

                        <div class="user-create-wrapper">
                            <div class="user-create add-wr">
                                <h2 class="module-title">Create new user</h2>
								
									
                                <form:form action="registerUser" id="newSuperUser" modelAttribute="newSuperUser">

                                   <%--  <div class="form-line">
                                        <div class="label"><label>Username</label></div>
                                        <form:input path="username"  class="field medium"/>
                                        <form:hidden path="creatorId" value="${user.id}"/>
                                    </div> --%>                                    
                                     <div class="form-line">
                                        <div class="label"><label>Email</label></div>
                                        <form:hidden path="creatorId" value="${user.id}"/>
                                        <form:input path="email" class="field medium" />
                                    </div>
                                    
                                    <div class="form-line clearFix">
                                        <div class="form-line-l">
                                            <div class="label"><label>First name</label></div>
                                            <form:input path="firstName" class="field small"/>
                                        </div>
                                        <div class="form-line-r">
                                            <div class="label"><label>Second name</label></div>
                                            <form:input path="lastName" class="field small"/>
                                        </div>
                                    </div>

                                    <div class="form-line">
                                        <div class="label"><label>password</label></div>
                                        <form:input path="userPassword"  class="field medium"/>
                                    </div>
                                    
                                     <div class="form-line">
                                        <div class="label"><label>Group/Company Name</label></div>
                                        <form:input path="groupName"  class="field medium"/>
                                    </div>
                                    
                                    <div class="form-line">
                                        <div class="label"><label>Office</label></div>
                                       <form:select path="regionName" data-custom="true">
                                        
                                         <option value="UK">UK</option>
                                        
                                    </form:select>
                                    </div>

                                   <!--  <div class="form-line">
                                        <div class="label"><label>Date of birth</label></div>
                                        <select data-custom="true">
                                            <option>Day</option>
                                        </select>
                                        <select data-custom="true">
                                            <option>Month</option>
                                        </select>
                                        <select data-custom="true">
                                            <option>Year</option>
                                        </select>
                                    </div> -->

                                   

                                    <button type="submit" class="btn-control create"><i class="icon-create"></i><span>Create</span></button><a class="cancelBtn" href="#">CANCEL</a>

                                </form:form>

                            </div>
                        </div><!-- /.user-create-wrapper -->

                        

                    </div>


                </div>
            </div>
        </div><!-- /.content-wrapper -->

        <%@ include file="footer.html"%><!-- /.footer-wrapper -->

    </div>
</div><!-- /.page -->

<%@ include file="scripts.html"%>
</body>
</html>