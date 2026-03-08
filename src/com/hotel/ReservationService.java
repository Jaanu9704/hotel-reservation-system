package com.hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ReservationService {

	
		public static void reserveRoom(Connection con,Scanner sc) throws Exception{
			System.out.println("Enter Guest Name: ");
			  
			  sc.nextLine();   //  clear leftover new line from previous input
			  
			  // read the guest name
			  String guestname=sc.nextLine();
			  System.out.println("Enter Room Number: ");
			  int roomnumber= sc.nextInt();
			  System.out.println("Enter the contact Number: ");
			  String contactnumber= sc.next();
			  
			  // SQL query to insert reservation details into database
			  String query="insert into reservations(guest_name,room_number,contact_number) values(?,?,?)";
			  
			// PreparedStatement is used to pass values (guest name, room number, contact)
			// into the SQL query safely
			  PreparedStatement ps=con.prepareStatement(query);
			  
			  // set values for the SQL Query parameters
			  ps.setString(1, guestname);
			  ps.setInt(2, roomnumber);
			  ps.setString(3, contactnumber);
			  
			  // Execute the insert Query
			  int rows=ps.executeUpdate();
			  
			  //check if the insert was inserted successfully
			  if(rows>0) {
				  System.out.println("Room Reserved Successfully");
			  }
			  else {
				  System.out.println("Reservation Failed");
			  }
			  
			// close PreparedStatement to release resources
			  ps.close();
			  
		}
		
		
		public static void viewReservations(Connection con,Scanner sc) throws Exception{
			// SQL query to get all reservations
			   String viewQuery="select * from reservations";
				  
			   
			   // preparedStatement used to execute the select query
			   PreparedStatement ps2= con.prepareStatement(viewQuery);
			   
			   // Resultset stores the data returned from the database
			   ResultSet rs=ps2.executeQuery();
			   System.out.println("\n----- Reservation List -----");
			   
			   // loop through each row of the result
			     while(rs.next()) {
			    	 int id=rs.getInt("reservation_id");
			    	 String name=rs.getString("guest_name");
			    	 int room=rs.getInt("room_number");
			    	 String contact=rs.getString("contact_number");
			    	 String date=rs.getString("reservation_date");
			    	 
			    	 
			    	 System.out.println("Reservation ID: "+id);
			    	 System.out.println("Guest Name: "+name);
			    	 System.out.println("Room Number: "+room);
			    	 System.out.println("Contact Number: "+contact);
			    	 System.out.println("Date: "+date);
			    	 System.out.println("--------------------------");
			     }
			     rs.close();
			     ps2.close();
			   
			   
		}
		public static void getRoomNumber(Connection con,Scanner sc) throws Exception{
			
			
			String searchquery="select room_number from reservations where reservation_id=? and guest_name=?";
			   PreparedStatement ps3= con.prepareStatement(searchquery);
			   System.out.print("Enter Reservation ID: ");
			   int reservationid=sc.nextInt();
			   System.out.print("Enter Guest Name: ");
			   sc.nextLine();
			   String guestName=sc.nextLine();
			   ps3.setInt(1, reservationid);
			   ps3.setString(2, guestName);
			   
			   ResultSet rs2=ps3.executeQuery();
			   if(rs2.next()) {
				   int room=rs2.getInt("room_number");
				   System.out.println("Room Number : "+room);
				   		
			   }
			   else {
				   System.out.println("Reservation not found.");
			   }
			   rs2.close();
			   ps3.close();
			   
			   
			   
		}
		
		public static void updateReservation(Connection con,Scanner sc) throws Exception{
			
			
			System.out.println("Enter Reservation ID to update: ");
			   int reservationId=sc.nextInt();
			   sc.nextLine();
			   
			   System.out.print("Enter Guest Name: ");
			   String newGuestName=sc.nextLine();
			   
			   
			   System.out.println("Enter new Room Number: ");
			   int newRoomNumber=sc.nextInt();
			   
			   System.out.println("Enter new Contact Number: ");
			   String newContactNumber=sc.next();
			   
			   
			   String updatequery="update reservations set guest_name=?, room_number=?, contact_number=? where reservation_id=?";
			   
			   PreparedStatement ps4=con.prepareStatement(updatequery);
			   ps4.setString(1,newGuestName);
			   ps4.setInt(2, newRoomNumber);
			   ps4.setString(3, newContactNumber);
			   ps4.setInt(4, reservationId);
			   
			   int updatedrows=ps4.executeUpdate();
			   if(updatedrows>0) {
				   System.out.println("Reservation updated successfully.");
			   }
			   else {
				   System.out.println("Reservation not found.");
			   }
			   ps4.close();
		}
		
		
		public static void deleteReservation(Connection con,Scanner sc) throws Exception{
		
			
			System.out.println("Enter Reservation  ID to Delete: ");
			  int deleteid=sc.nextInt();
			  
			  // SQL Query to delete a reservation
			  String deleteQuery="Delete from reservations where reservation_id=?";
			  PreparedStatement ps5=con.prepareStatement(deleteQuery);
			  ps5.setInt(1, deleteid);
			  int deletedrows=ps5.executeUpdate();
			  if(deletedrows>0) {
				  System.out.println("Reservation deleted successfully");
			  }
			  else {
				  System.out.println("Reservation not found.");
			  }
			  ps5.close();
		}
	
}
