package com.in2.technical.challenge.api.exceptions;

public class ApiExceptionResponse {

	private String title;
	private int code;
	private String detail;
	
	public ApiExceptionResponse(String title, int code, String detail) {
        super();
        this.title = title;
        this.code = code;
        this.detail = detail;
    }  
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
}
