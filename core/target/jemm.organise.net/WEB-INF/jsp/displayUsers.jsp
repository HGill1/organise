<div class="content-left" id="contentLeft">
                        <div class="search-wrapper">
                           <input id="searchBox" class="search-field" type="text" placeholder="Search">
                           <input type="hidden" id="searching" value="false">
                        </div>

                        <div class="content-left-scroll" id="contentLeftScroll">
                            <div class="content-left-wrapper">
                                
                                <div class="left-nav">
                                 <input type="hidden" id="hiddenUserId" value="${user.id}">
                                    <input type="hidden" id="multiSourceFilter" value="false">
                                    <div class="left-nav-title"><span data-control="leftExpander" class="left-expander down"><i class="icon-message"></i> Topics</span> 
                                    <span class="float-right" data-control="editLeftNav"></span></div>
                                    <ul id="allSourcesList" class="left-nav-sub" data-control="leftNavFolders">
                                        <li class="green"><i class="icon-folder"></i> <a href="index">ALL</a></li>
                                        <li><i class="icon-folder"></i> <a id="multiSourceFilterId" href="#">Filter</a></li>
                                        <c:forEach var="feedCategory" items="${userFeedSources}">
                                        
                                        <c:set var="subtopicCss" value='' />
                                        
                                        <c:if test="${feedCategory.subTopics.size() > 0}">
                                        <c:set var="subtopicCss" value='data-control="dropdownExpander" class="left-nav-ddwn-item collapsed"' />
                                        </c:if>
                                        
                                        
	                                        <li class="new-item"><i class="icon-ball"><i style="background: ${feedCategory.colour};"></i></i>
	                                        <a ${subtopicCss} id="source${feedCategory.id}" href="#" data-topic-colour="${feedCategory.colour}">${feedCategory.name}</a>
	                                         <span class="edit-row"><a href="#" data-control="editNavRow">Hide</a></span>
	                                        <input type="hidden" id="catIdForShowHide" value="${feedCategory.id}">
	                                        <span class="edit-row" id="showHideId"><a href="#" data-control="editNavRow">Hide</a></span>
	                                        <ul class="left-nav-dropdown accordion-hide">
	                                        <c:forEach var="subtopic" items="${feedCategory.subTopics}">
                                                <li><i class="icon-ball"><i style="background: ${subtopic.subtopicColor};"></i></i> 
                                                <a id="source${subtopic.subtopicId}" href="#" data-topic-colour="${feedCategory.colour}">${subtopic.subtopicName}</a></li>
                                            </c:forEach>
                                            </ul>
	                                        </li>
                                        </c:forEach>
                                    </ul>

                                    <div class="left-nav-title">
                                    <input type="hidden" id="followingByCat" value="false">
									<input type="hidden" id="followCatId" value="">
                                    <span data-control="leftExpander" class="left-expander collapsed"><i class="icon-following"></i> Following threads</span></div>

                                    <ul id="allFollowingList" class="left-nav-sub left-nav-links accordion-hide">
                                    <c:forEach var = "followingCat" items = "${following}">
                                        <li class="new-follow"><i class="icon-ball"><i style="background: ${followingCat.colour};"></i></i>
                                        <a id="followingCat" href="#">${followingCat.name}
                                        <input type="hidden" id="followingCatId" value="${followingCat.id}">
                                        </a></li>
                                    </c:forEach>    
                                    </ul>

                                    <div class="left-nav-title"><span data-control="leftExpander" class="left-expander down"><i class="icon-message"></i> Viewed links</span></div>

                                    <ul id="recentlyViewedTh" class="left-nav-sub left-nav-links">
                                    <c:forEach var = "viewedThread" items = "${recentlyViewed}">
                                        <li class="recently-viewed"><a id="recentlyViewedId" href="#">${viewedThread.name}
                                        <input type="hidden" id="lastViewedId" value="${viewedThread.id}">
                                        </a></li>
                                    </c:forEach>    
                                    </ul>


                                    <div class="left-nav-title"><span data-control="leftExpander" class="left-expander down"><i class="icon-users"></i> Online Users</span>
</div>

                                    <ul class="left-nav-sub left-nav-users" id="onlineUsersContainer">
                                       <c:forEach var="onlineUser" items="${usersOnline}">
                         				 <c:set var="pImage" value="${profileImagePath}${onlineUser.profileimage}"></c:set>
                         				<c:if test="${onlineUser.profileimage == null}">
                                        			<c:set var="pImage" value="resources/images/blank.gif"></c:set>
                                        		</c:if> 
                                        <li>
                                            <a id="myTask${onlineUser.id}" href="#" class="left-nav-user">
                                                <img src="${pImage}" width="32" height="32" title="${onlineUser.email}">
                                                <span>
                                                    <strong>${onlineUser.firstName} ${onlineUser.lastName }</strong>
                                                    ${onlineUser.jobTitle.length()>0 ? onlineUser.jobTitle :''} (${onlineUser.regionName})
                                                </span>
                                            </a>
                                        </li>
                                        </c:forEach>   
                                    </ul>


                                    <div class="left-nav-title"><span data-control="leftExpander" class="left-expander down"><i class="icon-users"></i> Offline Users</span>
</div>

                                    <ul class="left-nav-sub left-nav-users" id="offlineUsersContainer">
                                         <c:forEach var="offlineUser" items="${usersOffline}">
                         				 <c:set var="pImage" value="${profileImagePath}${offlineUser.profileimage}"></c:set>
                         				<c:if test="${offlineUser.profileimage == null}">
                                        			<c:set var="pImage" value="resources/images/blank.gif"></c:set>
                                        		</c:if> 
                                    
                                        <li>
                                            <a id="myTask${offlineUser.id}" href="#" class="left-nav-user green offline">
                                                <img src="${pImage}" width="32" height="32" title="${offlineUser.email}">
                                                <span>
                                                    <strong>${offlineUser.firstName} ${offlineUser.lastName }</strong>
                                                    ${offlineUser.jobTitle.length()>0 ? offlineUser.jobTitle :''} (${offlineUser.regionName})
                                                </span>
                                            </a>
                                        </li>
                                       </c:forEach> 
                                        

                                    </ul>

                                </div>

                            </div>

                        </div>
                    </div>