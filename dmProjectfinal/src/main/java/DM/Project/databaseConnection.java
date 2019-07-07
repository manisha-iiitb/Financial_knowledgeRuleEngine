package DM.Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class databaseConnection {

	Statement statement;
	ResultSet resultSet;
	static Connection connection = null;
	String query = null;

	// Constructor for opening the Database Connection
	public databaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Found");
		}

		catch (ClassNotFoundException e) {
			System.out.println("Driver Not Found: " + e);
		}

		String url = "jdbc:mysql://localhost/finance?verifyServerCertificate=false&useSSL=true";

		String user = "gargdiv";
		String password = "root";
		connection = null;

		try {
			connection = (Connection) DriverManager.getConnection(url, user, password);
			System.out.println("Successfully Connected to Database");
		} catch (SQLException e) {
			System.out.println("SQL Exception: " + e);
		}
	}
	public String updatedata(String data) 
	{
		java.sql.PreparedStatement preparedStatement = null;
		try {
			JSONObject user_data = new JSONObject(data);
			int age = user_data.getInt("age");
			String email = user_data.getString("email");
			int income = user_data.getInt("income");
			String citizen =user_data.getString("seniorcitizen");
			String employment = user_data.getString("Employment");
			Double saving = user_data.getDouble("saving");
		query = "update  user set age=?, income=?, citizen_category=?, employee_type=?, savings=? where email=?";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1,age );
		preparedStatement.setInt(2, income);
		preparedStatement.setString(3, citizen);
		preparedStatement.setString(4, employment);
		preparedStatement.setDouble(5, saving);
		preparedStatement.setString(6, email);

		preparedStatement.executeUpdate();
		return "success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "fail";
	}
	public String get_old_data(String email)
	{
		JSONObject user=new JSONObject();
		java.sql.PreparedStatement preparedStatement = null;
		try {
			query = "select * from user where email=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				user.put("name", resultSet.getString("name"));
				user.put("age", resultSet.getInt("age"));
				user.put("email", resultSet.getString("email"));
				user.put("income", resultSet.getInt("income"));
				user.put("saving", resultSet.getFloat("savings"));
				user.put("citizen", resultSet.getString("citizen_category"));
				user.put("e_type", resultSet.getString("employee_type"));
				return user.toString();
			}
			else {
				return "fail";
				}
			}
			catch (SQLException | JSONException e) {
				e.printStackTrace();
			}
		return "exception";	
	}
	public String setdetails(String data) 
	{
		java.sql.PreparedStatement preparedStatement = null;
		try {
			JSONObject user_data = new JSONObject(data);
			String name = user_data.getString("name");
			int age = user_data.getInt("age");
			String email = user_data.getString("email");
			int income = user_data.getInt("income");
			String citizen =user_data.getString("seniorcitizen");
			String employment = user_data.getString("Employment");
			String password = user_data.getString("password");
			Double saving = user_data.getDouble("saving");
		query = "insert into user(name, age, email, income, citizen_category, employee_type, savings, password) values(?,?,?,?,?,?,?,?)";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, name);
		preparedStatement.setInt(2,age );
		preparedStatement.setString(3, email);
		preparedStatement.setInt(4, income);
		preparedStatement.setString(5, citizen);
		preparedStatement.setString(6, employment);
		preparedStatement.setDouble(7, saving);
		preparedStatement.setString(8, password);
		preparedStatement.execute();
		return "success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "fail";
	}
	
	public String authenticate(String email, String password) throws Exception{
		// TODO Auto-generated method stub
		java.sql.PreparedStatement preparedStatement = null;
		try {
			query = "select * from user where email=? and password=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return "success";
			}
			else {
				return "fail";
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		return "exception";

	}
	
	// **********************************Divya***********************************************************
	
	
	
	
	public String getFDdetails(String email) throws Exception
	
	{
		java.sql.PreparedStatement preparedStatement = null;
		JSONObject user = new JSONObject();

		try {
			query = "select income,citizen_category from user where email=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				// System.out.println(resultSet.getString("earning_per_share"));
				user.put("income", resultSet.getInt("income"));
				user.put("category",resultSet.getString("citizen_category"));

			} else {
				user.put("result", "fail");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(user.toString());
		return user.toString();

		
	}

	public String getRateOfInterest(String bank_name) throws Exception {

		java.sql.PreparedStatement preparedStatement = null;
		JSONObject user = new JSONObject();

		try {
			query = "select rate_of_interest from fixeddeposit where bankname=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bank_name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				// System.out.println(resultSet.getString("earning_per_share"));
				user.put("rate_of_interest", resultSet.getString("rate_of_interest"));
				user.put("result", "success");

			} else {
				user.put("result", "fail");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(user.toString());
		return user.toString();

	}

	public void readCSVOfFixedDeposit() {
		try {

			java.sql.PreparedStatement preparedStatement = null;
			query = "truncate table fixeddeposit";
			preparedStatement = connection.prepareStatement(query);
			// preparedStatement.setString(1, BankName);
			preparedStatement.executeQuery();
			String loadQuery = "LOAD DATA LOCAL INFILE '" + "output_fixed.csv"
					+ "' INTO TABLE fixeddeposit FIELDS TERMINATED BY ','"
					+ " LINES TERMINATED BY '\n' (bankname, rate_of_interest) ";
			System.out.println(loadQuery);
			Statement stmt = (Statement) connection.createStatement();
			stmt.execute(loadQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getbanknameFD() {
		ArrayList<String> al = new ArrayList<String>();
		try {
			java.sql.PreparedStatement preparedStatement = null;
			query = "Select bankname from fixeddeposit";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				al.add(resultSet.getString("bankname"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}

	public void readCSVOfFixedDeposit1() {
		try {

			java.sql.PreparedStatement preparedStatement = null;
			query = "truncate table fdrates";
			preparedStatement = connection.prepareStatement(query);
			// preparedStatement.setString(1, BankName);
			preparedStatement.executeQuery();
			String loadQuery = "LOAD DATA LOCAL INFILE '" + "general_fixedrate.csv"
					+ "' INTO TABLE fdrates FIELDS TERMINATED BY ','"
					+ " LINES TERMINATED BY '\n' (bankname, generalrate,seniorrate) ";
			System.out.println(loadQuery);
			Statement stmt = (Statement) connection.createStatement();
			stmt.execute(loadQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// divya
	public void readCSVOfFixedDeposit2() {
		try {

			java.sql.PreparedStatement preparedStatement = null;
			query = "truncate table taxsavingfd";
			preparedStatement = connection.prepareStatement(query);
			// preparedStatement.setString(1, BankName);
			preparedStatement.executeQuery();
			String loadQuery = "LOAD DATA LOCAL INFILE '" + "taxsaving_fixedrate.csv"
					+ "' INTO TABLE taxsavingfd FIELDS TERMINATED BY ','"
					+ " LINES TERMINATED BY '\n' (bankname, generalrate,seniorrate) ";
			System.out.println(loadQuery);
			Statement stmt = (Statement) connection.createStatement();
			stmt.execute(loadQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// divya
	public GeneralFDModel getMaxRegularRate() throws Exception {

		java.sql.PreparedStatement preparedStatement = null;
		// JSONObject user = new JSONObject();
		GeneralFDModel gd = new GeneralFDModel();

		try {
			query = "select * from fdrates where generalrate=(select max(generalrate) from fdrates)";
			preparedStatement = connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				// System.out.println(resultSet.getString("earning_per_share"));
				gd.setBankname(resultSet.getString("bankname"));
				gd.setRate(resultSet.getDouble("generalrate"));

			}
			/*
			 * else { user.put("result", "fail"); }
			 */

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// System.out.println(user.toString());
		return gd;

	}

	// divya
	public GeneralFDModel getMaxSeniorRate() throws Exception {

		java.sql.PreparedStatement preparedStatement = null;
		// JSONObject user = new JSONObject();
		GeneralFDModel gd = new GeneralFDModel();

		try {
			query = "select * from fdrates where seniorrate=(select max(seniorrate) from fdrates)";
			preparedStatement = connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				// System.out.println(resultSet.getString("earning_per_share"));
				gd.setBankname(resultSet.getString("bankname"));
				gd.setRate(resultSet.getDouble("seniorrate"));

			}
			/*
			 * else { user.put("result", "fail"); }
			 */

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// System.out.println(user.toString());
		return gd;

	}

	// divya
	public GeneralFDModel getMaxRegularTaxSaving() throws Exception {

		java.sql.PreparedStatement preparedStatement = null;
		// JSONObject user = new JSONObject();
		GeneralFDModel gd = new GeneralFDModel();

		try {
			query = "select * from taxsavingfd where generalrate=(select max(generalrate) from taxsavingfd)";
			preparedStatement = connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				// System.out.println(resultSet.getString("earning_per_share"));
				gd.setBankname(resultSet.getString("bankname"));
				gd.setRate(resultSet.getDouble("generalrate"));

			}
			/*
			 * else { user.put("result", "fail"); }
			 */

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// System.out.println(user.toString());
		return gd;

	}

	// divya
	public GeneralFDModel getMaxSeniorTaxSaving() throws Exception {

		java.sql.PreparedStatement preparedStatement = null;
		// JSONObject user = new JSONObject();
		GeneralFDModel gd = new GeneralFDModel();

		try {
			query = "select * from taxsavingfd where seniorrate=(select max(seniorrate) from taxsavingfd)";
			preparedStatement = connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				// System.out.println(resultSet.getString("earning_per_share"));
				gd.setBankname(resultSet.getString("bankname"));
				gd.setRate(resultSet.getDouble("seniorrate"));

			}
			/*
			 * else { user.put("result", "fail"); }
			 */

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// System.out.println(user.toString());
		return gd;

	}

	// **************************************Jhalak***************************************************
	public String getGoldDetails(String carat) throws Exception {

		java.sql.PreparedStatement preparedStatement = null;
		JSONObject user = new JSONObject();
		System.out.println("fc-------------------------------------" + carat);
		try {
			query = "select curr_rate from gold_rate where carat=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, carat);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				// System.out.println(resultSet.getString("earning_per_share"));
				user.put("curr_rate", resultSet.getString("curr_rate"));
				user.put("result", "success");

			} else {
				user.put("result", "fail");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(user.toString());
		return user.toString();

	}

	public void readCsvUsingGold() {
		try {

			java.sql.PreparedStatement preparedStatement = null;
			query = "truncate table gold_rate";
			preparedStatement = connection.prepareStatement(query);
			// preparedStatement.setString(1, BankName);
			preparedStatement.executeQuery();
			String loadQuery = "LOAD DATA LOCAL INFILE '" + "output_gold.csv"
					+ "' INTO TABLE gold_rate FIELDS TERMINATED BY ','"
					+ " LINES TERMINATED BY '\n' (carat, curr_rate) ";
			System.out.println(loadQuery);
			Statement stmt = (Statement) connection.createStatement();
			stmt.execute(loadQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
public String getMFdetails(String email) throws Exception
	
	{
		java.sql.PreparedStatement preparedStatement = null;
		JSONObject user = new JSONObject();

		try {
			query = "select age,savings from user where email=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				// System.out.println(resultSet.getString("earning_per_share"));
				user.put("age", resultSet.getInt("age"));
				user.put("savings",resultSet.getDouble("savings"));

			} else {
				user.put("result", "fail");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(user.toString());
		return user.toString();

		
	}
 

	public MutualFundModel getEquityMFDetails(String duration) throws Exception {

		java.sql.PreparedStatement preparedStatement = null;
		MutualFundModel mf = new MutualFundModel();
		System.out.println("Mutual fund duration " + duration);

		try {
			query = "select fund," + duration + " from MutualFund where " + duration + "=(select max(" + duration
					+ ") from MutualFund where type=\"Equity\")";
			preparedStatement = connection.prepareStatement(query);
			// preparedStatement.setString(1, carat);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				mf.setFundName(resultSet.getString("fund"));
				mf.setRate(resultSet.getDouble(duration));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// System.out.println(user.toString());
		return mf;

	}

	public MutualFundModel getDebtMFDetails(String duration) throws Exception {

		java.sql.PreparedStatement preparedStatement = null;
		MutualFundModel mf = new MutualFundModel();
		System.out.println("Mutual fund duration " + duration);

		try {
			query = "select fund," + duration + " from MutualFund where " + duration + "=(select max(" + duration
					+ ") from MutualFund where type=\"Debt\")";
			preparedStatement = connection.prepareStatement(query);
			// preparedStatement.setString(1, carat);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				mf.setFundName(resultSet.getString("fund"));
				mf.setRate(resultSet.getDouble(duration));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// System.out.println(user.toString());
		return mf;

	}

	public MutualFundModel getHybridMFDetails(String duration) throws Exception {

		java.sql.PreparedStatement preparedStatement = null;
		MutualFundModel mf = new MutualFundModel();
		System.out.println("Mutual fund duration " + duration);

		try {
			query = "select fund," + duration + " from MutualFund where " + duration + "=(select max(" + duration
					+ ") from MutualFund where type=\"Hybrid\")";
			preparedStatement = connection.prepareStatement(query);
			// preparedStatement.setString(1, carat);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				mf.setFundName(resultSet.getString("fund"));
				mf.setRate(resultSet.getDouble(duration));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// System.out.println(user.toString());
		return mf;

	}

	public void readCsvUsingMF() {
		try {

			java.sql.PreparedStatement preparedStatement = null;
			query = "truncate table MutualFund";
			preparedStatement = connection.prepareStatement(query);
			// preparedStatement.setString(1, BankName);
			preparedStatement.executeQuery();
			String loadQuery = "LOAD DATA LOCAL INFILE '" + "output_MF.csv"
					+ "' INTO TABLE MutualFund FIELDS TERMINATED BY ','"
					+ " LINES TERMINATED BY '\n' (fund, type, 6mon, 1yr, 2yr, 3yr) ";
			System.out.println(loadQuery);
			Statement stmt = (Statement) connection.createStatement();
			stmt.execute(loadQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//***************************************Manisha******************************************************
	
	public void readCsvUsingLoad() {
		try {

			java.sql.PreparedStatement preparedStatement = null;
			query = "truncate table stock";
			preparedStatement = connection.prepareStatement(query);
			// preparedStatement.setString(1, BankName);
			preparedStatement.executeQuery();
			String loadQuery = "LOAD DATA LOCAL INFILE '" + "/home/manisha/Downloads/DM project/output_stock.csv"
					+ "' INTO TABLE stock FIELDS TERMINATED BY '\t'"
					+ " LINES TERMINATED BY '\n' (companyname, earning_per_share, current_stock_price) ";
			System.out.println(loadQuery);
			Statement stmt = (Statement) connection.createStatement();
			stmt.execute(loadQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertdata() {

		String csvFile = "/home/manisha/Downloads/DM project/buy_stock2.csv";
		String line = "";
		String cvsSplitBy = "\t";

		java.sql.PreparedStatement preparedStatement = null;
		java.sql.PreparedStatement preparedStatement1 = null;

		// System.out.println(country[1]);

		query = "select companyname,profit from stock_buy1";
		try {
			preparedStatement = connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				// System.out.println("Op");
				int f = 0;
				try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

					while ((line = br.readLine()) != null) {
						// System.out.println("hu");
						String[] country = line.split(cvsSplitBy);
						if (country[0].equals(resultSet.getString("companyname"))) {
							Double profit = resultSet.getDouble("profit");
							Double d = Double.parseDouble(country[1]);
							Double profitLiabilityRatio = profit / d;
							String query1 = "update stock_buy1 set liabilities= ?, ProfitLiabilityRatio=? where companyname =?";
							preparedStatement1 = connection.prepareStatement(query1);

							preparedStatement1.setDouble(1, d);
							preparedStatement1.setDouble(2, profitLiabilityRatio);
							preparedStatement1.setString(3, country[0]);
							preparedStatement1.executeUpdate();
							f = 1;
							break;
						}

					}
					if (f == 0) {
						String query1 = "delete from stock_buy1 where companyname=?";
						preparedStatement1 = connection.prepareStatement(query1);
						preparedStatement1.setString(1, resultSet.getString("companyname"));
						preparedStatement1.executeUpdate();
					}
				}

				catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	// Fetch list of company corresponding to a creator
	public JSONArray getAllCompany() throws Exception {

		java.sql.PreparedStatement preparedStatement = null;
		// JSONObject user = new JSONObject();
		JSONObject result = new JSONObject();
		JSONArray companies = new JSONArray();

		// System.out.println("lj");
		try {
			query = "select * from stock_buy1";
			preparedStatement = connection.prepareStatement(query);
			// preparedStatement.setString(1, creator_id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				JSONObject company = new JSONObject();
				company.put("companyname", resultSet.getString("companyname"));
				company.put("profit", resultSet.getString("profit"));
				company.put("liabilities", resultSet.getString("liabilities"));
				companies.put(company);
			}
			result.put("stat", "success");
			result.put("companies", companies);
			System.out.println(result);
			return companies;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		result.put("stat", "fail");
		return companies;
	}

	public ArrayList<String> getcompanynam() {
		ArrayList<String> al = new ArrayList<String>();
		try {
			java.sql.PreparedStatement preparedStatement = null;
			query = "Select companyname from stock";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				al.add(resultSet.getString("companyname"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}

	public void readBuyCsvUsingLoad() {
		try {

			java.sql.PreparedStatement preparedStatement = null;
			query = "truncate table stock_buy1";
			preparedStatement = connection.prepareStatement(query);
			// preparedStatement.setString(1, BankName);
			preparedStatement.executeQuery();
			String loadQuery = "LOAD DATA LOCAL INFILE '" + "/home/manisha/Downloads/DM project/buy_stock.csv"
					+ "' INTO TABLE stock_buy1 FIELDS TERMINATED BY '\t'"
					+ " LINES TERMINATED BY '\n' (companyname, LastPrice, Change_company,  Change_percent, profit) ";
			System.out.println(loadQuery);
			Statement stmt = (Statement) connection.createStatement();
			stmt.execute(loadQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JSONObject getmax() {
		// TODO Auto-generated method stub
		java.sql.PreparedStatement preparedStatement = null;
		JSONObject user = new JSONObject();

		try {
			query = "select  companyname , LastPrice, Change_company , Change_percent, profit, liabilities, ProfitLiabilityRatio  from stock_buy1 where ProfitLiabilityRatio=(select max(ProfitLiabilityRatio) from stock_buy1)";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				// System.out.println(resultSet.getString("earning_per_share"));
				user.put("companyname", resultSet.getString("companyname"));
				user.put("LastPrice", resultSet.getDouble("LastPrice"));
				user.put("Change_company", resultSet.getDouble("Change_company"));
				user.put("Change_percent", resultSet.getDouble("Change_percent"));
				user.put("profit", resultSet.getDouble("profit"));
				user.put("liabilities", resultSet.getDouble("liabilities"));
				user.put("ProfitLiabilityRatio", resultSet.getDouble("ProfitLiabilityRatio"));
				user.put("result", "success");

			} else {
				user.put("result", "fail");
			}

		} catch (SQLException | JSONException e) {
			e.printStackTrace();
		}
		System.out.println(user.toString());
		return user;

	}

	public JSONObject getmaxprofit() {
		// TODO Auto-generated method stub
		java.sql.PreparedStatement preparedStatement = null;
		JSONObject user = new JSONObject();

		try {
			query = "select  companyname , LastPrice, Change_company , Change_percent, profit, liabilities, ProfitLiabilityRatio  from stock_buy1 where profit=(select max(profit) from stock_buy1);";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user.put("companyname", resultSet.getString("companyname"));
				user.put("LastPrice", resultSet.getDouble("LastPrice"));
				user.put("Change_company", resultSet.getDouble("Change_company"));
				user.put("Change_percent", resultSet.getDouble("Change_percent"));
				user.put("profit", resultSet.getDouble("profit"));
				user.put("liabilities", resultSet.getDouble("liabilities"));
				user.put("ProfitLiabilityRatio", resultSet.getDouble("ProfitLiabilityRatio"));
				user.put("result", "success");

			} else {
				user.put("result", "fail");
			}

		} catch (SQLException | JSONException e) {
			e.printStackTrace();
		}
		System.out.println(user.toString());
		return user;

	}

	public ArrayList<String> getcompanynamesell() {
		ArrayList<String> al = new ArrayList<String>();
		try {
			java.sql.PreparedStatement preparedStatement = null;
			query = "Select companyname from stock_buy1 ";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				al.add(resultSet.getString("companyname"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}

	public JSONObject getLastPrice(String cname) throws Exception {

		java.sql.PreparedStatement preparedStatement = null;
		// JSONObject user = new JSONObject();
		JSONObject result = new JSONObject();
		JSONArray companies = new JSONArray();

		// System.out.println("lj");
		try {
			query = "select * from stock_buy1 where companyname=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, cname);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {// System.out.println(resultSet.getString("LastPrice"));
				result.put("companyname", resultSet.getString("companyname"));
				result.put("LastPrice", resultSet.getDouble("LastPrice"));
				result.put("Change_company", resultSet.getDouble("Change_company"));
				result.put("Change_percent", resultSet.getDouble("Change_percent"));
				result.put("profit", resultSet.getDouble("profit"));
				result.put("liabilities", resultSet.getDouble("liabilities"));
				result.put("ProfitLiabilityRatio", resultSet.getDouble("ProfitLiabilityRatio"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getDetails(String company_name) throws Exception {

		java.sql.PreparedStatement preparedStatement = null;
		JSONObject user = new JSONObject();

		try {
			query = "select earning_per_share,current_stock_price from stock where companyname=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, company_name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				user.put("earning_per_share", resultSet.getString("earning_per_share"));
				user.put("current_stock_price", resultSet.getString("current_stock_price"));
				user.put("result", "success");

			} else {
				user.put("result", "fail");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(user.toString());
		return user.toString();

	}

	// *********************************jyoti*********************************************************

public String getHomeLoandetails(String email) throws Exception
	
	{
		java.sql.PreparedStatement preparedStatement = null;
		JSONObject user = new JSONObject();

		try {
			query = "select employee_type,income,age from user where email=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				// System.out.println(resultSet.getString("earning_per_share"));
				user.put("emp", resultSet.getString("employee_type"));
				user.put("income",resultSet.getInt("income"));
				user.put("age",resultSet.getInt("age"));

			} else {
				user.put("result", "fail");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(user.toString());
		return user.toString();

		
	}
	
	public String getBankDetails(String BankName) throws Exception {

		java.sql.PreparedStatement preparedStatement = null;
		JSONObject user = new JSONObject();

		try {
			query = "select rateofInterest from Emi where bankname=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, BankName);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				// System.out.println(resultSet.getString("earning_per_share"));
				user.put("rateofInterest", resultSet.getString("rateofinterest"));
				user.put("result", "success");

			} else {
				user.put("result", "fail");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(user.toString());
		return user.toString();

	}

	// Jyoti
	public ArrayList<String> getbankname() {
		ArrayList<String> al = new ArrayList<String>();
		try {
			java.sql.PreparedStatement preparedStatement = null;
			query = "Select bankname from Emi";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				al.add(resultSet.getString("bankname"));
				System.out.println(resultSet.getString("bankname"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}

	public void readCsvUsingLoadHomeLoan() {
		try {

			java.sql.PreparedStatement preparedStatement = null;
			query = "truncate table Emi";
			preparedStatement = connection.prepareStatement(query);
			// preparedStatement.setString(1, BankName);
			preparedStatement.executeQuery();

			String loadQuery = "LOAD DATA LOCAL INFILE '" + "homeloan.csv" + "' INTO TABLE Emi FIELDS TERMINATED BY ';'"
					+ " LINES TERMINATED BY '\n' (bankname,rateofinterest) ";
			System.out.println(loadQuery);
			Statement stmt = (Statement) connection.createStatement();
			stmt.execute(loadQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getBankDetailsofMinimumInterest() {
		java.sql.PreparedStatement preparedStatement = null;
		JSONObject user = new JSONObject();
		JSONArray array2 = new JSONArray();

		try {
			query = "select bankname,rateofinterest from Emi where rateofinterest=(select min(rateofinterest) from Emi)";
			preparedStatement = connection.prepareStatement(query);
			// preparedStatement.setString(1, BankName)"
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				// System.out.println(resultSet.getString("earning_per_share"));
//				user.put("bankname", resultSet.getString("bankname"));
//				user.put("interstrate", resultSet.getString("rateofinterest"));

				array2.put(new JSONObject().put("bankname", resultSet.getString("bankname")).put("interestrate",
						resultSet.getString("rateofinterest")));
				/* user.put("result", "success"); */

			}
			user.put("ListOfBank", array2);

		} catch (SQLException | JSONException e) {
			e.printStackTrace();
		}
		System.out.println(user.toString());
		return user.toString();
	}

}
