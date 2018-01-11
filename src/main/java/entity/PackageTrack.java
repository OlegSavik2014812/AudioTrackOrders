package entity;

import java.io.Serializable;

public class PackageTrack implements Serializable {
    private int idPackage;
    private int idAudioTrack;
    private int idArtist;
    private String namePackage;
    private String nameAudioTrack;
    private String nameArtist;

    public PackageTrack() {
    }

    public String getNamePackage() {
        return namePackage;
    }

    public void setNamePackage(String namePackage) {
        this.namePackage = namePackage;
    }

    public String getNameAudioTrack() {
        return nameAudioTrack;
    }

    public void setNameAudioTrack(String nameAudioTrack) {
        this.nameAudioTrack = nameAudioTrack;
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    public int getIdPackage() {
        return idPackage;
    }

    public void setIdPackage(int idPackage) {
        this.idPackage = idPackage;
    }

    public int getIdAudioTrack() {
        return idAudioTrack;
    }

    public void setIdAudioTrack(int idAudioTrack) {
        this.idAudioTrack = idAudioTrack;
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

        PackageTrack that = (PackageTrack) o;

        if (idPackage != that.idPackage) return false;
        if (idAudioTrack != that.idAudioTrack) return false;
        if (idArtist != that.idArtist) return false;
        if (namePackage != null ? !namePackage.equals(that.namePackage) : that.namePackage != null) return false;
        if (nameAudioTrack != null ? !nameAudioTrack.equals(that.nameAudioTrack) : that.nameAudioTrack != null)
            return false;
        return nameArtist != null ? nameArtist.equals(that.nameArtist) : that.nameArtist == null;
    }

    @Override
    public int hashCode() {
        int result = idPackage;
        result = 31 * result + idAudioTrack;
        result = 31 * result + idArtist;
        result = 31 * result + (namePackage != null ? namePackage.hashCode() : 0);
        result = 31 * result + (nameAudioTrack != null ? nameAudioTrack.hashCode() : 0);
        result = 31 * result + (nameArtist != null ? nameArtist.hashCode() : 0);
        return result;
    }
}
