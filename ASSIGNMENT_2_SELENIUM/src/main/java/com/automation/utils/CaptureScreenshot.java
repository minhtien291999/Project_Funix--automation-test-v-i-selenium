package com.automation.utils;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;

public class CaptureScreenshot {
	public static void takeScreenshot(WebDriver driver, String imageName) {
		try {
			File f = new File("./Screenshots");
			if (!f.exists()) {
				f.mkdir();
			}
			// Chụp màn hình
			BufferedImage image = new Robot()
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

			// Tạo đối tượng file với tên đã đặt bên trên tại thư mục /ScreenShots,
			// Sau đó thực hiện cope file ảnh vào đó
			File destiny = new File("./Screenshots" + imageName + ".jpg");
			ImageIO.write(image, "jpg", destiny);
			
			//FileHandler.copy(image, destiny);
			
		} catch (Exception ex) {
			System.out.println("Đã xảy ra lỗi khi chụp màn hình");
			ex.printStackTrace();
		}

	}
}
