package generics;
import java.util.ArrayList;

public class intro {

		public static void main(String[] args){				
		Double []s= new Double[3];
		s[0] =  100.58;
		 test<Integer> t = new test<Integer>();
		 
		 t.meth(s[0]);
		 
		}
	
		
}
class  test<t extends Number>{
	
	 void meth (Double s){	 
	      System.out.print(s );	  
}	
	 void meth (t x){	 
	      System.out.print(x );	  
  }	
	 
	
	 
	 
}
