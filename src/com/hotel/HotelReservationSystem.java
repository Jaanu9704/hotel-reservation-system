package com.hotel;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

public class HotelReservationSystem {
  public static void main(String[] args) {
	  Scanner sc = new Scanner(System.in);
	  Connection con=null;
	 
	  try {
		  
		  // Load properties file
		  Properties p= new Properties();
	  FileInputStream fis= new FileInputStream("db.properties");
	  p.load(fis);
	  fis.close();
	  
	  
	  // Read database credentials from properties file
	  String url=p.getProperty("url");
	  String username=p.getProperty("username");
	  String password=p.getProperty("password");
	 
	 
		  // Step-1: Load the MySQL JDBC Driver
		  
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  
		  // step-2: Establish the connection with the database
		  
		   con=DriverManager.getConnection(url,username,password);
		   System.out.println("Database connected Successfully");
		   
		   
		   
		   
		   
		   //  Infinite loop to keep the menu running until user exits
		   while(true) {
			   
			   // Display menu options to the user
			   System.out.println("\nHotel Reservation System");
			   System.out.println("1. Reserve Room");
			   System.out.println("2. View Reservations");
			   System.out.println("3. Get Room Number");
			   System.out.println("4. Update Reservation");
			   System.out.println("5. Delete Reservation");
			   System.out.println("0. Exit");
			   
			   // Ask user to choose an option
			   System.out.print("Choose an option: ");
			   int choice=sc.nextInt();
			   
		   
		   
		   
		   
		   
		   switch(choice) {
		   
		   //-------- RESERVE ROOM ----------------------------
		   case 1:
			  ReservationService.reserveRoom(con,sc);
			  break;
		// -------------------------VIEW RESERVATIONS-------------------------
		   case 2:
			 
			   ReservationService.viewReservations(con, sc);
			   break;
			   
		// ---------------------------GET ROOM NUMBER--------------------------
		   case 3:
			   ReservationService.getRoomNumber(con, sc);
			   
			   break;
			 
		// --------------------UPDATE RESERVATION---------------------
		   case 4:
			   
			   ReservationService.updateReservation(con, sc);
			   
			   break;
			   
	   // ---------------DELETE RESERVATION------------------------
		   case 5:
			  ReservationService.deleteReservation(con, sc);
			   break;
			   
	  // -------------------------EXIT SYSTEM------------------------
		   case 0:
			   System.out.println("Exiting system....");
			   sc.close();
			   return;
			   
	  // if user enters an invalid option
			default:
				System.out.println("Invalid choice");
				   
		   }
		   }
		   
	  }
	  catch(Exception e) {
		  
		  // print error details if any exception occurs
		  e.printStackTrace();
	  }
	  finally {
	  try {
			// Close database connection before program ends	  
		  if(con!=null) {
			  con.close();
			  System.out.println("Connection closed");
		  }
	  }
	  catch(Exception e) {
		  e.printStackTrace();
	  }
	  }
  }
}
