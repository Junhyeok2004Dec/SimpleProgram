package cjh;

import javax.swing.*;
import java.awt.*;

public class Window {

	public static void main(String[] args) {



		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}


		Dimension dim = new Dimension(1280,720);

		Data data = new Data();
		//ReadWrite rw = new ReadWrite(data);



		Main main = new Main(data);

		JFrame jFrame = new JFrame("Application");
		jFrame.setContentPane(main.getPanel());

		jFrame.setPreferredSize(dim);
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.pack();
		jFrame.setVisible(true);

		main.start();

		//rw.start();




		//rw.dynamixel.closePort(rw.port_num);


	}
}
