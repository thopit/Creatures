package de.thomas.creatures.implementation.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.thomas.creatures.implementation.WorldCreator;
import de.thomas.creatures.implementation.factories.WorldFactory;
import de.thomas.creatures.implementation.model.WorldModel;

public class CreateWorldView extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private WorldCreator worldCreator;

	private JPanel backPanel;
	private JLabel widthLabel;
	private JTextField widthInput;
	private JLabel heightLabel;
	private JTextField heightInput;
	private JLabel foodRateLabel;
	private JTextField foodRateInput;
	private JButton createButton;

	public CreateWorldView(WorldCreator worldCreator) {
		this.worldCreator = worldCreator;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(220, 150);
		setLocationRelativeTo(null);
		setTitle("Create World");

		initUI();

		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}


	private void initUI() {
		backPanel = new JPanel();
		backPanel.setLayout(new GridLayout(0, 1));

		JPanel widthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		widthLabel = new JLabel("Width:");
		widthInput = new JTextField();
		widthInput.setPreferredSize(new Dimension(50, 20));
		widthPanel.add(widthLabel);
		widthPanel.add(widthInput);


		JPanel heightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		heightLabel = new JLabel("Height:");
		heightInput = new JTextField();
		heightInput.setPreferredSize(new Dimension(50, 20));
		heightPanel.add(heightLabel);
		heightPanel.add(heightInput);

		JPanel foodPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		foodRateLabel = new JLabel("Food Rate: (0-100)");
		foodRateInput = new JTextField();
		foodRateInput.setPreferredSize(new Dimension(50, 20));
		foodPanel.add(foodRateLabel);
		foodPanel.add(foodRateInput);

		backPanel.add(widthPanel);
		backPanel.add(heightPanel);
		backPanel.add(foodPanel);

		createButton = new JButton("Create World");
		createButton.addActionListener(this);

		backPanel.add(createButton);

		setContentPane(backPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == createButton) {
			createWorld();
		}
	}

	private void createWorld() {
		int width = 0;
		int height = 0;
		int foodRate = 0;
		
		try {
			width = Integer.parseInt(widthInput.getText());
			height = Integer.parseInt(heightInput.getText());
			foodRate = Integer.parseInt(foodRateInput.getText());
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Input must be a number.", "Wrong input", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (foodRate < 0 || foodRate > 100) {
			JOptionPane.showMessageDialog(this, "Food rate must be between 0 and 100.", "Wrong input", JOptionPane.WARNING_MESSAGE);
			return;
		}

		WorldModel model = WorldFactory.createEmptyWorld(width, height, foodRate);
		worldCreator.setupWorld(model);
		dispose();
	}

}
