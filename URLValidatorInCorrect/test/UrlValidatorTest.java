

import junit.framework.TestCase;
import java.util.concurrent.ThreadLocalRandom;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!


public class UrlValidatorTest extends TestCase 
{


   public UrlValidatorTest(String testName)
   {
      super(testName);
   }

   
   
   public void testManualTest()
   {
	   
	   // Very basic manual test that I did just to get going
	   
	  /* System.out.println();
	   System.out.println("MANUAL TEST: Call 12 urls of known validity\n");
	   // create a UrlValidator object and define "options"
	   long options =
	            UrlValidator.ALLOW_2_SLASHES
	                + UrlValidator.ALLOW_ALL_SCHEMES
	                + UrlValidator.NO_FRAGMENTS;
	   UrlValidator urlVal = new UrlValidator(options);
	   
	   // specifically choose the 12 URLS that we had in Final Project Part A: 
	   String valid1 = "http://255.255.255.255:80/test1/file?action=view";
	   String valid2 = "http://www.google.com:80";
	   String valid3 = "ftp://go.cc:65535";
	   String valid4 = "ftp://0.0.0.0:65535/t123?action=edit&mode=up";
	   String invalid1 = "://.1.2.3.4:65a";
	   String invalid2 = "http/go.1aa:-1";
	   String invalid3 = "3ht://aaa:65636";
	   String invalid4 = "3ht://go.com:80/test1?action=view";
	   String invalid5 = "http://1.2.3.4.5:65535/$23?action=edit&mode=up";
	   String invalid6 = "ftp://255.255.255.255:65a/test1/?action=view";
	   String invalid7 = "h3t://255.com:0/..?action=edit&mode=up";
	   String invalid8 = "://1.2.3:-1/../";
	   
	   String urls[] = {valid1, valid2, valid3, valid4, invalid1, invalid2, invalid3, invalid4, invalid5, invalid6, invalid7, invalid8};
	   
	   teststruct TestArr[] = new teststruct[12]; 
	   
	   for (int i = 0; i < 12; i++)
	   {
		   TestArr[i] = new teststruct();
		   TestArr[i].url = urls[i];
		   if(i > 3)
			   TestArr[i].expect = false; 
		   else
			   TestArr[i].expect = true; 
	   }
	   
	   boolean result;
	   for (int j = 0; j < 12; j++)
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
	   
	   System.out.print("Hello "); 
	   System.out.println("world");
	   
	   teststruct.printResults(TestArr);
	   */
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
	   
	   
	   System.out.println("\tTesting initialization of allowable schemes: http, https, ftp\n");
	   System.out.println("\tCall to isValidScheme() directly (not inValid())\n");
	   
	   // TEST isValidScheme call in context of instantiated UrlValidator
	   if (!urlVal.isValidScheme("http"))
	   {
		   System.out.print("\t\thttp: FAIL\n");
		   httpError = true;
	   }
	   
	   // TEST isValidScheme call in context of instantiated UrlValidator
	   if (!urlVal.isValidScheme("https"))
	   {
		   System.out.print("\t\thttps: FAIL\n");
		   httpsError = true;
	   }
		  
	   // TEST isValidScheme call in context of instantiated UrlValidator
	   if (!urlVal.isValidScheme("https"))
	   {
		   System.out.print("\t\tftp: FAIL\n");
		   ftpError = true;
	   }
		  
	   if(!(httpError || httpsError || ftpError))
	   {
		   System.out.println();
		   System.out.println("\tCross references of scheme validity in context:");
		   System.out.println("\tTesting above scheme validity in context of isValid() call: \n\n");
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
				   new ResultPair("h3t://" + "www.google.com", true),
				   new ResultPair("Xttp://" + "www.google.com", true),	
				   new ResultPair("htfp:/" + "www.google.com", false),  
				   new ResultPair("-ttp:" + "www.google.com", false),  
				   new ResultPair("htkp/" + "www.google.com", false),	
				   new ResultPair(":=/" + "www.google.com", false),   	
				   new ResultPair(">ht://" + "www.google.com", false), 
				   new ResultPair("Xttp:/" + "www.google.com", false),	
				   new ResultPair("h_tp:" + "www.google.com", false),   
				   new ResultPair("http/" + "www.google.com", false),   
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
			   if(i < 4) // CHANGE THIS
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
	   // TEST 1: DEFAULT SCHEMES
	   System.out.println();
	   System.out.println("UNIT TEST: Testing isValidScheme() on ALLOW_ALL_SCHEMES");
	   long options =
	            UrlValidator.ALLOW_ALL_SCHEMES;
	   setting2 = "All schemes allowed"; 
	   boolean httpErrorB = false;
	   boolean httpsErrorB = false;
	   boolean ftpErrorB = false;
	   boolean XttpError = false;
	   boolean fError = false;
	   
	   UrlValidator urlVal2 = new UrlValidator(options);
	   
	   // TEST isValidScheme call in context of instantiated UrlValidator
	   if (!urlVal2.isValidScheme("http"))
	   {
		   System.out.print("\t\thttp: FAIL;");
		   httpErrorB = true;
	   }
	   
	   // TEST isValidScheme call in context of instantiated UrlValidator
	   if (!urlVal2.isValidScheme("https"))
	   {
		   System.out.print("\t\thttps: FAIL \n");
		   httpsErrorB = true;
	   }
		  
	   // TEST isValidScheme call in context of instantiated UrlValidator
	   if (!urlVal2.isValidScheme("ftp"))
	   {
		   System.out.print("\t\tftp: FAIL \n");
		   ftpErrorB = true;
	   }
		  
	   if(!urlVal2.isValidScheme("Xttp"))
	   {
		   System.out.print("\t\tXttp: FAIL \n");
		   XttpError = true;
	   }
	   
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
			   System.out.print("		" + fArr[k] + "FAIL \n");
			   fError = true;
		   }
	   }
	   
