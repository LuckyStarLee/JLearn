package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: zhima.credit.risk.evaluate.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-10-25 21:49:04
 */
public class ZhimaCreditRiskEvaluateQueryResponse extends AlipayResponse {

	private static final long serialVersionUID = 6852984689188927523L;

	/** 
	 * 风控评估结果[True:准入;False:不准入]
	 */
	@ApiField("accessible")
	private Boolean accessible;

	/** 
	 * 芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账
	 */
	@ApiField("biz_no")
	private String bizNo;

	/** 
	 * 当准入通过且需要输出免押额度等级时，用该字段表示额度等级。当无法评估额度时，用N/A表述
	 */
	@ApiField("limit_level")
	private String limitLevel;

	/** 
	 * 当准入accessible为False时，用该字段描述原因
	 */
	@ApiField("risk_code")
	private String riskCode;

	public void setAccessible(Boolean accessible) {
		this.accessible = accessible;
	}
	public Boolean getAccessible( ) {
		return this.accessible;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	public String getBizNo( ) {
		return this.bizNo;
	}

	public void setLimitLevel(String limitLevel) {
		this.limitLevel = limitLevel;
	}
	public String getLimitLevel( ) {
		return this.limitLevel;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getRiskCode( ) {
		return this.riskCode;
	}

}
