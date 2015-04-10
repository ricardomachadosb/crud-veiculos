package com.veiculo.utils;

import java.util.HashMap;

import com.veiculo.enums.RequestStatus;

/**
 * @author ricardo
 *
 */
public class HashMapUtils extends HashMap<String, Object>{

	private static final long serialVersionUID = 1L;
	
	/**
	 * @param message
	 * @param requestStatus
	 */
	public HashMapUtils(String message, RequestStatus requestStatus) {
		this.put("message", message);
		this.put("requestStatus", requestStatus);
	}
	
}
