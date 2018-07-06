import java.text.SimpleDateFormat;
import java.util.Date;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeCreateRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AntMerchantExpandIndirectCreateRequest;
import com.alipay.api.request.AntMerchantExpandIndirectModifyRequest;
import com.alipay.api.request.AntMerchantExpandIndirectQueryRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeCreateResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.api.response.AntMerchantExpandIndirectCreateResponse;
import com.alipay.api.response.AntMerchantExpandIndirectModifyResponse;
import com.alipay.api.response.AntMerchantExpandIndirectQueryResponse;


public class AliTest {
	static String sub_mchnt_id="2088621897309812";
	static AlipayClient alipayClient = new DefaultAlipayClient(
			"https://apay.test.95516.com/ali/aligateway",
			"2017040606571877",//沙箱APPID（交行）
			"MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDA7HglcvDgABHnUWxjkzme9AuyKBc2N30XLthZfYsX8dfl816mBpF3O9RgFfezC8Rlbkl6mwtfYOuqMVL5p9C8vcwPJOsQhjM3Am2xKiqA5O6JYqt4H1z9hkiXmazegzqo1F964skuLF0vlrWZXmo496VMHOTDncEx24+/q85g199ofJCH1X60c4LbUKvyjhR6I9rNF1dHLBW9nzyJj/uc2VQeYZiVR4yTBRjS9EaenVG+jLx/TXyRmxKeMRCxgL5sGzVWef4Y/tfrMgvkInQKe1xfwpI/CqoKrEWPMb4VQt33y12wpbaQgf9q1hpgQWOTvd0HvnsQGzrJJ5KZ/4otAgMBAAECggEAWZMx8cldd9PwfCO9HLq17UzIxW6B4IWBCiuQ/nQhCfwbT0RhdNrl3aOk5vwsJzDWfnXnngqxDBb3NO1z5kD51TiWr41nqyN0Uh1JixHV7ETfUGDE1qBRT9FykRkkP6hUqyD4OBlmaY7lsXvbU5uX3F13nVBpEz6C/kBAjTEbQLEMPRUAqx2ApdFz8kGsoO/9vTCfERxhPAXhvKUEZH2j0N3goyaYhCWHLM+Y8G3dz4EHwAoG60H5xDN2xuBBDUZ/nJI0vMPPlBGIQFY5jcS8YClWpV16gxxRkKwu9pS633bDA05JVaAdFV+D4fx0A5/pSOoJN7IbYoPbWU5X0JTHwQKBgQDiTbe1+oGS/eps3uJQSUsyIfrqIOXmKgVh2M1apZA0D6TqywvBL82Y2Qqcd63Hcj4jVXJiLf0tvMqAcv4KmjketD+o8o8vGooLkqTRGl5XQlRD84LktuNo6Jwer5BUbG1wYLqzMi4fa0l3b13krfLK4fWhKEnElYP5ZCAa6e71nQKBgQDaPWlqJQWMB2rgbmFcY1GNAEhnFpbD9ByIi6TSaAhs7jwYwPSBZo2I7bzAybKvsCnIG4lljhcdsHfwnnL4yRWgUJ91wEd7zG5zvbKPJUfRMRDJIe94FlwzSQjLI8/w/WVnlScfllbs0c8dIfTS0Ur2eddcASW441+A4up3gNiJ0QKBgFAIHiUsT3C1fZc9B5pPIVm8bKkqM0O/rqGY857QGHxg3/jtD94lUrdwYnFNXdbADzudt1MDYpsvPgpJIJCNVBAIvM654WtOHm3TUZhlk+GWIojZcHwENc5fHP85JXjF07o/ayd+YpNX9OZZK0J5Rfj8CksRHW15Vu/2uefqvUh9AoGAWQt9m2WHod7U6MWgzAVqHNHkuMsqsMFFcyEnGwJ/jZKWyrLQEIw7a4c04KRrV+vU4GT75ofXPrHl/jNoTcIeJM9AgNb3U7fRyT+5P1bCusP+SVKjVqgo7nP6NohiK734Rg9Ba034IaBplUVpkyr6Hx8Pk+aT8aA0M25ipGfZ8kECgYBoiT8JbQnJ7iNvPln8robXkh6T3tqKAEKpkkw571LHlyvR9TB3f9e9HFGCnGSSZ0y3PBbvg78dk3JKtqykIIl9zduY5mXVRq/TSdPQqCPS1adjYgBtcRbI6E/nAEwK9uT4KH1x8V4y/o1VNvxYmI9CAc2QeOPFCYtFk6zdkOOpjw==",
			"json",
			"UTF-8",
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwyFjugBEq4Pt+B4G1PZkqYHn9LJemr69XWw/yI42WDOt0qEItwmR2VMqMswSqHUc6MCa8F5nfoQpnXCH2r3b38IusfqbSTafXRLaXxAJyZPuKrq4kBY9XTYr+rj/AEYJ9O1DIYHWAew/GgNY++2JNUTse/ixXqO62Q3XXtlNaKT8YyKjj6oj56O/CTF6RZ+y4hj17u+W8GNq2CBhBCJp8qYImLltb6PVDhuOzTFk57CHcuvBarj0sORSqeX6C/NuRjV96CUmbovHdlA5DCwWrH9Oic9CSs+QFW5WiPrNuP4gn6PK/ulmgrPHugQ5GooyZEf270QkHkKW191fFqEy1QIDAQAB",
			"RSA2");
	
