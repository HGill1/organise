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

<c:url value="message.htm" var="messageUploadUrl"/>
<c:url value="attachFile.htm" var="fileUploadUrl"/>
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="status" value="${status}"/>
<%@ include file="header.html"%>

<body>
<input type="hidden" id="userObject" data-userdetail = '{"id":"${user.id}","firstName":"${user.firstName}","lastName": "${user.lastName}","regionName":"${user.regionName}","jobTitle":"${user.jobTitle}","userImage":"${user.profileimage}"}'>
<input type="hidden" id="newTaskObject" data-taskdetail = '{"createdAt":"","firstName":"${user.firstName}","lastName": "${user.lastName}","assignToName":"All","userId": "${user.id}","regionName":"${user.regionName}","feedType":"0","id":"","deadline":"","status":"3","urgentStatus":"false","colour":"","name":"","summary":"","follower":"0","assignTo":"","feedSourceId":"","taskCreatorEmail":"${user.email}","taskUpdateEmailNotify":"","attachmentCount":""}'> 
<c:set var="campaignList" value="${pagedListHolder}" />
<div class="page">
    <div class="page-container">
        <%@ include file="menu.jsp"%>

        <div class="content-wrapper" id="content">
            <div class="content-container">
                <div class="content clearFix">
                
					<%@include file="displayUsers.jsp" %>
										                    
                    <div class="content-right" id="contentRight">
                        <div class="content-header clearFix">
                            <div class="content-header-l">
                               <!--  <span class="content-title">Quick filters:</span> -->
                                <input type="hidden" id="filterStatusId" value="0">
                                <input type="hidden" id="filterUserId" value="0">
                                <input type="hidden" id="filterCatId" value="0">
                                <input type="hidden" id="myAddedTasksClicked" value="false">
                                <input type="hidden" id="urgentClicked" value="false">
                                <!-- <a href="index" class="content-link">all</a> -->
                                <%-- <a id="urgent" href="#" class="content-link">urgent</a>
                                <a id="live" href="#" class="content-link">live</a>
                                <a id="pending" href="#" class="content-link">pending</a>
                                <a id="done" href="#" class="content-link">done</a>
                                <a id="myTask${user.id}" href="#" class="content-link">my tasks</a>
                                <a id="myAddedTasks" href="#" class="content-link">my added tasks</a> --%>
                                
                                <div class="content-title-cell">
                                    <span class="content-title">Quick filters:</span>
                                    <select data-custom="true" id="selectTasksType" name="selectTasksType">
                                    	<option value="0">All</option>
                                        <option value="myTask">My Tasks</option>
                                        <option value="myAddedTasks">My Added Tasks</option>
                                        <option value="1">Urgent</option>
                                        <option value="2">Live</option>
                                        <option value="3">Pending</option>
                                        <option value="4">Done</option>
                                    </select>
                                </div>
                                <div class="content-title-cell">
                                    <span class="content-title">Sort By:</span>
                                    <select data-custom="true" id="orderBy" name="orderBy">
                                        <option value="0">Newest</option>
                                        <option value="1">Oldest</option>
                                    </select>
                                </div>
                                <div class="content-title-cell">
                                    <span class="content-title">View:</span>
                                    <input type="hidden" value="${viewTypeSelected}" id="listViewTypeSelected" name="listViewTypeSelected" />
                                    <select id="tasksViewType" name="tasksViewType" data-custom="true">
										<option value="0">Excerpt View</option>
                                        <option ${viewTypeSelected} value="1">List View</option>
                                    </select>
                                </div> 
                                
                                
                            </div>
                            <div class="content-header-r">
                                <a id="toggleAddForm" href="#" class="task-new"><i class="icon-plus"></i>Create a new task</a>
                            </div>
                        </div>
                        <div class="content-right-scroll" id="contentRightScroll">

                            <div class="content-right-container" data-control="msgBox">
                                <!-- content here-->
						<div id="contentContainer">
						<c:if test="${campaignList.size() < 1}">
							<h1 class="content-title">No records found! </h1>
						</c:if>
							<fmt:formatDate type="date" value="${now}" var="currentDate" /> 
							<c:forEach var="campaign" items="${campaignList}" varStatus="status">
							<c:set var="activeFirstTask" value=""></c:set>
							<c:if test="${status.count == 1}">
							<c:set var="activeFirstTask" value="active"></c:set>
							</c:if>
							<c:set var="listView" value=""></c:set>
							<c:if test="${not empty viewTypeSelected}">
							<c:set var="listView" value="list-view"></c:set>
							</c:if>
							<c:set var="taskStatusIcon" value="icon-rss" />
							<c:choose>
							<c:when test="${campaign[0].status == 2}">
								<c:set var="taskStatusIcon" value="icon-on-it" />
							</c:when>
							<c:when test="${campaign[0].status == 3}">
								<c:set var="taskStatusIcon" value="icon-pending" />
							</c:when>
							<c:when test="${campaign[0].status == 4}">
								<c:set var="taskStatusIcon" value="icon-don" />
							</c:when>
							</c:choose>
							
                                                              
                            <c:set var="assignToUsername" value="All"></c:set>
                            <fmt:formatDate type="date" value="${campaign[0].createdAt}" var="campaignDate"/>  
                            <c:set var="displayDate" value="Today"/>
                            <c:if test="${campaignDate != currentDate}">
                            <fmt:formatDate pattern="dd MMM yyyy" value="${campaign[0].createdAt}" var="displayDate"/>  
                            </c:if> 
						
                                <div id="taskContainer${campaign[0].id}" class="campaign-line ${listView} ${activeFirstTask}" data-control="campaignWrapper" data-taskId="${campaign[0].id}">

                                    <div style="background: ${campaign[0].colour};" data-control="campaign" class="campaign-wrapper">
                                        <div class="campaign-container">
                                            <div id="title${campaign[0].id}" class="campaign-title-wr" data-control="campaignShowMore">
                                                <i class="${taskStatusIcon}"></i>
                                                <span class="campaign-title">${campaign[0].name}</span>
                                                <i class="icon-arrow-right"></i>
                                               <%--  <span class="campaign-name">${campaign[0].name}</span> --%>

                                                <div class="close-box">
                                                   <c:if test="${campaign[0].userId == user.id && (now.time/1000 - campaign[0].createdAt.time/1000) < 600 && campaign[0].feedType == 0}">
                                    				<div class="close-box">
		                                                 <a id="editFeed" href="#" data-control="editCampaign"><!-- <i class="icon-edit-box"> --></i>
			                                               <%--  <input type="hidden" id="editFeedId" value="${campaign[0].id}" name="editFeedId"/>  --%>
			                                                </a>
			                                                <a id ="deleteFeed" href="#" data-control="delCampaign"><i class="icon-close-box"></i>
			                                                <input type="hidden" id="deleteFeedId" value="${campaign[0].id}" name="deleteFeedId"/> 
			                                                </a>
		                                            </div>
                                    </c:if> 
                                    
                                            </div>
                                            <c:if test="${campaign[0].urgentStatus == true || campaign[0].status == 1}">
										<div class="urgent"></div>
									</c:if>   
                                            </div>
                                            <div class="campaign-content">
                                            <c:if test="${empty viewTypeSelected}">
                                                 <div class="campaign-body">
                                                    <div class="campaign-body-title">
                                                        <span class="nick">${campaign[0].firstName} ${campaign[0].lastName} (${campaign[0].regionName})</span>,
                                                        <span class="date"><fmt:formatDate value="${campaign[0].createdAt}" pattern="hh:mm a" />, ${displayDate}</span>
                                                    </div>
                                                    <div class="campaign-body-txt" data-control="campaignShowMore">
	                                                    <input type="hidden" value="${campaign[0].id}" id="viewed" >
	                                                    <div class="campaign-body-trim">
	                                                        ${campaign[0].summary} 
	                                                     </div>
	                                                     <div class="show-more-wr">
	                                                            <a href="#" class="showMore">show more</a>
	                                                     </div>
                                                	</div>
                                                </div>
                                             </c:if>  
                                             
                                             
                                             
                                               
                                                <div class="campaign-info">
                                                	 <c:if test="${not empty campaign[0].assignToName}">
                                                		<c:set var="assignToUsername" value="${campaign[0].assignToName}"></c:set>
                                                	  </c:if>
                                                	   <c:if test="${not empty campaign[0].deptName}">
                                                		<c:set var="assignToUsername" value="${campaign[0].deptName}"></c:set>
                                                	  </c:if>
                                                    Assigned to: <span>${assignToUsername}</span>
                                                    <c:if test="${campaign[0].deadline!=null}">
                                                     , Remaining days:
                                                      <span><fmt:formatNumber var="timeDeadline" value=" ${(campaign[0].deadline.time - now.time)/86400000+1}" maxFractionDigits="0"></fmt:formatNumber> ${timeDeadline}
                                                      </span> 
                                                      </c:if> 
                                                <span class="float-right">
                                                    &bull; <a id="${campaign[0].follower == user.id ? 'unfollow' :'follow'}" href="#">
                                                    ${campaign[0].follower == user.id ? 'unfollow' :'follow'}
                                                      <input type="hidden" id="followFeedId" value="${campaign[0].id}" name="followFeedId"/> 
                                                    </a>
                                                    &nbsp;&bull; <a href="#" data-control="campaignShowMore">comments  (${fn:length(campaign)-1})</a>
                                                </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <!---->
                                    <div class="more-wrapper" data-control="campaignMore">
                                        <div class="more-container">
                                            <div class="more-title-wrapper">
                                                <div class="more-title">${campaign[0].name}</div>
                                                <div class="more-info">
                                                    by:
                                                    <strong>${campaign[0].firstName} ${campaign[0].lastName}</strong>,
                                                    <span><span class="date"><fmt:formatDate value="${campaign[0].createdAt}" pattern="hh:mm a" />, ${displayDate}</span>
                                                </div>
                                            </div>

                                            <div class="more-content">
                                                <pre>${campaign[0].summary}</pre>
                                            </div>
                                            <div>
                                             <c:if test="${campaign[0].attachmentCount > 0}">
                                            	<c:forEach var="fileAttached" items="${campaign[0].fileAttachment}">
                                            	<a href="${attachmentsPath}${fileAttached.attachmentId}${fileAttached.fileExtension}" class="btn-control attach" download = "${fileAttached.name}" target="_blank"><i class="icon-attach"></i><span>${fileAttached.name} </span></a>
                                            	</c:forEach>
											</c:if>
                                            </div>

                                            <div class="reply-wrapper hide" data-control="replyWrapper">
                                            </div>


                                            <div class="comments-wrapper">
                                                <div class="comments-title-wr clearFix">
                                                    <span class="comments-title">COMMENTS (${fn:length(campaign)-1})</span>
                                                    <span class="float-right"><a id="${campaign[0].id}" href="#" class="write-comment" data-control="replyBtn">add comment
                                                    <input type="hidden" id="feedSourceIdForThis" value="${campaign[0].feedSourceId}" name="feedSourceIdForThis"/>
                                                    <input type="hidden" id="assignTo" value="${campaign[0].assignTo}" name="assignTo"/>
                                                    <input type="hidden" id="taskCreatorEmail" value="${campaign[0].taskCreatorEmail}" name="taskCreatorEmail"/>
                                                    <input type="hidden" id="postCommentEmailNotify" value="${campaign[0].taskUpdateEmailNotify}" name="postCommentEmailNotify"/>
                                                    <input type="hidden" id="assignToName" value="${campaign[0].assignToName}" name="assignToName"/>
                                                    <input type="hidden" id="assignerName" value="${campaign[0].firstName} ${campaign[0].lastName}" name="assignerName"/>
                                                    <input type="hidden" id="taskTitle" value="${campaign[0].name}" name="taskTitle"/>
                                                    <input type="hidden" id="taskCreatorId" value="${campaign[0].userId}" name="taskCreatorId"/>
                                                    <input type="hidden" id="taskAssigneeEmail" value="${campaign[0].assignToEmail}" name="taskAssigneeEmail"/>
                                                    <input type="hidden" id="assignToDept" value="${campaign[0].assignToDept}" name="assignToDept"/>
                                                    </a></span>
                                                </div>
                                             <div id="comments${campaign[0].id}">
													<c:forEach var="campaignReply" items="${campaign}" begin="1">
														<c:set var="pImage" value="${profileImagePath}${campaignReply.profileImage}"></c:set> 			
														<fmt:formatDate type="date" value="${campaignReply.createdAt}" var="campaignReplyDate"/>  
							                            <c:set var="displayDate" value="Today"/>
							                            <c:if test="${campaignDate != currentDate}">
							                            	<fmt:formatDate pattern="dd MMM yyyy hh:mm a" value="${campaignReply.createdAt}" var="displayDate"/>  
							                            </c:if> 													
													
		                                                <div class="comment clearFix">
		                                                    <div class="comment-img">
		                                                    	<c:if test="${campaignReply.profileImage == null}">
		                                        					<c:set var="pImage" value="resources/images/blank.gif"></c:set>
		                                        				</c:if>
		                                                    <img src="${pImage}" height="56">
		                                                        
		                                                    </div>
		                                                    <div class="comment-txt">
		                                                        <div>
		                                                          <pre> ${campaignReply.summary} </pre>
		                                                        </div>
		                                                        <div>
		                                                        <c:if test="${campaignReply.attachmentCount > 0}">
					                                            	<c:forEach var="fileAttached" items="${campaignReply.fileAttachment}">
					                                            	<a href="${attachmentsPath}${fileAttached.id}${fileAttached.fileExtension}" class="btn-control attach" download = "${fileAttached.name}" target="_blank"><i class="icon-attach"></i><span>${fileAttached.name} </span></a>
					                                            	</c:forEach>
																</c:if>
		                                                        </div>
		                                                        <div class="comment-info clearFix">
		                                                            <span class="float-left comment-info-name"><strong>${campaignReply.firstName} ${campaignReply.lastName}</strong> - ${campaignReply.jobTitle.length()>0 ? campaignReply.jobTitle :''} (${campaignReply.regionName})
																	<br>changed status to: ${campaignReply.statusName}
		                                                            </span>
		                                                            <span class="float-right comment-info-day">${displayDate}</span>
		                                                        </div>
		                                                    </div>
		                                                </div>
		                                               </c:forEach>
                                               </div>



                                                <div class="comments-controls clearFix">
                                                <span class="float-right">
                                                    &bull; <a id="follow" href="#">follow
                                                    <input type="hidden" id="followFeedId" value="${campaign[0].id}" name="followFeedId"/> 
                                                    </a>
                                                </span>
                                                </div>

                                            </div>


                                        </div>
                                        <div class="task-arrow"></div>
                                    </div>
                                    <!---->

                                </div>

