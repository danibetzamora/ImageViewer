package control;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import view.ImageDisplay;

public class ImagePanel extends JPanel implements ImageDisplay {
    
    private String current;
    private String future;
    private BufferedImage image;
    private BufferedImage image2;
    private int offset = 0;
    private Shift shift = new Shift.Null();

    public ImagePanel() {
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }
    
    @Override
    public void paint(Graphics g){
        g.drawImage(image, offset, 0, null);
        if(offset == 0) return;
        g.drawImage(image2, offset > 0 ? -(image2.getWidth()+offset) : image2.getWidth(), 0, null);
    }

    @Override
    public void display(String name) {
        this.current = name;
        this.image = load(name);
    }

    private static BufferedImage load(String name) {
        try{
            return ImageIO.read(new File(name));
        } catch(IOException ex){
            return null;
        }
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift;
    }

    @Override
    public String current() {
        return this.current;
    }

    private class MouseHandler implements MouseListener, MouseMotionListener {
        
        private int initial;
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            initial = e.getX();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(Math.abs(offset) > getWidth() / 2){
                current = future;
                image = image2;
            }
            offset = 0;
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            offset = e.getX() - initial;
            if(offset < 0) future = shift.right();
            if(offset > 0) future = shift.left();
            image2 = load(future);
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }
    
}