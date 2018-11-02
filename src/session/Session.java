package session;

import java.util.Date;

import namingService.INamingService;

public abstract class Session {
	
	protected String sessionID;
	protected Date creationDate;
	protected INamingService namingService;
	
	/***************
	 * CONSTRUCTOR *
	 ***************/
	public Session(INamingService ns, String sID) {
		this.namingService = ns;
		this.sessionID = sID;
		this.creationDate = new Date();
	}
	
	/***********
	 * GETTERS *
	 ***********/
	public INamingService getNamingService() {
		return this.namingService;
	}
	
	public String getSessionID() {
		return this.sessionID;
	}
	
	public Date getDate() {
		return this.creationDate;
	}

}
