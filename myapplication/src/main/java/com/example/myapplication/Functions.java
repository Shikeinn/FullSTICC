package com.example.myapplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Functions {
	static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
	
	//push a User to the database, given User object and Connection
	public static void pushUsertoDatabase(DataClasses.User x, Connection dbConnection) throws SQLException {
		
		Statement st = dbConnection.createStatement();
		st.executeQuery("use mtconnect");
		
		String query = "{create_user(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		java.sql.CallableStatement stmt = dbConnection.prepareCall(query);
		
		stmt.setString(1, x.name_f);
		stmt.setString(2, x.m_number);
		stmt.setString(3, x.email);
		stmt.setString(4, x.pw);
		stmt.setString(5, x.major);
		stmt.setString(6, x.major);
		stmt.setString(7, x.interest_1);
		stmt.setString(8, x.interest_2);
		stmt.setString(9, x.interest_3);
		stmt.setString(10, x.club_sk_1);
		stmt.setString(11, x.club_sk_2);
		stmt.setString(12, x.club_sk_3);
		stmt.setString(13, x.club_sk_4);
		stmt.setString(14, x.club_sk_5);
		stmt.setString(15, x.club_sk_6);
		stmt.setString(16, x.club_sk_7);
		
		stmt.executeUpdate();
	}
	
	//push an Event to the database, given Event object and Connection
	public static void pushEventtoDatabase(DataClasses.Event x, Connection dbConnection) throws SQLException {
		
		Statement st = dbConnection.createStatement();
		st.executeQuery("use mtconnect");
		
		String query = "{create_event(?, ?, ?, ?, ?, ?, ?)}";
		java.sql.CallableStatement stmt = dbConnection.prepareCall(query);
		stmt.setString(1, x.event_name);
		stmt.setString(2, x.club_name);
		stmt.setString(3, x.interest_name);
		stmt.setDate(4, (java.sql.Date) x.date_of_event);
		stmt.setString(5, x.duration);
		stmt.setString(6, x.location_name);
		stmt.setString(7, x.event_desc);
		
		stmt.executeUpdate();
		
	}
	
	/*
	//push a location to the database, given Location object and Connection
	public static void pushLoctoDatabase(DataClasses.Location x, Connection dbConnection) throws SQLException {
		Statement st = dbConnection.createStatement();
		st.executeQuery("use mtconnect");
		
		st.executeUpdate("INSERT INTO location_m(location_name, location_name, lat, long)" + "VALUE ('"+x.location_name+"','"+x.location_name+"', '"+x.latitude+"', '"+x.longitude+"')");
		
	}
	*/
	
	
	//returns a User record if username and email match the input
	public static DataClasses.User pullUserData(String mNumber, String password, Connection dbConnection) throws SQLException {
		
		Statement st = dbConnection.createStatement();
		st.executeQuery("use mtconnect");
		
		ResultSet results = st.executeQuery("SELECT * FROM user_m WHERE m_number ='"+mNumber+"' AND pw ='"+password+ "'");
                if(results.next() == false) {
                return null;
                }
				
		String m_test = results.getString("m_number");
		String pass_test = results.getString("pw");
		
		if( (m_test.equals(mNumber) ) && (pass_test.equals(password) ) )
		{	
			DataClasses.User pulledUser = new DataClasses.User(
				results.getString("name_f"),
				results.getString("m_number"), 
				results.getString("email"), 
				results.getString("pw"), 
				results.getString("major"), 
				results.getString("interest_1"), 
				results.getString("interest_2"),
				results.getString("interest_3"), 
				results.getString("club_sk_1"), 
				results.getString("club_sk_2"), 
				results.getString("club_sk_3"), 
				results.getString("club_sk_4"), 
				results.getString("club_sk_5"), 
				results.getString("club_sk_6"), 
				results.getString("club_sk_7"));
				
				return pulledUser;
		}
		else {return null;}
	}

	
	
	//returns an Event record if event_name and date_of_Event match the input
	public static DataClasses.Event pullEventData(String event_name, String eventDate, Connection dbConnection) throws SQLException {
		
		Statement st = dbConnection.createStatement();
		st.executeQuery("use mtconnect");	
	
		ResultSet results = st.executeQuery("SELECT * FROM event_m WHERE event_name ="+event_name+" AND date_of_event="+eventDate);
		results.next();
		if( (event_name.equalsIgnoreCase(results.getString("event_name"))) && (eventDate.equalsIgnoreCase(results.getString("date_of_event")) ) )
		{	
			DataClasses.Event pulledEvent = new DataClasses.Event(
				results.getString("event_name"),
				results.getString("club_name"), 
				results.getString("interest_name"),
				results.getDate("date_of_event"), 
				results.getString("duration"), 
				results.getString("location_name"), 
				results.getString("event_desc"));				
				return pulledEvent;
		}
		else {return null;}
		
	}
		
	//returns a Location record if location_name match the inputs
	public static ArrayList<Double> pullLocData(String locationName, Connection dbConnection) throws SQLException
	{
		Statement st = dbConnection.createStatement();
		st.executeQuery("use mtconnect");
		String query = "{get_club_names()}";
		java.sql.CallableStatement stmt = dbConnection.prepareCall(query);
		
		Double latitude = 0.0;
		Double longitude = 0.0;

		ArrayList<Double> locationArrayList = new ArrayList<Double>();
		ResultSet results = stmt.executeQuery();
		while(results.next() ) {
			latitude = results.getDouble("latitude");
			longitude = results.getDouble("longitude");
			
			locationArrayList.add(latitude);
			locationArrayList.add(longitude);
		}
		return locationArrayList;
		
	}
	
	
	
	//returns an ArrayList of Users
	public static ArrayList<DataClasses.User> pullUserArray(Connection dbConnection) throws SQLException {
		Statement st = dbConnection.createStatement();
		st.executeQuery("use mtconnect");
		
		String name_f = "";
		String m_number = "";
		String email = "";
		String pw = "";
		String major = "";
		String interest_1 = "";
		String interest_2 = "";
		String interest_3 = "";
		String club_sk_1 = "";
		String club_sk_2 = "";
		String club_sk_3 = "";
		String club_sk_4 = "";
		String club_sk_5 = "";
		String club_sk_6 = "";
		String club_sk_7 = "";
		
		ArrayList<DataClasses.User> userArrayList = new ArrayList<DataClasses.User>();
		
		ResultSet results = st.executeQuery("SELECT * FROM user_m");
		while( results.next() ) {
			name_f = results.getString("name_f");
			m_number = results.getString("m_number");
			email = results.getString("email");
			pw = results.getString("pw");
			major = results.getString("major");
			interest_1 = results.getString("interest_1");
			interest_2 = results.getString("interest_2");
			interest_3 = results.getString("interest_3");
			club_sk_1 = results.getString("club_sk_1");
			club_sk_2 = results.getString("club_sk_2");
			club_sk_3 = results.getString("club_sk_3");
			club_sk_4 = results.getString("club_sk_4");
			club_sk_5 = results.getString("club_sk_5");
			club_sk_6 = results.getString("club_sk_6");
			club_sk_7 = results.getString("club_sk_7");
			
			userArrayList.add( new DataClasses.User(name_f, m_number, email, pw, major, interest_1, interest_2,
						   interest_3, club_sk_1, club_sk_2, club_sk_3, club_sk_4, club_sk_5, club_sk_6, club_sk_7));
				

		}
		
		return userArrayList;
	}
	
	//returns an ArrayList of Events
	public static ArrayList<DataClasses.Event> pullEventArray(Connection dbConnection) throws SQLException {
		Statement st = dbConnection.createStatement();
		st.executeQuery("use mtconnect");
		
		String event_name_db = "";
		String club_name = "";
		String interest_name = "";
		java.sql.Date date_of_event;
		String duration = "";
		String location_name = "";
		String event_desc = "";
		ArrayList<DataClasses.Event> eventArrayList = new ArrayList<DataClasses.Event>();
		ResultSet results = st.executeQuery("SELECT * FROM event_m");
		
		//grab all the data and add it to an event array list
		while(results.next()) {
			event_name_db = results.getString("event_name");
			club_name = results.getString("club_name");
			interest_name = results.getString("interest_name");
			date_of_event = results.getDate("date_of_event");
			duration = results.getString("duration");
			location_name = results.getString("location_name");
			event_desc = results.getString("event_desc");
			//adds the event to the event array list
			eventArrayList.add(new DataClasses.Event(event_name_db, club_name, interest_name,
			          date_of_event, duration, location_name, event_desc));
		}
		
		return eventArrayList; //returns the event
	}
	
	//returns an ArrayList of Locations
	public static ArrayList<DataClasses.Location> pullLocArray(Connection dbConnection) throws SQLException {
	
		Statement st = dbConnection.createStatement();
		st.executeQuery("use mtconnect");
		
		String location_name = "";
		Double latitude = 0.0;
		Double longitude = 0.0;
		
		ArrayList<DataClasses.Location> eventLocList = new ArrayList<DataClasses.Location>();
		
		ResultSet results = st.executeQuery("SELECT * FROM location_m");
		
		while( results.next() ) {

			location_name = results.getString("location_name");
			location_name = results.getString("location_name");
			latitude = results.getDouble("latitude");
			longitude = results.getDouble("longitude");
			
				eventLocList.add(new DataClasses.Location(location_name, location_name, latitude, longitude));
			}
			
		return eventLocList; //return list of all locations
		}

	
	
	//used to delete User records by their name_f and password
	public static void deleteUserRecord(String mNumber, String password, Connection dbConnection) throws SQLException {
		Statement st = dbConnection.createStatement();
		st.executeQuery("use mtconnect");
		String sql = "delete from user_m where m_number = '"+mNumber+"' AND pw = '"+password+"' ";
		
		st.executeUpdate(sql);
	}
	
	//used to delete Event records by their event_name and date_of_event
	public static void deleteEventRecord(String event_name, String date_of_event, Connection dbConnection) throws SQLException {
		Statement st = dbConnection.createStatement();
		st.executeQuery("use mtconnect");
		String sql = "delete from event_m where event_name = '"+event_name+"' AND date_of_event = '"+date_of_event+"' ";
		
		st.executeUpdate(sql);
	}
	
	//used to delete location records by their location_name
	public static void deleteLocRecord(String locationName, Connection dbConnection) throws SQLException {
		Statement st = dbConnection.createStatement();
		st.executeQuery("use mtconnect");
		String sql = "delete from location_m where location_name = '"+locationName+"' ";
		
		st.executeUpdate(sql);
	}
	

	
	//pull 10 events by location
	//returns an ArrayList of Events
	public static ArrayList<DataClasses.Event> pullDaysEvents(String location, Connection dbConnection) throws SQLException {
		Statement st = dbConnection.createStatement();
		st.executeQuery("use mtconnect");
		String query = "{Call pull_10_events(?)}";
		java.sql.CallableStatement stmt = dbConnection.prepareCall(query);
		stmt.setInt(location, 1);

		String event_name_db = "";
		String club_name = "";
		String interest_name = "";
		Date date_of_event;
		String duration = "";
		String location_name = "";
		String event_desc = "";
		ArrayList<DataClasses.Event> eventArrayList = new ArrayList<DataClasses.Event>();
		ResultSet results = stmt.executeQuery();
		
		//grab all the data and add it to an event array list
		while(results.next() ) {
			event_name_db = results.getString("event_name");
			club_name = results.getString("club_name");
			interest_name = results.getString("interest_name");
			date_of_event = results.getDate("date_of_event");
			duration = results.getString("duration");
			location_name = results.getString("location_name");
			event_desc = results.getString("event_desc");
			//only add if it matches the currentDate
			eventArrayList.add(new DataClasses.Event(event_name_db, club_name, interest_name,
			          date_of_event, duration, location_name, event_desc));
		}
		
		return eventArrayList; //returns the event
		
	}

	public static ArrayList<String> get_club_names(Connection dbConnection) throws SQLException{
		Statement st = dbConnection.createStatement();
		st.executeQuery("use mtconnect");
		String query = "{get_club_names()}";
		java.sql.CallableStatement stmt = dbConnection.prepareCall(query);
		
		String club_name = "";
		ArrayList<String> clubArrayList = new ArrayList<String>();
		
		ResultSet results = stmt.executeQuery();
		while(results.next() ) {
			club_name = results.getString("club_name");
			
			clubArrayList.add(club_name);
		}
		return clubArrayList;
	}
	
	public static ArrayList<String> get_location_names(Connection dbConnection) throws SQLException{
		Statement st = dbConnection.createStatement();
		st.executeQuery("use mtconnect");
		String query = "{get_location_names()}";
		java.sql.CallableStatement stmt = dbConnection.prepareCall(query);
		
		String location_name = "";
		ArrayList<String> locationArrayList = new ArrayList<String>();
		
		ResultSet results = stmt.executeQuery();
		while(results.next() ) {
			location_name = results.getString("location_name");
			
			locationArrayList.add(location_name);
		}
		return locationArrayList;
	}
	
	public static ArrayList<String> get_event_type_names(Connection dbConnection) throws SQLException{
		Statement st = dbConnection.createStatement();
		st.executeQuery("use mtconnect");
		String query = "{get_event_type_names()}";
		java.sql.CallableStatement stmt = dbConnection.prepareCall(query);
		
		String event_type_name = "";
		ArrayList<String> eventTypeArrayList = new ArrayList<String>();
		
		ResultSet results = stmt.executeQuery();
		while(results.next() ) {
			event_type_name = results.getString("event_type_name");
			
			eventTypeArrayList.add(event_type_name);
		}
		return eventTypeArrayList;
	}
	
	public static ArrayList<String> get_interest_names(Connection dbConnection) throws SQLException{
		Statement st = dbConnection.createStatement();
		st.executeQuery("use mtconnect");
		String query = "{get_interest_names()}";
		java.sql.CallableStatement stmt = dbConnection.prepareCall(query);
		
		String interest_name = "";
		ArrayList<String> interestArrayList = new ArrayList<String>();
		
		ResultSet results = stmt.executeQuery();
		while(results.next() ) {
			interest_name = results.getString("interest_name");
			
			interestArrayList.add(interest_name);
		}
		return interestArrayList;
	}
	

	
	
	//contains examples and opens database
	public static void main(String[] args) throws SQLException, ParseException {
		
		Connection dbConnection = MySqlCon.connect();
		//create DataClass examples
		
		//String dateString = "2019-12-12";
		
		//Date date = formatter.parse(dateString);
		
		//DataClasses.Event event = new DataClasses.Event("CSC meeting", "0", "0", date ,"1 hour", "0", "Meet Your Professors: Dr. Phillips");
		//DataClasses.User user = new DataClasses.User("XxGam3r_SW4GxX", "m1000011", "GamerBoi@gamer.com", "Nixon4Prez", "Early Childhood Education", "1", "2","3", "0", "1", "2", "-1", "-1", "-1", "-1");
		//DataClasses.Location Loc = new DataClasses.Location("2", "HONR"); 
		
		
		//push DataClass examples
		//Functions.pushUsertoDatabase(user, dbConnection);
		//Functions.pushEventtoDatabase(event, dbConnection);
		//Functions.pushLoctoDatabase(Loc, dbConnection);
		
		
		//delete record example
		//Functions.deleteEventRecord("CSC meeting", "Oct 25, 2019; 12:30pm", dbConnection);
		//Functions.deleteUserRecord("XxGam3r_SW4GxX", "Nixon4Prez", dbConnection);
		//Functions.deleteLocRecord("HONR", dbConnection);
		
		
		//pull event example
		//Event pulled2 = pullEventData("CSC meeting", "Oct 25, 2019; 12:30pm", dbConnection);
		//DataClasses.User pulled2 = pullUserData("M100000","password", dbConnection);
		//DataClasses.Location pulled2 = pullLocData("KOM", dbConnection);
		
		
		//print record example
		/*if(pulled2 != null) {pulled2.printUser();}
		else {
			System.out.println("user not found");
		}
		*/
		
		//print array list of Events example
		/*
		ArrayList<DataClasses.Event> eventList = pullEventArray(dbConnection);
		for (int x=0; x<eventList.size(); x++) {
			//System.out.println(x);
			(eventList.get(x)).printEvent();
		}
		*/
		
		
		//print array list of Users example
		/*
		ArrayList<DataClasses.User> userList = pullUserArray(dbConnection);
		for (int x=0; x<userList.size(); x++) {
			//System.out.println(x);
			(userList.get(x)).printUser();
		}
		*/
	
		
		
		/*ArrayList<DataClasses.Location> locList = pullLocArray(dbConnection);
		
		for (int x=0; x<locList.size(); x++) {
			//System.out.println(x);
			(locList.get(x)).printLocation();
		}*/
	
	
		dbConnection.close();
	}
	
}




