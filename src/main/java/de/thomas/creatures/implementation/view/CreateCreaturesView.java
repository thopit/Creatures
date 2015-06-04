package de.thomas.creatures.implementation.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.thomas.creatures.implementation.ai.BasicAI;
import de.thomas.creatures.implementation.controller.WorldController;
import de.thomas.creatures.implementation.model.Creature;
import de.thomas.creatures.implementation.model.Creature.Gender;
import de.thomas.creatures.implementation.util.AssertionException;
import de.thomas.creatures.implementation.util.AssertionHelper;
import de.thomas.creatures.implementation.util.VariationHelper;

public class CreateCreaturesView extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private WorldController worldController;
	private double width;
	private double height;
	
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
	
	private JTextField maxEnergyVarianceInput;
	private JTextField maxLifeVarianceInput;
	private JTextField speedVarianceInput;
	private JTextField visionRangeVarianceInput;
	private JTextField matingEnergyNeededVarianceInput;
	private JTextField breedLengthVarianceInput;
	private JTextField breedProgressSpeedVarianceInput;
	
	private JButton createButton;

	//TODO Default Values
	public CreateCreaturesView(WorldController worldController, double width, double height) {
		this.worldController = worldController;
		this.width = width;
		this.height = height;
		
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
		
		maxEnergyVarianceInput = new JTextField();
		maxEnergyVarianceInput.setPreferredSize(new Dimension(50, 20));
		maxLifeVarianceInput = new JTextField();
		maxLifeVarianceInput.setPreferredSize(new Dimension(50, 20));
		speedVarianceInput = new JTextField();
		speedVarianceInput.setPreferredSize(new Dimension(50, 20));
		visionRangeVarianceInput = new JTextField();
		visionRangeVarianceInput.setPreferredSize(new Dimension(50, 20));
		matingEnergyNeededVarianceInput = new JTextField();
		matingEnergyNeededVarianceInput.setPreferredSize(new Dimension(50, 20));
		breedLengthVarianceInput = new JTextField();
		breedLengthVarianceInput.setPreferredSize(new Dimension(50, 20));
		breedProgressSpeedVarianceInput = new JTextField();
		breedProgressSpeedVarianceInput.setPreferredSize(new Dimension(50, 20));
		
		panels[0].add(new JLabel("Amount:"));
		panels[0].add(amountInput);
		
		panels[1].add(new JLabel("Max Energy:"));
		panels[1].add(maxEnergyInput);
		panels[1].add(maxEnergyVarianceInput);
		
		panels[2].add(new JLabel("Max Life:"));
		panels[2].add(maxLifeInput);
		panels[2].add(maxLifeVarianceInput);
		
		panels[3].add(new JLabel("Speed:"));
		panels[3].add(speedInput);
		panels[3].add(speedVarianceInput);
		
		panels[4].add(new JLabel("Vision Range:"));
		panels[4].add(visionRangeInput);
		panels[4].add(visionRangeVarianceInput);
		
		panels[5].add(new JLabel("Male Ratio: (0-100)"));
		panels[5].add(maleRationInput);
		
		panels[6].add(new JLabel("Mating Energy Needed:"));
		panels[6].add(matingEnergyNeededInput);
		panels[6].add(matingEnergyNeededVarianceInput);
		
		panels[7].add(new JLabel("Breed Length:"));
		panels[7].add(breedLengthInput);
		panels[7].add(breedLengthVarianceInput);
		
		panels[8].add(new JLabel("Breed Progress Speed:"));
		panels[8].add(breedProgressSpeedInput);
		panels[8].add(breedProgressSpeedVarianceInput);

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
			createCreatures();
		}
	}

	private void createCreatures() {
		double amount = 0;
		double maxEnergy = 0;
		double maxLife = 0;
		double speed = 0;
		double visionRange = 0;
		double maleRation = 0;
		double matingEnergyNeeded = 0;
		double breedLength = 0;
		double breedProgressSpeed = 0;

		double maxEnergyVariance = 0;
		double maxLifeVariance = 0;
		double speedVariance = 0;
		double visionRangeVariance = 0;
		double matingEnergyNeededVariance = 0;
		double breedLengthVariance = 0;
		double breedProgressSpeedVariance = 0;
		
		try {
			amount = Double.parseDouble(amountInput.getText());
			maxEnergy = Double.parseDouble(maxEnergyInput.getText());
			maxLife = Double.parseDouble(maxLifeInput.getText());
			speed = Double.parseDouble(speedInput.getText());
			visionRange = Double.parseDouble(visionRangeInput.getText());
			maleRation = Double.parseDouble(maleRationInput.getText());
			matingEnergyNeeded = Double.parseDouble(matingEnergyNeededInput.getText());
			breedLength = Double.parseDouble(breedLengthInput.getText());
			breedProgressSpeed = Double.parseDouble(breedProgressSpeedInput.getText());

			maxEnergyVariance = Double.parseDouble(maxEnergyVarianceInput.getText());
			maxLifeVariance = Double.parseDouble(maxLifeVarianceInput.getText());
			speedVariance = Double.parseDouble(speedVarianceInput.getText());
			visionRangeVariance = Double.parseDouble(visionRangeVarianceInput.getText());
			matingEnergyNeededVariance = Double.parseDouble(matingEnergyNeededVarianceInput.getText());
			breedLengthVariance = Double.parseDouble(breedLengthVarianceInput.getText());
			breedProgressSpeedVariance = Double.parseDouble(breedProgressSpeedVarianceInput.getText());
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Input must be a number.", "Wrong input", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		try {
			AssertionHelper.checkSmallerZero(amount, "Amount");
			AssertionHelper.checkSmallerZero(maxEnergy, "Max Energy");
			AssertionHelper.checkSmallerZero(maxLife, "Max Life");
			AssertionHelper.checkSmallerZero(speed, "Speed");
			AssertionHelper.checkSmallerZero(visionRange, "Vision Range");
			AssertionHelper.checkSmallerZero(maleRation, "Male Ratio");
			AssertionHelper.checkSmallerZero(matingEnergyNeeded, "Mating Energy Needed");
			AssertionHelper.checkSmallerZero(breedLength, "Breed Length");
			AssertionHelper.checkSmallerZero(breedProgressSpeed, "Breed Progress Speed");
			AssertionHelper.checkSmallerZero(maxEnergyVariance, "Max Energy Variance");
			AssertionHelper.checkSmallerZero(maxLifeVariance, "Max Life Variance");
			AssertionHelper.checkSmallerZero(speedVariance, "Speed Variance");
			AssertionHelper.checkSmallerZero(visionRangeVariance, "Vision Range Variance");
			AssertionHelper.checkSmallerZero(matingEnergyNeededVariance, "Mating Energy Needed Variance");
			AssertionHelper.checkSmallerZero(breedLengthVariance, "Breed Length Variance");
			AssertionHelper.checkSmallerZero(breedProgressSpeedVariance, "Breed Progress Speed Variance");
			
			AssertionHelper.checkSmallerEqualThan(maleRation, 100, "Male Ratio");
			
			AssertionHelper.checkSmallerEqualThan(maxEnergyVariance, maxEnergy, "Max Energy Variance");
			AssertionHelper.checkSmallerEqualThan(maxLifeVariance, maxLife, "Max Life Variance");
			AssertionHelper.checkSmallerEqualThan(speedVariance, speed, "Speed Variance");
			AssertionHelper.checkSmallerEqualThan(visionRangeVariance, visionRange, "Vision Range Variance");
			AssertionHelper.checkSmallerEqualThan(matingEnergyNeededVariance, matingEnergyNeeded, "Mating Energy Needed Variance");
			AssertionHelper.checkSmallerEqualThan(breedLengthVariance, breedLength, "Breed Length Variance");
			AssertionHelper.checkSmallerEqualThan(breedProgressSpeedVariance, breedProgressSpeed, "Breed Progress Speed Variance");
		}
		catch (AssertionException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Wrong input", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		for (int k = 0; k < amount; k++) {
			Gender gnd;
			
			if (Math.random() * 100 > maleRation)
				gnd = Gender.FEMALE;
			else
				gnd = Gender.MALE;
			
			double middleWidth  = width / 2;
			double middleHeight = height / 2;
			
			Creature creature = new Creature(
					maxEnergy, 
					maxEnergy * VariationHelper.mutationFactor(maxEnergyVariance / maxEnergy),
					maxLife * VariationHelper.mutationFactor(maxLifeVariance / maxLife),
					new Point.Double
						(middleWidth *  VariationHelper.mutationFactor(0.95), 
						middleHeight *  VariationHelper.mutationFactor(0.95)),
					speed * VariationHelper.mutationFactor(speedVariance / speed),
					visionRange * VariationHelper.mutationFactor(visionRangeVariance / visionRange),
					gnd,
					new BasicAI(),
					matingEnergyNeeded * VariationHelper.mutationFactor(matingEnergyNeededVariance / matingEnergyNeeded),
					breedLength * VariationHelper.mutationFactor(breedLengthVariance / breedLength),
					breedProgressSpeed * VariationHelper.mutationFactor(breedProgressSpeedVariance / breedProgressSpeed)
					);
			
			
			
			
			worldController.addCreature(creature);
		}

		dispose();
	}
}
