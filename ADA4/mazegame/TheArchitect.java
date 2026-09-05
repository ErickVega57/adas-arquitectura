import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
//Your life is the sum of a remainder of an unbalanced equation inherent to the programming
//of the matrix

public class TheArchitect extends JFrame {
    //records the location of the exit so we can show it when its time
    int foundPlayer=0;
    String[][] updatedMatrix;
    int WallXCord;
    int WallYCord;
    int collected=0;
    boolean level;
    int globalTotalDimonds=0;


    public void setExit(int x, int y){
        WallXCord=x;
        WallYCord=y;  
    }

    //used when its time to show the exit.  
    public void showWall() {
        updatedMatrix[WallXCord][WallYCord]="E";  
    }

    public void playerMove(int xScale, int yScale, String[][] currentMatrix,int totalDimonds)throws StupidAssMove {

        int x=0;
        int y=0;
        int found=0;
        globalTotalDimonds=totalDimonds; //use this later for the gui dimond count
        nextLevel(false); //dont go to the next level yet.
        String[][] junkMatrix = currentMatrix;//we will be updating currentMatrix  
        
        //for loop will find were the player is now
        for (int i = 0; i < currentMatrix.length; i++) {
            for (int j = 0; j < currentMatrix[i].length; j++) {
                //we found the player
                if(currentMatrix[i][j].equals("P")) {
                    x=i;//record the players position
                    y=j;
                    found = 1;
                    break;
                }
           }
        }//end both for loops

        if(currentMatrix[x+xScale][y+yScale].equals("H"))//its a hidden dimond
        {
            currentMatrix[x][y]="N";
            currentMatrix[x+xScale][y+yScale]="P";
            currentMatrix[x][y]="N";
            collected+=1;//we got a hidden dimond! wow!
        } else if(currentMatrix[x+xScale][y+yScale].equals("D"))//its a dimond
        {
            currentMatrix[x][y]="N";
            currentMatrix[x+xScale][y+yScale]="P";
            collected+=1;//we got a dimond
        } else if(currentMatrix[x+xScale][y+yScale].equals("M") && currentMatrix[x+(xScale*2)][y+(yScale*2)].equals("N"))//move a moveable wall
        {
            currentMatrix[x][y]="N";
            currentMatrix[x+xScale][y+yScale]="P"; 
            currentMatrix[x+(xScale*2)][y+(yScale*2)]="M";
        } else if (currentMatrix[x+xScale][y+yScale].equals("N"))//normal move foward onto nothing
        {
            currentMatrix[x][y]="N";
            currentMatrix[x+xScale][y+yScale]="P"; 
        } else if (currentMatrix[x+xScale][y+yScale].equals("E"))//its an exit
        {
            currentMatrix[x][y]="N";
            currentMatrix[x+xScale][y+yScale]="P"; 
            nextLevel(true);//allow the next level to be loaded.
        } else{
            throw new StupidAssMove("Ass Hole hit wall!");
        }
            
        if(collected==totalDimonds)//if we have all the dimonds give the player the exit
        showWall();
           
        updatedMatrix=currentMatrix;  //we will return updatedMatrix for the gui                     
    }//end method

    //true we go to next level, false we update current level's gui 
    public void nextLevel(boolean tOrF) {
        level=tOrF;
    }
    
    //returs level true or false
    public boolean getLevel() {
        return level;
    }
        
    public int getDimondsLeft() {
        return globalTotalDimonds-collected;//for GUI JLabel, show how many dimonds are left to be collected
    }
    
    //returns the updated matrix for the gui to display
    public String[][] getUpdatedMatrix() {
        return updatedMatrix;    
    }
    
    private class StupidAssMove extends RuntimeException
    {
         public StupidAssMove(String event) {
             JFrame frame = new JFrame("Warning");
             JOptionPane.showMessageDialog(frame, "You Stupid Ass, Ran into something did you?");
         }
    }//end inner class
    

}//end class
