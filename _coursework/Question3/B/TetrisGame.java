package _coursework.Question3.B;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
/*
 * Question 3 (B)
A Game of Tetris

Functionality:
Queue: Use a queue to store the sequence of falling blocks.
Stack: Use a stack to represent the current state of the game board.

GUI:
A game board with grid cells.
A preview area to show the next block.
Buttons for left, right, and rotate.
Implementation:
Initialization:
 Create an empty queue to store the sequence of falling blocks.
 Create an empty stack to represent the game board.
 Initialize the game board with empty cells.
 Generate a random block and enqueue it.
Game Loop:
While the game is not over:
 Check for game over: If the top row of the game board is filled, the game is over.
 Display the game state: Draw the current state of the game board and the next block in the
preview area.
Handle user input:
 If the left or right button is clicked, move the current block horizontally if possible.
 If the rotate button is clicked, rotate the current block if possible.
 Move the block: If the current block can move down without colliding, move it down. Otherwise:
 Push the current block onto the stack, representing its placement on the game board.
 Check for completed rows: If a row is filled, pop it from the stack and add a new empty row at the
top.
 Generate a new random block and enqueue it.
Game Over:
 Display a game over message and the final score.

Data Structures:
Block: A class or struct to represent a Tetris block, including its shape, color, and current position.
GameBoard: A 2D array or matrix to represent the game board, where each cell can be empty or filled
with a block.
Queue: A queue to store the sequence of falling blocks.
Stack: A stack to represent the current state of the game board.

Additional Considerations:
Collision detection: Implement a function to check if a block can move or rotate without colliding with
other blocks or the game board boundaries.
Scoring: Implement a scoring system based on factors like completed rows, number of blocks placed, and
other game-specific rules.
Leveling: Increase the speed of the falling blocks as the player's score increases.
Power-ups: Add power-ups like clearing lines, adding extra rows, or changing the shape of the current
block.
 */


public class TetrisGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TetrisGameFrame frame = new TetrisGameFrame();
            frame.setVisible(true);
        });
    }
}

// Represents a Tetris block with a shape (2D array), a color, and a current
// board position.
class Block {
    int[][] shape;
    int x, y; // position on board (top-left of the block’s bounding box)
    Color color;

    public Block(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
        x = 0;
        y = 0;
    }

    // Rotates the shape 90° clockwise.
    public void rotate() {
        int rows = shape.length;
        int cols = shape[0].length;
        int[][] rotated = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotated[j][rows - 1 - i] = shape[i][j];
            }
        }
        shape = rotated;
    }
}

// Represents the Tetris board using a stack of rows (each row is an int[]).
// A value of 0 indicates an empty cell; a value of 1 indicates a filled cell.
class GameBoard {
    final int ROWS = 20;
    final int COLS = 10;
    Stack<int[]> boardStack;

    public GameBoard() {
        boardStack = new Stack<>();
        // Initialize board: index 0 is the top row.
        for (int i = 0; i < ROWS; i++) {
            boardStack.add(new int[COLS]); // all cells start empty (0)
        }
    }

    // Returns the value at a specific cell.
    public int getCell(int row, int col) {
        return boardStack.get(row)[col];
    }

    // Sets the cell to the given value.
    public void setCell(int row, int col, int value) {
        boardStack.get(row)[col] = value;
    }

