package de.thomas.creatures.implementation.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.thomas.creatures.implementation.WorldCreator;
import de.thomas.creatures.implementation.controller.WorldController;
import de.thomas.creatures.implementation.model.WorldModel;
import de.thomas.creatures.implementation.statistics.StatElement;
import de.thomas.creatures.implementation.statistics.Statistics;

public class MainWindow extends JFrame implements ActionListener , ChangeListener {
	private static final long serialVersionUID = 1L;
	private WorldView view;
	private Statistics statistics;
	private WorldController controller;
	private WorldCreator worldCreator;
	private WorldModel worldModel;
	
	private JSplitPane splitPane;
	
	private JLabel textLabel;
	private JLabel speedLabel;
	private JSlider speedSlider;
	
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem openItem;
	private JMenuItem preferencesItem;
	private JMenuItem closeItem;
	
	private JMenu worldMenu;
	private JMenuItem createWorldItem;
	private JMenuItem createCreatureItem;
	private JMenuItem createCreaturesItem;
	
	private JMenu statisticsMenu;
	private JMenuItem showStatisticsItem;
	
	private boolean isExternalUpdate = false;

	public MainWindow(WorldView view, WorldController controller, Statistics statistics, WorldCreator worldCreator, WorldModel worldModel) {
		this.view = view;
		this.controller = controller;
		this.statistics = statistics;
		this.worldCreator = worldCreator;
		this.worldModel = worldModel;

		initUI(view);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1366, 768);
		setLocationRelativeTo(null);
		setTitle("Creatures");

		setResizable(true);
		setVisible(true);
	}

	private void initUI(WorldView view) {
		JPanel rightContainer = new JPanel();
		rightContainer.setLayout(new BoxLayout(rightContainer, BoxLayout.PAGE_AXIS));

		textLabel = new JLabel("Creatures");
		textLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		speedLabel = new JLabel("Speed");
		speedLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		speedSlider = new JSlider(0, 15, 1);
		speedSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
		speedSlider.setMajorTickSpacing(5);
		speedSlider.setMinorTickSpacing(1);
		speedSlider.setSnapToTicks(true);
		speedSlider.setPaintLabels(true);
		speedSlider.setPaintTicks(true);
		speedSlider.addChangeListener(this);
		
		rightContainer.add(textLabel);
		rightContainer.add(Box.createVerticalStrut(10));
		rightContainer.add(speedLabel);
		rightContainer.add(speedSlider);

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, view, rightContainer);
		splitPane.setResizeWeight(0.8);

		add(splitPane);
		
		menuBar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		openItem = new JMenuItem("Open");
		openItem.addActionListener(this);
		preferencesItem = new JMenuItem("Preferences");
		preferencesItem.addActionListener(this);
		closeItem = new JMenuItem("Close");
		closeItem.addActionListener(this);
		fileMenu.add(openItem);
		fileMenu.addSeparator();
		fileMenu.add(preferencesItem);
		fileMenu.addSeparator();
		fileMenu.add(closeItem);
		
		worldMenu = new JMenu("Creation");
		createWorldItem = new JMenuItem("Create World");
		createWorldItem.addActionListener(this);
		worldMenu.add(createWorldItem);
		worldMenu.addSeparator();
		createCreatureItem = new JMenuItem("Create Creature");
		createCreatureItem.addActionListener(this);
		worldMenu.add(createCreatureItem);
		createCreaturesItem = new JMenuItem("Create Creatures");
		createCreaturesItem.addActionListener(this);
		worldMenu.add(createCreaturesItem);
		
		statisticsMenu = new JMenu("Statistics");
		showStatisticsItem = new JMenuItem("Show Statistics");
		showStatisticsItem.addActionListener(this);
		statisticsMenu.add(showStatisticsItem);
		
		menuBar.add(fileMenu);
		menuBar.add(worldMenu);
		menuBar.add(statisticsMenu);
		
		setJMenuBar(menuBar);
	}

	public void update(double delta) {
		if (statistics.getStatElements().size() > 0) {
			StatElement top = statistics.getTopStatElement();

			String displayString = "";
			displayString += "Creatures: " + top.getCreatureAmount() + "          " + 
					"Avg. Speed: " + String.format("%1$,.2f", top.getAverageSpeed());;

					textLabel.setText(displayString);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {		
		if (e.getSource() == speedSlider && ! isExternalUpdate) {
			JSlider source = (JSlider) e.getSource();
			int value = source.getValue();
			
			controller.setSpeed(value);
		}
	}

	public void setSpeedSlider(double d) {
		isExternalUpdate = true;
		speedSlider.setValue((int) d);
		isExternalUpdate = false;
	}

	public void setViewInputFocus() {
		view.requestFocus();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == closeItem) {
			System.exit(0);
		}
		else if (e.getSource() == createWorldItem) {
			new CreateWorldView(worldCreator);
		}
		else if (e.getSource() == createCreatureItem) {
			new CreateCreatureView(controller, worldModel.getWidth(), worldModel.getHeight());
		}
		else if (e.getSource() == createCreaturesItem) {
			new CreateCreaturesView(controller, worldModel.getWidth(), worldModel.getHeight());
		}
		else if (e.getSource() == showStatisticsItem) {
			new StatisticsView(statistics.getStatElements());
		}
	}
	
	public void setView(WorldView view) {
		this.view = view;
		splitPane.setLeftComponent(view);
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	public void setController(WorldController controller) {
		this.controller = controller;
	}

	public void setWorldCreator(WorldCreator worldCreator) {
		this.worldCreator = worldCreator;
	}

	public void setWorldModel(WorldModel worldModel) {
		this.worldModel = worldModel;
	}
}
