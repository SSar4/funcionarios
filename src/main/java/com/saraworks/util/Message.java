package com.saraworks.util;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@RequestScoped
public class Message {
	public void addMessage(String massagr) {
		FacesMessage mensagem = new FacesMessage(massagr);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}
}