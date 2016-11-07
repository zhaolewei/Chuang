package com.jyy.bean;

public class VQuanBean {

	/**
	 * V圈bean
	 */
	private int id; // V圈动态id
	private int user_id; // 发布人id
	private String content; // 内容
	private String imgs_url; // 图片
	private int zan_count; // 点赞数
	private int comment_count; // 评论数

	public VQuanBean() {
		super();
	}

	public VQuanBean(int id, int user_id, String content, String imgs_url, int zan_count, int comment_count) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.content = content;
		this.imgs_url = imgs_url;
		this.zan_count = zan_count;
		this.comment_count = comment_count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgs_url() {
		return imgs_url;
	}

	public void setImgs_url(String imgs_url) {
		this.imgs_url = imgs_url;
	}

	public int getZan_count() {
		return zan_count;
	}

	public void setZan_count(int zan_count) {
		this.zan_count = zan_count;
	}

	public int getComment_count() {
		return comment_count;
	}

	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}

}
