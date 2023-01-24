package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Main {

	private javax.swing.JPanel JPanel;
	private JButton addButton;
	private JButton 추가Button;
	private JButton 회수Button;
	private JSpinner spinner1;


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
	}

	public JPanel getPanel() {
		return this.JPanel;
	}


	private void createUIComponents() {
		// TODO: place custom component creation code here
	}


}
