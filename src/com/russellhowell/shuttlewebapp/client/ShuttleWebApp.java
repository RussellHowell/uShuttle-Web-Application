package com.russellhowell.shuttlewebapp.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ListBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ShuttleWebApp implements EntryPoint {
public int StartValue;
public int EndValue;
public String Arrive;
public int Meridiem;
	public void onModuleLoad(){
		//Window.alert("uShuttle Prototype!");
		
	    //timebox = new UTCTimeBox(DateTimeFormat.getFormat("hh:mm a"));
		
		//Declare new ListBox for start/end locations and add items
		final ListBox startLocBox = new ListBox();
		final ListBox endLocBox = new ListBox();
		final ListBox timeHour = new ListBox();
		final ListBox timeMinute = new ListBox();
		final ListBox timeMeridiem = new ListBox();
		Button findTrip = new Button("uShuttle!");
		
		//Handle Click Event 
		findTrip.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				StartValue = startLocBox.getSelectedIndex();
				EndValue = endLocBox.getSelectedIndex();
				Arrive = timeHour.getValue(timeHour.getSelectedIndex()) + 
						timeMinute.getValue(timeMinute.getSelectedIndex());
				Meridiem = timeMeridiem.getSelectedIndex();
				Shuttle findShuttle = new Shuttle(StartValue, EndValue, Arrive, Meridiem);
				Window.alert(findShuttle.Result_String);
			}
		});
		
		
		startLocBox.addItem("South Campus");
		startLocBox.addItem("North Ithan");
		startLocBox.addItem("Falvey Library");
		startLocBox.addItem("Tolentine Hall");
		startLocBox.addItem("Law School");
		startLocBox.addItem("West Campus");
		
		endLocBox.addItem("South Campus");
		endLocBox.addItem("North Ithan");
		endLocBox.addItem("Falvey Library");
		endLocBox.addItem("Tolentine Hall");
		endLocBox.addItem("Law School");
		endLocBox.addItem("West Campus");
		
		//Populate Hour Box
		for(int i=1; i<=12; i++){
			timeHour.addItem(""+ i,"0"+i);
		}
		
		timeMinute.addItem("00", "00");
		timeMinute.addItem("10", "10");
		timeMinute.addItem("20","20");
		timeMinute.addItem("30", "30");
		timeMinute.addItem("40", "40");
		timeMinute.addItem("50","50");
		
		timeMeridiem.addItem("AM");
		timeMeridiem.addItem("PM");
		
		//Set all items visible
		startLocBox.setVisibleItemCount(6);
		endLocBox.setVisibleItemCount(6);
		
		startLocBox.setStyleName("startLocLB");
		
		RootPanel.get("locBox").add(startLocBox);
		
		endLocBox.setStyleName("endLocLB");
		
		RootPanel.get("locBox").add(endLocBox);
		
		RootPanel.get("timebox").add(timeHour);
		
		RootPanel.get("timebox").add(timeMinute);
		
		RootPanel.get("timebox").add(timeMeridiem);
		
		RootPanel.get("button").add(findTrip);

	}
}

