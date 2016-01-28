package read.cvs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

public class ReadCVS {
	
	  public static void main(String[] args) {

			ReadCVS obj = new ReadCVS();
			obj.run("C:/enem.csv");
	  }
	
	
	 public void run(String pathCSV) {

			String csvFile = pathCSV;
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ";";

			try {

				br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null) {
					String[] enemResult = line.split(cvsSplitBy);
					
					// IGNORA A PRIMEIRA LINHA DO CSV QUE NAO CONTEM VALORES, SO OS NOMES DAS COLUNAS
					if(enemResult[0].contains("inscricao") || enemResult[0].contains("cienciasDaNatureza") || enemResult[0].contains("matematica"))
						continue;
					
					/*System.out.println("Inscricao =" + enemResult[0] +
							" ; cienciasDaNatureza =" + enemResult[1] +
							" ; cienciasHumanas =" + enemResult[2] +
							" ; linguagensCodigos =" + enemResult[3] +
							" ; matematica =" + enemResult[4] +
							" ; redacao =" + enemResult[5]);*/
										
					float coluna_1 = Float.valueOf(enemResult[1]);
					float coluna_2 = Float.valueOf(enemResult[2]);
					float coluna_3 = Float.valueOf(enemResult[3]);
					float coluna_4 = Float.valueOf(enemResult[4]);
					float coluna_5 = Float.valueOf(enemResult[5]);
					
					double media = (coluna_1 + coluna_2 + coluna_3 + coluna_4 + coluna_5) / 5;
					
					//System.out.println("Média " + media);
				

				}
				
				adicionaColunaMedia();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			System.out.println("Sucesso!");
		  }
	 
	 public void adicionaColunaMedia() throws IOException{
		 CsvListReader reader;
		try {
			reader = new CsvListReader(new FileReader("C:/enem.csv"), CsvPreference.STANDARD_PREFERENCE);
			CsvListWriter writer = new CsvListWriter(new FileWriter("C:/Users/Marcio/Videos/saida.csv"), CsvPreference.STANDARD_PREFERENCE);
			 List<String> columns;
			 while ((columns = reader.read()) != null) {
			     //System.out.println("Input: " + columns);
			     // Add new columns
			     columns.add(1, "Media");
			     //columns.add("Last_column");

			     //System.out.println("Output: " + columns);
			     writer.write(columns);
			 }
			 reader.close();
			 writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }

}
