package com.veiculo.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;



/**
 * @author ricardo
 *
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	/**
     * 
     */
	public SecurityInitializer() {
		super(SecurityConfig.class);
	}
	
}
