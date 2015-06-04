package de.thomas.creatures.implementation.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import de.thomas.creatures.implementation.statistics.StatElement;

public class StatisticsView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFreeChart chart;
	private JPanel backPanel;
	private ChartPanel chartPanel;
	
	private JPanel downPanel;
	private JCheckBox creatureAmountCheckBox;
	private JCheckBox averageLifeCheckBox;
	private JCheckBox averageMaxLifeCheckBox;
	private JCheckBox averageEnergyLifeCheckBox;
	private JCheckBox averageMaxEnergyCheckBox;
	private JCheckBox averageSpeedCheckBox;
	private JCheckBox averageVisionRangeCheckBox;
	private JCheckBox averageMatingEnergyNeededCheckBox;
	private JCheckBox averageBreedLengthCheckBox;
	private JCheckBox averageBreedProgressSpeedCheckBox;
	private JCheckBox genderRatioCheckBox;
	private JCheckBox pregnancyRatioCheckBox;

	public StatisticsView(Vector<StatElement> statElements) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1366, 768);
		setLocationRelativeTo(null);
		setTitle("Statistics");
		
		initUI(statElements);
		
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void initUI(Vector<StatElement> statElements) {
		backPanel = new JPanel();
		
		creatureAmountCheckBox = new JCheckBox("Creatures");
		averageLifeCheckBox = new JCheckBox("Average Life");
		averageMaxLifeCheckBox = new JCheckBox("Average Max Life");
		averageEnergyLifeCheckBox = new JCheckBox("Average Max Life");
		averageMaxEnergyCheckBox = new JCheckBox("Average Max Life");
		averageSpeedCheckBox = new JCheckBox("Average Max Life");
		averageVisionRangeCheckBox = new JCheckBox("Average Max Life");
		averageMatingEnergyNeededCheckBox = new JCheckBox("Average Max Life");
		averageBreedLengthCheckBox = new JCheckBox("Average Max Life");
		averageBreedProgressSpeedCheckBox = new JCheckBox("Average Max Life");
		genderRatioCheckBox = new JCheckBox("Average Max Life");
		pregnancyRatioCheckBox = new JCheckBox("Average Max Life");
		
		XYDataset dataset = createDataset(statElements, false);
		chart = createChart(dataset);
		chartPanel = new ChartPanel(chart);
		
	
		//backPanel.setLayout(new BoxLayout(backPanel, BoxLayout.PAGE_AXIS));
		backPanel.setLayout(new BorderLayout());
		
		//backPanel.add(chartPanel);
		backPanel.add(chartPanel, BorderLayout.CENTER);
		
		downPanel = new JPanel();
		downPanel.add(creatureAmountCheckBox);
		downPanel.add(averageLifeCheckBox);
		downPanel.add(averageMaxLifeCheckBox);
		downPanel.add(averageEnergyLifeCheckBox);
		downPanel.add(averageMaxEnergyCheckBox);
		downPanel.add(averageSpeedCheckBox);
		downPanel.add(averageVisionRangeCheckBox);
		downPanel.add(averageMatingEnergyNeededCheckBox);
		downPanel.add(averageBreedLengthCheckBox);
		downPanel.add(averageBreedProgressSpeedCheckBox);
		downPanel.add(genderRatioCheckBox);
		downPanel.add(pregnancyRatioCheckBox);
		
		//backPanel.add(downPanel);
		backPanel.add(downPanel, BorderLayout.SOUTH);
	
		setContentPane(backPanel);
	}
	
	//TODO implement relative statistics view
	private XYDataset createDataset(Vector<StatElement> statElements, boolean relative) {
		XYSeries creatureAmount = new XYSeries("Creatures");
		XYSeries averageLife = new XYSeries("Average Life");
		XYSeries averageMaxLife = new XYSeries("Average Max Life");
		XYSeries averageEnergy = new XYSeries("Average Energy");
		XYSeries averageMaxEnergy = new XYSeries("Average Max Energy");
		XYSeries averageSpeed = new XYSeries("Average Speed");
		XYSeries averageVisionRange = new XYSeries("Average Vision Range");
		XYSeries averageMatingEnergyNeeded = new XYSeries("Average Mating Energy Needed");
		XYSeries averageBreedLength = new XYSeries("Average Breed Length");
		XYSeries averageBreedProgressSpeed = new XYSeries("Average Breed Progress Speed");
		XYSeries genderRatio = new XYSeries("Gender Ratio");
		XYSeries pregnancyRatio = new XYSeries("Pregnancy Ratio");
		
		double current = 0;
		for (StatElement element : statElements) {
			creatureAmount.add(current, element.getCreatureAmount());
			averageLife.add(current, element.getAverageLife());
			averageMaxLife.add(current, element.getAverageMaxLife());
			averageEnergy.add(current, element.getAverageEnergy());
			averageMaxEnergy.add(current, element.getAverageMaxEnergy());
			averageSpeed.add(current, element.getAverageSpeed());
			averageVisionRange.add(current, element.getAverageVisionRange());
			averageMatingEnergyNeeded.add(current, element.getAverageMatingEnergyNeeded());
			averageBreedLength.add(current, element.getAverageBreedLength());
			averageBreedProgressSpeed.add(current, element.getAverageBreedProgressSpeed());
			genderRatio.add(current, element.getGenderRatio());
			pregnancyRatio.add(current, element.getGenderRatio());
			
			current++;
		}
		
		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(creatureAmount);
		dataset.addSeries(averageLife);
		dataset.addSeries(averageMaxLife);
		dataset.addSeries(averageEnergy);
		dataset.addSeries(averageMaxEnergy);
		dataset.addSeries(averageSpeed);
		dataset.addSeries(averageVisionRange);
		dataset.addSeries(averageMatingEnergyNeeded);
		dataset.addSeries(averageBreedLength);
		dataset.addSeries(averageBreedProgressSpeed);
		dataset.addSeries(genderRatio);
		dataset.addSeries(pregnancyRatio);

		return dataset;
	}

	private JFreeChart createChart(final XYDataset dataset) {
		// create the chart...
		final JFreeChart chart = ChartFactory.createXYLineChart(
				"Creature Statistics",      // chart title
				"Time",                      // x axis label
				"Value",                      // y axis label
				dataset,                  // data
				PlotOrientation.VERTICAL,
				true,                     // include legend
				true,                     // tooltips
				false                     // urls
				);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		chart.setBackgroundPaint(Color.white);

		// get a reference to the plot for further customisation...
		final XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.lightGray);
		//    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);


		// change the auto tick unit selection to integer units only...
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		// OPTIONAL CUSTOMISATION COMPLETED.

		return chart;
	}
}
