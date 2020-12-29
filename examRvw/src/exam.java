import java.util.Iterator;

public class exam extends Object implements Iterable<Object> {
	  
	  // finds a factorial recursively
	  public static int factorial(int number) {
	  	// base case: argument is 1.
	  	if(number==1) return 1;
	  	return number * factorial(number-1);
	  }
	  
	  // returns true if prime, false otherwise
	  public static boolean primeChecker(int number) {
	  	// base cases: 0 and 1 are prime, even numbers are not prime
	  	if(number==0||number==1) return true;
	  	if(number%2==0) return false;
	  	
	  	// for all other cases we have to check by dividing it by all the preceding numbers
	  	for(int i=2; i<number; i++) {
	  		if(number % i == 0) {
	  			System.out.println(number + " is not prime. It is divisible by " + i);
	  			return false;
	  		}
	  	}
	  	System.out.println(number + " is prime.");
	  	return true;
	  }
	  
	  public static int mystery( LinkedNode<Double> current,  double value) {
	    if ( current == null) {
	      return 0;
	    }
	    int x = mystery(current.getNext(), value);
	    if ( current.getData() <= value ){
	      x++;
	    }
	    return x;
	  }
	  
	  public static void main(String[] args) {
	    Object s1 = new exam();
	    Iterable<Object> c = new exam();
	    exam t1 = new exam();
	    exam t2 = (exam)t1;
	    Object s2 = (Object) t1;
	    Object o2 = new Object();
	    exam t3 = (exam)o2;
	  }

		@Override
		public Iterator<Object> iterator() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
