

import junit.framework.TestCase;
import java.util.concurrent.ThreadLocalRandom;

public class UrlValidatorTest extends TestCase 
{


   public UrlValidatorTest(String testName)
   {
      super(testName);
   }

   
   public void testManualTest()
   {
	   
	   System.out.println();
	   System.out.println("MANUAL TEST: Call 15 urls of known validity\n");
	  
	   long options =
	            UrlValidator.ALLOW_2_SLASHES
	                + UrlValidator.ALLOW_ALL_SCHEMES
	                + UrlValidator.NO_FRAGMENTS;
	   UrlValidator urlVal = new UrlValidator(options);
	   
	   String valid1 = "http://255.255.255.255:80/test1/file?action=view";
	   String valid2 = "http://www.google.com:80";
	   String valid3 = "f1p://go.cc:65535";
	   String valid4 = "fpp://0.0.0.0:65535/t123?action=edit&mode=up";
	   String valid5 = "HTTP://WWW.GOOGLE.COM";
	   String invalid1 = "t://.1.2.3.4:65a";
	   String invalid2 = "http/go.1aa:-1";
	   String invalid3 = "3ht://aaa:65636";
	   String invalid4 = "3ht://go.com:80/test1?action=view";
	   String invalid5 = "http://1.2.3.4.5:65535/$23?action=edit&mode=up";
	   String invalid6 = "ftp://255.255.255.255:65a/test1/?action=view";
	   String invalid7 = "h3t://255.com:0/..?action=edit&mode=up";
	   String invalid8 = "://1.2.3:-1/../";
	   String invalid9 = ""; 
	   String invalid10 = "ftp://0.*hj.0.0";
	   
	   String urls[] = {valid1, valid2, valid3, valid4, valid5, 
			   invalid1, invalid2, invalid3, invalid4, invalid5,
			   invalid6, invalid7, invalid8, invalid9, invalid10};
	   
	   teststruct TestArr[] = new teststruct[15]; 
	   
	   for (int i = 0; i < 15; i++)
	   {
		   TestArr[i] = new teststruct();
		   TestArr[i].url = urls[i];
		   if(i > 4)
			   TestArr[i].expect = false; 
		   else
			   TestArr[i].expect = true; 
	   }
	   
	   boolean result;
	   for (int j = 0; j < 15; j++)
	   {
		   try
		   {
			   result = urlVal.isValid(TestArr[j].url);
			   TestArr[j].crash = false;
			   TestArr[j].isValidReturn = result; 
			   if(TestArr[j].expect == TestArr[j].isValidReturn)
				   TestArr[j].pass = true;
			   else
				   TestArr[j].pass = false;
				   
		   }
		   catch (Throwable t)
		   {
			   TestArr[j].crash = true;
		   }

	   }
	   
	   teststruct.printResults(TestArr);
	   System.out.println("\n\n");
   }

