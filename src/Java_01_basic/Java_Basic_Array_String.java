package Java_01_basic;

public class Java_Basic_Array_String {

	public static void main(String[] args) {
		String automation = "Automation Testing Tutorials Online 123456";
		String lower = automation.toLowerCase();
		 int[] mangDem = new int[Character.MAX_VALUE];
		   char kyTu = 'a';
		    int count = 0;

//Count 'a' char
		int length = lower.length();
		for(int i = 0; i < length; i++) {
			if (lower.charAt(i) == kyTu) {
				count++;
			}
		}
		System.out.println("So ky tu 'a': " + count);
		
//Check 'Testing' in array
		System.out.println("Testing in array: " + automation.contains("Testing"));

//Start with 'Automation'
		System.out.println("Start with 'Automation': " + automation.startsWith("Automation"));
		
//End with 'Online'
		System.out.println("End with 'Online': " + automation.endsWith("Online"));
		
//Locate 'tutorials' in array
		System.out.println("Locate 'tutorials' in array: " + automation.indexOf("Tutorials"));
		
//Count number in array
//		for(int i=0;i<length;i++){
//            try{
//
//                mangDem[automation.charAt(i)]++;
//                System.out.println(mangDem[automation.charAt(i)]);
//            }catch(StringIndexOutOfBoundsException e){
//                System.out.println("index over limit");
//            }
//        }
//        for(int i=0;i<mangDem.length;i++){
//            if(mangDem[i]!=0){
//                System.out.println((char)i + ":" + mangDem[i]);
//            }
//        }
		
//Replace Online to Offline
		automation = automation.replace("Online", "Offline");
		System.out.print("New string: " + automation);
	}

}