package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: ssdata.dataservice.risk.fraudscore.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-08-16 14:40:17
 */
public class SsdataDataserviceRiskFraudscoreQueryResponse extends AlipayResponse {

	private static final long serialVersionUID = 1643659414751789689L;

	/** 
	 * 基于传入的USER_ID，计算得到的作弊风险值，范围[0,100],分数越高风险程度越高
	 */
	@ApiField("score")
	private String score;

	/** 
	 * 用户唯一请求ID
	 */
	@ApiField("unique_id")
	private String uniqueId;

	public void setScore(String score) {
		this.score = score;
	}
	public String getScore( ) {
		return this.score;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getUniqueId( ) {
		return this.uniqueId;
	}

}
