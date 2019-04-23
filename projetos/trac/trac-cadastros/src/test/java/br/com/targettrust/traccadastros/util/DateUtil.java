package br.com.targettrust.traccadastros.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static Date createDate(String data) {
		try {
			String pattern = "dd/MM/yyyy HH:mm";
			DateFormat formatter = new SimpleDateFormat(pattern);
			return formatter.parse(data);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
