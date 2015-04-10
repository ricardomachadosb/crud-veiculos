package com.veiculo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.veiculo.dao.VeiculoDao;
import com.veiculo.entity.Veiculo;
import com.veiculo.exception.VeiculoException;

/**
 * @author ricardo
 *
 */
@Service
public class VeiculoService {
	
	@Autowired
	VeiculoDao veiculoDao;
	
	@Autowired
	FileService fileService;
	
	/**
	 * Cria um registro no banco de dados para a entidade informada
	 * 
	 * @param com.veiculo.entity.Veiculo veiculo
	 */
	public void save(Veiculo veiculo){
		veiculoDao.save(veiculo);
	}
	
	/**
	 * Retorna uma lista com todos os registro de veiculos.
	 * 
	 * @return
	 */
	public List<Veiculo> list(){
		return veiculoDao.list();
	}
	
	/**
	 * Busca um registro de veículo que tenha a pk com o mesmo valor do id informado.
	 * Devolve VeiculoException caso o id seja null ou não encontre veiculo para o id informado 
	 * 
	 * @param java.lang.Integer id
	 * @return
	 * @throws com.veiculo.exception.VeiculoException VeiculoException
	 */
	public Veiculo get(Integer id) throws VeiculoException{
		if(id == null){
			throw new VeiculoException("Informado identificar nulo para a requisição");
		}
		Veiculo veiculo =  veiculoDao.get(id);
		
		if(veiculo == null){
			throw new VeiculoException("Não encontrado veiculo com o identificador informado");
		}
		
		return veiculo;
	}
	
	/**
	 * Exclui do banco de dados o registro do veículo recebido como parametro
	 * 
	 * @param com.veiculo.entity.Veiculo veiculo
	 * @throws com.veiculo.exception.VeiculoException VeiculoException
	 */
	public void delete(Veiculo veiculo) throws VeiculoException{
		if(veiculo == null){
			throw new VeiculoException("Informado identificar nulo para a requisição");
		}
		veiculoDao.delete(veiculo);
	}
	
	/**
	 * 
	 * Altera os valores do veículo informado no parametro e faz merge da alteração
	 * 
	 * @param com.veiculo.entity.Veiculo veiculo
	 * @param java.lang.String fabricante
	 * @param java.lang.String ano
	 * @param java.lang.String modelo
	 * @param org.springframework.web.multipart.MultipartFile foto
	 * @throws java.io.IOException IOException 
	 * @throws java.lang.IllegalStateException IllegalStateException
	 */
	public void updateVeiculo(Veiculo veiculo, String fabricante, String ano, String modelo, MultipartFile foto) throws IllegalStateException, IOException{
		veiculo.setFabricante(fabricante);
		veiculo.setAno(ano);
		veiculo.setModelo(modelo);
		if(foto != null && foto.getOriginalFilename().length() > 0){
			veiculo.setFoto(foto.getOriginalFilename());
		}
		
		veiculoDao.merge(veiculo);
	}
}
