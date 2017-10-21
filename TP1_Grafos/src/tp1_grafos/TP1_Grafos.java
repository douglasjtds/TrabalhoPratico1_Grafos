package tp1_grafos;

import java.io.File;
import java.io.FileInputStream;
import sun.misc.IOUtils;

/**
 *
 * @author douglasjtds & yMetsBless
 */
public class TP1_Grafos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TÁ, E AGR?
        //UCYSDCHSDUHCSUDHCUSDUCHUISADCHIUSHDAIUCHSD
        
        String path = args[0];
        File CP_file = new File(path);
        path = "TrabalhoPratico1_Grafos\ArestasFacebook_20171010214533.txt";
        
        
        File fileToRead = new File(path);

        try( FileReader fileStream = new FileReader( fileToRead ); 
        BufferedReader bufferedReader = new BufferedReader( fileStream ) ) {

        String line = null;

        while( (line = bufferedReader.readLine()) != null ) {
            //do something with line
        }

        } catch ( FileNotFoundException ex ) {
            //exception Handling
        } catch ( IOException ex ) {
            //exception Handling
        }
        
        
        
        ///
        try(FileInputStream inputStream = new FileInputStream("TrabalhoPratico1_Grafos\ArestasFacebook_20171010214533.txt")) {     
            String everything = IOUtils.toString(inputStream);
            // do something with everything string
        } catch (Exception couldNotReadFileException){
            System.out.println("Arquivo não pôde ser lido.");
        }
        
    }
}