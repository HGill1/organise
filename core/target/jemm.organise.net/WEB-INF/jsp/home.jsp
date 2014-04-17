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
<!--[if !IE]><!--><html lang="en"><!--<![endif]-->
<head>
<meta charset="utf-8">
    <title></title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge; chrome=1">
    <link href="resources/scripts/css/selectBox.css" rel="stylesheet" type="text/css">
    <link href="resources/css/common.css" rel="stylesheet" type="text/css">
<title>Home</title>
</head>
<body>
<div class="page">
    <div class="page-container">
    
    <%@ include file="menu.jsp"%>
    
</div>
</div>

<%-- <div>
Welcome <%= SecurityContextHolder.getContext().getAuthentication().getName() %>
</div>
<div>
</div> --%>
<%@ include file="scripts.html"%>
</body>
</html>