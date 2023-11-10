package RSA;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
public class RSA implements Serializable{
	/*
	 * Update 15/2/2023 Author: Vuong Tung Duong
	 */
	// Khởi tạo các thuộc tính
	private int b0 = 73;
	private int p;
	private int q;
	private int n;
	private int phiN;
	private int a0;

	// Hàm khởi tạo
	public RSA() {
		this(0, 0, 73, 0, 0, 0);
	}

	public RSA(int P, int Q, int B0, int N, int PhiN, int A0) {
		this.p = P;
		this.q = Q;
		this.n = N;
		this.b0 = B0;
		this.phiN = PhiN;
		this.a0 = A0;
	}
	
	public RSA getRSA() {
		return this;
	}

	public int getB() {
		return b0;
	}

	public int getP() {
		return p;
	}

	public int getQ() {
		return q;
	}

	public int getN() {
		return n;
	}

	public int getA() {
		return a0;
	}
	public int getPhiN() {
		return phiN;
	}

	public void TaoKhoa() {
		// Sàng số nguyên tố trong khoảng 100 đến 229 được mảng "list"
		List<Integer> list = sangSoNguyenTo(50, 150);
		// Vòng lặp while đến khi phiN và b0 nguyên tố cùng nhau
		// Tạo 1 biến "chiSo" giá trị tối đa là (kích thước mảng "list"-1)
		int chiSo = (int) (Math.random() * list.size());
		// Gán giá trị cho "p" là phần tử có chỉ số là "chiSo" trong mảng "list"
		p = list.get(chiSo);
		// Vòng lặp while đến khi "p khác q"
		do {
			// Biến "chiSo" được gán cho 1 số random khác để random giá trị của "q"
			chiSo = (int) (Math.random() * list.size());
			q = list.get(chiSo);
		} while (q == p);
		// Gán giá trị cho "n"
		n = p * q;
		// Gán giá trị cho "phiN"
		phiN = (p - 1) * (q - 1);
		do {
			chiSo = (int) (Math.random() * list.size());
			b0 = list.get(chiSo);
		} while (timUCLN(phiN, b0) != 1);
		// gán giá trị cho "a0" bằng hàm tìm nghịch đảo của "b0" theo mod "phiN" (sử
		// dụng thuật toán Euclid mở rộng)
		a0 = timNghichDao(phiN, b0);
	}

	public int encrypt(int mess) {
		// Sử dụng công thuật toán bình phương và nhân để mã hóa 1 số int "mess" với
		// khóa {b0,n}
		return BpVaNhan(mess, b0, n);
	}

	public int decrypt(int cipher) {
		// Sử dụng công thuật toán bình phương và nhân để giải mã 1 số int "mess" với
		// khóa {a0,p,q}
		return BpVaNhan(cipher, a0, n);
	}

