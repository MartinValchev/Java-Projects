package campaignProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GeneralSettingsData {
	private ArrayList<String> positionsList;
	private ArrayList<String> websitesList;
	private int websitePosLimit;
	public GeneralSettingsData(DataModule position,DataModule website) {
		positionsList = position.getEnteredValues(); 
		websitesList = website.getEnteredValues();
	}

	public void readFiles(File fileName) {
		// File one = new File("C:\\Users\\martin\\Desktop\\hubAccounts.txt");
		Path currentPath = fileName.toPath();
		Charset charset = Charset.forName("UTF-8");
		try (BufferedReader reader = Files.newBufferedReader(currentPath,
				charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
	}

	public void writeFiles(File fileName, String input) {
		Charset charset = Charset.forName("US-ASCII");
		Path currentPath = fileName.toPath();
		try (BufferedWriter writer = Files.newBufferedWriter(currentPath,
				charset)) {
			writer.write(input, 0, input.length());
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
	}
	public void setWebsites(){
		
	}
}
