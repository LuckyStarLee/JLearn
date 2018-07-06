package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: alipay.social.base.chat.send response.
 * 
 * @author auto create
 * @since 1.0, 2016-07-27 17:27:27
 */
public class AlipaySocialBaseChatSendResponse extends AlipayResponse {

	private static final long serialVersionUID = 8788237555129727434L;

	/** 
	 * msg_index:msgid+sessionId
	 */
	@ApiField("msg_index")
	private String msgIndex;

	public void setMsgIndex(String msgIndex) {
		this.msgIndex = msgIndex;
	}
	public String getMsgIndex( ) {
		return this.msgIndex;
	}

}
