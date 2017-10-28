package tp1_grafos;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import java.util.Stack;

/**
 *
 * @author douglasjtds & yMetsBless
 */

public class TP1_Grafos {
    
    public static String[] InicializarArquivo(String caminho, int linhasArquivo) throws IOException{
        
        String[] vetorPreenchido = new String[linhasArquivo];

        FileReader arquivoInicial = new FileReader(caminho);
        BufferedReader LeitorTxt = new BufferedReader(arquivoInicial);

        for (int i = 0; i < linhasArquivo; i++) {
            vetorPreenchido[i] = LeitorTxt.readLine();
        }

        arquivoInicial.close();
        return vetorPreenchido;      
    }
    
 
    public static void BuscaProf(Node Nó){     //FUNÇÃO PRA FAZER A BUSCA EM PROF
        Stack<Node> pilhadeNós = new Stack<>();
        
        Nó.setAttribute("ExaminandoAdjacencia");    //SETANDO O ATRIBUTO PARA "COR CINZA" = ANALISANDO AS ADJECENTES DO NÓ
        pilhadeNós.push(Nó);        //ADICIONA O NÓ NA PILHA
        
            Iterator<Node> nodesAdj = Nó.getNeighborNodeIterator();     //A VARIÁVEL nodesAdj RECEBE TODOS OS NÓS ADJACENTES DO NÓ ANALISADO NO MOMENTO PELO FOREACH
                while (nodesAdj.hasNext()) {        //ENQUANTO TIVER NÓS ADJACENTES...
                    Node ndAdj = nodesAdj.next();       //A VARIÁVEL NdAdj VAI RECEBENDO OS ADJACENTES DESSE NÓ
                    
                    if(ndAdj.hasAttribute("NaoVisitado")){      //SE ELE AINDA NÃO FOI VISITADO
                        
                        //MARCA A ARESTA ENTRE O NÓ PASSADO COMO O ARGUMENTO DA FUNÇÃO E O ndAdj
                        BuscaProf(ndAdj);
                        
                    }
                }
        Nó.setAttribute("Visitado");    //MARCOU COM A "COR PRETA"
    }

    
    
    public static void Busca(Graph G){
        
        for(Node n : G.getEachNode()){     //NÃO SEI SE REALMENTE PRECISA DESSE FOREACH, MAS TO SEGUINDO O SLIDE DA JAQUELINE
            n.setAttribute("NaoVisitado"); //MARCAR CADA NÓ COMO NÃO VISITADO(TENTANDO ESSE MÉTODO, NÃO SEI SE VAI DAR CERTO) MARCANDO COM A "COR BRANCA"
        }
        
        for(Node n : G.getEachNode()){ 
            if(n.hasAttribute("NaoVisitado")){
                BuscaProf(n);
            }
        }
    }

        //      POSSÍVEL MÉTODO PRA CONTAR QUANTAS LINHAS TEM O ARQUIVO. TEM QUE MEXER NELA :/* */
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
            
            Path EdgePath = Paths.get("src\\ArestasFacebook_20171010214533.txt");
            Path VertexPath = Paths.get("src\\VerticesFacebook_20171010214551.txt");         
            
            String CaminhoAresta = EdgePath.toString();
            String CaminhoVertice = VertexPath.toString();

            int qntdArestas = 41161;
            int qntdVertices = 1919;
            
            String[] vetorArestas = null, vetorVertices = null;
            
                try {
                   vetorArestas = InicializarArquivo(CaminhoAresta, qntdArestas);
                   vetorVertices = InicializarArquivo(CaminhoVertice, qntdVertices);

//                      PRINTA OS VETORES DE ARESTAS E DE VERTICES
//                   
////                    for (int i=0; i<qntdVertices; i++){
////                       System.out.println(vetorVertices[i]);
////                    }    
////
////                    System.out.println("\n\n\n");
////
////                    for (int i=0; i<qntdArestas; i++){
////                       System.out.println(vetorArestas[i]);
////                    } 
                }
                catch (IOException arq){
                    System.out.println("ERRO AQUI Ó: " + arq.getMessage());
                }
                
                
                Graph FaceGraph = new SingleGraph("Facebook Graph");    //Criei o grafo
                
                for(int i=0; i<qntdVertices; i++){      //Inseri os vértices
                    FaceGraph.addNode(vetorVertices[i]);
                }
                
//                PRINTA OS NODES PRA CONFERIR SE FORAM INSERIDOS
//                
//                for(Node n : FaceGraph.getEachNode()){
//                    System.out.println(n.getId());
//                }

                for(int i=0; i<qntdArestas; i++){       //Inseri arestas
                    String[] parts = vetorArestas[i].split(";");
                    String parte1 = parts[0];
                    String parte2 = parts[1];
                    
                    FaceGraph.addEdge(vetorArestas[i], parte1, parte2, false);
                }

//                PRINTA AS ARESTAS PRA CONFERIR SE FORAM INSERIDAS
//                
//                for (Edge e : FaceGraph.getEachEdge()) {
//                    System.out.println(e.getId());
//                }
            
                    System.setProperty("org.graphstream.ui.renderer",
                    "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
                    
                    FaceGraph.display();        //Plotou o grafo
                    
                    /*FaceGraph*/
        }
}