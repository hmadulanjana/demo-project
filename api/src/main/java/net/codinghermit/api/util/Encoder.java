package net.codinghermit.api.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encoder{

	public static String encodeEncryptUserPassword(String password)
	{
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(password);
			return encodedPassword;
	}
}
