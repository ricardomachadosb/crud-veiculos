package com.veiculo.dao;

import java.util.List;

import com.veiculo.entity.Veiculo;


/**
 * @author ricardo
 *
 */
public interface VeiculoDao{
	public void save(Veiculo veiculo);
	
	public List<Veiculo> list();
	
	public Veiculo get(Integer id);
	
	public void delete(Veiculo id);
}
