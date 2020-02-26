# Console Minesweeper  

[![Build Status](https://travis-ci.org/cshtdd/minesweeper.svg?branch=master)](https://travis-ci.org/cshtdd/minesweeper)  

Console implementation of a basic Minesweeper game with Java and TDD.  

[Minesweeper Spec](https://en.wikipedia.org/wiki/Minesweeper_(video_game))  

## Run the application  

```bash
java -cp target/minesweeper-1.0.jar Program
```

## Sample Output  

```
Select Task:
1- Expand a cell
2- Flag a mine
3- Remove flag
1
Row: [0-9]
8
Col: [0-9]
9
_ _ _ _ _ _ _ _ _ _
_ _ _ _ _ _ _ _ _ _
_ _ _ _ _ _ _ _ _ _
_ _ _ _ _ _ _ _ _ _
_ _ _ _ _ _ _ _ _ _
_ _ _ _ _ _ _ _ _ _
_ _ _ _ _ _ 1 _ _ _
_ _ _ _ _ _ 2 1 2 1
_ _ _ _ _ _ _ _ _ X
_ _ _ _ _ _ _ _ 2 1

Game Over
```

## Development  

Run the tests  

```bash
mvn clean test
```

Compile the project  

```bash
mvn clean package
```