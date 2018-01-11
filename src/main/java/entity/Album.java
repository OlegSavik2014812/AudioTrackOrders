package entity;

import java.io.Serializable;

public class Album implements Serializable {
    private int idAlbum;
    private String nameAlbum;
    private int idArtist;
    public Album() {
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public int getIdArtist() {
        return idArtist;
    }

    public void setIdArtist(int idArtist) {
        this.idArtist = idArtist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        if (idAlbum != album.idAlbum) return false;
        if (idArtist != album.idArtist) return false;
        return nameAlbum != null ? nameAlbum.equals(album.nameAlbum) : album.nameAlbum == null;
    }

    @Override
    public int hashCode() {
        int result = idAlbum;
        result = 31 * result + (nameAlbum != null ? nameAlbum.hashCode() : 0);
        result = 31 * result + idArtist;
        return result;
    }
}
