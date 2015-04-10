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
	 * Grava no caminho de destino informado (destPath), 
	 * o arquivo informado no parâmetro (fileToSave)
	 * 
	 * @throws java.io.IOException IOException 
	 * @throws java.lang.IllegalStateException IllegalStateException 
	 * 
	 */
	public void saveImage (MultipartFile fileToSave, String destPath) throws IllegalStateException, IOException{
		File f = new File(destPath);
		if(!f.exists()){
			f.mkdirs();
		}
		fileToSave.transferTo(new File(destPath));
	}
}