   public void testScheme()
   {
	   
       boolean test1Run = false; 
       boolean test2Run = false;
       String setting1 = "";
       String setting2 = "";
       teststruct TestArr[] = new teststruct[18]; 
       teststruct TestArr2[] = new teststruct[18]; 
       
	   System.out.println();
	   System.out.println("UNIT TEST: Testing isValidScheme() on provided schemes\n");
	   
	   // TEST 1: DEFAULT SCHEMES
	   String[] DEFAULT_SCHEMES = {"http", "https", "ftp"}; 
	   UrlValidator urlVal = new UrlValidator(DEFAULT_SCHEMES);
	   
	   boolean initializationError;
	   boolean httpError = false;
	   boolean httpsError = false;
	   boolean ftpError = false;
	   boolean fError = false;
	   
	   
	   System.out.println("\tTesting constructor initialization of allowable schemes: http, https, ftp\n");
	   
	   // TEST isValidScheme call in context of instantiated UrlValidator
	   if (!urlVal.isValidScheme("http"))
	   {
		   System.out.print("\t\thttp: FAIL\n");
		   httpError = true;
	   }
	   else if(urlVal.isValidScheme("http"))
		   System.out.print("\t\thttp: PASS\n");
	   
	   // TEST isValidScheme call in context of instantiated UrlValidator
	   if (!urlVal.isValidScheme("https"))
	   {
		   System.out.print("\t\thttps: FAIL\n");
		   httpsError = true;
	   }
	   else if(urlVal.isValidScheme("https"))
		   System.out.print("\t\thttps: PASS\n");
		  
	   // TEST isValidScheme call in context of instantiated UrlValidator
	   if (!urlVal.isValidScheme("ftp"))
	   {
		   System.out.print("\t\tftp: FAIL\n");
		   ftpError = true;
	   }
	   else if(urlVal.isValidScheme("ftp"))
		   System.out.print("\t\tftp: PASS\n");
	   
		  
	   // TEST isValidScheme call in context of instantiated UrlValidator
	   if (urlVal.isValidScheme("xyz"))
	   {
		   System.out.print("\t\txyz: FAIL\n");
		   fError = true;
	   }
	   else if(!urlVal.isValidScheme("xyz"))
		   System.out.print("\t\txyz: PASS\n");
		  
	   if(!(httpError || httpsError || ftpError || fError))
	   {
		   // Trace Statements for debugging
		   // System.out.println();
		   // System.out.println("\tCross references of scheme validity in context:");
		   // System.out.println("\tTesting above scheme validity in context of isValid() call: \n\n");
		   
		   System.out.println();
		   System.out.println("\tUnit test calls to isValid() for schemes http, https, ftp runing . . . \n");
		   
		   test1Run = true;
		   boolean preliminary;
		   setting1 = "Only: http/https/ftp"; 
		   
		   try 
		   {
			   preliminary = urlVal.isValid("http://www.google.com");
		   }
		   catch (Exception e)
		   {
			   System.out.println("\t\tCRASHED on preliminary test case: http://www.google.com");
			   return; 
		   }
		   
		   // create urls with invalid valid and invalid scheme while holding the rest constant
		   ResultPair[] testingScheme =  
			   {	   
				   new ResultPair("http://" + "www.google.com", true),
				   new ResultPair("ftp://" + "www.google.com", true),
				   new ResultPair("https://" + "www.google.com", true),
				   new ResultPair("xTtp://" + "www.google.com", false),	
				   new ResultPair("h-fp://" + "www.google.com", false), 
				   new ResultPair("httpppp://" + "www.google.com", false),
				   new ResultPair("h://" + "www.google.com", false),		
				   new ResultPair(":ttp://" + "www.google.com", false), 
				   new ResultPair("h*tp://" + "www.google.com", false), 
				   new ResultPair("Xttp:/" + "www.google.com", false),	
				   new ResultPair("h_tp:" + "www.google.com", false),  
				   new ResultPair("/" + "www.google.com", false),       
				   new ResultPair("://" + "www.google.com", false),     
				   new ResultPair("3ht://" + "www.google.com", false),  
				   new ResultPair("http:/" + "www.google.com", false),  
				   new ResultPair("http:" + "www.google.com", false),  
				   new ResultPair("http/" + "www.google.com", false),   
				   new ResultPair("://" + "www.google.com", false)		
			   };
		   
		   for (int i = 0; i < 18; i++)
		   {
			   TestArr[i] = new teststruct();
			   TestArr[i].url = testingScheme[i].item;
			   if(i < 3) // CHANGE THIS
				   TestArr[i].expect = true; 
			   else
				   TestArr[i].expect = false; 
		   }
		   
		   // call isValid()
		   boolean result;
		   for (int j = 0; j < 18; j++)
		   {
			   try
			   {
				   result = urlVal.isValid(TestArr[j].url);
				   TestArr[j].crash = false;
				   TestArr[j].isValidReturn = result; 
				   if(TestArr[j].expect == TestArr[j].isValidReturn)
					   TestArr[j].pass = true;
				   else
					   TestArr[j].pass = false;
					   
			   }
			   catch (Throwable t)
			   {
				   TestArr[j].crash = true;
			   }

		   }
		   
		   System.out.println("\tSCHEME UNIT TEST 1 COMPLETE \n");
		   System.out.println("\tSee full results in 'Scheme_Unit_Test1.txt''\n\n");
		   teststruct.printResultsToFile(TestArr, "Scheme_Unit_Test1.txt", "SchemeTest1");

	   }
	   else
	   {
		   System.out.print("\tSUMMARY 'allowable scheme' initialization produces fatal error ");
		   if(httpError && httpsError && ftpError)
			   System.out.println("TYPE: all schemes evaluate to invalid\n\n");
		   else
			   System.out.println("TYPE: one or more schemes evaluate to invalid\n\n");
	   }
	   
	   // TEST 2: TESTING "ALLOW_ALL_SCHEMES"
	   System.out.println();
	   System.out.println("UNIT TEST: Testing isValidScheme() on ALLOW_ALL_SCHEMES");
	   long options =
	            UrlValidator.ALLOW_ALL_SCHEMES;
	   setting2 = "All schemes allowed"; 
	   boolean httpErrorB = false;
	   boolean httpsErrorB = false;
	   boolean ftpErrorB = false;
	   boolean XttpError = false;
	   boolean fsError = false;
	   
	   UrlValidator urlVal2 = new UrlValidator(options);
	   
	   System.out.println("\tTesting constructor initialization of ALLOW_ALL_SCHEMES\n");
	   
	   // TEST isValidScheme call in context of instantiated UrlValidator
	   if (!urlVal2.isValidScheme("http"))
	   {
		   System.out.print("\t\thttp: FAIL;");
		   httpErrorB = true;
	   }
	   else if(urlVal2.isValidScheme("http"))
		   System.out.print("\t\thttp: PASS\n");  
	   
	   // TEST isValidScheme call in context of instantiated UrlValidator
	   if (!urlVal2.isValidScheme("https"))
	   {
		   System.out.print("\t\thttps: FAIL \n");
		   httpsErrorB = true;
	   }
	   else if(urlVal2.isValidScheme("https"))
		   System.out.print("\t\thttps: PASS\n"); 
		  
	   // TEST isValidScheme call in context of instantiated UrlValidator
	   if (!urlVal2.isValidScheme("ftp"))
	   {
		   System.out.print("\t\tftp: FAIL \n");
		   ftpErrorB = true;
	   }
	   else if(urlVal2.isValidScheme("ftp"))
		   System.out.print("\t\tftp: PASS\n");
		  
	   if(!urlVal2.isValidScheme("Xttp"))
	   {
		   System.out.print("\t\tXttp: FAIL \n");
		   XttpError = true;
	   }
	   else if(urlVal2.isValidScheme("Xttp"))
		   System.out.print("\t\tXttp: PASS\n");
	   
	   String fArr[] = 
		   {
			   "-ttp",
			   ":=",
			   ">ht:",
			   "h_tp",
		   };
	   
	   for(int k = 0; k < fArr.length; k++)
	   {
		   if(urlVal2.isValidScheme(fArr[k]))
		   {
			   System.out.print("\t\t" + fArr[k] + ": FAIL \n");
			   fsError = true;
		   }
		   else if(!urlVal2.isValidScheme(fArr[k]))
			   System.out.print("\t\t" + fArr[k] + " PASS \n");
	   }
	   
	   System.out.println();
	   
	   // if things that are supposed to be true evaluate to false, do not context tests
	   if(!(httpErrorB || httpsErrorB || ftpErrorB || XttpError || fsError))
	   {
		   
		   System.out.println("\tUnit test calls to isValid() for ALLOW_ALL_SCHEMES . . . \n");
		   
		   test2Run = true;
		   try 
		   {
			   boolean preliminary = urlVal2.isValid("http://www.google.com");
		   }
		   catch (Exception e)
		   {
			   System.out.println("\t\tCRASHED on preliminary test case: http://www.google.com");
			   return; 
		   }
		   
		   ResultPair[] testingScheme2 =  
			   {	   
					   new ResultPair("http://" + "www.google.com", true),
					   new ResultPair("ftp://" + "www.google.com", true),
					   new ResultPair("https://" + "www.google.com", true),
					   new ResultPair("xTtp://" + "www.google.com", true),	
					   new ResultPair("h-fp://" + "www.google.com", true), 
					   new ResultPair("httpppp://" + "www.google.com", true),
					   new ResultPair("h://" + "www.google.com", true),		
					   new ResultPair(":ttp://" + "www.google.com", false), // FALSE: invalid format begins with a non-letter
					   new ResultPair("h*tp://" + "www.google.com", false), // FALSE: invalid format of second letter
					   new ResultPair("Xttp:/" + "www.google.com", false),	// FALSE: invalid format, ":/" not allowed
					   new ResultPair("h_tp:" + "www.google.com", false),   // FALSE: invalid format "_" not allowed
					   new ResultPair("/" + "www.google.com", false),       // FALSE: invalid format begins with a non-letter
					   new ResultPair("://" + "www.google.com", false),     // FALSE: invalid format begins with a non-letter 
					   new ResultPair("3ht://" + "www.google.com", false),  // FALSE: invalid format begins with a non-letter
					   new ResultPair("http:/" + "www.google.com", false),  // FALSE: invalid format ":/"
					   new ResultPair("http:" + "www.google.com", false),   // FALSE: invalid format ":"
					   new ResultPair("http/" + "www.google.com", false),   // FALSE: invalid format "/"
					   new ResultPair("://" + "www.google.com", false)		// FALSE: invalid format - must start with letter
			   };
		   
		   for (int i = 0; i < 18; i++)
		   {
			   TestArr2[i] = new teststruct();
			   TestArr2[i].url = testingScheme2[i].item;
			   
			  if(i < 7)
				  TestArr2[i].expect = true;
			  else
				  TestArr2[i].expect = false;
		   }
		   
		   // call isValid()
		   for (int j = 0; j < 18; j++)
		   {
			   try
			   {
				   boolean result = urlVal2.isValid(TestArr2[j].url);
				   TestArr2[j].crash = false;
				   TestArr2[j].isValidReturn = result; 
				   if(TestArr2[j].expect == TestArr2[j].isValidReturn)
					   TestArr2[j].pass = true;
				   else
					   TestArr2[j].pass = false;
					   
			   }
			   catch (Throwable t)
			   {
				   TestArr2[j].crash = true;
			   }

		   }
		   
		   //teststruct.printResults(TestArr2);
		   System.out.println("\t SCHEME UNIT TEST 2 COMPLETE \n");
		   System.out.println("\tSee full results in 'Scheme_Unit_Test2.txt'\n");
		   teststruct.printResultsToFile(TestArr2, "Scheme_Unit_Test2.txt", "SchemeTest2");
		   
	   }
	   else
	   {
		   System.out.print("\t\tError upon initialization of ALLOW_ALL_SCHEMES ");
		   if(httpErrorB && httpsErrorB && ftpErrorB && XttpError && fError)
			   System.out.println("TYPE: invalid scheme evaluation in all scheme input types\n\n");
		   else
			   System.out.println("TYPE: one or more schemes (alone) evaluate incorrectly\n\n");
		   
	   }
	   
	   // if we ran both full tests, we can compare the results
	   if(test1Run && test2Run)
	   {
		   System.out.println("\tComparison of Scheme options test results in output 'CompareSchemeSettingsTest.txt'\n\n");
		   // NOW COMPARE THE TWO TESTS STRUCT ARRAY RESULTS 
		   //teststruct.compareTestStructArrays(TestArr, TestArr2, setting1, setting2);
		   teststruct.printCompareTestStructArraysToFile(TestArr, TestArr2, setting1, setting2, "CompareSchemeSettingsTest.txt", "Compare Scheme Settings Test");
	   }
	 
	   
	   
   }
   public void testAuthority()
   {
	   System.out.println("UNIT TEST: Testing isValidAuthority() directly (not calling isValid() )"); 
	   
	   // TEST 1: TESTING DEFAULT SETTINGS with IANA
	   long options =
	            UrlValidator.ALLOW_ALL_SCHEMES;
	   
	   UrlValidator urlValAuth = new UrlValidator(options);
	   String setting1 = "IANA"; 
	   
	   String tArr[] = 
		   {
				   "www.google.com",
				   "amazon.com.au",
				   "0.0.0.0",
				   "172.com",
				   "youtube.cc",
				   "255.255.255.255:81",
				   "[FEDC:BA98:7654:3210:FEDC:BA98:7654:3210]",
				   "[1080:0:0:0:8:800:200C:417A]",
				   "[3ffe:2a00:100:7031::1]",
				   "[1080::8:800:200C:417A]",
				   "[2010:836B:4179::836B:4179]"
		   };
	   String fArr[] = 
		   {
				   "172.192.172.256", 
				   "10.11.12.13.14", 
				   "1.1.1.1.", 
				   "1.2.3", 
				   ".1.2.3.4",
				   "go.a", 
				   "go.1aa", 
				   ".aaa",
				   "aaa.", 
				   "aaa"
		   };
	   
	   
	   boolean result;
	   
	   // call isValidAuthority with "null"
	   System.out.println();
	   System.out.println("\tTesting isValidAuthority called with authority = null: \n");
	   try
	   {
		   result = urlValAuth.isValidAuthority(null);
		   if(result)
			   System.out.println("\t\tNull input returned valid\n\n");
		   else
			   System.out.println("\t\tNull input returned invalid\n\n");
	   }
	   catch(Throwable t)
	   {
		   System.out.println("\t\tCATCH: null input returned invalid\n\n");
	   }
	  
	   // call !isValidAuthority(authority) on valid authorities:
	   System.out.println("\tTesting isValidAuthority on valid authority strings (failures printed): \n\n");
	   int count1 = 0;
	   int count2 = 0;
	   
	   for(int i = 0; i < tArr.length; i++)
	   {
		   try
		   {
			   result = urlValAuth.isValidAuthority(tArr[i]);
			   if(!result)
				   System.out.println("\t\t" +tArr[i] + " FAIL");
		   }
		   catch(Throwable t)
		   {
			   System.out.println("\t\tThrowable error with " + tArr[i] + " calling isValidAuthority() \n");
			   count1++;
		   }
	   }
	   
	   System.out.println();
	   System.out.println("\tTesting isValidAuthority on invalid authority strings (failures printed): \n");
	   // call !isValidAuthority(authority) on invalid authorities
	   for(int i = 0; i < fArr.length; i++)
	   {
		   try
		   {
			   result = urlValAuth.isValidAuthority(fArr[i]);
			   if(result)
				   System.out.println("\t\t" + fArr[i] + " FAIL");
		   }
		   catch(Throwable t)
		   {
			   System.out.println("\t\tThrowable error with " + fArr[i] + " calling isValidAuthority() \n");
			   count2++;
		   }
	   }
	   
	   if(count1 == tArr.length && count2 == fArr.length)
		   System.out.print("\tIMPORTANT FINDING: Unamimous outcome - all calls (valid and invalid) to isValidAuthority have thrown error\n\n");
	   
	   if(count1 == tArr.length && !(count2 == fArr.length))
		   System.out.print("\tIMPORTANT FINDING: ALL CALLS TO isValidAuthority with VALID authorities have thown error.\n\n");
	   if(!(count2 == fArr.length) && count2 == fArr.length)
		   System.out.print("\tIMPORTANT FINDING: ALL CALLS TO isValidAuthority with INVALID authorities have thown error.\n\n");
	   
	   
	   // Attempt to test IPV4 and IPV6 addresses directly
	   System.out.println();
	   System.out.println("\tAttempting to create InetAddressValidator.getInstance(): \n");
	   try
	   {
		   InetAddressValidator iNet = InetAddressValidator.getInstance();
		   System.out.println();
		   System.out.println("\t\ttesting isValidInet6Address: \n");
		   result = iNet.isValidInet6Address("[FEDC:BA98:7654:3210:FEDC:BA98:7654:3210]");
		   if(!result)
			   System.out.println("\t\tFAIL: failure to correctly evaluate ipv6\n");
	   }
	   catch(Throwable t)
	   {
		   System.out.println("\t\tFAIL: Error in InetAddressValidator.getInstance()");
		   System.out.println("\t\tUnable to evaluate ANY ipv4 or ipv6 due to above error \n");
	   }
     
	   // TESTING AUTHORITIES IN CONTEXT
	   ResultPair[] testingAuthority =  
		   {	   
				   new ResultPair("http://" + "www.google.com", true),   
				   new ResultPair("https://" + "www.google.com", true),    							
				   new ResultPair("ftp://" + "www.google.com", true),    							
				   new ResultPair("http://" + "amazon.com.au", true),    							
				   new ResultPair("http://" + "0.0.0.0", true),          							
				   new ResultPair("http://" + "255.255.255.255", true),	 							
				   new ResultPair("http://" + "172.com", true),   	     							
				   new ResultPair("http://" + "youtube.cc", true), 	     													
				   new ResultPair("http://" + "255.255.255.255:81/path", true),	 						
				   new ResultPair("http://" + "[FEDC:BA98:7654:3210:FEDC:BA98:7654:3210]/path", true), 	
				   new ResultPair("http://" + "[1080:0:0:0:8:800:200C:417A]/path", true),            	
				   new ResultPair("http://" + "[3ffe:2a00:100:7031::1]/path", true),         			
				   new ResultPair("http://" + "[1080::8:800:200C:417A]/path", true),            	       
				   new ResultPair("http://" + "[2010:836B:4179::836B:4179]/path", true),                 
				   new ResultPair("http://" + "path", true),                  				 
				   new ResultPair("http://" + "user:password@host:81", true), 					
				   new ResultPair("http://" + "amazon.com.xx1", false),    							
				   new ResultPair("http://" + "172.ccc", false),   	     							
				   new ResultPair("http://" + "youtube.czq", false), 
				   new ResultPair("http://" + "172.192.172.256/path", false), 								
				   new ResultPair("http://" + "10.11.12.13.14/path", false),      						
				   new ResultPair("http://" + "1.2.3.4///", false),           							
				   new ResultPair("http://" + ".1.2.3.4", false),        							
				   new ResultPair("http://" + "go.a", false),            							
				   new ResultPair("http://" + "go.1aa", false),         							
				   new ResultPair("http://" + ".aaa", false),            							
				   new ResultPair("http://" + "aaa.", false),            							
				   new ResultPair("file://" + ":", false)	            							   
				   
		   };
	   
	   teststruct TestArr[] = new teststruct[28]; 
	   
	   for (int i = 0; i < 28; i++)
	   {
		   TestArr[i] = new teststruct();
		   TestArr[i].url = testingAuthority[i].item;
		   
		  if(i < 16)
			  TestArr[i].expect = true;
		  else
			  TestArr[i].expect = false;
	   }
	   
	   // call isValid()
	   for (int j = 0; j < 28; j++)
	   {
		   try
		   {
			   result = urlValAuth.isValid(TestArr[j].url);
			   TestArr[j].crash = false;
			   TestArr[j].isValidReturn = result; 
			   if(TestArr[j].expect == TestArr[j].isValidReturn)
				   TestArr[j].pass = true;
			   else
				   TestArr[j].pass = false;
				   
		   }
		   catch (Throwable t)
		   {
			   TestArr[j].crash = true;
		   }

	   }
	   
	   System.out.println();
	   System.out.println("\tUnit test calls to isValid() testing authority component . . . \n");
	   System.out.println("\t AUTHORITY UNIT TEST COMPLETE\n");
	   System.out.println("\tSee Full Unit Test Report in output 'Authority_Unit_Test_Results.txt' \n\n");
	   
	  
	   //teststruct.printResults(TestArr);
	   teststruct.printResultsToFile(TestArr, "Authority_Unit_Test_Results.txt", "IsValid() Calls Testing Authority NO-IANA");
	   
	   
   }
   
  
   public void testPath()
   {	   
	   String setting1 = "Double Slashes-No Fragments"; 
	   String setting2 = "Single Slash Only - Fragments Allowed";
	   boolean check1 = false;
	   boolean check2 = false;
	   boolean check3 = false;
	   boolean check4 = false;
	   boolean ran1 = false;
	   boolean ran2 = false;

       teststruct TestArr[] = new teststruct[11]; 
       teststruct TestArr2[] = new teststruct[11]; 
	   
	   // PART 1: Testing Double Slashes Allowed No Fragments Allowed
	   long options =
	            UrlValidator.ALLOW_ALL_SCHEMES
                + UrlValidator.ALLOW_2_SLASHES
                + UrlValidator.NO_FRAGMENTS;
	   
	   UrlValidator urlValPath = new UrlValidator(options);
	   
	   System.out.println("\tTesting constructor initialization and direct calls to isValidPath()\n");
	   
	   if (!urlValPath.isValidPath("/index.html"))
	   {
		   System.out.print("\t\t/index.html: FAIL\n");
		   check1 = true;
	   }
	   else if(urlValPath.isValidPath("/index.html"))
		   System.out.print("\t\t/index.html: PASS\n");
	   
	  
	   if (!urlValPath.isValidPath("/path//index.html"))
	   {
		   System.out.print("\t\t/path//index.html: FAIL\n");
		   check2 = true;
	   }
	   else if(urlValPath.isValidPath("/path//index.htm"))
		   System.out.print("\t\t/path//index.htm: PASS\n");
		  
	   
	   if (urlValPath.isValidPath("/../index.html"))
	   {
		   System.out.print("\t\t/../index.html: FAIL\n");
		   check3 = true;
	   }
	   else if(!urlValPath.isValidPath("/../index.html"))
		   System.out.print("\t\t/../index.html: PASS\n");
	   
	   if (urlValPath.isValidPath("/index.html#fragment.html"))
	   {
		   System.out.print("\t\t/index.html#fragment: FAIL\n");
		   check4 = true;
	   }
	   else if(!urlValPath.isValidPath("/../index.html#fragment"))
		   System.out.print("\t\t/index.html#fragment: PASS\n");
	   
	   
	   // only if we pass all the direct calls to isValidPath()
	   if(!(check1 || check2 || check3 || check4))
	   {
		   ran1 = true; 
		   
		   System.out.println("\t .... \n");
		   
		   ResultPair[] testingPath =  
			   {	   
					   new ResultPair("http://www.google.com" + "", true),   							// with null path
					   new ResultPair("http://www.google.com" + "/", true),								// with trailing / 
					   new ResultPair("index.html", true),												// with null authority no // for path
					   new ResultPair("http://www.google.com" + "/index.html", true),         			// Valid for both 
					   new ResultPair("http://www.google.com" + "/word/index.html", true),    			// Valid for both       							
					   new ResultPair("http://www.google.com" + "/word//index.html", true),   			// allow two slashes (False in part 2 only) 	
					   new ResultPair("http://www.google.com" + "/index.html#fragment", false), 		// False on part 1 
					   new ResultPair("http://www.google.com" + "/word//index.html#fragment", false),	// allow two slashes (False in part 1 and 2)   							     							
					   new ResultPair("http://www.google.com" + "/../", false),							// false in both parts 	 							
					   new ResultPair("http://www.google.com" + "/../index.html", false),				// false in both parts   
					   new ResultPair("http://www.google.com" + "/#/index.html", false),				// false in both parts     							

			   };
		   
		   for (int i = 0; i < 11; i++)
		   {
			   TestArr[i] = new teststruct();
			   TestArr[i].url = testingPath[i].item;
			   if(i < 6) 
				   TestArr[i].expect = true; 
			   else
				   TestArr[i].expect = false; 
		   }
		   
		   // call isValid()
		   boolean result;
		   for (int j = 0; j < 11; j++)
		   {
			   try
			   {
				   result = urlValPath.isValid(TestArr[j].url);
				   TestArr[j].crash = false;
				   TestArr[j].isValidReturn = result; 
				   if(TestArr[j].expect == TestArr[j].isValidReturn)
					   TestArr[j].pass = true;
				   else
					   TestArr[j].pass = false;
					   
			   }
			   catch (Throwable t)
			   {
				   TestArr[j].crash = true;
			   }

		   }
		   
		   System.out.println("\tPATH UNIT TEST 1 COMPLETE \n");
		   System.out.println("\tSee full results in 'Path_Unit_Test1.txt''\n\n");
		   teststruct.printResultsToFile(TestArr, "Path_Unit_Test1.txt", "PathTest1");
		   
	   }
	   
	   else
	   {
		   System.out.println("\tFull Path Unit Test not run due to preliminary validation bugs detected \n");
	   }

	   
	   
	   // PART 2: No double slash, fragments allowed (default constructor)
	   long options2 = UrlValidator.ALLOW_ALL_SCHEMES;

	   check1 = false;
	   check2 = false;
	   check3 = false;
	   check4 = false;
	   
	   UrlValidator urlValPath2 = new UrlValidator(options2);
	   
	   System.out.println("\tTesting constructor initialization and direct calls to isValidPath() no double slash, fragments allowed\n");
	   
	   if (!urlValPath.isValidPath("/index.html"))
	   {
		   System.out.print("\t\t/index.html: FAIL\n");
		   check1 = true;
	   }
	   else if(urlValPath.isValidPath("/index.html"))
		   System.out.print("\t\t/index.html: PASS\n");
	   
	   if (!urlValPath.isValidPath("/path//index.html"))
	   {
		   System.out.print("\t\tpath//index.html: FAIL\n");
		   check2 = true;
	   }
	   else if(urlValPath.isValidPath("/path//index.htm"))
		   System.out.print("\t\tpath//index.htm: PASS\n");
	   
	   if (!urlValPath.isValidPath("/site//index.html"))
	   {
		   System.out.print("\t\t/site//index.html: FAIL\n");
		   check3 = true;
	   }
	   else if(urlValPath.isValidPath("/site//index.html"))
		   System.out.print("\t\t/site//index.html: PASS\n");
	   
	   // only if we pass all the direct calls to isValidPath()
	   if(!(check1 || check2 || check3 ))
	   {
		   ran2 = true; 
		   
		   System.out.println("\t .... \n");
		   
		   ResultPair[] testingPath2 =  
			   {	   
					   new ResultPair("http://www.google.com" + "", true),   							// [0] with null path
					   new ResultPair("http://www.google.com" + "/", true),								// [1] with trailing / 
					   new ResultPair("index.html", true),												// [2] with null authority no // for path
					   new ResultPair("http://www.google.com" + "/index.html", true),         			// [3] Valid for both 
					   new ResultPair("http://www.google.com" + "/word/index.html", true),    			// [4] Valid for both       							
					   new ResultPair("http://www.google.com" + "/word//index.html", false),   			// [5] allow two slashes (False in part 2 only) 	
					   new ResultPair("http://www.google.com" + "/index.html#fragment", true), 	    	// [6] False on part 1 
					   new ResultPair("http://www.google.com" + "/word//index.html#fragment", false),	// [7] allow two slashes (False in part 1 and 2)   							     							
					   new ResultPair("http://www.google.com" + "/../", false),							// [8] false in both parts 	 							
					   new ResultPair("http://www.google.com" + "/../index.html", false),				// [9] false in both parts   
					   new ResultPair("http://www.google.com" + "/#/index.html", true),		    		// [10] false in both parts     							

			   };
		   
		   for (int i = 0; i < 11; i++)
		   {
			   TestArr2[i] = new teststruct();
			   TestArr2[i].url = testingPath2[i].item;
			   if(i < 7 && i != 5 || i == 10) 
				   TestArr2[i].expect = true; 
			   else
				   TestArr2[i].expect = false; 
		   }
		   
		   // call isValid()
		   boolean result;
		   for (int j = 0; j < 11; j++)
		   {
			   try
			   {
				   result = urlValPath2.isValid(TestArr2[j].url);
				   TestArr2[j].crash = false;
				   TestArr2[j].isValidReturn = result; 
				   if(TestArr2[j].expect == TestArr2[j].isValidReturn)
					   TestArr2[j].pass = true;
				   else
					   TestArr2[j].pass = false;
					   
			   }
			   catch (Throwable t)
			   {
				   TestArr2[j].crash = true;
			   }

		   }
		   
		   System.out.println("\tPATH UNIT TEST 2 COMPLETE \n");
		   System.out.println("\tSee full results in 'Path_Unit_2Test1.txt''\n\n");
		   teststruct.printResultsToFile(TestArr, "Path_Unit_Test2.txt", "PathTest2");   
	   } 
	   else
	   {
		   System.out.println("\tFull Path Unit Test 2 not run due to preliminary validation bugs detected \n");
	   }

	   // If we ran both unit test versions, we can run the comparison
	   if(ran1 && ran2)
	   {
		   System.out.println("\tComparison of Path options test results in output 'ComparePathSettingsTest.txt'\n\n");
		   teststruct.printCompareTestStructArraysToFile(TestArr, TestArr2, setting1, setting2, "ComparePathSettingsTest.txt", "Compare Path Settings Test");
	   } 
   }
   
   
   public void testQuery()
   {
	   System.out.println();
       System.out.println("UNIT TEST: Testing query evaluation of isValid()");
       String tArr[] = 
           {
                   "https:\\regexr.com/",
                   "http:\\example.com/over/there?name=ferret",
                   "http:\\example.com/path/to/page?name=ferret&color=purple",
                   "\\foo.html?e0a72cb2a2c7",
                   "\\bar.html?e0a72cb2a2c7",
                   "http:\\example.com/?@bar._=???/1:",
                   "\\S?"
           };
       String fArr[] = 
           {
                   "",
                   "172.192.172.256", 
                   "10.11.12.13.14", 
                   "1.1.1.1.", 
                   "1.2.3", 
                   ".1.2.3.4",
                   "go.a", 
                   "go.1aa", 
                   ".aaa",
                   "aaa.", 
                   "aaa"
           };
        long options =
            UrlValidator.ALLOW_ALL_SCHEMES;
       UrlValidator urlValQuerValidator = new UrlValidator(options);
       for( int iterator = 0; iterator < tArr.length; iterator++ )
       {
           boolean result = urlValQuerValidator.isValidAuthority(tArr[iterator]);

           try{
            if(urlValQuerValidator.isValidQuery(tArr[iterator])) result = true;
            if(!urlValQuerValidator.isValidQuery(fArr[iterator])) result = true;
           }
           catch (Throwable t)
           {
               result = false;
           }
       }

       System.out.println("QueryTests Passed");          
   }
	   
