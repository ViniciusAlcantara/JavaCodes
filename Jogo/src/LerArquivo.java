

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LerArquivo {

	private final File file;
	private List<List<String>> listas;

	public LerArquivo(File file) {

		this.file = file;
		this.listas = new ArrayList<List<String>>();
		this.criaListadeLinhasdoArquivo();
	}

	private void criaListadeLinhasdoArquivo() {

		List<String> dataFile = getDataFile();
		for (int x = 0; x < dataFile.size(); x++) {

			if (dataFile.get(x).length() >= 3) {
				listas.add(splitLine(dataFile.get(x)));
			} else {
				listas.add(splitLine2(dataFile.get(x)));
			}

		}

	}

	/** Faz a leitura do arquivo e retorna os dados em uma lista */
	@SuppressWarnings("resource")
	public List<String> getDataFile() {

		List<String> data = new ArrayList<String>();
		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(file));

			try {

				while (br.ready()) {
					String line = br.readLine();
					data.add(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return data;
	}

	/** Separa cada linha do em arquivo em uma array de elementos */
	public List<String> splitLine(String line) {
		List<String> novo = new ArrayList<String>();
		String[] split = line.split(" ");

		if (split.length <= 0)
			return null;

		for (String string : split) {
			if (!string.isEmpty())
				novo.add(string);
		}

		return novo;
	}

	public List<String> splitLine2(String line) {
		List<String> novo = new ArrayList<String>();

		novo.add(line);

		return novo;
	}

	public List<List<String>> getListas() {
		return listas;
	}

}
