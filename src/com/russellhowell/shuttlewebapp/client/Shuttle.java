package com.russellhowell.shuttlewebapp.client;

import com.google.gwt.user.client.Window;

//Created by Russell Howell
//Last Modified 3/17/2015
//************************************//
//									  //
//	Helper class holding methods	  //
// for the Timetable.java driver class//
//									  //
//************************************//
//Modified for use in the Shuttle Web application

public class Shuttle {
	
protected static int Departure;
protected static int Arrival;
protected static int Meridiem;
private static int tripType;
protected static String Arrival_String;
protected static String Departure_String;
protected static String Result_String;
protected static boolean tripFound;

public Shuttle(int start_loc, int end_loc, int timeHourVal, String timeMinuteVal, int meridiem, int tripTypeFlag) {
		
		Meridiem = meridiem;
		tripType = tripTypeFlag;
		
		int time_int = formatTime(timeHourVal, timeMinuteVal);
			ShuttleTimetable shuttle_info = new ShuttleTimetable(tripType);
			findTrip(start_loc, end_loc, time_int, shuttle_info);
		formatOutput();
	}
	

		

	public String toString(){

		return Result_String;
		
	}

	
	private void formatOutput() {
		String arrival_string = Integer.toString(Arrival);
		String departure_string = Integer.toString(Departure);
		
		if(tripFound){
		int arrivalHour, departureHour; //these values hold the the digits of the time before the colon in for the arrival and departure time
		
		if (Arrival < 1000){ 		//A problem occurs if the number before the colon is only one digit, (example being the '9' in '9:30' 
			arrivalHour = Integer.parseInt(arrival_string.substring(0,1));		//these conditionals take care of that case by changing the index
			arrival_string = arrival_string.substring(1);						//of the substring if necessary
			}else{arrivalHour = Integer.parseInt(arrival_string.substring(0,2));
				 arrival_string = arrival_string.substring(2);
						 } 		
		if (Departure < 1000){															
			departureHour = Integer.parseInt(departure_string.substring(0,1));	
		departure_string = departure_string.substring(1);
			}else{departureHour = Integer.parseInt(departure_string.substring(0,2));
				departure_string= departure_string.substring(2);}	
																					
		arrival_string = convertTo12Hr(arrivalHour, arrival_string);
		 departure_string = convertTo12Hr(departureHour, departure_string);
		
		
		Result_String = "<h3> Departing from " + Departure_String + ": " + departure_string +
				"<br>Arrival at " + Arrival_String + ": " + arrival_string + "</h3>";
		}else {
			Result_String = "<h3>No trip found, please try a different time</h3>";
		}
	}
	
	private String convertTo12Hr(int hr, String min) {
		String meridiem = null;
		if(hr <12){
			meridiem = "AM";
		}
		else if(hr >=13 && hr < 24){
			hr = hr - 12;
			meridiem = "PM";
		}
		else if(hr >= 25){
			meridiem = "AM";
			hr = hr - 24;
		}
		else if(hr == 12 ){
			meridiem = "PM";
		}
		else if(hr ==24){
			hr = 12;
			meridiem = "AM";
		}
		
		return hr + ":" + min + " " + meridiem;
	}




	private static void findTrip(int start_loc, int end_loc, int time_int, ShuttleTimetable shuttle_info) {
		
		//This switch case finds the arrival time based on 
		//the "arrive by" and destination parameters set by the user
		
		/* tripType: OnCampus			: 0
		 * 			 On Campus Saturday : 1
		 * 			 Off Campus 		: 2
		 * 			 Main Line			: 3
		 * 			 KOP Mall			: 4
		 */
		switch(end_loc){
			
			case 0:
				Arrival = findInArray(time_int, shuttle_info.loc0);
				Arrival_String = shuttle_info.s0;	
				break;
			case 1:
				Arrival = findInArray(time_int, shuttle_info.loc1);
				Arrival_String = shuttle_info.s1;
				break;
			case 2:
				Arrival = findInArray(time_int, shuttle_info.loc2);
				Arrival_String = shuttle_info.s2;
				break;
			case 3:
				Arrival = findInArray(time_int, shuttle_info.loc3);
				Arrival_String = shuttle_info.s3;
				break;
				
			case 4:
				Arrival = findInArray(time_int, shuttle_info.loc4);
				Arrival_String = shuttle_info.s4;
				break;
				 
			case 5:
				Arrival = findInArray(time_int, shuttle_info.loc5);
				Arrival_String = shuttle_info.s5;
				break;		
			case 6: 
				Arrival = findInArray(time_int, shuttle_info.loc6);
				Arrival_String = shuttle_info.s6;
				break;
				
		}
		
		//This switch case finds the departure time based on 
		//the arrival time and departure location parameter set by the user
		switch(start_loc){
					
		case 0:
			Departure = findInArray(Arrival, shuttle_info.loc0);
			Departure_String = shuttle_info.s0;	
			break;
		case 1:
			Departure = findInArray(Arrival, shuttle_info.loc1);
			Departure_String = shuttle_info.s1;
			break;
		case 2:
			Departure = findInArray(Arrival, shuttle_info.loc2);
			Departure_String = shuttle_info.s2;
			break;
		case 3:
			Departure = findInArray(Arrival, shuttle_info.loc3);
			Departure_String = shuttle_info.s3;
			break;
			
		case 4:
			Departure = findInArray(Arrival, shuttle_info.loc4);
			Departure_String = shuttle_info.s4;
			break;
			 
		case 5:
			Departure= findInArray(Arrival, shuttle_info.loc5);
			Departure_String = shuttle_info.s5;
			break;		
		case 6: 
			Departure = findInArray(Arrival, shuttle_info.loc6);
			Departure_String = shuttle_info.s6;
			break;
				
				}
	}

	//this method finds the closest lower value in the passed array
	private static int findInArray(int time_int, int[] timeArray) {
		int i = 0;
		int result=0;
		
		
	try{
		outerloop:
		while(i<= timeArray.length + 1){
			
			if(timeArray[i]>time_int){
				result = timeArray[i-1];
				tripFound = true;
				break outerloop;
			}
			else if (timeArray[i]==time_int){
				result = timeArray[i];
				tripFound = true;
				break outerloop;
			}else if(i + 1 == timeArray.length){
				result = timeArray[i];
				tripFound = false;
				break outerloop;
			}//end of if - else
			++i;
		}//end of while loop
	}catch(Exception e){
							tripFound = false; 
						}
	
		return result;
	}


private static int formatTime(int hour, String minute) {
		

		switch (Meridiem){
			case 0: //time is AM
				if (hour < 3){//Early morning
				hour = hour + 24;
				}else if (hour ==12){
					hour = 24; //make 00:00 -> 24
				}break;
			case 1: //time is PM
				if (hour == 12){
					//Do nothing
				}else{
					hour = hour + 12; //Change to 24 hour format
				}break;
		
		}
				
				String tempString = Integer.toString(hour);
				int arrive_before = Integer.parseInt(tempString + minute);
		
		return arrive_before;
	
	}//End of method
	
	
}
