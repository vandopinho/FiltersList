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

/**
 *
 * @author vando
 */
public class FiltroHSI {

    BufferedImage imagemInicial;
    double teta;
    int largura = 0;
    int altura = 0;
    double R;
    double G;
    double B;
    BufferedImage imagemFinal;
    
    public FiltroHSI() throws IOException {
        imagemInicial = ImageIO.read(new File("image.png"));
        altura = imagemInicial.getHeight();
        largura = imagemInicial.getWidth();
        imagemFinal = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
    }

    public double findS(double R, double G, double B) {
        double resultS = 0;
        if (R < G && R < B) {
            resultS = R;
        }
        if (G < R && G < B) {
            resultS = G;
        }
        if (B < G && B < R) {
            resultS = B;
        }
        return resultS;
    }

    public void FiltroHSI() throws IOException {        
        Color HSI = null;
        double H = 0;
        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                Color temp = new Color(imagemInicial.getRGB(i, j));
                R = temp.getRed();
                G = temp.getGreen();
                B = temp.getBlue();
                double valorR = R / 255;
                double valorG = G / 255;
                double valorB = B / 255;
                double num = 1/2 *((valorR - valorG) + (valorR - valorB));
                double denum = Math.sqrt((Math.pow((valorR - valorG), 2) + (valorR - valorB) * (valorG - valorB)));
                if (num < 0) {
                    num = num * -1;
                }
                if (denum < 0) {
                    denum = denum * -1;
                }
                double angulo = Math.acos(num/denum+0.000001);
                if(valorB<=valorG){
                    H = angulo;
                }
                if(valorB > valorG){
                    double x = 360 - angulo;
                    x = x/360;                    
                    H = x;
                }
               double S = 1 - (3/(R+G+B))*findS(R,G,B);
               double I = (valorR+valorB+valorG)/3;
               H = H * 100;
               I = I * 100;
               S = S * 100;
               HSI = new Color((int)H,(int)S,(int)I);
               imagemFinal.setRGB(i, j, HSI.getRGB());
            }
        }
        ImageIO.write(imagemFinal, "jpg", new File("FiltroHSI.jpg"));
    }
}
