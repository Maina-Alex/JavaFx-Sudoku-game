package Sudoku.Computationlogic;

import Sudoku.ProblemDomain.Constants.GameState;
import Sudoku.ProblemDomain.Constants.Rows;
import Sudoku.ProblemDomain.SudokuGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Sudoku.ProblemDomain.SudokuGame.GRID_BOUNDARY;

public class GameLogic {
    public static SudokuGame getNewGame(){
        return new SudokuGame(
                GameState.NEW,
                GameGenerator.getNewGameGrid()
        );

    }
    
    public static  GameState checkForCompletion(int [][] grid){
        if(sudokuIsInvalid(grid))return GameState.ACTIVE;
        if(tilesAreNotFilled(grid)) return GameState.ACTIVE;
        return GameState.COMPLETE;
        
    }

    public static boolean sudokuIsInvalid(int[][] grid) {
        if(rowsAreInvalid(grid)) return true;
        if(ColumnsAreInvalid(grid)) return true;
        if(SquaresAreInvalid(grid)) return true;
        else return false;

    }
    private static boolean rowsAreInvalid(int[][] grid) {
        for (int yIndex=0; yIndex<GRID_BOUNDARY; yIndex++){
            List<Integer> row= new ArrayList<>();
            for(int xIndex=0; xIndex<GRID_BOUNDARY; xIndex++){
                row.add(grid[xIndex][yIndex]);
            }

            if(collectionHasRepeats(row))return true;
        }
        return false;
    }

    private static boolean ColumnsAreInvalid(int[][] grid) {
        for (int xIndex=0; xIndex<GRID_BOUNDARY; xIndex++){
            List<Integer> row= new ArrayList<>();
            for(int yIndex=0; yIndex<GRID_BOUNDARY; yIndex++){
                row.add(grid[xIndex][yIndex]);
            }

            if(collectionHasRepeats(row))return true;
        }
        return false;
    }

    private static boolean SquaresAreInvalid(int[][] grid) {
        if(rowOfSquaresIsInvalid(Rows.TOP,grid)) return true;
        if(rowOfSquaresIsInvalid(Rows.MIDDLE,grid)) return true;
        if(rowOfSquaresIsInvalid(Rows.BOTTOM,grid)) return true;
        return false;
    }

    private static boolean rowOfSquaresIsInvalid(Rows value, int[][] grid) {
        switch(value){
            case TOP:
                if(squareIdInvalid(0,0,grid)) return true;
                if(squareIdInvalid(0,3,grid)) return true;
                if(squareIdInvalid(0,6,grid)) return true;
                return  false;
            case MIDDLE:
                if(squareIdInvalid(3,0,grid)) return true;
                if(squareIdInvalid(3,3,grid)) return true;
                if(squareIdInvalid(3,6,grid)) return true;
                return  false;

            case BOTTOM:
                if(squareIdInvalid(6,0,grid)) return true;
                if(squareIdInvalid(6,3,grid)) return true;
                if(squareIdInvalid(6,6,grid)) return true;
                return  false;

            default:
                return false;

        }
    }

    private static boolean squareIdInvalid(int xIndex, int yIndex, int[][] grid) {
        int yIndexEnd =yIndex=yIndex+3;
        int xIndexEnd=xIndex+3;

        List<Integer> square= new ArrayList<>();
        while(yIndex<yIndexEnd){
            while(xIndex<xIndexEnd){
                square.add(
                        grid[xIndex][yIndex]
                );
                xIndex++;
            }
            xIndex-=3;
            yIndex++;
        }
        if (collectionHasRepeats(square)) return  true;
        return false;
    }

    public static boolean collectionHasRepeats(List<Integer> collection) {
        for(int index=1; index<=GRID_BOUNDARY;index++){
            if(Collections.frequency(collection,index)>1) return true;
        }
        return  false;

    }

    public static boolean tilesAreNotFilled(int[][] grid) {

        for (int xIndex=0;xIndex<GRID_BOUNDARY;xIndex++){
            for (int yIndex=0;yIndex<GRID_BOUNDARY;yIndex++){
                if(grid[xIndex][yIndex]==0) return true;
            }

        }
        return  false;
    }
}
