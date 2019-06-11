/*
 * MIT License
 *
 * Copyright (c) 2019 XenoAmess
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.xenoamess.xenoamess_avatar_generator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map.Entry;


/**
 * <p>XenoAmessAvatarGenerator class.</p>
 *
 * @author XenoAmess
 * @version 2.1.0
 */
public class XenoAmessAvatarGenerator {

    /**
     * <p>generatePng.</p>
     *
     * @param imageDiameter    size of the image.
     * @param innerRadiusRatio ratio of the inner circle.
     * @param colorStrings     strings of the color
     * @param plateColorString color of the plate.
     * @param round            repeat time for every colors.
     * @param outputFile       output File
     */
    public static void generatePng(int imageDiameter, double innerRadiusRatio, String[] colorStrings,
                                   String plateColorString,
                                   int round, File outputFile) {
        File svgFile = new File(outputFile.getAbsolutePath() + ".temp.svg");
        generateSvg(imageDiameter, innerRadiusRatio, colorStrings,
                plateColorString,
                round, svgFile);
        String commandString =
                "-f " + svgFile.getAbsolutePath() + " -o " + outputFile.getParentFile().getAbsolutePath() + " -n " + outputFile.getName() +
                        " -h " + imageDiameter +
                        " -w " + imageDiameter;
        org.sterl.svg2png.Main.main(commandString.split("\\s"));
        svgFile.delete();
    }


