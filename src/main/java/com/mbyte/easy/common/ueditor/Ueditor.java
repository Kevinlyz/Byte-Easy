package com.mbyte.easy.common.ueditor;
/**
 * ueditor参数实体
 * @author wpw
 *
 */
public class Ueditor {
	private  String state;
	private  String url;
	private  String title;
	private  String original;

	public String getState() {
		return state;
	}

	public Ueditor setState(String state) {
		this.state = state;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public Ueditor setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Ueditor setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getOriginal() {
		return original;
	}

	public Ueditor setOriginal(String original) {
		this.original = original;
		return this;
	}

	@Override
	public String toString() {
		return "Ueditor{" +
				"state='" + state + '\'' +
				", url='" + url + '\'' +
				", title='" + title + '\'' +
				", original='" + original + '\'' +
				'}';
	}
}
