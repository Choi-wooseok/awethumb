package com.awethumb.invitations.service;

import com.awethumb.repository.vo.Invitations;
import com.awethumb.repository.vo.SharedProject;

public interface InvitationsService {

	SharedProject selectSharedProject(Invitations inv);

}
