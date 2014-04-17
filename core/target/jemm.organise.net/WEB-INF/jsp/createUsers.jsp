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

                    <div class="task-info">
                        <span class="task-remain">User management (${usersCount})</span>
                        <!-- <a id="toggleAddForm" href="#" class="task-new"><i class="icon-plus"></i>Create a new task</a> -->
                    </div>


                    <div class="content-main">

                        <%-- <div class="sort-wrapper clearFix">

                            <div class="sort-left">
                                <span class="sort-label">Sort by:</span>

                                <span class="sort-btn-wrapper">
                                    <span class="btns-set clearFix" data-control="sortBtns">
<input type="radio" id="radio1" name="radio" checked="checked"><label for="radio1">All</label><input type="radio" id="radio2" name="radio"><label for="radio2">Alphabet</label><input type="radio" id="radio3" name="radio"><label for="radio3">Employee title</label><input type="radio" id="radio4" name="radio"><label for="radio4">Campaign name</label><input type="radio" id="radio5" name="radio"><label for="radio5">Office location</label>

                                    </span>
                                </span>
                            </div>
                            <div class="sort-right">
                                <div class="search-wrapper">
                                    <form>
                                        <input class="search-field" type="text">
                                    </form>
                                </div>
                            </div>

                        </div> --%>
					<div class="form-errors">${msgUser}</div>
					<div class="form-errors">${packageError}</div>
                        <div class="user-create-wrapper">
                            <div class="user-create add-wr">
                                <h2 class="module-title">Create new user for ${user.groupName}</h2>
								
									
                                <form:form action="createNewUsers" id="createUsers" modelAttribute="createUser">

                                   <%--  <div class="form-line">
                                        <div class="label"><label>Username</label></div>
                                        <form:input path="username"  class="field medium"/>
                                        <form:hidden path="creatorId" value="${user.id}"/>
                                    </div> --%>                                    
                                     <div class="form-line">
                                        <div class="label"><label>Email</label></div>
                                        <form:hidden path="creatorId" value="${user.id}"/>
                                        <form:hidden path="groupId" value="${user.groupId}"/>
                                        <form:input path="email" class="field medium emailcheck" />
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
                                        <div class="label"><label>Job title</label></div>
                                        <form:input path="jobTitle"  class="field medium"/>
                                    </div>

                                    <div class="form-line">
                                        <div class="label"><label>Office</label></div>
                                       <form:select path="regionId" data-custom="true">
                                        <c:forEach var="region" items="${regions}">
                                         <option value="${region.id}">${region.regionName}</option>
                                        </c:forEach>
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

                                   

                                    <button type="submit" class="btn-control create"><i class="icon-create"></i><span>Create</span></button><!-- <a class="cancelBtn" href="#">CANCEL</a> -->

                                </form:form>

                            </div>
                        </div><!-- /.user-create-wrapper -->

                        <div class="user-mngt-wrapper clearFix">
						<c:forEach var="user" items="${users}">
                         <c:set var="pImage" value="${profileImagePath}${user.profileimage}"></c:set>              
                                        
                            <div class="user-item-wrapper" data-control="userItemWrapper">
                                <div class="user-item clearFix" data-control="userItemContent">
                                    <div class="user-item-left">
                                        <div class="user-item-content">
                                            <div class="user-item-info">
                                                <div class="user-item-image">
                                                <c:if test="${user.profileimage == null}">
                                        			<c:set var="pImage" value="resources/images/blank.gif"></c:set>
                                        		</c:if>
                                                    <img src="${pImage}" height="56">
                                                </div>
                                                <div class="user-item-txt">
                                                    <h4 class="user-item-name">${user.firstName} ${user.lastName} (${user.regionName})</h4>
                                                    <div class="user-item-note">${user.jobTitle}</div>
                                                     <!-- <div class="user-item-birth">date of birth: <strong>19 Sep 1980</strong></div> --> 
                                                    <div class="user-item-email">email: <a href="mailto:email@email.com">${user.email}</a></div>
                                                </div>
                                            </div>
                                            <div class="user-item-separator"></div>
                                            <div class="user-item-form" id="userRoleInfo">
                                            <input type="hidden" id="userId" value="${user.id}"/>
                                                <div class="form-line">
                                                    <label>${user.roleName}</label>
                                                </div>
                                                <div class="form-line">
                                                    <select id="roleId" data-custom="true">                                                   
                                                    <option id="0" >Assign Role</option>
                                                    <c:forEach var="role" items="${roles}">
                                                    <c:set var="roleSelected" value="" />
                                                    <c:if test="${user.roleId == role.id}">
                                                    	 <c:set var="roleSelected" value="selected" />
                                                    </c:if>
                                                        <option value="${role.id}" ${roleSelected}>${role.name}</option>
                                                    </c:forEach>
                                                    </select>
                                                </div>

                                                <!-- <div class="user-item-separator"></div>

                                                <div class="form-line">
                                                    <label class="user-resend-pass">Password - <a href="#">re-send password</a>
                                                    <small>we will use email from user profile</small>
                                                    </label>

                                                </div>

                                                <div class="user-item-separator"></div>

                                                <div class="form-line">
                                                    <label>Permission</label>
                                                    <span>Yes</span>
                                                    <span>No</span>
                                                </div> -->

                                                <!-- <div class="form-line">
                                                    <div>
                                                        <label>Post comments</label>
                                                        <span><input type="radio" name="user1"></span>
                                                        <span><input type="radio" name="user1"></span>
                                                    </div>
                                                    <div>
                                                        <label>Create new campaign</label>
                                                        <span><input type="radio" name="user2"></span>
                                                        <span><input type="radio" name="user3"></span>
                                                    </div>
                                                    <div>
                                                        <label>Create new users</label>
                                                        <span><input type="radio" name="user4"></span>
                                                        <span><input type="radio" name="user5"></span>
                                                    </div>
                                                    <div>
                                                        <label>Post comments</label>
                                                        <span><input type="radio" name="user6"></span>
                                                        <span><input type="radio" name="user7"></span>
                                                    </div>
                                                    <div>
                                                        <label>Create new campaign</label>
                                                        <span><input type="radio" name="user8"></span>
                                                        <span><input type="radio" name="user8"></span>
                                                    </div>
                                                </div> -->

                                            </div>
                                            <div class="user-item-separator"></div>
                                            <!--  <div class="user-item-msg">
                                                <a href="#" class="btn-send-msg"><i class="icon-send-msg"></i>Send message</a>
                                            </div> -->
                                        </div>
                                    </div>
                                    <div class="user-item-right">
                                        <a href="#" class="updown-arrow" data-control="userBtn"><i class="icon-arrow-big"></i></a>
                                    </div>
                                </div>
                            </div>

						</c:forEach>


                        </div>

                    </div>


                </div>
            </div>
        </div><!-- /.content-wrapper -->

        <div class="footer-wrapper" id="footer">
            <div class="footer-container">
                <div class="footer">
                    &copy; 2010 JEMM MEDIA GROUP, LLC ALL RIGHTS RESERVED.
                </div>
            </div>
        </div><!-- /.footer-wrapper -->

    </div>
</div><!-- /.page -->

<%@ include file="scripts.html"%>
</body>
</html>