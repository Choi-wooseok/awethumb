package com.awethumb.auth;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class KakaoAPI20 extends DefaultApi20 implements SnsUrls {
	private String clientId;
	private String clientSecret;
	private String redirectUrl;
	
	private KakaoAPI20(String clientId, String clientSecret, String redirectUrl) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.redirectUrl = redirectUrl;
	}
	
	private static class InstanceHolder {
		private static final KakaoAPI20 INSTANCE(String clientId, String ClientSecret, String redirectUrl) { 
			return new KakaoAPI20(clientId, ClientSecret, redirectUrl);
		};
	}
	
	public static KakaoAPI20 instance(String clientId, String ClientSecret, String redirectUrl) {
		return InstanceHolder.INSTANCE(clientId, ClientSecret, redirectUrl);
	}
	
	@Override
	public String getAccessTokenEndpoint() {
		// TODO Auto-generated method stub
		return KAKAO_ACCESS_TOKEN + "&client_id=" + this.clientId + "&redirect_url=" + this.redirectUrl + "&client_secret=" + this.clientSecret;
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		return KAKAO_AUTH;
	}
	
}
