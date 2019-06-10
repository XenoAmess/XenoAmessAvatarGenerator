import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map.Entry;

public class XenoAmessAvatarGenerator {
    public static String outputFileName = "output2.png";

    // output image's width and height(must be same).
    public static int imageWidth = 3072;
    public static int imageHeight = imageWidth;

    // rate of the inner radius
    public static double innerRadius = 0.4;

    // Color outof the circle
    public static Color backgroundColor = new Color(0, 0, 0, 0);

    // <Color,rate of the Color>
    public static ArrayList<Entry<Color, Double>> colors = new ArrayList<Entry<Color, Double>>();

    static {
        colors.add(new AbstractMap.SimpleEntry<Color, Double>(Color.red, 0.30));
        colors.add(new AbstractMap.SimpleEntry<Color, Double>(Color.black, 0.30));
    }

    // Color of the plate of thevcircle
    public static Color plateColor = new Color(255, 255, 255, 255);

    // repeat time for every colors.
    public static int round = 3;

    public static void main(String args[]) {

        File file = new File(outputFileName);

        BufferedImage bi = new BufferedImage(imageWidth, imageWidth, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) bi.createGraphics();
        g2.setBackground(backgroundColor);
        g2.clearRect(0, 0, imageWidth, imageHeight);

        g2.setColor(plateColor);
        g2.fillOval((int) (imageWidth * 0.01), (int) (imageHeight * 0.01), (int) (imageWidth * 0.98),
                (int) (imageHeight * 0.98));
//        g2.fillArc(0, 0, (int) (imageWidth * 0.99), (int) (imageHeight * 0.99), 0, 360);

        double eachSideSize = 360.0 / round;

        double eachPlateSize = 1.0;
        for (Entry<Color, Double> entry : colors) {
            eachPlateSize -= entry.getValue();
        }
        double eachPlateAngle = eachPlateSize * eachSideSize / colors.size();
        double nowAngle = 90 - eachSideSize * colors.get(0).getValue() / 2;
        for (int i = 0; i < round; i++) {
            for (Entry<Color, Double> entry : colors) {
                System.out.println(nowAngle);
                g2.setColor(entry.getKey());
                // System.out.println(eachSideSize);
                g2.fillArc(0, 0, imageWidth, imageHeight, (int) nowAngle, (int) (eachSideSize * entry.getValue()));
                nowAngle += eachSideSize * entry.getValue();
                System.out.println(nowAngle);
                g2.setColor(plateColor);
                g2.fillArc(0, 0, imageWidth, imageHeight, (int) nowAngle, (int) (eachPlateAngle));
                nowAngle += eachPlateAngle;
            }

        }

        g2.setColor(plateColor);
        g2.fillOval((int) (imageWidth - innerRadius * imageWidth) / 2,
                (int) (imageHeight - innerRadius * imageHeight) / 2, (int) (innerRadius * imageWidth),
                (int) (innerRadius * imageHeight));

        try {
            ImageIO.write(bi, "PNG", file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
