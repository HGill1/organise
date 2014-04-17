<c:url value="home.htm" var="homeUrl"/>
<c:url value="user.htm" var="userUrl"/>
<c:url value="admin.htm" var="adminUrl"/>
<c:url value="logout.htm" var="logoutUrl"/>
<c:url value="index.htm" var="journalUrl"/>
<c:set var = "profileImagePath" value="files/${user.groupId}/profileImages/" />
<c:set var = "attachmentsPath" value="files/${user.groupId}/attachments/" />

<div class="header-wrapper" id="header">
            <div class="header-container">
                <div class="header">
                    <div class="header-left">
                        <div class="logo">
                            <a href="${journalUrl}"></a>
                        </div>
                    </div>
                    
					<c:set var="pImage" value="${profileImagePath}${user.profileimage}"></c:set>
					
                    
                    
                     <c:forEach var="tasksCount" items="${selectTasksCount}">
                          <c:set var="tasksCountParts" value="${fn:split(tasksCount, '-')}" /> 
                          <c:if test="${tasksCountParts[0] == 'displayOldTasksPending'}"> 
                          
                          	<c:set var="displayOldTasksPending" value="${tasksCountParts[0]}" />
                          	<c:set var="oldTasksPending" value="${tasksCountParts[1]}" />
                          	<c:set var="oldTasksPendingClass" value="past" />
                          	<c:set var="oldTasksPendingSpan" value="Past Due <span>${tasksCountParts[1]} tasks</span>" />
                          
                          </c:if>
                           <c:if test="${tasksCountParts[0] == 'displayTodaysDueTasks'}"> 
                          
                          	<c:set var="displayTodaysDueTasks" value="${tasksCountParts[0]}" />
                          	<c:set var="todaysDueTasksClass" value="even" />
                          	<c:set var="todaysDueTasksSpan" value="Due Today <span>${tasksCountParts[1]} tasks</span>" />
                          
                          </c:if> 
                          
                           <c:if test="${tasksCountParts[0] == 'displayUrgentTasks'}"> 
                          
                          	<c:set var="displayUrgentTasks" value="${tasksCountParts[0]}" />
                          	<c:set var="urgentTasksSpan" value="Urgent<span>${tasksCountParts[1]} tasks</span>" />
                          
                          </c:if> 
                          
                          <c:if test="${tasksCountParts[0] == 'displayTodaysTasksPending'}"> 
                          
                          	<c:set var="displayTodaysTasksPending" value="${tasksCountParts[0]}" />
                          	<c:set var="todaysTasksPending" value="${tasksCountParts[1]}" />
                          	<c:set var="todaysTasksPendingClass" value="even" />
                          	<c:set var="todaysTasksPendingSpan" value="New <span>${tasksCountParts[1]} tasks</span>" />
                          	
                          </c:if>  
                          
                          <c:if test="${tasksCountParts[0] == 'displayAllMyTasks'}"> 
                          
                          	<c:set var="displayAllTasks" value="${tasksCountParts[1]}" />
                          </c:if> 
                     </c:forEach>
                     
                     <c:set var="bubbleClass" value=""></c:set>
					 <c:if test="${oldTasksPending > 0}">
                     <c:set var="bubbleClass" value="past"></c:set>
                    </c:if>
					
                    <div class="header-right clearFix">
                    
                    <%@ include file="tasksMenu.jsp"%>
                    
                        <div class="user-info">
                            <span class="user-name" data-control="userMenuBtn">${user.firstName} ${user.lastName}</span>
                            <div class="user-dropdown hide" data-control="userMenu">
                                <div class="user-dropdown-content show" data-control="userMenuInfo">

                                    <div class="user-dropdown-info">
                                        <div class="module-title">Your Profile</div>
                                        <div class="user-dropdown-image">
                                            <c:if test="${user.profileimage == null}">
                                        		<c:set var="pImage" value="resources/images/blank.gif"></c:set>
                                        	</c:if>
											 <img id="profilePhoto" src="${pImage}" width="56" height="56">
											  <a href="#" id="uploadImage" class="user-ava-plus"></a>
                                            <input id="profileImage" type="file" name="file" data-url="uploadProfileImage.htm" multiple style="opacity: 0; filter: alpha(opacity :   0);position:absolute;z-index:3;">
                                             <!-- <a href="#" onclick="jQuery('#profileImage').click(); return false;" class="user-ava-plus"></a> 
                                            <input id="profileImage" type="file" name="profileImage" data-url="attachFile.htm" multiple style="opacity: 0; filter: alpha(opacity :   0);position:absolute;z-index:3;"> -->
                                        </div>
                                       <div class="user-dropdown-txt">
                                            <h4 class="user-dropdown-name">${user.firstName} ${user.lastName}</h4>

                                            <div class="user-dropdown-note">${user.jobTitle}</div>
                                            <!-- <div class="user-dropdown-birth">date of birth: <strong>19 Sep 1980</strong> 
                                            </div>-->
                                            <div class="user-dropdown-email">email: <a href="mailto:email@email.com">${user.email}</a>
                                            </div>
                                           <!--  <div>
                                                <a href="#" data-control="userMenuEditBtn">change profile details and password</a>
                                            </div> -->
                                        </div>
                                    </div>

                                    <div class="user-dropdown-separator"></div>

                                    <div class="user-actions-wrapper">
                                        <div class="module-title">Actions</div>
                                        <ul class="user-actions">
                                         <li><i class="icon-user-create"></i><a href="preferences">Preferences</a></li>
                                         <c:if test="${user.roleId == 1}">
                                            <li><i class="icon-user-feed" data-control="userMenuFeed"></i><a href="jemmadmin">Jemm Admin</a></li>
                                        </c:if>
                                         <c:if test="${user.roleId == 1 || user.roleId == 2 || user.roleId == 3}">
                                            <li><i class="icon-user-feed"></i><a href="manageSources" data-control="userMenuFeed">Topic manager</a></li>
                                        </c:if>
                                        <c:if test="${user.roleId == 1 || user.roleId == 2}">
                                            <li><i class="icon-user-feed" data-control="userMenuFeed"></i><a href="statsPage">View Stats</a></li>
                                        </c:if>
                                            <!-- <li><i class="icon-user-msg"></i><a href="announcements" data-control="userMenuMsg">Create important message</a></li> -->
                                            <li><i class="icon-user-logout"></i><a href="${logoutUrl}" data-control="userMenuFeed">Logout</a></li>
                                        </ul>
                                    </div>


                                </div>

                                <!-- <div class="user-dropdown-content hide" data-control="userMenuCreate">
                                    <div class="user-dropdown-create">
                                        <div class="module-title">Create new user</div>
                                        <form>

                                            <div class="form-line clearFix">
                                                <div class="form-line-l">
                                                    <div class="label"><label>First name</label></div>
                                                    <input type="text" class="field small">
                                                </div>
                                                <div class="form-line-r">
                                                    <div class="label"><label>Second name</label></div>
                                                    <input type="text" class="field small">
                                                </div>
                                            </div>

                                            <div class="form-line">
                                                <div class="label"><label>Job title</label></div>
                                                <input type="text" class="field">
                                            </div>

                                            <div class="form-line">
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
                                            </div>

                                            <div class="form-line">
                                                <div class="label"><label>Email</label></div>
                                                <input type="text" class="field">
                                            </div>

                                            <button type="submit" class="btn-control done"><i class="icon-save"></i><span>Save</span>
                                            </button>
                                            <a class="cancelBtn" href="#" data-control="userDropdownCancel">CANCEL</a>

                                        </form>
                                    </div>
                                </div> -->

                                <%-- <div class="user-dropdown-content hide" data-control="userMenuEdit">
                                    <div class="user-dropdown-edit">
                                        <div class="module-title">Edit Your Profile</div>
                                            <form:form id='userInfo1' action="" modelAttribute="user">
											<form:hidden path="id"/>
                                            <div class="form-line clearFix">
                                                <div class="form-line-l">
                                                    <div class="label"><label>First name</label></div>
                                                    <form:input path="firstName" class="field small" />
                                                </div>
                                                <div class="form-line-r">
                                                    <div class="label"><label>Last name</label></div>
                                                    <form:input path="lastName" class="field small" />
                                                </div>
                                            </div>

                                            <div class="form-line">
                                                <div class="label"><label>Job title</label></div>
                                                <form:input path="jobTitle" class="field" />
                                            </div>
                                           

                                            <!-- <div class="form-line">
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

                                            <div class="form-line">
                                                <div class="label"><label>Email</label></div>
                                                <form:input path="email" class="field"/>
                                            </div>

                                            <button type="submit" id="profileEditBtn" name="profileEditBtn" class="btn-control done"><i class="icon-save"></i><span>Save</span>
                                            </button>
                                            <a class="cancelBtn" data-control="userDropdownCancel" href="#">CANCEL</a>

                                        </form:form>
                                        </div>

                                        <div class="user-dropdown-separator"></div>

                                    <div class="user-dropdown-pass">
                                        <div class="module-title">Change you password</div>

                                       <form:form id='userPass' action="">
                                       <input type="hidden" id="id" name="id" value="${user.id}">
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
                                                <div class="label"><label>Retype new password</label></div>
                                                <input type="password" id="confirmPassword" name="confirmPassword" class="field">
                                            </div>

                                            <button type="submit" id="changePassBtn" name="changePassBtn" class="btn-control done"><i class="icon-save"></i><span>Save</span>
                                            </button>
                                            <a class="cancelBtn" data-control="userDropdownCancel" href="#">CANCEL</a>
                                        </form:form>
										
                                    </div>

                                </div>
 --%>
                            </div>
                        </div>
                    </div>
                </div>
           </div><!-- /.header-wrapper -->

					<c:if test="${announcements.length()>0}">
					 <div data-control="alert" class="alert-wrapper">
                            <div class="alert-block">
                                <marquee behavior="scroll">
                                <i class="icon-warning"></i>
                                <span> ${announcements}</span>
                            	</marquee>
                            </div>
                            <span data-control="alertClose" class="icon-close-alert"></span>
                        </div>
                       </c:if> 
