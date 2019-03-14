
public class teststruct 
{
	String url; 
	boolean expect;
	boolean isValidReturn;
	boolean pass;
	boolean crash;
	
	// public static void means no need for an object 
	static void printResults(teststruct arr[])
	{
		System.out.println("PRINTING TEST OUTCOMES: \n");
		
		for(int i = 0; i < arr.length; i++)
		{
			System.out.print(i + 1);
			
			if(arr[i].crash == true)
			{
				System.out.print(": CRASHED on url: ");
				System.out.println(arr[i].url);
				System.out.println();
				continue;
			}
			
			System.out.print(": TESTING: ");
			System.out.println(arr[i].url);
			System.out.print("Expecting: "); 
			System.out.println(arr[i].expect);
			System.out.print("isValid() Return: ");
			System.out.println(arr[i].isValidReturn);
			System.out.print("Summary: ");
			if(arr[i].pass == true)
				System.out.println("PASS");
			else
				System.out.println("FAIL");
			
			System.out.println();
		}
	}
	
	
	// compare results as a function of validator settings (i.e. options)
	// arr1 and arr2 must be the same length
	static void compareTestStructArrays(teststruct arr1[], teststruct arr2[], String setting1, String setting2)
	{
		String arr1ExpectValid; 
		String arr2ExpectValid;
		String arr1ResultValid;
		String arr2ResultValid;
		
		System.out.println("Comparing results based on options: \n");
		System.out.println();
		System.out.println("OPTION 1: " + setting1);
		System.out.println("OPTION 2: " + setting2);
		System.out.println();
		
		
		for(int i = 0; i < arr2.length; i++)
		{
			// for more convenient access to data, note: isValidReturn will be meaningless in context of crash
			boolean arr1Expect = arr1[i].expect;
			
			if(arr1Expect)
				arr1ExpectValid = "Valid";
			else
				arr1ExpectValid = "Invalid";
			
			boolean arr2Expect = arr2[i].expect;
			
			if(arr2Expect)
				arr2ExpectValid = "Valid";
			else
				arr2ExpectValid = "Invalid";
			
			boolean arr1Result = arr1[i].isValidReturn;
			
			if(arr1Result)
				arr1ResultValid = "Valid";
			else
				arr1ResultValid = "Invalid";
			
			boolean arr2Result = arr2[i].isValidReturn;
			
			if(arr2Result)
				arr2ResultValid = "Valid";
			else
				arr2ResultValid = "Invalid";
			
			// get crash status
			boolean arr1Crash = arr1[i].crash;
			boolean arr2Crash = arr2[i].crash;
			
			System.out.print(i + 1);
			System.out.print(": TESTING: ");
			System.out.println(arr1[i].url + "\n");
			
			if(arr1Crash == true)
			{
				System.out.println(setting1 + ": Expected " + arr1ExpectValid + " and CRASHED");
			}
			else
			{
				System.out.println(setting1 + ": Expected " + arr1ExpectValid + "  Result: " + arr1ResultValid);
			}
			if(arr2Crash == true)
			{
				System.out.println(setting2 + ": Expected " + arr2ExpectValid + " and CRASHED");
			}
			else
			{
				System.out.println(setting2 + ": Expected " + arr2ExpectValid + "  Result: " + arr2ResultValid);
			}
			
			
			// Print out where passing occurs on both option settings
			if(!arr1Crash && !arr2Crash)
			{
				if(arr1[i].pass && arr2[i].pass)
				{
					System.out.println("PASS ON BOTH SETTINGS for: " + arr1[i].url);
					System.out.println();
				}
				else if((!arr1[i].pass && arr2[i].pass) || (arr1[i].pass && !arr2[i].pass)) 
				{
					System.out.println("SETTING DEPENDENCY DETECTED: Fails on only 1 setting ");
					System.out.println();
				}
			}
			
			// Print out where passing occurs on only 1
			else if(!arr1Crash && !arr2Crash)
			{
				if(!arr1[i].pass || !arr2[i].pass)
				{
					System.out.println("SETTING DEPENDENCY DETECTED: FAILS TEST ON ONLY ONE SETTING: " + arr1[i].url);
					System.out.println();
				}
			}
			
			
			// If no crash
			// Match on 4 possible scenarios: 
			// Expected both true but got one false
			// Expected both false but got one true
			// Expected true-false but got false-true
			// Expected false-true but got true-false
			if(!arr1Crash && !arr2Crash)
			{
				if(arr1Expect == true && arr2Expect == true)
				{
					if(arr1Result == false && arr2Result == false)
					{
						System.out.println("SETTING INDEPENDENT INVERSION DETECTED: Expected both valid but both returned invalid.\n");
					}
				}
				if(arr1Expect == false && arr2Expect == false)
				{
					if(arr1Result == true && arr2Result == true)
					{
						System.out.println("SETTING INDEPENDENT INVERSION DETECTED: Expected both invalid but both returned valid.\\n");
					}
				}
				if(arr1Expect == true && arr2Expect == false)
				{
					if(arr1Result == false || arr2Result == true)
					{
						System.out.println("SETTING DEPENDENT INVERSION DETECTED: Expected valid-invalid but got invalid-valid.\n");
					}
				}
				if(arr1Expect == false && arr2Expect == true)
				{
					if(arr1Result == true || arr2Result == false)
					{
						System.out.println("SETTING DEPENDENT INVERSION DETECTED: Expected invalid-valid but got valid-invalid.\n");
					}
				}
					
			}
			
			// find correspondence in crashing
			// 3 possible scenarios:
			// both crashed
			// arr1 crash, arr2 no crash
			// arr1 no crash, arr2 crash
			if(arr1Crash || arr2Crash)
			{
				if(arr1Crash && arr2Crash)
				{
					System.out.println("SETTING INDEPENDENCY DETECTED: Crashed on both settings \n");
				}
				if(arr1Crash && !arr2Crash)
				{
					System.out.println("SETTING DEPENDENCY DETECTED: Crashed only for: " + setting1 + "\n");
				}
				if(!arr1Crash && arr2Crash)
				{
					System.out.println("SETTING DEPENDENCY DETECTED: Crashed only for:  " + setting2 + "\n");
				}
			}
		}
		
	}
	
	
}