package cjh;

import com.sun.java.accessibility.util.AWTEventMonitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;


interface tempData {;
	double height = 771.2;
	double velocity = 35.23911111;
	double temp = -2.4991;
	String path = "/home/cuka/dev/recog.txt";
	String path2 = "/home/cuka/dev/image.png";
	String path3 = "/home/cuka/dev/csv/data.csv";
	String path4 = "/home/cuka/dev/xS.txt";
	String path5 = "/home/cuka/dev/gps.txt";
	String path6 = "/home/cuka/dev/err/err.txt";

	String satellite = "KOALA-03";
	String element[] = {"높이", "속도", "기온"};
}


public class Main extends Thread{


	private Data data;
	private javax.swing.JPanel JPanel;
	private JButton Connect;

	private JLabel titleDisplay;
	private Image image;
	private Image updateImag;
	private JLabel date;
	private JLabel data2;
	private JLabel data1;
	private JLabel cameraLabel;
	private JButton leftBtn;
	private JButton rightBtn;

	private JLabel pos;
	private JLabel posit;
	private JButton STOPButton;
	private JComboBox comboBox1;


	public Boolean flag = false;
	ImageIcon icon, newIcon ,testIcon;

	Date dateClass = new Date();



	public String title, lore;

	File file;




	public void init() {
		title = "미교신 상태";
		lore = "";
	}

	@Override
	public void run()  {



		while (data.mod != -1) {

			title = dateClass.getDate() + " " + dateClass.getTime();


			data.txtToObjFile(file);



			icon = new ImageIcon(
					tempData.path2);


			image = icon.getImage();
			updateImag = image.getScaledInstance(400, 300, Image.SCALE_SMOOTH);

			newIcon = new ImageIcon(updateImag);

			cameraLabel.setIcon(newIcon);
			cameraLabel.setText("");

			

			data.position = new Position(data.position.getX(), data.position.getY());

			posit.setText((data.position.getX()) + "," + data.position.getY());
 
			date.setText(title);
			dateClass.timeUpdate();

		try{
			data1.setText(data.splitData3[0]+","+data.splitData3[1]);
			data2.setText(data.splitData2[9]+","+data.splitData2[10]+","+data.splitData2[11]);

} catch(Exception e) {
	e.printStackTrace();}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

		}




	}
	public Main(Data data) {

		file = new File(tempData.path);

		this.data = data;


			leftBtn.addActionListener(new ActionListener() {
				/**
				 * Invoked when an action occurs.
				 *
				 * @param e the event to be processed
				 */
				@Override
				public void actionPerformed(ActionEvent e) {

					if(data.mod != 0) {
						pos.setText("매뉴얼 모드 전환 요함");
					}

					else {
						data.position.subX();
						data.movement = -100;
						pos.setText("좌측이동");
					}
				}
			});
			rightBtn.addActionListener(new ActionListener() {
				/**
				 * Invoked when an action occurs.
				 *
				 * @param e the event to be processed
				 */
				@Override
				public void actionPerformed(ActionEvent e) {


					if(data.mod != 0) {

						pos.setText("매뉴얼 모드 전환 요함");

				} else {
						data.position.addX();
						data.movement = 100;
						pos.setText("우측이동");
					}
				}


			});


			STOPButton.addActionListener(new ActionListener() {
				/**
				 * Invoked when an action occurs.
				 *
				 * @param e the event to be processed
				 */
				@Override
				public void actionPerformed(ActionEvent e) {


					data.movement = 0;
					pos.setText("정지");
				}
			});

		comboBox1.addActionListener(new ActionListener() {




			/**
			 * Invoked when an action occurs.
			 *
			 * @param e the event to be processed
			 */
			@Override
			public void actionPerformed(ActionEvent e) {


				data.change = true;
				switch(comboBox1.getSelectedItem().toString()) {
					case "CV":
						titleDisplay.setText("지상관제센터 [CV]");
						data.change = true;
						data.mod = 1;
						data.OPERATING_MODE = 4;
						break;
					case "GPS":
						titleDisplay.setText("지상관제센터 [GPS]");
						data.change = true;
						data.mod = 2;

						break;
					case "MANUAL":
						titleDisplay.setText("지상관제센터 [MANUAL]");
						data.change = true;
						data.mod = 0;
						data.OPERATING_MODE = 1;
						break;
				}
			}
		});


	}



	public JPanel getPanel() {
		return this.JPanel;
	}


}
