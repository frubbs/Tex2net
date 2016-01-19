	import java.io.IOException;
	import java.nio.file.Files;
	import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class GeraListaNomes {



	    public static void main(String[] args) throws IOException {
	        List<String> lista1 = Files.readAllLines(Paths.get("C:\\Users\\rafa\\Desktop\\nomes.txt"));
	        lista1.remove(3);
	        List<String> lista2 = Files.readAllLines(Paths.get("C:\\Users\\rafa\\Desktop\\names.lst"));
	        List<String> newList = new ArrayList<String>(lista1);
	        newList.addAll(lista2);
	        
	        
	     // add elements to al, including duplicates
	     Set<String> hs = new HashSet<>();
	     hs.addAll(newList);
	     newList.clear();
	     newList.addAll(hs);
	        System.out.println(newList.toString().replace(", ", "\n"));
	    }

}
