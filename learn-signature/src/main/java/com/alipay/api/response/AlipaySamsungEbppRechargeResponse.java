package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: alipay.samsung.ebpp.recharge response.
 * 
 * @author auto create
 * @since 1.0, 2017-04-26 15:45:26
 */
public class AlipaySamsungEbppRechargeResponse extends AlipayResponse {

	private static final long serialVersionUID = 7191942645627114198L;

	/** 
	 * 直接返回页面
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
