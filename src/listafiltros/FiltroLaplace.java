package listafiltros;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FiltroLaplace {

    int[][] mascara;
    BufferedImage imagemInicial;
    BufferedImage imagemFinal;
    int[][] aux;
    int altura;
    int largura;
    
    public FiltroLaplace() throws IOException {
        imagemInicial = ImageIO.read(new File("image.png"));
        mascara = new int[3][3];
        altura = imagemInicial.getHeight();
        largura = imagemInicial.getWidth();
        imagemFinal = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        aux = new int[largura][altura];
    }

    public void setMask() {
        mascara[0][0] = 1;
        mascara[0][1] = 1;
        mascara[0][2] = 1;
        mascara[1][0] = 1;
        mascara[1][1] = -8;
        mascara[1][2] = 1;
        mascara[2][0] = 1;
        mascara[2][1] = 1;
        mascara[2][2] = 1;

    }

    public void filtroLaplaciano() throws IOException {
        this.setMask();
        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                Color c = new Color(imagemInicial.getRGB(i, j));
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                int cinza = (r + g + b) / 3;
                aux[i][j] = cinza;
            }
        }
        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                try {
                    int total = aux[i][j] * mascara[1][1];
                    total += aux[i + 1][j] * mascara[1][2];
                    total += aux[i][j + 1] * mascara[2][1];
                    total += aux[i - 1][j] * mascara[1][0];
                    total += aux[i][j - 1] * mascara[0][1];
                    total += aux[i - 1][j - 1] * mascara[0][0];
                    total += aux[i + 1][j + 1] * mascara[2][2];
                    total += aux[i - 1][j + 1] * mascara[2][0];
                    total += aux[i + 1][j - 1] * mascara[0][2];
                    if (total < 0) {
                        total = total * -1;
                    }
                    if (total > 255) {
                        total = 255;
                    }
                    Color c = new Color(total, total, total);
                    imagemFinal.setRGB(i, j, c.getRGB());
                } catch (ArrayIndexOutOfBoundsException ex) {
                    if (j == 0) {
                        if (i == 0) {
                            int total = aux[i][j] * mascara[1][1];
                            total += aux[i + 1][j] * mascara[1][2];
                            total += aux[i][j + 1] * mascara[2][1];
                            total += aux[i + 1][j] * mascara[2][2];
                            if (total < 0) {
                                total = total * -1;
                            }
                            total = total / 3;
                            if (total > 255) {
                                total = 255;
                            }
                            Color c = new Color(total, total, total);
                            imagemFinal.setRGB(i, j, c.getRGB());

                        }
                        if (i == largura - 1) {
                            int total = aux[i][j] * mascara[1][1];
                            total += aux[i - 1][j] * mascara[1][0];
                            total += aux[i][j + 1] * mascara[2][0];
                            total += aux[i - 1][j + 1] * mascara[2][1];
                            if (total < 0) {
                                total = total * -1;
                            }
                            total = total / 3;
                            if (total > 255) {
                                total = 255;
                            }
                            Color c = new Color(total, total, total);
                            imagemFinal.setRGB(i, j, c.getRGB());
                        }
                        if (i != 0 && i != largura - 1) {
                            int total = aux[i][j] * mascara[1][1];
                            total += aux[i - 1][j] * mascara[1][0];
                            total += aux[i - 1][j + 1] * mascara[2][0];
                            total += aux[i][j + 1] * mascara[2][1];
                            total += aux[i + 1][j + 1] * mascara[2][2];
                            total += aux[i + 1][j] * mascara[1][2];
                            if (total < 0) {
                                total = total * -1;
                            }
                            total = total / 4;
                            if (total > 255) {
                                total = 255;
                            }
                            Color c = new Color(total, total, total);
                            imagemFinal.setRGB(i, j, c.getRGB());
                        }
                    }
                    if (j == altura - 1) {
                        if (i == 0) {
                            int total = aux[i][j] * mascara[1][1];
                            total += aux[i + 1][j] * mascara[1][2];
                            total += aux[i][j - 1] * mascara[0][1];
                            total += aux[i + 1][j - 1] * mascara[0][2];
                            if (total < 0) {
                                total = total * -1;
                            }
                            total = total / 3;
                            if (total > 255) {
                                total = 255;
                            }
                            Color c = new Color(total, total, total);
                            imagemFinal.setRGB(i, j, c.getRGB());

                        }
                        if (i == largura - 1) {
                            int total = aux[i][j] * mascara[1][1];
                            total += aux[i - 1][j] * mascara[1][0];
                            total += aux[i][j - 1] * mascara[2][0];
                            total += aux[i - 1][j - 1] * mascara[2][1];
                            if (total < 0) {
                                total = total * -1;
                            }
                            total = total / 3;

                            if (total > 255) {
                                total = 255;
                            }
                            Color c = new Color(total, total, total);
                            imagemFinal.setRGB(i, j, c.getRGB());

                        }
                        if (i != 0 && i != largura - 1) {
                            int total = aux[i][j] * mascara[1][1];
                            total += aux[i - 1][j] * mascara[1][0];
                            total += aux[i - 1][j - 1] * mascara[0][0];
                            total += aux[i][j - 1] * mascara[0][1];
                            total += aux[i + 1][j - 1] * mascara[0][2];
                            total += aux[i + 1][j] * mascara[1][2];
                            if (total < 0) {
                                total = total * -1;
                            }
                            total = total / 4;

                            if (total > 255) {
                                total = 255;
                            }
                            Color c = new Color(total, total, total);
                            imagemFinal.setRGB(i, j, c.getRGB());
                        }
                    }
                }
            }
        }
        ImageIO.write(imagemFinal, "jpg", new File("FiltroLaPlace.jpg"));
    }
}
