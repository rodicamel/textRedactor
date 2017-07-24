

	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;

	class LireEcrireTexte {
	    public static void main(String[] arg) throws IOException {
		String fichierLecture = "Essai.txt";
		FileReader entree = new FileReader(fichierLecture);
		FileWriter sortie = new FileWriter("copie" + fichierLecture);
		int c;
		
		sortie.write("copie de " + fichierLecture + '\n');
		while((c = entree.read()) != -1) sortie.write(c);
		entree.close();
		sortie.close();
	 }
	
}
