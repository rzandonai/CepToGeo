package br.com.rzandonai.geo.VO;

import java.lang.reflect.Field;

public class GeoVO {

	private String cep;
	private String logradouro;
	private String nomeLogradouro;
	private String latitude;
	private String longitude;
	private String bairro;
	private String municipio;
	private String uf;
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNomeLogradouro() {
		return nomeLogradouro;
	}
	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	
	
    @Override
    public String toString()
    {
    	StringBuilder sb = new StringBuilder();
    	
    	Field[] lista = this.getClass().getDeclaredFields();
    	for (Field field : lista) {
    		try {
				sb.append(field.getName()+": "+field.get(this)+"\n");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
        return sb.toString();
    }
}
