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
                <div class="content clearFix">

                       
                    <div class="content-main">


                        <div class="source-wr">
                            <div class="title-bg">
                                <div class="title-bg-name">Topics management</div>
                            </div>

                            <div class="create-btn-wrapper">
                                <a href="addSourcesForm" class="task-new"><i class="icon-plus"></i>Create Topic</a>
                            </div>

                            <div class="source-container">
                                <table class="source-table">
                            <tr class="source-header">
                                <th>Name</th>
                                <!-- <th><a href="#" class="sort-table-arrow"></a>Url</th> -->
                                <th>Color</th>
                                <th>Visible by</th>
                                <th>Actions</th>
                            </tr>
                            <c:forEach var="source" items="${feedSources}">
                            <tr>
                                <td>
                                    <div class="source-type-name" style="background: ${source.colour};">${source.name}</div>
                                    <div class="source-type">${source.sourceType}</div>
                                </td>
                                <%-- <td>
                                    <div class="source-url" style="color: ${source.colour};">${source.url}</div>
                                </td> --%>
                                <td>
                                    <div class="source-color" style="color: ${source.colour};">${source.colour}</div>
                                </td>
                                <td>
                                    <div class="source-visible">
                                        ${source.users}
                                        <%-- <a href="#" style="color: ${source.colour};">Tony Soprano</a>, <a href="#" style="color: ${source.colour};">Tony Soprano</a>, <a href="#" style="color: ${source.colour};">Tony Soprano</a>, <a href="#" style="color: ${source.colour};">Tony Soprano</a> --%>
                                    </div>
                                </td>
                                <td>
                                    <div class="source-actions">
                                        <!-- <a href="#">Show</a> -->
                                        <a id="editSource" href="editSourcesForm?id=${source.id}">Edit</a>
                                        <a data-control="sourceDestroy" id="deleteSource" href="#">Destroy
                                        <input type="hidden" id="topicId" value="${source.id}" name="topicId"/> 
                                        </a>
                                    </div>
                                </td>
                            </tr>
                            </c:forEach>
                        </table>
                            </div>

                </div>

            </div>
          </div>
        </div>
      </div><!-- /.content-wrapper -->
    </div>
</div><!-- /.page -->
<!-- delete source dialog -->
<div id="dialogDelSource" title="Delete?" style="height: auto !important;">
    <p class="clearFix">
        <span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 0 0;"></span>
        This topic and all its tasks will be
        permanently deleted and cannot be recovered. Are you sure?
    </p>
</div>

<%@ include file="scripts.html"%>
</body>
</html>