   public void testRandomPorts()
   {
	   System.out.println("TESTING RANDOM PORTS \n\n");
	   
	   long options =
	            UrlValidator.ALLOW_ALL_SCHEMES;
	   
	   UrlValidator urlValRandPorts = new UrlValidator(options);
	   // create arrays of port numbers
	   
	   // TEST DEPENDS ON ASSUMING http://www.google.com returns valid
	   try 
	   {
		   boolean preliminary = urlValRandPorts.isValid("http://www.google.com");
	   }
	   catch (Exception e)
	   {
		   System.out.println("\tCRASHED on preliminary test case: http://www.google.com\n\n");
		   return; 
	   }
	   
	   int min = 1; 
	   int max = 65535;
	   int ArrValid[] = new int[2000];
	   
	   for(int i = 0; i < 2000; i++)
	   {
		   int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		   ArrValid[i] = randomNum;
	   }
	  
	   // create random port numbers out of range (expect all to be invalid)
	   int min2 = 65536; 
	   int max2 = 10000000;
	   int ArrInValid[] = new int[2000];
	   
	   
	   for(int i = 0; i < 2000; i++)
	   {
		   int randomNum = ThreadLocalRandom.current().nextInt(min2, max2 + 1);
		   ArrInValid[i] = randomNum;
	   }
	   
	   String prePort = "http://www.google.com:";
	   String auth = "www.google.com:";
	   String postPort = "/test1";
	   
	   teststruct TestArr[] = new teststruct[2000]; 
	   
	   for (int i = 0; i < 2000; i++)
	   {
		   String s=String.valueOf(ArrValid[i]);
		   TestArr[i] = new teststruct();
		   TestArr[i].url = prePort + s + postPort; 
		   
		   TestArr[i].expect = true;
		  
	   }
	   boolean result;
	   String resultArr[] = new String[2000];
	   
	   System.out.println();
	   System.out.println("\tTesting random valid ports in isValidAuthority() call: \n");
	   
	   for(int j = 0; j < 2000; j++)
	   {
		   	try
	   		{
			   String p = String.valueOf(ArrValid[j]);
			   String q = auth + p;
			   result = urlValRandPorts.isValidAuthority(q);
			   if(result)
				   resultArr[j] = "true";
			   else
				   resultArr[j] = "false";
			  
	   		}
	   		catch(Throwable t)
	   		{
	   			resultArr[j] = "Exception Thrown";
	   		}
	  
	   }
	   
	   // count up Pass, Fail, Exceptions
	   int numTrue = 0;
	   int numFalse = 0; 
	   int numExceptions1 = 0; 
	   
	   for(int k = 0; k < 2000; k++)
	   {
		   if(resultArr[k] == "true")
			   numTrue++;
		   else if(resultArr[k] == "false")
			   numFalse++;
		   else if(resultArr[k] == "Exception Thrown")
			   numExceptions1++;
	   }
	   
	   // print summary from random valid port call to isValidAuthoirty
	   System.out.println();
	   System.out.println("\tSummary of results for random valid ports called to isValidAuthoirty(): \n");
	   System.out.println("\t\t# return TRUE (PASS): " + numTrue + "\n\n");
	   System.out.println("\t\t# return FALSE (FAIL): " + numFalse + "\n\n");
	   System.out.println("\t\t# return Exception Thrown (FAIL): " + numExceptions1 + "\n\n");
	   
	   
	   // REPEAT WITH INVALID PORTS
	   teststruct TestArr2[] = new teststruct[2000]; 
	   
	   for (int i = 0; i < 2000; i++)
	   {
		   String s=String.valueOf(ArrInValid[i]);
		   TestArr2[i] = new teststruct();
		   TestArr2[i].url = prePort + s + postPort; 
		   
		   TestArr2[i].expect = false;
		  
	   }
	   boolean result2;
	   String resultArr2[] = new String[2000];
	   
	   System.out.println();
	   System.out.println("\tTesting random invalid ports in isValidAuthority() call: \n");
	   
	   for(int j = 0; j < 2000; j++)
	   {
		   	try
	   		{
			   String p = String.valueOf(ArrInValid[j]);
			   String q = auth + p;
			   result = urlValRandPorts.isValidAuthority(q);
			   if(result)
				   resultArr2[j] = "true";
			   else
				   resultArr2[j] = "false";
			  
	   		}
	   		catch(Throwable t)
	   		{
	   			resultArr2[j] = "Exception Thrown";
	   		}
	  
	   }
	   
	   // count up Pass, Fail, Exceptions
	   numTrue = 0;
	   numFalse = 0; 
	   int numExceptions2 = 0; 
	   
	   for(int k = 0; k < 2000; k++)
	   {
		   if(resultArr2[k] == "true")
			   numTrue++;
		   else if(resultArr2[k] == "false")
			   numFalse++;
		   else if(resultArr2[k] == "Exception Thrown")
			   numExceptions2++;
	   }
	   
	   // print summary from random invalid port call to isValidAuthoirty
	   System.out.println();
	   System.out.println("\tSummary of results for random INVALID ports called to isValidAuthoirty(): \n");
	   System.out.println("\t\t# return TRUE (FAIL): " + numTrue + "\n\n");
	   System.out.println("\t\t# return FALSE (PASS): " + numFalse + "\n\n");
	   System.out.println("\t\t# return Exception Thrown (FAIL): " + numExceptions2 + "\n\n");
	   
	   
	   // if everything crashed on direct calls to isValidAuthority()
	   if(numExceptions1 == 2000)
	   {
		   System.out.println();
		   System.out.println("\t 100% crash rate on valid ports in isValidAuthoirty() calls, no further testing with isValid() \n");
	   }
	   else
	   {
		   System.out.println();
		   System.out.println("\t Now calling isValid() with valid ports . . . \n");
		   // NOW CALL isValid() with the same ports (starting with valid ports)
		   for (int j = 0; j < 2000; j++)
		   {
			   try
			   {
				   result = urlValRandPorts.isValid(TestArr[j].url);
				   TestArr[j].crash = false;
				   TestArr[j].isValidReturn = result; 
				   if(TestArr[j].expect == TestArr[j].isValidReturn)
					   TestArr[j].pass = true;
				   else
					   TestArr[j].pass = false;
					   
			   }
			   catch (Throwable t)
			   {
				   TestArr[j].crash = true;
			   }

		   }
		  
		   // print results to file
		   System.out.println("\tRANDOM TESTING OF VALID PORTS COMPLETE  \n");
		   System.out.println("\tSee full results in 'Random_Testing_Valid_Ports.txt'\n\n");
		   teststruct.printResultsToFile(TestArr, "Random_Testing_Valid_Ports.txt", "Random Testing of Valid Ports");   
		   
	   }
	   
	   
	   // Do the same for invalid ports
	   
	   // if everything crashed on direct calls to isValidAuthority()
	   if(numExceptions2 == 2000)
	   {
		   System.out.println();
		   System.out.println("\t 100% crash rate on valid ports in isValidAuthoirty() calls, no further testing with isValid() \n");
	   }
	   
	   else
	   {
		   
		   System.out.println();
		   System.out.println("\t Now calling isValid() with invalid ports . . . \n");
		   // call isValid() with the INVALID ports
		   for (int j = 0; j < 2000; j++)
		   {
			   try
			   {
				   result = urlValRandPorts.isValid(TestArr2[j].url);
				   TestArr2[j].crash = false;
				   TestArr2[j].isValidReturn = result; 
				   if(TestArr2[j].expect == TestArr2[j].isValidReturn)
					   TestArr2[j].pass = true;
				   else
					   TestArr2[j].pass = false;
					   
			   }
			   catch (Throwable t)
			   {
				   TestArr2[j].crash = true;
			   }

		   }
		   
		   // print results to file
		   System.out.println("\tRANDOM TESTING OF INVALID PORTS COMPLETE  \n");
		   System.out.println("\tSee full results in 'Random_Testing_Invalid_Ports.txt'\n\n");
		   teststruct.printResultsToFile(TestArr, "Random_Testing_Invalid_Ports.txt", "Random Testing of Invalid Ports");   
	   }
	   
	   
   }
   
