package com.example.myapplication;

import java.util.Date;

//all functions natively will have the following
//null constructor and main constructor
//getters
//setters
//print

//what else do we need

public class DataClasses
{
	//this will hold all the data for an Event
	public static class Event{
		String event_name; 
		String club_name; 
		String interest_name;
		Date date_of_event;
		String duration;
		String location_name;
		String event_desc;
		
		//default constructor
		public Event() {
			super();
			this.event_name = null; 
			this.club_name = null; 
			this.interest_name = null;
			this.date_of_event = null;
			this.duration = null;
			this.location_name = null;
			this.event_desc = null;
		}
		
		//constructor given all values in string format
		public Event(String event_name, String club_name, String interest_name, Date date_of_event, String duration,
				String location_name, String event_desc) {
			super();
			this.event_name = event_name;
			this.club_name = club_name;
			this.interest_name = interest_name;
			this.date_of_event = date_of_event;
			this.duration = duration;
			this.location_name = location_name;
			this.event_desc = event_desc;
		}
		
		//getters and setters
		public String getEvent_name() {
			return event_name;
		}
		public void setEvent_name(String event_name) {
			this.event_name = event_name;
		}
		public String getclub_name() {
			return club_name;
		}
		public void setclub_name(String club_name) {
			this.club_name = club_name;
		}
		public String getinterest_name() {
			return interest_name;
		}
		public void setinterest_name(String interest_name) {
			this.interest_name = interest_name;
		}
		public Date getDate_of_event() {
			return date_of_event;
		}
		public void setDate_of_event(Date date_of_event) {
			this.date_of_event = date_of_event;
		}
		public String getDuration() {
			return duration;
		}
		public void setDuration(String duration) {
			this.duration = duration;
		}
		public String getlocation_name() {
			return location_name;
		}
		public void setlocation_name(String location_name) {
			this.location_name = location_name;
		}
		public String getEvent_desc() {
			return event_desc;
		}
		public void setEvent_desc(String event_desc) {
			this.event_desc = event_desc;
		}
		
		
		//native print for events
		public void printEvent() {
			System.out.printf("%s, %s, %s, %s, %s, %s, %s\n", this.event_name, this.club_name, this.interest_name, 
					this.date_of_event, this.duration, this.location_name, this.event_desc);
		}
			
	}
	
	//this will hold all the data for a User
	public static class User{
		String name_f; 
		String m_number;
		String email;
		String pw;
		String major;
		String interest_1;
		String interest_2;
		String interest_3;
		String club_sk_1;
		String club_sk_2;
		String club_sk_3;
		String club_sk_4;
		String club_sk_5;
		String club_sk_6;
		String club_sk_7;
		
		//default constructor
		public User() {
			super();
			this.name_f = null; 
			this.m_number = null;
			this.email = null;
			this.pw = null;
			this.major = null;
			this.interest_1 = null;
			this.interest_2 = null;
			this.interest_3 = null;
			this.club_sk_1 = null;
			this.club_sk_2 = null;
			this.club_sk_3 = null;
			this.club_sk_4 = null;
			this.club_sk_5 = null;
			this.club_sk_6 = null;
			this.club_sk_7 = null;
		}
		
		//constructor given all values in string format
		public User(String name_f, String m_number, String email, String pw, String major, String interest_1,
				String interest_2, String interest_3, String club_sk_1, String club_sk_2, String club_sk_3, String club_sk_4,
				String club_sk_5, String club_sk_6, String club_sk_7) {
			super();
			this.name_f = name_f;
			this.m_number = m_number;
			this.email = email;
			this.pw = pw;
			this.major = major;
			this.interest_1 = interest_1;
			this.interest_2 = interest_2;
			this.interest_3 = interest_3;
			this.club_sk_1 = club_sk_1;
			this.club_sk_2 = club_sk_2;
			this.club_sk_3 = club_sk_3;
			this.club_sk_4 = club_sk_4;
			this.club_sk_5 = club_sk_5;
			this.club_sk_6 = club_sk_6;
			this.club_sk_7 = club_sk_7;
		}

