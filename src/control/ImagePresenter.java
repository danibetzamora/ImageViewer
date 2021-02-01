package control;

import java.util.List;
import model.Image;
import view.ImageDisplay;

public class ImagePresenter {
    private final List<Image> images;
    private final ImageDisplay imageDisplay;

    public ImagePresenter(List<Image> images, ImageDisplay imageDisplay) {
        this.images = images;
        this.imageDisplay = imageDisplay;
        this.imageDisplay.display(images.get(0).getName());
        this.imageDisplay.on(shift());
    }

    private ImageDisplay.Shift shift() {
        
        return new ImageDisplay.Shift(){
            @Override
            public String left() {
                int index = find(imageDisplay.current());
                return images.get((index + 1) % images.size()).getName();
            }

            @Override
            public String right() {
                int index = find(imageDisplay.current());
                return images.get((index - 1 + images.size()) % images.size()).getName();
            }
        };
    }

    private int find(String current) {
        for (int i = 0; i < images.size(); i++) {
            if(images.get(i).getName().equals(current)) return i;
        }
        return -1;
    }
}