    /**
     * <p>generateSvg.</p>
     *
     * @param imageDiameter    size of the image.
     * @param innerRadiusRatio ratio of the inner circle.
     * @param colorStrings     strings of the color
     * @param plateColorString color of the plate.
     * @param round            repeat time for every colors.
     * @param outputFile       output File
     */
    public static void generateSvg(int imageDiameter, double innerRadiusRatio, String[] colorStrings,
                                   String plateColorString,
                                   int round, File outputFile) {
        try (FileWriter fileWriter = new FileWriter(outputFile)) {
            generateSvg(imageDiameter, innerRadiusRatio, colorStrings,
                    plateColorString,
                    round, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>generateSvg.</p>
     *
     * @param imageDiameter    size of the image.
     * @param innerRadiusRatio ratio of the inner circle.
     * @param colorStrings     strings of the color
     * @param plateColorString color of the plate.
     * @param round            repeat time for every colors.
     * @param outputWriter     output writer
     * @throws java.io.IOException if any.
     * @since 2.1.0
     */
    public static void generateSvg(int imageDiameter, double innerRadiusRatio, String[] colorStrings,
                                   String plateColorString,
                                   int round, Writer outputWriter) throws IOException {

        StringBuilder svgStringBuilder = new StringBuilder();
        svgStringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append("<svg xmlns=\"http://www.w3.org/2000/svg\" ")
                .append("fill-opacity=\"1\" ")
                .append("width=\"")
                .append(imageDiameter)
                .append("\" ")
                .append("height=\"")
                .append(imageDiameter)
                .append("\" ")
                .append("stroke-width=\"0\">")
                .append("<defs id=\"xenoamess@gmail.com\"/>").append("<g transform=\"translate(").append(imageDiameter / 2.0).append(",").append(imageDiameter / 2.0).append(")\">");

        double eachSideSize = 360.0 / round;
        double eachPlateSize = 1.0 - colorStrings.length * 0.3;

        svgStringBuilder.append("<circle cx=\"0\" cy=\"0\" r=\"").
                append(imageDiameter / 2.0)
                .append("\" ")
                .append("fill=\"")
                .append(plateColorString)
                .append("\"/>");

        double eachPlateAngle = eachPlateSize * eachSideSize / colorStrings.length;
        double nowAngle = 180.0 - eachSideSize * 0.30 / 2;
        for (int i = 0; i < round; i++) {
            for (String colorString : colorStrings) {
                svgStringBuilder.append("<path d=\"M 0 0 ")
                        .append(Math.sin(nowAngle * Math.PI / 180.0) * imageDiameter / 2)
                        .append(" ")
                        .append(Math.cos(nowAngle * Math.PI / 180.0) * imageDiameter / 2)
                        .append(" A ").append(imageDiameter / 2.0)
                        .append(" ").append(imageDiameter / 2.0)
                        .append(" 0 0 0 ")
                        .append(Math.sin((nowAngle + eachSideSize * 0.30) * Math.PI / 180.0) * imageDiameter / 2)
                        .append(" ")
                        .append(Math.cos((nowAngle + eachSideSize * 0.30) * Math.PI / 180.0) * imageDiameter / 2)
                        .append("Z\"")
                        .append(" fill=\"")
                        .append(colorString)
                        .append("\"/>");
//                System.out.println(nowAngle);
                nowAngle += eachSideSize * 0.30;
//                System.out.println(nowAngle);
                nowAngle += eachPlateAngle;
            }
        }

        svgStringBuilder.append("<circle cx=\"0\" cy=\"0\" r=\"")
                .append(innerRadiusRatio * imageDiameter / 2.0)
                .append("\" ")
                .append("fill=\"")
                .append(plateColorString)
                .append("\"/>");
        svgStringBuilder.append("</g></svg>");
        outputWriter.write(svgStringBuilder.toString());
    }


    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        new File("out").mkdir();
        generatePng(10240, 0.5, new String[]{"#FF0000", "#000000"}, "#FFFFFF", 3, new File("out/test.png"));
        generateSvg(10240, 0.5, new String[]{"#FF0000", "#000000"}, "#FFFFFF", 3, new File("out/test.svg"));
    }

    /**
     * Original version of generatePng.
     *
     * @param imageDiameter    size of the image.
     * @param innerRadiusRatio ratio of the inner circle.
     * @param colorStrings     strings of the color
     * @param plateColorString color of the plate.
     * @param round            repeat time for every colors.
     * @param outputFile       output File
     * @since 2.1.0
     * @deprecated It was written in ImageIO thus buggy and low performance.
     */
    @Deprecated
    public static void generatePng_ImageIO(int imageDiameter, double innerRadiusRatio, String[] colorStrings,
                                           String plateColorString,
                                           int round, File outputFile) {

        ArrayList<Entry<Color, Double>> colors = new ArrayList<>();

        for (String string : colorStrings) {
            colors.add(new AbstractMap.SimpleEntry<>(Color.decode(string), 0.30));
        }

        Color plateColor = Color.decode(plateColorString);

        BufferedImage bi = new BufferedImage(imageDiameter, imageDiameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
        g2.setBackground(new Color(0, 0, 0, 0));
        g2.clearRect(0, 0, imageDiameter, imageDiameter);

        g2.setColor(plateColor);
        g2.fillOval((int) (imageDiameter * 0.01), (int) (imageDiameter * 0.01), (int) (imageDiameter * 0.98),
                (int) (imageDiameter * 0.98));

        double eachSideSize = 360.0 / round;

        double eachPlateSize = 1.0;
        for (Entry<Color, Double> entry : colors) {
            eachPlateSize -= entry.getValue();
        }
        double eachPlateAngle = eachPlateSize * eachSideSize / colors.size();
        double nowAngle = 90 - eachSideSize * colors.get(0).getValue() / 2;
        for (int i = 0; i < round; i++) {
            for (Entry<Color, Double> entry : colors) {
//                System.out.println(nowAngle);
                g2.setColor(entry.getKey());
                // System.out.println(eachSideSize);
                g2.fillArc(0, 0, imageDiameter, imageDiameter, (int) nowAngle, (int) (eachSideSize * entry.getValue()));
                nowAngle += eachSideSize * entry.getValue();
//                System.out.println(nowAngle);
                g2.setColor(plateColor);
                g2.fillArc(0, 0, imageDiameter, imageDiameter, (int) nowAngle, (int) (eachPlateAngle));
                nowAngle += eachPlateAngle;
            }
        }

        g2.setColor(plateColor);
        g2.fillOval((int) (imageDiameter - innerRadiusRatio * imageDiameter) / 2,
                (int) (imageDiameter - innerRadiusRatio * imageDiameter) / 2, (int) (innerRadiusRatio * imageDiameter),
                (int) (innerRadiusRatio * imageDiameter));
        try {
            ImageIO.write(bi, "PNG", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
