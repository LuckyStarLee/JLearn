package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: alipay.weibo.ebpp.recharge response.
 * 
 * @author auto create
 * @since 1.0, 2017-04-26 15:46:17
 */
public class AlipayWeiboEbppRechargeResponse extends AlipayResponse {

	private static final long serialVersionUID = 2716315683764539465L;

	/** 
	 * 返回缴费页面
	 */
	@ApiField("partnerpuccharge")
	private String partnerpuccharge;

	public void setPartnerpuccharge(String partnerpuccharge) {
		this.partnerpuccharge = partnerpuccharge;
	}
	public String getPartnerpuccharge( ) {
		return this.partnerpuccharge;
	}

}
