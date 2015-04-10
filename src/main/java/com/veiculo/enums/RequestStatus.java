package com.veiculo.enums;

/**
 * @author ricardo
 *
 */
public enum RequestStatus {
	SUCCESS(200, "success"), 
	ERROR(400, "danger");
	
	private Integer statusCode;
	
	private String classe;
	
	/**
	 * @param codigo
	 * @param classe
	 */
	private RequestStatus(Integer statusCode, String classe){
		this.statusCode = statusCode;
		this.classe = classe;
	}
	
	/**
	 * @return
	 */
	public String getClasse(){
		return this.classe;
	}
	
	/**
	 * @return
	 */
	public Integer getStatusCode(){
		return this.statusCode;
	}
}
