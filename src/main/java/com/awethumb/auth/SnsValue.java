package com.awethumb.auth;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.api.DefaultApi20;

import lombok.Data;

@Data
public class SnsValue implements SnsUrls {
	private String service;
	private String clientId;
	private String clientSecret;
	private String redirectUrl;
	private DefaultApi20 api20Instance;
	private String profileUrl;
	
	private boolean isNaver;
	private boolean isGoogle;
	private boolean isKakao;
	
	// 생성자 - 기본생성자 생성 방지
	public SnsValue(String service, String clientId, String clientSecret, String redirectUrl) {
		this.service = service;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.redirectUrl = redirectUrl;
		this.isNaver = "naver".equals(service);
		this.isGoogle = "google".equals(service);
		this.isKakao = "kakao".equals(service);
		
		if (isNaver) {
			this.api20Instance = NaverAPI20.instance();
			this.profileUrl = NAVER_PROFILE_URL;
		} else if (isGoogle) {
			this.api20Instance = GoogleApi20.instance();
			this.profileUrl = GOOGLE_PROFILE_URL;
		} else if (isKakao) {
			this.api20Instance = KakaoAPI20.instance(this.clientId, this.clientSecret, this.redirectUrl);
			this.profileUrl = KAKAO_PROFILE_URL;
		}
	}
}
