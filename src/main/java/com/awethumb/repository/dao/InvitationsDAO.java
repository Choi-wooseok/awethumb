package com.awethumb.repository.dao;

import com.awethumb.repository.vo.Invitations;
import com.awethumb.repository.vo.SharedProject;

public interface InvitationsDAO {

	SharedProject selectSharedProject(Invitations inv);
}
