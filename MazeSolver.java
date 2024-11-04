import java.io.*;

public class mazes {
    //STARTER CODE
    public static char[][] readMazeGrid(String input) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line;
        int rows = 0;

        while((line = reader.readLine()) != null) {
            rows++;
        }

        reader.close();
        reader = new BufferedReader(new FileReader(input));

        char [][] mazeGrid = new char[rows][];

        int row = 0;
        while((line = reader.readLine()) != null) {
            mazeGrid[row] = line.toCharArray();
            row++;
        }
        reader.close();

        return mazeGrid;
    }


    //function to check if the exit is reachable and return its coordinates
    public static int[] this_is_exit(char[][] maze, int row, int col, boolean[][] once) {
        //bound_checker if
        if (row < 0 || col < 0 || row >= maze.length || col >= maze[0].length) {
            return null;
        }//terminates if statement is true but continues if false


        // checks if the cell is a wall or already visited
        if (maze[row][col] == '#' || once[row][col]== true) {
            return null;
        }

        // if the current cell is the exit 'X', return its coordinates
        if (maze[row][col] == 'X') {
            int[] answer = new int[2]; //create an array to display the  coordinates
            answer[0] = row;
            answer[1] = col;
            return answer;
        }

        // "TRUE" the current cell when visited
        once[row][col] = true;
        int[] result;

        result = this_is_exit(maze, row, col + 1, once);
        if (result != null) {
            return result;
        }

        //up
        result = this_is_exit(maze, row - 1, col, once);
        if (result != null) {
            return result;
        }

        // going left
        result = this_is_exit(maze, row, col - 1, once);
        if (result != null) {
            return result;
        }

        //down
        result = this_is_exit(maze, row + 1, col, once);
        if (result != null) {
            return result;
        }

        // backtrack... unmark the current cell as visited if no path to the exit is found
        once[row][col] = false;
        return null;  // No path leads to the exit
    }

    public static void printMaze(char [][] mazeGrid) {
        for (int i = 0; i < mazeGrid.length; i++) {
            for (int j = 0; j < mazeGrid[i].length; j++) {
                System.out.print(mazeGrid[i][j] + " ");
            }
            System.out.println();
    //this function is added to see the implementation clearer in the terminal
        }
    }
    // main function
    public static void main(String[] args) {
        try {
            if (args.length < 3) {  //for all required arguments
                System.out.println("pls provide x coord, y coord, and maze file path as arguments");
                return;
            }

            int x_coord = Integer.parseInt(args[0]);
            int y_coord = Integer.parseInt(args[1]);
            String filePath = args[2];

            char[][] maze = readMazeGrid(filePath);

            /*below code for debugging purposes
            printMaze(maze);
            System.out.println(x_coord + " row");
            System.out.println(y_coord + " column");
             */

            //if starting coordinates are valid
            if (x_coord < 0 || x_coord >= maze.length || y_coord < 0 || y_coord >= maze[0].length) {
                System.out.println("There is no solution!");
                return;
            }

            //if starting position is a wall
            if (maze[x_coord][y_coord] == '#') {
                System.out.println("starting position can not be a wall, pick again");
                return;
            }

            boolean[][] passed = new boolean[maze.length][maze[0].length];
            int[] exitCoordinates = this_is_exit(maze, x_coord, y_coord, passed);

            if (exitCoordinates != null) {
                System.out.println("x:" + exitCoordinates[0] + ", y:" + exitCoordinates[1]);
            } else {
                System.out.println("There is no solution!");
            }

        } catch (IOException e) {
            System.out.println("Error= " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("There is no solution!");
        }
    }
}
