package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


enum tempData {;
	double height = 771.2;
	double velocity = 35.23911111;
	double temp = -2.4991;
	String element[] = {"높이", "속도", "기온"};}

public class Main {

	private javax.swing.JPanel JPanel;
	private JButton 접속버튼;



	private JLabel date;
	private JLabel data2;
	private JLabel data1;
	private JButton 카메라확인;
	private JLabel cameraLabel;
	private JComboBox dataBox2;
	private JComboBox dataBox;
	private JButton leftBtn;
	private JButton rightBtn;

	private JLabel pos;

	BufferedImage bimage;
	ImageIcon icon, newIcon;


	public Main() {

		Data data = new Data();
		data.position = new Position(0d,0d);



		카메라확인.addActionListener(new ActionListener() {
			/**
			 * Invoked when an action occurs.
			 *
			 * @param e the event to be processed
			 */
			@Override
			public void actionPerformed(ActionEvent e) {

				icon = new ImageIcon(
						"src/assets/1d2d.png");

				Image image = icon.getImage();
				Image updateImag = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);

				newIcon = new ImageIcon(updateImag);

				cameraLabel.setIcon(newIcon);
				cameraLabel.setText("");
			}
		});
		접속버튼.addActionListener(new ActionListener() {
			/**
			 * Invoked when an action occurs.
			 *
			 * @param e the event to be processed
			 */
			@Override
			public void actionPerformed(ActionEvent e) {


				icon = new ImageIcon(
						"src/assets/image.png");

				Image image = icon.getImage();
				Image updateImag = image.getScaledInstance(640, 480, Image.SCALE_SMOOTH);

				newIcon = new ImageIcon(updateImag);

				cameraLabel.setIcon(newIcon);
				cameraLabel.setText("");

				Date dateClass = new Date();

				date.setText(dateClass.getDate() + " " + dateClass.getTime() + "  서버 IP : 192.168.0.5:16000");
			}
		});
		dataBox2.addActionListener(new ActionListener() {
			/**
			 * Invoked when an action occurs.
			 *
			 * @param e the event to be processed
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(dataBox2.getSelectedItem().toString()) {
					case "고도":
						data1.setText(String.valueOf(951.4413)+"m");
						break;
					case "속도":
						data1.setText(String.valueOf(4.2)+"m/s");
						break;
					case "예상도착시간":
						data1.setText(String.valueOf(79.2)+"초");
						break;
				}
			}
		});
		dataBox.addActionListener(new ActionListener() {
			/**
			 * Invoked when an action occurs.
			 *
			 * @param e the event to be processed
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(dataBox.getSelectedItem().toString()) {
					case "고도":
						data2.setText(String.valueOf(951.4413)+"m");
						break;
					case "속도":
						data2.setText(String.valueOf(4.2)+"m/s");
						break;
					case "예상도착시간":
						data2.setText(String.valueOf(79.2)+"초");
						break;
				}
			}
		});
		leftBtn.addActionListener(new ActionListener() {
			/**
			 * Invoked when an action occurs.
			 *
			 * @param e the event to be processed
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				data.position.subX();
				pos.setText(data.position.toString());

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
				data.position.addX();
				pos.setText(data.position.toString());
			}


		});

	}

	public JPanel getPanel() {
		return this.JPanel;
	}


}
