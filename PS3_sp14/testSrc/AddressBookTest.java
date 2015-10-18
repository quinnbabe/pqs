package edu.nyu.cims.am4993.pqs.problemset1;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddressBookTest {

	@Before
	public void setUp() throws Exception {
		System.out.println("Test beigins!");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test finishes!");
	}

	@Test
	public void testHashCode() throws InvalidUserNameException, InvalidEntryException {
		AddressBook newAddressBook = AddressBook.newInstance("Ashish");
		Entry one = new Entry.Builder("Pratik", "Indore").note("Good Boy").build();
		newAddressBook.addEntry(one);
		int test_hashcode="Ashish".hashCode()+one.hashCode();
		assertEquals(true,test_hashcode==newAddressBook.hashCode());
	}

	@Test
	public void testNewInstance() throws InvalidUserNameException {
		AddressBook newAddressBook = AddressBook.newInstance("Ashish");
		assertEquals(true,newAddressBook.equals(newAddressBook));
	}
	
	@Test
	public void testNewInstanceException() {
		try {
			AddressBook newAddressBook = AddressBook.newInstance(null);
			fail("The username cannot be null");
		} catch (InvalidUserNameException e) {}
	}

	@Test
	public void testGetUserName() throws InvalidUserNameException {
		AddressBook newAddressBook = AddressBook.newInstance("Ashish");
		assertEquals("Ashish",newAddressBook.getUserName());
	}

	@Test
	public void testGetId() throws InvalidUserNameException {
		AddressBook newAddressBook = AddressBook.newInstance("Ashish");
		assertEquals(AddressBook.id.toString(),newAddressBook.getId());
	}

	@Test
	public void testAddEntryAndGetEntries() throws InvalidUserNameException, InvalidEntryException {
		ArrayList<Entry> test_entry = new ArrayList<Entry>();
		AddressBook newAddressBook = AddressBook.newInstance("Ashish");
		Entry one = new Entry.Builder("Pratik", "Indore").note("Good Boy").build();
		test_entry.add(one); 
		newAddressBook.addEntry(one);
		assertEquals(true,test_entry.equals(newAddressBook.entries));				
	}

	@Test
	public void testAddEntryException() throws InvalidUserNameException {
			AddressBook newAddressBook = AddressBook.newInstance("Ashish");
			Entry one = new Entry.Builder(null, null).note("Good Boy").build();
			try {
				newAddressBook.addEntry(one);
				fail("the added entry or entry's name or entry's address cannot be null ");
			} catch (InvalidEntryException e) {
			}
	}

	@Test
	public void testRemoveEntry() throws InvalidUserNameException, InvalidEntryException {
		ArrayList<Entry> test_entry = new ArrayList<Entry>();
		AddressBook newAddressBook = AddressBook.newInstance("Ashish");
		Entry one = new Entry.Builder("Pratik", "Indore").note("Good Boy").build();
		Entry two = new Entry.Builder("Bobby", "Jersey city").email("Pratik@gmail.com").build();
		test_entry.add(one);
		newAddressBook.addEntry(one);
		newAddressBook.addEntry(two);
		newAddressBook.removeEntry(two);
		assertEquals(true,test_entry.equals(newAddressBook.entries));
	}
	
	@Test
	public void testRemoveEntryException() throws InvalidUserNameException{
		AddressBook newAddressBook = AddressBook.newInstance("Ashish");
		Entry one = null;
		try {
			newAddressBook.addEntry(one);
			fail("the removed entry cannot be null ");
		} catch (InvalidEntryException e) {}
	}

	@Test
	public void testSearch() throws InvalidUserNameException, InvalidEntryException, InvalidSearchStringException {
		ArrayList<Entry> test_entry = new ArrayList<Entry>();
		AddressBook newAddressBook = AddressBook.newInstance("Ashish");
		Entry one = new Entry.Builder("Pratik", "Indore").note("Good Boy").build();
		Entry two = new Entry.Builder("Bobby", "Jersey city").email("Pratik@gmail.com").build();
		test_entry.add(one);
		newAddressBook.addEntry(one);
		newAddressBook.addEntry(two);
		assertEquals(true,test_entry.equals(newAddressBook.search(Properties.NAME, "Pratik")));
	}
	
	@Test
	public void testSearchException() throws InvalidUserNameException, InvalidEntryException{
		AddressBook newAddressBook = AddressBook.newInstance("Ashish");
		Entry one = new Entry.Builder("Pratik", "Indore").note("Good Boy").build();
		Entry two = new Entry.Builder("Bobby", "Jersey city").email("Pratik@gmail.com").build();
		newAddressBook.addEntry(one);
		newAddressBook.addEntry(two);
		try {
			newAddressBook.search(Properties.EMAIL, null);
			fail("The search string cannot be null");
		} catch (InvalidSearchStringException e) {}
	}

	@Test
	public void testSave() throws InvalidUserNameException, InvalidEntryException, InvalidAddressBookException {
		AddressBook newAddressBook = AddressBook.newInstance("Ashish");
		Entry one = new Entry.Builder("Pratik", "Indore").note("Good Boy").build();
		newAddressBook.addEntry(one);
		newAddressBook.save(newAddressBook);
		File f = new File(newAddressBook.getUserName() + "_"+ newAddressBook.getId() + ".ser");
		assert f.exists();	
	}

	@Test
	public void testSaveInvalidAddressBookException() throws InvalidUserNameException {
		AddressBook newAddressBook = AddressBook.newInstance("Ashish");
		try {
			newAddressBook.save(null);
			fail("Cannot serialize a null object");
		} catch (InvalidAddressBookException e) {}
	}
	
	@Test
	public void testRead() throws InvalidUserNameException, InvalidEntryException, InvalidAddressBookException, InvalidFileNameException {
		AddressBook newAddressBook = AddressBook.newInstance("Ashish");
		String fileName=newAddressBook.getUserName() + "_"+ newAddressBook.getId() + ".ser";
		Entry one = new Entry.Builder("Pratik", "Indore").note("Good Boy").build();
		newAddressBook.addEntry(one);
		newAddressBook.save(newAddressBook);
		assertEquals(true,newAddressBook.equals(newAddressBook.read(fileName)));
	}
	
	@Test
	public void testReadInvalidFileNameException() throws InvalidUserNameException{
		AddressBook newAddressBook = AddressBook.newInstance("Ashish");
		try {
			newAddressBook.read(null);
			fail("Cannot read from a null file");
		} catch (InvalidFileNameException e) {}	
	}

	@Test
	public void testEqualsObject() throws InvalidUserNameException, InvalidEntryException {
		AddressBook newAddressBook1= AddressBook.newInstance("Ashish");
		AddressBook newAddressBook2= AddressBook.newInstance("Ashish");
		Entry one = new Entry.Builder("Pratik", "Indore").note("Good Boy").build();
		newAddressBook1.addEntry(one);
		newAddressBook2.addEntry(one);
		assertEquals(true,newAddressBook1.equals(newAddressBook2));
	}
	
	@Test
	public void testNotEqualsObject() throws InvalidUserNameException, InvalidEntryException {
		AddressBook newAddressBook1= AddressBook.newInstance("Ashish");
		AddressBook newAddressBook2= AddressBook.newInstance("Ashish");
		Entry one = new Entry.Builder("Pratik", "Indore").note("Good Boy").build();
		newAddressBook1.addEntry(one);
		assertEquals(false,newAddressBook1.equals(newAddressBook2));
	}

	@Test
	public void testToString() throws InvalidUserNameException, InvalidEntryException {
		AddressBook newAddressBook= AddressBook.newInstance("Ashish");
		Entry one = new Entry.Builder("Pratik", "Indore").note("Good Boy").build();
		newAddressBook.addEntry(one);
		String s="Ashish"+"~##~"+"Pratik"+"~#~"+"Indore"+"~#~"+null+"~#~"+null+"~#~"+"Good Boy";
		assertEquals(s, newAddressBook.toString());
	}
}
