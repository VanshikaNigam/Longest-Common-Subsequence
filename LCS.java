import java.io.*;
import java.util.Scanner;

public class LCS {

String Line_1[]; // Array to store the first line of the text file
String Line_2[]; // Array to store the second line of the text file
int direction[][]=new int[1000][1000]; // A 2-D array which will help in traversing the longest common subsequence
int Sequence[][]=new int[1000][1000]; // Storing the distance for each character of the 2 strings 
private String input="",output="";

	public static void main(String[] args) throws IOException
	{
		
		LCS lcs = new LCS();
		lcs.input = "input.txt"; // input file
		lcs.output = "output.txt"; // output file
		
		Scanner sc = new Scanner(new FileReader(lcs.input));
		FileWriter fw=new FileWriter(lcs.output);
		PrintWriter pw=new PrintWriter(fw);
		String read;
		read=sc.nextLine();
		String temp_1=read;
		read=sc.nextLine();
		String temp_2=read;
		
		for (int i=0; i<temp_1.length();i++)
		{
			lcs.Line_1=temp_1.split("");
		}
		for (int i=0; i<temp_2.length();i++)
		{
			lcs.Line_2=temp_2.split("");
		}
		 
		int row=lcs.Line_1.length;
		int column=lcs.Line_2.length;
		lcs.Sequence=new int [row+1][column+1]; // defining the array size
		lcs.Finding_Sequence(lcs.Line_1, lcs.Line_2,pw);
		
		pw.close();
	}
	
	public void Finding_Sequence(String Input1[],String Input2[],PrintWriter pw)
	{
		int length_1= Input1.length;
		int length_2= Input2.length;
		int j,i, a,b;
		
		// Initializing the first row and column to 0 
		for(j=0;j<=length_2;j++)
			{
				Sequence[0][j]=0;
			}
		for(i=0;i<=length_1;i++)
			{
				Sequence[i][0]=0;
			}
		
		for(int l=1;l<=length_1;l++)
		{
				for(int k=1;k<=length_2;k++)
				{
					if(Input1[l-1].compareTo(Input2[k-1])==0) // the character in the 2 strings are equal , the value on that position in the sequence array gets increased by 1
						{
							Sequence[l][k]=Sequence[l-1][k-1]+1;
							direction[l][k]=0; // assigning  the value to 0 which corresponds to moving diagonally							
						}
					
				//  comparing the value at the particular positions in the Sequence array and accordingly assigning value to that spot
					else if(Sequence[l][k-1]>Sequence[l-1][k]) 
					{
						Sequence[l][k]=Sequence[l][k-1];
						direction[l][k]=1;  // assigning the value to 1 which corresponds to moving left
					}
					else
					{
						Sequence[l][k]=Sequence[l-1][k];
						direction[l][k]=2;  // assigning the value to 1 which corresponds to moving up
					}
									
				}
			}
		
		pw.println(Sequence[length_1][length_2]); //writing the distance of the sequence to the file
		
		//printing the longest common subsequence by traversing the direction array
		a=length_1;
		b=length_2;
		String S="";
		while (a>0 && b>0)
		{
			if(direction[a][b]==0) // diagonal
			{
				S=S.concat(Input1[a-1]);
				a--;
				b--;
		
				
			}
			else if (direction[a][b]==2) // up
				a--;
			else // left
				b--;
		}

		pw.print(S); // writing the longest common subsequence to the file.
		
}
}