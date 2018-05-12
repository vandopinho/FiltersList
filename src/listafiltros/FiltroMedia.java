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
public class FiltroMedia {

    BufferedImage imagemInicial;
    double teta;
    int largura = 0;
    int altura = 0;
    double R;
    double G;
    double B;
    BufferedImage imagemFinal;

    public FiltroMedia() throws IOException {
        imagemInicial = ImageIO.read(new File("image.png"));
        altura = imagemInicial.getHeight();
        largura = imagemInicial.getWidth();
        imagemFinal = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
    }
    public int[] getChannel(int i, int j) {
        Color channel = new Color(imagemInicial.getRGB(i, j));
        int[] values = new int[3];
        values[0] = channel.getRed();
        values[1] = channel.getGreen();
        values[2] = channel.getBlue();
        return values;
    }
    public void filtroMedia() throws IOException {

        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                try {
                    int vermelhoInicial = getChannel(i, j)[0];
                    int verdeInicial = getChannel(i, j)[1];
                    int azulInicial = getChannel(i, j)[2];
                    vermelhoInicial += getChannel(i + 1, j)[0];
                    verdeInicial += getChannel(i + 1, j)[1];
                    azulInicial += getChannel(i + 1, j)[2];
                    vermelhoInicial += getChannel(i, j + 1)[0];
                    verdeInicial += getChannel(i, j + 1)[1];
                    azulInicial += getChannel(i, j + 1)[2];
                    vermelhoInicial += getChannel(i - 1, j)[0];
                    verdeInicial += getChannel(i - 1, j)[1];
                    azulInicial += getChannel(i - 1, j)[2];
                    vermelhoInicial += getChannel(i, j - 1)[0];
                    verdeInicial += getChannel(i, j - 1)[1];
                    azulInicial += getChannel(i, j - 1)[2];
                    int vermelhoFinal = vermelhoInicial / 5;
                    int verdeFinal = verdeInicial / 5;
                    int azulFinal = azulInicial / 5;
                    Color temp = new Color(vermelhoFinal,verdeFinal,azulFinal);
                    imagemFinal.setRGB(i, j, temp.getRGB());
                } catch (ArrayIndexOutOfBoundsException ex) {}
            }
        }
        ImageIO.write(imagemFinal, "jpg", new File("FiltroMedia.jpg"));
    }
}
