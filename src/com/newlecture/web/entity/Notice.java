package com.newlecture.web.entity;

import java.util.Date;

public class Notice {
	private int id;
	private String title;
	private String content;
	private String writerId;
	private Date regDate;
	private int hit;
	
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Notice(int id, String title, String content, String witer_id, Date regDate, int hit) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.writerId = witer_id;
		this.regDate = regDate;
		this.hit = hit;
	}

	public Notice(String title, String content, String witer_id) {
		super();
		this.title = title;
		this.content = content;
		this.writerId = witer_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getwriterId() {
		return writerId;
	}

	public void setwriterId(String writerId) {
		this.writerId = writerId;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", content=" + content + ", witer_id=" + writerId
				+ ", regDate=" + regDate + ", hit=" + hit + "]";
	}
	
	
	
}
