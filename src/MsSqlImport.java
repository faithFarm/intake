import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.faithfarm.sms.domain.Intake;
import org.faithfarm.sms.domain.StudentHistory;
import org.faithfarm.sms.hibernate.data.CwtJobDao;
import org.faithfarm.sms.hibernate.data.CwtSupervisorDao;
import org.faithfarm.sms.hibernate.data.IntakeDao;
import org.faithfarm.sms.hibernate.data.StudentHistoryDao;

public class MsSqlImport {

	public static void main(String[] args) {
		// TODO Auto-generated method stub.
		writeImages();
		//updateIntake();
		//updateStatus();
	}
	
	private static void writeImages() {
		try {
			IntakeDao dao = new IntakeDao();
			CwtJobDao jDao = new CwtJobDao();
			CwtSupervisorDao sDao = new CwtSupervisorDao();
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://goalmd.com:1433;"
					+ "database=FaithFarm;" + "user=ffarmapps;" + "password=J35u5isaliv3";
			Connection con = DriverManager.getConnection(connectionUrl);
			
				
				// Create and execute an SQL statement that returns some data.
				StringBuffer sql = new StringBuffer("select studentID, Photo from FaithFarm.dbo.Students_Personal ");		
						//sql.append("a.StudentID, FirstName, MI, LastName, SSN, Address, City, State, ZipCode,");
						//sql.append("Phone, Contact_Name, Contact_Relationship, Contact_Phone, ReferredBy, DOB, Height, Weight, Eyes,");
						//sql.append("Hair, Ethnic_Background, Marital_Status, DriverLicense_IsValid, DriverLicense_State,");
						//sql.append("DriverLicense_Number, Supervisor, Job, Class, Area, Room, Bed, Archived, dateOfLastEntered, Photo ");
						//sql.append(" from FaithFarm.dbo.Students_Personal a inner join FaithFarm.dbo.Students_Status b");
						//sql.append(" on a.StudentID=b.StudentID; ");
						 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());
				
				System.out.println("Beginning....");
				int row=0;
				// Iterate through the data in the result set and display it.
				while (rs.next()) {
					row++;
					Long id = rs.getLong(1);
					Intake i= dao.find(id);
					/*
					i.setIntakeId(rs.getLong(1));
					i.setFirstname(rs.getString(2));
					i.setMi(rs.getString(3));
					i.setLastname(rs.getString(4));
					i.setSsn(rs.getString(5));
					i.setAddress(rs.getString(6));
					i.setCity(rs.getString(7));
					
					String state=rs.getString(8);
					if (state==null) state="";
					i.setState(state.toUpperCase());
					
					i.setZipcode(rs.getString(9));
					i.setReferredByPhone(rs.getString(10));
					i.setEmergencyContact(rs.getString(11));
					i.setEmergencryRelationship(rs.getString(12));
					i.setEmergencyPhone(rs.getString(13));
					i.setReferredBy(rs.getString(14));
					String dob= rs.getString(15);
					if (dob!=null)
						dob=convertDate(dob);
					i.setDob(dob);
					
					i.setHeight(rs.getString(16));
					i.setWeight(rs.getString(17));
					i.setEyeColor(rs.getString(18));
					i.setHairColor(rs.getString(19));
					i.setEthnicity(rs.getString(20));
					i.setMaritalStatus(rs.getString(21));
					i.setDlFlag(rs.getString(22));
					String dlState = rs.getString(23);
					if (dlState==null)
						dlState="";
					i.setDlState(dlState.toUpperCase());
					i.setDlNumber(rs.getString(24));
					
					
					//i.setSupervisorId(rs.getString(25));
					//i.setJobId(rs.getString(26));
					i.setClass_(rs.getString(27));
					i.setArea(rs.getString(28));
					i.setRoom(rs.getString(29));
					i.setBed(rs.getString(30));
					i.setArchivedFlag(rs.getString(31));
					String entryDate=rs.getString(32);
					if (entryDate!=null)
						entryDate=convertDate(entryDate);
					i.setEntryDate(entryDate);
					i.setCreatedBy("import");
					i.setCreationDate(Validator.getEpoch()+"");
					*/
					InputStream imgData = rs.getBinaryStream(2);
					if (imgData!=null) {
						byte[] bytes = IOUtils.toByteArray(imgData);
						i.setImageHeadshot(bytes);
					}
					
					//Intake intake = dao.find(id);
					//intake.setImageHeadshot(bytes);
					dao.update(i);
					
					if (row%500==0)
						System.out.println("Updated "+row+" Rows");
					//System.out.println (">"+i.getIntakeId()+","+i.getDob()+","+i.getEntryDate());
				}
			System.out.println("Operation Completed!");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				//System.exit(0);
			}
		
	}
	
	
	private static void updateStatus() {
		
		 StudentHistoryDao dao = new StudentHistoryDao();
		 List<Intake> list = dao.list();
		 System.out.println ("Beginning..");
		 for (Iterator iterator =
	    			list.iterator(); iterator.hasNext();){
			 StudentHistory i = (StudentHistory)iterator.next();
			 
			String beginDate= i.getBeginDate();
				try {
			 	if (beginDate!=null)
			 		beginDate=convertDate(beginDate);
				}
			 catch (Exception e) { beginDate=""; }
				i.setBeginDate(beginDate);
			 
			 String endDate=i.getEndDate();
			 try {
				if (endDate!=null)
					endDate=convertDate(endDate);
			 } catch (Exception e) { endDate=""; }
				i.setEndDate(endDate);
				
			dao.update(i);
				
		 }
		 System.out.println ("Completed");
	    			
	}
	
	
	private static void updateIntake() {
		
		 IntakeDao dao = new IntakeDao();
		 List<Intake> list = dao.list();
		 System.out.println ("Beginning..");
		 for (Iterator iterator =
	    			list.iterator(); iterator.hasNext();){
			 Intake i = (Intake)iterator.next();
			 
			 String state=i.getState();
				if (state==null) state="";
				i.setState(state.toUpperCase());
			
			 String dlState = i.getDlState();
				if (dlState==null)
					dlState="";
				i.setDlState(dlState.toUpperCase());
			 
			String dob= i.getDob();
				try {
			 	if (dob!=null)
					dob=convertDate(dob);
				}
			 catch (Exception e) { dob=""; }
				i.setDob(dob);
			 
			 String entryDate=i.getEntryDate();
			 try {
				if (entryDate!=null)
					entryDate=convertDate(entryDate);
			 } catch (Exception e) { entryDate=""; }
				i.setEntryDate(entryDate);
				
			dao.update(i);
				
		 }
		 System.out.println ("Completed");
	    			
	}
	
	private static void writeImage() {
		// Load the SQLServerDriver class, build the
		// connection string, and get a connection
		try {
		IntakeDao dao = new IntakeDao();
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String connectionUrl = "jdbc:sqlserver://goalmd.com:1433;"
				+ "database=FaithFarm;" + "user=ffarmapps;" + "password=J35u5isaliv3";
		Connection con = DriverManager.getConnection(connectionUrl);
		
			System.out.println("Connected.");

			// Create and execute an SQL statement that returns some data.
			String SQL = "SELECT  StudentId, Photo FROM dbo.Students_Personal where studentId=4884";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				Long id = rs.getLong(1);
				System.out.println(">"+id);
				InputStream imgData = rs.getBinaryStream(2);
				byte[] bytes = IOUtils.toByteArray(imgData);
				System.out.println("img"+id+".jpg="+bytes.length);
				writeFile("img"+id+".jpg",bytes);
				//Intake intake = dao.find(id);
				//intake.setImageHeadshot(bytes);			
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			//System.exit(0);
		}
	}
	
	private static void writeFile (String filename, byte[] bytes) {
	    
        try {
      
	    //convert array of bytes into file
	    FileOutputStream fileOuputStream = new FileOutputStream("C:\\development\\StudentImages\\"+filename); 
	    fileOuputStream.write(bytes);
	    fileOuputStream.close();
 
	    System.out.println("Done Writing File");
        }catch(Exception e){
            e.printStackTrace();
        }

	}
	
	private static String convertDate(String sdate) {
		String year = sdate.substring(0,4);
		String month = sdate.substring(5,7);
		String day = sdate.substring(8,10);
		return (month+"/"+day+"/"+year);
	}
	
	
}
