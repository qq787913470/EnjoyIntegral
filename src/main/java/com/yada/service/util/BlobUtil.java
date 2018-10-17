package com.yada.service.util;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;



public class BlobUtil {
	public static String BLOB2String(Object o) throws SQLException, UnsupportedEncodingException{
		String str = null;
		byte[] inbyte=null;
		if(o instanceof Blob){
			Blob blob = (Blob) o;
	        if (blob != null) {
	            inbyte = blob.getBytes(1, (int) blob.length());
	        }
			str =new String (inbyte,"GBK");
		}
		return str;
	}
}
