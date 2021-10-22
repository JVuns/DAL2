package application.Misc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Absent implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8559730978185008608L;
	private String ansNo;
	private String absName;
	private String absJob;
	private String absHad;
	private String absOs;
	private String absAlpha;
	private String absIzin;
	private String absIp;
	private String absSakit;
	private String absBreak;
	private String absLibur;
	private String absCuti;
	private String abskarantina;
	private String total;
	private List<LocalDate> absDates;
	private List<String> absDatesVal;
	
	public List<LocalDate> getAbsDates() {
		return absDates;
	}
	public void setAbsDates(List<LocalDate> listOfDates) {
		this.absDates = listOfDates;
	}
	public List<String> getAbsDatesVal() {
		return absDatesVal;
	}
	public String getAbsDateVal(int i) {
		return absDatesVal.get(i);
	}
	public void setAbsDatesVal(List<String> absDatesVal) {
		this.absDatesVal = absDatesVal;
	}
	public String getAnsNo() {
		return ansNo;
	}
	public void setAnsNo(String ansNo) {
		this.ansNo = ansNo;
	}
	public String getAbsName() {
		return absName;
	}
	public void setAbsName(String absName) {
		this.absName = absName;
	}
	public String getAbsJob() {
		return absJob;
	}
	public void setAbsJob(String absJob) {
		this.absJob = absJob;
	}
	public String getAbsHad() {
		return absHad;
	}
	public void setAbsHad(String absHad) {
		this.absHad = absHad;
	}
	public String getAbsOs() {
		return absOs;
	}
	public void setAbsOs(String absOs) {
		this.absOs = absOs;
	}
	public String getAbsAlpha() {
		return absAlpha;
	}
	public void setAbsAlpha(String absAlpha) {
		this.absAlpha = absAlpha;
	}
	public String getAbsIzin() {
		return absIzin;
	}
	public void setAbsIzin(String absIzin) {
		this.absIzin = absIzin;
	}
	public String getAbsIp() {
		return absIp;
	}
	public void setAbsIp(String absIp) {
		this.absIp = absIp;
	}
	public String getAbsSakit() {
		return absSakit;
	}
	public void setAbsSakit(String absSakit) {
		this.absSakit = absSakit;
	}
	public String getAbsBreak() {
		return absBreak;
	}
	public void setAbsBreak(String absBreak) {
		this.absBreak = absBreak;
	}
	public String getAbsLibur() {
		return absLibur;
	}
	public void setAbsLibur(String absLibur) {
		this.absLibur = absLibur;
	}
	public String getAbsCuti() {
		return absCuti;
	}
	public void setAbsCuti(String absCuti) {
		this.absCuti = absCuti;
	}
	public String getAbskarantina() {
		return abskarantina;
	}
	public void setAbskarantina(String abskarantina) {
		this.abskarantina = abskarantina;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Absent(String ansNo, String absName, String absJob, String absHad, String absOs, String absAlpha,
			String absIzin, String absIp, String absSakit, String absBreak, String absLibur, String absCuti,
			String abskarantina, String total) {

		this.ansNo = ansNo;
		this.absName = absName;
		this.absJob = absJob;
		this.absHad = absHad;
		this.absOs = absOs;
		this.absAlpha = absAlpha;
		this.absIzin = absIzin;
		this.absIp = absIp;
		this.absSakit = absSakit;
		this.absBreak = absBreak;
		this.absLibur = absLibur;
		this.absCuti = absCuti;
		this.abskarantina = abskarantina;
		this.total = total;
	}
	
	public void addDates(LocalDate date)
	{
		absDates.add(absDates.size(), date);		
	}
	public int findPos(LocalDate date)
	{
		for(int i = 0; i<absDates.size(); i++)
		{
			if(absDates.get(i) == date)
			{
				return i;
			}
		}
		
		return -1;
	}
	public String getNotation(String string, int num)
	{
		LocalDate date = LocalDate.parse(string);
		String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		for(int i = 0; i<absDatesVal.size(); i++)
		{
			
//			System.out.println(absDatesVal + " " + formattedDate +" "+ (absDatesVal.contains(formattedDate)));
			if(absDatesVal.contains(formattedDate))
			return "HAD";
		}
		return "A";
	}
	public int findPos(String date)
	{
		for(int i = 0; i<absDates.size(); i++)
		{
			if(absDates.get(i).toString() == date)
			{
				return i;
			}
		}
		
		return -1;
	}
	
	public void addDatesVal(int index, String date)
	{
		if(absDatesVal.size() == 0)
		for(int i = 0; i<absDates.size()+1; i++) absDatesVal.add("");
		absDatesVal.set(absDates.size(), date);
	}
}
