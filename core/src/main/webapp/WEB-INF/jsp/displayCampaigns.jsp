<div id="campaignContainer">
 <fmt:formatDate type="date" value="${now}" var="currentDate" /> 
<c:forEach var="campaign" items="${campaignList}">

            
                       
                        <div class="campaign-wr">
                         <div class="campaign-body clearFix">
                         
                       
                          
                            <c:forEach var="sublist" items="${campaign}" varStatus="status">
                            <c:set var="assignToUsername" value="All"></c:set>
                            <fmt:formatDate type="date" value="${sublist.createdAt}" var="campaignDate"/>  
                            <c:set var="displayDate" value="Today"/>
                            <c:if test="${campaignDate != currentDate}">
                            <fmt:formatDate pattern="dd MMM yyyy" value="${sublist.createdAt}" var="displayDate"/>  
                            </c:if> 
                            
                            <c:choose>
                            <c:when test="${status.count == 1}">
                            
                            <div class="campaign-header clearFix">
                                <div class="campaign-header-l">
                                <c:if test="${sublist.mainStatus}">
                                    <span class="campaign-title campaign-warning">URGENT</span>
                                </c:if> 
                                    <span class="campaign-title">${sublist.name}</span> <i class="icon-arrow-right"></i>
                                    <!-- <span class="campaign-name">4 Germanwings UK Mar 21 - L519IELXCP552B</span> -->
                                </div>
                                <div class="campaign-header-r">
                                    <div class="btn-control-wr">
                                        <a href="" class="btn-control"><i class="icon-history"></i><span>History</span></a>
                                        <a href="" class="btn-control"><i class="icon-follow"></i><span>Follow</span></a>
                                        <a href="" id="${sublist.id}" class="btn-control"><i class="icon-reply"></i><span id="reply">Reply</span></a>
                                        <input type="hidden" id="feedSourceIdForThis" value="${sublist.feedSourceId}" name="feedSourceIdForThis"/>
                                    </div>
                                </div>
                            </div>
                            
                                <div class="campaign-body-left">
                                    <div class="campaign-body-content">
                                        <div class="campaign-body-title">
                                            <span class="nick">${sublist.username} (${sublist.regionName})</span>,
                                            <span class="date"><fmt:formatDate value="${sublist.createdAt}" pattern="hh:mm a" />, ${displayDate}</span>
                                        </div>

                                        <div class="campaign-body-txt">
                                            <pre>${sublist.summary}</pre>
                                        </div>
                                        <div class="campaign-body-txt">
                                        	 <pre><a href ="http://${sublist.url}" target="_blank">${sublist.url}</a> </pre>
                                        </div>

                                        <div class="campaign-body-info">
                                        <c:if test="${not empty sublist.assignToName}">
                                        	<c:set var="assignToUsername" value="${sublist.assignToName}"></c:set>                                            
                                            </c:if>
                                            Assigned to: <span>${assignToUsername}</span>, Remaining days: <span>3</span>
                                        </div>

                                    </div>
                                </div><!-- main Campaign ends here -->
                                </c:when>
                                <c:otherwise>
                                <div class="campaign-body-right">
                                    <div class="campaign-body-content">
                                        <div class="campaign-body-title">
                                            <span class="comment">Last comment -</span>
                                            <span class="nick">${sublist.username} (${sublist.regionName})</span>,
                                            <span class="date"><fmt:formatDate value="${sublist.createdAt}" pattern="hh:mm a" />, ${displayDate}</span>
                                        </div>

                                        <div class="campaign-body-controls">
                                            <div class="btn-control-wr">
                                                <a href="" class="btn-control done"><i class="icon-done"></i><span>${sublist.statusName}</span></a>
                                                <a href="" class="btn-control attach"><i class="icon-attach"></i><span>Attach images </span></a>
                                            </div>
                                        </div>

                                        <div class="campaign-body-txt">
                                            <pre>${sublist.summary}</pre>
                                        </div>
                                        <div class="campaign-body-txt">
                                        	 <pre><a href ="http://${sublist.url}" target="_blank">${sublist.url}</a> </pre>
                                        </div>

                                    </div>
                                </div>
                                </c:otherwise>
                             </c:choose>
                             </c:forEach>
                            </div>


                        </div>
                        
                        </c:forEach><!-- /. com-wr -->
    </div>  