package com.awethumb.util;

import com.awethumb.repository.vo.Message;
import com.google.gson.Gson;

public class ChatUtil {
	
	public static Message convertMessage(String source) {
		Message message = new Message();
		Gson gson = new Gson();
		message = gson.fromJson(source,  Message.class);
		return message;
	}
	
}
