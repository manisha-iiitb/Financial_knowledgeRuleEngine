package DM.Project;

public class GeneralFDModel {
	String bankname;
   double rate;
   //double seniorrate;
public String getBankname() {
	return bankname;
}
public void setBankname(String bankname) {
	this.bankname = bankname;
}

public void setRate(double rate) {
	this.rate = rate;
}
public double getRate() {
	return rate;
}

public GeneralFDModel(String bankname, double rate) {
	super();
	this.bankname = bankname;
	this.rate=rate;
}
public GeneralFDModel() {
	super();
}
   

}
