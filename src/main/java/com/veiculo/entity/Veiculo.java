package com.veiculo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.internal.NotNull;

/**
 * @author Ricardo
 *
 */
@Entity
@Table(name="veiculo")
public class Veiculo {
	
	@Id
	@Column(name="id")
	private int id;
	
	@NotNull
	@Column(name="fabricante")
	private String fabricante;
	
	@Column(name="modelo")
	private String modelo;
	
	@NotNull
	@Column(name="ano")
	private String ano;
	
	@Column(name="foto")
	private byte[] foto;

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
	public byte[] getFoto() {
		return foto;
	}

	/**
	 * @param foto
	 */
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
}
