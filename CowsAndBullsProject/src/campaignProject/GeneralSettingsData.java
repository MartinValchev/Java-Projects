package campaignProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class GeneralSettingsData {
	// will be used to get data from settings database
	// send the new changes to the settings database
	// check userInput;
	private TestValidInput checkInput;
	private SettingsCommandBuffer commandBuffer;
	private String errorMessage = "impressions field does not contain valid number";
	private DatabaseConnection databaseConnection;
	private int[] maxImpressions;
	private String[] positionName;
	private ArrayList<Integer> impressionsList;
	private ArrayList<String> positionList;
	private String separator = "-";
	private String[] positionArr;
	private int[] impressionsArr;
	private ArrayList<PositionTableRecord> addList;
	private ArrayList<PositionTableRecord> removeList;
	private ArrayList<PositionTableRecord> posList;
	private PositionManipulator positionManipulator;

	public GeneralSettingsData() throws SQLException {
		checkInput = new TestValidInput();
		commandBuffer = new SettingsCommandBuffer();
		databaseConnection = new DatabaseConnection();
		addList = new ArrayList<PositionTableRecord>();
		removeList = new ArrayList<PositionTableRecord>();
		positionManipulator = new PositionManipulator();
		posList = new ArrayList<PositionTableRecord>();
		posList = databaseConnection.pullFromPositionTable();
	}

	public boolean validateInput(String inputToCheck) {
		if (checkInput.checkSettingsInput(inputToCheck)) {
			return true;
		} else {
			return false;
		}

	}

	public void sortCommandList(ArrayList<PositionTableRecord> currentCommandsList,
			ArrayList<PositionTableRecord> newCommandsList) {
		boolean isFound = false;
		removeList = new ArrayList<PositionTableRecord>();
		addList = new ArrayList<PositionTableRecord>();
		for (int index = 0; index < newCommandsList.size(); index++) {
			isFound = false;
			for (int current = 0; current < currentCommandsList.size(); current++) {
				if (newCommandsList.get(index).getPostionRecord()
						.equals(currentCommandsList.get(current).getPostionRecord())) {
					isFound = true;
					newCommandsList.remove(index);
					index--;
					currentCommandsList.remove(current);
					break;
				}

			}
			if (!isFound) {
				addList.add(newCommandsList.get(index));
				newCommandsList.remove(index);
				index--;
			}

		}
		if (currentCommandsList.size() > 0 && newCommandsList.size() == 0) {
			for (int current = 0; current < currentCommandsList.size(); current++) {
				removeList.add(currentCommandsList.get(current));
			}
		}

	}

	public void compareLists(ArrayList<PositionTableRecord> newCommandsList) {
		sortCommandList(posList, newCommandsList);
		if (addList.size() > 0) {
			commandBuffer.callAddSettingsCommands(addList);
		}
		if (removeList.size() > 0) {
			commandBuffer.callRemoveSettingsCommands(positionArr, impressionsArr);
		}

	}

	public void fillListModel(DefaultListModel<String> listModel) {
		for (int i = 0; i < posList.size(); i++) {
			String currentElement = posList.get(i).getPostionRecord();
			listModel.addElement(currentElement);
		}
	}
}
