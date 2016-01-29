package read.cvs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Media;

import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

public class ReadCVS {
	
	  public static void main(String[] args) {

			ReadCVS obj = new ReadCVS();
			try {
				obj.run("C:/enem.csv");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	
	
	 public void run(String pathCSV) throws IOException {
			String csvFile = pathCSV;
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ";";
			String[] result = null;
			double media = 0.0;
			
			List<Double> mediasCalculadas = new ArrayList<Double>();
			
			try {
				
				br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null) {
					result = line.split(cvsSplitBy);
					
					// IGNORA A PRIMEIRA LINHA DO CSV QUE NAO CONTEM VALORES, SO OS NOMES DAS COLUNAS
					if(result[0].contains("inscricao") || result[0].contains("cienciasDaNatureza") || result[0].contains("matematica"))
						continue;
					
							
					/*System.out.println("Inscricao =" + enemResult[0] +
							" ; cienciasDaNatureza =" + enemResult[1] +
							" ; cienciasHumanas =" + enemResult[2] +
							" ; linguagensCodigos =" + enemResult[3] +
							" ; matematica =" + enemResult[4] +
							" ; redacao =" + enemResult[5]);*/
										
					
					float coluna_1 = Float.valueOf(result[1]);
					float coluna_2 = Float.valueOf(result[2]);
					float coluna_3 = Float.valueOf(result[3]);
					float coluna_4 = Float.valueOf(result[4]);
					float coluna_5 = Float.valueOf(result[5]);
					
					media = (coluna_1 + coluna_2 + coluna_3 + coluna_4 + coluna_5) / 5;
					mediasCalculadas.add(media);
					System.out.println("Média " + media);
				}
				
				CsvListReader reader;
				try {
					reader = new CsvListReader(new FileReader("C:/enem.csv"), CsvPreference.STANDARD_PREFERENCE);
					CsvListWriter writer = new CsvListWriter(new FileWriter("C:/Users/Marcio/Videos/saida.csv"), CsvPreference.STANDARD_PREFERENCE);
					 List<String> columns;
					 int i = 0;
					 while ((columns = reader.read()) != null) {				 
					    // columns.add(1, "	 Media");
						 columns.add(1, "	  "+String.valueOf(mediasCalculadas.get(i)));
					     writer.write(columns);

					     i++;
					     System.out.println(i);
					 }
					
					 reader.close();
					 writer.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

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
			     columns.add(1, "	 Media");
			     writer.write(columns);
			 }
			 reader.close();
			 writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 
	 }

}