    // “Locks” a block into the board by marking its filled cells.
    public void addBlock(Block block) {
        int[][] shape = block.shape;
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] != 0) {
                    int boardRow = block.y + i;
                    int boardCol = block.x + j;
                    if (boardRow >= 0 && boardRow < ROWS && boardCol >= 0 && boardCol < COLS) {
                        setCell(boardRow, boardCol, 1);
                    }
                }
            }
        }
    }

    // Checks each row for completion. If a row is full, it is removed from the
    // stack and
    // a new empty row is inserted at the top.
    public int clearCompleteRows() {
        int linesCleared = 0;
        for (int row = 0; row < ROWS; row++) {
            boolean full = true;
            for (int col = 0; col < COLS; col++) {
                if (getCell(row, col) == 0) {
                    full = false;
                    break;
                }
            }
            if (full) {
                boardStack.remove(row);
                boardStack.add(0, new int[COLS]);
                linesCleared++;
            }
        }
        return linesCleared;
    }

    // Checks if the block can be placed at (newX, newY) with the given shape.
    public boolean isValidPosition(Block block, int newX, int newY, int[][] shape) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] != 0) {
                    int boardX = newX + j;
                    int boardY = newY + i;
                    if (boardX < 0 || boardX >= COLS || boardY < 0 || boardY >= ROWS) {
                        return false;
                    }
                    if (getCell(boardY, boardX) != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // The game is over if any cell in the top row is filled.
    public boolean isGameOver() {
        for (int col = 0; col < COLS; col++) {
            if (getCell(0, col) != 0) {
                return true;
            }
        }
        return false;
    }
}

// The main frame that holds the game board, preview area, and control buttons.
class TetrisGameFrame extends JFrame {
    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 20;
    private final int CELL_SIZE = 30;

    private GameBoard gameBoard;
    private Queue<Block> blockQueue;
    private Block currentBlock;
    private Block nextBlock;
    private GamePanel gamePanel;
    private PreviewPanel previewPanel;
    private Timer timer;
    private int score = 0;

    public TetrisGameFrame() {
        setTitle("Tetris Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Fixed window size; users cannot resize the window.
        setSize(400, 700);
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initGame();

        // Create game board and preview panels.
        gamePanel = new GamePanel(gameBoard, currentBlock, CELL_SIZE);
        previewPanel = new PreviewPanel(nextBlock, CELL_SIZE);

        add(gamePanel, BorderLayout.CENTER);
        add(previewPanel, BorderLayout.EAST);

        // Create a control panel with Left, Right, Rotate, Down, and Restart buttons.
        JPanel controlPanel = new JPanel();
        JButton leftButton = new JButton("Left");
        JButton rightButton = new JButton("Right");
        JButton rotateButton = new JButton("Rotate");
        JButton downButton = new JButton("Down");
        JButton restartButton = new JButton("Restart");

        controlPanel.add(leftButton);
        controlPanel.add(rightButton);
        controlPanel.add(rotateButton);
        controlPanel.add(downButton);
        controlPanel.add(restartButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Button listeners.
        leftButton.addActionListener(e -> moveCurrentBlock(-1, 0));
        rightButton.addActionListener(e -> moveCurrentBlock(1, 0));
        rotateButton.addActionListener(e -> rotateCurrentBlock());
        downButton.addActionListener(e -> dropCurrentBlock());
        restartButton.addActionListener(e -> restartGame());

        // Timer for the game loop.
        timer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameLoop();
            }
        });
        timer.start();
    }

    // Initializes the game variables.
    private void initGame() {
        gameBoard = new GameBoard();
        blockQueue = new LinkedList<>();
        // Pre-generate two blocks.
        blockQueue.add(generateRandomBlock());
        blockQueue.add(generateRandomBlock());
        currentBlock = blockQueue.poll();
        nextBlock = blockQueue.peek();
        score = 0;
    }

    // The main game loop: move the current block down or lock it into the board.
    private void gameLoop() {
        if (gameBoard.isValidPosition(currentBlock, currentBlock.x, currentBlock.y + 1, currentBlock.shape)) {
            currentBlock.y += 1;
        } else {
            if (!lockAndNext()) {
                return; // Game over, exit loop.
            }
        }
        updatePanels();
    }

    // Moves the current block by dx horizontally and dy vertically if possible.
    private void moveCurrentBlock(int dx, int dy) {
        int newX = currentBlock.x + dx;
        int newY = currentBlock.y + dy;
        if (gameBoard.isValidPosition(currentBlock, newX, newY, currentBlock.shape)) {
            currentBlock.x = newX;
            currentBlock.y = newY;
            updatePanels();
        }
    }

    // Rotates the current block. If rotation results in an invalid position, it is
    // reversed.
    private void rotateCurrentBlock() {
        int[][] originalShape = currentBlock.shape;
        currentBlock.rotate();
        if (!gameBoard.isValidPosition(currentBlock, currentBlock.x, currentBlock.y, currentBlock.shape)) {
            // Reverse rotation by rotating three more times.
            for (int i = 0; i < 3; i++) {
                currentBlock.rotate();
            }
        }
        updatePanels();
    }

    // Immediately drops the current block to its lowest possible valid position.
    private void dropCurrentBlock() {
        while (gameBoard.isValidPosition(currentBlock, currentBlock.x, currentBlock.y + 1, currentBlock.shape)) {
            currentBlock.y++;
        }
        lockAndNext();
        updatePanels();
    }

    // Locks the current block into the board, clears rows, updates score, and
    // fetches the next block.
    // Returns false if the game is over.
    private boolean lockAndNext() {
        gameBoard.addBlock(currentBlock);
        int lines = gameBoard.clearCompleteRows();
        score += lines * 100;

        if (gameBoard.isGameOver()) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Game Over! Score: " + score);
            return false;
        }

        currentBlock = blockQueue.poll();
        if (currentBlock == null) {
            currentBlock = generateRandomBlock();
        }
        blockQueue.add(generateRandomBlock());
        nextBlock = blockQueue.peek();
        return true;
    }

    // Updates the game and preview panels.
    private void updatePanels() {
        gamePanel.setCurrentBlock(currentBlock);
        gamePanel.repaint();
        previewPanel.setBlock(nextBlock);
        previewPanel.repaint();
    }

    // Restarts the game.
    private void restartGame() {
        timer.stop();
        initGame();
        // Update game panel with new board.
        gamePanel.setGameBoard(gameBoard);
        updatePanels();
        timer.start();
    }

    // Generates a random Tetris block.
    private Block generateRandomBlock() {
        Random rand = new Random();
        int r = rand.nextInt(7);
        int[][] shape;
        Color color;
        switch (r) {
            case 0: // I shape
                shape = new int[][] {
                        { 1, 1, 1, 1 }
                };
                color = Color.CYAN;
                break;
            case 1: // J shape
                shape = new int[][] {
                        { 1, 0, 0 },
                        { 1, 1, 1 }
                };
                color = Color.BLUE;
                break;
            case 2: // L shape
                shape = new int[][] {
                        { 0, 0, 1 },
                        { 1, 1, 1 }
                };
                color = Color.ORANGE;
                break;
            case 3: // O shape
                shape = new int[][] {
                        { 1, 1 },
                        { 1, 1 }
                };
                color = Color.YELLOW;
                break;
            case 4: // S shape
                shape = new int[][] {
                        { 0, 1, 1 },
                        { 1, 1, 0 }
                };
                color = Color.GREEN;
                break;
            case 5: // T shape
                shape = new int[][] {
                        { 0, 1, 0 },
                        { 1, 1, 1 }
                };
                color = Color.MAGENTA;
                break;
            case 6: // Z shape
            default:
                shape = new int[][] {
                        { 1, 1, 0 },
                        { 0, 1, 1 }
                };
                color = Color.RED;
                break;
        }
        Block block = new Block(shape, color);
        // Set initial position: horizontally centered, top row.
        block.x = BOARD_WIDTH / 2 - block.shape[0].length / 2;
        block.y = 0;
        return block;
    }
}

