package com.bigdata.bigdata.utils;

import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * @author zhiqiu
 * @since 2020-03-18
 */
public class UAUtils {

	public static UASparser uaSparser = null;

	static {
		try {
			uaSparser = new UASparser(OnlineUpdater.getVendoredInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static com.bigdata.bigdata.entity.UserAgentInfo getUserAgentInfo(String ua) {
		com.bigdata.bigdata.entity.UserAgentInfo userAgentInfo = null;
		try {
			if (StringUtils.isNotEmpty(ua)) {
				UserAgentInfo parse = uaSparser.parse(ua);
				if (null != parse) {
					userAgentInfo = new com.bigdata.bigdata.entity.UserAgentInfo();
					userAgentInfo.setBrowserName(parse.getUaFamily());
					userAgentInfo.setBrowserVersion(parse.getBrowserVersionInfo());
					userAgentInfo.setOsName(parse.getOsFamily());
					userAgentInfo.setOsVersion(parse.getOsName());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userAgentInfo;
	}

	public static void main(String[] args) throws IOException {
		//		UASparser parser = uaSparser;
		//		UserAgentInfo info = parser.parse("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; )");
		//		System.out.println(info);
		com.bigdata.bigdata.entity.UserAgentInfo userAgentInfo = UAUtils.getUserAgentInfo("218.74.48.154 - - [30/Jan/2019:00:00:22 +0800] \"GET /.well-known/apple-app-site-association HTTP/1.1\" 200 165 \"www.imooc.com\" \"-\" - \"swcd (unknown version) CFNetwork/974.2.1 Darwin/18.0.0\" \"-\" 10.100.135.47:80 200 0.001 0.001\n");
		System.out.println(userAgentInfo.toString());
	}
}
