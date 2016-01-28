package read.cvs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCVS {
	
	  public static void main(String[] args) {

			ReadCVS obj = new ReadCVS();
			obj.run();
	  }
	
	
	 public void run() {

			String csvFile = "C:/enem.csv";
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ";";

			try {

				br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null) {

				        // use comma as separator
					String[] enemResult = line.split(cvsSplitBy);
					
					

					/*System.out.println("Inscricao =" + enemResult[0] +
							" ; cienciasDaNatureza =" + enemResult[1] +
							" ; cienciasHumanas =" + enemResult[2] +
							" ; linguagensCodigos =" + enemResult[3] +
							" ; matematica =" + enemResult[4] +
							" ; redacao =" + enemResult[5]);*/
					
					float coluna_1 = 0;
					float coluna_2 = 0;
					float coluna_3 = 0;
					float coluna_4 = 0;
					float coluna_5 = 0;
					
					coluna_1 = Float.valueOf(enemResult[1]);
					coluna_2 = Float.valueOf(enemResult[2]);
					coluna_3 = Float.valueOf(enemResult[3]);
					coluna_4 = Float.valueOf(enemResult[4]);
					coluna_5 = Float.valueOf(enemResult[5]);
					
					double media = (coluna_1 + coluna_2 + coluna_3 + coluna_4 + coluna_5) / 5;
					
					System.out.println("Média " + media);

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

			System.out.println("Done");
		  }

}
