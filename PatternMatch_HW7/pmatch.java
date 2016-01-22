package PatternMatch_hw7;

import javax.swing.JOptionPane;


public class pmatch {
	
	/**
	 * this function will only match 0-9 and '-'s.
	 * @param parttern 0-9 | -
	 * @return
	 */
	static boolean myMatches(String myString, String parttern)
	{
		int splitIndex = 0;
		for(int i=0;i<parttern.length();i++)
		{
			if(parttern.charAt(i)=='-')
			{
				splitIndex = i;
			}
		}
		for(int i=0;i<myString.length();i++)
		{
			if(i!=splitIndex)
			{
				if(!(myString.charAt(i)<='9' && myString.charAt(i)>='0'))
				{
					return false;
				}
			}
		}
		return true;
	}
	
    private static final String STOP = "0";
    private static final String VALID = "Valid phone number";
    private static final String INVALID = "Not a valid phone number";
    private static final String VALID_PHONE_PATTERN
                   = "[0-9]{2}-[0-9]{4}-[0-9]{3}";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("****************FOR LEVEL 1.1-1.2********************");
        String phoneStr, reply;

        while (true) {
            phoneStr = JOptionPane.showInputDialog(null, "Input#:");
            if (phoneStr.equals(STOP)) break;
            if (phoneStr.matches(VALID_PHONE_PATTERN)) {
                reply = VALID;
            } else {
                reply = INVALID;
            }
            String Str = phoneStr.replaceAll("[8]", "eight");
            System.out.println("****************FOR LEVEL 1.3********************");
            JOptionPane.showMessageDialog(null,
                                          Str + ":\n" + reply);
            String str2 = phoneStr.replaceAll("[0-9]", "*");
            System.out.println("practice 1: "+str2);
            String str3 = phoneStr.replaceAll("[^0-9-]", "NOT!NUM");
            System.out.println("practice 2: "+str3);
            System.out.println("****************FOR LEVEL 3********************");
            String myParttern = "xxxx-xxxx";
            System.out.println("My match : "+myMatches("1111-2222",myParttern));//true
            System.out.println("My match : "+myMatches("11121-222",myParttern));//false
            System.out.println("My match : "+myMatches("cd1-22d22",myParttern));//false
        }
    }
}
