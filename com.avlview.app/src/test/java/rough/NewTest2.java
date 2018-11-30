package rough;

public class NewTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "101 - 200 of 200";

		// Returns index of first occurrence of character.
		int firstIndex = str.indexOf('o');
		System.out.println("First occurrence of char 's'" + " is found at : " + firstIndex);

		String substrtxt = str.substring(0, firstIndex);
		System.out.println(substrtxt);

	}

}
