package com.awethumb.user.service;

import javax.mail.MessagingException;

public interface UserMailServlce {
	void mailSendWithUserKey(String eMail, String userId, String rdmKey) throws MessagingException;
}