// The panel that draws the game board (grid, placed blocks, and the falling
// block).
class GamePanel extends JPanel {
    private GameBoard board;
    private Block currentBlock;
    private int cellSize;

    public GamePanel(GameBoard board, Block currentBlock, int cellSize) {
        this.board = board;
        this.currentBlock = currentBlock;
        this.cellSize = cellSize;
        int width = board.boardStack.get(0).length * cellSize;
        int height = board.boardStack.size() * cellSize;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
    }

    public void setGameBoard(GameBoard board) {
        this.board = board;
    }

    public void setCurrentBlock(Block block) {
        this.currentBlock = block;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the board grid and locked blocks.
        for (int row = 0; row < board.boardStack.size(); row++) {
            for (int col = 0; col < board.boardStack.get(row).length; col++) {
                if (board.getCell(row, col) != 0) {
                    g.setColor(Color.GRAY);
                    g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
                } else {
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);
                }
            }
        }
        // Draw the current falling block.
        if (currentBlock != null) {
            g.setColor(currentBlock.color);
            for (int i = 0; i < currentBlock.shape.length; i++) {
                for (int j = 0; j < currentBlock.shape[0].length; j++) {
                    if (currentBlock.shape[i][j] != 0) {
                        int drawX = (currentBlock.x + j) * cellSize;
                        int drawY = (currentBlock.y + i) * cellSize;
                        g.fillRect(drawX, drawY, cellSize, cellSize);
                        g.setColor(Color.BLACK);
                        g.drawRect(drawX, drawY, cellSize, cellSize);
                        g.setColor(currentBlock.color);
                    }
                }
            }
        }
    }
}

// The preview panel that shows the next block.
class PreviewPanel extends JPanel {
    private Block block;
    private int cellSize;

    public PreviewPanel(Block block, int cellSize) {
        this.block = block;
        this.cellSize = cellSize;
        setPreferredSize(new Dimension(6 * cellSize, 6 * cellSize));
        setBackground(Color.LIGHT_GRAY);
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (block != null) {
            g.setColor(block.color);
            for (int i = 0; i < block.shape.length; i++) {
                for (int j = 0; j < block.shape[0].length; j++) {
                    if (block.shape[i][j] != 0) {
                        int drawX = j * cellSize + cellSize;
                        int drawY = i * cellSize + cellSize;
                        g.fillRect(drawX, drawY, cellSize, cellSize);
                        g.setColor(Color.BLACK);
                        g.drawRect(drawX, drawY, cellSize, cellSize);
                        g.setColor(block.color);
                    }
                }
            }
        }
    }
}
