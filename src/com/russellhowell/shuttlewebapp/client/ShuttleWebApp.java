package com.russellhowell.shuttlewebapp.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ListBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class ShuttleWebApp implements EntryPoint {
public int StartValue;
public int EndValue;
public int timeHourVal;
public String timeMinuteVal;
public int Meridiem;
public int tripTypeFlag = 0;
ListBox startLocBox = null;
ListBox endLocBox = null;
	
public void onModuleLoad(){
		
		//Declare new ListBox for start/end locations and add items and trip type
		final ListBox tripType = new ListBox();
		startLocBox = new ListBox();
		endLocBox = new ListBox();
		final ListBox timeHour = new ListBox();
		final ListBox timeMinute = new ListBox();
		final ListBox timeMeridiem = new ListBox();
		Button findTrip = new Button("uShuttle!");
		final HTML tripLabel = new HTML();
		Grid tripGrid = new Grid(2,2);
		
		
		//Handle Trip Type Change
		tripType.addChangeHandler(new ChangeHandler(){
			@Override
			public void onChange(ChangeEvent event) {
			startLocBox.clear();
			endLocBox.clear();
			populateLocations(tripType.getSelectedIndex());
			}
		});
		
		
		
		//Handle Click Event 
		findTrip.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				StartValue = startLocBox.getSelectedIndex();
				EndValue = endLocBox.getSelectedIndex();
				timeHourVal = 1 + timeHour.getSelectedIndex();
				timeMinuteVal = timeMinute.getValue(timeMinute.getSelectedIndex());
				Meridiem = timeMeridiem.getSelectedIndex();
				@SuppressWarnings("unused")
				Shuttle findShuttle = new Shuttle(StartValue, EndValue, timeHourVal, timeMinuteVal, Meridiem, tripType.getSelectedIndex());
				if(startLocBox.getSelectedIndex() == -1 || endLocBox.getSelectedIndex() == -1){tripLabel.setHTML("<h3>Please select a starting location and a destination</h3>");
				}else
				{tripLabel.setHTML(Shuttle.Result_String);}
				                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
			}
		});
		
		tripType.addItem("On Campus - Weekday");
		tripType.addItem("On Campus - Saturday Night");
		tripType.addItem("Off Campus - Weekday");
		tripType.addItem("Main Line - Saturday");
		tripType.addItem("King of Pussia Mall - Friday/Saturday");
		
		populateLocations(0); //Default setup for tripType ListBox (On-Campus)
		
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
		tripType.setVisibleItemCount(1);
		tripGrid.setText(0,0,"Start Location");
		tripGrid.setText(0, 1, "End Location");
		tripGrid.setWidget(1,0, startLocBox);
		tripGrid.setWidget(1, 1, endLocBox);
		
		//startLocBox.setStyleName("startLocLB");
		
		//RootPanel.get("locBox").add(startLocBox);
		
		tripGrid.setStyleName("center");
		
		//RootPanel.get("locBox").add(endLocBox);
		
		RootPanel.get("tripgrid").add(tripGrid);
		
		RootPanel.get("tripTypeBox").add(tripType);
		
		RootPanel.get("timebox").add(timeHour);
		
		RootPanel.get("timebox").add(timeMinute);
		
		RootPanel.get("timebox").add(timeMeridiem);
		
		RootPanel.get("button").add(findTrip);
		
		RootPanel.get("result_string").add(tripLabel);

	}

private void populateLocations(int tripTypeIndex){
	
	switch (tripTypeIndex){
	case 0:
		startLocBox.addItem("South Campus");
		startLocBox.addItem("North Ithan");
		startLocBox.addItem("Falvey Library");
		startLocBox.addItem("Tolentine Hall");
		startLocBox.addItem("Law School");
		startLocBox.addItem("West Campus");
		startLocBox.setVisibleItemCount(6);
		
		endLocBox.addItem("South Campus");
		endLocBox.addItem("North Ithan");
		endLocBox.addItem("Falvey Library");
		endLocBox.addItem("Tolentine Hall");
		endLocBox.addItem("Law School");
		endLocBox.addItem("West Campus");
		endLocBox.setVisibleItemCount(6);
		break;
	case 1:
		startLocBox.addItem("Pike Lot");
		startLocBox.addItem("South Campus");
		startLocBox.addItem("North Ithan");
		startLocBox.addItem("Falvey Library");
		startLocBox.addItem("Tolentine Hall");
		startLocBox.addItem("Law School");
		startLocBox.addItem("West Campus");
		startLocBox.setVisibleItemCount(7);
		
		endLocBox.addItem("Pike Lot");
		endLocBox.addItem("South Campus");
		endLocBox.addItem("North Ithan");
		endLocBox.addItem("Falvey Library");
		endLocBox.addItem("Tolentine Hall");
		endLocBox.addItem("Law School");
		endLocBox.addItem("West Campus");
		endLocBox.setVisibleItemCount(7);
		break;
	case 2:
		startLocBox.addItem("North Ithan Gate");
		startLocBox.addItem("HomeProperties");
		startLocBox.addItem("Bryn Mawr Hospital");
		startLocBox.addItem("Rosemont Pharmacy");
		startLocBox.addItem("Suburban Square");
		startLocBox.setVisibleItemCount(5);
		
		endLocBox.addItem("North Ithan Gate");
		endLocBox.addItem("HomeProperties");
		endLocBox.addItem("Bryn Mawr Hospital");
		endLocBox.addItem("Rosemont Pharmacy");
		endLocBox.addItem("Suburban Square");
		endLocBox.setVisibleItemCount(5);
		break;
		
	case 3:
		startLocBox.addItem("Pike Lot");
		startLocBox.addItem("Cosi");
		startLocBox.addItem("Suburban Square");
		startLocBox.addItem("Giant");
		startLocBox.addItem("Minella's Diner");
		startLocBox.addItem("Wayne Movie Theatre");
		startLocBox.addItem("Anthropologie");
		startLocBox.setVisibleItemCount(7);
		
		endLocBox.addItem("Pike Lot");
		endLocBox.addItem("Cosi");
		endLocBox.addItem("Suburban Square");
		endLocBox.addItem("Giant");
		endLocBox.addItem("Minella's Diner");
		endLocBox.addItem("Wayne Movie Theatre");
		endLocBox.addItem("Anthropologie");
		endLocBox.setVisibleItemCount(7);
		break;
	
	case 4:
		startLocBox.addItem("South Campus - VU");
		startLocBox.addItem("King of Prussia Mall");
		startLocBox.setVisibleItemCount(2);
		
		endLocBox.addItem("South Campus - VU");
		endLocBox.addItem("King of Prussia Mall");
		endLocBox.setVisibleItemCount(2);
		break;	
	
	}

}
}

