package com.spring.rest.model;

public class BaseResponse {
	 private String responseStatus;
	 private Integer responseCode;
	 
	 public String getResponseStatus() {
	  return responseStatus;
	 }
	 public void setResponseStatus(String responseStatus) {
	  this.responseStatus = responseStatus;
	 }
	 public Integer getResponseCode() {
	  return responseCode;
	 }
	 public void setResponseCode(Integer responseCode) {
	  this.responseCode = responseCode;
	 }
	}