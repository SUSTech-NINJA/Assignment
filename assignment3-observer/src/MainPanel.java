import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class MainPanel extends JPanel implements Subject<Ball> {
    private List<Ball> paintingBallList = new ArrayList<>();

    public enum GameStatus {PREPARING, START, STOP}

    private GameStatus gameStatus;
    private int score;
    private Ball whiteBall;
    Timer t;

    public MainPanel() {
        super();
        setLayout(null);
        setSize(590, 590);
        setFocusable(true);
        this.addKeyListener(this);
        t = new Timer(50, _ -> moveBalls());
        restartGame();
    }


    public void startGame() {
        this.gameStatus = GameStatus.START;
        this.whiteBall.setVisible(true);
        this.paintingBallList.forEach(b -> b.setVisible(false));
        registerObserver(this.whiteBall);
        notifyObservers(GameStatus.START);
    }

    public void stopGame() {
        this.gameStatus = GameStatus.STOP;
        this.t.stop();
        notifyObservers(GameStatus.STOP);
        paintingBallList.forEach(b -> {
            if (b.isVisible()) {
                if (b.getColor() == Color.RED) {
                    scoreIncrement(80);
                } else if (b.getColor() == Color.BLUE) {
                    scoreIncrement(-80);
                }
            }
        });
        removeObserver(this.whiteBall);
        repaint();
    }

    public void restartGame() {
        notifyObservers(GameStatus.PREPARING);
        this.gameStatus = GameStatus.PREPARING;
        if (!paintingBallList.isEmpty()) {
            paintingBallList.forEach(this::remove);
        }
        this.paintingBallList = new ArrayList<>();
        Ball.setCount(0);
        this.score = 100;
        if (this.whiteBall != null)
            this.whiteBall.setVisible(false);

        this.t.start();
        repaint();
    }

    public void setWhiteBall(Ball whiteBall) {
        this.whiteBall = whiteBall;
        this.whiteBall.setVisible(false);
        add(whiteBall);
    }

    public void moveBalls() {
        paintingBallList.stream().filter(ball -> !ball.equals(whiteBall)).forEach(Ball::move);
        if (this.gameStatus == GameStatus.START) {
            score--;
            whiteBall.move();
            notifyObservers(whiteBall);
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 20, 40);

        if (gameStatus == GameStatus.START) {
            this.setBackground(Color.WHITE);
        }

        if (gameStatus == GameStatus.STOP) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 45));
            g.drawString("Game Over!", 200, 200);
            g.setFont(new Font("", Font.BOLD, 40));
            g.drawString("Your score is " + score, 190, 280);
        }
    }

    public void scoreIncrement(int increment) {
        this.score += increment;
    }


    public void addBallToPanel(Ball ball) {
        registerObserver(ball);
        this.add(ball);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char keyChar = e.getKeyChar();
        System.out.println("Press: " + keyChar);
        notifyObservers(keyChar);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void registerObserver(Ball ball) {
        paintingBallList.add(ball);
    }

    @Override
    public void removeObserver(Ball ball) {
        paintingBallList.remove(ball);
    }

    @Override
    public void notifyObservers(char keyChar) {
        paintingBallList.forEach(ball -> ball.notify(keyChar));
    }

    @Override
    public void notifyObservers(GameStatus status) {
        paintingBallList.forEach(ball -> ball.notify(status));
    }

    @Override
    public void notifyObservers(Ball b) {
        paintingBallList.forEach(ball -> ball.checkIntersect(b));
    }
}
