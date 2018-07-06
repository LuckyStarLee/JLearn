package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: alipay.overseas.travel.content.create response.
 * 
 * @author auto create
 * @since 1.0, 2017-11-29 17:17:51
 */
public class AlipayOverseasTravelContentCreateResponse extends AlipayResponse {

	private static final long serialVersionUID = 2211227424334773627L;

	/** 
	 * 可选，仅当幂等成功时有值
	 */
	@ApiField("result_msg")
	private String resultMsg;

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public String getResultMsg( ) {
		return this.resultMsg;
	}

}
