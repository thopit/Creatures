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

public class CreateCreaturesView extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JPanel backPanel;
	
	private JTextField amountInput;
	private JTextField maxEnergyInput;
	private JTextField maxLifeInput;
	private JTextField speedInput;
	private JTextField visionRangeInput;
	private JTextField maleRationInput;
	private JTextField matingEnergyNeededInput;
	private JTextField breedLengthInput;
	private JTextField breedProgressSpeedInput;
	
	private JTextField maxEnergyVariance;
	private JTextField maxLifeVariance;
	private JTextField speedVariance;
	private JTextField visionRangeVariance;
	private JTextField matingEnergyNeededVariance;
	private JTextField breedLengthVariance;
	private JTextField breedProgressSpeedVariance;
	
	private JButton createButton;

	public CreateCreaturesView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350, 300);
		setLocationRelativeTo(null);
		setTitle("Create Creatures (With Variance)");

		initUI();

		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}


	private void initUI() {
		backPanel = new JPanel();
		backPanel.setLayout(new GridLayout(0, 1));
		
		JPanel[] panels = new JPanel[9];
		for (int k = 0; k < panels.length; k++) {
			panels[k] = new JPanel(new FlowLayout(FlowLayout.LEFT));
		}
		
		amountInput = new JTextField();
		amountInput.setPreferredSize(new Dimension(50, 20));
		maxEnergyInput = new JTextField();
		maxEnergyInput.setPreferredSize(new Dimension(50, 20));
		maxLifeInput = new JTextField();
		maxLifeInput.setPreferredSize(new Dimension(50, 20));
		speedInput = new JTextField();
		speedInput.setPreferredSize(new Dimension(50, 20));
		visionRangeInput = new JTextField();
		visionRangeInput.setPreferredSize(new Dimension(50, 20));
		maleRationInput = new JTextField();
		maleRationInput.setPreferredSize(new Dimension(50, 20));
		matingEnergyNeededInput = new JTextField();
		matingEnergyNeededInput.setPreferredSize(new Dimension(50, 20));
		breedLengthInput = new JTextField();
		breedLengthInput.setPreferredSize(new Dimension(50, 20));
		breedProgressSpeedInput = new JTextField();
		breedProgressSpeedInput.setPreferredSize(new Dimension(50, 20));
		
		maxEnergyVariance = new JTextField();
		maxEnergyVariance.setPreferredSize(new Dimension(50, 20));
		maxLifeVariance = new JTextField();
		maxLifeVariance.setPreferredSize(new Dimension(50, 20));
		speedVariance = new JTextField();
		speedVariance.setPreferredSize(new Dimension(50, 20));
		visionRangeVariance = new JTextField();
		visionRangeVariance.setPreferredSize(new Dimension(50, 20));
		matingEnergyNeededVariance = new JTextField();
		matingEnergyNeededVariance.setPreferredSize(new Dimension(50, 20));
		breedLengthVariance = new JTextField();
		breedLengthVariance.setPreferredSize(new Dimension(50, 20));
		breedProgressSpeedVariance = new JTextField();
		breedProgressSpeedVariance.setPreferredSize(new Dimension(50, 20));
		
		panels[0].add(new JLabel("Amount:"));
		panels[0].add(amountInput);
		
		panels[1].add(new JLabel("Max Energy:"));
		panels[1].add(maxEnergyInput);
		panels[1].add(maxEnergyVariance);
		
		panels[2].add(new JLabel("Max Life:"));
		panels[2].add(maxLifeInput);
		panels[2].add(maxLifeVariance);
		
		panels[3].add(new JLabel("Speed:"));
		panels[3].add(speedInput);
		panels[3].add(speedVariance);
		
		panels[4].add(new JLabel("Vision Range:"));
		panels[4].add(visionRangeInput);
		panels[4].add(visionRangeVariance);
		
		panels[5].add(new JLabel("Male Ratio: (0-100)"));
		panels[5].add(maleRationInput);
		
		panels[6].add(new JLabel("Mating Energy Needed:"));
		panels[6].add(matingEnergyNeededInput);
		panels[6].add(matingEnergyNeededVariance);
		
		panels[7].add(new JLabel("Breed Length:"));
		panels[7].add(breedLengthInput);
		panels[7].add(breedLengthVariance);
		
		panels[8].add(new JLabel("Breed Progress Speed:"));
		panels[8].add(breedProgressSpeedInput);
		panels[8].add(breedProgressSpeedVariance);

		for (JPanel p : panels) {
			backPanel.add(p);
		}
		
		createButton = new JButton("Create Creatures");
		createButton.addActionListener(this);

		backPanel.add(createButton);

		setContentPane(backPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == createButton) {
			createCreature();
		}
	}

	//TODO implement
	private void createCreature() {
		int foodRate = 0;
		
		try {
			//width = Integer.parseInt(widthInput.getText());
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Input must be a number.", "Wrong input", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (foodRate < 0 || foodRate > 100) {
			JOptionPane.showMessageDialog(this, "Food rate must be between 0 and 100.", "Wrong input", JOptionPane.WARNING_MESSAGE);
			return;
		}

		dispose();
	}

}
