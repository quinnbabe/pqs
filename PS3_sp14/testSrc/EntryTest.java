package edu.nyu.cims.am4993.pqs.problemset1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.nyu.cims.am4993.pqs.problemset1.Entry.Builder;

public class EntryTest {
	
	//crate an addressbook entries
	Entry one = new Entry.Builder("Pratik", "Indore").note("Good Boy").build();
	Entry two = new Entry.Builder(null, null).email("Pratik@gmail.com").build();
	Entry three = new Entry.Builder("Pratik", "Indore").note("Good Boy").build();
	Entry four = new Entry.Builder(null, null).email("Pratik@gmail.com").build();

	@Before
	public void setUp() throws Exception {
		System.out.println("Test begins!");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test finishes!");
	}
	
	@Test
	public void testHashCode() {
		assertNotEquals(one.hashCode(), two.hashCode());	
		assertEquals(one.hashCode(), three.hashCode());
	}

	@Test
	public void testGetName() {
		assertEquals("Pratik", one.getName());
	}

	@Test
	public void testGetAddress() {
		assertEquals("Indore", one.getAddress());
	}

	@Test
	public void testGetPhoneNum() {
		assertEquals(null, one.getPhoneNum());
	}

	@Test
	public void testGetEmail() {
		assertEquals(null, one.getEmail());
	}

	@Test
	public void testGetNote() {
		assertEquals("Good Boy", one.getNote());
	}

	@Test 
	//test the throw exception when NAME is null
	public void testEditFieldNAMEIsNull() throws Exception{
		try {
			one.editField(Properties.NAME, null);
			fail("The value of NAME cannot be null");
		} catch (InvalidUpdateStringException e) {
		}
	}
	
	@Test
	public void testEditFieldNAMEIsNotNull() throws InvalidUpdateStringException{
			one.editField(Properties.NAME, "Bobby");
			assertEquals("Bobby", one.getName());
	}
	
	@Test
	//test the throw exception when ADDRESS is null
	public void testEditFieldAddressIsNull(){
		try {
			one.editField(Properties.ADDRESS, null);
			fail("The value of ADDRESS cannot be null");
		} catch (InvalidUpdateStringException e) {
		}
	}
	
	@Test
	public void testEditFieldAddressIsNotNull() throws InvalidUpdateStringException{
			one.editField(Properties.ADDRESS, "Jersey city");
			assertEquals("Jersey city", one.getAddress());
	}
	
	@Test
	public void testEditFieldPhone() throws InvalidUpdateStringException{
			one.editField(Properties.PHONENUM, "9175134101");
			assertEquals("9175134101", one.getPhoneNum());
	}
	
	@Test
	public void testEditFieldEmail() throws InvalidUpdateStringException{
			one.editField(Properties.EMAIL, "Bobby@gmail.com");
			assertEquals("Bobby@gmail.com", one.getEmail());
	}
	
	@Test
	public void testEditFieldNote() throws InvalidUpdateStringException{
			one.editField(Properties.NOTE, "hello world");
			assertEquals("hello world", one.getNote());
	}
	
	@Test
	public void testEqualsObject() {
		assertEquals(false,one.equals(two)||one.equals(null));
		assertEquals(true,one.equals(three));
		assertEquals(true,two.equals(four));
	}

	@Test
	public void testToString() {
		String s="Pratik"+"~#~"+"Indore"+"~#~"+null+"~#~"+null+"~#~"+"Good Boy";
		assertEquals(s, one.toString());	
	}
}
