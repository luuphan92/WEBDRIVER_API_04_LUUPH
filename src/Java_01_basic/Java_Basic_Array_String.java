package Java_01_basic;

public class Java_Basic_Array_String {

	public static void main(String[] args) {
		String automation = "Automation Testing Tutorials Online 123456";
		String lower = automation.toLowerCase();
		   char kyTu = 'a';
		    int count1 = 0;
		    int count2 = 0;

//Count 'a' char
		int length = lower.length();
		for(int i = 0; i < length; i++) {
			if (lower.charAt(i) == kyTu) {
				count1++;
			}
		}
		System.out.println("So ky tu 'a': " + count1);
		
//Check 'Testing' in array
		System.out.println("Testing in array: " + automation.contains("Testing"));

//Start with 'Automation'
		System.out.println("Start with 'Automation': " + automation.startsWith("Automation"));
		
//End with 'Online'
		System.out.println("End with 'Online': " + automation.endsWith("Online"));
		
//Locate 'tutorials' in array
		System.out.println("Locate 'tutorials' in array: " + automation.indexOf("Tutorials"));
		
//Count number in array
		for(int i = 0; i < length; i ++)
            if(Character.isDigit(automation.charAt(i)))
                    count2++;
		System.out.println("so ky tu so trong chuoi la: " + count2);
		
//Replace Online to Offline
		automation = automation.replace("Online", "Offline");
		System.out.print("New string: " + automation);
	}

}