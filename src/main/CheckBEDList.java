package main;

import javax.swing.*;
import java.awt.event.*;

public class CheckBEDList extends JDialog {
	private JPanel contentPane;
	private JButton buttonOK;
	private JList table1;

	public CheckBEDList() {
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);

		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onOK();
			}
		});


		// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onCancel();
			}
		});

		// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCancel();
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

	private void onOK() {
		// add your code here
		dispose();
	}

	private void onCancel() {
		// add your code here if necessary
		dispose();
	}

	public static void main(String[] args) {
		CheckBEDList dialog = new CheckBEDList();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}

	public void setData(ReturnMethod data) {
	}

	public void getData(ReturnMethod data) {
	}

	public boolean isModified(ReturnMethod data) {
		return false;
	}
}
