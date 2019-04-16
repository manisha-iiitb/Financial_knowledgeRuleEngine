package DM.Project;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

		String user = "root";
		String password = "impossible";
		connection = null;

		try {
			connection = (Connection) DriverManager.getConnection(url, user, password);
			System.out.println("Successfully Connected to Database");
		} catch (SQLException e) {
			System.out.println("SQL Exception: " + e);
		}
	}

	public String getDetails(String company_name) throws Exception {

		java.sql.PreparedStatement preparedStatement = null;
		JSONObject user = new JSONObject();

		try {
			query = "select earning_per_share from stock where companyname=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, company_name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				// System.out.println(resultSet.getString("earning_per_share"));
				user.put("earning_per_share", resultSet.getString("earning_per_share"));
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
//Divya
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
	public  void readCSVOfFixedDeposit()
    {
        try 
        {
        	
        	java.sql.PreparedStatement preparedStatement = null;
			  query ="truncate table fixeddeposit"; 
			  preparedStatement = connection.prepareStatement(query);
			 // preparedStatement.setString(1, BankName); 
			  preparedStatement.executeQuery();
                String loadQuery = "LOAD DATA LOCAL INFILE '" + "output_fixed.csv" + "' INTO TABLE fixeddeposit FIELDS TERMINATED BY ','" + " LINES TERMINATED BY '\n' (bankname, rate_of_interest) ";
                System.out.println(loadQuery);
                Statement stmt = (Statement) connection.createStatement();
                stmt.execute(loadQuery);
        }
        catch (Exception e)
        {
                e.printStackTrace();
        }
    }
	public ArrayList<String> getbanknameFD()
	   {
			  ArrayList<String> al=new ArrayList<String>();
    	try
    	{
		   java.sql.PreparedStatement preparedStatement = null;
    		query ="Select bankname from fixeddeposit"; 
			  preparedStatement = connection.prepareStatement(query);	
			  resultSet = preparedStatement.executeQuery();
		
			  while(resultSet.next())
			  {
				 al.add(resultSet.getString("bankname"));
			  }
			 
    	}
    	catch (SQLException e) {
			 e.printStackTrace();
		   }
return al;
	   }

	// Jhalak
	public String getGoldDetails(String carat) throws Exception {

	       java.sql.PreparedStatement preparedStatement = null;
	       JSONObject user = new JSONObject();
	       System.out.println("fc-------------------------------------"+carat);
	    try {
		   query = "select curr_rate from gold_rate where carat=?";
		   preparedStatement = connection.prepareStatement(query);
		   preparedStatement.setString(1, carat);
		   resultSet = preparedStatement.executeQuery();
		   if(resultSet.next()) {
			  // System.out.println(resultSet.getString("earning_per_share"));
			 user.put("curr_rate", resultSet.getString("curr_rate"));
			 user.put("result", "success");

		 }
		 else {
			user.put("result", "fail");
		 }

	   } catch (SQLException e) {
		 e.printStackTrace();
	   }
	   System.out.println(user.toString());
	   return user.toString();

}

	public  void readCsvUsingGold()
    {
        try 
        {
        	
        	java.sql.PreparedStatement preparedStatement = null;
			  query ="truncate table gold_rate"; 
			  preparedStatement = connection.prepareStatement(query);
			 // preparedStatement.setString(1, BankName); 
			  preparedStatement.executeQuery();
                String loadQuery = "LOAD DATA LOCAL INFILE '" + "output_gold.csv" + "' INTO TABLE gold_rate FIELDS TERMINATED BY ','" + " LINES TERMINATED BY '\n' (carat, curr_rate) ";
                System.out.println(loadQuery);
                Statement stmt = (Statement) connection.createStatement();
                stmt.execute(loadQuery);
        }
        catch (Exception e)
        {
                e.printStackTrace();
        }
    }
	// Jyoti
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
//MAnisha
	public void readCsvUsingLoad() {
		try {

			java.sql.PreparedStatement preparedStatement = null;
			query = "truncate table stock";
			preparedStatement = connection.prepareStatement(query);
			// preparedStatement.setString(1, BankName);
			preparedStatement.executeQuery();
			String loadQuery = "LOAD DATA LOCAL INFILE '" + "output_stock.csv"
					+ "' INTO TABLE stock FIELDS TERMINATED BY ','"
					+ " LINES TERMINATED BY '\n' (companyname, earning_per_share) ";
			System.out.println(loadQuery);
			Statement stmt = (Statement) connection.createStatement();
			stmt.execute(loadQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//Manisha
	public ArrayList<String> getcompanyname() {
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
	
	//Jyoti
	public ArrayList<String> getbankname() {
		ArrayList<String> al = new ArrayList<String>();
		try {
			java.sql.PreparedStatement preparedStatement = null;
			query = "Select bankname from Emi";
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
	//jyoti
	
	public  void readCsvUsingLoadHomeLoan() {
		 try 
	        {
		
		  java.sql.PreparedStatement preparedStatement = null;
		  query ="truncate table Emi"; 
		  preparedStatement = connection.prepareStatement(query);
		 // preparedStatement.setString(1, BankName); 
		  preparedStatement.executeQuery();
		 
			 
	 
	                String loadQuery = "LOAD DATA LOCAL INFILE '" + "homeloan.csv" + "' INTO TABLE Emi FIELDS TERMINATED BY ';'" + " LINES TERMINATED BY '\n' (bankname,rateofinterest) ";
	                System.out.println(loadQuery);
	                Statement stmt = (Statement) connection.createStatement();
	                stmt.execute(loadQuery);
	        }
	        catch (Exception e)
	        {
	                e.printStackTrace();
	        }
		
	}
}