   public void testRandomIP()
   {
	   // create random correct ipv4 expect all valid
	   
	   // ipv4 format is 0-255.0-255.0-255.0-255
	   System.out.println("TESTING RANDOM IP ADDRESSES \n\n");
	   
	   long options =
	            UrlValidator.ALLOW_ALL_SCHEMES;
	   
	   UrlValidator urlValRandPorts = new UrlValidator(options);
	   // create arrays of port numbers
	   
	   // TEST DEPENDS ON ASSUMING http://www.google.com returns valid
	   try 
	   {
		   boolean preliminary = urlValRandPorts.isValid("http://www.google.com");
	   }
	   catch (Exception e)
	   {
		   System.out.println("\tCRASHED on preliminary test case: http://www.google.com\n\n");
		   return; 
	   }
	   
	   System.out.println("\tPart 1: Test Random Valid ipv4 \n\n");
	   

	  // BUILDING ARRAY OF VALID IPV4
	  int min = 0; 
	  int max = 255;
	  int ArrValid[] = new int[8000];
	   
	  // generate 8000 numbers in range 
	  for(int i = 0; i < 8000; i++)
	  {
		   int randomNum = ThreadLocalRandom.current().nextInt(min, max);
		   // System.out.println(randomNum);
		   ArrValid[i] = randomNum;
	  }
	  // create 4000 ip addresses by concatenation
	  
	  String validIPV4[] = new String[2000];
	  int a, b, c, d;
	  int count = 0;
	  
	  for(int i = 0; i < 8000; i+=4)
	  {
		  a = ArrValid[i];
		  b = ArrValid[i+1];
		  c = ArrValid[i+2];
		  d = ArrValid[i+3];
		  
		  String e=String.valueOf(a);
		  String f=String.valueOf(b);
		  String g=String.valueOf(c);
		  String h=String.valueOf(d);

		  String ipv4 = e + "." + f + "." + g + "." + h;
		  
		  // for debugging
		  // System.out.println(ipv4);
		  
		  validIPV4[count] = ipv4;
		  count++;
	  }
	  
	  	  // BUILDING ARRAY OF INVALID IPV4
		  min = 255; 
		  max = 1000;
		  
		  int ArrInvalid[] = new int[8000];
		   
		  // generate 8000 numbers out of range 
		  for(int i = 0; i < 8000; i++)
		  {
			   int randomNum = ThreadLocalRandom.current().nextInt(min, max);
			   
			   // make every third value negative
			   if(i % 3 == 0)
				   randomNum = randomNum * -1;
			   
			   //System.out.println(randomNum);
			   
			   ArrInvalid[i] = randomNum;
		  }
		  // create 4000 ip addresses by concatenation
		  
		  String invalidIPV4[] = new String[2000];
		  count = 0;
		  
		  for(int i = 0; i < 8000; i+=4)
		  {
			 
			  a = ArrInvalid[i];
			  b = ArrInvalid[i+1];
			  c = ArrInvalid[i+2];
			  d = ArrInvalid[i+3];
			  
			  String q=String.valueOf(a);
			  
			  String r=String.valueOf(b);

			  String s=String.valueOf(c);

			  String t=String.valueOf(d);
			  

			  String ipv4 = q + "." + r + "." + s + "." + t;
			  
			  // for debugging
			  // System.out.println(ipv4);
			  
			  invalidIPV4[count] = ipv4;
			  count++;
			  
		  }
	  
	      // BUILD ARRAY OF VALID IPV6
	      // ex 2001:0db8:85a3:0000:0000:8a2e:0370:7334
	      // ASCII 97 to 102 for lower case letters, 48 to 57 for 0-9
		  
	      // fill array with random ASCII 97 to 102
		  String ArrASCII_1[] = new String[32000];
		  for(int i = 0; i < 32000; i++)
		  {
			   int randomNum = ThreadLocalRandom.current().nextInt(97, 102);
			   // System.out.println(randomNum);
			   char ch = (char)randomNum;
			   String s=String.valueOf(ch);
			   ArrASCII_1[i] = s;
		  }
		  
	      // fill array with random ASCII 48 to 57
		  String ArrASCII_2[] = new String[32000];
		  for(int i = 0; i < 32000; i++)
		  {
			   int randomNum = ThreadLocalRandom.current().nextInt(48, 57);
			   // System.out.println(randomNum);
			   char ch = (char)randomNum;
			   String s=String.valueOf(ch);
			   ArrASCII_2[i] = s;
		  }
		  
		  // create format from random array contents 2001:0db8:85a3:0000:0000:8a2e:0370:7334
		  // create 2000 ipv6 strings
		  String q1, q2, q3, q4, q5, q6, q7, q8;
		  String qq, rr, ss, tt;
		  String ipv6;
		  count = 0; 
		  String validIPV6[] = new String[1000];
		  String validIPV6_nobrackets[] = new String[1000];
		  
		  for(int i = 0; i < 32000; i+=32)
		  {
			  // if i is divisible by 3 start with letters
			  if(i % 3 == 0)
			  {
				  qq = ArrASCII_2[i];
				  rr = ArrASCII_1[i + 1];
				  ss = ArrASCII_2[i + 2];
				  tt = ArrASCII_1[i + 3];
			  }
			  else
			  {
				  qq = ArrASCII_1[i];
				  rr = ArrASCII_2[i + 1];
				  ss = ArrASCII_1[i + 2];
				  tt = ArrASCII_2[i + 3];
			  }
			  
			  q1 = qq + rr + ss + tt;
			  
			  // if i is divisible by 2 start with letters
			  if(i % 2 == 0)
			  {
				  qq = ArrASCII_2[i + 4];
				  rr = ArrASCII_1[i + 5];
				  ss = ArrASCII_2[i + 6];
				  tt = ArrASCII_1[i + 7];
			  }
			  else
			  {
				  qq = ArrASCII_1[i + 4];
				  rr = ArrASCII_2[i + 5];
				  ss = ArrASCII_1[i + 6];
				  tt = ArrASCII_2[i + 7];
			  }
			  
			  q2 = qq + rr + ss + tt;
			  
			  // if i is divisible by 2 start with letters
			  if(i % 2 == 0)
			  {
				  qq = ArrASCII_2[i + 8];
				  rr = ArrASCII_1[i + 9];
				  ss = ArrASCII_1[i + 10];
				  tt = ArrASCII_1[i + 11];
			  }
			  else
			  {
				  qq = ArrASCII_1[i + 8];
				  rr = ArrASCII_1[i + 9];
				  ss = ArrASCII_1[i + 10];
				  tt = ArrASCII_2[i + 11];
			  }
			  
			  q3 = qq + rr + ss + tt;
			  
			  // if i is divisible by 3 start with numbers
			  if(i % 3 == 0)
			  {
				  qq = ArrASCII_2[i + 12];
				  rr = ArrASCII_2[i + 13];
				  ss = ArrASCII_2[i + 14];
				  tt = ArrASCII_1[i + 15];
			  }
			  else
			  {
				  qq = ArrASCII_1[i + 12];
				  rr = ArrASCII_2[i + 13];
				  ss = ArrASCII_2[i + 14];
				  tt = ArrASCII_1[i + 15];
			  }
			  
			  q4 = qq + rr + ss + tt;
			  
			  // if i is divisible by 3 start with letters
			  if(i % 3 == 0)
			  {
				  qq = ArrASCII_2[i + 16];
				  rr = ArrASCII_1[i + 17];
				  ss = ArrASCII_2[i + 18];
				  tt = ArrASCII_1[i + 19];
			  }
			  else
			  {
				  qq = ArrASCII_1[i + 16];
				  rr = ArrASCII_2[i + 17];
				  ss = ArrASCII_1[i + 18];
				  tt = ArrASCII_2[i + 19];
			  }
			  
			  q5 = qq + rr + ss + tt;
			  
			  // if i is divisible by 2 start with letters
			  if(i % 2 == 0)
			  {
				  qq = ArrASCII_2[i + 20];
				  rr = ArrASCII_1[i + 21];
				  ss = ArrASCII_2[i + 22];
				  tt = ArrASCII_1[i + 23];
			  }
			  else
			  {
				  qq = ArrASCII_1[i + 20];
				  rr = ArrASCII_2[i + 21];
				  ss = ArrASCII_1[i + 22];
				  tt = ArrASCII_2[i + 23];
			  }
			  
			  q6 = qq + rr + ss + tt;
			  
			  // if i is divisible by 2 start with letters
			  if(i % 2 == 0)
			  {
				  qq = ArrASCII_2[i + 24];
				  rr = ArrASCII_1[i + 25];
				  ss = ArrASCII_1[i + 26];
				  tt = ArrASCII_1[i + 27];
			  }
			  else
			  {
				  qq = ArrASCII_1[i + 24];
				  rr = ArrASCII_1[i + 25];
				  ss = ArrASCII_1[i + 26];
				  tt = ArrASCII_2[i + 27];
			  }
			  
			  q7 = qq + rr + ss + tt;
			  
			  // if i is divisible by 3 start with numbers
			  if(i % 3 == 0)
			  {
				  qq = ArrASCII_2[i + 28];
				  rr = ArrASCII_2[i + 29];
				  ss = ArrASCII_2[i + 30];
				  tt = ArrASCII_1[i + 31];
			  }
			  else
			  {
				  qq = ArrASCII_1[i + 28];
				  rr = ArrASCII_2[i + 29];
				  ss = ArrASCII_2[i + 30];
				  tt = ArrASCII_1[i + 31];
			  }
			  
			  q8 = qq + rr + ss + tt;
			  
			  
			  String ipv6_no_bracket;
			  // if count divisible by 13, add extra ":" instead of a chars
			  if(count % 5 == 0)
			  {
				  ipv6 = "[" + q1 + ":" + q2 + ":" + q3 + ":" + q4 + "::" + "]";
				  ipv6_no_bracket =  q1 + ":" + q2 + ":" + q3 + ":" + q4 + "::";
			  }
			  else
			  {
				  ipv6 = "[" + q1 + ":" + q2 + ":" + q3 + ":" + q4 + ":" + q5 + ":" + q6 + ":" + q7 + ":" + q8 + "]";
				  ipv6_no_bracket = q1 + ":" + q2 + ":" + q3 + ":" + q4 + ":" + q5 + ":" + q6 + ":" + q7 + ":" + q8;
			  }
			  
			  validIPV6[count] = ipv6;
			  validIPV6_nobrackets[count] = ipv6_no_bracket;
			  // for debugging
			  // System.out.println(ipv6);
			  count ++;
			  
		  }
		  
		  // BUILD ARRAY OF INVALID IPV6
	      // ex 2001:0db8:85a3:0000:0000:8a2e:0370:7334
	      // ASCII 97 to 102 for lower case letters, 33 to 47 (nonsense characters)
		  
	      // fill array with random ASCII 97 to 102
		  String ArrASCII_3[] = new String[32000];
		  for(int i = 0; i < 32000; i++)
		  {
			   int randomNum = ThreadLocalRandom.current().nextInt(97, 102);
			   // System.out.println(randomNum);
			   char ch = (char)randomNum;
			   String s=String.valueOf(ch);
			   ArrASCII_3[i] = s;
		  }
		  
	      // fill array with random ASCII 48 to 57
		  String ArrASCII_4[] = new String[32000];
		  for(int i = 0; i < 32000; i++)
		  {
			   int randomNum = ThreadLocalRandom.current().nextInt(33, 47);
			   // System.out.println(randomNum);
			   char ch = (char)randomNum;
			   String s=String.valueOf(ch);
			   ArrASCII_4[i] = s;
		  }
		  
		  count = 0; 
		  String invalidIPV6[] = new String[1000];
		  String invalidIPV6_no_brackets[] = new String[1000];
		  
		  for(int i = 0; i < 32000; i+=32)
		  {
			  // if i is divisible by 3 start with letters
			  if(i % 3 == 0)
			  {
				  qq = ArrASCII_3[i];
				  rr = ArrASCII_4[i + 1];
				  ss = ArrASCII_2[i + 2];
				  tt = ArrASCII_1[i + 3];
			  }
			  else
			  {
				  qq = ArrASCII_4[i];
				  rr = ArrASCII_2[i + 1];
				  ss = ArrASCII_1[i + 2];
				  tt = ArrASCII_3[i + 3];
			  }
			  
			  q1 = qq + rr + ss + tt;
			  
			  // if i is divisible by 2 start with letters
			  if(i % 2 == 0)
			  {
				  qq = ArrASCII_4[i + 4];
				  rr = ArrASCII_4[i + 5];
				  ss = ArrASCII_4[i + 6];
				  tt = ArrASCII_4[i + 7];
			  }
			  else
			  {
				  qq = ArrASCII_3[i + 4];
				  rr = ArrASCII_4[i + 5];
				  ss = ArrASCII_3[i + 6];
				  tt = ArrASCII_4[i + 7];
			  }
			  
			  q2 = qq + rr + ss + tt;
			  
			  // if i is divisible by 2 start with letters
			  if(i % 2 == 0)
			  {
				  qq = ArrASCII_2[i + 8];
				  rr = ArrASCII_1[i + 9];
				  ss = ArrASCII_3[i + 10];
				  tt = ArrASCII_4[i + 11];
			  }
			  else
			  {
				  qq = ArrASCII_3[i + 8];
				  rr = ArrASCII_3[i + 9];
				  ss = ArrASCII_3[i + 10];
				  tt = ArrASCII_3[i + 11];
			  }
			  
			  q3 = qq + rr + ss + tt;
			  
			  // if i is divisible by 3 start with numbers
			  if(i % 3 == 0)
			  {
				  qq = ArrASCII_4[i + 12];
				  rr = ArrASCII_3[i + 13];
				  ss = ArrASCII_4[i + 14];
				  tt = ArrASCII_3[i + 15];
			  }
			  else
			  {
				  qq = ArrASCII_4[i + 12];
				  rr = ArrASCII_4[i + 13];
				  ss = ArrASCII_4[i + 14];
				  tt = ArrASCII_4[i + 15];
			  }
			  
			  q4 = qq + rr + ss + tt;
			  
			  // if i is divisible by 3 start with letters
			  if(i % 3 == 0)
			  {
				  qq = ArrASCII_1[i + 16];
				  rr = ArrASCII_1[i + 17];
				  ss = ArrASCII_3[i + 18];
				  tt = ArrASCII_1[i + 19];
			  }
			  else
			  {
				  qq = ArrASCII_1[i + 16];
				  rr = ArrASCII_4[i + 17];
				  ss = ArrASCII_4[i + 18];
				  tt = ArrASCII_4[i + 19];
			  }
			  
			  q5 = qq + rr + ss + tt;
			  
			  // if i is divisible by 2 start with letters
			  if(i % 2 == 0)
			  {
				  qq = ArrASCII_2[i + 20];
				  rr = ArrASCII_3[i + 21];
				  ss = ArrASCII_3[i + 22];
				  tt = ArrASCII_4[i + 23];
			  }
			  else
			  {
				  qq = ArrASCII_3[i + 20];
				  rr = ArrASCII_3[i + 21];
				  ss = ArrASCII_4[i + 22];
				  tt = ArrASCII_4[i + 23];
			  }
			  
			  q6 = qq + rr + ss + tt;
			  
			  // if i is divisible by 2 start with letters
			  if(i % 2 == 0)
			  {
				  qq = ArrASCII_4[i + 24];
				  rr = ArrASCII_4[i + 25];
				  ss = ArrASCII_3[i + 26];
				  tt = ArrASCII_3[i + 27];
			  }
			  else
			  {
				  qq = ArrASCII_3[i + 24];
				  rr = ArrASCII_3[i + 25];
				  ss = ArrASCII_3[i + 26];
				  tt = ArrASCII_4[i + 27];
			  }
			  
			  q7 = qq + rr + ss + tt;
			  
			  // if i is divisible by 3 start with numbers
			  if(i % 3 == 0)
			  {
				  qq = ArrASCII_4[i + 28];
				  rr = ArrASCII_3[i + 29];
				  ss = ArrASCII_4[i + 30];
				  tt = ArrASCII_3[i + 31];
			  }
			  else
			  {
				  qq = ArrASCII_4[i + 28];
				  rr = ArrASCII_3[i + 29];
				  ss = ArrASCII_4[i + 30];
				  tt = ArrASCII_3[i + 31];
			  }
			  
			  q8 = qq + rr + ss + tt;
			  
			  String ipv6_no_bracket;
			  // if count divisible by 7, add extra ":" instead of a chars
			  if(count % 7 == 0)
			  {
				  ipv6 = "[" + q1 + ":" + q2 + ":" + q3 + ":" + q4 + "::" + "]";
				  ipv6_no_bracket =  q1 + ":" + q2 + ":" + q3 + ":" + q4 + "::";
			  }
			  else
			  {
				  ipv6 = "[" + q1 + ":" + q2 + ":" + q3 + ":" + q4 + ":" + q5 + ":" + q6 + ":" + q7 + ":" + q8 + "]";
				  ipv6_no_bracket = q1 + ":" + q2 + ":" + q3 + ":" + q4 + ":" + q5 + ":" + q6 + ":" + q7 + ":" + q8;
			  }
			  
			  invalidIPV6[count] = ipv6;
			  invalidIPV6_no_brackets[count] = ipv6_no_bracket;
			  // for debugging
			  // System.out.println(ipv6);
			  count ++;
		  }
		  
		  // Attempt to test IPV4 and IPV6 addresses directly
		   
		   System.out.println();
		   System.out.println("\tAttempting to create InetAddressValidator.getInstance(): \n");
		   try
		   {
			   InetAddressValidator iNet = InetAddressValidator.getInstance();
			   System.out.println("\t\t Result: Successful InetAddressValidator creation \n");
			   
			   // run tests on ipv addresses directly and store results for printing
			   String resultArr1[] = new String[2000];
			   String resultArr2[] = new String[2000];
			   String resultArr3[] = new String[1000];
			   String resultArr4[] = new String[1000];
			   
			   for(int j = 0; j < 2000; j++)
			   {
				   	try
			   		{
				   		boolean result = iNet.isValidInet4Address(validIPV4[j]);
					    if(result)
						    resultArr1[j] = "true";
					    else
						    resultArr1[j] = "false";
					  
			   		}
			   		catch(Throwable t)
			   		{
			   			resultArr1[j] = "Exception Thrown";
			   		}
			  
			   }
			   for(int j = 0; j < 2000; j++)
			   {
				   	try
			   		{
				   		boolean result = iNet.isValidInet4Address(invalidIPV4[j]);
					    if(result)
						    resultArr2[j] = "true";
					    else
						    resultArr2[j] = "false";
					  
			   		}
			   		catch(Throwable t)
			   		{
			   			resultArr2[j] = "Exception Thrown";
			   		}
			  
			   }
			   for(int j = 0; j < 1000; j++)
			   {
				   	try
			   		{
				   		boolean result = iNet.isValidInet6Address(validIPV6_nobrackets[j]);
					    if(result)
						    resultArr3[j] = "true";
					    else
						    resultArr3[j] = "false";
					  
			   		}
			   		catch(Throwable t)
			   		{
			   			resultArr3[j] = "Exception Thrown";
			   		}
			  
			   }
			   for(int j = 0; j < 1000; j++)
			   {
				   	try
			   		{
				   		boolean result = iNet.isValidInet6Address(invalidIPV6_no_brackets[j]);
					    if(result)
						    resultArr4[j] = "true";
					    else
						    resultArr4[j] = "false";
					  
			   		}
			   		catch(Throwable t)
			   		{
			   			resultArr4[j] = "Exception Thrown";
			   		}
			  
			   }
			   
			   // count up results and print
			   // count up Pass, Fail, Exceptions
			   int numTrue = 0;
			   int numFalse = 0; 
			   int numExceptions = 0; 
			   
			   for(int k = 0; k < 2000; k++)
			   {
				   if(resultArr1[k] == "true")
					   numTrue++;
				   else if(resultArr1[k] == "false")
					   numFalse++;
				   else if(resultArr1[k] == "Exception Thrown")
					   numExceptions++;
			   }
			   
			   // print summary from random valid ipv4 call to isValidInet
			   System.out.println();
			   System.out.println("\tSummary of results for random VALID ipv4 called to isValidInet4Addres(): \n");
			   System.out.println("\t\t# return TRUE (PASS): " + numTrue + "\n\n");
			   System.out.println("\t\t# return FALSE (FAIL): " + numFalse + "\n\n");
			   System.out.println("\t\t# return Exception Thrown (FAIL): " + numExceptions + "\n\n");
			   
			   numTrue = 0;
			   numFalse = 0; 
			   numExceptions = 0; 
			   
			   for(int k = 0; k < 2000; k++)
			   {
				   if(resultArr2[k] == "true")
					   numTrue++;
				   else if(resultArr2[k] == "false")
					   numFalse++;
				   else if(resultArr2[k] == "Exception Thrown")
					   numExceptions++;
			   }
			   // print summary from random invalid ipv4 call to isValidInet
			   System.out.println();
			   System.out.println("\tSummary of results for random INVALID ipv4 called to isValidInet4Addres(): \n");
			   System.out.println("\t\t# return TRUE (PASS): " + numTrue + "\n\n");
			   System.out.println("\t\t# return FALSE (FAIL): " + numFalse + "\n\n");
			   System.out.println("\t\t# return Exception Thrown (FAIL): " + numExceptions + "\n\n");
			  
			   numTrue = 0;
			   numFalse = 0; 
			   numExceptions = 0; 
			   
			   for(int k = 0; k < 1000; k++)
			   {
				   if(resultArr3[k] == "true")
					   numTrue++;
				   else if(resultArr3[k] == "false")
					   numFalse++;
				   else if(resultArr3[k] == "Exception Thrown")
					   numExceptions++;
			   }
			   // print summary from random invalid ipv4 call to isValidInet
			   System.out.println();
			   System.out.println("\tSummary of results for random VALID ipv6 called to isValidInet6Addres(): \n");
			   System.out.println("\t\t# return TRUE (PASS): " + numTrue + "\n\n");
			   System.out.println("\t\t# return FALSE (FAIL): " + numFalse + "\n\n");
			   System.out.println("\t\t# return Exception Thrown (FAIL): " + numExceptions + "\n\n");
			   
			   numTrue = 0;
			   numFalse = 0; 
			   numExceptions = 0; 
			   
			   for(int k = 0; k < 1000; k++)
			   {
				   if(resultArr4[k] == "true")
					   numTrue++;
				   else if(resultArr4[k] == "false")
					   numFalse++;
				   else if(resultArr4[k] == "Exception Thrown")
					   numExceptions++;
			   }
			   // print summary from random invalid ipv4 call to isValidInet
			   System.out.println("\tSummary of results for random INVALID ipv6 called to isValidInet6Addres(): \n");
			   System.out.println("\t\t# return TRUE (PASS): " + numTrue + "\n\n");
			   System.out.println("\t\t# return FALSE (FAIL): " + numFalse + "\n\n");
			   System.out.println("\t\t# return Exception Thrown (FAIL): " + numExceptions + "\n\n");
			   
		   }
		   catch(Throwable t)
		   {
			   System.out.println("\t\tFAIL: Error in InetAddressValidator.getInstance()");
			   System.out.println("\t\tUnable to evaluate ANY ipv4 or ipv6 due to above error \n");
		   }
	      
		  
		   // Create teststructs to test ipv with isValid() call
		   String prePort = "ftp://";
		   String postPort = "/test1";
		   
		   teststruct TestArr1[] = new teststruct[2000];
		   teststruct TestArr2[] = new teststruct[2000];
		   teststruct TestArr3[] = new teststruct[1000];
		   teststruct TestArr4[] = new teststruct[1000];
		   
		   for (int i = 0; i < 2000; i++)
		   {
			   TestArr1[i] = new teststruct();
			   TestArr1[i].url = prePort + validIPV4[i] + postPort; 
			   TestArr1[i].expect = true;
		   }
		   for (int i = 0; i < 2000; i++)
		   {
			   TestArr2[i] = new teststruct();
			   TestArr2[i].url = prePort + invalidIPV4[i] + postPort; 
			   TestArr2[i].expect = false;
		   }
		   for (int i = 0; i < 1000; i++)
		   {
			   TestArr3[i] = new teststruct();
			   TestArr3[i].url = prePort + validIPV6[i] + postPort; 
			   TestArr3[i].expect = true;
		   }
		   for (int i = 0; i < 1000; i++)
		   {
			   TestArr4[i] = new teststruct();
			   TestArr4[i].url = prePort + invalidIPV6[i] + postPort; 
			   TestArr4[i].expect = true;
		   }

		   // NOW CALL isValid() with various test structs
		   for (int j = 0; j < 2000; j++)
		   {
			   try
			   {
				   boolean result = urlValRandPorts.isValid(TestArr1[j].url);
				   TestArr1[j].crash = false;
				   TestArr1[j].isValidReturn = result; 
				   if(TestArr1[j].expect == TestArr1[j].isValidReturn)
					   TestArr1[j].pass = true;
				   else
					   TestArr1[j].pass = false;
					   
			   }
			   catch (Throwable t)
			   {
				   TestArr1[j].crash = true;
			   }

		   }
		   for (int j = 0; j < 2000; j++)
		   {
			   try
			   {
				   boolean result = urlValRandPorts.isValid(TestArr2[j].url);
				   TestArr2[j].crash = false;
				   TestArr2[j].isValidReturn = result; 
				   if(TestArr2[j].expect == TestArr2[j].isValidReturn)
					   TestArr2[j].pass = true;
				   else
					   TestArr2[j].pass = false;
					   
			   }
			   catch (Throwable t)
			   {
				   TestArr2[j].crash = true;
			   }

		   }
		   for (int j = 0; j < 1000; j++)
		   {
			   try
			   {
				   boolean result = urlValRandPorts.isValid(TestArr3[j].url);
				   TestArr3[j].crash = false;
				   TestArr3[j].isValidReturn = result; 
				   if(TestArr3[j].expect == TestArr3[j].isValidReturn)
					   TestArr3[j].pass = true;
				   else
					   TestArr3[j].pass = false;
					   
			   }
			   catch (Throwable t)
			   {
				   TestArr3[j].crash = true;
			   }

		   }
		   for (int j = 0; j < 1000; j++)
		   {
			   try
			   {
				   boolean result = urlValRandPorts.isValid(TestArr4[j].url);
				   TestArr4[j].crash = false;
				   TestArr4[j].isValidReturn = result; 
				   if(TestArr4[j].expect == TestArr4[j].isValidReturn)
					   TestArr4[j].pass = true;
				   else
					   TestArr4[j].pass = false;
					   
			   }
			   catch (Throwable t)
			   {
				   TestArr4[j].crash = true;
			   }

		   }
		   
		   
		   // Print results to file
		   
		   //teststruct.printResults(TestArr1);
		   System.out.println("\tSee full results in 'Random_Test_Valid_IPV4.txt'");
		   teststruct.printResultsToFile(TestArr1, "Random_Test_Valid_IPV4.txt", "Random Valid IPV4 Ports Test");
		   
		   //teststruct.printResults(TestArr2);
		   System.out.println("\tSee full results in 'Random_Test_Inalid_IPV4.txt'");
		   teststruct.printResultsToFile(TestArr2, "Random_Test_Invalid_IPV4.txt", "Random Inalid IPV4 Ports Test");
		   
		   //teststruct.printResults(TestArr3);
		   System.out.println("\tSee full results in 'Random_Test_Valid_IPV6.txt'");
		   teststruct.printResultsToFile(TestArr3, "Random_Test_Valid_IPV6.txt", "Random Valid IPV6 Ports Test");
		   
		   //teststruct.printResults(TestArr4);
		   System.out.println("\tSee full results in 'Random_Test_Inalid_IPV6.txt'");
		   teststruct.printResultsToFile(TestArr4, "Random_Test_Invalid_IPV6.txt", "Random Invalid IPV6 Ports Test");
		   
		   // compare the results to elucidate additional contextual influences
		   String s1 = "Valid ipv4";
		   String s2 = "Invalid ipv4";
		   
		   // Prints all 2000 results compared
		   System.out.println("\tSee output file 'Random_Testing_IPV4_Comparative.txt' for ");
		   System.out.println("\tcomparative analysis between valid and invalid IPV4 calls\n");
		   String testName = "Comparing Valid and InValid IPV4 Results";
		   teststruct.printCompareTestStructArraysToFile(TestArr1, TestArr2, s1, s2, "Random_Testing_IPV4_Comparative.txt", testName);
		   
		   // compare the results to elucidate contextual influences
		   String s3 = "Valid ipv6";
		   String s4 = "Invalid ipv6";
		   
		   // Prints all 1000 results compared
		   System.out.println("\tSee output file 'Random_Testing_IPV6_Comparative.txt' for ");
		   System.out.println("\tcomparative analysis between valid and invalid IPV6 calls\n");
		   testName = "Comparing Valid and InValid IPV6 Results";
		   teststruct.printCompareTestStructArraysToFile(TestArr3, TestArr4, s3, s4, "Random_Testing_IPV6_Comparative.txt", testName);
		   
   }
  
}
