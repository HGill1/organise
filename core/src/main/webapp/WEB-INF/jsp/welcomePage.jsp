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
<%@ include file="header.html"%>
<body>

<div class="page">
    <div class="page-container">
          <%@ include file="menu.jsp"%>
        <!-- /.header-wrapper -->

        <div class="content-wrapper" id="content">
            <div class="content-container">
                <div class="content clearFix">

                    <div class="nav-back">
                        Welcome to Organise
                    </div><br/>
                    <div style="text-align:center;">
                    	To get started please create your first topic
                    </div><br/>
                    <div style="text-align:center;">
						<a href="addSourcesForm"> <button type="button" class="btn-control create">
							<span>Continue</span>
						</button></a>
						<a class="cancelBtn" id="skipTopicCreation" href="createDefaultTopic">SKIP</a>
					</div>
                       
                </div>
            </div>
        </div><!-- /.content-wrapper -->


   <%@ include file="footer.html"%>



    </div>
</div><!-- /.page -->


<%@ include file="scripts.html"%>
</body>
</html>

