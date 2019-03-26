package com.tianya.mw.LearnQRCode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
* Copyright: Copyright (c) 2019 tianwyam
* 
* @ClassName: QRCodeUtils.java
* @Description: 二维码工具
* @version: v1.0.0
* @author: tianwyam
* @date: 2019年3月23日 上午11:28:05
 */
public class QRCodeUtils {
	
	private static transient final Logger log = Logger.getLogger(QRCodeUtils.class);
	
	
	/**
	* @Function: QRCodeUtils.java
	* @Description: 生成二维码
	* <p>
		纠错能力: <font color="red">ERROR_CORRECTION</font>
	　　　　<li>L级：约可纠错7%的数据码字</li>
	　　　　<li>M级：约可纠错15%的数据码字</li>
	　　　　<li>Q级：约可纠错25%的数据码字</li>
	　　　　<li>H级：约可纠错30%的数据码字 </li>
	* </p>
	* @param path 生成后的二维码存放路径
	* @param contents 二维码里面存放的内容
	* @return 生成后的二维码路径+名称
	* @version: v1.0.0
	* @author: tianwyam
	* @date: 2019年3月23日 上午11:40:58
	 */
	public static String create(String path, String contents, int width, int height , String imgFormat) {
		
		try {
			
			// 文件夹路径不存在，则创建
			File qrDir = new File(path);
			if (!qrDir.exists() ) {
				qrDir.mkdirs();
			}
			
			// 存放二维码文件路径
			Path file = Paths.get(path, getImgFileName(imgFormat));
			
			// 二维码 编码设置
			Map<EncodeHintType, Object> hints = new HashMap<>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
			
			// 生成矩阵
			BitMatrix matrix = new MultiFormatWriter()
					.encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
			
			// 生成二维码图片
			MatrixToImageWriter.writeToPath(matrix, imgFormat, file);
			
			// 返回 二维码图片路径
			return file.toString();
		} catch (Exception e) {
			log.error("生成二维码发生异常！", e);
		}
		
		return "" ;
	}
	
	
	
	
	/**
	* @Function: QRCodeUtils.java
	* @Description: 解析二维码
	* @param path 将要解析的二维码的文件路径
	* @return 二维码的内容
	* @version: v1.0.0
	* @author: tianwyam
	* @date: 2019年3月23日 下午12:41:29
	 */
	public static String parse(String path) {
		
		// 二维码内容
		String content = "" ;
		try {
			// 获取二维码 图片
			BufferedImage image = ImageIO.read(new File(path));
			BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
			// 分析器
			HybridBinarizer binarizer = new HybridBinarizer(source);
			// 矩阵
			BinaryBitmap bitmap = new BinaryBitmap(binarizer);
			
			// 二维码 解码设置
			Map<DecodeHintType, Object> hints = new HashMap<>();
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			
			// 解析二维码
			Result result = new MultiFormatReader().decode(bitmap, hints);
			content = result.getText();
			
		} catch (Exception e) {
			log.error("解析二维码发生异常！", e);
		}
		
		return content ;
	}
	
	
	/**
	* @Function: QRCodeUtils.java
	* @Description: 生成唯一值
	* @return
	* @version: v1.0.0
	* @author: tianwyam
	* @date: 2019年3月23日 下午12:28:13
	 */
	public static String getUUidPath() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	
	/**
	* @Function: QRCodeUtils.java
	* @Description: 根据要生成的二维码图片格式，生成二维码文件名称
	* @param imgFormat 图片格式/png/jpeg/等
	* @return
	* @version: v1.0.0
	* @author: tianwyam
	* @date: 2019年3月23日 下午12:28:32
	 */
	public static String getImgFileName(String imgFormat) {
		return File.separator + getUUidPath() + "." + imgFormat ;
	}
	
	

}
