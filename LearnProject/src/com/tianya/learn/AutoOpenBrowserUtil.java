package com.tianya.learn;

import java.awt.Desktop;
import java.net.URI;


public class AutoOpenBrowserUtil {

	/**
	 * 使用Java打开系统浏览器访问网址
	 * @author tianya
	 * @param url 网址
	 */
	public static void open(String url) {
		// 首先判断是否支持桌面系统
		if (Desktop.isDesktopSupported()) {
			// 获取桌面系统对象
			Desktop desktop = Desktop.getDesktop();
			// 判断是否支持打开浏览器
			if (desktop.isSupported(Desktop.Action.BROWSE)) {
				try {
					// 打开浏览器 URL
					desktop.browse(URI.create(url));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		AutoOpenBrowserUtil.open("www.baidu.com");
	}
	
}
