package Package;
//import java.beans.Statement;
import java.sql.*;
import java.util.Currency;
import java.util.concurrent.ExecutionException;

//import com.sun.corba.se.pept.transport.Connection;


public class DBconnect {

	private Connection con;
	private Statement st;
	private Statement st1;
	private ResultSet rs;
	private String query;
	private String username = "";
	private String password = "";
	
	public DBconnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-01.cleardb.net:3306/ad_a65dfb72eb8d753", "b2d2fd7ffbad33", "95cac4ee");
			st =con.createStatement();
		}catch(Exception ex){
			System.out.println("Error :" + ex);
		}
	}
	

	
	protected String verifyLogin(String inUserName, String inPassword){
		String outPassword = "";
		username = inUserName;
		password = inPassword;
		
		try{
			query = "select PASSWORD from LOGINDETAILS where USERNAME = " + "'" + username + "'";
			System.out.println(query);
			rs = st.executeQuery(query);
			if(!rs.next()){
				System.out.println("User Does not exist");
				return "";
			}
			else{
				outPassword = rs.getString("PASSWORD");
				if(outPassword.isEmpty() || outPassword.equals(null)){
					return "";
				}
				if(outPassword.equals(password)){
					try{
						String firstName = "";
						int userId = 0;
						query = "select u.FIRSTNAME, l.USERID from USERDETAILS u, LOGINDETAILS l where l.USERNAME = " + "'" + username +"'" + " and u.USERID = l.USERID";
						rs = st.executeQuery(query);
						while(rs.next()){
						firstName = rs.getString("FIRSTNAME");
						userId = rs.getInt("USERID");
						}
						return firstName + "," + userId;
					}catch(Exception ee){
						System.out.println("Error in fetching first name from userdetails " + ee);
					}
					
				}
				else{
					return "";
				}
			}
			
		}catch(Exception ex){
			System.out.println("Database Error: " + ex);
		}
		return "";
	}
	
	
	
}
