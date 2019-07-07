package DM.Project;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class xmlCreator {
	static String bankname;

	static String cn;
	static Double cps;
	static Double eps;
	static Double grams;
	static String carat;
	static Double curr_rate;
	static String risklevel;
	static Integer age;
	static Double savings;
	static String equity_fund;
	static Double equity_rate;
	static String debt_fund;
	static Double debt_rate;
	static String hybrid_fund;
	static Double hybrid_rate;

	public void getMFData(String risklevel, Integer age, Double savings, String equity_fund, Double equity_rate,
			String debt_fund, Double debt_rate, String hybrid_fund, Double hybrid_rate) {
		this.risklevel = risklevel;
		this.age = age;
		this.savings = savings;
		this.equity_fund = equity_fund;
		this.equity_rate = equity_rate;
		this.debt_fund = debt_fund;
		this.debt_rate = debt_rate;
		this.hybrid_fund = hybrid_fund;
		this.hybrid_rate = hybrid_rate;
	}

	public void getGoldData(Double gms, String ct, Double rate) {
		grams = gms;
		carat = ct;
		curr_rate = rate;
	}

	public void getData(String companyname, Double cps, Double eps) {
		cn = companyname;
		this.cps = cps;
		this.eps = eps;
	}

	public static void set() {

		try {

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			// root element
			Element root = document.createElement("stock");
			document.appendChild(root);

			// employee element
			Element company = document.createElement("company_name");
			company.appendChild(document.createTextNode(cn));
			root.appendChild(company);


			// firstname element
			Element EPS = document.createElement("EPS");
			EPS.appendChild(document.createTextNode(eps.toString()));
			root.appendChild(EPS);

			Element CPS = document.createElement("Current_Stock_Price");
			CPS.appendChild(document.createTextNode(cps.toString()));
			root.appendChild(CPS);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File("/home/divya/Downloads/DM project/STOCK.xml"));


			transformer.transform(domSource, streamResult);

			System.out.println("Done creating XML File");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public static void setCompany(JSONArray companies) {

		try {

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			// root element
			Element root = document.createElement("stock");
			document.appendChild(root);

			for (int i = 0; i < companies.length(); i++) {
				Element company1 = document.createElement("company");
				// company1.appendChild(document.createTextNode(company1));
				root.appendChild(company1);
				JSONObject company = companies.getJSONObject(i);
				String companyn = company.getString("companyname");
				String profit1 = company.getString("profit");
				String liabilities1 = company.getString("liabilities");
				Element companyname = document.createElement("company_name");
				companyname.appendChild(document.createTextNode(companyn));
				company1.appendChild(companyname);
				Element profit = document.createElement("profit");
				profit.appendChild(document.createTextNode(profit1));
				company1.appendChild(profit);
				Element liabilities = document.createElement("liabilities");
				liabilities.appendChild(document.createTextNode(liabilities1));
				company1.appendChild(liabilities);

			}
			// create the xml file
			// transform the DOM Object to an XML File
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(
					new File("/home/divya/Downloads/DM project/STOCK_Company.xml"));

			transformer.transform(domSource, streamResult);

			System.out.println("Done creating XML File");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void createbuyxml(String amount, String inv_type, JSONObject conserv, JSONObject aggr) {

		try {

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			// root element
			Element root = document.createElement("Buy");
			document.appendChild(root);
			Element root1 = document.createElement("User");
			root.appendChild(root1);
			Element amt = document.createElement("Amount");
			amt.appendChild(document.createTextNode(amount));
			root1.appendChild(amt);
			Element type = document.createElement("InvestmentType");
			type.appendChild(document.createTextNode(inv_type));
			root1.appendChild(type);

			Element root2 = document.createElement("Conservative");
			root.appendChild(root2);
			String companyn = conserv.getString("companyname");
			String lastprice = conserv.getString("LastPrice");
			String Change_company = conserv.getString("Change_company");
			String Change_percent = conserv.getString("Change_percent");
			String profit1 = conserv.getString("profit");
			String liabilities1 = conserv.getString("liabilities");
			String ProfitLiabilityRatio = conserv.getString("ProfitLiabilityRatio");

			Element companyname = document.createElement("CompanyName");
			companyname.appendChild(document.createTextNode(companyn));
			root2.appendChild(companyname);
			Element LastPrice = document.createElement("LastPrice");
			LastPrice.appendChild(document.createTextNode(lastprice));
			root2.appendChild(LastPrice);
			Element Change_compan = document.createElement("ChangeInCompany");
			Change_compan.appendChild(document.createTextNode(Change_company));
			root2.appendChild(Change_compan);
			Element Change_percen = document.createElement("ChangePercent");
			Change_percen.appendChild(document.createTextNode(Change_percent));
			root2.appendChild(Change_percen);
			Element profit = document.createElement("Profit");
			profit.appendChild(document.createTextNode(profit1));
			root2.appendChild(profit);
			Element liabilities = document.createElement("Liability");
			liabilities.appendChild(document.createTextNode(liabilities1));
			root2.appendChild(liabilities);
			Element ProfitLiabilityRati = document.createElement("ProfitLiabilityRatio");
			ProfitLiabilityRati.appendChild(document.createTextNode(ProfitLiabilityRatio));
			root2.appendChild(ProfitLiabilityRati);

			Element root3 = document.createElement("Aggressive");
			root.appendChild(root3);
			companyn = aggr.getString("companyname");
			lastprice = aggr.getString("LastPrice");
			Change_company = aggr.getString("Change_company");
			Change_percent = aggr.getString("Change_percent");
			profit1 = aggr.getString("profit");
			liabilities1 = aggr.getString("liabilities");
			ProfitLiabilityRatio = aggr.getString("ProfitLiabilityRatio");

			companyname = document.createElement("CompanyName");
			companyname.appendChild(document.createTextNode(companyn));
			root3.appendChild(companyname);
			LastPrice = document.createElement("LastPrice");
			LastPrice.appendChild(document.createTextNode(lastprice));
			root3.appendChild(LastPrice);
			Change_compan = document.createElement("ChangeInCompany");
			Change_compan.appendChild(document.createTextNode(Change_company));
			root3.appendChild(Change_compan);
			Change_percen = document.createElement("ChangePercent");
			Change_percen.appendChild(document.createTextNode(Change_percent));
			root3.appendChild(Change_percen);
			profit = document.createElement("Profit");
			profit.appendChild(document.createTextNode(profit1));
			root3.appendChild(profit);
			liabilities = document.createElement("Liability");
			liabilities.appendChild(document.createTextNode(liabilities1));
			root3.appendChild(liabilities);
			ProfitLiabilityRati = document.createElement("ProfitLiabilityRatio");
			ProfitLiabilityRati.appendChild(document.createTextNode(ProfitLiabilityRatio));
			root3.appendChild(ProfitLiabilityRati);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File("/home/divya/Downloads/DM project/stock_buy.xml"));

			transformer.transform(domSource, streamResult);

			System.out.println("Done creating XML File");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (JSONException e) {

			e.printStackTrace();
		}
	}

	public static void createsellxml(JSONArray jsonArr) {
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();
			Element root = document.createElement("SellStock");
			document.appendChild(root);

			for (int i = 0; i < jsonArr.length(); i++) {
				Element company = document.createElement("Company");
				root.appendChild(company);

				JSONObject jsonObj = jsonArr.getJSONObject(i);
				String cname = jsonObj.getString("name");
				String units = jsonObj.getString("units");
				String price = jsonObj.getString("price");
				String lastprice = jsonObj.getString("LastPrice");
				String Change_company = jsonObj.getString("Change_company");
				String Change_percent = jsonObj.getString("Change_percent");
				String profit1 = jsonObj.getString("profit");
				String liabilities1 = jsonObj.getString("liabilities");
				String ProfitLiabilityRatio = jsonObj.getString("ProfitLiabilityRatio");

				Element companyname = document.createElement("cname");
				companyname.appendChild(document.createTextNode(cname));
				company.appendChild(companyname);

				Element uni = document.createElement("units");
				uni.appendChild(document.createTextNode(units));
				company.appendChild(uni);

				Element Price = document.createElement("Price");
				Price.appendChild(document.createTextNode(price));
				company.appendChild(Price);

				Element LastPrice = document.createElement("LastPrice");
				LastPrice.appendChild(document.createTextNode(lastprice));
				company.appendChild(LastPrice);

				Element Change_compan = document.createElement("ChangeInCompany");
				Change_compan.appendChild(document.createTextNode(Change_company));
				company.appendChild(Change_compan);
				Element Change_percen = document.createElement("ChangePercent");
				Change_percen.appendChild(document.createTextNode(Change_percent));
				company.appendChild(Change_percen);
				Element profit = document.createElement("Profit");
				profit.appendChild(document.createTextNode(profit1));
				company.appendChild(profit);
				Element liabilities = document.createElement("Liability");
				liabilities.appendChild(document.createTextNode(liabilities1));
				company.appendChild(liabilities);
				Element ProfitLiabilityRati = document.createElement("ProfitLiabilityRatio");
				ProfitLiabilityRati.appendChild(document.createTextNode(ProfitLiabilityRatio));
				company.appendChild(ProfitLiabilityRati);

			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File("/home/divya/Downloads/DM project/stock_sell.xml"));

			transformer.transform(domSource, streamResult);

			System.out.println("Done creating XML File");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

// ************************************** Jhalak***************************************************
	public static void goldXML() {
		try {

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			// root element
			Element root = document.createElement("Gold_investment");
			document.appendChild(root);

			// employee element
			Element carats = document.createElement("carat");
			carats.appendChild(document.createTextNode(carat.toString()));
			root.appendChild(carats);

			Element current_rate = document.createElement("current_rate");
			current_rate.appendChild(document.createTextNode(curr_rate.toString()));
			root.appendChild(current_rate);

			Element gram = document.createElement("grams");
			gram.appendChild(document.createTextNode(grams.toString()));
			root.appendChild(gram);

			// create the xml file
			// transform the DOM Object to an XML File
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File("/home/divya/Downloads/DM project/gold.xml"));

			transformer.transform(domSource, streamResult);

			System.out.println("Done creating XML File");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public static void MutualFundXML() {
		try {

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			// root element
			Element root = document.createElement("MutualFund");
			document.appendChild(root);

			// user details element
			Element userdetails = document.createElement("UserDetails");
			root.appendChild(userdetails);

			Element a = document.createElement("age");
			a.appendChild(document.createTextNode(age.toString()));
			userdetails.appendChild(a);
			Element saving = document.createElement("savings");
			saving.appendChild(document.createTextNode(savings.toString()));
			userdetails.appendChild(saving);
			Element risk = document.createElement("risk");
			risk.appendChild(document.createTextNode(risklevel.toString()));
			userdetails.appendChild(risk);
			// set an attribute to staff element
			/*
			 * Attr attr = document.createAttribute("id"); attr.setValue("10");
			 * employee.setAttributeNode(attr);
			 */

			// you can also use staff.setAttribute("id", "1") for this

			Element equity = document.createElement("Equity");
			root.appendChild(equity);

			Element efund = document.createElement("fundname");
			efund.appendChild(document.createTextNode(equity_fund.toString()));
			equity.appendChild(efund);
			Element erate = document.createElement("rate");
			erate.appendChild(document.createTextNode(equity_rate.toString()));
			equity.appendChild(erate);

			Element debt = document.createElement("Debt");
			root.appendChild(debt);

			Element dfund = document.createElement("fundname");
			dfund.appendChild(document.createTextNode(debt_fund.toString()));
			debt.appendChild(dfund);
			Element drate = document.createElement("rate");
			drate.appendChild(document.createTextNode(debt_rate.toString()));
			debt.appendChild(drate);
			Element hybrid = document.createElement("Hybrid");
			root.appendChild(hybrid);

			Element hfund = document.createElement("fundname");
			hfund.appendChild(document.createTextNode(hybrid_fund.toString()));
			hybrid.appendChild(hfund);
			Element hrate = document.createElement("rate");
			hrate.appendChild(document.createTextNode(hybrid_rate.toString()));
			hybrid.appendChild(hrate);
			// create the xml file
			// transform the DOM Object to an XML File
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File("/home/divya/Downloads/DM project/mutualfund.xml"));

			// If you use
			// StreamResult result = new StreamResult(System.out);
			// the output will be pushed to the standard output ...
			// You can use that for debugging

			transformer.transform(domSource, streamResult);

			System.out.println("Done creating XML File");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
}