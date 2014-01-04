package nl.lakedigital.hulpmiddelen.domein;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import nl.lakedigital.hulpmiddelen.exception.PostcodeNietGoedException;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class Adres implements Serializable {
    private static final long serialVersionUID = 2361944992062349932L;
    private static final int MAXLENGTE = 6;
    private static final int DEELEEN = 4;

    @Column(name = "STRAAT")
    private String straat;
    @Column(name = "HUISNUMMER")
    private Long huisnummer;
    @Column(name = "TOEVOEGING")
    private String toevoeging;
    @Column(length = 6, name = "POSTCODE")
    private String postcode;
    @Column(name = "PLAATS")
    private String plaats;

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public Long getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(Long huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getToevoeging() {
        return toevoeging;
    }

    public void setToevoeging(String toevoeging) {
        this.toevoeging = toevoeging;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        if (postcode != null) {
            this.postcode = postcode.trim().replace(" ", "");
        }
    }

    public String getPlaats() {
        return plaats;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public void validate() throws PostcodeNietGoedException {
        checkNietNullEnLengte(postcode);

        String deelEen = postcode.substring(0, DEELEEN);
        String deelTwee = postcode.substring(DEELEEN, MAXLENGTE);

        // Geeft wel een fout als e.e.a. niet numeriek is
        try {
            Long.parseLong(deelEen);
        } catch (NumberFormatException e) {
            throw new PostcodeNietGoedException(postcode);
        }
        for (int i = 0; i < 2; i++) {
            try {
                Long.parseLong(deelTwee.substring(i, (i + 1)));
                throw new PostcodeNietGoedException(postcode);
            } catch (NumberFormatException e) {
                // dit is verwacht, want deeltwee moet alfanumeriek zijn
            }
        }
    }

    private void checkNietNullEnLengte(String postcode) throws PostcodeNietGoedException {
        if (postcode == null) {
            throw new PostcodeNietGoedException("");
        }
        if (postcode.length() < MAXLENGTE) {
            throw new PostcodeNietGoedException(postcode);
        }
        if (postcode.length() > MAXLENGTE) {
            throw new PostcodeNietGoedException(postcode);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Adres [straat=");
        builder.append(straat);
        builder.append(", huisnummer=");
        builder.append(huisnummer);
        builder.append(", toevoeging=");
        builder.append(toevoeging);
        builder.append(", postcode=");
        builder.append(postcode);
        builder.append(", plaats=");
        builder.append(plaats);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(huisnummer).append(plaats).append(postcode).append(straat).append(toevoeging).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Adres other = (Adres) obj;
        return new EqualsBuilder().append(huisnummer, other.huisnummer).append(plaats, other.plaats).append(postcode, other.postcode).append(straat, other.straat).append(toevoeging, other.toevoeging)
                .isEquals();
    }
}
