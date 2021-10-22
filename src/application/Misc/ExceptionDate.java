package application.Misc;

import java.time.LocalDate;

public class ExceptionDate implements java.io.Serializable{
	/**
	 * 
	 */
	private LocalDate date;
	private String shift;
	private String desc;
	private String code;
	
	private static final long serialVersionUID = 012L;
	public ExceptionDate(LocalDate date, String shift, String desc, String code) {
		this.date = date;
		this.shift = shift;
		this.desc = desc;
		this.code = code;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDate getDate() {
		return date;
	}
	public String getShift() {
		return shift;
	}
	public String getDesc() {
		return desc;
	}
	public String getCode() {
		return code;
	}
	
}
