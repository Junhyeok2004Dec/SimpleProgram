package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

	private javax.swing.JPanel JPanel;
	private JButton 접속버튼;



	private JLabel date;
	private JLabel alt;
	private JLabel velocity;
	private JButton 카메라확인;
	private JLabel cameraLabel;

	BufferedImage bimage;
	ImageIcon icon, newIcon;


	public Main() {



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
				Image updateImag = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);

				newIcon = new ImageIcon(updateImag);

				cameraLabel.setIcon(newIcon);
				cameraLabel.setText("");
			}
		});
	}

	public JPanel getPanel() {
		return this.JPanel;
	}


}
