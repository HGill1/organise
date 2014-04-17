/**
 * 
 */
package com.jemmmedia.organise.mybatis.db.mybatis.model;

/**
 * @author harjinder
 *
 */
public class PaidUserObject {
	
	private String email;
	private PaypalBean paypalBean;
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the paypalBean
	 */
	public PaypalBean getPaypalBean() {
		return paypalBean;
	}
	/**
	 * @param paypalBean the paypalBean to set
	 */
	public void setPaypalBean(PaypalBean paypalBean) {
		this.paypalBean = paypalBean;
	}
	
	
}
