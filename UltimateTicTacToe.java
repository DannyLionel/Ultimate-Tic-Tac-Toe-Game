import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class UltimateTicTacToe extends JFrame implements ActionListener
{
	private JTextArea TextArea;
	private JButton[][] button = new JButton[9][9];
	public int [][] winner=new int [3][3];
	private int num = 1;
	String answer = "";
	private LayoutManager Flowlayout;
	
	
	
	public UltimateTicTacToe()
	{
		super();
		Container contentPane = getContentPane();
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setSize(1250, 1000);
		setTitle("Ultimate Tic Tac Toe");
		contentPane.setLayout(new BorderLayout());
		
		createTextArea(contentPane);
		createButtons(contentPane);
	}


	public void createButtons(Container contentPane)
	{
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(button.length,
				button[0].length));
		Font fontType = new Font("Simhei", Font.BOLD, 25);

		int count=1;
		for(int i=0; i<button.length; i++)
		{
			for(int j=0; j<button[0].length; j++)
			{
				button[i][j] = new JButton();
				button[i][j].setOpaque(true);
				button[i][j].setActionCommand(""+count);
				button[i][j].addActionListener(this);
				button[i][j].setForeground(Color.black);
				button[i][j].setFont(fontType);
				buttonPanel.add(button[i][j]);
				count++;
			}
		}

		for (int i=0;i<3;i++)
		{
			for (int j=0;j<3;j++)
			{
				button[i][j].setBackground(Color.green);
			}
		}

		for (int i=0;i<3;i++)
		{
			for (int j=6;j<9;j++)
			{
				button[i][j].setBackground(Color.green);
			}
		}

		for (int i=6;i<9;i++)
		{
			for (int j=6;j<9;j++)
			{
				button[i][j].setBackground(Color.green);
			}
		}

		for (int i=6;i<9;i++)
		{
			for (int j=0;j<3;j++)
			{
				button[i][j].setBackground(Color.green);
			}
		}

		for (int i=3;i<6;i++)
		{
			for (int j=3;j<6;j++)
			{
				button[i][j].setBackground(Color.green);
			}
		}
		contentPane.add(buttonPanel, BorderLayout.CENTER);
	}

	public void createTextArea(Container contentPane)
	{
		Font fontType = new Font("SimHei", Font.BOLD, 50);

		TextArea= new JTextArea();
		TextArea.setLayout(Flowlayout);
		String text = ("Let's Play");
		TextArea = new JTextArea(text);
		TextArea.setBackground(Color.white);
		TextArea.setForeground(Color.black);
		TextArea.setFont(fontType);
		contentPane.add(TextArea, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent event)
	{
		String letter="";

		if(num % 2 == 0)
		{
			letter = "0";
		}
		else
		{
			letter = "X";
		}	

		for(int i=0; i<button.length; i++)
		{

			for(int j=0; j<button[0].length; j++)
			{	

				if(event.getActionCommand().equalsIgnoreCase
						(button[i][j].getActionCommand()))
				{	
					if (button[i][j].getText().equals(""))
					{
						button[i][j].setText(letter);
						
						if (num % 2 == 0) {
							button[i][j].setForeground(Color.RED);
						} else {
							button[i][j].setForeground(Color.BLUE);
						}
						
						/*Get a person's turn*/
						if (button[i][j].getText().equals("X")) 
						{
							TextArea.setText("Your turn O !");
						} 
						else
						{
							TextArea.setText("Your turn X !");
						}

						/*See if there is a winner.*/
						winner [0][0] = buttonOp(0, 0, letter);
						winner [0][1] = buttonOp(0, 3, letter);
						winner [0][2] = buttonOp(0, 6, letter);

						winner [1][0] = buttonOp(3, 0, letter);
						winner [1][1] = buttonOp(3, 3, letter);
						winner [1][2] = buttonOp(3, 6, letter);

						winner [2][0] = buttonOp(6, 0, letter);
						winner [2][1] = buttonOp(6, 3, letter);
						winner [2][2] = buttonOp(6, 6, letter);

						for (int p=0;p<3;p++)
						{
							if (winner[0][p]==1 && 
									winner[1][p]==1 && 
									winner[2][p]==1||
									winner[p][0]==1 && 
									winner[p][1]==1 && 
									winner[p][2]==1||
									winner[0][0]==1 && 
									winner[1][1]==1 && 
									winner[2][2]==1||
									winner[0][2]==1 && 
									winner[1][1]==1 && 
									winner[2][0]==1)
							{
								TextArea.setText("The Winner is " +
										"X! Good Job!");
								for(int r=0; r<button.length; r++)
								{
									for(int s=0; s<button[0].length; 
											s++)
									{
										button[r][s].setText("X");
									}
								}
							}

							else if (winner[0][p]==2 && 
									winner[1][p]==2 && 
									winner[2][p]==2||
									winner[p][0]==2 && 
									winner[p][1]==2 &&
									winner[p][2]==2||
									winner[0][0]==2 && 
									winner[1][1]==2 && 
									winner[2][2]==2||
									winner[0][2]==2 && 
									winner[1][1]==2 && 
									winner[2][0]==2)
							{
								TextArea.setText("The Winner is " +
										"O! Good Job!");
								for(int r=0; r<button.length; r++)
								{
									for(int s=0; s<button[0].length;
											s++)
									{
										button[r][s].setText("O");
									}
								}
							}
						}
						num++;

					} 
					else{
						TextArea.setText(" Player" + letter +
								" YOU CANNOT SELECT THIS ONE");					
					}
				}
			}
		}
	}

	public int buttonOp(int row, int col,String letter)
	{
		int l = 0;
		int m = 0;
		int value = 0;

		/*If a player wins for a small sized tic tac toe set
		 * set every block in small sized tic tac toe to that
		 * players value and color.*/
		for (int i=row;i<row+3;i++)
		{
			for (int j=col;j<col+3;j++)
			{
				if(button[row][j].getText().equals(letter)&&
						button[row+1][j].getText().equals
						(letter)&&
						button[row+2][j].getText().equals
						(letter)||
						button[i][col].getText().equals
						(letter)&&
						button[i][col+1].getText().equals
						(letter)&&
						button[i][col+2].getText().equals
						(letter)||
						button[row][col].getText().equals
						(letter)&&
						button[row+1][col+1].getText().equals
						(letter)&&
						button[row+2][col+2].getText().equals
						(letter)||
						button[row+2][col].getText().equals
						(letter)&&
						button[row+1][col+1].getText().equals
						(letter)&&
						button[row][col+2].getText().equals
						(letter))
				{
					for (l=row;l<row+3;l++)
					{
						for (m=col;m<col+3;m++)
						{

							button[l][m].setText(letter);	

						}
					}
					
					if (button[row][col].getText().equals("X"))
					{
						value = 1;
						button[i][j].setForeground(Color.BLUE);
					}
					else if (button[row][col].getText().equals("0"))
					{
						value = 2;
						button[i][j].setForeground(Color.RED);
					}
				}
			}
		}
		return value;
	}

	public static void main(String[] args)
	{
		UltimateTicTacToe  pictureGui = new UltimateTicTacToe();
		pictureGui.setVisible(true);
	}
}