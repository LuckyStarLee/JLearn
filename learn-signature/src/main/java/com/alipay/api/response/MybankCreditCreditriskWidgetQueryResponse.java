package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: mybank.credit.creditrisk.widget.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-09-12 12:15:08
 */
public class MybankCreditCreditriskWidgetQueryResponse extends AlipayResponse {

	private static final long serialVersionUID = 1384211477795977586L;

	/** 
	 * widget json数据
	 */
	@ApiField("widgetjson")
	private String widgetjson;

	public void setWidgetjson(String widgetjson) {
		this.widgetjson = widgetjson;
	}
	public String getWidgetjson( ) {
		return this.widgetjson;
	}

}
