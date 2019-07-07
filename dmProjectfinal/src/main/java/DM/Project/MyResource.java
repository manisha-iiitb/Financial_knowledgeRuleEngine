package DM.Project;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/myresource")
public class MyResource {

	static int income;
	static String category;
	static String employment;
	static int age;
	static double savings;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
	}
	
	@Path("/olddata")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String old_data(String data) throws Exception{
				
		databaseConnection dc = new databaseConnection();
		return dc.get_old_data(data);
	}
	@Path("/register")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String register(String data) throws Exception{
				
		databaseConnection dc = new databaseConnection();
		return dc.setdetails(data);
	}
	
	@Path("/login")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String login(String data) throws Exception{
		JSONObject user_data = new JSONObject(data);
		String email = user_data.getString("email");
		String password = user_data.getString("password");
		System.out.println(email+" "+password);
		databaseConnection dc = new databaseConnection();
		return dc.authenticate(email,password);
	}
	@Path("/updateprofile")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String updatepro(String data) throws Exception{
		/*
		 * JSONObject user_data = new JSONObject(data); String email =
		 * user_data.getString("email"); String password =
		 * user_data.getString("password"); System.out.println(email+" "+password);
		 */
		databaseConnection dc = new databaseConnection();
		return dc.updatedata(data);
	}
	@Path("/show")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String show(String data) throws Exception{
		//Manisha
		JSONObject user_data = new JSONObject(data);
		String cn = user_data.getString("choice");			
		databaseConnection dc = new databaseConnection();
		return dc.getDetails(cn);
	}

	
	
    //MAnisha
    @Path("/getcn")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String getcn()
    {
    	Stock_scraping sp=new Stock_scraping();
		sp.createCSV();
    	databaseConnection dc = new databaseConnection();
		System.out.println(dc.getcompanynam());
		JSONObject jb=new JSONObject();
		try {
			jb.put("cn", dc.getcompanynam());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jb.toString();
    }
    
    @Path("/getEPS")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String getEPS(String data) throws Exception{
    	System.out.println("k"+data);
    	/*int i = data.indexOf(" ");
    	data = data.substring(i);
    	JSONObject json = new JSONObject(data.trim());*/
		JSONObject user_data = new JSONObject(data);
		String companyname = user_data.getString("companyname");
		//Double cps=user_data.getDouble("cps");
		JSONObject result = new JSONObject();
		databaseConnection dc = new databaseConnection();
		//System.out.println(dc.getcompanyname());
		transform t=new transform();
		
		String s=dc.getDetails(companyname);
		JSONObject js=new JSONObject(s);
		Double eps=js.getDouble("earning_per_share");
		Double cps=js.getDouble("current_stock_price");
		xmlCreator xmlc=new xmlCreator();
		xmlc.getData(companyname,cps,eps);
		xmlc.set();
		String st=t.transform1();
		JSONObject js1=new JSONObject();
		 js1.put("output", st);
		 js1.put("result", "success");
		 System.out.println("ml"+js1);
		return js1.toString();
		
	}


	@Path("/buystock")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String buyStock(String data) throws Exception {
		JSONObject user_data = new JSONObject(data);

		Double amount = user_data.getDouble("amount");
		String inv_type = user_data.getString("invest_type");
		// Double curr_rate=user_data.getDouble("curr_rate");
		JSONObject result = new JSONObject();
		databaseConnection dc = new databaseConnection();
		transform t = new transform();
		buystock bs = new buystock();
		bs.createdb();
		JSONObject j1 = dc.getmax();
		JSONObject j2 = dc.getmaxprofit();
		String sa = String.valueOf(amount);
		xmlCreator xmlc = new xmlCreator();
		xmlc.createbuyxml(sa, inv_type, j1, j2);
		String st[] = t.transformbuy();
		JSONObject js1 = new JSONObject();
		js1.put("output", st);
		js1.put("result", "success");
		return js1.toString();

	}

	@Path("/loaddata")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String sellstock() throws Exception {
		JSONObject result = new JSONObject();
		databaseConnection dc = new databaseConnection();
		transform t = new transform();
		buystock bs = new buystock();
		bs.createdb();
		JSONObject jb = new JSONObject();
		try {
			jb.put("cn", dc.getcompanynamesell());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jb.toString();

	}

	@Path("/sellsent")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String setsell(String selldata) {
		JSONArray jsonArr;
		JSONArray jsonArr1;
		databaseConnection dc = new databaseConnection();
		try {
			jsonArr = new JSONArray(selldata);
			jsonArr1 = new JSONArray();

			for (int i = 0; i < jsonArr.length(); i++) {

				JSONObject jsonObj = jsonArr.getJSONObject(i);
				String cname = jsonObj.getString("name");
				String units = jsonObj.getString("units");
				String price = jsonObj.getString("price");
				JSONObject j = dc.getLastPrice(cname);
				jsonObj.put("LastPrice", j.getDouble("LastPrice"));
				jsonObj.put("Change_company", j.getDouble("Change_company"));
				jsonObj.put("Change_percent", j.getDouble("Change_percent"));
				jsonObj.put("profit", j.getDouble("profit"));
				jsonObj.put("liabilities", j.getDouble("liabilities"));
				jsonObj.put("ProfitLiabilityRatio", j.getDouble("ProfitLiabilityRatio"));
				jsonArr1.put(jsonObj);

			}

			xmlCreator xmlcreate = new xmlCreator();
			xmlcreate.createsellxml(jsonArr1);
			transform t = new transform();
			String[] st = t.transformsell();
			JSONObject js1 = new JSONObject();
			js1.put("output", st);
			js1.put("result", "success");
			System.out.println("ml" + js1);
			return js1.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		// return jsonArr1.toString();

	}

	@Path("/sellsent2")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String setsell2(String selldata) {
		JSONArray jsonArr;
		JSONArray jsonArr1;
		String result = "";
		databaseConnection dc = new databaseConnection();
		try {
			jsonArr = new JSONArray(selldata);
			jsonArr1 = new JSONArray();
			Double d = 1000.0;
			String company = "";
			Double LastPrice, Change_company, Change_percent, profit, liabilities, ProfitLiabilityRatio;
			for (int i = 0; i < jsonArr.length(); i++) {

				JSONObject jsonObj = jsonArr.getJSONObject(i);
				String cname = jsonObj.getString("name");
				String units = jsonObj.getString("units");
				String price = jsonObj.getString("price");
				JSONObject j = dc.getLastPrice(cname);

				if (d > j.getDouble("ProfitLiabilityRatio")) {
					d = j.getDouble("ProfitLiabilityRatio");
					company = cname;
					LastPrice = j.getDouble("LastPrice");
					Change_company = j.getDouble("Change_company");
					Change_percent = j.getDouble("Change_percent");
					profit = j.getDouble("profit");
					liabilities = j.getDouble("liabilities");
					ProfitLiabilityRatio = j.getDouble("ProfitLiabilityRatio");
				}
			}
			if (d < 0.2) {
				result = "<b>Highly recommended to sell your stocks of " + company + ".<b>";
			} else {
				result = "<b>If you wish to sell then sell your stocks of " + company + ", "
						+ "otherwise we recommend to hold your stocks.<b>";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}
	
	

	// ********************************************Divya***********************************************
	
	@Path("/setFDDetails")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String setFDDetails(String email) throws Exception {
		System.out.println("Hi");
		databaseConnection dc = new databaseConnection();
		String data=dc.getFDdetails(email);
		JSONObject js = new JSONObject(data);
		income=js.getInt("income");
		category=js.getString("category");
		System.out.println(income);
		
		return data;
		
	}

	
	@Path("/MaturityAmount")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String getMaturityAmount(String data) throws Exception {

		JSONObject user_data = new JSONObject(data);
		String bankname = user_data.getString("bankname");
		int years = user_data.getInt("years");
		int amount = user_data.getInt("amount");
		JSONObject result = new JSONObject();
		databaseConnection dc = new databaseConnection();
		transform t = new transform();

		FixedDeposit_Scraping fp = new FixedDeposit_Scraping();
		fp.createCSV2();

		String s = dc.getRateOfInterest(bankname);
		JSONObject js = new JSONObject(s);
		double roi = js.getDouble("rate_of_interest");
		xmlCreatorfd xmlc = new xmlCreatorfd();
		xmlc.getAmount(bankname, roi, amount, years);
		xmlc.createFDXML();
		String st = t.transform2();
		JSONObject js1 = new JSONObject();
		js1.put("output", st);
		js1.put("result", "success");

		return js1.toString();

	}

	// Divya
	@Path("/getbank")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String getbank() {
		databaseConnection dc = new databaseConnection();

		JSONObject jb = new JSONObject();
		try {
			jb.put("bn", dc.getbanknameFD());
			System.out.print(jb.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jb.toString();
	}

	@Path("/FDAnalysis")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String getFDAnalysis(String data) throws Exception {
		JSONObject user_data = new JSONObject(data);
		//int income = user_data.getInt("income");

		//String category = user_data.getString("category");

		int years = user_data.getInt("years");

		int amount = user_data.getInt("amount");

		JSONObject result = new JSONObject();
		databaseConnection dc = new databaseConnection();
		transform t = new transform();

		FixedDepositAnalysis_Scraping fp = new FixedDepositAnalysis_Scraping();
		fp.readGeneralFDRates();
		
		fp.readTaxSavingFDRates();
		GeneralFDModel max_generalregular = dc.getMaxRegularRate();
		GeneralFDModel max_generalsenior = dc.getMaxSeniorRate();
		GeneralFDModel max_taxsaveregular = dc.getMaxRegularTaxSaving();
		GeneralFDModel max_taxsavesenior = dc.getMaxSeniorTaxSaving();
           
		System.out.println(income);
		System.out.println(category);
		
		
		xmlCreatorfd xmlc = new xmlCreatorfd();
		xmlc.FDDetails(category, years, amount, income, max_generalregular.getBankname(), max_generalregular.getRate(),
				max_generalsenior.getBankname(), max_generalsenior.getRate(), max_taxsaveregular.getBankname(),
				max_taxsaveregular.getRate(), max_taxsavesenior.getBankname(), max_taxsavesenior.getRate());
		xmlc.createFDAXML();
		String st = t.transformFDA();
		JSONObject js1 = new JSONObject();
		js1.put("output", st);
		js1.put("result", "success");

		return js1.toString();

	}

	// *********************************************Jhalak*********************************************
	@Path("/getGoldInvest")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String getGoldInvest(String data) throws Exception {
		System.out.println("enter");
		JSONObject user_data = new JSONObject(data);
		String carat = user_data.getString("carat");
		Double grams = user_data.getDouble("grams");
		// Double curr_rate=user_data.getDouble("curr_rate");
		JSONObject result = new JSONObject();
		databaseConnection dc = new databaseConnection();
		transform t = new transform();
		Goldrate_scraping sp = new Goldrate_scraping();
		sp.createCSVGold();
		String c = dc.getGoldDetails(carat);
		JSONObject js = new JSONObject(c);
		Double curr_rate = js.getDouble("curr_rate");
		xmlCreator xmlc = new xmlCreator();
		xmlc.getGoldData(grams, carat, curr_rate);
		xmlc.goldXML();
		String st = t.transform3();
		JSONObject js1 = new JSONObject();
		js1.put("output", st);
		js1.put("result", "success");
		System.out.println("ml" + js1);
		return js1.toString();

	}

	@Path("/setMFDetails")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String setMFDetails(String email) throws Exception {
		System.out.println("Hi");
		databaseConnection dc = new databaseConnection();
		String data=dc.getMFdetails(email);
		JSONObject js = new JSONObject(data);
		age=js.getInt("age");
		savings=js.getDouble("savings");
		System.out.println(savings);
		
		return data;
		
	}
	@Path("/Mutualfund")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String getMF(String data) throws Exception {
		// System.out.println("k"+data);
		/*
		 * int i = data.indexOf(" "); data = data.substring(i); JSONObject json = new
		 * JSONObject(data.trim());
		 */
		JSONObject user_data = new JSONObject(data);
		String duration = user_data.getString("duration");
		String risklevel = user_data.getString("risk");
		//Integer age = user_data.getInt("age");
		//Double savings = user_data.getDouble("savings");
		JSONObject result = new JSONObject();
		databaseConnection dc = new databaseConnection();
		transform t = new transform();
		MF_scraping sp = new MF_scraping();
		sp.createCSVMF();
		MutualFundModel equity = dc.getEquityMFDetails(duration);
		MutualFundModel debt = dc.getDebtMFDetails(duration);
		MutualFundModel hybrid = dc.getHybridMFDetails(duration);
		Double equity_rate = equity.getRate();
		String equity_fund = equity.getFundName();
		Double debt_rate = debt.getRate();
		String debt_fund = debt.getFundName();
		Double hybrid_rate = hybrid.getRate();
		String hybrid_fund = hybrid.getFundName();
		xmlCreator xmlc = new xmlCreator();
		xmlc.getMFData(risklevel, age, savings, equity_fund, equity_rate, debt_fund, debt_rate, hybrid_fund,
				hybrid_rate);
		xmlc.MutualFundXML();
		String st = t.transformMF();
		JSONObject js1 = new JSONObject();
		js1.put("output", st);
		js1.put("result", "success");
		System.out.println("ml" + js1);
		return js1.toString();

	}

	// *****************************Jyoti**************************************************************

	@Path("/getbn")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String getbn() {
		databaseConnection dc = new databaseConnection();
		// System.out.println(dc.getcompanyname());
		JSONObject jb = new JSONObject();
		try {
			jb.put("cn", dc.getbankname());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jb.toString();
	}

	@Path("/getEMI")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String geteof(String data) throws Exception {
		System.out.println("k" + data);
		/*
		 * int i = data.indexOf(" "); data = data.substring(i); JSONObject json = new
		 * JSONObject(data.trim());
		 */
		JSONObject user_data = new JSONObject(data);
		String BankName = user_data.getString("BankName");
		Integer timePeriod = user_data.getInt("timePeriod");
		Integer principal = user_data.getInt("principal");
		JSONObject result = new JSONObject();
		databaseConnection dc = new databaseConnection();
		HomeloanScrape hl = new HomeloanScrape();
		hl.ScrapingInterest();
		transform t = new transform();
		String s = dc.getBankDetails(BankName);
		JSONObject js = new JSONObject(s);
		Double rateofInterest = js.getDouble("rateofInterest");
		String minInterest = dc.getBankDetailsofMinimumInterest();
		
		JSONObject js2 = new JSONObject(minInterest);
		JSONArray result2 = js2.getJSONArray("ListOfBank");
		/* JSONArray keys = js.names(); */
		String[] banknameLowInterest = new String[result2.length()];
		System.out.println("The length of json Array " + result2.length());
		Double minimum_Interest = 0.0;

		for (int j = 0; j < result2.length(); j++) {
			JSONObject result3 = result2.getJSONObject(j);
			minimum_Interest = result3.getDouble("interestrate");
			banknameLowInterest[j] = result3.getString("bankname");
		}
		
		xmlCreater_homeLoan xmlc = new xmlCreater_homeLoan();
		xmlc.getData(BankName, rateofInterest, timePeriod, principal,banknameLowInterest,minimum_Interest);
		xmlc.setHomeLoan();
		String st = t.transform_HomeLoan();
		JSONObject js1 = new JSONObject();
		js1.put("output", st);
		js1.put("result", "success");
		System.out.println("ml" + js1);
		return js1.toString();

	}

	@Path("/getHomeLoanEligibility")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String getofHomeLoanEligibility(String data) throws Exception {
		System.out.println("Data passed from front end to backend " + data);

		JSONObject user_data = new JSONObject(data);

		//Integer Age = user_data.getInt("Age");
		//Integer MonthlyIncome = user_data.getInt("MonthlyIncome");
		//String Employment = user_data.getString("Employment");
		Integer ITR1 = user_data.getInt("ITR1");
		//String email = user_data.getString("email");
		//System.out.println(email);
		JSONObject result = new JSONObject();
		databaseConnection dc = new databaseConnection();
		HomeloanScrape hl = new HomeloanScrape();
		hl.ScrapingInterest();

		System.out.println("Done Scraping and adding to database");

		transform t = new transform();

		System.out.println("Getting bank details with minimum Interest");

		String s = dc.getBankDetailsofMinimumInterest();
		System.out.println("After getting from database " + s);

		JSONObject js = new JSONObject(s);
		JSONArray result2 = js.getJSONArray("ListOfBank");
		/* JSONArray keys = js.names(); */
		String[] banknameLowInterest = new String[result2.length()];
		System.out.println("The length of json Array " + result2.length());
		Double rateofInterest = 0.0;

		for (int j = 0; j < result2.length(); j++) {
			JSONObject result3 = result2.getJSONObject(j);
			rateofInterest = result3.getDouble("interestrate");
			banknameLowInterest[j] = result3.getString("bankname");
		}

		System.out.println("Minimum interest rate is " + rateofInterest);

		for (int j = 0; j < result2.length(); j++) {
			System.out.println(banknameLowInterest[j]);
		}
		

		xml_HomeLoanEligibility xmlc = new xml_HomeLoanEligibility();
		xmlc.getData(age, rateofInterest, income, employment, ITR1,banknameLowInterest);
		System.out.println("Done getting bank with minimum interest rate");
		xmlc.setHomeLoanEligibility();
		String st = t.transform_HomeLoanEligibility();
		JSONObject js1 = new JSONObject();
		js1.put("output", st);
		js1.put("result", "success");
		System.out.println("ml" + js1);
		return js1.toString();

	}
	
	@Path("/setHomeLoanDetails")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String setHomeLoanDetails(String email) throws Exception {
		System.out.println("Hi");
		databaseConnection dc = new databaseConnection();
		String data=dc.getHomeLoandetails(email);
		JSONObject js = new JSONObject(data);
		employment=js.getString("emp");
		age = js.getInt("age");
		income = js.getInt("income");
		//category=js.getString("category");
		System.out.println(employment);
		
		return data;
		
	}

}
