package com.sena.disocc.app.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
	public static Date convertirFecha(String fh) {
		
		
		SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy");
    	Date fecha=null;
    	try {
    		fecha= formato.parse(fh);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fecha;	
	}

}
