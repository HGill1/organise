package com.jemmmedia.organise.mybatis.db.mybatis.model;

public class PackagesSupport extends BaseModel {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column packages_support.support_desc
	 * @mbggenerated  Tue Mar 12 14:20:31 GMT 2013
	 */
	private String supportDesc;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column packages_support.support_desc
	 * @return  the value of packages_support.support_desc
	 * @mbggenerated  Tue Mar 12 14:20:31 GMT 2013
	 */
	public String getSupportDesc() {
		return supportDesc;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column packages_support.support_desc
	 * @param supportDesc  the value for packages_support.support_desc
	 * @mbggenerated  Tue Mar 12 14:20:31 GMT 2013
	 */
	public void setSupportDesc(String supportDesc) {
		this.supportDesc = supportDesc == null ? null : supportDesc.trim();
	}
}