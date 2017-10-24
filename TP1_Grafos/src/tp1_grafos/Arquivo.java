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
    public BufferedReader BufferArestas, BufferVertices;
    public int QntdArestas, QntdVertices;

    
    public int ContarLinhas (BufferedReader Texto) throws IOException{
        int lines = 0;
        
        while (Texto.readLine() != null){
            lines++;
        }
            
        return lines;
    }
    
    public BufferedReader InicializarArquivo(String caminho) throws FileNotFoundException{
        FileReader la = new FileReader(caminho);
        BufferedReader Texto = new BufferedReader(la);
        
        return Texto;
        
        
    }           
        int NumArestas = 1919;
        int NumVertices = 41161;
        
        String[] VetorArestas = new String[NumArestas];
        
        
}
