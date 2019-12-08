package com.awethumb.repository.dao;

import com.awethumb.repository.vo.Message;

public interface ChatDAO {
	int selectRoom(Message message);
	void insertRoom();
}
