package com.veiculo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.veiculo.dao.impl.VeiculoDaoImpl;
import com.veiculo.entity.Veiculo;
import com.veiculo.exception.VeiculoException;

/**
 * @author ricardo
 *
 */
@Service
public class VeiculoService {
	
	VeiculoDaoImpl veiculoImpl = new VeiculoDaoImpl();
	FileService fileService = new FileService();
	
	/**
	 * @param veiculo
	 */
	public void save(Veiculo veiculo){
		veiculoImpl.save(veiculo);
	}
	
	/**
	 * @return
	 */
	public List<Veiculo> list(){
		return veiculoImpl.list();
	}
	
	/**
	 * @param id
	 * @return
	 * @throws VeiculoException
	 */
	public Veiculo get(Integer id) throws VeiculoException{
		if(id == null){
			throw new VeiculoException("Informado identificar nulo para a requisição");
		}
		Veiculo veiculo =  veiculoImpl.get(id);
		
		if(veiculo == null){
			throw new VeiculoException("Não encontrado veiculo com o identificador informado");
		}
		
		return veiculo;
	}
	
	/**
	 * @param veiculo
	 * @throws VeiculoException
	 */
	public void delete(Veiculo veiculo) throws VeiculoException{
		if(veiculo == null){
			throw new VeiculoException("Informado identificar nulo para a requisição");
		}
		
		veiculoImpl.delete(veiculo);
	}
	
	/**
	 * @param veiculo
	 * @param fabricante
	 * @param ano
	 * @param modelo
	 * @param foto
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public void updateVeiculo(Veiculo veiculo, String fabricante, String ano, String modelo, MultipartFile foto) throws IllegalStateException, IOException{
		veiculo.setFabricante(fabricante);
		veiculo.setAno(ano);
		veiculo.setModelo(modelo);
		if(foto != null && foto.getOriginalFilename().length() > 0){
			veiculo.setFoto(foto.getOriginalFilename());
		}
		
		veiculoImpl.merge(veiculo);
	}
}
