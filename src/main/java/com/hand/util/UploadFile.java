package com.hand.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

public class UploadFile {
	public static String SaveFile(File imgUpLoad,String oldFileName){
		
		try{
		//基于myFile创建一个文件输入流  
        InputStream is = new FileInputStream(imgUpLoad);  
        
        String uploadPath = ServletActionContext.getServletContext().getRealPath("/jsp/img");  
        String s=UUID.randomUUID().toString();
		String fileName=s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
		String[]  strTmp=oldFileName.split("\\.");
		fileName+="."+strTmp[1];
		
        // 设置目标文件  
        File toFile = new File(uploadPath,fileName);  
          
        // 创建一个输出流  
        OutputStream os = new FileOutputStream(toFile);  
  
        //设置缓存  
        byte[] buffer = new byte[1024];  
  
        int length = 0;  
  
        //读取myFile文件输出到toFile文件中  
        while ((length = is.read(buffer)) > 0) {  
            os.write(buffer, 0, length);  
        }
 
        //关闭输入流  
        is.close();  
        //关闭输出流  
        os.close();
        
        return fileName;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return "";
		}
        
	}
}
