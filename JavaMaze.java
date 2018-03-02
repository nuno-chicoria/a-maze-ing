import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class JavaMaze {

	//Method to get the number of rows and columns in the file in order to initialize a 2D array later.
	public static int[] getNbOfRowsCols(String filepath) {
		Scanner scanIn;
		String inputLine;
		int rows = 0;
		int cols = 0;
		try {
			scanIn = new Scanner(new File(filepath));
			//Skip the line with the header.
			scanIn.nextLine();
			while (scanIn.hasNextLine()) {
				inputLine = scanIn.nextLine();
				String[] line = inputLine.split(",");
				//For each line we increment +1 to the rows variable.
				rows++;
				//After Splitting the line by commas, the length of the created 1D array is the number of columns in the 2D array.
				cols = line.length;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		int[] nbOfRowsCols = {rows, cols};
		return nbOfRowsCols;
	}

	//Method to import the information of the maze in the format of a 2D array.
	public static String[][] importMaze(String filepath, int[] nbOfRowsCols) {
		Scanner scanIn;
		String inputLine;
		//Initialization of the 2D array with the values we obtained from the getNbOfRowsCols method.
		String [][] maze = new String[nbOfRowsCols[0]][nbOfRowsCols[1]];
		int rows = 0;
		try {
			scanIn = new Scanner(new File(filepath));
			//Skip the line with the header.
			scanIn.nextLine();
			while(scanIn.hasNextLine()) {
				inputLine = scanIn.nextLine();
				String[] line = inputLine.split(",");
				//For each line we use a for loop to fill the row and all its columns with the information in the previously created 1D array.
				for (int col = 0; col < line.length; col++)
					maze[rows][col] = line[col];
				rows++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return maze;
	}

	//Method to print the maze using information in the 2D array.
	public static void printMaze(String[][] maze) {
		//In this method we access all the Y coordinates for each X in each loop of this 1st for loop. That's why a multiplier variable is created.
		for (int mult = 1; mult <= Integer.parseInt(maze[maze.length - 1][0]) + 1; mult++) {
			//In this for loop we will print the information of the NorthWall for all the Y's in one X. For each loop we change the X.
			//There is no need to print the southWall information as this is the same as the northWall information of the cell bellow.
			for (int line = maze.length - (maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1) * mult); line < maze.length - (maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1) * (mult - 1)); line++) {
				if (maze[line][2].equals("wall"))
					System.out.print("+---");
				else if (maze[line][2].equals("fake"))
					System.out.print("+---");
				else if (maze[line][2].equals("breakable"))
					System.out.print("+-b-");
				else if (maze[line][2].equals("door"))
					System.out.print("+-d-");
				else
					System.out.print("+   ");
			}
			//Prints the final + for each line.
			System.out.print("+");
			System.out.println();
			//In this for loop we will print the information of the westWall for all the Y's in one X. For each loop we change the X.
			//There is no need to print the eastWall information as this is the same as the westWall information of the cell to the right.
			//The if loops inside the for loops first analyze if it is a "wall", a "fake", a "door", a "breakable" or a "no" and then the "Object" that it contains inside.
			for (int line = maze.length - (maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1) * mult); line < maze.length - (maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1) * (mult - 1)); line++) {
				if (maze[line][5].equals("wall") && maze[line][6].equals("no"))
					System.out.print("|   ");
				else if (maze[line][5].equals("wall") && maze[line][6].equals("key"))
					System.out.print("|   ");
				else if (maze[line][5].equals("wall") && maze[line][6].equals("hammer"))
					System.out.print("|   ");
				else if (maze[line][5].equals("wall") && (maze[line][6].equals("start") || maze[line][6].equals("S")))
					System.out.print("| S ");
				else if (maze[line][5].equals("wall") && (maze[line][6].equals("end") || maze[line][6].equals("E")))
					System.out.print("| E ");
				else if (maze[line][5].equals("wall") && maze[line][6].equals("player"))
					System.out.print("| P ");
				else if (maze[line][5].equals("fake") && maze[line][6].equals("no"))
					System.out.print("|   ");
				else if (maze[line][5].equals("fake") && maze[line][6].equals("key"))
					System.out.print("|   ");
				else if (maze[line][5].equals("fake") && maze[line][6].equals("hammer"))
					System.out.print("|   ");
				else if (maze[line][5].equals("fake") && (maze[line][6].equals("start") || maze[line][6].equals("S")))
					System.out.print("| S ");
				else if (maze[line][5].equals("fake") && (maze[line][6].equals("end") || maze[line][6].equals("E")))
					System.out.print("| E ");
				else if (maze[line][5].equals("fake") && maze[line][6].equals("player"))
					System.out.print("| P ");
				else if (maze[line][5].equals("breakable") && maze[line][6].equals("no"))
					System.out.print("b   ");
				else if (maze[line][5].equals("breakable") && maze[line][6].equals("key"))
					System.out.print("b   ");
				else if (maze[line][5].equals("breakable") && maze[line][6].equals("hammer"))
					System.out.print("b   ");
				else if (maze[line][5].equals("breakable") && (maze[line][6].equals("start") || maze[line][6].equals("S")))
					System.out.print("b S ");
				else if (maze[line][5].equals("breakable") && (maze[line][6].equals("end") || maze[line][6].equals("E")))
					System.out.print("b E ");
				else if (maze[line][5].equals("breakable") && maze[line][6].equals("player"))
					System.out.print("b P ");
				else if (maze[line][5].equals("door") && maze[line][6].equals("no"))
					System.out.print("d   ");
				else if (maze[line][5].equals("door") && maze[line][6].equals("key"))
					System.out.print("d   ");
				else if (maze[line][5].equals("door") && maze[line][6].equals("hammer"))
					System.out.print("d   ");
				else if (maze[line][5].equals("door") && (maze[line][6].equals("start") || maze[line][6].equals("S")))
					System.out.print("d S ");
				else if (maze[line][5].equals("door") && (maze[line][6].equals("end") || maze[line][6].equals("E")))
					System.out.print("d E ");
				else if (maze[line][5].equals("door") && maze[line][6].equals("player"))
					System.out.print("d P ");
				else if (maze[line][5].equals("no") && maze[line][6].equals("no"))
					System.out.print("    ");
				else if (maze[line][5].equals("no") && maze[line][6].equals("key"))
					System.out.print("    ");
				else if (maze[line][5].equals("no") && maze[line][6].equals("hammer"))
					System.out.print("    ");
				else if (maze[line][5].equals("no") && (maze[line][6].equals("start") || maze[line][6].equals("S")))
					System.out.print("  S ");
				else if (maze[line][5].equals("no") && (maze[line][6].equals("end") || maze[line][6].equals("E")))
					System.out.print("  E ");
				else if (maze[line][5].equals("no") && maze[line][6].equals("player"))
					System.out.print("  P ");
			}
			//Prints the final | for each line.
			System.out.print("|");
			System.out.println();
		}
		//Since we are only printing the information of the northWall we need this last for loop to print the bottom line of the maze.
		for (int i = 0; i < maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1); i++)
			System.out.print("+---");
		//Prints the final + of the bottom line.
		System.out.println("+");
	}

	//Method to play the maze based on the information in the 2D array.
	//NOTE: The player position will be represented by a P on the maze. I chose to save the position by adding a P into the Object column.
	public static int navigateMaze(String mazeName) {
		//Boolean to state if the player finished the maze.
		Boolean gameEnded = false;
		String movement = "";
		//2D array obtained by calling the previous methods.
		String [][] maze = importMaze(mazeName, getNbOfRowsCols(mazeName));
		//Three variables to keep track of the players performance.
		int nbOfSteps = 0;
		int nbOfKeys = 0;
		int nbOfHammers = 0;
		//With this for loop we place the player in the starting position of the maze.
		for (int i = 0; i < maze.length; i++)
			if( maze[i][6].equals("start") || maze[i][6].equals("S"))
				maze[i][6] = "player";
		//While loop goes on until the player finishes the maze.
		while(gameEnded == false) {
			//Call the printMaze method so the player can have a look and decide the next move for each turn.
			printMaze(maze);
			//Information about the steps taken until that turn and the number of keys and hammers in the player's possession.
			System.out.println("\n\nNb of steps taken: " + nbOfSteps + "\nNb of keys: " + nbOfKeys + "\nNb of hammers: " + nbOfHammers + "\n");
			//Reader to obtain information about the player's desired next move.
			try {
				BufferedReader move = new BufferedReader (new InputStreamReader(System.in));
				System.out.print("Please select the walking direction using W (up), A (left), S (down) or D (right): ");
				movement = move.readLine();
			} catch (Exception e) {
				System.out.println(e);
			}
			//For each turn we increment +1 in the nbOfSteps variable.
			nbOfSteps++;
			//We use this variable to get the line in the array where the player is in each turn.
			int playerPosition = 0;
			//If the player decides to go up we will use one of the if statements inside this one.
			if (movement.equals("W") || movement.equals("w")) {
				//For loop to determine the player's position before we execute the move.
				for (int i = 0; i < maze.length; i++)
					if( maze[i][6].equals("player"))
						playerPosition = i;
				try {
					//If the southWall is represented by a "wall" the movement is not possible.
					if (maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][3].equals("wall"))
						System.out.println("You can't move in that direction.\n");
					//If the southWall is represented by a "no" the movement is possible.
					else if (maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][3].equals("no")) {
						//These two lines check if there is a key or a hammer in the new cell.
						if (maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][6].equals("key"))
							nbOfKeys++;
						if (maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][6].equals("hammer"))
							nbOfHammers++;
						//This line checks if the player arrived to the end cell and changes the boolean to true.
						if (maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][6].equals("end") || maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][6].equals("E"))
							gameEnded = true;
						//These two lines change the player position to the new cell.
						maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][6] = "player";
						maze[playerPosition][6] = "no";
					}
					//If the southWall is represented by a "fake" the movement is possible.
					else if (maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][3].equals("fake")) {
						//These two lines check if there is a key or a hammer in the new cell.
						if (maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][6].equals("key"))
							nbOfKeys++;
						if (maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][6].equals("hammer"))
							nbOfHammers++;
						//This line checks if the player arrived to the end cell and changes the boolean to true.
						if (maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][6].equals("end") || maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][6].equals("E"))
							gameEnded = true;
						//These two lines change the player position to the new cell.
						maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][6] = "player";
						maze[playerPosition][6] = "no";
						//Since the wall is fake and the player discovered it, these two lines remove that wall.
						maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][3] = "no";
						maze[playerPosition][2] = "no";
					}
					//If the southWall is represented by a "breakable".
					else if (maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][3].equals("breakable"))
						//Check if the player has a hammer to break the wall.
						if(nbOfHammers > 0) {
							//These two lines check if there is a key or a hammer in the new cell.
							if (maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][6].equals("key"))
								nbOfKeys++;
							if (maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][6].equals("hammer"))
								nbOfHammers++;
							//This line checks if the player arrived to the end cell and changes the boolean to true.
							if (maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][6].equals("end") || maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][6].equals("E"))
								gameEnded = true;
							//These two lines change the player position to the new cell.
							maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][6] = "player";
							maze[playerPosition][6] = "no";
							//These two lines remove the wall that has been broken.
							maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][3] = "no";
							maze[playerPosition][2] = "no";
							//Removes one hammer from the player's possession.
							nbOfHammers--;
						} else {
							System.out.println("You need a hammer to break this wall.\n");
						}

					//If the southWall is represented by a "door".
					else if (maze[playerPosition + maze.length / Integer.parseInt(maze[maze.length - 1][1]) - 1][3].equals("door"))
						//Check if the player has a key to open the door.
						if(nbOfKeys > 0) {
							//These two lines check if there is a key or a hammer in the new cell.
							if (maze[playerPosition + maze.length / Integer.parseInt(maze[maze.length - 1][1]) - 1][6].equals("key"))
								nbOfKeys++;
							if (maze[playerPosition + maze.length / Integer.parseInt(maze[maze.length - 1][1]) - 1][6].equals("hammer"))
								nbOfHammers++;
							//This line checks if the player arrived to the end cell and changes the boolean to true.
							if (maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][6].equals("end") || maze[playerPosition + maze.length / (Integer.parseInt(maze[maze.length - 1][1]) + 1)][6].equals("E"))
								gameEnded = true;
							//These two lines change the player position to the new cell.
							maze[playerPosition + maze.length / Integer.parseInt(maze[maze.length - 1][1]) - 1][6] = "player";
							maze[playerPosition][6] = "no";
							//These two lines remove the wall that has been broken.
							maze[playerPosition + maze.length / Integer.parseInt(maze[maze.length - 1][1]) - 1][3] = "no";
							maze[playerPosition][2] = "no";
							//Removes one key from the player's possession.
							nbOfKeys--;
						} else {
							System.out.println("You need a key to open this door.\n");
						}
				} catch (Exception e) {
					System.out.println(e + "\n");
				}
			}
			//If the player decides to go to the left we will use one of the if statements inside this one.
			else if (movement.equals("A") || movement.equals("a")) {
				//For loop to determine the player's position before we execute the move.
				for (int i = 0; i < maze.length; i++)
					if( maze[i][6].equals("player"))
						playerPosition = i;
				try {
					//If the eastWall is represented by a "wall" the movement is not possible.
					if (maze[playerPosition - 1][4].equals("wall"))
						System.out.println("You can't move in that direction.\n");
					//If the eastWall is represented by a "no" the movement is possible.
					else if (maze[playerPosition - 1][4].equals("no")) {
						//These two lines check if there is a key or a hammer in the new cell.
						if (maze[playerPosition - 1][6].equals("key"))
							nbOfKeys++;
						if (maze[playerPosition - 1][6].equals("hammer"))
							nbOfHammers++;
						//This line checks if the player arrived to the end cell and changes the boolean to true.
						if (maze[playerPosition - 1][6].equals("end") || maze[playerPosition - 1][6].equals("E"))
							gameEnded = true;
						//These two lines change the player position to the new cell.
						maze[playerPosition - 1][6] = "player";
						maze[playerPosition][6] = "no";
					}
					//If the eastWall is represented by a "fake" the movement is possible.
					else if (maze[playerPosition - 1][4].equals("fake")) {
						//These two lines check if there is a key or a hammer in the new cell.
						if (maze[playerPosition - 1][6].equals("key"))
							nbOfKeys++;
						if (maze[playerPosition - 1][6].equals("hammer"))
							nbOfHammers++;
						//This line checks if the player arrived to the end cell and changes the boolean to true.
						if (maze[playerPosition - 1][6].equals("end") || maze[playerPosition - 1][6].equals("E"))
							gameEnded = true;
						//These two lines change the player position to the new cell.
						maze[playerPosition - 1][6] = "player";
						maze[playerPosition][6] = "no";
						//Since the wall is fake and the player discovered it, these two lines remove that wall.
						maze[playerPosition - 1][4] = "no";
						maze[playerPosition][5] = "no";
					}
					//If the eastWall is represented by a "breakable".
					else if (maze[playerPosition - 1][4].equals("breakable"))
						//Check if the player has a hammer to break down the wall.
						if(nbOfHammers > 0) {
							//These two lines check if there is a key or a hammer in the new cell.
							if (maze[playerPosition - 1][6].equals("key"))
								nbOfKeys++;
							if (maze[playerPosition - 1][6].equals("hammer"))
								nbOfHammers++;
							//This line checks if the player arrived to the end cell and changes the boolean to true.
							if (maze[playerPosition - 1][6].equals("end") || maze[playerPosition - 1][6].equals("E"))
								gameEnded = true;
							//These two lines change the player position to the new cell.
							maze[playerPosition - 1][6] = "player";
							maze[playerPosition][6] = "no";
							//These two lines remove the wall that has been broken.
							maze[playerPosition - 1][4] = "no";
							maze[playerPosition][5] = "no";
							//Removes one hammer from the player's possession.
							nbOfHammers--;
						} else {
							System.out.println("You need a hammer to break this wall.\n");
						}
					//If the eastWall is represented by a "door".
					else if (maze[playerPosition - 1][4].equals("door"))
						//Check if the player has a key to open the door.
						if(nbOfKeys > 0) {
							//These two lines check if there is a key or a hammer in the new cell.
							if (maze[playerPosition - 1][6].equals("key"))
								nbOfKeys++;
							if (maze[playerPosition - 1][6].equals("hammer"))
								nbOfHammers++;
							//This line checks if the player arrived to the end cell and changes the boolean to true.
							if (maze[playerPosition - 1][6].equals("end") || maze[playerPosition - 1][6].equals("E"))
								gameEnded = true;
							//These two lines change the player position to the new cell.
							maze[playerPosition - 1][6] = "player";
							maze[playerPosition][6] = "no";
							//These two lines remove the door that has been open.
							maze[playerPosition - 1][4] = "no";
							maze[playerPosition][5] = "no";
							//Removes one key from the player's possession.
							nbOfKeys--;
						} else {
							System.out.println("You need a key to open this door.\n");
						}
				} catch (Exception e) {
					System.out.println(e + "\n");
				}
			}
			//If the player decides to go down we will use one of the if statements inside this one.
			//The code follows the same principles of the moving up code and so, the same documentation.
			//Here, instead of checking the southWall of the cell we are moving to we check the northWall.
			else if (movement.equals("S") || movement.equals("s")) {
				for (int i = 0; i < maze.length; i++)
					if( maze[i][6].equals("player"))
						playerPosition = i;
				try {
					if (maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][2].equals("wall"))
						System.out.println("You can't move in that direction.\n");
					else if (maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][2].equals("no")) {
						if (maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][6].equals("key"))
							nbOfKeys++;
						if (maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][6].equals("hammer"))
							nbOfHammers++;
						if (maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][6].equals("end") || maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][6].equals("E"))
							gameEnded = true;
						maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][6] = "player";
						maze[playerPosition][6] = "no";
					}
					else if (maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][2].equals("fake")) {
						if (maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][6].equals("key"))
							nbOfKeys++;
						if (maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][6].equals("hammer"))
							nbOfHammers++;
						if (maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][6].equals("end") || maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][6].equals("E"))
							gameEnded = true;
						maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][6] = "player";
						maze[playerPosition][6] = "no";
						maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][2] = "no";
						maze[playerPosition][3] = "no";
					}
					else if (maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][2].equals("breakable"))
						if(nbOfHammers > 0) {
							if (maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][6].equals("key"))
								nbOfKeys++;
							if (maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][6].equals("hammer"))
								nbOfHammers++;
							if (maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][6].equals("end") || maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][6].equals("E"))
								gameEnded = true;
							maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][6] = "player";
							maze[playerPosition][6] = "no";
							maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][2] = "no";
							maze[playerPosition][3] = "no";
							nbOfHammers--;
						}
						else {
							System.out.println("You need a hammer to break this wall.\n");
						}
					else if (maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][2].equals("door"))
						if(nbOfKeys > 0) {
							if (maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][6].equals("key"))
								nbOfKeys++;
							if (maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][6].equals("hammer"))
								nbOfHammers++;
							if (maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][6].equals("end") || maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][6].equals("E"))
								gameEnded = true;
							maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][6] = "player";
							maze[playerPosition][6] = "no";
							maze[playerPosition - maze.length / Integer.parseInt(maze[maze.length - 1][1]) + 1][2] = "no";
							maze[playerPosition][3] = "no";
							nbOfKeys--;
						}
						else {
							System.out.println("You need a key to open this door.\n");
						}
				} catch (Exception e) {
					System.out.println(e + "\n");
				}
			}
			//If the player decides to go to the right we will use one of the if statements inside this one.
			//The code follows the same principles of the moving to the left code and so, the same documentation.
			//Here, instead of checking the eastWall of the cell we are moving to we check the westWall.
			else if (movement.equals("D") || movement.equals("d")) {
				for (int i = 0; i < maze.length; i++)
					if( maze[i][6].equals("player"))
						playerPosition = i;
				try {
					if (maze[playerPosition + 1][5].equals("wall"))
						System.out.println("You can't move in that direction.\n");
					else if (maze[playerPosition + 1][5].equals("no")) {
						if (maze[playerPosition + 1][6].equals("key"))
							nbOfKeys++;
						if (maze[playerPosition + 1][6].equals("hammer"))
							nbOfHammers++;
						if (maze[playerPosition + 1][6].equals("end") || maze[playerPosition + 1][6].equals("E"))
							gameEnded = true;
						maze[playerPosition + 1][6] = "player";
						maze[playerPosition][6] = "no";
					}
					else if (maze[playerPosition + 1][5].equals("fake")) {
						if (maze[playerPosition + 1][6].equals("key"))
							nbOfKeys++;
						if (maze[playerPosition + 1][6].equals("hammer"))
							nbOfHammers++;
						if (maze[playerPosition + 1][6].equals("end") || maze[playerPosition + 1][6].equals("E"))
							gameEnded = true;
						maze[playerPosition + 1][6] = "player";
						maze[playerPosition][6] = "no";
						maze[playerPosition + 1][5] = "no";
						maze[playerPosition][4] = "no";
					}
					else if (maze[playerPosition + 1][5].equals("breakable"))
						if(nbOfHammers > 0) {
							if (maze[playerPosition + 1][6].equals("key"))
								nbOfKeys++;
							if (maze[playerPosition + 1][6].equals("hammer"))
								nbOfHammers++;
							if (maze[playerPosition + 1][6].equals("end") || maze[playerPosition + 1][6].equals("E"))
								gameEnded = true;
							maze[playerPosition + 1][6] = "player";
							maze[playerPosition][6] = "no";
							maze[playerPosition + 1][5] = "no";
							maze[playerPosition][4] = "no";
							nbOfHammers--;
						} else {
							System.out.println("You need a hammer to break this wall.\n");
						}
					else if (maze[playerPosition + 1][5].equals("door"))
						if(nbOfKeys > 0) {
							if (maze[playerPosition + 1][6].equals("key"))
								nbOfKeys++;
							if (maze[playerPosition + 1][6].equals("hammer"))
								nbOfHammers++;
							if (maze[playerPosition + 1][6].equals("end") || maze[playerPosition + 1][6].equals("E"))
								gameEnded = true;
							maze[playerPosition + 1][6] = "player";
							maze[playerPosition][6] = "no";
							maze[playerPosition + 1][5] = "no";
							maze[playerPosition][4] = "no";
							nbOfKeys--;
						} else {
							System.out.println("You need a key to open this door.\n");
						}
				} catch (Exception e) {
					System.out.println(e + "\n");
				}
			} else {
				System.out.print("\nAdd a valid direction.\n\n");
			}
		}
		printMaze(maze);
		System.out.println("Congratulations, you have solved the maze in " + nbOfSteps + " steps!");
		return nbOfSteps;
	}

	//Method to play the maze and save the score to a file.
	public static void playMaze(String mazeName) throws IOException {
		String playerName = "";
		//Reader to obtain the player name to latter store it in the highscore file.
		try {
			BufferedReader name = new BufferedReader (new InputStreamReader(System.in));
			System.out.print("Please insert your name: ");
			playerName = name.readLine();
		} catch (Exception e) {
			System.out.println(e);
		}
		//Calling the navigateMaze method to play the maze and save the number of steps to a variable.
		int nbOfSteps = navigateMaze(mazeName);
		//If loop to check if the file already exists.
		if (new File("mazeHighScores.txt").exists()) {
			//Create the string to save all the information into the file.
			String scoreInfo = playerName + ", " + mazeName + ", " + nbOfSteps;
			List<String> scoreLine = Arrays.asList(scoreInfo);
			//Writing to the file the score.
			Files.write(Paths.get("mazeHighScores.txt"), scoreLine,StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		}
		else {
			//Create the strings to save all the information into the file.
			String header = "PLAYERNAME, MAZENAME, NUMBER_OF_STEPS_SOLVED\n";
			String scoreInfo = playerName + ",\t" + mazeName + ",\t" + nbOfSteps;
			List<String> scoreLine = Arrays.asList(header, scoreInfo);
			//Writing to the file the score.
			Files.write(Paths.get("mazeHighScores.txt"), scoreLine,StandardOpenOption.CREATE, StandardOpenOption.APPEND);	
		}	
	}

	//Main method where we can choose the maze to play by (un)commenting the mazeName variables.
	public static void main(String[] args) throws IOException {
		//String mazeName = "BaseMaze";
		//String mazeName = "MidiMaze";
		String mazeName = "ComplexMaze";
		playMaze(mazeName);
	}

}
