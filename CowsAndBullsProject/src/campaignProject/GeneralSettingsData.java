package campaignProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class GeneralSettingsData {
	// will be used to get data from settings database
	// send the new changes to the settings database
	// check userInput;
	private TestValidInput checkInput;
	private SettingsCommandBuffer commandBuffer;
	private String errorMessage = "impressions field does not contain valid number";
	private DatabaseConnection databaseConnection;

	public GeneralSettingsData() {
		checkInput = new TestValidInput();
		commandBuffer = new SettingsCommandBuffer();
		databaseConnection = new DatabaseConnection();
	}

	public boolean validateInput(String inputToCheck) {
		if (checkInput.checkSettingsInput(inputToCheck)) {
			return true;
		} else {
			return false;
		}

	}
	
	public void pushToCommandBuffer(String command) {
		commandBuffer.addToQueue(command);
	}
	public void sendCommandsToDatabase() throws ClassNotFoundException{
		commandBuffer.callSettingsCommands();
	}
	public void pullSettingsFromTable(GeneralSettingsModule settingsModule){
		try {
			ArrayList<String> positionRecords = new ArrayList<String>();
			positionRecords = databaseConnection.pullFromSettingsTable();
			for (String element:positionRecords){
				settingsModule.getListModel().addElement(element);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No connection to the database");
		}
	}
}
