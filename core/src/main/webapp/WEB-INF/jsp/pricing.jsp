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
                       <!--  <a href="#">&larr; show dashboard</a> -->
                    </div>

                       
                    <div class="content-main pricing-txt">

                        <div class="block-gray">
                                    <div class="title-block form-errors"><strong>Your account has been expired. If you would like to reactivate your account in order to continue using Organise please select a package below</strong></div>

                         <!--    <div class="txt">
                                text
                            </div> -->

                                    <div class="pricing-wr">
                                        <div class="pricing-cols">

                                            <div class="pricing-col">
                                                <div class="pricing-item-col">
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
                                                            <input type="hidden" name="cancel_return" value="<fmt:message key="cancel.pricing.url"/>">
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
                                                <div class="pricing-item-col">
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
                                                            <input type="hidden" name="cancel_return" value="<fmt:message key="cancel.pricing.url"/>">
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
                                                <div class="pricing-item-col gold">
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
                                                            <input type="hidden" name="cancel_return" value="<fmt:message key="cancel.pricing.url"/>">
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
                                                <div class="pricing-item-col">
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


<%@ include file="scripts.html"%>
</body>
</html>

