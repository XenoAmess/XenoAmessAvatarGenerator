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

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class XenoAmessAvatarGeneratorTest {

    public void singleTest(int imageDiameter) {
        new File("out").mkdir();
        XenoAmessAvatarGenerator.generatePng(
                imageDiameter,
                0.5,
                new String[]{"#FF0000", "#000000"},
                "#FFFFFF",
                3,
                new File("out/output_" + imageDiameter + ".png"));
        XenoAmessAvatarGenerator.generateSvg(
                imageDiameter,
                0.5,
                new String[]{"#FF0000", "#000000"},
                "#FFFFFF",
                3,
                new File("out/output_" + imageDiameter + ".svg"));
//        XenoAmessAvatarGenerator.generatePng_ImageIO(imageDiameter, 0.5, new String[]{"#FF0000", "#000000"}, "#FFFFFF"
//                , 3,
//                new File(
//                        "out/output_native" + imageDiameter + ".png"));
        assertNotEquals(new File("out/output_" + imageDiameter + ".png").length(), 0);
        assertNotEquals(new File("out/output_" + imageDiameter + ".svg").length(), 0);
    }

    @Test
    public void mainTest() {
        singleTest(16);
        singleTest(32);
        singleTest(64);
        singleTest(128);
        singleTest(184);
        singleTest(256);
        singleTest(512);
        singleTest(1024);
        singleTest(3072);
//        singleTest(4096);
//        singleTest(8192);
//        singleTest(10240);
//        singleTest(20480);
    }
}
