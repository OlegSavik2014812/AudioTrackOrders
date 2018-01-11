package entity;


import java.io.Serializable;

public class Artist implements Serializable {
    private int idArtist;
    private String nameArtist;

    public Artist() {
    }

    public int getIdArtist() {
        return idArtist;
    }

    public void setIdArtist(int idArtist) {
        this.idArtist = idArtist;
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        if (idArtist != artist.idArtist) return false;
        return nameArtist != null ? nameArtist.equals(artist.nameArtist) : artist.nameArtist == null;
    }

    @Override
    public int hashCode() {
        int result = idArtist;
        result = 31 * result + (nameArtist != null ? nameArtist.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "idArtist=" + idArtist +
                ", nameArtist='" + nameArtist + '\'' +
                '}';
    }
}
