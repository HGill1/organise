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
                <form:form action="${formAction}" id="feedSourceForm" modelAttribute="feedSource">
                <input type="hidden" name="subTopicsName" id="subTopicsName" value=""/>
                <c:set var="hideShow" value="hide" />
                <form:hidden path="id"/>
                <input type="hidden" value="${user.groupId}" name="groupId" id="groupId" />
                    <div class="content-main">


                        <div class="user-edit-wrapper">
                          
                            <div class="user-edit add-wr">
                                <h2 class="module-title">${formCaption} Topic </h2>
						
								<div id="messageBox"></div>
                                

                                     <div class="form-line">
                                        <div class="label"><label>Topic name</label></div>
                                        <form:input path="name" class="field medium" />
                                    </div>

                                    <div class="form-line">
                                        <div class="label"><label>Topic type (Leave it if topic type is not RSS) </label></div>
                                         <form:select path="sourceType" data-custom="true">
                                            <form:option value="0" label="Standard" />
                                            <form:options items="${sourceTypeList}"/>
                                        </form:select> 
                                    </div>

                                    <div class="form-line">
                                        <div class="label"><label>Color</label></div>
                                        <c:if test="${feedSource.id == null }">
                                        <form:input path="colour" id="editColor" class="user-edit-color" style="background: #a65200;" value="#A65200"/> <a href="#" data-control="editColorBtn" class="color-picker-btn">colour picker</a>
                                        </c:if>
                                        
                                        <c:if test="${feedSource.id != null }">
                                        <form:input path="colour" id="editColor" class="user-edit-color" style="background: #a65200;"/> <a href="#" data-control="editColorBtn" class="color-picker-btn">colour picker</a>
                                        </c:if>
                                        
                                        <div id="editColorPicker" class="color-picker-wr"></div>
                                    </div>
                                    <c:if test="${feedSource.sourceType > 0 }">
                                    <c:set var="hideShow" value=" " />
                                    </c:if>
									<div id="divToken" class="form-line ${hideShow}">
                                        <div class="label"><label>Token</label></div>
                                        <form:input path="token"  class="field medium" placeholder="Write token"/>
                                    </div>
                                    
                                    <div id="divUrl" class="form-line ${hideShow}">
                                        <div class="label"><label>Url</label></div>
                                        <form:input path="url"  class="field medium" placeholder="Rss url type or copy here "/>
                                    </div>
                                    
                                    
                            </div>
                                <!-- <div class="add-btn-wr">
                                    
                                    <button id="topicSave" type="submit" class="btn-control create"><i class="icon-save"></i><span>Save</span></button><a class="cancelBtn" href="manageSources">CANCEL</a>

                                 </div> -->
                        </div><!-- /.user-create-wrapper -->
                        
                        
                        <div class="user-edit-wrapper subtopics-wrapper">
                            <div class="user-edit add-wr">
                                <h2 class="module-title">Subtopics</h2>

                                    <div class="form-line" data-control="subtopicAddField">
                                        <input type="text" class="field medium2" placeholder="Add extra subtopic"><button class="btn-control inline" type="button"><span>ADD</span></button>
                                    </div>

                                    <ul id="subTopicsList" class="left-nav-sub"  data-control="subtopicsEditList">
									<c:if test="${formCaption == 'Edit'}">
										<c:forEach var="subtopic" items="${subtopicsList}">
											<li><i class="icon-ball"><i style="background: #ffd200;"></i></i> 
											<a href="#">${subtopic.name}</a><span class="edit-row">
											<a id="subtopic${subtopic.id}" href="#" data-control="delSubtopics"></a></span></li>
										</c:forEach>
									</c:if>
                                    </ul>
                                    

                            </div>
                            <div class="add-btn-wr">
                                    
                                    <button id="topicSave" type="submit" class="btn-control create"><i class="icon-save"></i><span>Save</span></button><a class="cancelBtn" href="manageSources">CANCEL</a>

                                 </div>
                               <!--  <div class="add-btn-wr">

                                    <button type="submit" class="btn-control create"><i class="icon-save"></i><span>Save</span></button><a class="cancelBtn" href="#">CANCEL</a>

                                 </div> -->
                        </div>

                        <div class="user-edit-mngt-wrapper clearFix">

                            <h2 class="module-title">Seen by users</h2>

									<div class="clearFix" data-control="userSeen">
									
									<c:forEach var="user" items="${activeUsers}">
								<c:set var="pImage" value="${profileImagePath}${user.profileimage}"></c:set>
								
								<c:forEach var="fuser" items="${feedSource.usersId}">
								
								<c:if test="${fuser == user.id}">

										<div class="user-item-wrapper" data-control="userItemSource" title="${user.email}">
											<div class="user-item clearFix">
												<div class="user-edit-left">
													<span class="user-edit-checkbox">
													<input id="checkedUserId" name="checkedUserId" value="${user.id}" data-control="userCheckBox" type="checkbox" checked >
													</span>
												</div>
												<div class="user-edit-right">
													<div class="user-item-content">
														<div class="user-item-info">
															<div class="user-item-image">
																		<c:if test="${user.profileimage == null}">
																			<c:set var="pImage"
																				value="resources/images/blank.gif"></c:set>
																		</c:if>
																		<img src="${pImage}" height="56">
															</div>
															<div class="user-item-txt">
																<h4 class="user-item-name">${user.firstName} ${user.lastName} (${user.regionName})</h4>

																<div class="user-item-note">${user.jobTitle}</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										</c:if>
										
								</c:forEach>
										</c:forEach>
									</div>
								</div>


                        <div class="user-edit-mngt-wrapper clearFix">


                            <h2 class="module-title">All users <span class="note"><input data-control="userSelectAll" type="checkbox"> select all</span></h2>

                            <div class="clearFix" data-control="userAll">
								<c:forEach var="user" items="${usersNotSelected}">
								<c:set var="pImage" value="${profileImagePath}${user.profileimage}"></c:set> 
								
                                <div class="user-item-wrapper" data-control="userItemSource" title="${user.email}">
                                    <div class="user-item clearFix">
                                        <div class="user-edit-left">
                                            <span class="user-edit-checkbox">
                                            <input id="checkedUserId" name="checkedUserId" value="${user.id}"  data-control="userCheckBox" type="checkbox">
                                            </span>
                                        </div>
                                        <div class="user-edit-right">
                                            <div class="user-item-content">
                                                <div class="user-item-info">
                                                    <div class="user-item-image">
																		<c:if test="${user.profileimage == null}">
																			<c:set var="pImage"
																				value="resources/images/blank.gif"></c:set>
																		</c:if>
																		<img src="${pImage}" height="56">
                                                    </div>
                                                    <div class="user-item-txt">
                                                        <h4 class="user-item-name">${user.firstName} ${user.lastName} (${user.regionName})</h4>

                                                        <div class="user-item-note">${user.jobTitle}</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                               
</c:forEach>

                                


                                

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