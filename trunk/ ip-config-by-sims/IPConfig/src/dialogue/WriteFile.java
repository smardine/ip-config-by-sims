package dialogue;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class WriteFile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Ecrire le contenu d'un String dans un fichier
	 * @param contenu -String ce qu'il faut ecrire
	 * @param chemin -String le chemin du fichier
	 */

	public WriteFile(String contenu, String chemin) {

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(chemin));
			out.write(contenu);
			out.close();
		} catch (IOException e) {
		}
	}

	/**
	 * Ecrire une ligne dans un fichier
	 * @param ligneAEcrire -String ce qu'il faut ecrire
	 * @param CheminDuFichier -String le chemin du fichier
	 */
	public static void WriteLine(String ligneAEcrire, String CheminDuFichier) {
		FileWriter writer = null;
		String texte = (ligneAEcrire + "\n");

		try {
			writer = new FileWriter(CheminDuFichier, true);
			writer.write(texte, 0, texte.length());

		} catch (IOException ex) {
			System.out.println("IO exception");
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					System.out.println("impossible de fermer le fichier");
				}
			}
		}
	}

	/**
	 * Ecrire une ligne dans un fichier
	 * @param ligneAEcrire -String ce qu'il faut ecrire
	 * @param CheminDuFichier -String le chemin du fichier
	 */
	public static void WriteList(ArrayList<String> ListeEcrire,
			String CheminDuFichier) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(CheminDuFichier, false);
		} catch (IOException e1) {
			System.out.println("IO Exception");
		}

		for (String ligne : ListeEcrire) {
			String texte = (ligne + "\n");
			try {
				writer.write(texte, 0, texte.length());
			} catch (IOException ex) {
				System.out.println("IO exception");
			}
		}
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				System.out.println("impossible de fermer le fichier");
			}
		}
	}

	public static void WriteLineVector(Vector<String> ligneAEcrire,
			String CheminDuFichier) throws IOException {
		FileWriter writer = null;
		String texte = (ligneAEcrire + "\n");

		try {
			writer = new FileWriter(CheminDuFichier, true);
			writer.write(texte, 0, texte.length());

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

}
