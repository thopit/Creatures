package de.thomas.creatures.implementation.view;

import de.thomas.creatures.implementation.statistics.StatElement;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StatisticsView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JFreeChart chart;
    private JPanel backPanel;
    private ChartPanel chartPanel;

    private JPanel downPanel;
    private JCheckBox allCheckBox;
    private JCheckBox[] checkBoxes;

    private List<StatElement> statElements;

    private String[] statLabels = {
        "Creatures",
        "Avg. Life",
        "Avg. Max Life",
        "Avg. Energy",
        "Avg. Max Energy",
        "Avg. Speed",
        "Avg. Vision Range",
        "Avg. Mating Energy Needed",
        "Avg. Breed Length",
        "Avg. Breed Progress Speed",
        "Gender Ratio",
        "Pregnancy Ratio"
    };

    private Color[] statColors = {
        new Color(255, 85, 85),
        new Color(85, 85, 255),
        new Color(85, 255, 85),
        new Color(255, 255, 85),
        new Color(255, 85, 255),
        new Color(85, 255, 255),
        new Color(255, 175, 175),
        new Color(128, 128, 128),
        new Color(192, 0, 0),
        new Color(0, 0, 192),
        new Color(0, 192, 0),
        new Color(192, 164, 0)
    };


    public StatisticsView(List<StatElement> statElements) {
        this.statElements = statElements;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1366, 768);
        setLocationRelativeTo(null);
        setTitle("Statistics");

        initUI(statElements);

        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initUI(List<StatElement> statElements) {
        backPanel = new JPanel();

        allCheckBox = new JCheckBox("Show all", true);
        allCheckBox.addActionListener(this);
        checkBoxes = new JCheckBox[statLabels.length];

        for (int k = 0; k < statLabels.length; k++) {
            checkBoxes[k] = new JCheckBox(statLabels[k], true);
            checkBoxes[k].addActionListener(this);
        }

        XYDataset dataset = createDataset(statElements, false);

        List<Color> colors = getColors();

        chart = createChart(dataset, colors);
        chartPanel = new ChartPanel(chart);

        backPanel.setLayout(new BorderLayout());

        backPanel.add(chartPanel, BorderLayout.CENTER);

        downPanel = new JPanel();
        downPanel.setLayout(new BoxLayout(downPanel, BoxLayout.LINE_AXIS));

        downPanel.add(allCheckBox);

        for (JCheckBox box : checkBoxes) {
            downPanel.add(box);
        }

        backPanel.add(downPanel, BorderLayout.SOUTH);

        setContentPane(backPanel);
    }

    private List<Color> getColors() {
        List<Color> colors = new ArrayList<>();

        for (int k = 0; k < checkBoxes.length; k++) {
            if (checkBoxes[k].isSelected()) {
                colors.add(statColors[k]);
            }
        }

        return colors;
    }

    private XYDataset createDataset(List<StatElement> statElements, boolean relative) {
        XYSeries[] allSeries = new XYSeries[statLabels.length];

        for (int k = 0; k < allSeries.length; k++) {
            allSeries[k] = new XYSeries(statLabels[k]);
        }

        double current = 0;
        for (StatElement element : statElements) {
            allSeries[0].add(current, element.getCreatureAmount());
            allSeries[1].add(current, element.getAverageLife());
            allSeries[2].add(current, element.getAverageMaxLife());
            allSeries[3].add(current, element.getAverageEnergy());
            allSeries[4].add(current, element.getAverageMaxEnergy());
            allSeries[5].add(current, element.getAverageSpeed());
            allSeries[6].add(current, element.getAverageVisionRange());
            allSeries[7].add(current, element.getAverageMatingEnergyNeeded());
            allSeries[8].add(current, element.getAverageBreedLength());
            allSeries[9].add(current, element.getAverageBreedProgressSpeed());
            allSeries[10].add(current, element.getGenderRatio());
            allSeries[11].add(current, element.getPregnancyRatio());

            current++;
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();

        for (int k = 0; k < allSeries.length; k++) {
            if (checkBoxes[k].isSelected()) {
                dataset.addSeries(allSeries[k]);
            }
        }

        return dataset;
    }

    private JFreeChart createChart(final XYDataset dataset, List<Color> colors) {
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Creature Statistics",
                "Time",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        chart.setBackgroundPaint(Color.white);

        final XYPlot plot = chart.getXYPlot();
        for (int k = 0; k < colors.size(); k++) {
            plot.getRenderer().setSeriesPaint(k, colors.get(k));
        }

        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        return chart;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source instanceof JCheckBox) {
            JCheckBox selectedBox = (JCheckBox) source;

            if (selectedBox == allCheckBox) {
                for (JCheckBox checkBox : checkBoxes) {
                    checkBox.setSelected(selectedBox.isSelected());
                }
            }

            List<Color> colors = getColors();
            XYDataset dataset = createDataset(statElements, false);
            chart = createChart(dataset, colors);

            backPanel.remove(chartPanel);

            chartPanel = new ChartPanel(chart);

            backPanel.add(chartPanel, BorderLayout.CENTER);

            revalidate();
            repaint();
        }
    }
}
