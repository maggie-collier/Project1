// package project1;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;

/********************************************************************
Tests all of the methods from the StopWatch class
********************************************************************/

public class UpdatedTestsStopWatch {

	/******************************************************************
	Testing function StopWatch.StopWatch()
	******************************************************************/
	@Test  
	public void testDefaultConstructor() {
		StopWatch s = new StopWatch();
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 0);
		assertTrue(s.getMilliseconds() == 0);
	}

	/******************************************************************
	Testing function StopWatch.StopWatch(int minutes, int seconds, int milliseconds)
	******************************************************************/
  @Test  
	public void testConstructor3Parameters() {
		StopWatch s = new StopWatch(2,30,400);
		assertTrue(s.getMinutes() == 2);
		assertTrue(s.getSeconds() == 30);
		assertTrue(s.getMilliseconds() == 400);

		s = new StopWatch(0,3,4);
		assertTrue(s.getMinutes() == 0);
		assertTrue(s.getSeconds() == 3);
		assertTrue(s.getMilliseconds() == 4);

		s = new StopWatch(20,0,4);
		assertTrue(s.getMinutes() == 20);
		assertTrue(s.getSeconds() == 0);
		assertTrue(s.getMilliseconds() == 4);

		s = new StopWatch(20,5,0);
		assertTrue(s.getMinutes() == 20);
		assertTrue(s.getSeconds() == 5);
		assertTrue(s.getMilliseconds() == 0);

		s = new StopWatch(1000,59,999);
		assertTrue(s.getMinutes() == 1000);
		assertTrue(s.getSeconds() == 59);
		assertTrue(s.getMilliseconds() == 999);
	}

  @Test  
	(expected = IllegalArgumentException.class)
	public void testConstructor3e2Parameters() {
		StopWatch s = new StopWatch(12,67,14);
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testConstructor3e3Parameters() {
		StopWatch s = new StopWatch(12,6,1000);
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testConstructor3e4Parameters() {
		StopWatch s = new StopWatch(-12,6,100);
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testConstructor3e5Parameters() {
		StopWatch s = new StopWatch(12,-6,100);
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testConstructor3e6Parameters() {
		StopWatch s = new StopWatch(12,6,-100);
	}

	/******************************************************************
	Testing function StopWatch.StopWatch(int seconds, int milliseconds)
	******************************************************************/
  @Test  
	public void testConstructor2Parameters() {
		StopWatch s = new StopWatch(30,400);
		assertTrue(s.getSeconds() == 30);
		assertTrue(s.getMilliseconds() == 400);

		s = new StopWatch(0,4);
		assertTrue(s.getSeconds() == 0);
		assertTrue(s.getMilliseconds() == 4);

		s = new StopWatch(5,0);
		assertTrue(s.getSeconds() == 5);
		assertTrue(s.getMilliseconds() == 0);

		s = new StopWatch(59,999);
		assertTrue(s.getSeconds() == 59);
		assertTrue(s.getMilliseconds() == 999);
	}

  @Test  
	(expected = IllegalArgumentException.class)
	public void testConstructor2e2Parameters() {
		StopWatch s = new StopWatch(67,14);
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testConstructor2e3Parameters() {
		StopWatch s = new StopWatch(6,1400);
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testConstructor2e4Parameters() {
		StopWatch s = new StopWatch(-6,140);
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testConstructor2e5Parameters() {
		StopWatch s = new StopWatch(6,-140);
	}

	/******************************************************************
	Testing function StopWatch.StopWatch(int milliseconds)
	******************************************************************/
	@Test  
	public void testConstructor1Parameter() {
		StopWatch s = new StopWatch(400);
		assertTrue(s.getMilliseconds() == 400);

		s = new StopWatch(0);
		assertTrue(s.getMilliseconds() == 0);

		s = new StopWatch(0);
		assertTrue(s.getMilliseconds() == 0);

		s = new StopWatch(999);
		assertTrue(s.getMilliseconds() == 999);
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testNegSingleInput() {
		new StopWatch(-2);
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testOverSingleInput() {
		new StopWatch(1000);
	}

	/******************************************************************
	Testing function StopWatch.StopWatch(String timeInput)
	******************************************************************/
  @Test  
	public void testStringConstructor() {
		StopWatch s = new StopWatch (5,10,300);
		assertEquals(s.toString(),"05:10:300");

		s = new StopWatch("20:10:8");
		assertEquals(s.toString(),"20:10:008");

		s = new StopWatch("20:10:80");
		assertEquals(s.toString(),"20:10:080");

		s = new StopWatch("20:1:800");
		assertEquals(s.toString(),"20:01:800");

		s = new StopWatch("0:10:800");
		assertEquals(s.toString(),"00:10:800");

		s = new StopWatch("10:0:800");
		assertEquals(s.toString(),"10:00:800");

		s = new StopWatch("10:20:0");
		assertEquals(s.toString(),"10:20:000");

		s = new StopWatch("10:59:110");
		assertEquals(s.toString(),"10:59:110");

		s = new StopWatch("10:49:999");
		assertEquals(s.toString(),"10:49:999");

		s = new StopWatch("0000010:0000049:00000999");
		assertEquals(s.toString(),"10:49:999");

		s = new StopWatch("20:800");
		assertEquals(s.toString(),"00:20:800");

		s = new StopWatch("20:8");
		assertEquals(s.toString(),"00:20:008");

		s = new StopWatch("20:80");
		assertEquals(s.toString(),"00:20:080");

		s = new StopWatch("2:800");
		assertEquals(s.toString(),"00:02:800");

		s = new StopWatch("0:800");
		assertEquals(s.toString(),"00:00:800");

		s = new StopWatch("10:0");
		assertEquals(s.toString(),"00:10:000");

		s = new StopWatch("59:200");
		assertEquals(s.toString(),"00:59:200");

		s = new StopWatch("20:999");
		assertEquals(s.toString(),"00:20:999");

		s = new StopWatch("00000020:000000999");
		assertEquals(s.toString(),"00:20:999");

		s = new StopWatch("200");
		assertEquals(s.toString(),"00:00:200");

		s = new StopWatch("80");
		assertEquals(s.toString(),"00:00:080");

		s = new StopWatch("8");
		assertEquals(s.toString(),"00:00:008");

		s = new StopWatch("0");
		assertEquals(s.toString(),"00:00:000");

		s = new StopWatch("999");
		assertEquals(s.toString(),"00:00:999");

		s = new StopWatch("00000000999");
		assertEquals(s.toString(),"00:00:999");

	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testNegWith3Parameters1() {
		new StopWatch("-1:45:23");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testNegWith3Parameters2() {
		new StopWatch("1:-45:23");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testNegWith3Parameters3() {
		new StopWatch("1:45:-23");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testNegWith3Parameters4() {
		new StopWatch("-1:-45:23");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testNegWith3Parameters5() {
		new StopWatch("-1:45:-23");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testNegWith3Parameters6() {
		new StopWatch("1:-45:-23");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testNegWith3Parameters7() {
		new StopWatch("-1:-45:-23");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testNegWith2Parameters1() {
		new StopWatch("-45:23");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testNegWith2Parameters2() {
		new StopWatch("45:-23");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testNegWith2Parameters3() {
		new StopWatch("-45:-23");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testNegWith1Parameter() {
		new StopWatch("-45");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testThreshold3Parameters1() {
		new StopWatch("12:13:12345");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testThreshold3Parameters2() {
		new StopWatch("12:500:123");
	} 

	@Test  
	(expected = IllegalArgumentException.class)
	public void testThreshold3Parameters3() {
		new StopWatch("12:500:1234");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testThreshold2Parameters1() {
		new StopWatch("50:1234");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testThreshold2Parameters2() {
		new StopWatch("500:123");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testThreshold2Parameters3() {
		new StopWatch("500:1234");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testThreshold1Parameters() {
		new StopWatch("1234");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testLowerCaseInput() {
		new StopWatch("a");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testUpperCaseInput() {
		new StopWatch("A");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput1() {
		new StopWatch("!");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput2() {
		new StopWatch("@");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput3() {
		new StopWatch("#");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput4() {
		new StopWatch("$");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput5() {
		new StopWatch("%");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput6() {
		new StopWatch("^");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput7() {
		new StopWatch("&");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput8() {
		new StopWatch("*");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput9() {
		new StopWatch("(");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput10() {
		new StopWatch(")");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput11() {
		new StopWatch("-");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput12() {
		new StopWatch("_");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput13() {
		new StopWatch("=");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput14() {
		new StopWatch("+");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput15() {
		new StopWatch("`");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput16() {
		new StopWatch("~");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput17() {
		new StopWatch("[");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput18() {
		new StopWatch("{");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput19() {
		new StopWatch("]");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput20() {
		new StopWatch("}");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput21() {
		new StopWatch("\\");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput22() {
		new StopWatch("|");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput23() {
		new StopWatch(";");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput24() {
		new StopWatch("'");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput25() {
		new StopWatch("\"");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput26() {
		new StopWatch(",");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput27() {
		new StopWatch("<");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput28() {
		new StopWatch(".");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput29() {
		new StopWatch(">");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput29b() {
		new StopWatch(":");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput30() {
		new StopWatch("/");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput31() {
		new StopWatch("?");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput32() {
		new StopWatch(" ");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput33() {
		new StopWatch("?:45:400");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput34() {
		new StopWatch("45:&:400");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput35() {
		new StopWatch("45:13:@");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput36() {
		new StopWatch("*:500");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSymbolInput37() {
		new StopWatch("10:%");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testEndWithColon() {
		new StopWatch("1:23:456:");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testBeginWithColon() {
		new StopWatch(":1:23:456");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSequentialColons1() {
		new StopWatch("1::23:456");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSequentialColons2() {
		new StopWatch("1:23::456");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSequentialColons3() {
		new StopWatch("1:::23:456");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testSequentialColons4() {
		new StopWatch("1:23:::456");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testValidLength1() {
		new StopWatch("1:23:456:432");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testValidLength2() {
		new StopWatch("");
	}

	/******************************************************************
	Testing function StopWatch.getMinutes()
	******************************************************************/
	@Test
	public void getMinutes() {
		StopWatch s = new StopWatch();
		int minutes = 3;
		assertEquals(s.getMinutes(), minutes);

	}
	
	/******************************************************************
	Testing function StopWatch.getSeconds()
	******************************************************************/

	/******************************************************************
	Testing function StopWatch.getMilliseconds()
	******************************************************************/

	/******************************************************************
	Testing function StopWatch.setMinutes()
	******************************************************************/
	@Test  
	public void testSetMinutes() {
		StopWatch s1 = new StopWatch();
		s1.setMinutes(2);
		assertEquals (s1.toString(), "02:00:000");

		s1 = new StopWatch(15,12,123);
		s1.setMinutes(0);
		assertEquals (s1.toString(), "00:12:123");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testFailureSetMinutes() {
		StopWatch s1 = new StopWatch("01:23:456");
		s1.setMinutes(-10);
	}

	/******************************************************************
	Testing function StopWatch.setSeconds()
	******************************************************************/
	@Test  
	public void testSetSeconds() {
		StopWatch s1 = new StopWatch();
		s1.setSeconds(2);
		assertEquals (s1.toString(), "00:02:000");

		s1 = new StopWatch();
		s1.setSeconds(25);
		assertEquals (s1.toString(), "00:25:000");

		s1 = new StopWatch();
		s1.setSeconds(59);
		assertEquals (s1.toString(), "00:59:000");

		s1 = new StopWatch(15,12,123);
		s1.setSeconds(0);
		assertEquals (s1.toString(), "15:00:123");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testFailureSetSeconds1() {
		StopWatch s1 = new StopWatch("01:23:456");
		s1.setSeconds(-10);
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testFailureSetSeconds2() {
		StopWatch s1 = new StopWatch("01:23:456");
		s1.setSeconds(70);
	}

	/******************************************************************
	Testing function StopWatch.setMilliseconds()
	******************************************************************/
	@Test  
	public void testSetMilliseconds() {
		StopWatch s1 = new StopWatch();
		s1.setMilliseconds(2);
		assertEquals (s1.toString(), "00:00:002");

		s1 = new StopWatch();
		s1.setMilliseconds(22);
		assertEquals (s1.toString(), "00:00:022");

		s1 = new StopWatch();
		s1.setMilliseconds(222);
		assertEquals (s1.toString(), "00:00:222");

		s1 = new StopWatch();
		s1.setMilliseconds(999);
		assertEquals (s1.toString(), "00:00:999");

		s1 = new StopWatch(15,12,123);
		s1.setMilliseconds(0);
		assertEquals (s1.toString(), "15:12:000");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testFailureSetMilliseconds1() {
		StopWatch s1 = new StopWatch("01:23:456");
		s1.setMilliseconds(-100);
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void testFailureSetMilliseconds2() {
		StopWatch s1 = new StopWatch("01:23:456");
		s1.setMilliseconds(10000);
	}

	/******************************************************************
	Testing function StopWatch.toString()
	******************************************************************/

	/******************************************************************
	Testing function StopWatch.checkInput(String inputMin, String inputSec, String inputMil)
	******************************************************************/
	
	/******************************************************************
	Testing function StopWatch.checkInput(String timeInput)
	******************************************************************/

	/******************************************************************
	Testing function StopWatch.checkInput(int inputMil)
	******************************************************************/

	/******************************************************************
	Testing function StopWatch.add(int timeDelay)
	******************************************************************/
	@Test  
	public void testAddIntMethod () {
		StopWatch s1 = new StopWatch (5,59,300);
		s1.addTime(2000);
		assertEquals (s1.toString(),"06:01:300");

		s1 = new StopWatch (5,59,300);
		s1.addTime(0);
		assertEquals (s1.toString(),"05:59:300");
	}

	@Test  
	(expected = IllegalArgumentException.class)
	public void AddIntMethodFailure1() {
		StopWatch s1 = new StopWatch (5,59,300);
		s1.addTime(-10);
	} 

	@Test  
	public void testAddStopWatch() {	
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (2,2,300);
		s1.add(s2);
		assertEquals (s1.toString(),"08:01:600");

		s1 = new StopWatch (5,59,300);
		s2 = new StopWatch (0,0,0);
		s1.add(s2);
		assertEquals (s1.toString(), "05:59:300");
	}
  





}