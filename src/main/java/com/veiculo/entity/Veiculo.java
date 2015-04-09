package com.veiculo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Ricardo
 *
 */
@Entity
@Table(name="veiculo")
public class Veiculo {
	
	/**
	 * 
	 */
	public Veiculo(){}
	
	/**
	 * @param ano
	 * @param fabricante
	 * @param modelo
	 * @param foto
	 */
	public Veiculo(String ano, String fabricante, String modelo, String foto){
		this.setAno(ano);
		this.setFabricante(fabricante);
		this.setModelo(modelo);
		this.setFoto(foto);
	}
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="fabricante")
	private String fabricante;
	
	@Column(name="modelo")
	private String modelo;
	
	@Column(name="ano")
	private String ano;
	
	@Column(name="foto")
	private String foto;

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return
	 */
	public String getFabricante() {
		return fabricante;
	}

	/**
	 * @param fabricante
	 */
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	/**
	 * @return
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * @param modelo
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return
	 */
	public String getAno() {
		return ano;
	}

	/**
	 * @param ano
	 */
	public void setAno(String ano) {
		this.ano = ano;
	}

	/**
	 * @return
	 */
	public String getFoto() {
		return foto;
	}

	/**
	 * @param foto
	 */
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
}
