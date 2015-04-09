package com.veiculo.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ricardo
 *
 */
@Service
public class FileService {
	

	
	/**
	 * @throws IOException 
	 * @throws IllegalStateException 
	 * 
	 */
	public void saveImage (MultipartFile fileToSave, String destPath) throws IllegalStateException, IOException{
		//String destPath = servletContext.getRealPath("/resources/") + "/images/" + fileToSave.getOriginalFilename();
		File f = new File(destPath);
		if(!f.exists()){
			f.mkdirs();
		}
		fileToSave.transferTo(new File(destPath));
		
	}
}
