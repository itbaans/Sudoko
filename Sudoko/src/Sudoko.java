import java.util.Random;
import java.util.Scanner;

public class Sudoko {
    
    private Scanner userIn = new Scanner (System.in);
    private int[][] sudokodArr = new int[9][9];
    private int[] shuffle;
    private int[][] inputArr = new int[9][9];
    private int[][] missingBoxes = new int[9][9];
    private Random rand = new Random();
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_RESET = "\u001B[0m";


    private boolean rowCondition(int[][] sudokodArr, int rNo, int cNo, int n) {
        int x = 0;
        for (int i = 0; i < 9; i++) {
            if (sudokodArr[rNo][i]==n) {
                x++;
            }
        }
        if (x > 0) return false;
        else return true;
    }
    private boolean colCondition(int[][] sudokodArr, int rNo, int cNo, int n) {
        int x = 0;
        for (int i = 0; i < 9; i++) {
            if (sudokodArr[i][cNo]==n) {
                x++;
            }
        }
        if (x > 0) return false;
        else return true;
    }
    private boolean boxCondition(int[][] sudokodArr, int rNo, int cNo, int n) {
        boolean x = true;
        int r = rNo-(rNo%3), c = cNo-(cNo%3);
        for(int i = r; i < (r+3); i++) {
            for (int j = c; j < (c+3); j++) {
                if (sudokodArr[i][j]==n) x = false;
            }
        }
        return x;
    }

