package com.aotuspace.aotucms.web.model;

/**
 * 
 * Title:JosonResult
 * Description:Json������Ϣ��
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-24 ����6:16:52
 *
 */
public class JsonResult {
	private int code;	//����
	private String msg;	//��Ϣ
	private Object data;//����
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}