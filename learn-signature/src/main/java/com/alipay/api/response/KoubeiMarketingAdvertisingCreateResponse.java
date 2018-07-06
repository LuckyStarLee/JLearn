package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: koubei.marketing.advertising.create response.
 * 
 * @author auto create
 * @since 1.0, 2017-10-10 20:29:35
 */
public class KoubeiMarketingAdvertisingCreateResponse extends AlipayResponse {

	private static final long serialVersionUID = 3196743584326493432L;

	/** 
	 * 创建广告唯一标识
	 */
	@ApiField("ad_id")
	private String adId;

	public void setAdId(String adId) {
		this.adId = adId;
	}
	public String getAdId( ) {
		return this.adId;
	}

}