</c:forEach>

							</div>
						<!-- container content ends here-->	
                            </div>
<!-- content ends here-->

                        </div>
                    </div>

                </div>
            </div>
        </div>

       <div class="footer-wrapper" id="footer">
				<div class="footer-container">
					<div class="footer">&copy; 2010 JEMM MEDIA GROUP, LLC ALL
						RIGHTS RESERVED.</div>
				</div>
			</div>

    </div>
</div>
<!-- /.page -->


<!-- delete campaign dialog -->
<div id="dialogDel" title="Delete?" style="height: auto !important;">

        <p class="clearFix">
            <span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 0 0;"></span>
            These items will be
            permanently deleted and cannot be recovered. Are you sure?
        </p>

</div>


<!-- add task dialog -->
<div id="dialogAddTask" title="Create new task">
    <div class="popup-form" id="editForm">
         <form:form id='addNotes' action="" modelAttribute="feedEntryBean">
         
          <div class="form-line" id="topicSelectionArea">
               <div class="label">
                       <label for="form1">Topic</label>
                </div> 
              
                       <select id="feedSourceId" name="feedSourceId" data-custom="true">                                   
                           <option value='0'>Select Topic</option>
                           <c:forEach var="feedCategory" items="${userFeedSources}">
                           <c:if test="${feedCategory.sourceType == 0}">
                           <option value="${feedCategory.id}-${feedCategory.colour}">${feedCategory.name}</option>
                            <c:if test="${feedCategory.subTopics.size() >0 }">
                            <c:forEach var="subtopic" items="${feedCategory.subTopics}">
                                 <option value="${subtopic.subtopicId}-${subtopic.subtopicColor}">&nbsp;&nbsp;${subtopic.subtopicName}</option>
                            </c:forEach>
                            </c:if>
                           
                           </c:if>
                           </c:forEach>
                       </select>
                             
           </div> 
         
            <div class="form-line">
                <div class="label">
                    <label for="form1423">Task title</label>
                </div>
                <form:input path="name" type="text" class="field" placeholder="Type short task title here"/>
            </div>

            <div class="form-line">
                <div class="label">
                    <label for="form2543">Describe you task here</label>
                </div>
                <form:textarea path="summary" class="textarea" placeholder="Describe what exactly about your task"/>
            </div>
            
            <div class="form-line clearFix">
                <div class="float-left">
                    <div class="label">
                        <label for="assignEmailAndId">Assigned to:</label>
                    </div>
                   <%--  <select id="assignEmailAndId" name="assignEmailAndId" data-custom="true">
                        <option value="0">Select Assignee</option>
                        <c:forEach var="user" items="${users}">
                         <option value="${user.id}#${user.email}#${user.taskAddEmail}">${user.firstName} ${user.lastName}</option>
                        </c:forEach>
                   </select> --%>
                   
                   <input id="assignEmailAndId" type="text" name="assignEmailAndId" class="field medium" placeholder="Type employee name"  data-selected-item-id="">
                    <div class="autocompleteContainer" data-autocomplete-target="assignEmailAndId">
                        <div class="autocompleteGroupContainer" id="deptDetail">
                           <span class="autocompleteGroupTitle">Select Group</span>
                           <c:forEach var="aDept" items="${allDeptsWithUsers}">
                           		 <span id="deptName" class="autocompleteItem" data-dept-name="${aDept.name}" data-item-id ="d${aDept.id}">${aDept.name}</span>
                            </c:forEach>
                        </div>
                        <div class="autocompleteGroupContainer" id="assigneeDetail">
                            <span class="autocompleteGroupTitle">Select Assignee</span>
                            <c:forEach var="user" items="${users}">
                            	<span class="autocompleteItem" data-assignee-name="${user.firstName} ${user.lastName}" data-item-id ="${user.id}#${user.email}#${user.taskAddEmail}#${user.firstName} ${user.lastName}">${user.firstName} ${user.lastName}</span>
                           </c:forEach>
                        </div>
                    </div>
                </div>
                &nbsp;
                 <div class="float-left">
                    <div class="label">
                        <label for="form3432432">Task due on:</label>
                    </div>
                     <span data-control="datepicker" class="calendar-inline"><form:input path="deadline" placeholder="Deadline" data-control="datepickerFrom" /></span> 
                </div> 


            </div>
            


            <%-- <div class="form-line">
                <div class="label">
                    <label for="form1445543">URL</label>
                </div>
                <form:input path="url" type="text" class="field" placeholder="Put here a URL you want to refer to and Journal will make it a link"/>
            </div> --%>
            <div class="form-line">
			    <input type="checkbox" id="status" name="status"> <label for="form2">Mark Status as Urgent</label>
            </div>


            <div class="form-line">
                <a href='#' id='attach' class="btn-control attach add"><i class="icon-attach"></i><span>Attach file </span></a>
                <input id="upload" type="file" name="file" data-url="attachFile.htm" multiple style="opacity: 0; filter: alpha(opacity :   0);">
            </div>
            <div id="attachmentsCont" class="form-line">
	        </div>

            <div class="popup-btns">
              <button type="submit" class="btn-control create"><i class="icon-create"></i><span>Create</span></button>
            <a id="cancelAdd" class="cancelBtn" href="#">CANCEL</a>
            </div>


        </form:form>

    </div>
