import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener, ActionListener{

    private int[] snakeXlength = new int[750];
    private int[] snakeYlenght = new int[750];

    String player_name = "";

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon leftmouth;

    private int lenghtofsnake = 3;

    private Timer timer;
    private int delay = 100;
    private ImageIcon snakeimage;

    private int [] enemyxpose = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400,
    425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
    private int [] enemyypose = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400,
            425, 450, 475, 500, 525, 550, 575, 600, 625};

    private ImageIcon enemyimage;
    private Random random = new Random();
    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);

    private int score = 0;

    private int moves = 0;

    private ImageIcon titleImage;

    public Gameplay()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        player_name = JOptionPane.showInputDialog("Введите свой никнейм."); //Ввод имени, а также проверка на то,


        timer = new Timer(delay, this);
        timer.start();


    }

    public void paint(Graphics g)
    {
        if(moves == 0) //В начале игры рисуем позицию змейки. Если игра уже идёт это не проверяется.
        {
            snakeXlength[2] = 50;
            snakeXlength[1] = 75;
            snakeXlength[0] = 100;

            snakeYlenght[2] = 100;
            snakeYlenght[1] = 100;
            snakeYlenght[0] = 100;
        }

        //Рисует границы заглавной картинки
        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 851 , 55);

        //Рисует картинку
        titleImage = new ImageIcon("snaketitle.jpg");
        titleImage.paintIcon(this, g, 25 , 11);

        //Рисует границы для самой игры
        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 577);

        //Рисуем фон для игры
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);

        //Lenght
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Lenght: "+lenghtofsnake, 750, 50);

        //Input nickname
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Player name: "+player_name, 50, 40);

        //Draw score
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Scores: "+score, 750, 30);

        rightmouth = new ImageIcon("rightmouth.png");
        rightmouth.paintIcon(this, g, snakeXlength[0], snakeYlenght[0]);

        for(int a = 0; a < lenghtofsnake; a++){ // Проверяем направление гадюки

            if (a == 0 && right) //Иду направо, песнь заводит
            {
                rightmouth = new ImageIcon("rightmouth.png");
                rightmouth.paintIcon(this, g, snakeXlength[a], snakeYlenght[a]);
            }

            if (a == 0 && left) //налево, сказки говорит
            {
                leftmouth = new ImageIcon("leftmouth.png");
                leftmouth.paintIcon(this, g, snakeXlength[a], snakeYlenght[a]);
            }

            if (a == 0 && up) //Вверх
            {
                upmouth = new ImageIcon("upmouth.png");
                upmouth.paintIcon(this, g, snakeXlength[a], snakeYlenght[a]);
            }

            if (a == 0 && down) //Вниз (По социальной лестнице)
            {
                downmouth = new ImageIcon("downmouth.png");
                downmouth.paintIcon(this, g, snakeXlength[a], snakeYlenght[a]);
            }

            if (a!=0) // Рисуем тело змейки.
            {
                snakeimage = new ImageIcon("snakeimage.png");
                snakeimage.paintIcon(this, g, snakeXlength[a], snakeYlenght[a]);
            }
        }

        enemyimage = new ImageIcon("enemy.png");

        if((enemyxpose[xpos] == snakeXlength[0]) && (enemyypose[ypos] == snakeYlenght[0]))
        {
            score = score + 2;
            lenghtofsnake++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }

        enemyimage.paintIcon(this, g, enemyxpose[xpos], enemyypose[ypos]);

        for(int b = 1; b < lenghtofsnake; b++)
        {
            if(snakeXlength[b] == snakeXlength[0] && snakeYlenght[b] == snakeYlenght[0])
            {
                right = false;
                left = false;
                up = false;
                down = false;

                repaint();

                g.setColor(Color.WHITE);
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("Game over", 300, 300);

                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString("Press 'R' to restart", 350, 340);

            }
        }

        g.dispose();
    }

    @Override
    public void actionPerformed (ActionEvent e){
        timer .start();
        if (right)
        {
            for(int r = lenghtofsnake-1; r >= 0; r--)
            {
                snakeYlenght[r+1] = snakeYlenght[r];
            }

            for(int r = lenghtofsnake; r>=0; r--) //Логика игры (Направление движений и прочее..)
            {
                if (r==0){
                    snakeXlength[r] = snakeXlength[r] + 25;
                }
                else
                {
                    snakeXlength[r] = snakeXlength[r-1];
                }
                if( snakeXlength [r] > 850)
                {
                    snakeXlength[r] = 25;
                }

            }
            repaint();
        }

        if (left)
        {
            for(int r = lenghtofsnake-1; r >= 0; r--)
            {
                snakeYlenght[r+1] = snakeYlenght[r];
            }

            for(int r = lenghtofsnake; r>=0; r--)
            {
                if (r==0){
                    snakeXlength[r] = snakeXlength[r] - 25;
                }
                else
                {
                    snakeXlength[r] = snakeXlength[r-1];
                }
                if( snakeXlength [r] < 25)
                {
                    snakeXlength[r] = 850;
                }

            }
            repaint();
        }
        if (up)
        {
            for(int r = lenghtofsnake-1; r >= 0; r--)
            {
                snakeXlength[r+1] = snakeXlength[r];
            }

            for(int r = lenghtofsnake; r>=0; r--)
            {
                if (r==0){
                    snakeYlenght[r] = snakeYlenght[r] - 25;
                }
                else
                {
                    snakeYlenght[r] = snakeYlenght[r-1];
                }
                if( snakeYlenght [r] < 75)
                {
                    snakeYlenght[r] = 625;
                }

            }
            repaint();
        }
        if (down)
        {
            for(int r = lenghtofsnake-1; r >= 0; r--)
            {
                snakeXlength[r+1] = snakeXlength[r];
            }

            for(int r = lenghtofsnake; r>=0; r--)
            {
                if (r==0){
                    snakeYlenght[r] = snakeYlenght[r] + 25;
                }
                else
                {
                    snakeYlenght[r] = snakeYlenght[r-1];
                }
                if( snakeYlenght [r] > 625)
                {
                    snakeYlenght[r] = 75;
                }

            }
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_R)
        {
            moves = 0;
            score = 0;
            lenghtofsnake = 3;
            repaint();

        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) //Допрашиваем какая кнопка нажата, прям как в подвалах КГБ
        {
            moves++;
            right = true;
            if (!left) //Prover_O4ka
            {
                right = true;
            }
            else
            {
                right = false;
                left = true;
            }
            up = false;
            down = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            moves++;
            left = true;
            if (!right) //Prover_O4ka
            {
                left = true;
            }
            else
            {
                left = false;
                right = true;
            }
            up = false;
            down = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            moves++;
            up = true;
            if (!down) //Prover_O4ka
            {
                up = true;
            }
            else
            {
                up = false;
                down = true;
            }
            right = false;
            left = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            moves++;
            down = true;
            if (!up) //Prover_O4ka
            {
                down = true;
            }
            else
            {
                down = false;
                up = true;
            }
            right = false;
            left = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
