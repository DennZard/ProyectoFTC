package com.ftc.demo.entities;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ftc.demo.DTOs.UserLoginDTO;

import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
	private LocalDateTime timeStamp;
	private Map<?, ?> data;
	private HttpStatus status;
	
	Response(Builder builder) {
		data = builder.data;
		timeStamp = builder.timeStamp;
		status = builder.status;
		
	}
	
	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		protected LocalDateTime timeStamp;
		protected Map<?, ?> data;
		protected HttpStatus status;
		
	public Builder timeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
		return this;
	}
	
	public Builder data(Map<?, ?> data) {
		this.data = data;
		return this;
	}
	
	public Builder status(HttpStatus status) {
		this.status = status;
		return this;
	}
	
	public Response build() {
		return new Response(this);
	}
	}
	
}
