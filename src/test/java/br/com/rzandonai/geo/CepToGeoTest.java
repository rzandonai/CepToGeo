package br.com.rzandonai.geo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.rzandonai.geo.VO.GeoVO;

public class CepToGeoTest {
	@Test public void testSomeLibraryMethod() {
        CepToGeo cepToGeo = new CepToGeo();
        //ibpq curitiba
        String cep = "80210-350";

        GeoVO retorno = cepToGeo.parse("80210-350");
        assertEquals(retorno.getCep(),cep);
    }
}
