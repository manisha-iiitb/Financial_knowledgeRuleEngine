package DM.Project;

public class MutualFundModel {
	String fundname;
   double rate;
 
public String getFundName() {
	return fundname;
}
public void setFundName(String fundname) {
	this.fundname = fundname;
}

public void setRate(double rate) {
	this.rate = rate;
}
public double getRate() {
	return rate;
}

public MutualFundModel(String fundname, double rate) {
	super();
	this.fundname = fundname;
	this.rate=rate;
}
public MutualFundModel() {
	super();
}

}