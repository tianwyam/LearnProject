package com.tianya.mw.LearnQRCode;

import org.junit.Test;

public class QRCodeTest {

	
	@Test
	public void create() {
		
		String path = QRCodeUtils.create("f:\\qrcode\\", "大家好，这儿是tianwyam的二维码学习", 300, 300, "jpeg");
		System.out.println(path);
		
		String content = QRCodeUtils.parse(path);
		System.out.println(content);
	}
	
}
