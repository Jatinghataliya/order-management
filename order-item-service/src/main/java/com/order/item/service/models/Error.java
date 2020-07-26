package com.order.item.service.models;

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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Error<T> {

	private int code;
	private String message;
	T data;
	
	@JsonIgnore
	public String getJson() {
		return new Gson().toJson(this);
	}
}
