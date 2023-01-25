package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Main {

	private javax.swing.JPanel JPanel;
	private JButton addButton;
	private JButton 추가Button;
	private JButton 목록확인Button;
	private JSpinner spinner1;

	private JTextField textField1;
	private JTextField textField2, textField3;

	private JComboBox comboBox1;

	private JLabel date;
	private JRadioButton 사용RadioButton;
	private JRadioButton 회수RadioButton;

	public Main() {



		addButton.addActionListener(new ActionListener() {
			/**
			 * Invoked when an action occurs.
			 *
			 * @param e the event to be processed
			 */
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		spinner1.addComponentListener(new ComponentAdapter() {
		});
		spinner1.addPropertyChangeListener(new PropertyChangeListener() {
			/**
			 * This method gets called when a bound property is changed.
			 *
			 * @param evt A PropertyChangeEvent object describing the event source
			 *            and the property that has changed.
			 */
			@Override
			public void propertyChange(PropertyChangeEvent evt) {

			}
			/**
			 * This method gets called when a bound property is changed.
			 *
			 * @param evt A PropertyChangeEvent object describing the event source
			 *            and the property that has changed.
			 */

		});
		목록확인Button.addActionListener(new ActionListener() {
			/**
			 * Invoked when an action occurs.
			 *
			 * @param e the event to be processed
			 */
			@Override
			public void actionPerformed(ActionEvent e) {

				CheckBEDList chkbdlist = new CheckBEDList();
				chkbdlist.pack();
				chkbdlist.setVisible(true);


			}
		});
	}

	public JPanel getPanel() {
		return this.JPanel;
	}


	private void createUIComponents() {
		// TODO: place custom component creation code here
	}


}
