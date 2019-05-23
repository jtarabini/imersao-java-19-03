package br.com.targettrust.traccadastros.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {
	public static LocalDate createDate(String data) {
		try {
			String pattern = "dd/MM/yyyy HH:mm";
			DateFormat formatter = new SimpleDateFormat(pattern);
			Date date = formatter.parse(data);
			ZoneId defaultZoneId = ZoneId.systemDefault();;
			return date.toInstant().atZone(defaultZoneId).toLocalDate();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
