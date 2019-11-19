package com.awethumb.user.service;


import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.awethumb.util.CommUtil;


@Service
public class UserMailServiceImpl implements UserMailServlce {
	
	@Autowired
	private JavaMailSender mailSender;

	
	// 메일 인증 메소드
	@Override
	public String mailSendWithUserKey(String eMail, String userId) throws MessagingException {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    String contextPath = attr.getRequest().getContextPath(); 
		String key = CommUtil.randomKeyByPassword();
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h2>안녕하세요 :p awethumb 입니다!</h2><br><br>" 
				+ "<h3>" + userId + "님</h3>" + "<p>인증하기 버튼을 누르시면 로그인을 하실 수 있습니다 : " 
				+ "<a href='http://203.236.209.150:8000" + contextPath + "/user/regist_finish_user.do?userId="+ eMail +"&userEmailKey="+key+"'>인증하기</a></p>"
				+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";
		mail.setSubject("[본인인증] :p awethumb님의 인증메일입니다", "utf-8");
		mail.setText(htmlStr, "utf-8", "html");
		mail.addRecipient(RecipientType.TO, new InternetAddress(eMail));
		mailSender.send(mail);
		return key;
		
	}
}