</div>






<!-- edit -->

<!-- add task dialog -->
<div id="dialogEditCampaign" title="Edit task">
    <div class="popup-form">
        <form>
            <div class="form-line">
                <div class="label">
                    <label for="form142423423">Task title</label>
                </div>
                <input id="form142423423" type="text" class="field" placeholder="Type short task title here">
            </div>

            <div class="form-line">
                <div class="label">
                    <label for="form2545343">Describe you task here</label>
                </div>
                <textarea class="textarea" id="form2545343" placeholder="Describe what exactly about your task"></textarea>
            </div>

            <div class="form-line clearFix">
                <div class="float-left">
                    <div class="label">
                        <label for="form34432432">Assigned to:</label>
                    </div>
                    <input id="form34432432" type="text" class="field medium" placeholder="Type employee name">
                </div> &nbsp;
                <div class="float-left">
                    <div class="label">
                        <label for="form3432432">Task due on:</label>
                    </div>
                    <span data-control="datepicker" class="calendar-inline"><input type="text" placeholder="Deadline" data-control="datepickerFrom"></span>
                </div>


            </div>


            <div class="form-line">
                <div class="label">
                    <label for="formewqe">URL</label>
                </div>
                <input id="formewqe" type="text" class="field" placeholder="">
            </div>


            <div class="form-line">
                <a href="" class="btn-control attach"><i class="icon-attach"></i><span>Attach File </span></a>
                <span class="form-attached"><span>image.png</span> <i class="icon-del"></i></span>
            </div>

            <div class="popup-btns">
              <button type="submit" class="btn-control create"><i class="icon-create"></i><span>Create</span></button>
            <a class="cancelBtn" href="#">CANCEL</a>
            </div>


        </form>

    </div>
</div>


<!--reply form -->
<%@ include file="replyForm.jspf"%>

<%@ include file="scripts.html"%>
</body>
</html>