package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: alipay.security.risk.authentication.cancel response.
 * 
 * @author auto create
 * @since 1.0, 2017-11-27 13:53:16
 */
public class AlipaySecurityRiskAuthenticationCancelResponse extends AlipayResponse {

	private static final long serialVersionUID = 4331476268534257987L;

	/** 
	 * 返回取消结果
	 */
	@ApiField("biz_result")
	private String bizResult;

	public void setBizResult(String bizResult) {
		this.bizResult = bizResult;
	}
	public String getBizResult( ) {
		return this.bizResult;
	}

}
