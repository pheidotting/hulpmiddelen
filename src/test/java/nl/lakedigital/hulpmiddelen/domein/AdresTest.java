package nl.lakedigital.hulpmiddelen.domein;

import static org.junit.Assert.fail;
import nl.lakedigital.hulpmiddelen.domein.Adres;
import nl.lakedigital.hulpmiddelen.exception.PostcodeNietGoedException;

import org.junit.Test;

public class AdresTest {

    @Test
    public void validateGoedGeval() throws PostcodeNietGoedException {
        Adres adres = new Adres();
        adres.setPostcode("1234AA");

        adres.validate();
    }

    @Test
    public void validateTeKort() {
        Adres adres = new Adres();
        adres.setPostcode("aa");

        try {
            adres.validate();
            fail("exception verwacht");
        } catch (PostcodeNietGoedException e) {
            // verwacht
        }
    }

    @Test
    public void validateNull() {
        Adres adres = new Adres();
        adres.setPostcode(null);

        try {
            adres.validate();
            fail("exception verwacht");
        } catch (PostcodeNietGoedException e) {
            // verwacht
        }
    }

    @Test
    public void validateTeLang() {
        Adres adres = new Adres();
        adres.setPostcode("aaaa123");

        try {
            adres.validate();
            fail("exception verwacht");
        } catch (PostcodeNietGoedException e) {
            // verwacht
        }
    }

    @Test
    public void validateVolledigNumeriek1() {
        Adres adres = new Adres();
        adres.setPostcode("123456");

        try {
            adres.validate();
            fail("exception verwacht");
        } catch (PostcodeNietGoedException e) {
            // verwacht
        }
    }

    @Test
    public void validateVolledigNumeriek2() {
        Adres adres = new Adres();
        adres.setPostcode("1234A6");

        try {
            adres.validate();
            fail("exception verwacht");
        } catch (PostcodeNietGoedException e) {
            // verwacht
        }
    }

    @Test
    public void validateVolledigAlfaNumeriek() {
        Adres adres = new Adres();
        adres.setPostcode("abcdef");

        try {
            adres.validate();
            fail("exception verwacht");
        } catch (PostcodeNietGoedException e) {
            // verwacht
        }
    }

}
