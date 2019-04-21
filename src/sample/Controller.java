package sample;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

public class Controller{
    private String filePath;
    private Integer K;
    private Image originalImage;
    private Image segmentedImage;
    private Segmentator segmentator = new Segmentator();
    public void setPath(String filePath){

        this.filePath = filePath;
    }

    public void setK(Integer K){

        this.K = K;
    }

    public GridPane getPane(){

        return topGrid;
    }
    private BufferedImage toGrayScale(BufferedImage image){
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        return op.filter(image, null);
    }

    @FXML
    private GridPane topGrid = new GridPane();

    @FXML
    private ImageView imageView;

    @FXML
    private ImageView segmentedView;



    public void ini() throws IOException {
        File file = new File(filePath);
        BufferedImage original = toGrayScale(ImageIO.read(file));

        segmentator.setImage(original);
        segmentator.setK(K);

        originalImage = SwingFXUtils.toFXImage(original,null);
        imageView.setImage(originalImage);
        topGrid.add(imageView,0,0);

        BufferedImage realSegmented = segmentator.segment();
        segmentedImage = SwingFXUtils.toFXImage(realSegmented,null);
        segmentedView.setImage(segmentedImage);
        topGrid.add(segmentedView,1,0);

    }


}
