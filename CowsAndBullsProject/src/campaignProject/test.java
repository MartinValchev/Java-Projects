package campaignProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// CampaignModule test = new CampaignModule("HELLO");
		// MainMenu menu = new MainMenu();
		// GeneralSettingsModule set = new GeneralSettingsModule();
		// date.getDates("start");
		// date.getDates("end");
		// System.out.println(date.getDates("start"));
		// System.out.println(date.getDates("end"));
		File one = new File("C:\\Users\\martin\\Desktop\\hubAccounts.txt");
		Path example = one.toPath();
		Charset charset = Charset.forName("UTF-8");
		try (BufferedReader reader = Files.newBufferedReader(example, charset)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
}