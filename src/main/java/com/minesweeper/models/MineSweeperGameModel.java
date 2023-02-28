package com.minesweeper.models;

import com.minesweeper.utils.FieldType;
import com.minesweeper.utils.GameStatus;
import com.minesweeper.utils.RandomUtil;
import com.minesweeper.utils.VisibilityType;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class MineSweeperGameModel {

    private final static int MAX_SIZE = 100;
    private final static int MIN_SIZE = 8;

    private final Field[][] main_board;
    private VisibilityType[][] visibility_mask;
    private boolean[][] test;

    private GameStatus game_status = GameStatus.NOT_STARTED;
    private int moves = 0;
    private final int x_size, y_size;


    public MineSweeperGameModel(int x_size, int y_size) throws Exception {
        if (!inputClean(x_size, y_size)) {
            throw new Exception("Insufficient board size");
        }

        this.x_size = x_size;
        this.y_size = y_size;
        main_board = new Field[x_size][y_size];
        initializeEmptyBoard();
    }

    public GameStatus clickField(int row, int col) {
        if (!fieldIsPressable(row, col)) {
            // throw new Exception("Field is not pressable");
            System.out.println("[GAME_MODEL]: Field is not pressable");
            return GameStatus.RUNNING;
        }

        switch (game_status) {
            case NOT_STARTED -> handleFirstClick(row, col);
            case RUNNING -> {
                Field pressed_field = main_board[row][col];
                // Check if we have clicked on a bomb
                if (pressed_field.getFieldType() == FieldType.BOMB) {
                    game_status = GameStatus.DEAD;
                    return game_status;
                }

                // else we can legally update the board


                moves++;
            }
            case WON -> {
                System.out.println("[GAME_MODEL]: Player has won");
            }
            case DEAD -> {
                System.out.println("[GAME_MODEL]: Player is dead");
            }
        }
        return GameStatus.RUNNING;
    }

    private boolean firstClick(){
        return moves == 0;
    }

    private boolean fieldIsPressable(int row, int col){
        return main_board[row][col].isPressable();
    }

    private void handleFirstClick(int row, int col) {
        // Generate a board with bombs on it (updated main_board)
        generateNewBoard();

        // Make sure we don't press on a bomb
        if (main_board[row][col].getFieldType() == FieldType.BOMB) {
            main_board[row][col].setFieldType(FieldType.EMPTY);
        }


        // All empty fields connected to the click are to be revealed
        test = calculateConnectedCells(row, col);

        // When there is a bomb around a field then show number of bombs in square
        calculateBombsInArea();

    }

    private void calculateBombsInArea(){
        int rows = x_size;
        int cols = y_size;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int count = 0;
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        int row = i + x;
                        int col = j + y;
                        if (row >= 0 && row < rows && col >= 0 && col < cols) {
                            if (main_board[row][col].getFieldType() == FieldType.BOMB) {
                                count++;
                            }
                        }
                    }
                }
                main_board[i][j].setNumberOfBombs(main_board[i][j].getFieldType() == FieldType.BOMB ? 0 : count);
            }
        }

    }

    private boolean[][] calculateConnectedCells(int row, int col){
        int numRows = x_size;
        int numCols = y_size;
        boolean[][] visited = new boolean[numRows][numCols];
        Queue<int[]> queue = new LinkedList<>();

        visited[row][col] = true;
        queue.add(new int[]{row, col});

        while (!queue.isEmpty()) {
            int[] currentCell = queue.poll();
            int currentRow = currentCell[0];
            int currentCol = currentCell[1];
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    int nextRow = currentRow + x;
                    int nextCol = currentCol + y;
                    if (nextRow >= 0 && nextRow < numRows && nextCol >= 0 && nextCol < numCols
                            && main_board[nextRow][nextCol].getFieldType() != FieldType.BOMB && !visited[nextRow][nextCol]) {
                        visited[nextRow][nextCol] = true;
                        queue.add(new int[]{nextRow, nextCol});
                    }
                }
            }
        }

        return visited;
    }

    private void updateBoard(int row, int col){
        //
    }

    private void generateNewBoard() {
        int max_mines = (x_size - 1) * (y_size - 1);
        int min_mines = 10;

        int mines_amount = RandomUtil.getRandomIntBetween(min_mines, max_mines);
        Point[] mine_points = RandomUtil.getRandomCoordinatesBetweenInterval(mines_amount, 0, x_size, 0, y_size);

        for (Point p : mine_points) {
            int x = (int) p.getX();
            int y = (int) p.getY();
            main_board[x][y].setFieldType(FieldType.BOMB);
        }

    }

    private void initializeEmptyBoard(){
        for(int row = 0;  row < x_size; row++) {
            for (int col = 0; col < y_size; col++) {
                main_board[row][col] = new Field(FieldType.EMPTY, new Point(row, col));
            }
        }
    }

    private boolean inputClean(int x, int y) {
        boolean x_check = x <= MAX_SIZE && x >= MIN_SIZE;
        boolean y_check = y <= MAX_SIZE && y >= MIN_SIZE;
        return x_check && y_check;
    }

    public Field[][] getBoard(){
        return this.main_board;
    }

    public boolean[][] getTest() {
        return this.test;
    }

}
