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


                    <div class="content-main">


                    <div class="add-wr" data-control="announcement">
                        <h2 class="module-title">Create important announcement</h2>
                        <div class="form-errors">${announcementSuccessMsg}</div>
                        

                        <form:form action="createAnnouncement" modelAttribute="announcement">
							<input type="hidden" id="creatorId" name="creatorId" value="${user.id}">
							<input type="hidden" id="groupId" name="groupId" value="${user.groupId}">
                            <div class="form-line">
                                <div class="label">
                                    <label for="form2432">Write your message</label>
                                </div>
                                <form:textarea path="message" class="textarea" placeholder="Type short task title here" />
                            </div>

                            <div class="form-line">
                                <div class="label">
                                    <label>Date range</label>
                                </div>
                                <span data-control="datepicker" class="calendar-inline"><strong>From</strong> 
                                <form:input path="startsAt" placeholder="Choose date" data-control="datepickerFrom" /> <strong>To</strong> <form:input path="endsAt" placeholder="Choose date" data-control="datepickerTo" /></span>
                            </div>

                           <!--  <div class="form-line">
                                <div class="label">
                                    <label for="form2432">Who should recieve this message? </label>
                                </div>
                                <span class="checkbox-group">
                                    <input type="checkbox" id="form31231"> <label for="form31231">All</label>
                                    <input type="checkbox" id="form312"> <label for="form312">AU office</label>
                                    <input type="checkbox" id="form43242"> <label for="form43242">UK office</label>
                                    <input type="checkbox" id="form435"> <label for="form435">FR office</label>
                                </span>
                            </div> -->

                            <button type="submit" class="btn-control create"><i class="icon-create"></i><span>Publish</span></button> <a class="cancelBtn" href="#">CANCEL</a>

                       </form:form>

                    </div>



                   <!--  <div data-control="alert" class="alert-wrapper">
                            <div class="alert-block">
                                <i class="icon-warning"></i>
                                <span>NASA administrator's voice was radioed to the Curiosity rover on Mars. The rover then sent the message back. What did he say?</span>
                            </div>
                            <span data-control="alertClose" class="icon-close-alert"></span>
                        </div> -->


                    </div>


                </div>
            </div>
        </div><!-- /.content-wrapper -->

        

    </div>
</div><!-- /.page -->
<%@ include file="scripts.html"%>
</body>
</html>