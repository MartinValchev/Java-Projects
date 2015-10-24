package campaignProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenuModule {
	JFrame mainFrame;
	JButton addCampaign;
	JButton editCampaignButton;
	JButton showOccupacy;
	JButton settingsButton;
	JButton closeButton;
	JPanel campaignPanel;
	JPanel editCampaignPanel;
	JPanel occupacyPanel;
	Container settingsCont;
	JPanel centerPanel;
	JPanel closePanel;
	//Container mainFrameCont;
	
	public MainMenuModule() {
		mainFrame = new JFrame("Main Menu");
		mainFrame.setSize(400, 400);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new GridLayout(5,1));
		mainFrame.setBackground(new Color(205, 172, 141));
		// panels 
		campaignPanel = new JPanel();
		campaignPanel.setLayout(new FlowLayout());
		editCampaignPanel = new JPanel();
		editCampaignPanel.setLayout(new FlowLayout());
		occupacyPanel  = new JPanel();
		occupacyPanel.setLayout(new FlowLayout());
		centerPanel = new JPanel();
		centerPanel.setLayout(new FlowLayout());
		settingsCont = new Container();
		settingsCont.setLayout(new FlowLayout());
		closePanel = new JPanel();
		closePanel.setLayout(new FlowLayout());
		// buttons
		addCampaign = new JButton("Add Campaign");
		addCampaign.setBackground(new Color(205, 172, 141));
		addCampaign.setName("addCampaingButton");
		addCampaign.addActionListener(new AddCampaingListener());
		editCampaignButton = new JButton("Edit Campaign");
		editCampaignButton.setBackground(new Color(165, 108, 51));
		editCampaignButton.setName("EditCampaign");
		editCampaignButton.addActionListener(new EditCampaignListener());
		showOccupacy = new JButton("Show Occupacy");
		showOccupacy.setBackground(new Color(143, 71, 0));
		showOccupacy.setName("ShowOccupacyButton");
		showOccupacy.addActionListener(new ShowOccupacyListener());
		settingsButton = new JButton("Settings");
		settingsButton.setName("SettingsButton");
		settingsButton.setBackground(new Color(165, 108, 51));
		settingsButton.addActionListener(new SettingsListener());
		closeButton = new JButton("Close");
		closeButton.setName("CloseButton");
		closeButton.setBackground(new Color(205, 172, 141));
		closeButton.addActionListener(new CloseProgramListener());

		
		mainFrame.add(addCampaign);
		mainFrame.add(editCampaignButton);
		mainFrame.add(showOccupacy);
		mainFrame.add(settingsButton);
		mainFrame.add(closeButton);
		mainFrame.setVisible(true);
	}
	public class AddCampaingListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			CampaignModule newCampaign;
			try {
				newCampaign = new CampaignModule();
				newCampaign.generateGUI();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	public class EditCampaignListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				EditCampaignModule editCampaign;
				editCampaign = new EditCampaignModule();
				editCampaign.createGui();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		
	}
	public class ShowOccupacyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public class SettingsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			GeneralSettingsModule settings = new GeneralSettingsModule();
		}
		
	}
	public class CloseProgramListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			mainFrame.setVisible(false);
			mainFrame = null;
			
		}
		
	}
}
