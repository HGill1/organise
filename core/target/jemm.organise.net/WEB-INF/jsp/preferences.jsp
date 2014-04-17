<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
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
                        <a href="index">&larr; show dashboard</a>
                    </div>
                    <c:set var="tSelected" value="${tabSelected}" />
					<c:if test="${empty tabSelected || tabSelected == 2}">
						<c:set var="tSelected" value="2" />
					</c:if>
					
                       
                    <div class="content-main preferences">

                        <div id="tabsPreferences" class="ui-tabs-vertical ui-helper-clearfix">
                            <ul id ="prefList">
                            	 <c:if test="${user.roleId == 1 || user.roleId == 2 || user.roleId == 3}">    
                                <li><a href="#tabs-1" class="pref1">Account details</a></li>
                                </c:if>
                                <li ${tSelected == 2?' class="ui-tabs-selected"':''}><a href="#tabs-2" class="pref2">profile details</a></li>
                                <li><a href="#tabs-3" class="pref3">Security preferences</a></li>
                                <li><a href="#tabs-4" class="pref4">Email notifications</a></li>
                                <c:if test="${user.roleId == 1 || user.roleId == 2 || user.roleId == 3}">                                
                                <li ${tSelected == 5?' class="ui-tabs-selected"':''}><a href="#tabs-5" class="pref5">User management</a></li>
                                </c:if>
                                <!-- <li><a href="#tabs-6" class="pref6">Topics manager</a></li> -->
                                <c:if test="${user.roleId == 1 || user.roleId == 2 || user.roleId == 3}">
                                <li><a href="#tabs-7" class="pref7">Locations and Groups</a></li>
                                </c:if>
                                <!-- <li><a href="#tabs-8" class="pref8">View Statistic</a></li> -->
                                <c:if test="${user.roleId == 1 || user.roleId == 2 || user.roleId == 3}">
                                <li${tSelected == 9?' class="ui-tabs-selected"':''}><a href="#tabs-9" class="pref9">Create important message</a></li>
                                </c:if>
                            </ul>
                            <c:if test="${user.roleId == 1 || user.roleId == 2 || user.roleId == 3}">    
                            <div id="tabs-1">
							
							<div id="accountInfo">
                                <%-- <div class="block-gray">
                                <c:set var="pEndDateText" value=""/>
										<c:if test="${packageEndDate != null}">
											<joda:format value="${packageEndDate}" pattern="MMMM dd, yyyy" var="pEndDate" />
										<c:set var="pEndDateText" value="package runs until ${pEndDate}"/>

										</c:if>

										<div class="title-block"><strong>Account Details</strong> - You use: <strong>${packageName} package</strong> ${pEndDateText}</div>

                                    <div class="pref-acc-wr clearFix">
                                        <div class="pref-acc-r float-right">
<p><strong>${tasksCompleted}</strong> tasks - <span class="green">completed</span></p>
<p><strong>${tasksOpen}</strong> tasks - <span class="black">OPEN</span></p>
<p><strong>${tasksPending}</strong> tasks - <span class="gray">Pending</span></p>
                                        </div>
                                        <div class="pref-acc-l">
