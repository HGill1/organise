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
<jsp:useBean id="column_totals_tasks_created" class="java.util.LinkedHashMap" scope="page"/>
<jsp:useBean id="column_totals_tasks_completed" class="java.util.LinkedHashMap" scope="page"/>
<jsp:useBean id="column_totals_tasks_assigned" class="java.util.LinkedHashMap" scope="page"/>


<body>

<div class="page">
    <div class="page-container">

        <%@ include file="menu.jsp"%>
        <div class="content-wrapper" id="content">
            <div class="content-container">
                <div class="content clearFix">

                       
                    <div class="content-main">

						
                        <div class="source-wr stats">
                            <div class="title-bg">
                                <div class="title-bg-name">Number	of	Tasks	Created	by	User <div class="float-right">
                               <form action="statsPage" id="stats"> 
                                Last<input type="text" id="numberOfDays" value="7"  name="numberOfDays" class="field small"/>days
                                
                                <select id="selectNumberOfDays" name="selectNumberOfDays">
                                	<option value="0">select Options</option>
                                	<option value="1">Today</option>
                                	<option value="2">Yesterday</option>
                                	<option value="7">Past 7 Days</option>
                                	<option value="30">Past 30 Days</option>
                                </select>
                                <button type="submit" class="btn-control create"><i class="icon-create"></i><span>Submit</span></button>
                                 </form>
                                </div></div>
                            </div>

                            <div class="source-container">
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



                        

                    <div class="source-wr stats">
                            <div class="title-bg">
                                <div class="title-bg-name">Number	of	Tasks	Completed	by	User</div>
                            </div>

                            <div class="source-container">
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
                            
                            <c:forEach var="row" items="${dataForTaskCompleted}">
							    <tr>
							    <c:set var="row_total" value="0"/>
							        <td>${row.key}</td>
							        <c:forEach var="column" items="${row.value}">
							            <td>${column.value}</td>
							            <c:set var="row_total" value="${column.value}"/>
							            <c:if test="${empty column_totals_tasks_completed[column.key]}">
							                <c:set target="${column_totals_tasks_completed}" property="${column.key}" value="0"/>
							            </c:if>
							            <c:set target="${column_totals_tasks_completed}" property="${column.key}" value="${column_totals_tasks_completed[column.key] + column.value}"/>
							            
							        </c:forEach>
							         <td class="foo"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${(row_total*100)/totalcompleted}" />%</td>
							    </tr>
							</c:forEach>
                            
                            <tr class="foo">
                            <td>TOTAL</td>
                            <c:forEach var="column_total" items="${column_totals_tasks_completed}">
     							<td>${column_total.value}</td>
  							</c:forEach>
                                <td class="foo"></td>
                            </tr>
                            
						

                        </table>
                            </div>



                        </div>


                    <div class="source-wr stats">
                            <div class="title-bg">
                                <div class="title-bg-name">Number	of	Tasks	Assigned	to	User</div>
                            </div>

                            <div class="source-container">
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
                            
                            <c:forEach var="row" items="${dataForTaskAssigned}">
							    <tr>
							     <c:set var="row_total" value="0"/>
							        <td>${row.key}</td>
							        <c:forEach var="column" items="${row.value}">
							            <td>${column.value}</td>
							            <c:set var="row_total" value="${column.value}"/>
							            <c:if test="${empty column_totals_tasks_assigned[column.key]}">
							                <c:set target="${column_totals_tasks_assigned}" property="${column.key}" value="0"/>
							            </c:if>
							            <c:set target="${column_totals_tasks_assigned}" property="${column.key}" value="${column_totals_tasks_assigned[column.key] + column.value}"/>
							        </c:forEach>
							         <td class="foo"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${(row_total*100)/totalassigned}" />%</td>
							    </tr>
							</c:forEach>
                            
                            <tr class="foo">
                            <td>TOTAL</td>
                            <c:forEach var="column_total" items="${column_totals_tasks_assigned}">
     							<td>${column_total.value}</td>
  							</c:forEach>
                                <td class="foo"></td>
                            </tr>
                            
						

                        </table>
                            </div>



                        </div>






                    </div>


                </div>
            </div>
        </div><!-- /.content-wrapper -->



    </div>
</div><!-- /.page -->

<%@ include file="scripts.html"%>
</body>
</html>