	   // if things that are supposed to be true evaluate to false, do not context tests
	   if(!(httpErrorB || httpsErrorB || ftpErrorB || XttpError || fError))
	   {
		   System.out.println();
		   System.out.println("\tCross references of scheme validity in context:");
		   System.out.println("\tTesting ALLOW_ALL_SCHEME validity in context of isValid() call: \n\n");
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
		   
		   
		   // From: https://tools.ietf.org/html/rfc3986#section-3.1
		   //
		   // Scheme names consist of a sequence of characters beginning with a
		   // letter and followed by any combination of letters, digits, plus
		   // ("+"), period ("."), or hyphen ("-").  Although schemes are case-
		   // insensitive, the canonical form is lowercase and documents that
		   // specify schemes must do so with lowercase letters.  An implementation
		   // should accept uppercase letters as equivalent to lowercase in scheme
		   // names (e.g., allow "HTTP" as well as "http") for the sake of
		   // robustness but should only produce lowercase scheme names for
		   // consistency.
		   
		   
		   // create urls with invalid valid and invalid scheme while holding the rest constant
		   // ALLOW_ALL_SCHEMES requires that urls be properly formatted, see formatting requirements above
		   ResultPair[] testingScheme2 =  
			   {	   
					   new ResultPair("http://" + "www.google.com", true),
					   new ResultPair("ftp://" + "www.google.com", true),
					   new ResultPair("h3t://" + "www.google.com", true),
					   new ResultPair("Xttp://" + "www.google.com", true),	// TEST START WITH CAPITAL + valid format ://
					   new ResultPair("htfp:/" + "www.google.com", false),  // FALSE: invalid format, no ":/" allowed
					   new ResultPair("-ttp:" + "www.google.com", false),   // FALSE: invalid format begins with a non-letter and ":"
					   new ResultPair("htkp/" + "www.google.com", false),	// FALSE: inavlid format "/" not allowed
					   new ResultPair(":=/" + "www.google.com", false),   	// FALSE: invalid format begins with a non-letter, "="
					   new ResultPair(">ht://" + "www.google.com", false), 	// FALSE: invalid format begins with a non-letter
					   new ResultPair("Xttp:/" + "www.google.com", false),	// FALSE: invalid format, ":/" not allowed
					   new ResultPair("h_tp:" + "www.google.com", false),   // FALSE: invalid format "_" not allowed
					   new ResultPair("http/" + "www.google.com", false),   // FALSE: invalid format begins with a non-letter
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
			   
			  if(i < 4)
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
		   System.out.println("\tSee full results in 'Scheme_Unit_Test2.txt'");
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
		   System.out.println("Comparison of Scheme options test results in output 'CompareSchemeSettingsTest.txt'\n\n");
		   // NOW COMPARE THE TWO TESTS STRUCT ARRAY RESULTS 
		   //teststruct.compareTestStructArrays(TestArr, TestArr2, setting1, setting2);
		   teststruct.printCompareTestStructArraysToFile(TestArr, TestArr2, setting1, setting2, "CompareSchemeSettingsTest.txt", "Compare Scheme Settings Test");
	   }
	 
	   
	   
   }
   public void testAuthority()
   {
	   // Explaining test url evaluations in terms of valid/invalid 
	   // Authority validity defined by: https://en.wikipedia.org/wiki/URL
	   //
	   // Authority component is after scheme and before path
	   //           URI = scheme:[//authority]path[?query][#fragment]
	   //           Where: authority = [userinfo@]host[:port]
	   //
	   // Authority component is OPTIONAL (urls without authority can be valid)
	   //
	   //
	   //
	   // "An OPTIONAL authority component preceded by two slashes (//), comprising:
	   // 
	   // An optional userinfo subcomponent that may consist of a 
	   //    user name and an optional password preceded by a colon (:), 
	   //    followed by an at symbol (@). Use of the format username:password 
	   //    in the userinfo subcomponent is deprecated for security reasons. 
	   //    Applications should not render as clear text any data after the first colon (:) 
	   //    found within a userinfo subcomponent unless the data after the colon is
	   //    the empty string (indicating no password).
	   // 
	   // An optional host subcomponent, consisting of either
	   //    a registered name (including but not limited to a hostname), 
	   //    or an IP address. IPv4 addresses must be in dot-decimal notation, 
	   //    and IPv6 addresses must be enclosed in brackets ([]).[16][c]
	   // 
	   // An optional port subcomponent preceded by a colon (:).

	   System.out.println("UNIT TEST: Testing isValidAuthority() directly (not calling isValid() )"); 
	   
	   // TEST 1: TESTING DEFAULT SETTINGS - IPV6 inputs expected to be INVALID on default settings
	   long options =
	            UrlValidator.ALLOW_ALL_SCHEMES;
	   
	   UrlValidator urlValAuth = new UrlValidator(options);
	   String setting1 = "No IPV6"; 
	   
	   String tArr[] = 
		   {
				   "www.google.com",
				   "amazon.com.au",
				   "0.0.0.0",
				   "172.com",
				   "youtube.cc",
				   "user:password@host:81", 
				   "255.255.255.255:81",
				   "[FEDC:BA98:7654:3210:FEDC:BA98:7654:3210]",
				   "[1080:0:0:0:8:800:200C:417A]",
				   "[3ffe:2a00:100:7031::1]",
				   "[1080::8:800:200C:417A]",
				   "[::192.9.5.5]",
				   "[::FFFF:129.144.52.38]",
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
	   // call !isValidAuthority(authority) on valid authorities:
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
     
	   // PART 2: TESTING AUTHORITIES IN CONTEXT
	   ResultPair[] testingAuthority =  
		   {	   
				   new ResultPair("http://" + "www.google.com", true),   
				   new ResultPair("ftp://" + "www.google.com", true),    							// TEST that when not http:// but still standard
				   new ResultPair("h3t://" + "www.google.com", true),    							// TEST that when not http:// but still standard
				   new ResultPair("http://" + "amazon.com.au", true),    							// TEST .au 
				   new ResultPair("http://" + "0.0.0.0", true),          							// TEST lower bounds of IPV4
				   new ResultPair("http://" + "255.255.255.255", true),	 							// TEST upper bounds of IPV4
				   new ResultPair("http://" + "172.com", true),   	     							// TEST IP host 
				   new ResultPair("http://" + "youtube.cc", true), 	     							// TEST .cc
				   new ResultPair("http://" + "user:password@host:81/path", true),						// TEST username/password/port format 
				   new ResultPair("http://" + "255.255.255.255:81/path", true),	 						// TEST port option on host
				   new ResultPair("http://" + "[FEDC:BA98:7654:3210:FEDC:BA98:7654:3210]/path", true), 	// TEST: Valid IPV6 Type1 
				   new ResultPair("http://" + "[1080:0:0:0:8:800:200C:417A]/path", true),            	// TEST: Valid IPV6 Type2
				   new ResultPair("http://" + "[3ffe:2a00:100:7031::1]/path", true),         			// TEST: Valid IPV6 Type3
				   new ResultPair("http://" + "[1080::8:800:200C:417A]/path", true),            	        // TEST: Valid IPV6 Type4
				   new ResultPair("http://" + "[::192.9.5.5]/path", true),            				    // TEST: Valid IPV6 Type5
				   new ResultPair("http://" + "[::FFFF:129.144.52.38]/path", true),                      // TEST: Valid IPV6 Type6
				   new ResultPair("http://" + "[2010:836B:4179::836B:4179]/path", true),                 // TEST: Valid IPV6 Type7 (ipv6 addresses from: https://www.ietf.org/rfc/rfc2732.txt)
				   new ResultPair("http://" + "172.192.172.256/path", false), 							// FALSE invalid IPV4 	
				   new ResultPair("http://" + "10.11.12.13.14/path", false),      						// FALSE invalid IPV4
				   new ResultPair("http://" + "1.1.1.1./path", false),       							// FALSE invalid IPV4 format (trailing dot)
				   new ResultPair("http://" + "1.2.3", false),           							// FALSE invalid IPV4 (insufficient address components)
				   new ResultPair("http://" + ".1.2.3.4", false),        							// FALSE invalid IPV4 format (leading dot)
				   new ResultPair("http://" + "go.a", false),            							// FALSE invalid host
				   new ResultPair("http://" + "go.1aa", false),         							// FALSE invalid host 
				   new ResultPair("http://" + ".aaa", false),            							// FALSE invalid format
				   new ResultPair("http://" + "aaa.", false),            							// FALSE invalid format
				   new ResultPair("http://" + "aaa", false)	            							// FALSE invalid format    
		   };
	   
	   teststruct TestArr[] = new teststruct[27]; 
	   
	   for (int i = 0; i < 27; i++)
	   {
		   TestArr[i] = new teststruct();
		   TestArr[i].url = testingAuthority[i].item;
		   
		  if(i < 10)
			  TestArr[i].expect = true;
		  else
			  TestArr[i].expect = false;
	   }
	   
	   // call isValid()
	   for (int j = 0; j < 27; j++)
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
	   
	   System.out.println("\tSee Full Unit Test Report in output 'Authority_Unit_Test_Results.txt' \n\n");
	   //teststruct.printResults(TestArr);
	   teststruct.printResultsToFile(TestArr, "Authority_Unit_Test_Results.txt", "IsValid() Calls Testing Authority");
   }
   
   // NOTE: ALL WILL FAIL DUE TO isValidAuthoirty bug, but these tests relevant when that bug resolved
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
	   
	   // using ftp to get to isValidAuthority call (not called with http)
	   // furthermore anything with ":" and http returns false
	   String prePort = "ftp://www.google.com:";
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
	   int numExceptions = 0; 
	   
	   for(int k = 0; k < 2000; k++)
	   {
		   if(resultArr[k] == "true")
			   numTrue++;
		   else if(resultArr[k] == "false")
			   numFalse++;
		   else if(resultArr[k] == "Exception Thrown")
			   numExceptions++;
	   }
	   
	   // print summary from random valid port call to isValidAuthoirty
	   System.out.println();
	   System.out.println("\tSummary of results for random valid ports called to isValidAuthoirty(): \n");
	   System.out.println("\t\t# return TRUE (PASS): " + numTrue + "\n\n");
	   System.out.println("\t\t# return FALSE (FAIL): " + numFalse + "\n\n");
	   System.out.println("\t\t# return Exception Thrown (FAIL): " + numExceptions + "\n\n");
	   
	   
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
	   
	   // print summary from random valid port call to isValidAuthoirty
	   System.out.println();
	   System.out.println("\tSummary of results for random INVALID ports called to isValidAuthoirty(): \n");
	   System.out.println("\t\t# return TRUE (PASS): " + numTrue + "\n\n");
	   System.out.println("\t\t# return FALSE (FAIL): " + numFalse + "\n\n");
	   System.out.println("\t\t# return Exception Thrown (FAIL): " + numExceptions + "\n\n");
	   
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
	   // NOW CALL isValid() with the invalid ports
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
	   
	   // compare the results to elucidate contextual influences
	   String s1 = "valid port";
	   String s2 = "invalid port";
	   
	   // Prints all 2000 results compared
	   System.out.println("\tSee output file 'Random_Testing_Ports_Comparative.txt' for ");
	   System.out.println("\tcomparative analysis between valid and invalid port calls\n");
	   String testName = "Comparing Valid and InValid Port Test Results";
	   teststruct.printCompareTestStructArraysToFile(TestArr, TestArr2, s1, s2, "Random_Testing_Ports_Comparative.txt", testName);
	   
	   
   }
   
// NOTE: ALL WILL FAIL DUE TO isValidAuthoirty bug, but these tests will be useful when that bug resolved
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
			  
			  
			  // if count divisible by 13, add extra ":" instead of a chars
			  if(count % 5 == 0)
			  {
				  ipv6 = "[" + q1 + ":" + q2 + ":" + q3 + ":" + q4 + "::" + "]";
			  }
			  else
			  {
				  ipv6 = "[" + q1 + ":" + q2 + ":" + q3 + ":" + q4 + ":" + q5 + ":" + q6 + ":" + q7 + ":" + q8 + "]";
			  }
			  
