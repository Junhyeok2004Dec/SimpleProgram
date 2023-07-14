package main;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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
		Main main = new Main(data);
		ReadWrite rw = new ReadWrite(data);




		JFrame jFrame = new JFrame("Application");
		jFrame.setContentPane(main.getPanel());

		jFrame.setPreferredSize(dim);
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.pack();
		jFrame.setVisible(true);

		main.start();
		main.init();

		rw.start();
		rw.init();


		main.run();
		rw.run();

		rw.dynamixel.closePort(rw.port_num);

	}
}