	public static int timUCLN(int a, int b) {
		// Hàm trả về gcd(a,b)
		if (a == 0) {
			return b;
		}
		if (b == 0) {
			return a;
		}
		
		while (b > 0) {
			int r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

	// Hàm kiểm tra số nguyên tố
	public static boolean kiemTraSoNguyenTo(int n) {
		if (n <= 1) {
			return false;
		}

		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}

	public String maHoa(String ChuoiVao) {
		byte[] mh1 = ChuoiVao.getBytes(StandardCharsets.UTF_8);
		String base64 = Base64.getEncoder().encodeToString(mh1);
		int[] mh2 = new int[base64.length()];
		for (int i = 0; i < base64.length(); i++) {
			mh2[i] = base64.charAt(i);
		}
		int[] mh3 = new int[mh2.length];
		for (int i = 0; i < mh2.length; i++) {
			mh3[i] = BpVaNhan(mh2[i], b0, n);
		}
		String str = "";
		for (int i = 0; i < mh3.length; i++) {
			str = str + (char) mh3[i];
		}
		byte[] data = str.getBytes(StandardCharsets.UTF_8);
		String data2 = Base64.getEncoder().encodeToString(data);
		return data2;
	}

	public String giaiMa(String ChuoiVao) {
		byte[] gm1 = Base64.getMimeDecoder().decode(ChuoiVao);
		String gm = new String(gm1, StandardCharsets.UTF_8);
		int[] gm2 = new int[gm.length()];
		for (int i = 0; i < gm.length(); i++) {
			gm2[i] = gm.charAt(i);
		}
		int[] gm3 = new int[gm2.length];
		for (int i = 0; i < gm3.length; i++) {
			gm3[i] = BpVaNhan(gm2[i], a0, n);
		}
		String str = "";
		for (int i = 0; i < gm3.length; i++) {
			str = str + (char) gm3[i];
		}
		byte[] data2 = Base64.getMimeDecoder().decode(str);
		return new String(data2, StandardCharsets.UTF_8);
	}

	public static String chuyenCoSo2(int n) {
		// Hàm trả về cơ số 2 (string) từ cơ số 10 "n"
		int du;
		String kq = "";
		while (n > 0) {
			du = n % 2;
			kq = String.valueOf(du).concat(kq);
			n /= 2;
		}
		return kq;
	}

	public static int BpVaNhan(int a, int b, int c) {
		// Trả về a mũ b theo mod c
		// Chuyển b sang cơ số 2 là 1 mảng char "b2" gồm 0 và 1
		char[] b2 = chuyenCoSo2(b).toCharArray();
		// Khởi tạo p = 1
		int p = 1;
		// Vòng lặp for theo từng phần tử của mảng "b2"
		for (int i = 0; i < b2.length; i++) {
			p = p * p;
			p = mod(p, c);
			// Nếu b2[i] = '1' thêm bước nhân với a
			if (b2[i] == '1') {
				p = p * a;
				p = mod(p, c);
			}
		}
		return p;
	}

	public static int mod(int a, int b) {
		// Hàm trả về giá trị của a mod b

		if (a % b < 0) {
			return a % b + b;
		}
		return a % b; 
	}

	public static int timNghichDao(int a, int b) {
		// Hàm trả về giá trị của phần tử nghịch đảo của "b" theo mod "a" (sử dụng thuật
		// toán Euclid mở rộng)
		int q1, q2, q3, r, s1 = 1, t1 = 0, s2 = 0, t2 = 1, tmp, mod = a;
		r = mod(a, b);
		q1 = a / b;
		tmp = r;
		r = mod(b, r);
		a = b;
		b = tmp;
		q2 = a / b;
		while (r > 0) {
			tmp = r;
			r = mod(b, r);
			a = b;
			b = tmp;
			q3 = a / b;
			tmp = s2;
			s2 = s1 - q1 * s2;
			s1 = tmp;
			tmp = t2;
			t2 = t1 - q1 * t2;
			t1 = tmp;
			q1 = q2;
			q2 = q3;
		}
		s2 = s1 - q1 * s2;
		t2 = t1 - q1 * t2;
		if(t2<0) t2=t2+mod;
		return t2;
	}

	public static List<Integer> sangSoNguyenTo(int m, int n) {
		// Hàm trả về 1 mảng các số nguyên tố bắt đầu từ "m" đến "n"
		List<Integer> kq = new ArrayList<Integer>();
		if (m <= 2) {
			kq.add(2);
			kq.add(3);
			for (int j = 5; j <= n; j += 2) {
				int count = 0;
				if (j % 3 == 0) {
					count++;
				} else {
					for (int k = 5; k <= Math.sqrt(j); k += 6) {
						if (j % k == 0) {
							count++;
							break;
						}
					}
					for (int k = 7; k <= Math.sqrt(j); k += 6) {
						if (j % k == 0) {
							count++;
							break;
						}
					}
				}
				if (count == 0)
					kq.add(j);
			}
		} else if (m == 3) {
			kq.add(3);
			for (int j = 5; j <= n; j += 2) {
				int count = 0;
				if (j % 3 == 0) {
					count++;
				} else {
					for (int k = 5; k <= Math.sqrt(j); k += 6) {
						if (j % k == 0) {
							count++;
							break;
						}

					}
					for (int k = 7; k <= Math.sqrt(j); k += 6) {
						if (j % k == 0) {
							count++;
							break;
						}
					}
				}
				if (count == 0)
					kq.add(j);
			}
		} else if (m > 3) {
			if (m % 2 == 0) {
				for (int j = m + 1; j <= n; j += 2) {
					int count = 0;
					if (j % 3 == 0) {
						count++;
					} else {
						for (int k = 5; k <= Math.sqrt(j); k += 6) {
							if (j % k == 0) {
								count++;
								break;
							}

						}
						for (int k = 7; k <= Math.sqrt(j); k += 6) {
							if (j % k == 0) {
								count++;
								break;
							}
						}
					}
					if (count == 0)
						kq.add(j);
				}
			} else if (m % 2 == 1) {
				for (int j = m; j <= n; j += 2) {
					int count = 0;
					if (j % 3 == 0) {
						count++;
					} else {
						for (int k = 5; k <= Math.sqrt(j); k += 6) {
							if (j % k == 0) {
								count++;
								break;
							}

						}
						for (int k = 7; k <= Math.sqrt(j); k += 6) {
							if (j % k == 0) {
								count++;
								break;
							}
						}
					}
					if (count == 0)
						kq.add(j);
				}
			}
		}
		return kq;
	}
}
