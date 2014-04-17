<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--[if IE 7]><html class="ie7" id="ie7" lang="en"><![endif]-->
<!--[if IE 8]><html class="ie8" id="ie8" lang="en"><![endif]-->
<!--[if IE 9]><html class="ie9" id="ie9" lang="en"><![endif]-->
<!--[if !IE]><!-->
<html lang="en"><!--<![endif]-->

<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge; chrome=1">
    <link href="resources/css/common.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/png" href="resources/images/Jemm_bullet.png" />
</head>

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

        <div class="login-wrapper">
        <div class="errorblock">
        ${jemmUsersMessage}
 		${checkEmailMsg}
 		</div>   
        <%-- <c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
		</c:if> --%>
		
		<c:if test="${error == true}">
        				 <div class="form-errors">${SPRING_SECURITY_LAST_EXCEPTION.message}</div>
        </c:if>
        
            <div class="login">
                <form action="<c:url value='j_spring_security_check' />"  method="post">
                    <div class="form-line">
                        <label>Login:</label> <input autofocus="" type="text" id="j_username" name="j_username" class="field">
                    </div>
                    <div class="form-line">
                        <label>Password:</label> <input type="password" id="j_password" name="j_password" class="field">
                    </div>
                    <div class="form-offset">
                    <div class="form-line">
                            <input type="checkbox" class="hide" checked="checked" name='_spring_security_remember_me' id="_spring_security_remember_me"> <label class="label-inline hide">Keep me logged in on this computer</label>
                        </div>
                    </div>
                    <div class="form-line">
                        <div class="form-offset">
                            <button type="submit" class="btn-control submit-blue" id="user_session_submit"><span>Login</span></button>
                            <a href="forgotPasswordForm">Forgot your password?</a>
                        </div>
                    </div>
                </form>
            </div>
            <div class="login-footer">
                &copy; Jemm media Ltd. 2007-2012
            </div>
        </div>


    </div>
</div><!-- /.page -->
</body>
</html>