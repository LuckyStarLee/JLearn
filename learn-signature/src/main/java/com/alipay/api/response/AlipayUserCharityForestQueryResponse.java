package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: alipay.user.charity.forest.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-08-21 15:03:40
 */
public class AlipayUserCharityForestQueryResponse extends AlipayResponse {

	private static final long serialVersionUID = 8197328968219133783L;

	/** 
	 * 是否开通了蚂蚁森林
	 */
	@ApiField("forest_user")
	private Boolean forestUser;

	public void setForestUser(Boolean forestUser) {
		this.forestUser = forestUser;
	}
	public Boolean getForestUser( ) {
		return this.forestUser;
	}

}
