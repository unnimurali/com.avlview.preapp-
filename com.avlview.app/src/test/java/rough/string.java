package rough;

public class string {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// String s = " abc,cde,def,fgh";
		// System.out.println("subString1=" + s.substring(0, s.indexOf(",")));
		// System.out.println("subString2=" + s.substring(s.indexOf(",") + 1,
		// s.length()));

		String s1 = "This IMEI already exists in records, but assigned to MT 01 VH 1001 of .";

		System.out.println(s1.substring(0, s1.indexOf('u')).trim());

	}

}
