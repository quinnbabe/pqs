package AddressBook;

/**THIS ADDRESS BOOK LIBRARY IS DEVELOPED BY JIEMING GONG,(JG3607@NYU.EDU)
 * IN THIS LIBRARY, EACH ENTRY HAS SIX PROPERTIES INCLUDING NAME, 
 * PHONE_NUMBER, POSTAL_ADDRESS, EMAIL_ADDRESS AND NOTES
 * YOU CAN CREATE A NEW ADDRESS BOOK, 
 * ADD CONTACTOR INTO THE ADDRESS BOOK, 
 * DELETE CONTATCTOR FROM THE ADDRESS BOOK
 * READ THE ADDRESS BOOK 
 * SEARCH CONTACTOR BY ANY PROPERTIES 
 * IF YOU HAVE ANY QUESTION, PLEASE LET ME KNOW.THANKS*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class AddressBook {
	
  private static String path="AddressBook/addressbook.txt";
  private static File filename=new File(path);
  private static String readStr ="";
  private String Name;
  private String PhoneNumber;
  private String PostalAddress;
  private String EmailAddress;
  private String Note;
  private ArrayList<String> AddEntry = new ArrayList<String>();
  static private ArrayList<String> AllEntry = new ArrayList<String>();
		
  /**create a new Address Book */
  public static void createAddrBook(){
    if(!filename.exists()){
      try{
        filename.createNewFile();
        System.out.println(filename+" "+ "is create successfully");
      }catch(Exception e){
           e.printStackTrace();
       }
    }
    else{
      System.err.println(filename +" "+"have exist!");
    }	
  };
	
  /**This is a Builder,add an Entry in an Address Book, 
   *you need to new a AddEntry for use this.
   * eg. new AddressBook.AddEntry("Bobby", "9175134101")
   *         .PostalAddress("40 newport parkway").addentry();*/
  public static class AddEntry{
    //Required Parameters
    private  String Name;
	private  String PhoneNumber;
			
	//Optional Parameters
	private  String PostalAddress=null;
	private  String EmailAddress=null;
	private  String Note=null;
		
    /** initialize the address book*/
	public AddEntry(String Name, String PhoneNumber){
	  this.Name=Name;
	  this.PhoneNumber=PhoneNumber;
	}
		
	public AddEntry PostalAddress(String s){
	  PostalAddress = s;        
	  return this;
	}
		
	public AddEntry EmailAddress(String s){
	  EmailAddress = s;        
	  return this;
	}
		
	public AddEntry Note(String s){
	  Note = s;        
	  return this;
	}
		
	public AddressBook addentry(){
	  return new AddressBook(this);
	}
		
	/**override toString() */
	@Override public String toString(){
	  StringBuilder result = new StringBuilder();
	  result.append("Name:"+ " " + this.Name + ",");
	  result.append("Phone_Number:" + " "+ this.PhoneNumber + ",");
	  result.append("Postal_Address:" + " "+ this.PostalAddress + ",");
      result.append("Email_Address:" + " "+ this.EmailAddress + ",");
	  result.append("Note:"+ " " + this.Note + ",");
	  return result.toString();
	}
  }
    
  private AddressBook(AddEntry addentry){
  Name=addentry.Name;
	PhoneNumber=addentry.PhoneNumber;
	PostalAddress=addentry.PostalAddress;
	EmailAddress=addentry.EmailAddress;
	Note=addentry.Note;	
	AddEntry.add(addentry.toString());
	//System.out.println("print AddEntry.toString()"+AddEntry.toString());	
	writeAddrBook(AddEntry.toString());
  }
	
  /**read address book entry from txt */  
  public static void openAddrBook(){
    readAddrBook();
	printAddrBook();
  }
    
  /**remove entry*/
  public static void removeEntry(int entryID){
    try{
	  String temp_str = "";
	  readAddrBook();
	  clearAddrBook();
	  AllEntry.remove(entryID);
	  for(int i=0;i<AllEntry.size();i++){
	  temp_str = temp_str + AllEntry.get(i) + "\r\n";
	  backAddrBook(temp_str);
	  }		   
	}catch(IndexOutOfBoundsException e){
	   System.out.println("There is an error");
	 }		
  };
	
  /**search entry according property
   * The first parameters(property) should be one of the following
   * Name
   * Phone_Number
   * Postal_Address
   * Email_Address
   * Note
   * The second parameters should be the content you want to search*/	
  public static void searchAddrBook(String property,String search){
    readAddrBook();
	findEntry(property,search);		
  }
	
  /**change the property of contact
   * The first parameter is an interger, and it should no larger than AllEntry.size()
   * The second parameter should be one of following: 
   * Name
   * Phone_Number  
   * Postal_Address
   * Email_Address or
   * Note*/
  public static void changeEntry(int EntryID, String property, String change){
	readAddrBook();
	clearAddrBook();
	updateAddrBook(EntryID, property, change);
  }
	
  /**write new added entry into txt file*/
  private static void writeAddrBook(String newEntry){
    //Read original entries from file, then add new entry to the file
    readStr="";
    readAddrBook();
    String filein = newEntry + "\r\n" + readStr;
    writeTxt(filein);
  }
    
  /**write string to txt file*/
  private static void writeTxt(String filein){
  	RandomAccessFile raf = null;
      try {
        raf = new RandomAccessFile(filename, "rw");
        raf.writeBytes(filein);
      } catch (IOException e1) {
          e1.printStackTrace();
        } finally {
            if (raf != null) {
              try {
                raf.close();
              } catch (IOException e2) {
                  e2.printStackTrace();
                }
             }
          }
  }
    
  /**read the address book from the txt file*/
  private static String readAddrBook(){
    AllEntry.clear();
    String read=null;
    try {
      FileReader fileread = new FileReader(filename);
      BufferedReader bufread = new BufferedReader(fileread);
        try {
          while ((read = bufread.readLine()) != null) {
            readStr = readStr + read+ "\r";
            AllEntry.add(read);
          }
        } catch (IOException e) {
            e.printStackTrace();
          }
    } catch (FileNotFoundException e) {
        System.out.println("There is no any Address Book yet, Please create One!"+ "\r\n");
        //e.printStackTrace();
      }
    return readStr;
  }  
			
  /**Clear all entries in Address Book*/
  private static void clearAddrBook(){
    RandomAccessFile raf=null;
	try {
	  raf = new RandomAccessFile(filename, "rw");
	} catch (FileNotFoundException e) {
	    System.out.println("There is no any Address Book yet, please create one!");
		//e.printStackTrace();
	  }
	try {
	  raf.setLength( 0 );
	  raf.close();
	} catch (IOException e) {
	    e.printStackTrace();
	  }
  }
	
  /**print all entries in address book*/
  private static void printAddrBook(){
    System.out.println("The Whole address book is: "+ "\r\n");
    for(int i=0;i<AllEntry.size();i++){
      System.out.println(i + " "+AllEntry.get(i));
    }
}
	
  /**after remove the entry, write back the rest entries*/
  private static void backAddrBook(String entry){
    readStr="";
    String filein = entry  + readStr;
    writeTxt(filein);		
  }
	 
  /**find matched entry*/
  private static void findEntry(String property,String search){
    boolean SearchResult=false;
    for(int i=0;i<AllEntry.size();i++){
      if(AllEntry.get(i).contains(property+":"+" "+search)){
	    SearchResult=true;
	    System.out.println(i + " "+AllEntry.get(i));
	  }
    }
	if(SearchResult==false){
	  System.out.println("Do not find any matched entry!");
	}
  }
	
	
  /**Change the entry in the address book*/
  private static void updateAddrBook(int EntryID, String property, String change){
    try{
	  String new_entry="";
	  String original_entry=AllEntry.get(EntryID);
	  AllEntry.remove(EntryID);
	  String [] split = original_entry.split(",");
	  boolean property_exist=false;
	  for(int i=0;i<split.length;i++){
	    if(split[i].contains(property+":")){
	      split[i]=property+":"+" "+change;
	      property_exist=true;
	      break;
	    }
	  }
	  if(property_exist==false){
	    System.out.println("The property you want to change does not exist");
	  }
	  for(int i=0;i<split.length;i++){
	    new_entry=new_entry+split[i]+",";
	  }
	  AllEntry.add(new_entry);
	  try{
	    BufferedWriter bw=new BufferedWriter(new FileWriter(filename));
		for(int i=0;i<AllEntry.size();i++){
	    bw.write(AllEntry.get(i));
		bw.newLine();
	    }
	    bw.close();
	  }catch (IOException e) {
	    e.printStackTrace();
	    }
    }catch(IndexOutOfBoundsException e){
	   System.out.println("There is no such contact, EntryID should no larger than AllEntry.size()!");
	  }
  }				
	
}

