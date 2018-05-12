/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listafiltros;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FiltroYCbCr {
    BufferedImage imagemInicial;
    BufferedImage imagemFinal;
    int l = 0;
    int h = 0;
    int R, G, B;
    double Y, Cb, Cr;
    double[][] m = new double[3][3];

    public FiltroYCbCr() throws IOException {
        imagemInicial = ImageIO.read(new File("image.png"));
        m[0][0] = 0.2568;
        m[0][1] = 0.5041;
        m[0][2] = 0.0979;
        m[1][0] = -0.1482;
        m[1][1] = -0.2910;
        m[1][2] = 0.4392;
        m[2][0] = 0.4392;
        m[2][1] = -0.3678;
        m[2][2] = -0.0714;
        h = imagemInicial.getHeight();
        l = imagemInicial.getWidth();
        imagemFinal = new BufferedImage(l, h, BufferedImage.TYPE_INT_RGB);
    }

    public void FiltroYCbCr() throws IOException {
        for (int i = 0; i < l -1; i++) {
            for (int j = 0; j < h - 1; j++) {
                Color c = new Color(imagemInicial.getRGB(i, j));
                this.B = c.getBlue();
                this.R = c.getRed();
                this.G = c.getGreen();
                int line = 0, column = 0;
                Y = m[line][column] * R;
                column++;
                Y += m[line][column] * G;
                column++;
                Y += m[line][column] * B;
                Y += 16;
                line++;
                column = 0;
                Cb = m[line][column] * R;
                column++;
                Cb += m[line][column] * G;
                column++;
                Cb += m[line][column] * B;
                Cb += 128;
                line++;
                column = 0;
                Cr = m[line][column] * R;
                column++;
                Cr += m[line][column] * G;
                column++;
                Cr += m[line][column] * B;
                Cr += 128;
                Color filtroAplicado = new Color((int)Y,(int)Cb,(int)Cr);   
                imagemFinal.setRGB(i, j, filtroAplicado.getRGB());
            }
        }
        ImageIO.write(imagemFinal,"jpg", new File("FiltroYCbCr.jpg"));
    }
}