	//当面付包含条码支付（alipay.trade.pay），用于收银员使用扫描枪扫描用户支付宝手机app的付款码进行支付
	//{"alipay_trade_pay_response":{"msg":"Success","gmt_payment":"2018-03-30 12:55:57","code":"10000","buyer_user_id":"2088101117955611","invoice_amount":"60.00","fund_bill_list":[{"amount":"60.00","fund_channel":"ALIPAYACCOUNT"}],"out_trade_no":"777888999002","total_amount":"60.00","trade_no":"352018033012555799000000000080","buyer_logon_id":"159****5620","receipt_amount":"60.00","point_amount":"0.00","buyer_pay_amount":"60.00"},"sign":"j4SeFGUWbMUG/kjPdNl79KWfhflBlGfu43ajSmUvj+BEQBnupQ8v61nWdDt3LAQQJH7L9Nu+pUaeuumKg0HYXXty72VUkcdCEbXVVEVm7qcA6ecrkeXRw2BctU/pFAkTt+35NAq5UAmbTU9OgBt/lp7oj6Va7RS5Zt73f9ufTzASpRgaT4ut+OVfUaTct4aiUxIgpirlxHiZIx5/+F8E7fM9utpLk2fD4yy9Xrhk5aTO9YEmLwAesBCztXJwvd+4HxwekR03BM7yE6YvTZbHY8gD69zKfChNIKR3+fzGF1zonvChCzjPvRG5TzD9FmOGjKL6OxCbOiSq+FuRXbH3mg=="}
	//返回 trade_no=352018033012555799000000000080
	public static void testTradePay() throws AlipayApiException{
		AlipayTradePayRequest request = new AlipayTradePayRequest();
		request.setBizContent("{\"out_trade_no\":\""+getOutTradeNo()+"\"," +
				"\"scene\":\"bar_code\"," +
				"\"auth_code\":\"283905073008390559\"," +
				"\"subject\":\"UP订单\"," +
				"\"total_amount\":\"1.00\"," +
				"\"settle_currency\":\"CNY\"," +
				"\"sub_merchant\":{\"merchant_id\":\"2088621897309724\"}" +
				"}");
		request.setNotifyUrl("http://101.231.204.84:8091/sim/notify_url2.jsp");
		AlipayTradePayResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
			System.out.println("调用成功");
		} else {
			System.out.println("调用失败");
		}
	}
	
	public static void testTradeQuery() throws AlipayApiException{
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.setBizContent("{\"out_trade_no\":\"20180403191052640\"}");
		AlipayTradeQueryResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
		System.out.println("调用成功");
		} else {
		System.out.println("调用失败");
		}
	}
	//2088621897309812
	//扫码支付（alipay.trade.precreate），用于POS机产生二维码或打印二维码小票，用户使用支付宝手机app扫二维码支付
	//https://qr.alipay.com/bav201803301502200
	//{"alipay_trade_precreate_response":{"msg":"Success","qr_code":"https://qr.alipay.com/bav201803301502200","code":"10000","out_trade_no":"777888999003"},"sign":"ML1JRpt4yoA1HSw9ke3HyYfL/1+QbZO2UznLk5r5Pbt/D2uSBFzdotbAUzOFicmm5eZrh2ssFRCOaW2WdKHk4DNlUo+Izj2GfWaUJDvz+BHir0k8m92mYMrlHuwgeMnan3PZRdvO6s06qcM4iaqlIJ6CQgxvY1vFc/Waib0e6YSuMFk7PJDX/RUrecr0z+eqyApfK1eMFPYQ8w1a91o/PgGEdJOdwth5e+SzWBbp++9vapGolyXwWyngsrtDcWWj9uQ6CI+ESFm0DuxcPqLR4oX+QRvAxr8xJU95Ff7B1YpcgGE5luS62DbChh1vOjy1j5WA38FZBa/FpyW4x1bjaQ=="}]
	public static void testTradePrecreate() throws AlipayApiException{
		AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
		request.setBizContent(
				"{\"out_trade_no\":\"777888999012\"," +
				//"\"seller_id\":\"2088102170346152\"," +
				"\"total_amount\":\"88.88\"," +
				"\"subject\":\"UP订单\"," +
				"\"total_amount\":\"60.00\"," +
				"\"settle_currency\":\"CNY\"," +
				"\"sub_merchant\":{\"merchant_id\":\""+sub_mchnt_id+"\"}" +
				"}");
		
		AlipayTradePrecreateResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
		System.out.println("调用成功");
		} else {
		System.out.println("调用失败");
		}
	}
	//统一收单交易创建接口（alipay.trade.create），主要用于在支付宝手机app的H5页面内的支付，首先通过该接口创建支付宝订单，然后使用支付宝JSAPI唤起收银台进行支付
	//返回银联订单号trade_no=872018032913085599000000000207
	//2088621897309812
	public static void testTradeCreate() throws AlipayApiException{
		AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
		request.setBizContent("{" +
		"\"out_trade_no\":\""+getOutTradeNo()+"\"," +
		//"\"seller_id\":\"2088102146225135\"," +
		"\"total_amount\":\"88.88\"," +
		//"\"discountable_amount\":8.88," +
		"\"subject\":\"Iphone616G\"," +
		"\"body\":\"购买商品3件共10.00元\"," +
		//这个必送，坑死人啊
		"\"buyer_id\":\"2088102175222213\"," +
		"\"goods_detail\":[{" +
			"\"goods_id\":\"apple-01\"," +
			"\"goods_name\":\"ipad\"," +
			"\"quantity\":1," +
			"\"price\":2000," +
			"\"goods_category\":\"34543238\"," +
			"\"body\":\"特价手机\"," +
			"\"show_url\":\"http://www.alipay.com/xxx.jpg\"" +
		"}]," +
		"\"operator_id\":\"test_operator_id\"," +
		"\"store_id\":\"test_store_id\"," +
		"\"terminal_id\":\"test_terminal_id\"," +
		"\"timeout_express\":\"90m\"," +
		
		/*"\"disable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
		"\"extend_params\":{" +
			"\"sys_service_provider_id\":\"2088511833207846\"," +
			"\"hb_fq_num\":\"3\"," +
			"\"hb_fq_seller_percent\":\"100\"" +
		"}," +
		
		"\"royalty_info\":{" +
			"\"royalty_type\":\"ROYALTY\"," +
			"\"royalty_detail_infos\":[{" +
				"\"serial_no\":1," +
				"\"trans_in_type\":\"userId\"," +
				"\"batch_no\":\"123\"," +
				"\"out_relation_id\":\"20131124001\"," +
				"\"trans_out_type\":\"userId\"," +
				"\"trans_out\":\"2088101126765726\"," +
				"\"trans_in\":\"2088101126708402\"," +
				"\"amount\":0.1," +
				"\"desc\":\"分账测试1\"," +
				"\"amount_percentage\":\"100\"" +
			"}]" +
		"}," +*/
		"\"sub_merchant\":{\"merchant_id\":\""+sub_mchnt_id+"\"}," +
		/*"\"ext_user_info\":{" +
			"\"name\":\"李明\"," +
			"\"mobile\":\"16587658765\"," +
			"\"cert_type\":\"IDENTITY_CARD\"," +
			"\"cert_no\":\"362334768769238881\"," +
			"\"min_age\":\"18\"," +
			"\"fix_buyer\":\"F\"," +
			"\"need_check_info\":\"F\"" +
		"}," +*/
		"\"business_params\":{\"data\":\"123\"}" +
		"}");
		AlipayTradeCreateResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
		System.out.println("调用成功");
		} else {
		System.out.println("调用失败");
		}
	}
	//{"alipay_trade_cancel_response":{"msg":"Success","code":"10000","out_trade_no":"777888999002","retry_flag":"N","trade_no":"352018033013534099000000000014","action":"refund"},"sign":"pyc2mrxKLsRK6/bHzUZHFEeLVclBcj0OD5f2OD4VHv+zwILqPD40V3vVKRvJ344Kewz3hQP1R/Yb3+UvydMX0eTXbBXfFNAh8LToTt5SIuk536jRCn6KOo1g6rSZrE8AgjvR8+jJmor0UC2HorS69Ir3ruh76hpjIwayBMSsP9pCuoe/7bC3f54YQcfv7JQo8weKOTn8zZ0sI/ec3AE2j6BIP+RGB86K+KJ4kWWxGKnOr9VDxANHv8onIVCzYIEaxiqTsW04WIA6YMafoYf3gaS1eCFXHmkq+cS8JlHqVWgQCSo50V90r5e3Eu036H081TcbHBOI+Xr8/W5PaskZfw=="}
	public static void testTradeCancel() throws AlipayApiException{

		AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
		request.setBizContent(
				"{\"out_trade_no\":\"777888999004\"," +
				"\"trade_no\":\"352018033012555799000000000080\"}");
		
		AlipayTradeCancelResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
		System.out.println("调用成功");
		} else {
		System.out.println("调用失败");
		}
	}
	//当out_trade_no trade_no 同时出现的时候，优先判断trade_no是否正确。
	//{"alipay_trade_refund_response":{"refund_detail_item_list":[{"amount":"10.00","fund_channel":"ALIPAYACCOUNT"}],"msg":"Success","code":"10000","out_trade_no":"777888999002","refund_fee":"10.00","gmt_refund_pay":"2018-03-30 13:48:19","trade_no":"352018033013481999000000000006","send_back_fee":"10.00","buyer_logon_id":"159****5620","buyer_user_id":"2088101117955611","fund_change":"Y"},"sign":"TgK6woXSTfJVzOgk7VMtLvvi0ikdsvJMMi0pdAKb+DTssauQhXs8a6o/AsTKvwEaCJDRGiEes0cFafolG8zHW0k2JMgEGFPMEY/KHAzca+k2x2j18NmGeb+jIR2lPQp1I/OZPWXDdhOdrXCcPyHJgmX9P3rgGZsWyFpnsc3dmv1DY3bmrUlDXX7JPMC2wf5cuqQOc/jnk/fZ4WwFSncJvuw5tmR2PLN/G2JYFbklqL4diaRNq5UX2SUGjlG9PgRUYCuP+AMcY5veAmUmlPcftD7WYAc/oMgEPrBxV06O2s+TaNNGBKxEzoc6UXzX17r/Ir2jp5T3vm9FP7G17L7ltQ=="}
	public static void testTradeRefund() throws AlipayApiException{
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.setBizContent(
				"{\"out_trade_no\":\"777888999002\"," +
				"\"trade_no\":\"352018033012555799000000000080\"," +
				"\"refund_amount\":\"10.00\"," +
				"\"refund_currency\":\"CNY\"," +
				"\"refund_reason\":\"正常退款\"," +
				"\"out_request_no\":\"HZ01RF001\"," +
				"\"goods_detai\":[{\"goods_id\":\"apple-01\"," +
								  "\"goods_name\":\"ipad\"," +
								  "\"quantity\":\"1\"," +
								  "\"price\":\"20\"" +
				"}]" +
				"}");
		
		AlipayTradeRefundResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
		System.out.println("调用成功");
		} else {
		System.out.println("调用失败");
		}
	}
	//{"alipay_trade_fastpay_refund_query_response":{"msg":"Success","code":"10000","present_refund_buyer_amount":"0.00","trade_no":"352018033015195499000000000140","present_refund_mdiscount_amount":"0.00","refund_reason":"用户退款请求","present_refund_discount_amount":"0.00","out_request_no":"2014112611001004680073956707"},"sign":"fcvSOUuDicO6c8ue+WFxI9UjitTPFqJYL2n6yfNNCWBlxoB5xALE9Gve6Zi6psDWvl2xu5iQh3sVROgtJg8WK2B1GsSyCyPTx0r2YjMTeeY0Abd2igvIyAS9yYv7o+WCZLYhZuwcaH8NsDJMjlKl5fRoMcIOqiNFM7MKB2iyu3h7cgZcNYUAhVS40nQ408f+glf66CWqrwu488EVvalIQrU5LsrNujr2bYdQ5509DsanQzP2aPMDHaCShRwzMuePH4p5vjiclIKQbC1OqC6fqp1fWCFHnec5yY0lUjEGs/xC7hsZXMtI8XauXQdBYoc+SC3OQUf24t26Xqatql/BRQ=="}
	public static void testTradeFastpayRefundQuery() throws AlipayApiException{
		AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
		request.setBizContent(
				"{\"out_trade_no\":\"777888999002\"," +
				"\"trade_no\":\"352018033012555799000000000080\"," +
				"\"out_request_no\":\"2014112611001004680073956707\"" +
				"}");
		
		AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
		System.out.println("调用成功");
		} else {
		System.out.println("调用失败");
		}
	}
	
	
	//关闭后得到一个新的trade_no=352018033014373599000000000056
	public static void testTradeClose() throws AlipayApiException{
		AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
		request.setBizContent(
				"{\"out_trade_no\":\"20180404184340369\"}");
				/*"{\"out_trade_no\":\"20180404173600994\"," +
				"\"trade_no\":\"172018040421001004210200248581\"}");*/
		
		AlipayTradeCloseResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
		System.out.println("调用成功");
		} else {
		System.out.println("调用失败");
		}
	}
	
	
	
	//sub_merchant_id:2018032912503101
	//2088621897309724
	public static void testIndirectCreate() throws AlipayApiException{
		AntMerchantExpandIndirectCreateRequest request = new AntMerchantExpandIndirectCreateRequest();
		request.setBizContent("{\"external_id\":\"10529005999019\"," +
				"\"name\":\"国际物流公司\"," +
				"\"alias_name\":\"国务流\"," +
				"\"service_phone\":\"95188\"," +
				"\"category_id\":\"2015050700000000\"," +
				"\"source\":\"2088102170346152\"," +
				"\"contact_info\":[{" +
					"\"name\":\"95188\"," +
					"\"mobile\":\"13888888888\"," +
					"\"tag\":\"06\"," +
					"\"type\":\"LEGAL_PERSON\"}]," +
				"\"address_info\":[{\"province_code\":\"370000\",\"city_code\":\"371000\",\"district_code\":\"371002\",\"address\":\"万塘路18号黄龙时代广场B座\",\"longitude\":\"120.760001\",\"latitude\":\"60.270001\",\"type\":\"BUSINESS_ADDRESS\"}]," +
				"\"bankcard_info\":[{\"card_no\":\"62261000200030004000\",\"card_name\":\"一姜\"}],"+
				"\"pay_code_info\":[\"https://www.web.com/cashier\"]," +
				"\"logon_id\":[\"hello@domain.com\"]," +
				"\"memo\":\"memo1495004082582\"" +
				"}");
		
		AntMerchantExpandIndirectCreateResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
			System.out.println("调用成功");
		}else {
			System.out.println("调用失败");
		}
	}

	//{"ant_merchant_expand_indirect_query_response":{"msg":"Success","address_info":"[{\"address\":\"万塘路18号黄龙时代广场B座\",\"cityCode\":\"371000\",\"districtCode\":\"371002\",\"latitude\":\"60.270001\",\"longitude\":\"120.760001\",\"provinceCode\":\"370000\",\"type\":\"BUSINESS_ADDRESS\"}]","code":"10000","memo":"memo1495004082582","external_id":"105290059990199","contact_info":"[{\"mobile\":\"13888888888\",\"name\":\"95188\",\"type\":\"LEGAL_PERSON\"}]","source":"2088100020003000","alias_name":"国务流","bankcard_info":"[{\"cardName\":\"一姜\",\"cardNo\":\"62261000200030004000\"}]","sub_merchant_id":"2018032912503101","service_phone":"95188","category_id":"2015050700000000","name":"国际物流公司","indirect_level":"INDIRECT_LEVEL_M2"},"sign":"Y+9hHSkS/W0mm07l3i858U9/7OHfTPwqugMnL16HDM54OHzyZFlB2XiUcilxb934myw9+LhRsOuYnle/XDs8Et74lW8YyLAo34Yfl3+H3VGBRzS66n6g6ktpOf9xdf8Kz92Y7LX3hPjMlCsmvKXDn1ausVDOJ5rFK56awZoEjZw8KvUp5ttgc5aBsdQKUJs0JUHRm8IRwuSJ9hUM+hw1s3mNQvVzCVWy1YOM/0ZE92EpAcpCdH3ZbUn/cNJSpbWUSfAhFgc9Elp/ak2+1lq7D25HZpVONjQedjMa5YdHyF9KG9pAuECjPTaiA2wvQx/EjqkblTT5FkDNGLGSPAs3Zg=="}
	public static void testIndirectQuery() throws AlipayApiException{
		AntMerchantExpandIndirectQueryRequest request = new AntMerchantExpandIndirectQueryRequest();
		request.setBizContent("{\"external_id\":\"105290059990199\"," +
				"\"sub_merchant_id\":\""+sub_mchnt_id+"\"" +
				"}");
		
		AntMerchantExpandIndirectQueryResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
			System.out.println("调用成功");
		}else {
			System.out.println("调用失败");
		}
	}
	//{"ant_merchant_expand_indirect_modify_response":{"msg":"Success","external_id":"105290059990199","code":"10000","sub_merchant_id":"2018032912503101"},"sign":"kjkksgAvBK1EsbqSBmMc6ll0G52hXsktP3HHIIi0Bno8iTOmII6obSIK6SXJOuvq43dOvK7ixVcjXv3NIkR76e7r8ZrbDZsD2737pL3oAX/amd3Y7v4gOrdYxqapb4asMOfNUtqVoSEnIoOKqpTHTKBwEV4Ri65vSYxAcD97wjhZ1tb4qxc1sIOO+ZBvtGbRkzUTm8iroFBJuDFjHHTeUfQNL6qDhtMkEma47qt/rdcNWG7ZlziNNW3sYCQRftjcCDtG28XpPJNa29EQwsR2ZBZEly5l6aiSY6cU4wqVGaIm0dGIZrTqg4GZWJt+YeJODyzGNql9Q4p7I+fav81KxQ=="}]
	public static void testIndirectModify() throws AlipayApiException{
		AntMerchantExpandIndirectModifyRequest request = new AntMerchantExpandIndirectModifyRequest();
		request.setBizContent("{\"external_id\":\"10529005999019911\"," +
				"\"sub_merchant_id\":\""+sub_mchnt_id+"\"," +		
				"\"name\":\"国际物流公司(v2)\"," +
				"\"alias_name\":\"国务流(v2)\"," +
				"\"service_phone\":\"95187\"," +
				"\"category_id\":\"2015050700000000\"," +
				"\"source\":\"2088100020003000\"," +
				"\"contact_info\":[{" +
					"\"name\":\"95188\"," +
					"\"mobile\":\"13888888888\"," +
					"\"tag\":\"06\"," +
					"\"type\":\"LEGAL_PERSON\"}]," +
				"\"address_info\":[{\"province_code\":\"370000\",\"city_code\":\"371000\",\"district_code\":\"371002\",\"address\":\"万塘路18号黄龙时代广场B座\",\"longitude\":\"120.760001\",\"latitude\":\"60.270001\",\"type\":\"BUSINESS_ADDRESS\"}]," +
				"\"bankcard_info\":[{\"card_no\":\"62261000200030004000\",\"card_name\":\"一姜\"}],"+
				"\"pay_code_info\":\"https://www.web.com/cashier\"," +
				"\"logon_id\":\"hello@domain.com\"," +
				"\"memo\":\"memo1495004082582\"" +
				"}");
		AntMerchantExpandIndirectModifyResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
			System.out.println("调用成功");
		}else {
			System.out.println("调用失败");
		}
	}
	
	public static String getOutTradeNo() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}
	public static void  testSign() throws AlipayApiException{
		String privateKey ="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDA7HglcvDgABHnUWxjkzme9AuyKBc2N30XLthZfYsX8dfl816mBpF3O9RgFfezC8Rlbkl6mwtfYOuqMVL5p9C8vcwPJOsQhjM3Am2xKiqA5O6JYqt4H1z9hkiXmazegzqo1F964skuLF0vlrWZXmo496VMHOTDncEx24+/q85g199ofJCH1X60c4LbUKvyjhR6I9rNF1dHLBW9nzyJj/uc2VQeYZiVR4yTBRjS9EaenVG+jLx/TXyRmxKeMRCxgL5sGzVWef4Y/tfrMgvkInQKe1xfwpI/CqoKrEWPMb4VQt33y12wpbaQgf9q1hpgQWOTvd0HvnsQGzrJJ5KZ/4otAgMBAAECggEAWZMx8cldd9PwfCO9HLq17UzIxW6B4IWBCiuQ/nQhCfwbT0RhdNrl3aOk5vwsJzDWfnXnngqxDBb3NO1z5kD51TiWr41nqyN0Uh1JixHV7ETfUGDE1qBRT9FykRkkP6hUqyD4OBlmaY7lsXvbU5uX3F13nVBpEz6C/kBAjTEbQLEMPRUAqx2ApdFz8kGsoO/9vTCfERxhPAXhvKUEZH2j0N3goyaYhCWHLM+Y8G3dz4EHwAoG60H5xDN2xuBBDUZ/nJI0vMPPlBGIQFY5jcS8YClWpV16gxxRkKwu9pS633bDA05JVaAdFV+D4fx0A5/pSOoJN7IbYoPbWU5X0JTHwQKBgQDiTbe1+oGS/eps3uJQSUsyIfrqIOXmKgVh2M1apZA0D6TqywvBL82Y2Qqcd63Hcj4jVXJiLf0tvMqAcv4KmjketD+o8o8vGooLkqTRGl5XQlRD84LktuNo6Jwer5BUbG1wYLqzMi4fa0l3b13krfLK4fWhKEnElYP5ZCAa6e71nQKBgQDaPWlqJQWMB2rgbmFcY1GNAEhnFpbD9ByIi6TSaAhs7jwYwPSBZo2I7bzAybKvsCnIG4lljhcdsHfwnnL4yRWgUJ91wEd7zG5zvbKPJUfRMRDJIe94FlwzSQjLI8/w/WVnlScfllbs0c8dIfTS0Ur2eddcASW441+A4up3gNiJ0QKBgFAIHiUsT3C1fZc9B5pPIVm8bKkqM0O/rqGY857QGHxg3/jtD94lUrdwYnFNXdbADzudt1MDYpsvPgpJIJCNVBAIvM654WtOHm3TUZhlk+GWIojZcHwENc5fHP85JXjF07o/ayd+YpNX9OZZK0J5Rfj8CksRHW15Vu/2uefqvUh9AoGAWQt9m2WHod7U6MWgzAVqHNHkuMsqsMFFcyEnGwJ/jZKWyrLQEIw7a4c04KRrV+vU4GT75ofXPrHl/jNoTcIeJM9AgNb3U7fRyT+5P1bCusP+SVKjVqgo7nP6NohiK734Rg9Ba034IaBplUVpkyr6Hx8Pk+aT8aA0M25ipGfZ8kECgYBoiT8JbQnJ7iNvPln8robXkh6T3tqKAEKpkkw571LHlyvR9TB3f9e9HFGCnGSSZ0y3PBbvg78dk3JKtqykIIl9zduY5mXVRq/TSdPQqCPS1adjYgBtcRbI6E/nAEwK9uT4KH1x8V4y/o1VNvxYmI9CAc2QeOPFCYtFk6zdkOOpjw==";
		String signContent="app_id=1266000048280001&biz_content={\"external_id\":\"123456789\",\"name\":\"王立强测试\",\"alias_name\":\"测试王\",\"service_phone\":\"13800000000\",\"category_id\":\"2015050700000000\",\"source\":\"2088911212416201\",\"contact_info\":[{\"name\":\"王立强\",\"type\":\"LEGAL_PERSON\",\"tag\":[\"06\"]}]}&charset=UTF-8&format=json&method=ant.merchant.expand.indirect.create&sign_type=RSA&timestamp=2018-04-03 15:17:44&version=1.0";
		System.out.println(AlipaySignature.rsaSign(signContent, privateKey, "UTF-8","RSA2"));
	}
	
	public static void main(String[] args) throws AlipayApiException {
		testTradePay();
		//testTradeQuery();
		//testTradePrecreate();
		//testTradeCreate();
		//testTradeCancel();
		//testTradeRefund();
		//testTradeFastpayRefundQuery();
		//testTradeClose();
		//testIndirectCreate();
		//testIndirectQuery();
		//testIndirectModify();
		//testSign();
	}
}
