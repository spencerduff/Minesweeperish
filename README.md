# Minesweeperish
Minesweeper implemented in Java GUI-interface

The game of Minesweeper in a Java GUI-interface. Right click to swap cells from marked as bomb, to unsure, back to blank.

Hardest part of this project was figuring out how to make the cells percolate away when clicking a square with no bombs next to it.
Solved by the cells being in the same class as the Cell holder, which is a double array of Cells. The Cell that gets clicked calls
the click function on all the cells around it, and it will recursively call that until the Cell has a bomb next to it.
