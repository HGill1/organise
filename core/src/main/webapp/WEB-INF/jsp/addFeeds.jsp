<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--[if IE 7]><html class="ie7" id="ie7" lang="en"><![endif]-->
<!--[if IE 8]><html class="ie8" id="ie8" lang="en"><![endif]-->
<!--[if IE 9]><html class="ie9" id="ie9" lang="en"><![endif]-->
<!--[if !IE]><!-->
<html lang="en"><!--<![endif]-->

<head>
<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge; chrome=1">
    <link href="resources/css/common.css" rel="stylesheet" type="text/css">
</head>
</head>
<body>

<div class="page">
    <div class="page-container">

       <jsp:include page="menu.jsp" />

        <div>
        
        <c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
		</c:if>
        
            <div>
                <form:form action="insertFeed"  method="post" modelAttribute="feeds">
                    <div class="form-line">
                        <label>Source type:</label> 
                        <select id="sourceType" name="sourceType" data-custom="true">                                   
                                        <c:forEach var="feedsType" items="${allFeedTypes}">
                                        <option value="${feedsType.key}">${feedsType.value.name}</option>
                                        </c:forEach>
                                    </select>
                    </div>
                     <div class="form-line">
                        <label>Name:</label> <form:input path="name" class="field" />
                    </div>
                    <div class="form-line">
                        <label>URL:</label> <form:input path="url" class="field"/>
                    </div>
                    <input type="submit" value ="submit">
                </form:form>
            </div>
            <div class="login-footer">
                &copy; Jemm media Ltd. 2007-2012
            </div>
        </div>


    </div>
</div><!-- /.page -->
</body>
</html>