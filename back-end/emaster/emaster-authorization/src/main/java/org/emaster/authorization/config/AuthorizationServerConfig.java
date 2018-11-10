package org.emaster.authorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	static final String CLIEN_ID = "emaster-client";
	static final String CLIENT_SECRET = "$2a$04$KUIrME9KR9mXV2W6eYlKQOZ./XUNp0foclwdX5pLT37oEiZVZop2C";
	static final String GRANT_TYPE_PASSWORD = "password";
	static final String AUTHORIZATION_CODE = "authorization_code";
	static final String CLIENT_CREDENTIALS = "client_credentials";
	static final String REFRESH_TOKEN = "refresh_token";
	static final String IMPLICIT = "implicit";
	static final String SCOPE_READ = "read";
	static final String SCOPE_WRITE = "write";
	static final String TRUST = "trust";
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60 * 60;
	static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

		configurer.inMemory().withClient(CLIEN_ID).secret(CLIENT_SECRET)
				.authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT,
						CLIENT_CREDENTIALS)
				.scopes(SCOPE_READ, SCOPE_WRITE, TRUST).accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
				.refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager)
				.accessTokenConverter(accessTokenConverter());
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
//	For store token to redis
//	@Bean
//	public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
//		return new RedisTokenStore(redisConnectionFactory);
//	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		EmasterTokenConverter converter = new EmasterTokenConverter();
		converter.setSigningKey("btit95");
		return converter;
	}
}
