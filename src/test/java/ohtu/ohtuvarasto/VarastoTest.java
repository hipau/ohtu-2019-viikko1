package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto nollavarasto;
    Varasto alkusaldovarasto;
    Varasto nollaAlkusaldovarasto;
    Varasto negatiivinenAlkusaldovarasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        nollavarasto = new Varasto(0);
        alkusaldovarasto = new Varasto(10, 4);
        nollaAlkusaldovarasto = new Varasto(0, 2);
        negatiivinenAlkusaldovarasto = new Varasto(10, -2);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisaaTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiLisaa() {
        varasto.lisaaVarastoon(4);
        varasto.lisaaVarastoon(-1);
        
        assertEquals(4, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void liikalisaysTayttaa() {
        varasto.lisaaVarastoon(12);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void olemattomanVarastonLuominenOnnistuu() {
        assertEquals(0, nollavarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void olemattomanAlkusaldovarastonLuominenOnnistuu() {
        assertEquals(0, nollaAlkusaldovarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaAlkusaldovarastollaOikeaTilavuus() {
        assertEquals(10, alkusaldovarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudessaAlkusaldovarastossaOikeaMaaraTilaa() {
        assertEquals(6, alkusaldovarasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisenAlkusaldonVarastossaOikeaMaaraTilaa() {
        assertEquals(10, negatiivinenAlkusaldovarasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisenMaaranOttaminenEiMuutaSaldoa() {
        alkusaldovarasto.otaVarastosta(-2);
        assertEquals(4, alkusaldovarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ylimaaraisenOttaminenTyhjentaaVaraston() {
        alkusaldovarasto.otaVarastosta(6);
        assertEquals(0, alkusaldovarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringAntaaOikeatLuvut() {
        assertEquals("saldo = 4.0, vielä tilaa 6.0", alkusaldovarasto.toString());
    }

}