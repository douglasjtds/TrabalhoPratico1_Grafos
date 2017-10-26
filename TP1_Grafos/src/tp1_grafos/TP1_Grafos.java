package tp1_grafos;

//import java.io.File;
//import java.io.FileInputStream;
//import sun.misc.IOUtils;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author douglasjtds & yMetsBless
 */
public class TP1_Grafos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
//              CÓDIGO DOUGLAS

//        String path = args[0];
//        File CP_file = new File(path);
//        path = "TrabalhoPratico1_Grafos\ArestasFacebook_20171010214533.txt";
//        
//        
//        File fileToRead = new File(path);
//
//        try( FileReader fileStream = new FileReader( fileToRead ); 
//        BufferedReader bufferedReader = new BufferedReader( fileStream ) ) {
//
//        String line = null;
//
//        while( (line = bufferedReader.readLine()) != null ) {
//            //do something with line
//        }
//
//        } catch ( FileNotFoundException ex ) {
//            //exception Handling
//        } catch ( IOException ex ) {
//            //exception Handling
//        }
//        
//        
//        
//        ///
//        try(FileInputStream inputStream = new FileInputStream("TrabalhoPratico1_Grafos\ArestasFacebook_20171010214533.txt")) {     
//            String everything = IOUtils.toString(inputStream);
//            // do something with everything string
//        } catch (Exception couldNotReadFileException){
//            System.out.println("Arquivo não pôde ser lido.");
//        }
 
           String EdgePath = "TrabalhoPratico1_Grafos\\ArestasFacebook_20171010214533.txt";
           String VertexPath = "TrabalhoPratico1_Grafos\\VerticesFacebook_20171010214551.txt";
           
           int qntdArestas = 41161;
           int qntdVertices = 1919;
           Arquivo teste = new Arquivo(EdgePath, VertexPath);
            
//            teste.InicializarArquivo(EdgePath, qntdArestas);      ERRO DE TRY CATCH
//            teste.InicializarArquivo(VertexPath, qntdVertices);   ERRO DE TRY CATCH
    }
}