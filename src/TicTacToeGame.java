public class TicTacToeGame {
    private int[][] board;

    public TicTacToeGame() {
        board = new int[3][3];
    }

    public String start() {
        StringBuilder sb = new StringBuilder();

        sb.append("Welcome to Tic Tac Toe!\n");
        sb.append("Enter your move in the format x,y\n");
        sb.append("Example: 0,0\n");
        sb.append(getBoard());

        return sb.toString();
    }

    public String play(String move) {
        // Check if the pattern is correct
        String inputPattern = "^[1-3],[1-3]$";
        if (!move.matches(inputPattern)) {
            return "Invalid input. Please enter your move in the format x,y";
        }

        // Check if the move is valid. if so, play the move
        if (!clientPlay(move)) {
            return "Invalid move. Please try again";
        }

        if (checkWin() == 1) {
            return "You win!\n" + getBoard();
        }

        AIPlay();

        if (checkWin() == 2) {
            return "You lose!\n" + getBoard();
        } else {
            return getBoard();
        }
    }

    private boolean clientPlay(String move) {
        int x, y;

        // Parse input
        String[] coords = move.split(",");
        x = Integer.parseInt(coords[1]) - 1;
        y = Integer.parseInt(coords[0]) - 1;

        // Check if the input is valid
        if (board[x][y] != 0) {
            return false;
        }
        board[x][y] = 1;

        return true;
    }

    private void AIPlay() {
        int x = (int) (Math.random() * 3);
        int y = (int) (Math.random() * 3);

        // Check if the input is valid
        if (board[x][y] == 0) {
            board[x][y] = 2;
        } else {
            AIPlay();
        }
    }

    private int checkWin() {
        for (int team = 1; team < 3; team++) {
            // Check rows
            for (int x = 0; x < 3; x++) {
                if (board[x][0] == board[x][1] && board[x][1] == board[x][2] && board[x][0] == team) {
                    return team;
                }
            }

            // Check columns
            for (int y = 0; y < 3; y++) {
                if (board[0][y] == board[1][y] && board[1][y] == board[2][y] && board[0][y] == team) {
                    return team;
                }
            }

            // Check diagonals
            if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == team) {
                return team;
            }
            if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] == team) {
                return team;
            }
        }

        return 0;
    }

    private String getBoard() {
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                sb.append(board[x][y]);
                if (y < 2) {
                    sb.append(",");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
