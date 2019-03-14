

import junit.framework.TestCase;

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
	   System.out.println("UNIT TEST: Testing isValidScheme() on provided schemes");
	   
	   // TEST 1: DEFAULT SCHEMES
	   String[] DEFAULT_SCHEMES = {"http", "https", "ftp"}; 
	   UrlValidator urlVal = new UrlValidator(DEFAULT_SCHEMES);
	   
	   boolean initializationError;
	   boolean httpError = false;
	   boolean httpsError = false;
	   boolean ftpError = false;
	   
	   
	   System.out.println("Testing initialization of allowable schemes: http, https, ftp\n");
	   System.out.println("Call to isValidScheme() directly (not inValid())\n\n");
	   
	   // TEST isValidScheme call in context of instantiated UrlValidator
	   if (!urlVal.isValidScheme("http"))
	   {
		   System.out.print("http: FAIL\n");
		   httpError = true;
	   }
	   
	   // TEST isValidScheme call in context of instantiated UrlValidator
	   if (!urlVal.isValidScheme("https"))
	   {
		   System.out.print("https: FAIL\n");
		   httpsError = true;
	   }
		  
	   // TEST isValidScheme call in context of instantiated UrlValidator
	   if (!urlVal.isValidScheme("https"))
	   {
		   System.out.print("ftp: FAIL\n");
		   ftpError = true;
	   }
		  
	   if(!(httpError || httpsError || ftpError))
	   {
		   System.out.println();
		   System.out.println("Cross references of scheme validity in context:");
		   System.out.println("Testing above scheme validity in context of isValid() call: \n\n");
		   test1Run = true;
		   boolean preliminary;
		   setting1 = "Only: http/https/ftp"; 
		   
		   try 
		   {
			   preliminary = urlVal.isValid("http://www.google.com");
		   }
		   catch (Exception e)
		   {
			   System.out.println("CRASHED on preliminary test case: http://www.google.com");
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
		   
		   teststruct.printResults(TestArr);
		   
	   }
	   else
	   {
		   System.out.print("SUMMARY 'allowable scheme' initialization produces fatal error ");
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
		   System.out.print("http: FAIL;");
		   httpErrorB = true;
	   }
	   
	   // TEST isValidScheme call in context of instantiated UrlValidator
	   if (!urlVal2.isValidScheme("https"))
	   {
		   System.out.print("https: FAIL \n");
		   httpsErrorB = true;
	   }
		  
	   // TEST isValidScheme call in context of instantiated UrlValidator
	   if (!urlVal2.isValidScheme("ftp"))
	   {
		   System.out.print("ftp: FAIL \n");
		   ftpErrorB = true;
	   }
		  
	   if(!urlVal2.isValidScheme("Xttp"))
	   {
		   System.out.print("Xttp: FAIL \n");
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
			   System.out.print(fArr[k] + "FAIL \n");
			   fError = true;
		   }
	   }
	   
	   // if things that are supposed to be true evaluate to false, do not context tests
	   if(!(httpErrorB || httpsErrorB || ftpErrorB || XttpError || fError))
	   {
		   System.out.println();
		   System.out.println("Cross references of scheme validity in context:");
		   System.out.println("Testing ALLOW_ALL_SCHEME validity in context of isValid() call: \n\n");
		   test2Run = true;
		   try 
		   {
			   boolean preliminary = urlVal2.isValid("http://www.google.com");
		   }
		   catch (Exception e)
		   {
			   System.out.println("CRASHED on preliminary test case: http://www.google.com");
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
		   
		   teststruct.printResults(TestArr2);
		   
	   }
	   else
	   {
		   System.out.print("Error upon initialization of ALLOW_ALL_SCHEMES ");
		   if(httpErrorB && httpsErrorB && ftpErrorB && XttpError && fError)
			   System.out.println("TYPE: invalid scheme evaluation in all scheme input types\n\n");
		   else
			   System.out.println("TYPE: one or more schemes (alone) evaluate incorrectly\n\n");
		   
	   }
	   
	   // if we ran both full tests, we can compare the results
	   if(test1Run && test2Run)
	   {
		   // NOW COMPARE THE TWO TESTS STRUCT ARRAY RESULTS 
		   teststruct.compareTestStructArrays(TestArr, TestArr2, setting1, setting2);
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

	   System.out.println();
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
				   "255.255.255.255:81"
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
				   "aaa",
				   "[FEDC:BA98:7654:3210:FEDC:BA98:7654:3210]",
				   "[1080:0:0:0:8:800:200C:417A]",
				   "[3ffe:2a00:100:7031::1]",
				   "[1080::8:800:200C:417A]",
				   "[::192.9.5.5]",
				   "[::FFFF:129.144.52.38]",
				   "[2010:836B:4179::836B:4179]"
		   };
	   
	   
	   boolean result;
	   // call !isValidAuthority(authority) on valid authorities:
	   System.out.println("Testing isValidAuthority on valid authority strings (failures printed): \n");
	   int count1 = 0;
	   int count2 = 0;
	   
	   for(int i = 0; i < tArr.length; i++)
	   {
		   try
		   {
			   result = urlValAuth.isValidAuthority(tArr[i]);
			   if(!result)
				   System.out.println(tArr[i] + " FAIL");
		   }
		   catch(Throwable t)
		   {
			   System.out.println("Throwable error with " + tArr[i] + " calling isValidAuthority() \n");
			   count1++;
		   }
	   }
	   
	   System.out.println();
	   System.out.println("Testing isValidAuthority on invalid authority strings (failures printed): \n");
	   // call !isValidAuthority(authority) on invalid authorities
	   for(int i = 0; i < fArr.length; i++)
	   {
		   try
		   {
			   result = urlValAuth.isValidAuthority(fArr[i]);
			   if(!result)
				   System.out.println(fArr[i] + " FAIL");
		   }
		   catch(Throwable t)
		   {
			   System.out.println("Throwable error with " + fArr[i] + " calling isValidAuthority() \n");
			   count2++;
		   }
	   }
	   
	   
	   if(count1 == tArr.length && count2 == fArr.length)
		   System.out.print("IMPORTANT FINDING: Unamimous outcome - all calls (valid and invalid) to isValidAuthority have thrown error\n\n");
	   
	   if(count1 == tArr.length && !(count2 == fArr.length))
		   System.out.print("IMPORTANT FINDING: ALL CALLS TO isValidAuthority with VALID authorities have thown error.\n\n");
	   if(!(count2 == fArr.length) && count2 == fArr.length)
		   System.out.print("IMPORTANT FINDING: ALL CALLS TO isValidAuthority with INVALID authorities have thown error.\n\n");
	   
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
				   new ResultPair("http://" + "user:password@host:81", true),						// TEST username/password/port format 
				   new ResultPair("http://" + "255.255.255.255:81", true),	 						// TEST port option on host
				   new ResultPair("http://" + "172.192.172.256", false), 							// FALSE invalid IPV4 	
				   new ResultPair("http://" + "10.11.12.13.14", false),      						// FALSE invalid IPV4
				   new ResultPair("http://" + "1.1.1.1.", false),       							// FALSE invalid IPV4 format (trailing dot)
				   new ResultPair("http://" + "1.2.3", false),           							// FALSE invalid IPV4 (insufficient address components)
				   new ResultPair("http://" + ".1.2.3.4", false),        							// FALSE invalid IPV4 format (leading dot)
				   new ResultPair("http://" + "go.a", false),            							// FALSE invalid host
				   new ResultPair("http://" + "go.1aa", false),         							// FALSE invalid host 
				   new ResultPair("http://" + ".aaa", false),            							// FALSE invalid format
				   new ResultPair("http://" + "aaa.", false),            							// FALSE invalid format
				   new ResultPair("http://" + "aaa", false),            							// FALSE invalid format    
				   new ResultPair("http://" + "[FEDC:BA98:7654:3210:FEDC:BA98:7654:3210]", false), 	// FALSE: IPV6 
				   new ResultPair("http://" + "[1080:0:0:0:8:800:200C:417A]", false),            	// FALSE: IPV6
				   new ResultPair("http://" + "[3ffe:2a00:100:7031::1]", false),         			// FALSE: IPV6 
				   new ResultPair("http://" + "[1080::8:800:200C:417A]", false),            	    // FALSE: IPV6
				   new ResultPair("http://" + "[::192.9.5.5]", false),            				    // FALSE: IPV6
				   new ResultPair("http://" + "[::FFFF:129.144.52.38]", false),                     // FALSE: IPV6
				   new ResultPair("http://" + "[2010:836B:4179::836B:4179]", false)                 // FALSE: IPV6 (ipv6 addresses from: https://www.ietf.org/rfc/rfc2732.txt)
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
	   
	   
	   System.out.println("PART 2: Testing authority strings with full 'isValid' call: \n\n");
	   teststruct.printResults(TestArr);
	   
	   
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
