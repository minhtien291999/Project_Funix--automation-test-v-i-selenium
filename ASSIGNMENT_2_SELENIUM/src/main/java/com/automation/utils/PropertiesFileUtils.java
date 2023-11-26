package com.automation.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtils {
	// 1.tạo 1 đường dẫn properties files trong foder configuration
	private static String CONFIG_PATH = "./configuration/configs.properties";

	// 2.Lấy ra 1 giá trị bất kỳ property bất kỳ theo key;
	public static String getProperty(String key) {
		Properties properties = new Properties();
		String value = null;
		FileInputStream fileInputStream = null;
		// bắt expection
		try {
			fileInputStream = new FileInputStream(CONFIG_PATH);
			properties.load(fileInputStream);
			value = properties.getProperty(key);
			return value;
		} catch (Exception ex) {
			System.out.println("Xảy ra lỗi đọc giá trị của : " + key);
			ex.printStackTrace();
		} finally {
			// luôn luôn nhảy vào đây dù có xảy ra exception hay k
			// thực hiện đóng luồng đọc
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		return value;
	}

	/*
	 * public static void setProperty(String key, String value) { Properties
	 * properties = new Properties(); FileOutputStream fileOutputStream = null; try
	 * { // khi tạo 1 giá trị cho đối tượng FileOutpútStream fileOutputStream = new
	 * FileOutputStream(CONFIG_PATH); properties.setProperty(key, value); // Lưu key
	 * và value vào properties file properties.store(fileOutputStream,
	 * "Set new value in properties");
	 * System.out.println("Set new value in file properties success."); } catch
	 * (IOException e) { e.printStackTrace(); } finally { // luôn luôn nhảy vào đây
	 * dù có xảy ra exception hay k // thực hiện đóng luồng // đọc if
	 * (fileOutputStream != null) { try { fileOutputStream.close(); } catch
	 * (IOException e) { e.printStackTrace(); } } } }
	 */

}
