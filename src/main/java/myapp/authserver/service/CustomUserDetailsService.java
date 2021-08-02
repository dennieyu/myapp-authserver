package myapp.authserver.service;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import myapp.authserver.entity.User;
import myapp.authserver.repository.UserRepository;

@Slf4j
@Service(value = "userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String input) {
		User user = userRepository.findByUsername(input);
		log.info("################################################################");
		log.info("username={}", input);
		log.info("################################################################");

		if (user == null) {
			throw new BadCredentialsException("Bad credentials");
		} else {
			log.info(ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE));
		}

		new AccountStatusUserDetailsChecker().check(user);

		log.info("AccountStatusUserDetailsChecker.check");

		return user;
	}

}
