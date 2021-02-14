package Sudoku.userinterface;

import Sudoku.ProblemDomain.SudokuGame;

public interface IUserInterfaceContract {
     interface EventListener{
        void onSudokuInput(int x,int y, int input);
        void onDialogClick();
    }

    interface View{
        void setListener(IUserInterfaceContract.EventListener listener);
        void updateSquare(int x,int y, int input);
        void showDialog(String message);
        void updateBord(SudokuGame game);
        void showError(String message);

    }
}
