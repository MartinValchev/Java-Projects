package campaignProject;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EditCampaignModule {
	private JFrame editCampFrame;
	private JPanel campaignPanel;
	private CampaignModule camp;
	private String[] campaignNames;
	private JComboBox campNames;

	public EditCampaignModule() {
		camp = new CampaignModule();
		campaignPanel = new JPanel();
		campaignPanel = camp.getContent();
		campaignNames = new String[] { "CampaignOne", "CampaignTwo",
				"CampaignThree", "CampaignFour" };
		campNames = new JComboBox(campaignNames);
		campNames.setSelectedIndex(0);
	}

	public void createGui() {
		editCampFrame = new JFrame("Campaign Module");
		editCampFrame.setSize(370, 340);
		editCampFrame.setLocation(410, 0);
		editCampFrame.setResizable(false);
		editCampFrame.setLayout(new BorderLayout());
		editCampFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editCampFrame.getContentPane().add(campNames,BorderLayout.NORTH);
		editCampFrame.getContentPane().add(campaignPanel,BorderLayout.CENTER);
		editCampFrame.setVisible(true);
	}
}
