package com.awethumb.auth;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;

import com.awethumb.repository.vo.UserVO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

public class SnsLogin {
	private OAuth20Service oauthService;
	private SnsValue sns;
	private String scopeType;
	
	public SnsLogin(SnsValue sns) {
		if (sns.isKakao()) this.scopeType = "profile,account_email";
		else this.scopeType = "profile";
		
		this.oauthService = new ServiceBuilder(sns.getClientId())
				.apiSecret(sns.getClientSecret())
				.callback(sns.getRedirectUrl())
				.scope(scopeType)
				.build(sns.getApi20Instance());
		
		this.sns = sns;
	}
	
	public String getAuthURL() {
		return this.oauthService.getAuthorizationUrl();
	}

	public UserVO getUserProfile(String code, String methodType) throws Exception {
		OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
		OAuthRequest req = null;
		if ("kakao".equals(methodType)) req = new OAuthRequest(Verb.POST, this.sns.getProfileUrl());
		else req = new OAuthRequest(Verb.GET, this.sns.getProfileUrl());
		
		oauthService.signRequest(accessToken, req);
		
		Response res = oauthService.execute(req);
		
		return parseJSON(res.getBody());
		
	}
	
	private UserVO parseJSON(String body) throws Exception {
		
		System.out.println("body : " + body);
		UserVO user = new UserVO();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(body);
		if (this.sns.isGoogle()) {
//			String id = rootNode.get("id").asText();
//			String displayName = rootNode.get("displayName").asText();
			JsonNode nameNode = rootNode.findPath("name");
			user.setUserName(nameNode.get("familyName").asText() + nameNode.get("givenName").asText()); 
			Iterator<JsonNode> emailNodes = rootNode.path("emails").elements();
			while (emailNodes.hasNext()) {
				JsonNode emailNode = emailNodes.next();
				String type = emailNode.get("type").asText();
				if ("account".equals(type)) {
					user.setUserId((emailNode.get("value").asText()));
					break;
				}
			}
		} else if (this.sns.isNaver()) {
			JsonNode resNode = rootNode.findPath("response");
			if (resNode.get("email") == null || resNode.get("name") == null) {
				return null;
			}
			user.setUserPass(resNode.get("id").asText());
			user.setUserId(resNode.get("email").asText());
			user.setOauthType("naver");
			user.setUserName(URLDecoder.decode(resNode.get("name").asText(), "UTF-8"));
			
		} else if (this.sns.isKakao()) {
			JsonNode resNode = rootNode.get("kakao_account");
			String hasEmail = resNode.get("has_email").asText();
			if (resNode.get("email") == null || "false".equals(hasEmail)) {
				return null;
			}
			user.setUserPass(rootNode.get("id").asText());
			user.setUserId(resNode.get("email").asText() + "forKakao");
			user.setOauthType("kakao");
		}
		
		
		return user;
	}
	
	
}
