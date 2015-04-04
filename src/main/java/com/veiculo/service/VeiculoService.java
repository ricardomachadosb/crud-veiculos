package com.veiculo.service;

import org.springframework.stereotype.Service;

import com.veiculo.dao.impl.VeiculoDaoImpl;
import com.veiculo.entity.Veiculo;

/**
 * @author ricardo
 *
 */
@Service
public class VeiculoService {
	VeiculoDaoImpl veiculoImpl = new VeiculoDaoImpl();
	
	public void save(Veiculo veiculo){
		veiculoImpl.save(veiculo);
	}
}
