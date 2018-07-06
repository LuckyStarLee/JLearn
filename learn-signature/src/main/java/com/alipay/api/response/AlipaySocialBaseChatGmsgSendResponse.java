package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: alipay.social.base.chat.gmsg.send response.
 * 
 * @author auto create
 * @since 1.0, 2017-07-24 14:51:04
 */
public class AlipaySocialBaseChatGmsgSendResponse extends AlipayResponse {

	private static final long serialVersionUID = 7683589855233181924L;

	/** 
	 * 消息索引号 会话ID_消息ID
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
