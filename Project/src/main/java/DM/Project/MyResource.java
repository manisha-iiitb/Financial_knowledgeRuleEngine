package DM.Project;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    //Manisha
    @Path("/getcn")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String getcn()
    {
    	databaseConnection dc = new databaseConnection();
		System.out.println(dc.getcompanyname());
		JSONObject jb=new JSONObject();
		try {
			jb.put("cn", dc.getcompanyname());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jb.toString();
    }
    
    //Jyoti
    @Path("/getbn")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String getbn()
    {
    	databaseConnection dc = new databaseConnection();
		System.out.println(dc.getcompanyname());
		JSONObject jb=new JSONObject();
		try {
			jb.put("cn", dc.getbankname());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jb.toString();
    }
    
    //Manisha
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
		Double cps=user_data.getDouble("cps");
		JSONObject result = new JSONObject();
		databaseConnection dc = new databaseConnection();
		//System.out.println(dc.getcompanyname());
		transform t=new transform();
		Stock_scraping sp=new Stock_scraping();
		sp.createCSV();
		String s=dc.getDetails(companyname);
		JSONObject js=new JSONObject(s);
		Double eps=js.getDouble("earning_per_share");
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
  
    //Divya
    @Path("/MaturityAmount")
   	@POST
   	@Produces(MediaType.TEXT_PLAIN)
   	public String getMaturityAmount(String data) throws Exception{
       	
   		JSONObject user_data = new JSONObject(data);
   		String bankname = user_data.getString("bankname");
   		int years=user_data.getInt("years");
   		int amount=user_data.getInt("amount");
   		JSONObject result = new JSONObject();
   		databaseConnection dc = new databaseConnection();
   		transform t=new transform();
   		
   		FixedDeposit_Scraping fp=new FixedDeposit_Scraping();
		fp.createCSV2();
   		
   		String s=dc.getRateOfInterest(bankname);
   		JSONObject js=new JSONObject(s);
   		double roi=js.getDouble("rate_of_interest");
   		xmlCreator xmlc=new xmlCreator();
   		xmlc.getAmount(bankname,roi,amount,years);
   		xmlc.createXML();
   		String st=t.transform2();
   		JSONObject js1=new JSONObject();
   		 js1.put("output", st);
   		 js1.put("result", "success");
   		
   		return js1.toString();
   		
    }
    
    //Divya
    @Path("/getbank")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String getbank()
    {
    	databaseConnection dc = new databaseConnection();
		
		JSONObject jb=new JSONObject();
		try {
			jb.put("bn", dc.getbanknameFD());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jb.toString();
    }
    
    //Jhalak
    @Path("/getGoldInvest")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
    public String getGoldInvest(String data) throws Exception{
    	//System.out.println("k"+data);
    	/*int i = data.indexOf(" ");
    	data = data.substring(i);
    	JSONObject json = new JSONObject(data.trim());*/
		JSONObject user_data = new JSONObject(data);
		String carat = user_data.getString("carat");
		Double grams=user_data.getDouble("grams");
		//Double curr_rate=user_data.getDouble("curr_rate");
		JSONObject result = new JSONObject();
		databaseConnection dc = new databaseConnection();
		transform t=new transform();
		Goldrate_scraping sp=new Goldrate_scraping();
		sp.createCSVGold();
		String c=dc.getGoldDetails(carat);
		JSONObject js=new JSONObject(c);
		Double curr_rate=js.getDouble("curr_rate");
		xmlCreator xmlc=new xmlCreator();
		xmlc.getGoldData(grams,carat,curr_rate);
		xmlc.goldXML();
		String st=t.transform3();
		JSONObject js1=new JSONObject();
		 js1.put("output", st);
		 js1.put("result", "success");
		 System.out.println("ml"+js1);
		return js1.toString();
		
		
	}
    //Jyoti
    @Path("/getEMI")
  	@POST
  	@Produces(MediaType.TEXT_PLAIN)
  	public String geteof(String data) throws Exception{
      	System.out.println("k"+data);
      	/*int i = data.indexOf(" ");
      	data = data.substring(i);
      	JSONObject json = new JSONObject(data.trim());*/
  		JSONObject user_data = new JSONObject(data);
  		String BankName = user_data.getString("BankName");
  		Integer timePeriod=user_data.getInt("timePeriod");
  		Integer principal=user_data.getInt("principal");
  		JSONObject result = new JSONObject();
  		databaseConnection dc = new databaseConnection();
  		HomeloanScrape hl = new HomeloanScrape();
  		hl.ScrapingInterest();
  		transform t=new transform();
  		String s=dc.getBankDetails(BankName);
  		JSONObject js=new JSONObject(s);
  		Double rateofInterest=js.getDouble("rateofInterest");
  		xmlCreater_homeLoan xmlc=new xmlCreater_homeLoan();
  		xmlc.getData(BankName,rateofInterest,timePeriod,principal);
  		xmlc.setHomeLoan();
  		String st=t.transform_HomeLoan();
  		JSONObject js1=new JSONObject();
  		 js1.put("output", st);
  		 js1.put("result", "success");
  		 System.out.println("ml"+js1);
  		return js1.toString();
  		
  	}
  

}
