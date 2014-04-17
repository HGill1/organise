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
                <div class="content">
                <form:form action="${formAction}" id="regionForm" modelAttribute="region">
                <form:hidden path="id"/>
                <input type="hidden" value="${user.groupId}" name="groupId" id="groupId" />
                    <div class="content-main">


                        <div class="user-edit-wrapper">
                          
                            <div class="user-edit add-wr">
                                <h2 class="module-title">Edit Region </h2>

                                

                                     <div class="form-line">
                                        <div class="label"><label>Region name</label></div>
                                        <form:input path="name" class="field medium"/>
                                    </div>
                                    
                            </div>
                                <div class="add-btn-wr">
                                    
                                    <button type="submit" class="btn-control create"><i class="icon-save"></i><span>Save</span></button><a class="cancelBtn" href="#">CANCEL</a>

                                 </div>
                        </div>
                       

                    </div>

</form:form>
                </div>
            </div>
        </div><!-- /.content-wrapper -->



    </div>
</div><!-- /.page -->




<%@ include file="scripts.html"%>
</body>
</html>