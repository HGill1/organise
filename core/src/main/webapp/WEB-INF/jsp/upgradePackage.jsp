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

                        <div class="block-gray" style="width:400px">
                                    <div class="title-block"><strong>Pricing</strong></div>

                         <!--    <div class="txt">
                                text
                            </div> -->

                                    <div class="pricing-wr">
                                        <div class="pricing-cols">
                                            
                                            <div class="pricing-col">
                                                <div class="pricing-item-col gold">
                                                    <div class="pricing-header">PLATINUM</div>
                                                    <div class="pricing-content">
                                                        <div class="pricing-price">
                                                            <p>$${packagesPricing.price}</p>

                                                            <p>per month</p>
                                                        </div>
                                                        <div class="pricing-collaborator">
                                                            <p><strong>${packagesPricing.usersAllowed}</strong> collaborators</p>
                                                        </div>
                                                        <div class="pricing-storage">
                                                        <c:set var="packageStorage" value="${packagesPricing.storageAllowed}GB"/>
                                                        <c:if test="${packagesPricing.storageAllowed == 0}">
                                                        <c:set var="packageStorage" value="Unlimited"/>
                                                        </c:if>
                                                            <p>${packageStorage}</p>

                                                            <p>storage</p>
                                                        </div>
                                                       <div class="pricing-info">
                                                            <p><strong>Priority email, phone and webinar
                                                                training</strong> <br>support
                                                            </p>
                                                        </div>
                                                    </div>


                                                    <div class="pricing-footer">
                                                        <form action="<fmt:message key="paypal.url"/>" method="post">
                                                            <input type="hidden" name="cmd" value="_xclick-subscriptions">
                                                            <!-- <input type="hidden" name="cmd" value="_notify-synch">
                                                            <input type="hidden" name="tx" value="68H72064BA2356209">
                                                            <input type="hidden" name="at" value="JFWjUmuCIjRutKnPWnUe87Kp-fIqX1ZVnNbBeM3PYHk_y4x3zUgMqD9kbL0">  -->
                                                            <input type="hidden" name="business" value="<fmt:message key="paypal.account"/>">
                                                            <input type="hidden" name="item_name" value="Organise Platnium Plan">
                                                            <input type="hidden" name="item_number" value="${packagesPricing.id}">
                                                            <input type="hidden" name="groupId" value="1">
                                                            <input type="hidden" name="no_shipping" value="1">
                                                            <input type="hidden" name="return" value="<fmt:message key="return.url"/>">
                                                            <input type="hidden" name="rm" value="2">
                                                            <input type="hidden" name="cancel_return" value="<fmt:message key="cancel.upgrade.url"/>">
                                                            <input type="hidden" name="currency_code" value="USD">
                                                            <input type="hidden" name="a3" value="${packagesPricing.price}.00">
                                                            <input type="hidden" name="p3" value="1">
                                                            <input type="hidden" name="t3" value="M">
                                                            <input type="hidden" name="src" value="1">
                                                            <input type="hidden" name="sra" value="1">

                                                            <input type="submit" value="Choose plan">
                                                        </form>
                                                    </div>


                                                </div>
                                            </div>
                                            <!-- <div class="pricing-col">
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
                                            </div> -->
                                        </div>

                                    </div>



                                </div>


                    </div>


                </div>
            </div>
        </div><!-- /.content-wrapper -->

			<div class="footer-wrapper" id="footer">
				<div class="footer-container">
					<div class="footer">&copy; 2010 JEMM MEDIA GROUP, LLC ALL
						RIGHTS RESERVED.</div>
				</div>
			</div>

		</div>
</div><!-- /.page -->


<%@ include file="scripts.html"%>
</body>
</html>

