	package foxnRabbit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;

import com.sun.org.apache.bcel.internal.generic.SWAP;
import cell.Cell;
import digital.Digital;
import field.Field;
import field.Location;
import field.View;


class Point1{
	public int r,c,n;
	public Point1(){};
    
	public Point1(int _r, int _c){
        r=_r;
        c=_c;
        n=0;
    }
	
	public Point1(int _r, int _c,int _n){
        r=_r;
        c=_c;
        n=_n;
    }
	
    public Point1(Point1 p){
        r=p.r;
        c=p.c;
        n=p.n;
    }
    @Override
	public String toString() {
		return "Point1 [r=" + r + ", c=" + c + ", n=" + n + "]";
	}
};

public class SoluteShudu {
	
    private Field theField;
    private View theView;
   	private boolean rowmark[][] = new boolean[10][10];
   	private boolean colmark[][] = new boolean[10][10];
   	private boolean blockmark[][] = new boolean[10][10];
   	private int row[] = new int[10],col[] = new int[10],block[] = new int [10];
    ArrayList<Point1> tofill = new ArrayList<Point1>();
    
    private Digital questions[][] = new Digital[10][10];
    private int steptime = 140;
    
    public void setmark(int r, int c, int n, boolean flag)
    {
        rowmark[r][n]=flag;
        colmark[c][n]=flag;
        blockmark[getblocknum(r, c)][n]=flag;
    }

    int getblocknum(int r,int c)
    {
        return ((r-1)/3)*3+((c-1)/3);
    }

    boolean judge(int r,int c,int n)
    {
        return !rowmark[r][n]&&!colmark[c][n]&&!blockmark[getblocknum(r, c)][n];
    }

    public void printboard()
    {
        for(int r=1;r<10;r++)//from 1 to 9
        {
            for(int c=1;c<10;c++)//from 1 to 9
            {
                System.out.print(questions[r][c]);
                
            }
            System.out.println();
        }
    }
    
    public void Clear()
    {
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                rowmark[i][j]=false;
                colmark[i][j]=false;
                blockmark[i][j]=false;
                questions[i][j].i=0;
            }
            row[i]=0;
            col[i]=0;
            block[i]=0;
        }
        tofill.clear();
    }

    boolean dfs(int step)
    {
    	theView.repaint();
    	
    	try {
			Thread.sleep(steptime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        if(step==tofill.size())
        {
            return true;
        }
        int r=tofill.get(step).r,c=tofill.get(step).c;
        
        for(int i=1;i<10;i++)
        {
            if(judge(r, c, i))
            {
                questions[r][c].i=i;
                setmark(r, c, questions[r][c].i, true);

                if(dfs(step+1))return true;
                setmark(r, c, questions[r][c].i, false);
                questions[r][c].i=0;
            }
        }
        return false;
    }
    
    public SoluteShudu() {}
    
    public SoluteShudu(int size) {
    	initqusetion();
        theField = new Field(size, size);
        for ( int row = 0; row<theField.getHeight(); row++ ) {
            for ( int col = 0; col<theField.getWidth(); col++ ) {
                    theField.place(row, col, questions[row+1][col+1]);
            }
        }
        theView = new View(theField);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Êý¶ÀÄ£Äâ");
        frame.add(theView);
        frame.pack();
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }

    
    public void start() {
      if(dfs(0))
      {
          printboard();
      }
      Clear();
    }

    public void step() {
        for ( int row = 0; row< theField.getHeight(); row++ ) {
            for ( int col = 0; col < theField.getWidth(); col++ ) {
//                questions[row+1][col+1].i = (int)(Math.random()*10);
            }
        }
    }

	 private void ScanData() {
		 Scanner in = new Scanner(System.in);
		 
		 for(int r=1;r<10;r++) {//from 1 to 9
			String input = in.nextLine();
		    for(int c=1;c<10;c++) {//from 1 to 9
		        questions[r][c].i=input.charAt(c - 1) - '0';
		        
		        if(questions[r][c].i==0){
		            tofill.add(new Point1(r,c));
		        }
		        else{
		            setmark(r, c, questions[r][c].i, true);
		            questions[r][c].known = true;
		            row[r]++;
		            col[c]++;
		            block[getblocknum(r, c)]++;
		        }
		    }
		}
		
		for(int i=0;i<tofill.size();i++){
		    int r=tofill.get(i).r,c=tofill.get(i).c;
		    tofill.get(i).n=row[r]+col[c]+block[getblocknum(r, c)];
		}
		for(int i=0;i<tofill.size();i++){
		    int m=i;
		    for(int j=i;j<tofill.size()-1;j++){
		        if(tofill.get(m).n<tofill.get(j+1).n)
		            m=j+1;
		    }
		    
		    //½»»»
		    Point1 tmp = new Point1(tofill.get(i));
		    tofill.set(i, tofill.get(m));
		    tofill.set(m, tmp);
		}
		
	 }
	 
	private void initqusetion() {
		
		for ( int row = 0; row< questions.length; row++ ) {
	        for ( int col = 0; col < questions[row].length; col++ ) {
	            questions[row][col] = new Digital();
	        }
	    }
		ScanData();
	}
	    
    public static void main(String[] args) {
        SoluteShudu fnr = new SoluteShudu(9);
        fnr.start();
//ArrayList<Point1> p = new ArrayList<Point1>();
//p.add(new Point1(1,1,1));
//System.out.println(p);
//p.get(0).c = 100;
//System.out.println(p);
    }

}
	
