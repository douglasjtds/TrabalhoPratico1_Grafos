package tp1_grafos;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author douglasjtds & yMetsBless
 */

public class TP1_Grafos {
    
<<<<<<< HEAD
    public static String[] InicializarArquivo(File caminho, int linhasArquivo) throws IOException{
        
        String[] vetorPreenchido = new String[linhasArquivo];
=======
        public static String[] InicializarArquivo(String caminho, int linhasArquivo) throws IOException{
            String[] vetorPreenchido = new String[linhasArquivo];
>>>>>>> parent of 946168f... Plotando o grafo

            FileReader arquivoInicial = new FileReader(caminho);
            BufferedReader LeitorTxt = new BufferedReader(arquivoInicial);

                for(int i=0; i<linhasArquivo; i++){
                    vetorPreenchido[i] = LeitorTxt.readLine();
                }

            arquivoInicial.close();
            return vetorPreenchido;        
        }

        //      POSSÍVEL CLASSE PRA CONTAR QUANTAS LINHAS TEM O FILE.TXT. TEM QUE MEXER.
        //    
        //    public int qntdLinhas(String arquivoPath) throws IOException{
        //        
        //        BufferedReader reader = new BufferedReader(new FileReader(arquivoPath));
        //            int lines = 0;
        //                while (reader.readLine() != null){
        //                    lines++;
        //                }
        //            reader.close();
        //            
        //            return lines;
        //    } 
        
        public static void main(String[] args) {
            
            ClassLoader clA = getClass().getClassLoader();
            File fileArestas = new File(clA.getResource("/ArestasFacebook_20171010214533.txt").getFile());

            ClassLoader clV = getClass().getClassLoader();
            File fileVertices = new File(clV.getResource("/VerticesFacebook_20171010214551.txt").getFile()); 

            
//            Path EdgePath = Paths.get("ArestasFacebook_20171010214533.txt");
//            Path VertexPath = Paths.get("VerticesFacebook_20171010214551.txt");         
//            
//            String CaminhoAresta = EdgePath.toString();
//            String CaminhoVertice = VertexPath.toString();
            
            int qntdArestas = 41161;
            int qntdVertices = 1919;
            
            String[] vetorArestas, vetorVertices;
            
                try {
                   vetorArestas = InicializarArquivo(fileArestas, qntdArestas);
                   vetorVertices = InicializarArquivo(fileVertices, qntdVertices);

                    for (int i=0; i<qntdVertices; i++){
                       System.out.println(vetorVertices[i]);
                    }    

                    System.out.println("\n\n\n");

                    for (int i=0; i<qntdArestas; i++){
                       System.out.println(vetorArestas[i]);
                    } 
                }
                catch (IOException arq){
                    System.out.println(arq.getMessage());
                }
            
        }
}