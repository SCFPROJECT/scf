package com.qinghuo.bdpl.core.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/B2CSend")
@Controller
public class B2CSendTradeTestController {
	@RequestMapping("/sendTrade")
	public void sendTrade(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String merId = "198";
		String orderNo = "20150701172636";
		String payNo = "20150701185498";
		String systemSSN = "010";
		String respCode = "1001";
		String SettDate = "20150701185720";
		String amount = "100.00";
		String currCode = "CNY";
		String orderType = "B2C";
		String callBackUrl = "http://test.gnete.com:8888/bin/scripts/OpenVendor/gnete/v36/OvRcv_Test.asp";
		String langType = "UTF-8";
		String buzType = "01";

		// String msg = merId + orderNo + amount + currCode + orderType +
		// callBackUrl + langType + buzType;
		String msg = orderNo + payNo + amount + currCode + systemSSN + respCode + SettDate;
		String encodeKey = MD5.md5("12hi60ohgmp16nbev0gr8au69bodzguz");
		String encodeMsg = MD5.md5(msg + encodeKey);

		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("MerId", merId);
		reqMap.put("OrderNo", orderNo);
		reqMap.put("OrderAmount", amount);
		reqMap.put("CurrCode", currCode);
		reqMap.put("OrderType", orderType);
		reqMap.put("CallBackUrl", callBackUrl);
		reqMap.put("LangType", langType);
		reqMap.put("BuzType", buzType);
		reqMap.put("SignMsg", encodeMsg);

		StringBuffer sb = new StringBuffer();
		// sb.append("MerId=" + merId + "&");
		sb.append("OrderNo=" + orderNo + "&");
		sb.append("PayNo=" + payNo + "&");
		sb.append("PayAmount=" + amount + "&");
		sb.append("CurrCode=" + currCode + "&");
		sb.append("SystemSSN=" + systemSSN + "&");
		sb.append("RespCode=" + respCode + "&");
		sb.append("SettDate=" + SettDate + "&");
		// sb.append("BuzType=" + buzType + "&");
		sb.append("SignMsg=" + encodeMsg);

		String sendMsg = sb.toString();

		String pay_url = "http://test.gnete.com:8888/bin/scripts/OpenVendor/gnete/v36/OvRcv_Test.asp";

		String sendUrl = pay_url + "?" + sendMsg;

		try {
			response.sendRedirect(sendUrl);
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedEncodingException("-----------发送错误");
		} catch (IOException e) {
			throw new IOException("-----------发送错误------------");
		}

		// HttpClient client = new HttpClient(); // 保证链接及时关闭
		// PostMethod post = null;
		// BufferedReader reader = null;
		// InputStream resInputStream = null;
		// int statusCode = 0;
		//
		// String res = "";
		//
		// try {
		// client.getParams().setParameter("http.protocol.content-charset",
		// "GBK");
		// client.getParams().setParameter("http.connection.timeout", 20000);
		// client.getParams().setParameter("http.socket.timeout", 20000);
		// post = new
		// PostMethod("http://test.gnete.com:8888/bin/scripts/OpenVendor/gnete/V36/GetOvOrder.asp");
		// NameValuePair[] pair = this.queryString2NVPair(reqMap);
		//
		// post.setRequestBody(pair);
		//
		// client.executeMethod(post);
		//
		// statusCode = post.getStatusCode();
		//
		// if (statusCode != 200) {
		//
		// throw new Exception("【CP单笔代扣trade方法-2】【批次号" + post +
		// "】-Method failed: " + post.getStatusLine());
		// }
		//
		// // 读取内容
		// resInputStream = post.getResponseBodyAsStream();
		// // 处理内容
		// reader = new BufferedReader(new InputStreamReader(resInputStream));
		// String tempBf = null;
		// StringBuffer html = new StringBuffer();
		// while ((tempBf = reader.readLine()) != null) {
		//
		// html.append(tempBf);
		// }
		// res = html.toString();
		//
		// } catch (IOException e) {
		// throw new IOException("CP单笔代扣交易发生IOException异常");
		// } catch (Exception e) {
		// throw new Exception("CP单笔代扣交易发生Exception异常");
		// } finally {
		// // 释放httpclient
		// if (post != null) {
		// post.releaseConnection();
		// }
		// if (null != client) {
		// SimpleHttpConnectionManager manager = (SimpleHttpConnectionManager)
		// client.getHttpConnectionManager();
		// if (null == manager) {
		// client.getHttpConnectionManager().closeIdleConnections(0);
		// } else {
		// manager.shutdown();
		// }
		// }
		// if (reader != null) {
		// reader.close();
		// }
		// if (resInputStream != null) {
		// resInputStream.close();
		// }
		// }
		//
		// System.out.println("-------------------" + res);

	}

	// private NameValuePair[] queryString2NVPair(Map<String, String> paraMap) {
	// NameValuePair[] pair = new NameValuePair[paraMap.size()];
	// Iterator<String> it = paraMap.keySet().iterator();
	// int index = 0;
	// while (it.hasNext()) {
	// String key = it.next();
	// pair[index] = new NameValuePair(key, paraMap.get(key));
	// index++;
	// }
	// return pair;
	// }
}
