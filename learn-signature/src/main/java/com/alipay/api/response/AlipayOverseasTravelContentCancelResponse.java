package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: alipay.overseas.travel.content.cancel response.
 * 
 * @author auto create
 * @since 1.0, 2017-11-29 17:16:11
 */
public class AlipayOverseasTravelContentCancelResponse extends AlipayResponse {

	private static final long serialVersionUID = 3386629251658193587L;

	/** 
	 * 结果描述，仅当幂等成功时有值
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
