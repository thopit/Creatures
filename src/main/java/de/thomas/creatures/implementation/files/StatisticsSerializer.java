package de.thomas.creatures.implementation.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import de.thomas.creatures.implementation.statistics.StatElement;

public class StatisticsSerializer {
	public void exportStatistics(Vector<StatElement> elements, File file) {
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(getSerialization(elements));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getSerialization(Vector<StatElement> elements) {
		StringBuilder builder = new StringBuilder();
		
		builder.append("\"Creature Amount\"");
		builder.append(";");
		builder.append("\"Average Life\"");
		builder.append(";");
		builder.append("\"Average Max Life\"");
		builder.append(";");
		builder.append("\"Average Energy\"");
		builder.append(";");
		builder.append("\"Average Max Energy\"");
		builder.append(";");
		builder.append("\"Average Speed\"");
		builder.append(";");
		builder.append("\"Average Vision Range\"");
		builder.append(";");
		builder.append("\"Average Mating Energy Needed\"");
		builder.append(";");
		builder.append("\"Average Breed Length\"");
		builder.append(";");
		builder.append("\"Average Breed Progress Speed\"");
		builder.append(";");
		builder.append("\"Gender Ratio\"");
		builder.append(";");
		builder.append("\"Pregnancy Ratio\"");
		builder.append(System.lineSeparator());
		
		for (StatElement element : elements) {
			builder.append(element.getCreatureAmount());
			builder.append(";");
			builder.append(element.getAverageLife());
			builder.append(";");
			builder.append(element.getAverageMaxLife());
			builder.append(";");
			builder.append(element.getAverageEnergy());
			builder.append(";");
			builder.append(element.getAverageMaxEnergy());
			builder.append(";");
			builder.append(element.getAverageSpeed());
			builder.append(";");
			builder.append(element.getAverageVisionRange());
			builder.append(";");
			builder.append(element.getAverageMatingEnergyNeeded());
			builder.append(";");
			builder.append(element.getAverageBreedLength());
			builder.append(";");
			builder.append(element.getAverageBreedProgressSpeed());
			builder.append(";");
			builder.append(element.getGenderRatio());
			builder.append(";");
			builder.append(element.getPregnancyRatio());
			builder.append(System.lineSeparator());
		}
		
		return builder.toString();
	}
	
}
