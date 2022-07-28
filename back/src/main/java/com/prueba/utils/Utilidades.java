package com.prueba.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utilidades {
	
	public static Date convertirStringToDate(String fecha) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return null;
	}


	public static String[] formatearFecha(Date fecha) {
		try {
			String [] resultados = new String[2];
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int dia = dateToCalendar(fecha).get(Calendar.DAY_OF_MONTH);
			int diaActual = dateToCalendar(new Date()).get(Calendar.DAY_OF_MONTH);
			int atraso = diaActual - dia;
			resultados[0] = atraso + "";
			resultados[1] = format.format(fecha);
			return resultados;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Calendar dateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
}
