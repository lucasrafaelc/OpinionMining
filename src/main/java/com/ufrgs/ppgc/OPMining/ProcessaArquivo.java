package com.ufrgs.ppgc.OPMining;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class ProcessaArquivo {
        
    public ArrayList<String> carregaArquivo() throws IOException{
        ArrayList<String> resultado = new ArrayList<String>();
        String caminho = "C:\\Users\\lucas\\Desktop\\Mestrado\\Opinion Mining\\OpinionMining\\aylien_textapi_java\\src\\main\\java\\hotels";
        File gerenciadorArquivos = new File(caminho);
        BufferedReader leString = null;
        System.out.println(gerenciadorArquivos.getAbsolutePath());
        String[] listaPastas = gerenciadorArquivos.list();
        System.out.println(listaPastas == null);
        for(String pasta: listaPastas){
            String local = caminho+"\\"+pasta;
            File aux = new File(local);
            String[] arquivos = aux.list();
            for(String arquivo: arquivos){
                InputStream is = new FileInputStream(local+"\\"+arquivo);
                InputStreamReader leitorChar = new InputStreamReader(is);
                leString = new BufferedReader(leitorChar);
                String texto = "";
                while(texto!= null){
                    System.out.println(texto);
                    texto = leString.readLine();
                    if(texto != null){
                        resultado.add(texto);
                    }
                }
            }
        }
        leString.close();
        return resultado;
    }
    
}
