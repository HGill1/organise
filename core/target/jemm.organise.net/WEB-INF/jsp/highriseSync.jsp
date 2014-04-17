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

                        
					<div class="form-errors">${msg}</div>

                        <div class="user-create-wrapper">
                        <form:form action="syncHighriseData" >
                          	
                          <input type="submit" name="highriseSync" value="Highrise Sync">
                        
                        </form:form>
                        
                        
                        
                        <div style='margin-top: 50px;'>
                       		<a href="highriseIndReport">Indivdual report</a>
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