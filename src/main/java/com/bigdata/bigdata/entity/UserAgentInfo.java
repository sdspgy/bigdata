package com.bigdata.bigdata.entity;

/**
 * @author zhiqiu
 * @since 2020-03-18
 */
public class UserAgentInfo {

	/*浏览器名称*/
	private String browserName;
	/*浏览器版本*/
	private String browserVersion;
	/*操作系统名称*/
	private String osName;
	/*操作系统版本*/
	private String osVersion;

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getBrowserVersion() {
		return browserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	@Override
	public String toString() {
		return "UserAgentInfo{" +
						"browserName='" + browserName + '\'' +
						", browserVersion='" + browserVersion + '\'' +
						", osName='" + osName + '\'' +
						", osVersion='" + osVersion + '\'' +
						'}';
	}
}
