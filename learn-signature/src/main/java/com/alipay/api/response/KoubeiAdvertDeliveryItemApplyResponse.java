package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: koubei.advert.delivery.item.apply response.
 * 
 * @author auto create
 * @since 1.0, 2017-09-29 15:07:00
 */
public class KoubeiAdvertDeliveryItemApplyResponse extends AlipayResponse {

	private static final long serialVersionUID = 5638272743817483653L;

	/** 
	 * 广告id对应的权益id
	 */
	@ApiField("benefit_id")
	private String benefitId;

	public void setBenefitId(String benefitId) {
		this.benefitId = benefitId;
	}
	public String getBenefitId( ) {
		return this.benefitId;
	}

}
