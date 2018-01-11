package entity;


import java.io.Serializable;

public class AudioTrack implements Serializable {
    private int idAudioTrack;
    private int idArtist;
    private String nameAudioTrack;
    private double priceAudioTrack;
    private int idAlbum;
    private String nameArtist;

    public AudioTrack() {
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
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

    public String getNameAudioTrack() {
        return nameAudioTrack;
    }

    public void setNameAudioTrack(String nameAudioTrack) {
        this.nameAudioTrack = nameAudioTrack;
    }

    public double getPriceAudioTrack() {
        return priceAudioTrack;
    }

    public void setPriceAudioTrack(double priceAudioTrack) {
        this.priceAudioTrack = priceAudioTrack;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AudioTrack that = (AudioTrack) o;

        if (idAudioTrack != that.idAudioTrack) return false;
        if (idArtist != that.idArtist) return false;
        if (Double.compare(that.priceAudioTrack, priceAudioTrack) != 0) return false;
        if (idAlbum != that.idAlbum) return false;
        return nameAudioTrack != null ? nameAudioTrack.equals(that.nameAudioTrack) : that.nameAudioTrack == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idAudioTrack;
        result = 31 * result + idArtist;
        result = 31 * result + (nameAudioTrack != null ? nameAudioTrack.hashCode() : 0);
        temp = Double.doubleToLongBits(priceAudioTrack);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + idAlbum;
        return result;
    }

    @Override
    public String toString() {
        return "AudioTrack{" +
                "idAudioTrack=" + idAudioTrack +
                ", idArtist=" + idArtist +
                ", nameAudioTrack='" + nameAudioTrack + '\'' +
                ", priceAudioTrack=" + priceAudioTrack +
                ", idAlbum=" + idAlbum +
                ", nameArtist='" + nameArtist + '\'' +
                '}';
    }
}
