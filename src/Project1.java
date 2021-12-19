import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Project1 {
    public static void main(String[] args) {

        //This part takes all the data in the file with the format needed.

        String fileName = "input_file.txt";
        List<String> data = new ArrayList<String>();

        try {
            File file = new File(fileName);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                //data contains all characters in the file
                data.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            File myObj = new File("output_file.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int numberOfStates = Integer.parseInt(data.get(0).substring(0, 1));
        int numberOfVariables = Integer.parseInt(data.get(1).substring(0, 1));
        int numberOfGoalStates = Integer.parseInt(data.get(2).substring(0, 1));
        List<State> states = new ArrayList<State>();
        for (String stateName : data.get(3).split("\\s+")) {
            State state = new State(stateName);
            states.add(state);
        }
        List<FinalState> goalStates = new ArrayList<FinalState>();
        for (String state : data.get(4).split("\\s+")) {
            FinalState goalState = new FinalState(state);
            goalStates.add(goalState);
        }
        List<String> variables = new ArrayList<String>();
        for (String state : data.get(5).split("\\s+")) {
            variables.add(state);
        }

        StartState startState = new StartState(data.get(6));

        int functionConstant = 3;
        int functionStartConstant = 7;
        String[][] transitionFunctionWritten = new String[numberOfStates * numberOfVariables][functionConstant];
        int indexOfTransitions = 0;
        int whichVariable = 0;
        while (data.get(functionStartConstant + indexOfTransitions).split("\\s+")[0].contains("q")) {
            for (int t = 0; t < variables.size(); t++) {
                if (variables.get(t).equals(data.get(functionStartConstant + indexOfTransitions).split("\\s+")[1])) {
                    whichVariable = t;
                }
            }
            for (int j = 0; j < functionConstant; j++) {
                transitionFunctionWritten[2 * (Character.getNumericValue(data.get(functionStartConstant + indexOfTransitions).split("\\s+")[0].charAt(1)) - 1) + whichVariable][j] = data.get(functionStartConstant + indexOfTransitions).split("\\s+")[j];
            }
            indexOfTransitions++;
        }

        Matrix transitionFunctionMatrix = new Matrix(numberOfStates, numberOfVariables);
        for (int i = 0; i < transitionFunctionWritten.length; i++) {
            int stateIndex = 0;
            if(transitionFunctionWritten[i][2]==null) {
                transitionFunctionMatrix.setMatrixValues(i / numberOfVariables, i % numberOfVariables, null);
            }else{
                for (int j = 0; j < states.size(); j++) {
                    if (states.get(j).getName().equals(transitionFunctionWritten[i][2])) {
                        stateIndex = j;
                    }
                }
                transitionFunctionMatrix.setMatrixValues(i / numberOfVariables, i % numberOfVariables, states.get(stateIndex));
            }
        }

        List<Strings> stringsToBeDetected = new ArrayList<Strings>();
        for (int i = functionStartConstant + indexOfTransitions; i < data.size(); i++) {
            Strings string = new Strings(data.get(i));
            stringsToBeDetected.add(string);
        }

        //This part simulates the computation behaviour of the DFA.
        String output = "";
        for (Strings stringToBeDetected : stringsToBeDetected) {
            List<String> routeTaken = new ArrayList<String>();
            State currentState = new State(startState.getName());
            routeTaken.add(currentState.getName());
            for (int i = 0; i < stringToBeDetected.getString().length(); i++) {
                String newLetter = stringToBeDetected.getString().substring(i, i + 1);
                int index = 0;
                for (int j = 0; j < variables.size(); j++) {
                    if (variables.get(j).equals(newLetter)) {
                        index = j;
                    }
                }
                int stateIndex = Integer.parseInt(currentState.getName().substring(1));
                if (transitionFunctionMatrix.getMatrix()[stateIndex - 1][index] == null) {
                    i = stringToBeDetected.getString().length();
                } else {
                    currentState.setName(transitionFunctionMatrix.getMatrix()[stateIndex - 1][index].getName());
                    routeTaken.add(currentState.getName());
                }
            }
            output = output + "\n" + routeTaken.toString();
            boolean Accepted = false;
            for (FinalState goalState : goalStates)
                if (currentState.getName().equals(goalState.getName())) {
                    Accepted = true;
                }
            if (Accepted)
                output = output + "\nAccepted";
            else
                output = output + "\nRejected";
        }
        try {
            FileWriter myWriter = new FileWriter("output_file.txt");
            myWriter.write(output);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
