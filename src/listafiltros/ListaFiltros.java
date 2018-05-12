/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listafiltros;

import java.io.IOException;

/**
 *
 * @author vando
 */
public class ListaFiltros {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        FiltroLaplace laplace = new FiltroLaplace();
        FiltroCinza cinza = new FiltroCinza();
        FiltroMedia media = new FiltroMedia();
        FiltroMediana mediana = new FiltroMediana();
        FiltroYCbCr ycbcr = new FiltroYCbCr();
        FiltroHSI hsi = new FiltroHSI();
        hsi.FiltroHSI();
        ycbcr.FiltroYCbCr();
        mediana.FiltroMediana();
        media.filtroMedia();
        cinza.filtroCinza();
        laplace.filtroLaplaciano();   
    }
}
