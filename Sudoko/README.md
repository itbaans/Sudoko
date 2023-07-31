# Sudoku Console Game

![Sudoku Logo](sudoku_logo.png)

## Overview

This project is a console-based Sudoku game implemented as part of my first semester project. The game allows users to play Sudoku on an empty board or a semi-filled board by providing inputs through the console. Additionally, I went beyond the requirements and implemented a rudimentary algorithm that attempts to solve the Sudoku board, filling in the remaining empty cells randomly until a valid solution is found.

## Features

- Play Sudoku on a console-based interface.
- Choose to start with an empty board or a partially filled board.
- Get feedback on the correctness of each move.
- Simple and intuitive interface for input and interaction.
- Basic solving algorithm that fills the board with random numbers.

## How to Play

1. Clone the repository to your local machine.
2. Open a console or terminal window in the project directory.
3. Compile the program (if needed) using the necessary build commands.
4. Run the executable to start the Sudoku game.
5. You will be presented with a 9x9 grid representing the Sudoku board.
6. If you want to play on an empty board, start entering your moves row-wise by specifying the row and column number along with the value (e.g., `1 5 7` for row 1, column 5, and the value 7).
7. If you want to play on a semi-filled board, the program will prompt you to enter the provided values first, then you can continue with the same input format as mentioned above.
8. After each move, the program will verify if the move is valid and provide feedback accordingly.
9. Continue making moves until the board is filled or until you decide to exit the game.
10. If you wish to solve the board, choose the appropriate option from the menu, and the program will attempt to fill the remaining cells using the inefficient solving algorithm.

## Limitations

- The solving algorithm used in this project is inefficient and may not always find a solution in a reasonable amount of time for complex puzzles.
- The program doesn't include an option to generate new Sudoku puzzles or load puzzles from external files.
- The console-based interface might not be as user-friendly or visually appealing as a graphical user interface.

## Note

While the solving algorithm may not be the most efficient, I am proud of what I achieved as a beginner in programming. Despite discovering better solutions later, I chose to keep my original implementation to cherish my learning journey and the satisfaction of solving Sudoku using my own code.

