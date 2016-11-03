package com.jyy.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamUtils {

	public static String StreamToString(InputStream in){
		String result = "";
		
		try{
			
			//创建一个字节数组写入流
			//ByteArrayOutputStream类是在创建它的实例时，
			//程序内部创建一个byte型别数组的缓冲区，
			//然后利用ByteArrayOutputStream和ByteArrayInputStream的实例向数组中写入或读出byte型数据。
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length = 0 ;
			while( ( length=in.read(buffer) )!= -1 ){
				out.write(buffer,0,length);
				out.flush();
			}
			
			result = out.toString();
			
			
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
		
	}
	
}
