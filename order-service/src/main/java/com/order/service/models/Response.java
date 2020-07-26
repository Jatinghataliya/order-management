package com.order.service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Response<T> {
	private int code;
	private String message;
	private T data;

	@JsonIgnore
	public String getJson() {
		return new Gson().toJson(this);
	}
}
