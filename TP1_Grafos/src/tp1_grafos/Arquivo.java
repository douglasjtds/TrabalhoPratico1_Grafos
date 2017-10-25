package tp1_grafos;

/**
 *
 * @author Matheus Pires Fernandes & Douglas Tertuliano
 */

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class Arquivo {
        
    public String caminhoArestas, caminhoVertices;
    

    public Arquivo(String caminhoArestas, String caminhoVertices) {
        this.caminhoArestas = caminhoArestas;
        this.caminhoVertices = caminhoVertices;
    }
    
    public String[] InicializarArquivo(String caminho, int linhasArquivo) throws IOException{
        String[] vetorPreenchido;
        
        FileReader arquivoInicial = new FileReader(caminho);
        
        try (BufferedReader LeitorTxt = new BufferedReader(arquivoInicial)) {
            vetorPreenchido = new String[linhasArquivo];
            
            for(int i=0; i<linhasArquivo; i++){
                vetorPreenchido[i] = LeitorTxt.readLine();
            }
//        } catch () {
//            
        }
        
        
        return vetorPreenchido;        
        }                   
    }