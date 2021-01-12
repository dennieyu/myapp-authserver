package myapp.authserver.config;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (encodedPassword.equals(rawPassword)) {
			return true;
		} else {
			log.warn("raw={}, encoded={}", rawPassword, encodedPassword);
		}
		return false;
	}

}
