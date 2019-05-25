import org.junit.Test;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int x;
		for(x=0; x< 10; x++){
			print(x);
		}
		
		System.out.println(x);
	}
	public static void print(int x){
		
		for(int y = 0; y <2; y++){
				System.out.println(x);
		}
	
	}
	@Test
	public void test(){
	    int x=3;
	    int y=3;
		String a = "hello";
		String b = "hello";
		System.out.println(x == y);
		System.out.println(a == b);
		System.out.println(a.equals(b));
	}

}
