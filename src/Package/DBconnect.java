package Package;
//import java.beans.Statement;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Currency;
import java.util.concurrent.ExecutionException;

import org.apache.catalina.User;

//import com.sun.corba.se.pept.transport.Connection;


public class DBconnect {

	private Connection con = null;
	private Statement st;
	private Statement st1;
	private ResultSet rs;
	private String query;
	private String username = "";
	private String password = "";
	PreparedStatement preparedStatement = null;
	public DBconnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-01.cleardb.net:3306/ad_a65dfb72eb8d753", "b2d2fd7ffbad33", "95cac4ee");
			st =con.createStatement();
		}catch(Exception ex){
			System.out.println("Error :" + ex);
		}
	}
	
/**
 * Verify the user login
 * @param inUserName
 * @param inPassword
 * @return
 */
	
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
				System.out.println("password from db " + outPassword);
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
						return "";
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
	
	/**
	 * Register a new user
	 */
	protected String registerUser(String password, String firstName, String lastName, String university, String fields, 
			float rate, String email,String tagLine){
		
			int userID=0;
		
		try{
			query = "insert into LOGINDETAILS (USERNAME,PASSWORD) values (" + "'"+email+"'" + "," + "'"+password+"'" +")" ;
			System.out.println(query);
			int insertStatus = st.executeUpdate(query);
			System.out.println("Login details Insert Status  "+insertStatus);
			if(insertStatus == 1){
			query = "select USERID from LOGINDETAILS where USERNAME = " + "'"+email+"'" ;
			System.out.println(query);
			rs = st.executeQuery(query);
			while(rs.next()){
				userID = rs.getInt("USERID");
			}
			System.out.println("New User " + userID);
			query = "insert into USERDETAILS (USERID, EMAIL, FIRSTNAME, LASTNAME, UNIVERSITY, FIELDS, HOURLYRATE, TAGLINE) values ("
			+ userID + ",'"+ email+"','" +firstName+ "','" +lastName+ "','"+university+"','"+fields+"',"+rate + ",'"+tagLine+"'" +")";
			System.out.println(query);
			insertStatus = st.executeUpdate(query);
			System.out.println("User details insert status " + insertStatus);
			}
			return firstName +"," + userID;
		}catch(Exception ex){
			System.out.println("Error in Inserting : " + ex);
			if (userID !=0){
				try{
				query = "delete from LOGINDETAILS where USERID = " + userID;
				st.executeUpdate(query);
				}catch(Exception e){
					System.out.println("Error in catch block, Cannot remove user.");
				}
			}
		}

		return "";
	}
	
	
	/**
	 * Function for getting the jobs for all users
	 * @param category
	 * @param field
	 * @return
	 */
	protected List<GetJobClass> getJobs(String category, String field){
		
		List<GetJobClass> jobs = new ArrayList<GetJobClass>();
		if(category.equalsIgnoreCase("all") && field.equalsIgnoreCase("all")){
			query="select * from jobs";
		}
		else {
			String output = getCategoryAndField(category,field);
			String[] op = output.split(",");
			int categoryId = Integer.parseInt(op[0]);
			
		//	System.out.println("category id = " + categoryId + "Field ID = " + fieldId );
			if(field.equalsIgnoreCase("all")){
				query="select * from jobs where categoryid = " + categoryId;
			}
			else{
				int fieldId = Integer.parseInt(op[1]);
				query="select * from jobs where categoryid = " + categoryId + " and " +  " fieldid = " + fieldId ;
			}
		}
		try{
			rs = st.executeQuery(query);
			
			while(rs.next()){
				GetJobClass g = new GetJobClass();
				g.setJobId(rs.getInt("JOBID"));
				g.setPostUserId(rs.getInt("POSTUSERID"));
				g.setAssignUserId(rs.getInt("ASSIGNEDUSERID"));
				g.setDeadline(rs.getInt("DEADLINE"));
				g.setDescription(rs.getString("DESCRIPTION"));
				g.setPay(rs.getInt("PAY"));
				g.setStatus(rs.getString("STATUS"));
				g.setCategoryId(rs.getInt("CATEGORYID"));
				g.setTitle(rs.getString("TITLE"));
				g.setAddTime(rs.getString("TIME"));
				g.setLocation(rs.getString("LOCATION"));
				g.setFieldId(rs.getInt("FIELDID"));
				jobs.add(g);
			}
		}catch(Exception ee){
			System.out.println("Unable to get jobs list for category and field "+ category + " " + field + ee);
			
		}
		
		return jobs;
	}
	
	/**
	 * Get the categoryId and fieldsID for any  category and field
	 * @param category
	 * @param field
	 * @return
	 */
	
	protected String getCategoryAndField(String category, String field){
		String output="";
		
		try{
			query = "select categoryid from Categories where categoryname = "+ "'" + category + "'" + " " + "limit 1";
			System.out.println("Query for category id " + query);
			rs = st.executeQuery(query);
			while(rs.next()){
				output = "" + rs.getInt("CATEGORYID");
				System.out.println("Category Id is " + output);
			}
		}catch(Exception e){
			System.out.println("Unable to get the category for the product " + e);
		}
		if (!field.equalsIgnoreCase("all")){
			try{
				query = "select fieldid from FIELDS where FIELDNAME = "+ "'" + field + "'" + " " + "limit 1";
				System.out.println("Query for Field id " + query);
				rs = st.executeQuery(query);
				while(rs.next()){
					output = output + "," + rs.getInt("FIELDID");
				}
			}catch(Exception ee){
				System.out.println("unable to fetch the field ID for " + field);
			}
		}
		return output;
		
	}
	/**
	 * Get all categories and fields
	 * @return
	 */
	
	protected ArrayList<GetCategoryAndField> getAllCategoriesAndFields(){
		
		ArrayList<GetCategoryAndField> cAndF = new ArrayList<GetCategoryAndField>();
		
		query = "SELECT DISTINCT C.CATEGORYNAME, F.FIELDNAME FROM CATEGORIES C, FIELDS F WHERE  C.CATEGORYID = F.CATEGORYID";
		try{
			rs = st.executeQuery(query);
			
			while(rs.next()){
				GetCategoryAndField cf = new GetCategoryAndField();
				cf.setCategory(rs.getString("CATEGORYNAME"));
				cf.setField(rs.getString("FIELDNAME"));
				cAndF.add(cf);
			}
			
		}catch (Exception ex){
			System.out.println("Cannot retrieve all categories and fields " + ex);
		}	
		
		return cAndF;
	}
	
	/**
	 * Function to post the proposal of the user to the database
	 * @param pUserId
	 * @param pJobId
	 * @param proposal
	 * @param proposalTime
	 * @return
	 */
	
	protected int postUserProposal(int pUserId, int pJobId, String proposal, String proposalTime){
		int status = 0;
		
		query = "Insert into PROPOSAL (proposeduserid, jobid, proposal, proposaltime) values "
				+ "(" + pUserId + "," + pJobId + ",'"+ proposal + "'," + "'" + proposalTime + "')";
		System.out.println("under postUserProposal query " + query);
		try{
			status = st.executeUpdate(query);
			
		}catch(Exception ex){
			System.out.println("Unable to insert the proposal for the user " + ex);
		}
		
		return status;
	}
	
	/**
	 * Get the jos list for the logged in user
	 * @param userId
	 * @param value
	 * @return
	 */
	
	protected List<GetJobClass> getMyJobs(int userId, String value){
		
		List<GetJobClass> jobs = new ArrayList<GetJobClass>();
		if(value.equalsIgnoreCase("all")){
			query = "select * from jobs where postuserid = "+ userId;
		}
		else if(value.equalsIgnoreCase("completed")){
			query = "select * from jobs where postuserid = "+ userId + " and status = 'completed'";
		}
		else if(value.equalsIgnoreCase("myJobs")){
			query = "select * from jobs where assigneduserid = "+ userId;
		}
		else if(value.equalsIgnoreCase("completedByMe")){
			query = "select * from jobs where assigneduserid = "+ userId + " and status = 'completed'";
		}
		try{
			rs = st.executeQuery(query);
			
			while(rs.next()){
				GetJobClass g = new GetJobClass();
				g.setJobId(rs.getInt("JOBID"));
				g.setPostUserId(rs.getInt("POSTUSERID"));
				g.setAssignUserId(rs.getInt("ASSIGNEDUSERID"));
				g.setDeadline(rs.getInt("DEADLINE"));
				g.setDescription(rs.getString("DESCRIPTION"));
				g.setPay(rs.getInt("PAY"));
				g.setStatus(rs.getString("STATUS"));
				g.setCategoryId(rs.getInt("CATEGORYID"));
				g.setTitle(rs.getString("TITLE"));
				g.setAddTime(rs.getString("TIME"));
				g.setLocation(rs.getString("LOCATION"));
				g.setFieldId(rs.getInt("FIELDID"));
				jobs.add(g);
			}
		}catch(Exception ee){
			System.out.println("unable to get jobs for userId "+ userId + "  "  + ee);
			
		}
		return jobs;
	}
	
	/**
	 * Get the proposals for the jobs posted by the user
	 * @param userId
	 * @return
	 * 
	 */
	
	
	protected List<UserAndProposal> getMyProposals(int userId, String proposal){
		
		List<UserAndProposal> proposals = new ArrayList<UserAndProposal>();
		
		if (proposal.equalsIgnoreCase("proposalsForMe")){
			query = "select j.jobid, j.title, p.proposal, p.proposaltime, u.userid, u.firstname, u.lastname, u.university, u.fields, u.experience,"
					+ "u.hourlyrate, u.city, u.email from jobs j, proposal p, userdetails u where j.postuserid = " + userId + " and "
							+ "j.jobid = p.jobid and p.proposeduserid = u.userid";
			
			try{
				rs = st.executeQuery(query);
				while (rs.next()){
					UserAndProposal up = new UserAndProposal();
					up.setJobId(rs.getInt("JOBID"));
					up.setJobTitle(rs.getString("TITLE"));
					up.setProposal(rs.getString("PROPOSAL"));
					up.setProposalTime(rs.getString("PROPOSALTIME"));
					up.setUserId(rs.getInt("USERID"));
					up.setFirstName(rs.getString("FIRSTNAME"));
					up.setLastName(rs.getString("LASTNAME"));
					up.setUniversity(rs.getString("UNIVERSITY"));
					up.setFields(rs.getString("FIELDS"));
					up.setExperience(rs.getFloat("EXPERIENCE"));
					up.setRate(rs.getFloat("HOURLYRATE"));
					up.setCity(rs.getString("CITY"));
					up.setEmail(rs.getString("EMAIL"));
					proposals.add(up);	
				}
				return proposals;
				
			}catch(Exception ex){
				System.out.println("getMyproposal error " + ex);
			}
		}
		else if(proposal.equalsIgnoreCase("myProposals")){
			query = "select j.jobid, j.title, j.description,  p.proposal, p.proposaltime from jobs j, proposal p where p.proposeduserid = "+userId;
			
			try{
				rs = st.executeQuery(query);
				while (rs.next()){
					UserAndProposal up = new UserAndProposal();
					up.setJobId(rs.getInt("JOBID"));
					up.setJobTitle(rs.getString("TITLE"));
					up.setProposal(rs.getString("PROPOSAL"));
					up.setProposalTime(rs.getString("PROPOSALTIME"));
					up.setDescription(rs.getString("DESCRIPTION"));
					proposals.add(up);
				}
				
				return proposals;
			}catch(Exception e){
				System.out.println("problem in fetching the proposals given by me "+ e);
			}
			
		}
		
		return proposals;
	}
	
	/**
	 * Update the interest for the user
	 */
	
	protected int updateInterest(int userId, String interest){
		int status = 0;
		query = "Update userdetails set interest = '" + interest + "' where userid = " + userId;
		System.out.println(query);
		try{
			
			status = st.executeUpdate(query);
			return status;
			
		}catch(Exception e){
			System.out.println("Cannot update the user interest " + e);
		}
		
		return status;
	}
	

	protected void close(){
		try{
			con.close();
		}catch(Exception e){
			System.out.println("Cannot close connection "+ e);
		}
	}
	
}
