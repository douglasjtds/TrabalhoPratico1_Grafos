package tp1_grafos;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import java.util.Stack;

/**
 *
 * @author douglasjtds & yMetsBless
 */




//LEGENDA:
//AZUL: ANALISANDO ADJACENCIAS
//VERMELHO: NÃO VISITADO AINDA
//PRETO: FINALIZADO




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
    
    public static void PintarNo(Node no, String color){
 
        no.addAttribute("ui.style", "fill-color: " + color + ";");
        
        try{}
        catch(Exception exc){
           exc.printStackTrace();
        }
    }
    
    public static void PintarEdge(Edge Ed, String color){
    
        Ed.addAttribute("ui.style", "fill-color: " + color + ";");
        
        try{}
        catch(Exception exc){
           exc.printStackTrace();
        }
    }
 
    public static void BuscaProf(Node node, Stack<Node> pilhadeNos, Graph auxiliar, ArrayList<String> repetidos){     //FUNÇÃO PRA FAZER A BUSCA EM PROF 
 
//        node.setAttribute("ExaminandoAdjacencia");    //SETANDO O ATRIBUTO PARA "COR CINZA" = ANALISANDO AS ADJECENTES DO NÓ
//        node.addAttribute("ui.color", "blue");        
//        PintarNo(node, "blue");                       //PINTANDO DE AZUL
        
        pilhadeNos.push(node);                        //ADICIONA O NÓ NA PILHA
        
            Iterator<Node> nodesAdj = node.getNeighborNodeIterator();     //A VARIÁVEL nodesAdj RECEBE TODOS OS NÓS ADJACENTES DO NÓ ANALISADO NO MOMENTO PELO FOREACH
                
                while (nodesAdj.hasNext()) {                            //ENQUANTO TIVER NÓS ADJACENTES...
                    Node ndAdj = nodesAdj.next();                       //A VARIÁVEL NdAdj VAI RECEBENDO OS ADJACENTES DESSE NÓ
                    
                    if(ndAdj.hasAttribute("NaoVisitado") || ndAdj.getAttribute("ui.color") == "red"){          //SE ELE AINDA NÃO FOI VISITADO OU NÃO COMPLETOU TODA A LISTA DE ADJACENCIA
                        ndAdj.getEdgeBetween(node).setAttribute("Visitada");      //MARCA A ARESTA COMO VISITADA
                        ndAdj.addAttribute("ui.color", "yellow");            
                        PintarEdge(ndAdj.getEdgeBetween(node), "yellow");      //PINTA A ARESTA
                        
                        biggerComponent(auxiliar, node, ndAdj, repetidos);    //CHAMADA PRA FORMAR UM NOVO GRAFO COM A ÁRVORE
                        
                        BuscaProf(ndAdj, pilhadeNos, auxiliar, repetidos);
                        
                    }
                }
                pilhadeNos.pop();
                
            node.setAttribute("Visitado");    //MARCOU COM A "COR PRETA"
            node.addAttribute("ui.color", "black");  //LISTA DE ADJACENCIA TODA VISITADA! 
            PintarNo(node, "black");
    }
    
    public static void BuscaP(Graph G, Graph Maior, Graph aux, Stack<Node> pilhadeNos, ArrayList<String> repeated){
        Maior = new SingleGraph("O maior componente");
        
        for(Node n : G){     
            n.setAttribute("NaoVisitado");          //MARCAR CADA NÓ COMO NÃO VISITADO, MARCANDO COM A "COR BRANCA"
            n.addAttribute("ui.color", "red");      //DEIXANDO TODOS OS NÓS VERMELHOS
            PintarNo(n, "red");
        }
        
        for(Node n : G){ 
            if(n.hasAttribute("NaoVisitado") || n.getAttribute("ui.color") == "red"){      //SE O VÉRTICE N NÃO TIVER SIDO VISITADO
                pilhadeNos.removeAllElements();
                BuscaProf(n, pilhadeNos, aux, repeated);
                
                if(aux.getNodeCount() > Maior.getNodeCount()){      //Na primeira chamada já vai ser maior com certeza. Nas outras irá comparar o componente achado com o anterior. 
                    
                    Maior = aux;
                    aux = new SingleGraph("Grafo Aux");
                }
            }
        }
    } 
    
    
    
    public static void BuscaLargura(Node node, Queue<Node> FilaNo){        //FUNÇÃO BUSCA EM LARGURA
        node.setAttribute("ExaminandoAdjacencia");    //SETANDO O ATRIBUTO PARA "COR CINZA" = ANALISANDO AS ADJECENTES DO NÓ
        node.addAttribute("ui.color", "blue");        
        PintarNo(node, "blue");                       //PINTANDO DE AZUL
        
        
        int contador = 0;
        //int range = 0;
        
        node.addAttribute("comprimento", contador);
        FilaNo.add(node);
        
            while(!FilaNo.isEmpty()){    //EXECUTA ESSE WHILE ENQUANTO A PILHA NÃO ESVAZIAR
                Node aux = FilaNo.remove();
                aux.addAttribute("ui.color", "black");
                PintarNo(aux, "black");
                
                Iterator<Node> nodesAdj = aux.getNeighborNodeIterator();
                
                    if (nodesAdj.hasNext()){                    
                       contador = aux.getAttribute("comprimento");
                       contador++;          
                    } else {
                        contador = 0;
                    }
                   
                        while(nodesAdj.hasNext()){
                            Node ndAdj = nodesAdj.next();
                            
                                if(ndAdj.hasAttribute("NãoVisitado")){
                                    ndAdj.setAttribute("ExaminandoAdjacencia");
                                    ndAdj.addAttribute("ui.color", "blue");
                                    PintarNo(ndAdj, "blue");
                                    
                                    PintarEdge(aux.getEdgeBetween(ndAdj), "yellow");      //PINTA A ARESTA
                                    
                                    ndAdj.addAttribute("comprimento", contador);
                                    
                                    
                                    PintarNo(ndAdj, "blue");
                                    FilaNo.add(ndAdj);
                                    
                                }               
                        }
            }
    }
    
    public static void BuscaL(Graph grafo){        //FUNÇÃO BUSCA EM LARGURA
        
        for(Node n : grafo){
            n.setAttribute("NaoVisitado");
            n.addAttribute("ui.color", "red");
            PintarNo(n, "red");
        }
        
        for(Node n : grafo){
            Queue<Node> filadeNos = new LinkedList();
            
            if(n.hasAttribute("NaoVisitado") || n.getAttribute("ui.color").equals("")){
                BuscaLargura(n, filadeNos);
            }
        }
    }
    
    public static void biggerComponent(Graph aux, Node alfa, Node omega, ArrayList<String> repeated){   //PASSA OS DOIS NODES QUE ESTAVAM SENDO ANALISADOS NO METODO BUSCAPROF
        
        Boolean origin = false;
        Boolean destiny = false; 
        
        for (String nomeNo : repeated){     //Para todas as strings dentro da lista de nomes dos vertices
            if(nomeNo.equals(alfa.getId())){    //Se tiver qualquer um igual, vai receber true e não entra nos proximos ifs
                origin = true;
            }
            
            if(nomeNo.equals(omega.getId())){   //Se tiver qualquer um igual, vai receber true e não entra nos proximos ifs
                destiny = true;     
            }
        }
        
            if (origin.equals(false)){      //Se não tiver nenhum igual ao nó analisado
                aux.addNode(alfa.getId());  //Adiciona ele ao grafo da arvore
                repeated.add(alfa.getId());
            }
            
            if (destiny.equals(false)){     //Se não tiver nenhum igual ao nó adjacente
                aux.addNode(omega.getId());
                repeated.add(omega.getId());
            }
            
            if(destiny.equals(false) && origin.equals(false)){
                String nomeEdge = alfa.getId() + ";" + omega.getId();
                aux.addEdge(nomeEdge, omega, omega);
            }
        
    }

