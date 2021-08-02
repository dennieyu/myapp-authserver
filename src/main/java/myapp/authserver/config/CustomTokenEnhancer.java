package myapp.authserver.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import lombok.extern.slf4j.Slf4j;
import myapp.authserver.entity.User;

@Slf4j
public class CustomTokenEnhancer extends JwtAccessTokenConverter {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		log.info("################################################################");
		log.info("principal={}", authentication.getPrincipal());
		log.info("authentication={}", authentication.toString());
		log.info("################################################################");

		User user = null;
		if (authentication.getPrincipal() instanceof User) {
			user = (User) authentication.getPrincipal();
		} else {
			user = new User();
			user.setType("ADMIN");
			user.setUsername((String) authentication.getPrincipal());
		}

		Map<String, Object> info = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());

		info.put("type", user.getType()); // add type

		DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
		customAccessToken.setAdditionalInformation(info);

		// return super.enhance(customAccessToken, authentication);
		return rebuild(customAccessToken, authentication);
	}

	private DefaultOAuth2AccessToken rebuild(DefaultOAuth2AccessToken customAccessToken, OAuth2Authentication authentication) {
		customAccessToken.setScope(null); // remove scope

		DefaultOAuth2AccessToken result = (DefaultOAuth2AccessToken) super.enhance(customAccessToken, authentication);
		Map<String, Object> info = result.getAdditionalInformation();
		if (info.containsKey(TOKEN_ID)) {
			info.remove(TOKEN_ID); // remove jti
		}

		result.setAdditionalInformation(info);

		return result;
	}

}
