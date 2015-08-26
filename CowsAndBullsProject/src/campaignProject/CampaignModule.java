package campaignProject;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CampaignModule {
	JFrame campFrame; 
	String campaignName;
	JLabel windowName;
	JLabel campNameLabel;
	JLabel startDateLabel;
	JLabel endDateLabel;
	JLabel positionLabel;
	JLabel impressionsLabel;
	JTextField campNameField;
	JTextField startDateField;
	JTextField endDateField;
	JTextField positionField;
	JTextField impressionsField;
	Container infoCont;
	JButton submitButton;
	JButton addwebSiteButton;
	JButton addPositionButton;
	BannerPositionModule banerPos;
	DateManipulator dates;
	
	public CampaignModule(String newCampName) {
		campFrame= new JFrame("Campaign Module");
		campFrame.setSize(300, 200);
		campFrame.setResizable(false);
		campFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		campFrame.setLayout(new BorderLayout());
		windowName = new JLabel("Add Campaign");
		campaignName = newCampName;
		infoCont = new Container();
		infoCont.setLayout(new GridLayout(5,2));
		// label initialize
		campNameLabel = new JLabel("Campaign Name: ");
		startDateLabel = new JLabel("Start Date: ");
		endDateLabel = new JLabel("End Date: ");
		positionLabel =  new JLabel("Position: ");
		impressionsLabel = new JLabel("Impressions: ");
		// TextField initialize
		campNameField = new JTextField(20);
		startDateField = new JTextField(10);
		endDateField = new JTextField(10);
		positionField = new JTextField(15);
		impressionsField = new JTextField(5);
		infoCont.add(campNameLabel);
		infoCont.add(campNameField);
		infoCont.add(startDateLabel);
		infoCont.add(startDateField);
		infoCont.add(endDateLabel);
		infoCont.add(endDateField);
		infoCont.add(positionLabel);
		infoCont.add(positionField);
		infoCont.add(impressionsLabel);
		infoCont.add(impressionsField);
		// submit button initialize
		submitButton = new JButton("Submit");
		campFrame.add(windowName,BorderLayout.NORTH);
		campFrame.add(infoCont,BorderLayout.CENTER);
		campFrame.add(submitButton,BorderLayout.SOUTH);
		campFrame.setVisible(true);
	}

}
