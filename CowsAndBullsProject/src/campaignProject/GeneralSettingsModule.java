package campaignProject;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GeneralSettingsModule {
	private JFrame settingsFrame;
	private Container limitCont;
	private List<String> websites;
	private List<String> positionNames;
	//!it should not be List of Strings but of Class BanerPosition
	private List<String> positions;
	private List<Integer> positionLimit;
	private JLabel windowNameLabel;
	private JLabel limitLabel;
	private JTextField positionLimitField;
	private JButton saveSettings;
	private DataModule websiteModule;
	private DataModule positionModule;
	
	public GeneralSettingsModule() {
		settingsFrame= new JFrame("General Settings");
		settingsFrame.setSize(300, 550);
		settingsFrame.setResizable(false);
		settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		settingsFrame.setLayout(new BorderLayout());
		limitCont = new Container();
		limitCont.setLayout(new FlowLayout());
		websites = new ArrayList<String>();
		positions= new ArrayList<String>();
		positionLimit = new ArrayList<Integer>();
		windowNameLabel = new JLabel("General Settings");
		websiteModule = new DataModule("Websites");
		positionModule = new DataModule("Positions");
		limitLabel = new JLabel("Position Limit: ");
		positionLimitField= new JTextField(6);
		// adding elements to infoCont
		limitCont.add(limitLabel);
		limitCont.add(positionLimitField);
		Box verticalBox = Box.createVerticalBox();
		verticalBox.add(websiteModule);
		verticalBox.add(positionModule);
		verticalBox.add(limitCont);
		///
		saveSettings = new JButton("Save settings");
		settingsFrame.getContentPane().add(windowNameLabel,BorderLayout.NORTH);
		settingsFrame.getContentPane().add(verticalBox,BorderLayout.CENTER);
		settingsFrame.getContentPane().add(saveSettings, BorderLayout.SOUTH);
		settingsFrame.setVisible(true);
	}
	public void setWebsite(String websiteName){
		websites.add(websiteName);
	}
	public List<String> getWebsites(){
		return websites;
	}
	public List<String> getPositions(){
		return positions;
	}
}
