package control;

import java.io.File;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Image;
import loader.FileImageLoader;
import view.ImageDisplay;

public class ImageViewer extends JFrame {
    public static void main(String[] args) {
        new ImageViewer().execute();
    }
    private ImageDisplay imageDisplay;
    private final ImagePresenter imagePresenter;
    
    public ImageViewer(){
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(imagePanel());
        List<Image> images = new FileImageLoader(new File("images")).load();
        this.imagePresenter = new ImagePresenter(images, imageDisplay);
    }

    private void execute() {
        this.setVisible(true);
    }

    private JPanel imagePanel() {
        ImagePanel imagePanel = new ImagePanel();
        this.imageDisplay = imagePanel;
        return imagePanel;
    }
}