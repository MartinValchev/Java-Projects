package campaignProject;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BannerPositionModule {
	private JFrame bannerPosFrame;
	private JLabel websiteLabel;
	private JLabel campaignLabel;
	private JLabel poisitionNameLabel;
	private JLabel startDate;
	private JLabel	endDate;
	private String[] campaignArray;
	private String[] websiteArray;
	private JComboBox<String> campaignList;
	private JComboBox<String> websiteList;
	private JTextField positionName;
	private GeneralSettingsModule settings;
	private DateManipulator date;
	/*
	String[] campaigns = new String[] {"Effective Java", "Head First Java",
            "Thinking in Java", "Java for Dummies"};

JComboBox<String> campaignList = new JComboBox<>(campaigns);

//add to the parent container (e.g. a JFrame):
add(bookList);

//get the selected item:
String selectedBook = (String) campaignList.getSelectedItem(); */
	public BannerPositionModule() {
		bannerPosFrame= new JFrame("General Settings");
		bannerPosFrame.setSize(300, 550);
		bannerPosFrame.setResizable(false);
		bannerPosFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bannerPosFrame.setLayout(new BorderLayout());
		// label initialization
		campaignLabel = new JLabel("Campaign: ");
		websiteLabel = new JLabel("Website: ");
		// combobox initialize
		campaignArray = new String[]{"CampaignOne","CampaignTwo","CampaignThree"};
		websiteArray = new String[]{"WebsiteOne","WebsiteTwo","WebsiteThree"};
		campaignList = new JComboBox<>(campaignArray);
		websiteList = new JComboBox<>(campaignArray);
	}

}
