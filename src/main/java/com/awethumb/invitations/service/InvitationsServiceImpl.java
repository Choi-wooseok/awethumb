package com.awethumb.invitations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.InvitationsDAO;
import com.awethumb.repository.vo.Invitations;
import com.awethumb.repository.vo.SharedProject;

@Service
public class InvitationsServiceImpl implements InvitationsService{

	@Autowired
	private InvitationsDAO dao;
	
	@Override
	public SharedProject selectSharedProject(Invitations inv) {
		return dao.selectSharedProject(inv);
	}

	@Override
	public void updateSharedProject(SharedProject sp) {
		dao.updateSharedProject(sp);
	}

}
