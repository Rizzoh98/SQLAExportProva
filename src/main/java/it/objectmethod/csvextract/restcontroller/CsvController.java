package it.objectmethod.csvextract.restcontroller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CsvController {

	@CrossOrigin
	@PostMapping("/get/details/csv")
	public MultipartFile getDetailsCsv(@RequestParam(value ="file", required = true) MultipartFile file,
			@RequestParam(value ="NUM_FOUCOM", required = false) String NUM_FOUCOM, @RequestParam(value ="NUM_ART", required = false) String NUM_ART,
			@RequestParam(value ="COD_EAN", required = false) String COD_EAN, @RequestParam(value ="LIB_ART", required = false) String LIB_ART,
			@RequestParam(value ="COD_GAMART", required = false) String COD_GAMART, @RequestParam(value ="NEG", required = false) String NEG,
			@RequestParam(value ="QTA_STK", required = false) String QTA_STK, @RequestParam(value ="PRX_ACHFOU", required = false) String PRX_ACHFOU)
			throws Exception {
		BufferedReader br;
		String path = "D:/Progetti Eclipse/csvextract/risultati.txt";
		File filetxt = new File(path);
		filetxt.createNewFile();
		FileWriter fw=new FileWriter(filetxt);
		BufferedWriter bw = new BufferedWriter(fw);
		try {
			String line;
			List <String> valori = new ArrayList <String>();
			List <Integer> colonne = new ArrayList <Integer>();
			
			if(NUM_FOUCOM != null && !NUM_FOUCOM.equals("")){
				colonne.add(0);
				valori.add(NUM_FOUCOM);
				}
			if(NUM_ART != null && !NUM_ART.equals("")){
				colonne.add(1);
				valori.add(NUM_ART);
				}
			if(COD_EAN != null && !COD_EAN.equals("")){
				colonne.add(2);
				valori.add(COD_EAN);
				}
			if(LIB_ART != null && !LIB_ART.equals("")){
				colonne.add(3);
				valori.add(LIB_ART);
				}
			if(COD_GAMART != null && !COD_GAMART.equals("")){
				colonne.add(4);
				valori.add(COD_GAMART);
				}
			if(NEG != null && !NEG.equals("")){
				colonne.add(5);
				valori.add(NEG);
				}
			if(QTA_STK != null && !QTA_STK.equals("")){
				colonne.add(6);
				valori.add(QTA_STK);
				}
			if(PRX_ACHFOU != null && !PRX_ACHFOU.equals("")){
				colonne.add(7);
				valori.add(PRX_ACHFOU);
				}
			
			InputStream is = file.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			line=br.readLine();
			bw.write(line + "\n");
			System.out.println(line);
			while ((line = br.readLine()) != null) {
				
				String [] data = line.split("	");
				int crits=0;
				for(int index=0; index < colonne.size();) {
					int i=colonne.get(index);
					
					if(data[i].equals(valori.get(index))) {
						crits ++;
					}
					index++;	
				}
				
				if(crits == colonne.size()) {
					System.out.println(line);
					bw.write(line + "\n");
				}
				
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		bw.flush();
		bw.close();
		return null;
	}
	
}
