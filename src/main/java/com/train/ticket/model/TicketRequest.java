package com.train.ticket.model;

public class TicketRequest {
	
	private String from;
	private String to;
	private User user;
	private Boolean pricePaid;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Boolean getPricePaid() {
		return pricePaid;
	}
	public void setPricePaid(Boolean pricePaid) {
		this.pricePaid = pricePaid;
	}
	
	
}
