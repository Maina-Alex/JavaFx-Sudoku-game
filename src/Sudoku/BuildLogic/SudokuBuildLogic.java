package Sudoku.BuildLogic;

import Sudoku.Computationlogic.GameLogic;
import Sudoku.Persistence.LocalStorageImpl;
import Sudoku.ProblemDomain.Istorage;
import Sudoku.ProblemDomain.SudokuGame;
import Sudoku.userinterface.IUserInterfaceContract;
import Sudoku.userinterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {
    public static void build (IUserInterfaceContract.View userInterface) throws IOException{
        SudokuGame initialState;
        Istorage storage = new LocalStorageImpl();

        try{
            initialState= storage.getGameData();

        }catch (IOException e){
            initialState= GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }

        IUserInterfaceContract.EventListener uiLogic= new ControlLogic(storage,userInterface);

        userInterface.setListener(uiLogic);
        userInterface.updateBord(initialState);
    }
}