		//getters and setters
		public String getName_f() {
			return name_f;
		}
		public void setName_f(String name_f) {
			this.name_f = name_f;
		}
		public String getM_number() {
			return m_number;
		}
		public void setM_number(String m_number) {
			this.m_number = m_number;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPw() {
			return pw;
		}
		public void setPw(String pw) {
			this.pw = pw;
		}
		public String getMajor() {
			return major;
		}
		public void setMajor(String major) {
			this.major = major;
		}
		public String getInterest_1() {
			return interest_1;
		}
		public void setInterest_1(String interest_1) {
			this.interest_1 = interest_1;
		}
		public String getInterest_2() {
			return interest_2;
		}
		public void setInterest_2(String interest_2) {
			this.interest_2 = interest_2;
		}
		public String getInterest_3() {
			return interest_3;
		}
		public void setInterest_3(String interest_3) {
			this.interest_3 = interest_3;
		}
		public String getClub_sk_1() {
			return club_sk_1;
		}
		public void setClub_sk_1(String club_sk_1) {
			this.club_sk_1 = club_sk_1;
		}
		public String getClub_sk_2() {
			return club_sk_2;
		}
		public void setClub_sk_2(String club_sk_2) {
			this.club_sk_2 = club_sk_2;
		}
		public String getClub_sk_3() {
			return club_sk_3;
		}
		public void setClub_sk_3(String club_sk_3) {
			this.club_sk_3 = club_sk_3;
		}
		public String getClub_sk_4() {
			return club_sk_4;
		}
		public void setClub_sk_4(String club_sk_4) {
			this.club_sk_4 = club_sk_4;
		}
		public String getClub_sk_5() {
			return club_sk_5;
		}
		public void setClub_sk_5(String club_sk_5) {
			this.club_sk_5 = club_sk_5;
		}
		public String getClub_sk_6() {
			return club_sk_6;
		}
		public void setClub_sk_6(String club_sk_6) {
			this.club_sk_6 = club_sk_6;
		}
		public String getClub_sk_7() {
			return club_sk_7;
		}
		public void setClub_sk_7(String club_sk_7) {
			this.club_sk_7 = club_sk_7;
		}
		
		//Native print for User
		public void printUser() {
			System.out.printf("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s\n", 
					this.name_f, this.m_number, this.email, this.pw, this.major, this.interest_1, 
					this.interest_2, this.interest_3, this.club_sk_1, this.club_sk_2, this.club_sk_3, this.club_sk_4, 
					this.club_sk_5, this.club_sk_6, this.club_sk_7);
		}	
	}
	
	//holds locations data
	public static class Location{
		String location_name;
		String location_sk;
		double latitude;
		double longitude;
		
		
		
		
		//constructor given all fields as strings
		public Location(String location_name, String location_sk, double latitude, double longitude) {
			super();
			this.location_name = location_name;
			this.location_sk = location_sk;
			this.latitude = latitude;
			this.longitude = longitude;
		}
		
		//default constructor
		public Location() {
			super();
			this.location_name = null;
			this.location_sk = null;
			this.latitude = 0.0;
			this.longitude = 0.0;
		}

		
		//native print for Locations
		public void printLocation() {
			System.out.printf("%s, %s, %f, %f\n", this.location_name, this.location_sk, this.latitude, this.longitude);
		}

		//getters and setters
		public String getlocation_name() {
			return location_name;
		}

		public void setlocation_name(String location_name) {
			this.location_name = location_name;
		}

		public String getlocation_sk() {
			return location_sk;
		}

		public void setlocation_sk(String location_sk) {
			this.location_sk = location_sk;
		}

		public double getLatitude() {
			return latitude;
		}

		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}

		public double getLongitude() {
			return longitude;
		}

		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}
		
		
	}	
	
	
}
