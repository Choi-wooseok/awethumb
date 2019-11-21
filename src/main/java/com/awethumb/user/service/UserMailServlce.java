package com.awethumb.user.service;

import javax.mail.MessagingException;

public interface UserMailServlce {
	String mailSendWithUserKey(String eMail, String userId) throws MessagingException;
}
