package com.spring.board.vo;

import java.sql.Timestamp;

public class CommentVO {
	private int comment_num;
	private String comment_writer;
	private String comment_content;
	private Timestamp comment_date;
	private int board_num;
	private int comment_ref;

	public int getComment_num() {
		return comment_num;
	}

	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}

	public String getComment_writer() {
		return comment_writer;
	}

	public void setComment_wrtier(String comment_writer) {
		this.comment_writer = comment_writer;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public Timestamp getComment_date() {
		return comment_date;
	}

	public void setComment_date(Timestamp comment_date) {
		this.comment_date = comment_date;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public int getComment_ref() {
		return comment_ref;
	}

	public void setComment_ref(int comment_ref) {
		this.comment_ref = comment_ref;
	}

	public int getComment_level() {
		return comment_level;
	}

	public void setComment_level(int comment_level) {
		this.comment_level = comment_level;
	}

	public int getComment_length() {
		return comment_length;
	}

	public void setComment_length(int comment_length) {
		this.comment_length = comment_length;
	}

	private int comment_level;
	private int comment_length;
}
