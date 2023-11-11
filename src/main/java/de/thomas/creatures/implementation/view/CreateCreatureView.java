package de.thomas.creatures.implementation.view;

import de.thomas.creatures.implementation.ai.BasicAI;
import de.thomas.creatures.implementation.controller.WorldController;
import de.thomas.creatures.implementation.model.Creature;
import de.thomas.creatures.implementation.model.Creature.Gender;
import de.thomas.creatures.implementation.util.AssertionException;
import de.thomas.creatures.implementation.util.AssertionHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

public class CreateCreatureView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private transient WorldController worldController;
    private double sizeX;
    private double sizeY;

    private JPanel backPanel;
    private JTextField maxEnergyInput;
    private JTextField maxLifeInput;
    private JTextField positionXInput;
    private JTextField positionYInput;
    private JTextField speedInput;
    private JTextField visionRangeInput;
    private JTextField matingEnergyNeededInput;
    private JTextField breedLengthInput;
    private JTextField breedProgressSpeedInput;
    private JComboBox<String> genderComboBox;
    private JButton createButton;

    //TODO Default values (Save if changed)
    public CreateCreatureView(WorldController worldController, double sizeX, double sizeY) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setTitle("Create Creature");

        this.worldController = worldController;
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        initUI();

        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }


    private void initUI() {
        backPanel = new JPanel();
        backPanel.setLayout(new GridLayout(0, 1));

        JPanel[] panels = new JPanel[9];
        for (int k = 0; k < panels.length; k++) {
            panels[k] = new JPanel(new FlowLayout(FlowLayout.LEFT));
        }

        maxEnergyInput = new JTextField();
        maxEnergyInput.setPreferredSize(new Dimension(50, 20));
        maxLifeInput = new JTextField();
        maxLifeInput.setPreferredSize(new Dimension(50, 20));
        positionXInput = new JTextField();
        positionXInput.setPreferredSize(new Dimension(50, 20));
        positionYInput = new JTextField();
        positionYInput.setPreferredSize(new Dimension(50, 20));
        speedInput = new JTextField();
        speedInput.setPreferredSize(new Dimension(50, 20));
        visionRangeInput = new JTextField();
        visionRangeInput.setPreferredSize(new Dimension(50, 20));
        String[] values = {"Male", "Female"};
        genderComboBox = new JComboBox<String>(values);
        matingEnergyNeededInput = new JTextField();
        matingEnergyNeededInput.setPreferredSize(new Dimension(50, 20));
        breedLengthInput = new JTextField();
        breedLengthInput.setPreferredSize(new Dimension(50, 20));
        breedProgressSpeedInput = new JTextField();
        breedProgressSpeedInput.setPreferredSize(new Dimension(50, 20));

        panels[0].add(new JLabel("Max Energy:"));
        panels[0].add(maxEnergyInput);

        panels[1].add(new JLabel("Max Life:"));
        panels[1].add(maxLifeInput);

        panels[2].add(new JLabel("Position:"));
        panels[2].add(positionXInput);
        panels[2].add(positionYInput);

        panels[3].add(new JLabel("Speed:"));
        panels[3].add(speedInput);

        panels[4].add(new JLabel("Vision Range:"));
        panels[4].add(visionRangeInput);

        panels[5].add(new JLabel("Sex:"));
        panels[5].add(genderComboBox);

        panels[6].add(new JLabel("Mating Energy Needed:"));
        panels[6].add(matingEnergyNeededInput);

        panels[7].add(new JLabel("Breed Length:"));
        panels[7].add(breedLengthInput);

        panels[8].add(new JLabel("Breed Progress Speed:"));
        panels[8].add(breedProgressSpeedInput);

        for (JPanel p : panels) {
            backPanel.add(p);
        }

        createButton = new JButton("Create Creature");
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

    private void createCreature() {
        int maxEnergy;
        int maxLife;
        int positionX;
        int positionY;
        int speed;
        int visionRange;
        int matingEnergyNeeded;
        int breedLength;
        int breedProgressSpeed;

        String gender;


        try {
            maxEnergy = Integer.parseInt(maxEnergyInput.getText());
            maxLife = Integer.parseInt(maxLifeInput.getText());
            positionX = Integer.parseInt(positionXInput.getText());
            positionY = Integer.parseInt(positionYInput.getText());
            speed = Integer.parseInt(speedInput.getText());
            visionRange = Integer.parseInt(visionRangeInput.getText());
            gender = (String) genderComboBox.getSelectedItem();
            matingEnergyNeeded = Integer.parseInt(matingEnergyNeededInput.getText());
            breedLength = Integer.parseInt(breedLengthInput.getText());
            breedProgressSpeed = Integer.parseInt(breedProgressSpeedInput.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Input must be a number.", "Wrong input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            AssertionHelper.checkSmallerZero(maxEnergy, "Max Energy");
            AssertionHelper.checkSmallerZero(maxLife, "Max Life");
            AssertionHelper.checkSmallerZero(positionX, "Position X");
            AssertionHelper.checkSmallerZero(positionY, "Position Y");
            AssertionHelper.checkSmallerZero(speed, "Speed");
            AssertionHelper.checkSmallerZero(visionRange, "Vision Range");
            AssertionHelper.checkSmallerZero(matingEnergyNeeded, "Mating Energy Needed");
            AssertionHelper.checkSmallerZero(breedLength, "Breed Length");
            AssertionHelper.checkSmallerZero(breedProgressSpeed, "Breed Progress Speed");

            AssertionHelper.checkSmallerEqualThan(positionX, sizeX, "Position X");
            AssertionHelper.checkSmallerEqualThan(positionY, sizeY, "Position Y");
        } catch (AssertionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Wrong input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Gender gnd;
        if (gender.equals("Male"))
            gnd = Gender.MALE;
        else
            gnd = Gender.FEMALE;

        Creature creature = new Creature(
                maxEnergy,
                maxEnergy,
                maxLife,
                new Point2D.Double(positionX, positionY),
                speed,
                visionRange,
                gnd,
                new BasicAI(),
                matingEnergyNeeded,
                breedLength,
                breedProgressSpeed);


        worldController.addCreature(creature);
        dispose();
    }

}
