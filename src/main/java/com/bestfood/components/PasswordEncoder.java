package com.bestfood.components;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * <p>Sha Password Encoder bean</p>
 * <p>Use for decoding password from db</p>
 *
 * @author Dmitry Syaskiy
 * @see    ShaPasswordEncoder
 */

@Component("passwordEncoder")
public class PasswordEncoder extends ShaPasswordEncoder {

    /**
     * Check if password match
     * @param encPass encrypted Password
     * @param rawPass String param
     * @param salt  salt for encryption
     * @return result of validation
     */
    @Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		return super.isPasswordValid(encPass, rawPass, salt);
	}
}