    private void displayBoard(int[][] arr) {

        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j++) {

                if(j==2 || j==5 || j==8) {
                    if(inputArr[i][j] == 1) System.out.print(ANSI_RED + arr[i][j]+ANSI_RESET+" | ");
                    else if (inputArr[i][j] != 1 && arr[i][j] != 0 ) System.out.print(ANSI_GREEN + arr[i][j]+ANSI_RESET+" | ");
                    else System.out.print(arr[i][j]+" | ");                    
                }
                
                else if(j==0) {
                    if(inputArr[i][j] == 1) System.out.print(" | "+ANSI_RED+arr[i][j]+ANSI_RESET+"  ");
                    else if (inputArr[i][j] != 1 && arr[i][j] != 0 ) System.out.print(" | "+ANSI_GREEN+arr[i][j]+ANSI_RESET+"  ");
                    else System.out.print(" | "+arr[i][j]+"  ");                    
                }
                else {
                    if(inputArr[i][j] == 1) System.out.print(ANSI_RED+arr[i][j]+ANSI_RESET+"  ");
                    else if (inputArr[i][j] != 1 && arr[i][j] != 0 ) System.out.print(ANSI_GREEN+arr[i][j]+ANSI_RESET+"  ");
                    else System.out.print(arr[i][j]+"  ");
                }
            }
            if (i==2 || i==5 ){
                
                System.out.println();
                System.out.print("  -----------------------------");
            }
            System.out.println();
        } 
    }

    private void emptyBoard() {

        while(!arrCheck(sudokodArr)) {

                displayBoard(sudokodArr);
                System.out.println("Press 1 to input");
                System.out.println("Press 2 to solve according to your inputs");
                System.out.println("Press 3 to exit");         
                char choice = userIn.next().charAt(0);
                

                if(choice=='1') {
                    String[] RCN = userInput();

                    if(RCN == null) {
                        System.out.println("invalid input");
                        break;
                    }
                    
                    if(Integer.parseInt(RCN[0]) < 1 || Integer.parseInt(RCN[0]) > 9 || Integer.parseInt(RCN[1]) < 1 || Integer.parseInt(RCN[1]) > 9 || Integer.parseInt(RCN[2]) < 1 || Integer.parseInt(RCN[2]) > 9 ) {
                        System.out.println("Invalid Input");
                    }
                else { 
                        int r = Integer.parseInt(RCN[0])-1;
                        int c = Integer.parseInt(RCN[1])-1;                        
                        inputArr[r][c] = 1;                             
                        int n = Integer.parseInt(RCN[2]);
                        if(!rowCondition(sudokodArr, r, c, n) || !colCondition(sudokodArr, r, c, n) || !boxCondition(sudokodArr, r, c, n)) System.out.println("Invalid Input");    
                        if (rowCondition(sudokodArr, r, c, n) && colCondition(sudokodArr, r, c, n) && boxCondition(sudokodArr, r, c, n) && n>=1 && n <=9) sudokodArr[r][c]=n;
                    }              
                }

                if(choice=='2') {
                    solve();
                    displayBoard(sudokodArr);
                    break;              
                }
                
                if(choice == '3') break;

        }
        
    }

    private void fillBoxes() {

        solve();        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                missingBoxes[i][j] = sudokodArr[i][j];
            }
        }

        for (int i = 0; i < 35; i++) {

            int r = (int)(Math.random()*9);
            int c = (int)(Math.random()*9);  
            missingBoxes[r][c] = 0;

        }
        

        while (!arrCheck(missingBoxes)) {

            displayBoard(missingBoxes);
            System.out.println("Press 1 to input");
            System.out.println("Press 2 to solve");
            System.out.println("Press 3 to exit");         
            char choice = userIn.next().charAt(0);

            if(choice=='1') {
                String[] RCN = userInput();

                if(RCN == null) {
                    System.out.println("invalid input");
                    break;
                }
                
                if(Integer.parseInt(RCN[0]) < 1 || Integer.parseInt(RCN[0]) > 9 || Integer.parseInt(RCN[1]) < 1 || Integer.parseInt(RCN[1]) > 9 || Integer.parseInt(RCN[2]) < 1 || Integer.parseInt(RCN[2]) > 9 ) {
                    System.out.println("Invalid Input");
                }
            else { 
                    int r = Integer.parseInt(RCN[0])-1;
                    int c = Integer.parseInt(RCN[1])-1;                        
                    inputArr[r][c] = 1;                             
                    int n = Integer.parseInt(RCN[2]);
                    if(!rowCondition(missingBoxes, r, c, n) || !colCondition(missingBoxes, r, c, n) || !boxCondition(missingBoxes, r, c, n)) System.out.println("Invalid Input");    
                    if (rowCondition(missingBoxes, r, c, n) && colCondition(missingBoxes, r, c, n) && boxCondition(missingBoxes, r, c, n) && n>=1 && n <=9) missingBoxes[r][c]=n;
                }              
            }

            if(choice=='2') {

                displayBoard(sudokodArr);
                break;

            }
            
            if(choice == '3') break;


        }



    }

    private String[] userInput() {

        System.out.println("Write your input like this: row,colum,number ");
        String rcn = userIn.next();
        if (!rcn.contains(",")) return null;
        String[] split = rcn.split(",");
        return split;

    }


    public void play() {

        displayMessage();
        char options = userIn.next().charAt(0);

        switch (options) {

            case '1':
                emptyBoard();
                break;
            case '2':
                fillBoxes();
                break;
            case '3': break;

        }



    }

    private void solve() {

        randomShuff();
        int n = 1; 
        while(!arrCheck(sudokodArr)) {
        cleanArr(sudokodArr, inputArr);          
            for (int i = 0; i < 9; i ++) {
                int r = shuffle[i];           
                int c = (int)(Math.random()*9);
                for (int j = 0; j < 9; j++) {
                    if(n >= 9)  n = 1;
                    while(sudokodArr[r][c]==0 && n >= 1 && n <=9 && j <=8 && j >= 0) {
                        if (rowCondition(sudokodArr, r, c, n) && colCondition(sudokodArr, r, c, n) && boxCondition(sudokodArr, r, c, n)) {
                            sudokodArr[r][c]=n;
                        }
                        else { 
                             n++;
                             j--;
                        }                    
                    }
                    c = (int)(Math.random()*9);      
                }
        
                n=1;                              
            }
        }
        
    }

    private boolean arrCheck(int[][] sudokodArr) {
        boolean x =true;
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j++) {
                if(sudokodArr[i][j]==0) x=false;
            }
        }
        return x;
    }

    private void cleanArr(int[][] sudokodArr, int[][] inputArr) {

        int r = (int)(Math.random()*9);
        int c = (int)(Math.random()*9);

        while(true) {

             if(inputArr[r][c]!=1)
              sudokodArr[r][c]=0;
             else {
              r = (int)(Math.random()*9);
              c = (int)(Math.random()*9);
             }
             if(inputArr[r][c]!=1) break;
        }   
    }

    private void randomShuff() {
        
        shuffle = new int[9];
		for (int i = 0; i < 9; i ++) {
			shuffle[i]=i;
            
		}
		
		for (int i = 0; i < shuffle.length; i++) {
			int i1 = (int)(Math.random()*shuffle.length);
			int temp = shuffle[i];
			shuffle[i] = shuffle[i1];
			shuffle[i1] = temp;
		}      
		
    }

    private void displayMessage() {

        System.out.println("=======================================================");
        System.out.println("||         WELCOME TO THE SUDOKU GAME                ||");
        System.out.println("||                                                   ||");
        System.out.println("||        Press 1 to play on a empty Board           ||");
        System.out.println("||        Press 2 to fill missing boxes              ||");
        System.out.println("||        Press 3 to exit                            ||");
        System.out.println("||                                                   ||");
        System.out.println("||                  Good luck!                       ||");
        System.out.println("=======================================================");

    }

}
