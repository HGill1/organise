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
    <link rel="icon" type="image/png" href="resources/images/Jemm bullet.png" />
</head>
<body>

<div class="page">
    <div class="page-container">

        <div class="header-wrapper" id="header">
            <div class="header-container">
                <div class="header">
                    <div class="header-left">
                        <div class="logo">
                            <a href="/"></a>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /.header-wrapper -->

        <div class="login-wrapper">
        <div class="form-errors">
 			${msgEmailNotExist}
 		</div>   
            <div class="login">
                <form action="sendPassword" id="forgotPassword" name="forgotPassword">
                    <div class="form-line">
                        <label>Email address:</label> <input type="text" class="field" id="email" name="email">
                        <span class="login-note">To reset your password, enter the email address you use to sign in to Organise. </span>
                    </div>
                    <div class="form-line">
                        <div class="form-offset">
                            <button type="submit" class="btn-control submit-blue"><span>Reset</span></button>
                            <!-- <a href="#">I remember my password.</a> -->
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