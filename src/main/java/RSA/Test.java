package RSA;

public class Test {

	public static void main(String[] args) {
		
		String testMess ="1aưhg";
		String encryptMess = null;
		String decryptMess = null;
		RSA key= new RSA();
		for(int i=0; i<1000; i++) {
			try {
				key.TaoKhoa();
				encryptMess = key.maHoa(testMess);
				decryptMess = key.giaiMa(encryptMess);
				if(!decryptMess.equals(testMess)) {
					System.out.println((decryptMess));
					System.out.print(key.getA()+" ");
					System.out.print(key.getB()+" ");
					System.out.print(key.getP()+" ");
					System.out.print(key.getQ()+" ");
					System.out.print(key.getN()+" ");
					System.out.print(key.getPhiN()+" ");
					System.out.print(key.timNghichDao(key.getPhiN(),key.getB())+" ");
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.print(key.getA()+" ");
				System.out.print(key.getB()+" ");
				System.out.print(key.getP()+" ");
				System.out.print(key.getQ()+" ");
				System.out.print(key.getN()+" ");
				System.out.print(key.timNghichDao(key.getPhiN(),key.getB())+" ");
			}
		}
	}

}