This account was created on <joda:format value="${groupCreationDate}" pattern="MMMM dd, yyyy" /><br>
You have <strong>${regionsCount}</strong> location in a system and <strong>${usersCount}</strong> users are working with you.<br>
<strong>${topicsCount}</strong> topics created in a system and <strong>${commentsCount}</strong> comments posted.<br>
<c:if test="${user.allowedStorage >0}">
 You are using <strong>${folderSizeInMB} mb</strong> which is <strong>${spacePercentage}%</strong> of your disk space.
 </c:if>
                                        </div>
                                    </div>
                                  </div>   --%>

                                </div>



                                <div class="block-gray">
                                    <div class="title-block"><strong>Upgrade options</strong> - any time you can switch to smaller or bigger package</div>

                                    <div class="pricing-wr">
                                        <div class="pricing-cols">

                                            <div class="pricing-col">
                                                <div class="pricing-item-col ${(user.packageType == 2) ? 'gold':''}">
                                                    <div class="pricing-header">bronze</div>
                                                    <div class="pricing-content">
                                                        <div class="pricing-price">
                                                            <p>$50</p>

                                                            <p>per month</p>
                                                        </div>
                                                        <div class="pricing-collaborator">
                                                            <p><strong>10</strong> collaborators</p>
                                                        </div>
                                                        <div class="pricing-storage">
                                                            <p>10GB</p>

                                                            <p>storage</p>
                                                        </div>
                                                        <div class="pricing-info">
                                                            <p><strong>Community support and email</strong> <br>support
                                                            </p>
                                                        </div>
                                                    </div>


                                                    <div class="pricing-footer">
                                                        <form action="<fmt:message key="paypal.url"/>" method="post">
                                                            <input type="hidden" name="cmd" value="_xclick-subscriptions">
                                                            <input type="hidden" name="business" value="<fmt:message key="paypal.account"/>">
                                                            <input type="hidden" name="item_name" value="Organise Bronze Plan">
                                                            <input type="hidden" name="item_number" value="1">
                                                            <input type="hidden" name="groupId" value="2">
                                                            <input type="hidden" name="no_shipping" value="1">
                                                            <input type="hidden" name="return" value="<fmt:message key="return.url"/>">
                                                            <input type="hidden" name="rm" value="2">
                                                            <input type="hidden" name="cancel_return" value="<fmt:message key="cancel.preferences.url"/>">
                                                            <input type="hidden" name="currency_code" value="USD">


                                                            <input type="hidden" name="a3" value="50.00">
                                                            <input type="hidden" name="p3" value="1">
                                                            <input type="hidden" name="t3" value="M">
                                                            <input type="hidden" name="src" value="1">
                                                            <input type="hidden" name="sra" value="1">

                                                            <input type="submit" value="Choose plan">
                                                        </form>
                                                    </div>


                                                </div>
                                            </div>
                                            <div class="pricing-col">
                                                <div class="pricing-item-col ${(user.packageType == 3) ? 'gold':''}">
                                                    <div class="pricing-header">SILVER</div>
                                                    <div class="pricing-content">
                                                        <div class="pricing-price">
                                                            <p>$100</p>

                                                            <p>per month</p>
                                                        </div>
                                                        <div class="pricing-collaborator">
                                                            <p><strong>25</strong> collaborators</p>
                                                        </div>
                                                        <div class="pricing-storage">
                                                            <p>25GB</p>

                                                            <p>storage</p>
                                                        </div>
                                                        <div class="pricing-info">
                                                            <p><strong>Email, phone and webinar training</strong> <br>support
                                                            </p>
                                                        </div>
                                                    </div>


                                                    <div class="pricing-footer">
                                                        <form action="<fmt:message key="paypal.url"/>" method="post">
                                                            <input type="hidden" name="cmd" value="_xclick-subscriptions">
                                                            <input type="hidden" name="business" value="<fmt:message key="paypal.account"/>">
                                                            <input type="hidden" name="item_name" value="Organise Silver Plan">
                                                            <input type="hidden" name="item_number" value="3">
                                                            <input type="hidden" name="groupId" value="1">
                                                            <input type="hidden" name="no_shipping" value="1">
                                                            <input type="hidden" name="return" value="<fmt:message key="return.url"/>">
                                                            <input type="hidden" name="rm" value="2">
                                                            <input type="hidden" name="cancel_return" value="<fmt:message key="cancel.preferences.url"/>">
                                                            <input type="hidden" name="currency_code" value="USD">


                                                            <input type="hidden" name="a3" value="100.00">
                                                            <input type="hidden" name="p3" value="1">
                                                            <input type="hidden" name="t3" value="M">
                                                            <input type="hidden" name="src" value="1">
                                                            <input type="hidden" name="sra" value="1">

                                                            <input type="submit" value="Choose plan">
                                                        </form>
                                                    </div>


                                                </div>
                                            </div>
                                            <div class="pricing-col">
                                                <div class="pricing-item-col ${(user.packageType == 4) ? 'gold':''}">
                                                    <div class="pricing-header">GOLD</div>
                                                    <div class="pricing-content">
                                                        <div class="pricing-price">
                                                            <p>$185</p>

                                                            <p>per month</p>
                                                        </div>
                                                        <div class="pricing-collaborator">
                                                            <p><strong>50</strong> collaborators</p>
                                                        </div>
                                                        <div class="pricing-storage">
                                                            <p>50GB</p>

                                                            <p>storage</p>
                                                        </div>
                                                        <div class="pricing-info">
                                                            <p><strong>Email, phone and webinar training</strong> <br>support
                                                            </p>
                                                        </div>
                                                    </div>


                                                    <div class="pricing-footer">
                                                        <form action="<fmt:message key="paypal.url"/>" method="post">
                                                            <input type="hidden" name="cmd" value="_xclick-subscriptions">
                                                            <input type="hidden" name="business" value="<fmt:message key="paypal.account"/>">
                                                            <input type="hidden" name="item_name" value="Organise Gold Plan">
                                                            <input type="hidden" name="item_number" value="4">
                                                            <input type="hidden" name="groupId" value="1">
                                                            <input type="hidden" name="no_shipping" value="1">
                                                            <input type="hidden" name="return" value="<fmt:message key="return.url"/>">
                                                            <input type="hidden" name="rm" value="2">
                                                            <input type="hidden" name="cancel_return" value="<fmt:message key="cancel.preferences.url"/>">
                                                            <input type="hidden" name="currency_code" value="USD">


                                                            <input type="hidden" name="a3" value="185.00">
                                                            <input type="hidden" name="p3" value="1">
                                                            <input type="hidden" name="t3" value="M">
                                                            <input type="hidden" name="src" value="1">
                                                            <input type="hidden" name="sra" value="1">

                                                            <input type="submit" value="Choose plan">
                                                        </form>
                                                    </div>


                                                </div>
                                            </div>
                                            <div class="pricing-col">
                                                <div class="pricing-item-col ${(user.packageType > 4) ? 'gold':''}">
                                                    <div class="pricing-header">PLATINUM</div>
                                                    <div class="pricing-content">
                                                        <div class="pricing-price">
                                                            <div style="font-size: 24px;font-weight: 700;color: #333333;line-height: 30px">
                                                                Upon <br>Request
                                                            </div>
                                                        </div>
                                                        <div class="pricing-collaborator">
                                                            <div style="font-size: 18px">Varying</div>
                                                        </div>
                                                        <div class="pricing-storage">
                                                            <div style="font-size: 18px">Unlimited</div>
                                                        </div>
                                                        <div class="pricing-info">
                                                            <p><strong>Priority email, phone and webinar
                                                                training</strong> <br>support
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <div class="pricing-footer">
                                                        <a href="mailto:info@organise.net?subject=Please send me more detail about Platinum Plan">Choose plan</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>



                                </div>



                            </div>
                            </c:if>
                            <div id="tabs-2">
                                <div class="form-inline clearFix userpic-wr">
                                    <form:form id='userInfo' action="" modelAttribute="user">
                                    <form:hidden path="id"/>
                                        <div class="form-inline-header">Profile details</div>
                                        <div class="form-inline-content">

                                            <div class="clearFix userpic-wr">
                                                <div class="float-left">
                                                    <div class="form-line">
                                                        <div class="label"><label>First name</label></div>
                                                        <form:input path="firstName" class="field" />
                                                    </div>
                                                    <div class="form-line">
                                                        <div class="label"><label>Family name</label></div>
                                                        <form:input path="lastName" class="field" />
                                                    </div>
                                                </div>
                                                <div class="float-right userpic clearFix">
                                                    <div class="userpic-img">
                                                       <!--  <img src="images/user_sample1.jpg" width="100" height="100"> -->

															<c:if test="${user.profileimage == null}">
																<c:set var="pImage" value="resources/images/blank.gif"></c:set>
															</c:if>
															<img id="profileInnerPhoto" src="${pImage}" width="100" height="100">

														</div>
                                                    <div class="userpic-control">