//          POSSÍVEL MÉTODO PRA CONTAR QUANTAS LINHAS TEM O ARQUIVO. TEM QUE MEXER NELA :
//
//            public int qntdLinhas(String arquivoPath) throws IOException{
//                
//                BufferedReader reader = new BufferedReader(new FileReader(arquivoPath));
//                    int lines = 0;
//                        while (reader.readLine() != null){
//                            lines++;
//                        }
//                    reader.close();
//                    
//                    return lines;
//            } 
    
    
    
        public static void main(String[] args) {
            Stack<Node> pilhadeNos = new Stack<>();
            Queue<Node> filadeNos = new LinkedList();
            
            ArrayList<String> repeateds = new ArrayList<>();        //ArrayList para conferir os nós repetidos na hora de formar a árvore
            
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
                
                
                //CRIANDO UM GRAFO DE TESTE           erro no teste tb :!
                Graph teste = new SingleGraph("Grafo Teste");
                teste.addNode("A");
                teste.addNode("B");
                teste.addNode("C");
                teste.addNode("D");
                teste.addNode("E");
                teste.addNode("F");
                
                teste.addEdge("AB", "A", "B", false);
                teste.addEdge("AC", "A", "C", false);
                teste.addEdge("BD", "B", "D", false);
                teste.addEdge("BE", "B", "E", false);
                teste.addEdge("CE", "C", "E", false);
                
                teste.addEdge("DE", "D", "E", false);
                teste.addEdge("DF", "D", "F", false);
                teste.addEdge("EF", "E", "F", false);
                
                
                
                
                Graph FaceGraph = new SingleGraph("Facebook Graph");    //GRAFO ORIGINAL
                Graph Auxiliar = new SingleGraph("Aux do BuscaProf");   //ARVORE GERADA NO BUSCAPROF
                Graph BiggerComponent = null;                           //Grafo do maior componente
                
                for(int i=0; i<qntdVertices; i++){      //Inseri os vértices
                    FaceGraph.addNode(vetorVertices[i]);
                }
                
//                PRINTA OS NODES PRA CONFERIR SE FORAM INSERIDOS
//                
//                for(Node n : FaceGraph.getEachNode()){
//                    System.out.println(n.getId());
//                }

                for(int i=0; i<qntdArestas; i++){       //INSERINDO ARESTAS
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

                    String css = "edge .notintree {size:1px;fill-color:red;} " +
                    "edge .intree {size:3px;fill-color:blue;}";

                    FaceGraph.addAttribute("ui.stylesheet", css);
                    Auxiliar.addAttribute("ui.stylesheet", css);

                    System.setProperty("org.graphstream.ui.renderer",
                    "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
                    
                    for(Node n : FaceGraph){     
                        n.setAttribute("NaoVisitado");          //MARCAR CADA NÓ COMO NÃO VISITADO, MARCANDO COM A "COR BRANCA"
                        n.addAttribute("ui.color", "red");      //DEIXANDO TODOS OS NÓS VERMELHOS
                        PintarNo(n, "red");
                    }
                    
//                    BuscaP(teste, BiggerComponent, Auxiliar, pilhadeNos, repeateds);
                      FaceGraph.display();          //Plotou o grafo original

          
 //                   BuscaL(BiggerComponent);
                    
//                    BiggerComponent.display();      //Plotou a árvore do maior componente

        }
}