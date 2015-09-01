package campaignProject;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CampaignModule {
	private JFrame campFrame; 
	private String campaignName;
	private JLabel windowName;
	private JLabel campNameLabel;
	private JLabel startDateLabel;
	private JLabel endDateLabel;
	private JTextField campNameField;
	private JTextField startDateField;
	private JTextField endDateField;
	private JPanel content;
	//JTextField positionField;
	private JTextField impressionsField;
	private Container infoCont;
	private Container campDetails;
	private Container posDetails;
	private JButton submitButton;
	private JButton addwebSiteButton;
	private JButton addPositionButton;
	//BannerPositionModule banerPos;
	private DateManipulator dates;
	private String[] positionStrings;
	private JComboBox positionList;
	private CampaignPosModule positionModule;
	public CampaignModule() {
		/*campFrame= new JFrame("Campaign Module");
		campFrame.setSize(370, 340);
		campFrame.setResizable(false);
		campFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		campFrame.setLayout(new BorderLayout()); */
		content = new JPanel();
		content.setLayout(new BorderLayout());
		windowName = new JLabel("Campaign");
		campaignName = "";
		campDetails = new Container();
		infoCont = new Container();
		posDetails = new Container();
		posDetails.setLayout(new FlowLayout(FlowLayout.CENTER));
		campDetails.setLayout(new GridLayout(3,2));
		infoCont.setLayout(new BoxLayout(infoCont, BoxLayout.Y_AXIS));
		// label initialize
		campNameLabel = new JLabel("Campaign Name: ");
		startDateLabel = new JLabel("Campaign Start Date: ");
		endDateLabel = new JLabel("Campaign End Date: ");
		// TextField initialize
		campNameField = new JTextField(20);
		startDateField = new JTextField(10);
		endDateField = new JTextField(10);
		// Position Module initialize
		positionModule = new CampaignPosModule();
		// Container adding
		campDetails.add(campNameLabel);
		campDetails.add(campNameField);
		campDetails.add(startDateLabel);
		campDetails.add(startDateField);
		campDetails.add(endDateLabel);
		campDetails.add(endDateField);
		posDetails.add(positionModule);
		infoCont.add(campDetails);
		infoCont.add(posDetails);
		submitButton = new JButton("Submit");
		content.add(windowName,BorderLayout.NORTH);
		content.add(infoCont,BorderLayout.CENTER);
		content.add(submitButton,BorderLayout.SOUTH);
		
	}
	public JPanel getContent(){
		return content;
	}
	public void generateGUI(){
		campFrame= new JFrame("Campaign Module");
		campFrame.setSize(370, 340);
		campFrame.setLocation(410,0);
		campFrame.setResizable(false);
		campFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		campFrame.getContentPane().add(content);
		campFrame.setVisible(true);
	}
}
