package _coursework.Question3.B;
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
        
    }
}
