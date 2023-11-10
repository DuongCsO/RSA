package RSA;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


import java.awt.Color;

import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;


public class ViewAT2 extends JFrame {

	private JPanel contentPane;
	private JTextField txtNhapP;
	private JTextField txtNhapQ;
	private JTextField txtNhapB;
	private JTextPane txtXuatMaHoa;
	private JTextPane txtNhapMaHoa;
	private JTextPane txtXuatGiaiMa;
	private JTextPane txtNhapGiaiMa;
	private JRadioButton rdbtnTaoKhoaTuDong;
	private JRadioButton rdbtnTaoKhoaTuyChon;
	private JLabel lblKhoaCongKhai;
	private JLabel lblKhoaBiMat;
	private RSA rsa = new RSA();
	private boolean checkKhoa = false;
	private JTextField txtLinkFileMH;
	private int typeFile = 0;
	private int typeFile2 = 0;
	private JTextField txtLinkFileGM;
	private JButton btnMaHoa;
	private JButton btnGiaiMa;
	private JButton btnChuyen;
	private JButton btnLuuFile1;
	private JButton btnLuuFile2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAT2 frame = new ViewAT2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public ViewAT2() throws IOException {
		
		setTitle("Mã hóa RSA - Lớp 20223IT6001001 - Nhóm 5 - Vương Tùng Dương - 2020606212");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(130, 50, 1100, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);

		JPanel panelMaHoa = new JPanel();
		panelMaHoa.setLayout(null);
		panelMaHoa.setBorder(new LineBorder(Color.BLUE, 2, true));
		panelMaHoa.setBounds(360, 10, 350, 560);
		contentPane.add(panelMaHoa);

		txtXuatMaHoa = new JTextPane();
		txtXuatMaHoa.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, null));
		txtXuatMaHoa.setFont(new Font("Arial", Font.PLAIN, 14));
		txtXuatMaHoa.setBounds(10, 320, 330, 170);
		txtXuatMaHoa.setEditable(false);
		panelMaHoa.add(txtXuatMaHoa);

		JScrollPane sc1 = new JScrollPane(txtXuatMaHoa);
		sc1.setBounds(8, 318, 334, 174);
		panelMaHoa.add(sc1);

		JLabel lblXuatMaHoa = new JLabel("Bản mã:");
		lblXuatMaHoa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblXuatMaHoa.setBounds(10, 285, 120, 25);
		panelMaHoa.add(lblXuatMaHoa);

		btnMaHoa = new JButton("Mã hóa");
		btnMaHoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maHoaView();
			}
		});
		btnMaHoa.setBounds(10, 260, 168, 25);
		panelMaHoa.add(btnMaHoa);

		txtNhapMaHoa = new JTextPane();
		txtNhapMaHoa.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, null));
		txtNhapMaHoa.setFont(new Font("Arial", Font.PLAIN, 14));
		txtNhapMaHoa.setBounds(10, 40, 320, 150);
		panelMaHoa.add(txtNhapMaHoa);

		JScrollPane sc2 = new JScrollPane(txtNhapMaHoa);
		sc2.setBounds(10, 40, 320, 150);
		panelMaHoa.add(sc2);

		JLabel lblNhapMaHoa = new JLabel("Nhập text để mã hóa vào:");
		lblNhapMaHoa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNhapMaHoa.setBounds(10, 11, 200, 25);
		panelMaHoa.add(lblNhapMaHoa);

		JButton btnChonFileMaHoa = new JButton("Chọn file");
		btnChonFileMaHoa.setBounds(10, 200, 100, 25);
		btnChonFileMaHoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chonFile();
			}
		});
		panelMaHoa.add(btnChonFileMaHoa);

		txtLinkFileMH = new JTextField();
		txtLinkFileMH.setBounds(10, 230, 320, 25);
		panelMaHoa.add(txtLinkFileMH);

		btnChuyen = new JButton("CHUYỂN");
		btnChuyen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtXuatMaHoa.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Chuyển bản mã thất bại, bản mã không được rỗng!");
				} else {
					txtNhapGiaiMa.setText(txtXuatMaHoa.getText());
					JOptionPane.showMessageDialog(contentPane, "Chuyển bản mã đi thành công!");
				}
			}
		});
		btnChuyen.setBounds(229, 511, 89, 23);
		panelMaHoa.add(btnChuyen);

		btnLuuFile1 = new JButton("Lưu File");
		btnLuuFile1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					luuFileMaHoa();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLuuFile1.setBounds(130, 511, 89, 23);
		panelMaHoa.add(btnLuuFile1);
		
		JButton btnMaHoaMoi = new JButton("Mã hóa mới");
		btnMaHoaMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNhapMaHoa.setText("");
				txtXuatMaHoa.setText("");
				txtLinkFileMH.setText("");
				typeFile=0;
			}
		});
		btnMaHoaMoi.setBounds(18, 511, 103, 23);
		panelMaHoa.add(btnMaHoaMoi);

		JPanel panelGiaiMa = new JPanel();
		panelGiaiMa.setLayout(null);
		panelGiaiMa.setBorder(new LineBorder(Color.BLUE, 2, true));
		panelGiaiMa.setBounds(720, 10, 350, 560);
		contentPane.add(panelGiaiMa);

		txtXuatGiaiMa = new JTextPane();
		txtXuatGiaiMa.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, null));
		txtXuatGiaiMa.setFont(new Font("Arial", Font.PLAIN, 14));
		txtXuatGiaiMa.setBounds(20, 353, 320, 170);
		panelGiaiMa.add(txtXuatGiaiMa);

		JScrollPane sc3 = new JScrollPane(txtXuatGiaiMa);
		sc3.setBounds(20, 353, 320, 170);
		panelGiaiMa.add(sc3);

		JLabel lblXuatGiaiMa = new JLabel("Bản rõ:");
		lblXuatGiaiMa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblXuatGiaiMa.setBounds(20, 317, 120, 25);
		panelGiaiMa.add(lblXuatGiaiMa);

		btnGiaiMa = new JButton("Giải mã");
		btnGiaiMa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				giaiMaView();
			}
		});
		btnGiaiMa.setBounds(20, 281, 168, 25);
		panelGiaiMa.add(btnGiaiMa);

		txtNhapGiaiMa = new JTextPane();
		txtNhapGiaiMa.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, null));
		txtNhapGiaiMa.setFont(new Font("Arial", Font.PLAIN, 14));
		txtNhapGiaiMa.setBounds(20, 40, 320, 170);
		panelGiaiMa.add(txtNhapGiaiMa);

		JScrollPane sc4 = new JScrollPane(txtNhapGiaiMa);
		sc4.setBounds(20, 40, 320, 170);
		panelGiaiMa.add(sc4);

		JLabel lblNhapGiaiMa = new JLabel("Nhập text để giải mã vào:");
		lblNhapGiaiMa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNhapGiaiMa.setBounds(10, 11, 200, 25);
		panelGiaiMa.add(lblNhapGiaiMa);

		btnLuuFile2 = new JButton("Lưu File");
		btnLuuFile2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					luuFileGiaiMa();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLuuFile2.setBounds(229, 526, 89, 23);
		panelGiaiMa.add(btnLuuFile2);
		
		txtLinkFileGM = new JTextField();
		txtLinkFileGM.setBounds(20, 251, 320, 25);
		panelGiaiMa.add(txtLinkFileGM);
		
		JButton btnChonFileGiaiMa = new JButton("Chọn file");
		btnChonFileGiaiMa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chonFile2();
			}
		});
		btnChonFileGiaiMa.setBounds(20, 221, 100, 25);
		panelGiaiMa.add(btnChonFileGiaiMa);
		
		JButton btnGiaiMaMoi = new JButton("Giải mã mới");
		btnGiaiMaMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNhapGiaiMa.setText("");
				txtXuatGiaiMa.setText("");
				txtLinkFileGM.setText("");
				typeFile2=0;
			}
		});
		btnGiaiMaMoi.setBounds(20, 526, 103, 23);
		panelGiaiMa.add(btnGiaiMaMoi);

		JPanel panelTaoKhoa = new JPanel();
		panelTaoKhoa.setBorder(new LineBorder(Color.BLUE, 2));
		panelTaoKhoa.setBounds(10, 10, 340, 500);
		panelTaoKhoa.setLayout(null);
		contentPane.add(panelTaoKhoa);

		JLabel lblTaoKhoa = new JLabel("TẠO KHÓA RSA");
		lblTaoKhoa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTaoKhoa.setBounds(10, 11, 200, 25);
		panelTaoKhoa.add(lblTaoKhoa);

		rdbtnTaoKhoaTuDong = new JRadioButton("Tạo khóa tự động");
		rdbtnTaoKhoaTuDong.setSelected(true);
		rdbtnTaoKhoaTuDong.setBounds(20, 43, 140, 23);
		panelTaoKhoa.add(rdbtnTaoKhoaTuDong);

		rdbtnTaoKhoaTuyChon = new JRadioButton("Tạo khóa tùy chọn");
		rdbtnTaoKhoaTuyChon.setBounds(170, 43, 132, 23);
		panelTaoKhoa.add(rdbtnTaoKhoaTuyChon);

		ButtonGroup groupTaoKhoa = new ButtonGroup();
		groupTaoKhoa.add(rdbtnTaoKhoaTuDong);
		groupTaoKhoa.add(rdbtnTaoKhoaTuyChon);

		JLabel lblNhapP = new JLabel("Số nguyên tố bí mật p:");
		lblNhapP.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNhapP.setBounds(10, 73, 155, 25);
		panelTaoKhoa.add(lblNhapP);

		txtNhapP = new JTextField();
		txtNhapP.setBounds(180, 76, 132, 20);
		panelTaoKhoa.add(txtNhapP);
		txtNhapP.setColumns(10);

		JLabel lblNhapQ = new JLabel("Số nguyên tố bí mật q:");
		lblNhapQ.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNhapQ.setBounds(10, 96, 155, 25);
		panelTaoKhoa.add(lblNhapQ);

		txtNhapQ = new JTextField();
		txtNhapQ.setColumns(10);
		txtNhapQ.setBounds(180, 99, 132, 20);
		panelTaoKhoa.add(txtNhapQ);

		JLabel lblNhapB = new JLabel("Chọn khóa mã hóa b:");
		lblNhapB.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNhapB.setBounds(10, 119, 155, 25);
		panelTaoKhoa.add(lblNhapB);

		txtNhapB = new JTextField();
		txtNhapB.setColumns(10);
		txtNhapB.setBounds(180, 122, 132, 20);
		panelTaoKhoa.add(txtNhapB);

		JButton btnTaoKhoa = new JButton("Tạo khóa mới");
		btnTaoKhoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taoKhoaView();
			}
		});
		btnTaoKhoa.setBounds(107, 155, 121, 23);
		panelTaoKhoa.add(btnTaoKhoa);

		JLabel lblThongTinKhoa = new JLabel("THÔNG TIN KHÓA RSA");
		lblThongTinKhoa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblThongTinKhoa.setBounds(10, 204, 200, 25);
		panelTaoKhoa.add(lblThongTinKhoa);

		lblKhoaCongKhai = new JLabel("Khóa công khai (b,n):");
		lblKhoaCongKhai.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblKhoaCongKhai.setBounds(10, 234, 302, 25);
		panelTaoKhoa.add(lblKhoaCongKhai);

		lblKhoaBiMat = new JLabel("Khóa bí mật (a,p,q):");
		lblKhoaBiMat.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblKhoaBiMat.setBounds(10, 257, 302, 25);
		panelTaoKhoa.add(lblKhoaBiMat);
		

		JButton btnResetForm = new JButton("RESET FORM");
		btnResetForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetForm();
			}
		});
		btnResetForm.setBounds(123, 533, 109, 23);
		contentPane.add(btnResetForm);
		btnChuyen.setEnabled(false);
		btnLuuFile1.setEnabled(false);
		btnLuuFile2.setEnabled(false);
		
	}

	public boolean checkMaHoa() {
		if (this.checkKhoa == false || this.txtNhapMaHoa.getText().equals("")) {
			return false;
		}
		return true;
	}

	public boolean checkGiaiMa() {
		if (this.checkKhoa == false || this.txtNhapGiaiMa.getText().equals("")) {
			return false;
		}
		return true;
	}

	public boolean checkTaoKhoa() {
		if (this.txtNhapP.getText().equals("") || this.txtNhapQ.getText().equals("")
				|| this.txtNhapB.getText().equals("")) {
			return false;
		}
		return true;
	}

	public boolean checkTaoKhoa2() {
		int p, q, n, phiN, b, a;
		p = Integer.valueOf(txtNhapP.getText());
		q = Integer.valueOf(txtNhapQ.getText());
		b = Integer.valueOf(txtNhapB.getText());
		n = p * q;
		phiN = (p - 1) * (q - 1);
		if (RSA.timUCLN(b, phiN) == 1 && p < 250 && q < 250 && RSA.kiemTraSoNguyenTo(p) && RSA.kiemTraSoNguyenTo(q)
				&& b > 1 && b < phiN) {
			a = RSA.timNghichDao(b, phiN);
			rsa = new RSA(p, q, b, n, phiN, a);
			return true;
		}
		JOptionPane.showMessageDialog(contentPane,
				"Tạo khóa tùy chọn thất bại, hãy chọn khóa thỏa mãn chuẩn hệ mã RSA!");
		return false;
	}

	public void resetForm() {
		this.checkKhoa = false;
		this.txtNhapMaHoa.setText("");
		this.txtXuatMaHoa.setText("");
		this.txtNhapGiaiMa.setText("");
		this.txtXuatGiaiMa.setText("");
		this.txtNhapP.setText("");
		this.txtNhapQ.setText("");
		this.txtNhapB.setText("");
		this.lblKhoaCongKhai.setText("Khóa công khai (b,n):");
		this.lblKhoaBiMat.setText("Khóa bí mật (a,p,q):");
		this.txtLinkFileMH.setText("");
		this.typeFile = 0;
		btnChuyen.setEnabled(false);
		btnLuuFile1.setEnabled(false);
		btnLuuFile2.setEnabled(false);
	}

	public void hienThiKhoa() {
		txtNhapP.setText(""+rsa.getP());
		txtNhapQ.setText(""+rsa.getQ());
		txtNhapB.setText(""+rsa.getB());
		this.lblKhoaCongKhai.setText("Khóa công khai (b,n):  (" + rsa.getB() + ", " + rsa.getN() + ")");
		this.lblKhoaBiMat.setText("Khóa bí mật (a,p,q):  (" + rsa.getA() + ", " + rsa.getP() + ", " + rsa.getQ() + ")");
	}

	public void chonFile() {
		JFileChooser fChon = new JFileChooser();
		FileNameExtensionFilter fLocTxt = new FileNameExtensionFilter("txt", "txt");
		FileNameExtensionFilter fLocDoc = new FileNameExtensionFilter("docx", "docx");
		fChon.addChoosableFileFilter(fLocTxt);
		fChon.addChoosableFileFilter(fLocDoc);
		fChon.setMultiSelectionEnabled(false);
		int x = fChon.showDialog(this, "Tải file");

		if (x == JFileChooser.APPROVE_OPTION) {
			File f = fChon.getSelectedFile();
			if (f.getName().contains(".txt")) {
				txtLinkFileMH.setText(f.getPath());
				try {
					File nd = new File(txtLinkFileMH.getText());
					Scanner sc = new Scanner(nd);
					String content = "";
					while (sc.hasNextLine()) {
						content += sc.nextLine() + "\r\n";
					}
					txtNhapMaHoa.setText(content);
					typeFile = 1;
					sc.close();
					JOptionPane.showMessageDialog(contentPane, "Tải lên File hoàn tất!");
				} catch (FileNotFoundException ex) {
					JOptionPane.showMessageDialog(contentPane, "Tải lên File thất bại!");
				}
			} else if (f.getAbsolutePath().contains(".docx")) {
				txtLinkFileMH.setText(f.getPath());
				try {
					FileInputStream fis = new FileInputStream(txtLinkFileMH.getText());
					XWPFDocument document = new XWPFDocument(fis);
					StyledDocument doc = txtNhapMaHoa.getStyledDocument();
					for (XWPFParagraph para : document.getParagraphs()) {
		                // Tạo một đoạn văn mới trong StyledDocument
		                doc.insertString(doc.getLength(), para.getText(), null);

		                // Duyệt qua các run trong đoạn văn đó để giữ nguyên định dạng
		                for (XWPFRun run : para.getRuns()) {
		                    SimpleAttributeSet attrs = new SimpleAttributeSet();
		                    if (run.isBold()) {
		                        StyleConstants.setBold(attrs, true);
		                    }
		                    if (run.isItalic()) {
		                        StyleConstants.setItalic(attrs, true);
		                    }
		                    
		                    if (run.getColor() != null) {
		                        StyleConstants.setForeground(attrs, 
		                            new java.awt.Color(Color.decode(run.getColor()).getRGB()));
		                    }
		                    doc.setCharacterAttributes(doc.getLength() - para.getText().length() + run.getTextPosition(),
		                            run.getText(0).length(), attrs, false);
		                }
		            }
					document.close();
					typeFile = 2;
					JOptionPane.showMessageDialog(contentPane, "Tải lên File hoàn tất!");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(contentPane,
						"Tải lên File thất bại, ứng dụng chỉ hỗ trợ xử lý file .txt và .docx!");
			}

		}
	}
	
	public void chonFile2() {
		JFileChooser fChon = new JFileChooser();
		FileNameExtensionFilter fLocTxt = new FileNameExtensionFilter("txt", "txt");
		FileNameExtensionFilter fLocDoc = new FileNameExtensionFilter("docx", "docx");
		fChon.addChoosableFileFilter(fLocTxt);
		fChon.addChoosableFileFilter(fLocDoc);
		fChon.setMultiSelectionEnabled(false);
		int x = fChon.showDialog(this, "Tải file");

		if (x == JFileChooser.APPROVE_OPTION) {
			File f = fChon.getSelectedFile();
			if (f.getName().contains(".txt")) {
				txtLinkFileGM.setText(f.getPath());
				try {
					File nd = new File(txtLinkFileGM.getText());
					Scanner sc = new Scanner(nd);
					String content = "";
					while (sc.hasNextLine()) {
						content += sc.nextLine() + "\r\n";
					}
					txtNhapGiaiMa.setText(content);
					typeFile2 = 1;
					sc.close();
					JOptionPane.showMessageDialog(contentPane, "Tải lên File hoàn tất!");
				} catch (FileNotFoundException ex) {
					JOptionPane.showMessageDialog(contentPane, "Tải lên File thất bại!");
				}
			} else if (f.getAbsolutePath().contains(".docx")) {
				txtLinkFileGM.setText(f.getPath());
				try {
					FileInputStream fis = new FileInputStream(txtLinkFileGM.getText());
					XWPFDocument document = new XWPFDocument(OPCPackage.open(fis));
					XWPFWordExtractor wordExtractor = new XWPFWordExtractor(document);
					txtNhapGiaiMa.setText(wordExtractor.getText());
					wordExtractor.close();
					document.close();
					typeFile2 = 2;
					JOptionPane.showMessageDialog(contentPane, "Tải lên File hoàn tất!");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(contentPane,
						"Tải lên File thất bại, ứng dụng chỉ hỗ trợ xử lý file .txt và .docx!");
			}

		}
	}

	// Tạo khóa
	public void taoKhoaView() {
		if (rdbtnTaoKhoaTuDong.isSelected()) {
			rsa.TaoKhoa();
			this.checkKhoa = true;
			hienThiKhoa();
			JOptionPane.showMessageDialog(contentPane, "Tạo khóa tự động thành công, có thể bắt đầu mã hóa ngay!");
		} else {
			if (checkTaoKhoa()) {
				if (checkTaoKhoa2()) {
					this.checkKhoa = true;
					hienThiKhoa();
					JOptionPane.showMessageDialog(contentPane,
							"Tạo khóa tùy chọn thành công, có thể bắt đầu mã hóa ngay!");
				}
			} else {
				JOptionPane.showMessageDialog(contentPane,
						"Vui lòng nhập đầy đủ thông tin khóa để tiến hành tạo khóa!");
			}
		}
	}

	// Mã hóa
	public void maHoaView() {
		if (checkMaHoa()) {
			String banRo = txtNhapMaHoa.getText();
			String banMa;
			banMa = rsa.maHoa(banRo);
			txtXuatMaHoa.setText(banMa);
			JOptionPane.showMessageDialog(contentPane, "Mã hóa thành công!");
			btnLuuFile1.setEnabled(true);
			btnChuyen.setEnabled(true);
		} else {
			JOptionPane.showMessageDialog(contentPane, "Mã hóa thất bại, vui lòng kiểm tra lại khóa và bản rõ!");
		}
	}

	// Giải mã
	public void giaiMaView() {
		if (checkGiaiMa()) {
			try {
				String banMa = txtNhapGiaiMa.getText();
				String banRo;
				banRo = rsa.giaiMa(banMa);
				txtXuatGiaiMa.setText(banRo);
				JOptionPane.showMessageDialog(contentPane, "Giải mã thành công!");
				btnLuuFile2.setEnabled(true);
			} catch(Exception e) {
				JOptionPane.showMessageDialog(contentPane, "Giải mã thất bại, bản mã bị lỗi!");
			}
			
		} else {
			JOptionPane.showMessageDialog(contentPane, "Giải mã thất bại, vui lòng kiểm tra lại khóa và bản mã!");
		}
	}

	public void luuFileMaHoa() throws IOException {
		if (txtXuatGiaiMa.getText().equals("")) {
			JOptionPane.showMessageDialog(contentPane, "Bản mã không được trống!");
		} else {
			if (typeFile == 0 || typeFile == 1) {
				FileOutputStream fos = new FileOutputStream(new File("outMaHoa.txt"));
				FileWriter out = new FileWriter("outMaHoa.txt");
				out.write(txtXuatMaHoa.getText());
				out.close();
				fos.close();
				JOptionPane.showMessageDialog(contentPane, "Lưu file thành công! Kiểm tra File trong thư mục Project!");
			} else {
				FileOutputStream fos = new FileOutputStream(new File("outMaHoa.docx"));
				XWPFDocument doc = new XWPFDocument();
				XWPFParagraph para = doc.createParagraph();
				XWPFRun run = para.createRun();
				run.setText(txtXuatMaHoa.getText());
				doc.write(fos);
				doc.close();
				fos.close();
				JOptionPane.showMessageDialog(contentPane, "Lưu file thành công! Kiểm tra File trong thư mục Project!");
			}
		}
	}

	public void luuFileGiaiMa() throws IOException {
		if (txtXuatGiaiMa.getText().equals("")) {
			JOptionPane.showMessageDialog(contentPane, "Bản rõ không được trống!");
		} else {
			if (typeFile == 0 || typeFile == 1) {
				FileOutputStream fos = new FileOutputStream(new File("outGiaiMa.txt"));
				FileWriter out = new FileWriter("outGiaiMa.txt");
				out.write(txtXuatGiaiMa.getText());
				out.close();
				fos.close();
				JOptionPane.showMessageDialog(contentPane, "Lưu file thành công! Kiểm tra File trong thư mục Project!");
			} else {
				FileOutputStream fos = new FileOutputStream(new File("outGiaiMa.docx"));
				XWPFDocument doc = new XWPFDocument();
				XWPFParagraph para = doc.createParagraph();
				XWPFRun run = para.createRun();
				run.setText(txtXuatGiaiMa.getText());
				doc.write(fos);
				doc.close();
				fos.close();
				JOptionPane.showMessageDialog(contentPane, "Lưu file thành công! Kiểm tra File trong thư mục Project!");
			}
		}
	}

}
