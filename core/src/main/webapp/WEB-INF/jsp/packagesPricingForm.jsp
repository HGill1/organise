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
                         <form:form action="addPackagesPricing" id="packagesPricing" modelAttribute="packagesPricing">
                            <div class="user-create add-wr">
                                <h2 class="module-title">Create Platinum Package</h2>
								
									
                               

                                                                      
                                     <div class="form-line">
                                        <div class="label"><label>Select Groups</label></div>
                                         <form:select path="groupId" data-custom="true">
		                                            <form:options items="${groupsList}"/>
		                                 </form:select>
                                    </div>
                                    
                                    <div class="form-line clearFix">
                                        <div class="form-line-l">
                                            <div class="label"><label>Price</label></div>
                                            <form:input path="price" class="field medium"/>
                                        </div>
                                        <div class="form-line-l">
                                            <div class="label"><label>Number of users allowed</label></div>
                                            <form:input path="usersAllowed" class="field medium"/>
                                        </div>
                                        <div class="form-line-r">
                                            <div class="label"><label>Storage Allowed(Enter '0' for unlimited storage) </label></div>
                                            <form:input path="storageAllowed" class="field medium"/>
                                        </div>
                                    </div>

                                    <div class="form-line">
                                        <!-- <div class="label"><label>Support Type</label></div> -->
                                        <form:hidden path="supportType" value="3" />
                                    </div>
                                    
                                    <div class="form-line">
                                        <div class="label"><label>Subject</label></div> 
                                        <input type="text" name="emailSubject" value="" class="field medium">
                                    </div>
                                    
                                     <div class="form-line">
                                        <div class="label"><label>Email body:</label></div> 
                                        <textarea rows="5" cols="32" name="emailBody"></textarea> 
                                    </div>
                                    
                                    </div>

                                    <button type="submit" class="btn-control create"><i class="icon-create"></i><span>Create</span></button><a class="cancelBtn" href="#">CANCEL</a>

                                </form:form>

                            </div>
                        </div><!-- /.package creater-wrapper -->

                        

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