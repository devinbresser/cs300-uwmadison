import java.util.Iterator;
import java.util.LinkedList;
public class p7prequiz {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LinkedList<String> strings = new LinkedList<String>();
		strings.add("A");
		strings.add("C");
		strings.add("B");
		Iterator<String> it = strings.iterator();
		while(it.hasNext())
		  System.out.print(it.next());
		
		
	}

}
