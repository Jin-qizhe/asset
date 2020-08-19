package com.ydt.util;


public class SysResult {
	//表示状态码的数字
	private Integer code;
	//携带详细信息的字符串
	private String msg;
	//count
	private Integer count;
	//携带的各种数据
	private Object data;
	//准备一个封装数据的构造方法


	public SysResult(Integer code, String msg, Integer count, Object data) {
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}

	public SysResult() {
		// TODO Auto-generated constructor stub
	}
	//有可能返回的只有status,msg,没有data
	//编写静态方法,获取具有参数数据的SysResult,失败做准备
	public static SysResult build(Integer code
			,String msg,Integer count,Object data){
		return new SysResult(code, msg, count,data);
	}//绝大部分时候不会出现失败

	public static SysResult ok(){
		return new SysResult(0, "true", 1000,null);
	}
	public static SysResult er(){
		return new SysResult(1, "false", 0,null);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
