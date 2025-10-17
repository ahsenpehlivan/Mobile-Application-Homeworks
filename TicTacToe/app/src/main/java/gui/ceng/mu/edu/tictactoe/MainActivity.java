package gui.ceng.mu.edu.tictactoe;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    static final String PLAYER_1 = "X";
    static final String PLAYER_2 = "O";
    boolean player1Turn = true;
    byte[][] board = new byte[3][3];

    private Button[][] buttons = new Button[3][3];

    private boolean gameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        final TableLayout boardLayout = findViewById(R.id.board);

        ViewCompat.setOnApplyWindowInsetsListener(boardLayout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        for (int i = 0; i < 3; i++) {
            TableRow row = (TableRow) boardLayout.getChildAt(i);
            for (int j = 0; j < 3; j++) {
                Button btn = (Button) row.getChildAt(j);
                btn.setOnClickListener(new CellListener(i, j));
                buttons[i][j] = btn;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_restart) {
            resetGame();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void resetGame() {
        board = new byte[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }


        player1Turn = true;
        gameActive = true;

        Toast.makeText(this, "Oyun Yeniden Başlatıldı!", Toast.LENGTH_SHORT).show();
    }

    public boolean isValidMove(int row, int col) {
        return gameActive && board[row][col] == 0;
    }


    public int gameEnded(int row, int col) {
        byte player = board[row][col];
        if (board[0][col] == player && board[1][col] == player && board[2][col] == player) return player;
        if (board[row][0] == player && board[row][1] == player && board[row][2] == player) return player;
        if (row == col && board[0][0] == player && board[1][1] == player && board[2][2] == player) return player;
        if (row + col == 2 && board[0][2] == player && board[1][1] == player && board[2][0] == player) return player;

        boolean isDraw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    isDraw = false;
                    break;
                }
            }
            if (!isDraw) break;
        }
        if (isDraw) return 0;
        return -1;
    }


    class CellListener implements View.OnClickListener {
        int row, col;
        public CellListener(int row, int col){
            this.row = row;
            this.col = col;
        }

        @Override
        public void onClick(View v) {
            if (!gameActive || !isValidMove(row, col)) {
                return;
            }

            Button clickedButton = (Button) v;

            if (player1Turn) {
                clickedButton.setText(PLAYER_1);
                board[row][col] = 1;
            } else {
                clickedButton.setText(PLAYER_2);
                board[row][col] = 2;
            }

            int gameResult = gameEnded(row, col);

            if (gameResult != -1) {
                gameActive = false;

                String message;
                if (gameResult == 1) {
                    message = "Oyuncu X Kazandı!";
                } else if (gameResult == 2) {
                    message = "Oyuncu O Kazandı!";
                } else {
                    message = "Berabere!";
                }

                Toast.makeText(MainActivity.this, message + " Menüden yeniden başlatın.", Toast.LENGTH_LONG).show();
            } else {
                player1Turn = !player1Turn;
            }
        }
    }
}
