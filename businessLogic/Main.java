package businessLogic;
	
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		try {

			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/userInterface/Home.fxml"));
			Scene scene = new Scene(root, 1042, 673);
			scene.getStylesheets().add(getClass().getResource("/userInterface/application.css").toExternalForm());
			primaryStage.setTitle("PharmaTech");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		 /*//reading files
		//Creating a File object for directory
	      File directoryPath = new File("C:\\Users\\deel 5520\\Desktop\\pharmacy");
	      //List of all files and directories
	      File filesList[] = directoryPath.listFiles();
	      Scanner sec = null;
	    	  for(File file : filesList) {
	    		  String filename;
	    		 if(file.isFile()) {
	    			 filename=file.getName();
	    			 
	    			 //reading patients file
	    			 if(filename.startsWith("p_")) {
	    				 //Instantiating the Scanner class
			 	         sec= new Scanner(file);
			 	         String username,password,id,phone,bloodType,medHist;
			 	            username = sec.nextLine();
			 	            password=sec.nextLine();
			 	            id=sec.nextLine();
			 	            phone=sec.nextLine();
			 	            bloodType=sec.nextLine();
			 	            medHist=sec.nextLine();
			 	            Patient p= new Patient(username,password);
							p.enterInformation(phone, bloodType, medHist, id);
			 	            pc.patients.add(p);
			 	            System.out.println("patient account read");
	    			 }
	    			 
	    			 //reading Hospital files
	    			 if(filename.startsWith("h_")) {
	    				 //Instantiating the Scanner class
			 	         sec= new Scanner(file);
			 	         String name,addr,username,password,ID,verification;
			 	         Boolean xs;
			 	         while (sec.hasNextLine()) {
			 	            name = sec.nextLine();
			 	            addr=sec.nextLine();
			 	            username=sec.nextLine();
			 	            password=sec.nextLine();
			 	            ID=sec.nextLine();
			 	            verification=sec.nextLine();
			 	            xs=Boolean.parseBoolean(verification);
							Hospital h1=new Hospital(name,addr,username,password,ID,xs);
							h_arr.add(h1);
			 	            System.out.println("hospital account read");
			 	         }
	    			 }
	    			
	    			  //reading manager file
			 	        if(filename.startsWith("m_")) {
		    				 //Instantiating the Scanner class
				 	         sec= new Scanner(file);
				 	         String name1,username1,password1;
				 	         while (sec.hasNextLine()) {
				 	            username1=sec.nextLine();
				 	            password1=sec.nextLine();
				 	            name1=sec.nextLine();
								Manager m1=new Manager(username1,password1,name1);
				 	            System.out.println("manager account read");
				 	         }
			 	        }
	    		 }
	 	      }*/
	    	  
		launch(args);
		/*
		//writing file
  	  //Patient
  	  Patient p2=new Patient("tom12","1234");
  	  p2.enterInformation("232323", "B+", "Insomnia", "tm12");
  	  String s="p_"+p2.getUsername()+".txt";
			
			try {
			      File myObj = new File(s);
			      if (myObj.createNewFile()) {
			        System.out.println("File created: " + myObj.getName());
			      } else {
			        System.out.println("File already exists.");
			      }
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
			
			 try {
			      FileWriter myWriter = new FileWriter(s);
			      myWriter.write(p2.getUsername());
			      myWriter.write("\n");
			      myWriter.write(p2.getPassword());
			      myWriter.write("\n");
			      myWriter.write(p2.getPhoneNo());
			      myWriter.write("\n");
			      myWriter.write(p2.getBloodType());
			      myWriter.write("\n");
			      myWriter.write(p2.getMedHistory());
			      myWriter.write("\n");
			      myWriter.write(p2.getID());
			      myWriter.write("\n");
			      myWriter.close();
			      System.out.println("Successfully wrote to the file.");
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
  	  
			 //hospital
			 Hospital h2=new Hospital("Surgimed Hospital","Gulberg","su_hos","1234","suh123",false);
	    	  String sd="h_"+h2.getUsername()+".txt";
				
				try {
				      File myObj = new File(sd);
				      if (myObj.createNewFile()) {
				        System.out.println("File created: " + myObj.getName());
				      } else {
				        System.out.println("File already exists.");
				      }
				    } catch (IOException e) {
				      System.out.println("An error occurred.");
				      e.printStackTrace();
				    }
				
				 try {
				      FileWriter myWriter = new FileWriter(s);
				      myWriter.write(h2.getName());
				      myWriter.write("\n");
				      myWriter.write(h2.getAddress());
				      myWriter.write("\n");
				      myWriter.write(h2.getUsername());
				      myWriter.write("\n");
				      myWriter.write(h2.getPassword());
				      myWriter.write("\n");
				      myWriter.write(h2.getHospitalID());
				      myWriter.write("\n");
				      if(h2.getVerification()==false) {
				    	  myWriter.write("false");
					      myWriter.write("\n");
				      }
				      else {
				    	  myWriter.write("true");
					      myWriter.write("\n");
				      }
				      
				      myWriter.close();
				      System.out.println("Successfully wrote to the file.");
				    } catch (IOException e) {
				      System.out.println("An error occurred.");
				      e.printStackTrace();
				    }*/
	}
}
