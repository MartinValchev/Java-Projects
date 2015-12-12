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
	private String separator = "-";
	private ArrayList<PositionTableRecord> addList;
	private ArrayList<PositionTableRecord> removeList;
	private ArrayList<PositionTableRecord> posList;
	private PositionManipulator positionManipulator;
	private PositionCash positionCash;

	public GeneralSettingsData() throws SQLException {
		checkInput = new TestValidInput();
		commandBuffer = new SettingsCommandBuffer();
		addList = new ArrayList<PositionTableRecord>();
		removeList = new ArrayList<PositionTableRecord>();
		positionManipulator = new PositionManipulator();
		posList = new ArrayList<PositionTableRecord>();
		positionCash = new PositionCash();
		posList = positionCash.getPositionList();
	}

	public boolean validateInput(String impressions) {
		int number;
		try {
			number = Integer.parseInt(impressions);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean compareRecords(DefaultListModel<PositionTableRecord> listModel, PositionTableRecord record) {
		boolean isFound = false;
		for (int i = 0; i < listModel.size(); i++) {
			if (listModel.getElementAt(i).getPositionName().equals(record.getPositionName())) {
				isFound = true;
			}
		}
		return isFound;
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

	public void compareLists(ArrayList<PositionTableRecord> newCommandsList) throws ClassNotFoundException {
		sortCommandList(posList, newCommandsList);
		if (addList.size() > 0) {
			positionCash.addToPositionCash(addList);
		}
		if (removeList.size() > 0) {
			positionCash.removeFromPositionCash(removeList);
		}

	}

	public void fillListModel(DefaultListModel<PositionTableRecord> listModel) {
		for (int i = 0; i < posList.size(); i++) {
			PositionTableRecord currentElement = posList.get(i);
			listModel.addElement(currentElement);
		}
	}
}
