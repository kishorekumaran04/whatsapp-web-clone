package com.kishore.whatsapp.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetail {
	
	private String error;
	private String message;
	private LocalDateTime timeStamp;
	
	public ErrorDetail(String error, String message, LocalDateTime timeStamp) {
		super();
		this.error = error;
		this.message = message;
		this.timeStamp = timeStamp;
	}
}