click on a link to
<a href="#" id="uploadProfileInnerImg">change user pic</a>
                                                        <input id="profileInnerImage" type="file" name="file" data-url="uploadProfileImage.htm" multiple style="opacity: 0; filter: alpha(opacity :   0);position:absolute;z-index:3;">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-line">
                                                <div class="label"><label>Email</label></div>
                                                <form:input path="email" class="field"/>
                                            </div>

                                            <div class="form-line">
                                                <div class="label"><label>Job title</label></div>
                                                <form:input path="jobTitle" class="field" />
                                            </div>

                                            <div class="form-line">
                                                <div class="label"><label>Office</label></div>
                                                
                                                 <form:select path="regionId" data-custom="true">
		                                            <form:options items="${regionsMap}"/>
		                                        </form:select>
                                               
                                            </div>

                                        </div>
                                        <div class="form-inline-footer">
                                            <button type="submit" class="btn-control create"><span>Save</span></button>
                                            <!-- <a class="cancelBtn" href="#">CANCEL</a> -->
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                            <div id="tabs-3">

                                <div class="form-inline clearFix">
                                     <form:form id='userPass' action="">
                                       <input type="hidden" id="id" name="id" value="${user.id}">
                                        <div class="form-inline-header">Security preferences</div>
                                        <div class="form-inline-content">
                                            <div class="form-line">
                                                <div class="label"><label>Old password</label></div>
                                                <input type="password" id="oldPassword" class="field">
                                                <label id="oldpasserror" class="error hide">Incorrect old password</label>
                                            </div>

                                            <div class="form-line">
                                                <div class="label"><label>New password</label></div>
                                                <input type="password" id="password" name="password" class="field">
                                            </div>

                                            <div class="form-line">
                                                <div class="label"><label>Re-type password</label></div>
                                                <input type="password" id="confirmPassword" name="confirmPassword" class="field">
                                            </div>

                                        </div>
                                        <div class="form-inline-footer">
                                            <button type="submit" id="changePassBtn" name="changePassBtn" class="btn-control create"><span>Save</span></button>
                                            <!-- <a class="cancelBtn" href="#">CANCEL</a> -->
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                             <div id="tabs-4">

                                <div class="form-inline clearFix">
                                    <form>
                                        <div class="form-inline-header">Manage email notifications</div>
                                        <div class="form-inline-content">
                                            <div class="form-line form-inline-table">
                                                <label>Receive Email When:</label>
                                                <span>Yes</span>
                                                <span>No</span>
                                            </div>

                                            <div class="form-line form-inline-table">
                                                <div>
                                                    <label>New Task is Assigned</label>
                                                    <span><input type="radio" id="assignedTaskEmailTrue" name="assignedTaskEmail" ${user.taskAddEmail == 'true'? 'checked':''}></span>
                                                    <span><input type="radio" id="assignedTaskEmailFalse" name="assignedTaskEmail" ${user.taskAddEmail == 'false'? 'checked':''}></span>
                                                </div>
                                                <div>
                                                    <label>Comments posted</label>
                                                    <span><input type="radio" id="postCommentTrue" name="postComment" ${user.taskUpdateEmail == 'true'? 'checked':''}></span>
                                                    <span><input type="radio" id="postCommentFalse" name="postComment" ${user.taskUpdateEmail == 'false'? 'checked':''}></span>
                                                </div>
                                               <!--  <div> Response to Comment</div>
                                                <div>Overdue Tasks</div> -->
                                            </div>

                                        </div>
                                        <div class="form-inline-footer">
                                           <!--  <button type="submit" class="btn-control create"><span>Save</span></button>
                                            <a class="cancelBtn" href="#">CANCEL</a> -->
                                        </div>
                                    </form>
                                </div>

                            </div> 
                            <c:if test="${user.roleId == 1 || user.roleId == 2 || user.roleId == 3}">
                            <div id="tabs-5">
                                    <div class="user-mngt-wrapper clearFix">
                                     <%-- <div class="user-mngt-header clearFix">
                                            <div class="user-mngt-filters-container float-left clearFix">
                                                <div class="user-mngt-filters-text content-title float-left">Quick filters: </div>
                                                <div class="user-mngt-filter-group float-left">
                                                    <select data-custom="true">
                                                    <option value="0">Group Name</option>
                                                    <c:forEach var="aDept" items="${allDepts}" varStatus="theCount">
                                                        <option value="${aDept.id}">${aDept.name}</option>
                                                    </c:forEach>    
                                                    </select>
                                                </div>
                                                <div class="user-mngt-filter-activity float-left clearFix">
                                                    <a class="user-activity-status float-left" href="#">Active users</a>
                                                    <a class="user-activity-status float-left active" href="#">Disabled users</a>
                                                </div>
                                            </div>
                                            
                                            <div class="user-mngt-search-container float-right clearFix">
                                                <div class="user-mngt-search-text content-title float-left">Search: </div>
                                                <div class="search-wrapper float-left">
                                                    <form id="searchForm" action="filterUsers">
                                                        <input id="userSearchBox" class="search-field" type="text" placeholder="Search">
                                                    </form>
                                                </div>
                                            </div>
                                        </div> --%> 
										<div class="form-errors">${msgUser}</div>
										<div class="form-errors">${packageError}</div>
                                        <div class="user-item-wrapper">
                                            <div class="user-item">
                                                <div class="user-add-btn">
                                                    <a data-control="addUserBtn" href="#">Add new user</a>
                                                </div>
                                            </div>
                                            <div data-control="addUserForm" class="user-add-form hide">
                                                <div class="add-form-arrow"></div>
                                                <form:form action="createNewUsers" id="createUsers" modelAttribute="createUser">
                                                    <div class="form-inline-header">Add new user</div>
                                                    <input type="hidden" id="tabSelected" name="tabSelected" value="5" />
                                                    <form:hidden path="creatorId" value="${user.id}"/>
                                        			<form:hidden path="groupId" value="${user.groupId}"/>
                                        
                                                    <div class="form-inline-content">

                                                        <div class="clearFix form-inline-fields">
                                                            <div class="float-left">
                                                                <div class="form-line">
                                                                    <div class="label"><label>First name</label></div>
                                                                     <form:input path="firstName" class="field"/>
                                                                </div>
                                                            </div>
                                                            <div class="float-right clearFix">
                                                                <div class="form-line">
                                                                    <div class="label"><label>Family name</label></div>
                                                                    <form:input path="lastName" class="field"/>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="clearFix form-inline-fields">
																<div class="float-left">
																	<div class="form-line">
																		<div class="label"><label>Job title</label></div>
																		<form:input path="jobTitle" class="field" />
																	</div>
																</div>
																<div class="float-right clearFix">
                                                                <div class="form-line">
                                                                    <div class="label"><label>Office</label></div>
																		<form:select path="regionId" data-custom="true">
																			<form:options items="${regionsMap}" />
																		</form:select>
																	</div>
                                                            </div>
														</div>
														

                                                        <div class="form-line form-inline-fields">
                                                            <div class="label"><label>Email</label></div>
                                                            <form:input path="email" class="field emailcheck" />
                                                        </div>

                                                        <div class="clearFix form-inline-fields">
                                                            <div class="float-left">
                                                                <div class="form-line">
                                                                    <div class="label"><label>Groups</label></div>
                                                                    <a class="user-add-permissions" href="#">Choose Groups<span class="selectBox-arrow"></span></a>
                                                                    <div class="dept-dropdown" data-control="deptDropdown">
	                                                                     <c:forEach var="dept" items="${allDepts}">
	                                                                        <label class="user-add-permissions-item clearFix">
	                                                                            <form:checkbox class="user-permission-option" path="depts" name="user-permissions[]" value="${dept.id}" />
	                                                                            <span class="user-permission-option-text  ">${dept.name}</span>
	                                                                        </label>
	                                                                    </c:forEach>
	                                                                     <span class="arrowForDropdown"></span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="float-right clearFix">
                                                                <div class="form-line relative">
                                                                    <div class="label"><label>Topic permissions</label></div>
                                                                    <a class="user-add-permissions" href="#">Choose topics<span class="selectBox-arrow"></span></a>
                                                                    <div class="user-add-permissions-options" data-control="topicsDropdown">
                                                                    <c:forEach var="source" items="${feedSources}">
                                                                        <label class="user-add-permissions-item clearFix">
                                                                            <form:checkbox class="user-permission-option" path="topics" name="user-permissions[]" value="${source.id}" />
                                                                            <span class="user-permission-option-text  ">${source.name}</span>
                                                                        </label>
                                                                    </c:forEach>
                                                                        
                                                                        <span class="arrowForDropdown"></span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="form-line form-inline-fields">
                                                            <div class="label"><label>User permission </label></div>
                                                            <form:select path="roleId" data-custom="true">
						                                        <c:forEach var="role" items="${roles}">
						                                        <c:if test="${user.roleId <= role.id}">
						                                         <option value="${role.id}">${role.name}</option>
						                                        </c:if> 
						                                        </c:forEach>
						                                  	</form:select>
														</div>

                                                    </div>
                                                    <div class="form-inline-footer">
                                                        <button type="submit" class="btn-control create">
                                                            <span>Save</span></button>
                                                        <a class="cancelBtn" id="cancelUserForm" href="#">CANCEL</a>
                                                    </div>
                                                </form:form>

                                            </div>
                                        </div>
										<div id="usersMgmtDiv">
										</div>

									<%-- <c:forEach var="iuser" items="${users}">
                         <c:set var="pImage" value="${profileImagePath}${iuser.profileimage}"></c:set> --%>   
                                        <%-- <div class="user-item-wrapper" data-control="userItemWrapper">
                                            <div class="user-item clearFix">
                                                <div class="user-item-left">
                                                    <div class="user-item-content">
                                                        <div id="usersInfoDiv" class="user-item-info">
                                                            <div class="user-item-image">
                                                                <c:if test="${iuser.profileimage == null}">
				                                        			<c:set var="pImage" value="resources/images/blank.gif"></c:set>
				                                        		</c:if>
				                                                    <img src="${pImage}" height="56">
                                                            </div>
                                                            <div class="user-item-txt">
                                                                <h4 class="user-item-name">${iuser.firstName} ${iuser.lastName} (${iuser.regionName})</h4>

                                                                <div class="user-item-note">${iuser.jobTitle}</div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="user-item-right">
                                                    <a href="#" class="updown-arrow" data-control="userBtn"><i class="icon-arrow-big"></i></a>
                                                </div>
                                            </div>

                                            <div data-control="userDetails" class="user-details hide">
										
                                                    <div id="userButtonsDiv" class="form-inline-header">User manager 
                                                    <span class="pull-right">
	                                                    <c:set var="disableButtonUserId" value="disableUser" />
	                                                    <c:set var="disableButtonText" value="disable user" />
	                                                    <c:if test="${empty iuser.roleId || iuser.roleId == 0}">
		                                                    <c:set var="disableButtonUserId" value="enableUser" />
		                                                    <c:set var="disableButtonText" value="enable user" />
	                                                    </c:if>
	                                                    
		                                                    <button class="btn-small" id="${disableButtonUserId}" type="button">${disableButtonText}
		                                                    <input type="hidden" id="disableUserId" value="${iuser.id}" name="disableUserId"/>
		                                                    </button>
	                                                    <button class="btn-small" type="button" data-control="userDestroy">delete user
	                                                    <input type="hidden" id="delUserId" value="${iuser.id}" name="delUserId"/>
	                                                    </button>
	                                                    
	                                                    </span>
                                                    </div>
                                                    <div class="form-inline-content">

                                                        <div class="form-line" id="userEmailDiv">
                                                            <div class="label"><label>email: <strong><a href="mailto:${iuser.email}">${iuser.email}</a></strong></label></div>
                                                        </div>
                                                        <div class="user-item-separator"></div>

                                                        <div class="form-line form-inline-fields" id="userRoleInfo">
                                                        <input type="hidden" id="userId" value="${iuser.id}"/>
                                                            <div class="label"><label>User permission</label></div>
                                                            <c:set var="classRolesHideshow" value="show" />
                                                            <c:if test="${user.roleId == 3 && iuser.roleId !=0 && iuser.roleId < 3}">
                                                            	<div class="form-inline-header">Super Administrator </div>
                                                            	<c:set var="classRolesHideshow" value="hide" />
                                                            </c:if>
                                                            <select id="selRoleId" data-custom="true" class="${classRolesHideshow}">                                                   
			                                                    <option value="0" >Assign Role</option>
			                                                    <c:forEach var="role" items="${roles}">
			                                                    <c:if test="${user.roleId <= role.id}">
				                                                    <c:set var="roleSelected" value="" />
				                                                    <c:if test="${iuser.roleId == role.id}">
				                                                    	 <c:set var="roleSelected" value="selected" />
				                                                    </c:if>
			                                                        <option value="${role.id}" ${roleSelected}>${role.name}</option>
			                                                    </c:if>
			                                                    </c:forEach>
		                                                    </select>
                                                        </div>

                                                    </div>
                                                    
                                                    <c:if test="${iuser.roleId != 0}">
	                                                    <div id="userDeptDiv" class="form-inline-footer">
	                                                     <form:form action="" id="${iuser.id}" modelAttribute="createUser" onsubmit="modifyUserDept(${iuser.id});return false;">
	                                                    <form:hidden path="id" value="${iuser.id}"/>
	                                                    <div class="float-left">
	                                                                <div class="form-line">
	                                                                    <div class="label"><label>Groups</label></div>
	                                                                    <a class="user-add-permissions" href="#">Choose groups<span class="selectBox-arrow"></span></a>
	                                                                    <div class="dept-dropdown dept-user-dropdown" data-control="deptDropdown">
		                                                                     <c:forEach var="aDept" items="${allDepts}" varStatus="theCount">
		                                                                     <c:set var="checkDept"  value="" />
		                                                                    <c:forEach var="userDept"  items="${iuser.dept}">  
		                                                                    	 <c:if test="${userDept.id == aDept.id}">
		                                                                     		<c:set var="checkDept"  value="checked" />
		                                                                     	</c:if>
		                                                                     </c:forEach>
		                                                                        <label class="user-add-permissions-item clearFix">
		                                                                            <input class="user-permission-option" name="depts" value="${aDept.id}" ${checkDept}  type="checkbox"/>
		                                                                            <span class="user-permission-option-text  ">${aDept.name}</span>
		                                                                        </label>
		                                                                    </c:forEach>
		                                                                     <span class="arrowForDropdown"></span>
	                                                                    </div>
	                                                                </div>
	                                                            </div>
	                                                        <div style="float:left;margin-bottom:5px;">
		                                                        <button data-control="userInfoSaveButton"  type="submit" class="btn-control create">
		                                                            <span>Save Groups</span></button>
		                                                         <a data-control="userInfoCancelButton" class="cancelBtn" href="#">CANCEL</a> 
	                                                        </div>
	                                                        </form:form>
	                                                    </div>
                                                    </c:if>
                                            </div>


                                        </div> --%>
									<%-- </c:forEach> --%>
                                    </div>
                            </div>
                            </c:if>
                            <!-- <div id="tabs-6">
                                <div class="block-gray clearFix">
                                    <div class="title-block"><strong>Topics manager</strong>
                                    <span class="pull-right"><a href="#" class="task-new"><i class="icon-plus"></i>Add new topic</a></span>
                                    </div>
                                    <table class="source-table">
                                        <tr class="source-header">
                                            <th>Topic name</th>
                                            <th>Visible by</th>
                                            <th>Url</th>
                                            <th>Actions</th>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="source-type-name" style="background: #144c9a;">CM Trade</div>
                                                <div class="source-color" style="color: #144c9a;">#144c9a</div>
                                            </td>
                                            <td>
                                                <div class="source-visible">
                                                    Tony Soprano,
                                                    Tony Soprano,
                                                    Tony Soprano,
                                                    Tony Soprano
                                                </div>
                                            </td>
                                            <td>
                                                <div class="source-url">
                                                    <a href="#">http://rss.echosign.com/rss?v=PR1:dT05MDg0MjMm:-CmUzUqRUZpSJXbECOydQcqA_jVBoI13gNUz2kf_4WE&feedType=rss_2.0</a>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="source-actions">
                                                    <a href="#">Edit</a>
                                                    <a href="#" data-control="sourceDestroy">Destroy</a>
                                                </div>
                                            </td>
                                        </tr>

                                    </table>
                                </div>
                            </div> -->
                            <c:if test="${user.roleId == 1 || user.roleId == 2 || user.roleId == 3}">
                            <div id="tabs-7">
                            
                            <div class="form-inline locationsGroupsContainer clearFix">
                                    <form action="addRegion">
                                    <input type="hidden" id="formSubmitted" name ="formClicked" value="addRegion"> 
                                        <div class="form-inline-header">Locations and offices</div>
                                        <div class="form-inline-content">
                                            <div class="dialog-region-content">
                                            
                                                <div class="form-line-inline" data-control="regionAddField">
                                                    <div class="label"><label>New location</label></div>
                                                    <input class="field xsmall" type="text" placeholder="Write here office / location name">
                                                    <button class="btn-small" type="button">add</button>
                                                    <input class="btn-small" type="reset" value="cancel">
                                                </div>

                                                <div class="label"><label>Exist locations</label></div>

                                                <table id="regionTable" class="source-table dialog" data-control="regionEditList">
                                                    <tr class="source-header">
                                                        <th>Region name</th>
                                                        <th style="width: 104px">Action</th>
                                                    </tr>
                                                </table>
                                            </div>

                                        </div>
                                        <div class="form-inline-footer">
                                           <!--  <button type="submit" class="btn-control create"><span>Save</span></button>
                                            <a class="cancelBtn" href="#">CANCEL</a> -->
                                        </div>
                                    </form>
                                </div>
                                
                                 
                                
                                <div class="form-inline locationsGroupsContainer clearFix">
                                    <form action="addDept">
                                    <input type="hidden" id="formSubmitted" name ="formClicked" value="addDept">
                                        <div class="form-inline-header">Groups</div>
                                        <div class="form-inline-content">
                                            <div class="dialog-region-content">
                                                <div class="form-line-inline" data-control="regionAddField">
                                                    <div class="label"><label>New group</label></div>
                                                    <input class="field xsmall" type="text" placeholder="Group name">
                                                    <button class="btn-small" type="button">add</button>
                                                    <input class="btn-small" type="reset" value="cancel">
                                                </div>

                                                <div class="label"><label>Exist groups</label></div>

                                                <table id="deptTable" class="source-table dialog" data-control="regionEditList">
                                                    <tr class="source-header">
                                                        <th>Name</th>
                                                        <th style="width: 104px">Action</th>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>                                        
                                    </form>
                                </div>
                              
                            </div>
                            <%-- <div id="tabs-7">
                                <div class="form-inline locationsGroupsContainer clearFix">
                                    <form action="addRegion">
                                    <input type="hidden" id="formSubmitted" name ="formClicked" value="addRegion"> 
                                        <div class="form-inline-header">Locations and offices</div>
                                        <div class="form-inline-content">
                                            <div class="dialog-region-content">
                                            
                                                <div class="form-line-inline" data-control="regionAddField">
                                                    <div class="label"><label>New location</label></div>
                                                    <input class="field xsmall" type="text" placeholder="Write here office / location name">
                                                    <button class="btn-small" type="button">add</button>
                                                    <input class="btn-small" type="reset" value="cancel">
                                                </div>

                                                <div class="label"><label>Exist locations</label></div>

                                                <table class="source-table dialog" data-control="regionEditList">
                                                    <tr class="source-header">
                                                        <th>Region name</th>
                                                        <th style="width: 104px">Action</th>
                                                    </tr>
                                                    <c:forEach var="region" items="${regions}">
                                                    <tr>
                                                        <td class="regionName">${region.regionName}</td>
                                                        <td class="region-edit-btns" data-control="region-edit-del">
                                                        <input type="hidden" name='region-id' id="region-id" class='region-id' value ="${region.id}"/>
                                                            <a href="#" data-control="regionEdit">Edit</a><a href="#" data-control="delRegion">Delete</a>
                                                        </td>
                                                    </tr>
                                                    </c:forEach>
                                                </table>
                                            </div>

                                        </div>
                                        <div class="form-inline-footer">
                                           <!--  <button type="submit" class="btn-control create"><span>Save</span></button>
                                            <a class="cancelBtn" href="#">CANCEL</a> -->
                                        </div>
                                    </form>
                                </div>
                                <div class="form-inline locationsGroupsContainer clearFix">
                                    <form action="addDept">
                                    <input type="hidden" id="formSubmitted" name ="formClicked" value="addDept">
                                        <div class="form-inline-header">Groups</div>
                                        <div class="form-inline-content">
                                            <div class="dialog-region-content">
                                                <div class="form-line-inline" data-control="regionAddField">
                                                    <div class="label"><label>New group</label></div>
                                                    <input class="field xsmall" type="text" placeholder="Group name">
                                                    <button class="btn-small" type="button">add</button>
                                                    <input class="btn-small" type="reset" value="cancel">
                                                </div>

                                                <div class="label"><label>Exist groups</label></div>

                                                <table class="source-table dialog" data-control="regionEditList">
                                                    <tr class="source-header">
                                                        <th>Name</th>
                                                        <th style="width: 104px">Action</th>
                                                    </tr>
                                                    <c:forEach var="dept" items="${allDepts}">
                                                    <tr>
                                                        <td class="regionName">${dept.name}</td>
                                                        <td class="region-edit-btns" data-control="region-edit-del">
                                                        <input type="hidden" name='region-id' id="region-id" class='region-id' value ="${dept.id}"/>
                                                            <a href="#" data-control="regionEdit">Edit</a><a href="#" data-control="delRegion">Delete</a>
                                                        </td>
                                                    </tr>
                                                    </c:forEach>
                                                </table>
                                            </div>
                                        </div>                                        
                                    </form>
                                </div>
                            </div> --%>
                            </c:if>
                            <%-- <div id="tabs-8">
                               <div class="block-gray clearFix">
                                    <div class="title-block stats-header-filter"><strong>View statistic</strong>
                                    <span class="pull-right">
                                        <div class="form-line-inline">
                                        <form action="statsPage"  id="stats"> 
                                            <label><strong>Last</strong></label>
                                            <input class="field small" id="numberOfDays" value="7"  name="numberOfDays" type="text">
                                            <label><strong>days</strong></label>
                                            <label class="sep">or</label>
                                            
                                            <select id="selectNumberOfDays" name="selectNumberOfDays" data-custom="true">
			                                	<option value="0">select Options</option>
			                                	<option value="1">Today</option>
			                                	<option value="2">Yesterday</option>
			                                	<option value="7">Past 7 Days</option>
			                                	<option value="30">Past 30 Days</option>
			                                </select>
                                            <button class="btn-small" type="submit">show</button>
                                            </form>
                                        </div>
                                    </span>
                                    </div>


                                    <div class="pref-stats-contol">
                                        <a href="#" >Tasks created by user</a>
                                        <a href="#" class="active">Tasks comleted by user</a>
                                        <a href="#">Number of tasks assigned to user</a>
                                    </div>


                                    <div class="source-container pref-scroll">
                                        <table class="source-table">
                                            <tr class="source-header">
                                                <th></th>
					                                <c:forEach var="day" items="${dateWiseDaysDisplay}">
					                                <th>${day}</th>
					                                </c:forEach>
					                                <th class="foo">Total</th>
					                                <th class="foo">Percentage</th>
                                            </tr>
                                            <c:set var="total" value="0" />
                                            <c:forEach var="row" items="${dataForTaskCreated}">
												    <tr>
												    <c:set var="row_total" value="0"/>
												        <td>${row.key}</td>
												        <c:forEach var="column" items="${row.value}">
												            <td>${column.value}</td>
												            <c:set var="row_total" value="${column.value}"/>
												            <c:if test="${empty column_totals_tasks_created[column.key]}">
												                <c:set target="${column_totals_tasks_created}" property="${column.key}" value="0"/>
												            </c:if>
												            <c:set target="${column_totals_tasks_created}" property="${column.key}" value="${column_totals_tasks_created[column.key] + column.value}"/>
												        </c:forEach>
												         <td class="foo"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${(row_total*100)/totalcreated}" />%</td>
												    </tr>
												</c:forEach>
                                           
					                            <tr class="foo">
						                            <td>TOTAL</td>
						                            <c:forEach var="column_total" items="${column_totals_tasks_created}">
						     							 <td>${column_total.value}</td> 
						  							</c:forEach>
						                                <td class="foo"></td> 
					                            </tr>


                                        </table>
                                    </div>

                               </div>
                            </div> --%>
                            <c:if test="${user.roleId == 1 || user.roleId == 2 || user.roleId == 3}">
                            <div id="tabs-9">
                                <div class="form-inline clearFix">
                                <div class="form-errors">${announcementSuccessMsg}</div>
                                     <form:form action="createAnnouncement" id="announcements" modelAttribute="announcement">
							<input type="hidden" id="creatorId" name="creatorId" value="${user.id}">
							<input type="hidden" id="groupId" name="groupId" value="${user.groupId}">
                                        <div class="form-inline-header">Create important announcement</div>
                                        <div class="form-inline-content">

                                            <div class="form-line">
                                                <div class="label">
                                                    <label for="form2432">Write your message</label>
                                                </div>
                                               <form:textarea path="message" class="textarea" placeholder="Type message here" />
                                            </div>

                                            <div class="form-line">
                                                <span data-control="datepicker" class="calendar-inline"><label>Date range</label> <strong>From</strong>
                                                 <form:input path="startsAt" placeholder="Choose date" data-control="datepickerFrom" /> <strong>To</strong> 
                                                 <form:input path="endsAt" placeholder="Choose date" data-control="datepickerTo" /></span>
                                            </div>

                                            <div class="form-line">
                                                <div class="label">
                                                    <label for="form2432">Who should recieve this message? </label>
                                                </div>
                                	<!-- <span class="checkbox-group">
                                    <input type="checkbox" id="form31231"> <label for="form31231">All</label>
                                    <input type="checkbox" id="form312"> <label for="form312">AU office</label>
                                    <input type="checkbox" id="form43242"> <label for="form43242">UK office</label>
                                    <input type="checkbox" id="form435"> <label for="form435">FR office</label>
                                	</span> -->
                                
                                	<form:select path="regionId" data-custom="true" multiple="true">
                                		<form:option value="0" label="All" />
		                                 <form:options items="${regionsMap}"/>
		                            </form:select>
                                  </div>

                                        </div>
                                        <div class="form-inline-footer">
                                            <button type="submit" class="btn-control create"><span>Save</span></button>
                                            <a class="cancelBtn" href="#">CANCEL</a>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                            </c:if>
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
    </div>



    </div>
</div><!-- /.page -->


<!-- delete source dialog -->
<div id="dialogDelSource" title="Delete?" style="height: auto !important;">
    <p class="clearFix">
        <span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 0 0;"></span>
        These items will be
        permanently deleted and cannot be recovered. Are you sure?
    </p>
</div>


<!-- delete user dialog -->
<div id="dialogDelUser" title="Delete user?" style="height: auto !important;">
    <p class="clearFix">
        <span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 50px 0;"></span>
        If you choose to delete this user all tasks created 
        by him will be permantly removed and all their information 
        cannot be recovered. 
        To remove their access to this account but retain their tasks,
        please choose disable.
    </p>
</div>

<!-- delete region -->
<div id="dialogDelRegion" title="Delete Region?" style="height: auto !important;">
    <p class="clearFix">
        <span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 50px 0;"></span>
        This location cannot be deleted because there currently is a user attached to it.
        Please change user's location or delete user before proceeding.
    </p>
</div>


<%@ include file="scripts.html"%>
</body>
</html>