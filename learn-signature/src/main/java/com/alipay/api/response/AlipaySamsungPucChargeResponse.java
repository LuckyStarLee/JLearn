package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: alipay.samsung.puc.charge response.
 * 
 * @author auto create
 * @since 1.0, 2017-06-22 16:17:39
 */
public class AlipaySamsungPucChargeResponse extends AlipayResponse {

	private static final long serialVersionUID = 7366396548243674929L;

	/** 
	 * zhijiefanhui yemian
	 */
	@ApiField("page_retrun")
	private String pageRetrun;

	public void setPageRetrun(String pageRetrun) {
		this.pageRetrun = pageRetrun;
	}
	public String getPageRetrun( ) {
		return this.pageRetrun;
	}

}
