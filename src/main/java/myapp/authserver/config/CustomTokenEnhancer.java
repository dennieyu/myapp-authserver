package myapp.authserver.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import myapp.authserver.entity.User;

public class CustomTokenEnhancer extends JwtAccessTokenConverter {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		User user = (User) authentication.getPrincipal();

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
