package com.badmintonbuddy.models;

import java.io.Serializable;
import java.util.List;


public class MyMessagesResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean success;
	private List<Messages> messages;


	public List<Messages> getMessages() {
		return messages;
	}

	public void setMessages(List<Messages> messages) {
		this.messages = messages;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}



	public class Messages implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Messages(String message, String sender) {
			super();
			this.message = message;
			this.sender = sender;
		}
		private String message;

		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getSender() {
			return sender;
		}
		public void setSender(String sender) {
			this.sender = sender;
		}
		private String sender;


	}

}
