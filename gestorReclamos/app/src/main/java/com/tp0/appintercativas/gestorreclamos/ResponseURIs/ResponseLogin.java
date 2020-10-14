package com.tp0.appintercativas.gestorreclamos.ResponseURIs;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseLogin implements Serializable {

	private long nroUser;

	public long getNroUser() {
		return nroUser;
	}

	public void setNroUser(long nroUser) {
		this.nroUser = nroUser;
	}

	public ResponseLogin(long loginExitosoId) {
		super();
		this.nroUser = loginExitosoId;
	}

	public ResponseLogin() {
		super();
	}
	
	
}