			  validIPV6[count] = ipv6;
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
			  
			  
			  // if count divisible by 13, add extra ":" instead of a chars
			  if(count % 7 == 0)
			  {
				  ipv6 = "[" + q1 + ":" + q2 + ":" + q3 + ":" + q4 + "::" + "]";
			  }
			  else
			  {
				  ipv6 = "[" + q1 + ":" + q2 + ":" + q3 + ":" + q4 + ":" + q5 + ":" + q6 + ":" + q7 + ":" + q8 + "]";
			  }
			  
			  invalidIPV6[count] = ipv6;
			  // for debugging
			  // System.out.println(ipv6);
			  count ++;
		  }
		  
		  // CREATED AT THIS POINT
	      // 1 array of valid 1pv4 size 2000 validIPV4
		  // 1 array of invalid 1pv4 size 2000 invalidIPV4
		  // 1 array valid ipv6 size 1000 validIPV6
		  // 1 array invalid ipv6 size 1000 invalidIPV6
		  
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
				   		boolean result = iNet.isValidInet6Address(validIPV6[j]);
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
				   		boolean result = iNet.isValidInet6Address(invalidIPV6[j]);
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
			   
			   // print summary from random valid ipv4 call to isValidAuthoirty
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
			   // print summary from random invalid ipv4 call to isValidAuthoirty
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
			   // print summary from random invalid ipv4 call to isValidAuthoirty
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
			   // print summary from random invalid ipv4 call to isValidAuthoirty
			   System.out.println();
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
   
   // POSSIBLE OTHER UNIT TESTS DEPENDING ON DANEIL'S TESTS
   /*public void testPath()
   {
	   //System.out.println();
	   //System.out.println("UNIT TEST: Testing isValidPath() call in isValid()"); 
	   
   }
   public void testQuery()
   {
	   //System.out.println();
	   //System.out.println("UNIT TEST: Testing isValidQuery() call in isValid()"); 
	   
   }
   public void testFragment()
   {
	   //System.out.println();
	   //System.out.println("UNIT TEST: Testing isValidFragment() call in isValid()"); 
	   
   }

   */
   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing	   

   }
   
   public void testYourSecondPartition()
   {
		 //You can use this function to implement your Second Partition testing	   

   }
   //You need to create more test cases for your Partitions if you need to 
  
   public void testIsValid()
   {
	   //You can use this function for programming based testing
	   
	   // ?

   }
   


}
