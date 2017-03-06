package br.com.rzandonai.geo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import br.com.rzandonai.geo.VO.GeoVO;

public class CepToGeo {

	private byte[] enc = "aHR0cDovL3d3dy5tYXBhY2VwLmNvbS5ici9kZWZhdWx0LmFzcD9idXNjYS1jZXA9".getBytes();

	public GeoVO parse(String cep) {

		cep = cep.trim().replaceAll("[^0-9]", "");
		
		byte[] valueDecoded= Base64.getDecoder().decode(enc );
		
		String url = new String(valueDecoded)+ cep;
		Document doc2 = null;
		try {
			doc2 = Jsoup.connect(url).header("Accept-Encoding", "gzip, deflate")
					.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
					.maxBodySize(0).timeout(1000000).get();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
		Elements elements = doc2.getElementsByClass("resultado-buscacep");
		String resultado = elements.text().trim();
		
		if(!resultado.contains("CEP:")){
			return null;
		}
		GeoVO geo = new GeoVO();
		
		geo.setCep(cep);
		
		for (String atributo : atributos()) {
			Integer iniAt =resultado.indexOf(atributo);
			Integer endAt = iniAt+atributo.length();
			//pego o proximo ; e pego o ultimo espaco antes
			String proximo = resultado.substring(endAt);		
			Integer lastp = proximo.indexOf(":");
			String p2p = proximo.substring(0, lastp);
			String valor ="";
			if(p2p.contains("Nome Logradouro:")){
				valor = proximo.substring(0,p2p.length()-16);
			}else{
				Integer ultimoEspaco = p2p.lastIndexOf(" ");
				valor = p2p.substring(0,ultimoEspaco);
			}
			
			setaValor(geo,atributo,valor);
			
			System.out.println(geo.toString());
			
		}
		return geo;
	}
	
	private void setaValor(GeoVO geo , String atributo, String valor){
		if(atributo.equals("CEP:")){
			geo.setCep(valor);
		}else if (atributo.equals("Logradouro:")) {
			geo.setLogradouro(valor);
		}else if (atributo.equals("Nome Logradouro:")) {
			geo.setNomeLogradouro(valor);
		}
		else if (atributo.equals("Latitude:")) {
			geo.setLatitude(valor);
		}
		else if (atributo.equals("Longitude:")) {
			geo.setLongitude(valor);
		}
		else if (atributo.equals("Bairro:")) {
			geo.setBairro(valor);
		}
		else if (atributo.equals("Municipio:")) {
			geo.setMunicipio(valor);
		}
		else if (atributo.equals("UF:")) {
			geo.setUf(valor);
		}
	}

	private List<String> atributos() {
		List<String> atributos = new ArrayList<String>();
		atributos.add("CEP:");
		atributos.add("Logradouro:");
		atributos.add("Nome Logradouro:");
		atributos.add("Latitude:");
		atributos.add("Longitude:");
		atributos.add("Bairro:");
		atributos.add("Municipio:");
		atributos.add("UF:");
		return atributos;
	};

	// Decode data on other side, by processing encoded data

}
