/**
 * 
 */
package com.jemmmedia.organise.mybatis.db.mybatis.model;

import java.util.Date;

/**
 * @author harjinder
 *
 */
public class PaypalBean {
	
	private String txn_type;
	private String subscr_id;
	
	private String last_name;
	private String residence_country;
	
	private String mc_currency;
	private String item_name;
	
	private String business;
	private String amount1;
	
	private String amount3;
	private String recurring;
	
	private String payer_status;
	private String payer_email;
	
	private String first_name;
	private String receiver_email;
	
	private String payer_id;
	private String reattempt;
	
	private long item_number;
	private Date subscr_date;
	
	private String charset;
	private String period1;
	
	private String mc_amount1;
	private String period3;
	
	private String mc_amount3;
	private String auth;
	
	private String form_charset;
	private String user_login;
	
	private String exchange_rate;
	private String auth_status;
	
	private String invoice;
	private String mc_fee;
	
	private String mc_gross;
	private String memo;
	
	private String transaction_entity;
	private Long groupId;
	public String getTxn_stype() {
		return txn_type;
	}
	public void setTxn_type(String txn_type) {
		this.txn_type = txn_type;
	}
	public String getSubscr_id() {
		return subscr_id;
	}
	public void setSubscr_id(String subscr_id) {
		this.subscr_id = subscr_id;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getResidence_country() {
		return residence_country;
	}
	public void setResidence_country(String residence_country) {
		this.residence_country = residence_country;
	}
	public String getMc_currency() {
		return mc_currency;
	}
	public void setMc_currency(String mc_currency) {
		this.mc_currency = mc_currency;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public String getAmount1() {
		return amount1;
	}
	public void setAmount1(String amount1) {
		this.amount1 = amount1;
	}
	public String getAmount3() {
		return amount3;
	}
	public void setAmount3(String amount3) {
		this.amount3 = amount3;
	}
	public String getRecurring() {
		return recurring;
	}
	public void setRecurring(String recurring) {
		this.recurring = recurring;
	}
	public String getPayer_status() {
		return payer_status;
	}
	public void setPayer_status(String payer_status) {
		this.payer_status = payer_status;
	}
	public String getPayer_email() {
		return payer_email;
	}
	public void setPayer_email(String payer_email) {
		this.payer_email = payer_email;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getReceiver_email() {
		return receiver_email;
	}
	public void setReceiver_email(String receiver_email) {
		this.receiver_email = receiver_email;
	}
	public String getPayer_id() {
		return payer_id;
	}
	public void setPayer_id(String payer_id) {
		this.payer_id = payer_id;
	}
	public String getReattempt() {
		return reattempt;
	}
	public void setReattempt(String reattempt) {
		this.reattempt = reattempt;
	}
	public long getItem_number() {
		return item_number;
	}
	public void setItem_number(long item_number) {
		this.item_number = item_number;
	}
	public Date getSubscr_date() {
		return subscr_date;
	}
	public void setSubscr_date(Date subscr_date) {
		this.subscr_date = subscr_date;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getPeriod1() {
		return period1;
	}
	public void setPeriod1(String period1) {
		this.period1 = period1;
	}
	public String getMc_amount1() {
		return mc_amount1;
	}
	public void setMc_amount1(String mc_amount1) {
		this.mc_amount1 = mc_amount1;
	}
	public String getPeriod3() {
		return period3;
	}
	public void setPeriod3(String period3) {
		this.period3 = period3;
	}
	public String getMc_amount3() {
		return mc_amount3;
	}
	public void setMc_amount3(String mc_amount3) {
		this.mc_amount3 = mc_amount3;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getForm_charset() {
		return form_charset;
	}
	public void setForm_charset(String form_charset) {
		this.form_charset = form_charset;
	}
	public String getUser_login() {
		return user_login;
	}
	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}
	public String getExchange_rate() {
		return exchange_rate;
	}
	public void setExchange_rate(String exchange_rate) {
		this.exchange_rate = exchange_rate;
	}
	public String getAuth_status() {
		return auth_status;
	}
	public void setAuth_status(String auth_status) {
		this.auth_status = auth_status;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getMc_fee() {
		return mc_fee;
	}
	public void setMc_fee(String mc_fee) {
		this.mc_fee = mc_fee;
	}
	public String getMc_gross() {
		return mc_gross;
	}
	public void setMc_gross(String mc_gross) {
		this.mc_gross = mc_gross;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getTransaction_entity() {
		return transaction_entity;
	}
	public void setTransaction_entity(String transaction_entity) {
		this.transaction_entity = transaction_entity;
	}
	public String getTxn_type() {
		return txn_type;
	}
	/**
	 * @return the groupId
	 */
	public Long getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
}
