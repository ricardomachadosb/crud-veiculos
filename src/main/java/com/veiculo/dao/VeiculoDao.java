package com.veiculo.dao;

import java.util.List;

import com.veiculo.entity.Veiculo;


/**
 * @author ricardo
 *
 */
public interface VeiculoDao{
	
	/**
	 * @param veiculo
	 */
	public void save(Veiculo veiculo);
	
	/**
	 * @return
	 */
	public List<Veiculo> list();
	
	/**
	 * @param id
	 * @return
	 */
	public Veiculo get(Integer id);
	
	/**
	 * @param id
	 */
	public void delete(Veiculo id);
	
	/**
	 * @param veiculo
	 */
	public void merge(Veiculo veiculo);
}
