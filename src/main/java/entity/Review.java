package entity;


import java.io.Serializable;

public class Review implements Serializable {
    private int idClient;
    private int idAudioTrack;
    private String textReview;
    private String dateReview;

    public Review() {
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdAudioTrack() {
        return idAudioTrack;
    }

    public void setIdAudioTrack(int idAudioTrack) {
        this.idAudioTrack = idAudioTrack;
    }

    public String getTextReview() {
        return textReview;
    }

    public void setTextReview(String textReview) {
        this.textReview = textReview;
    }

    public String getDateReview() {
        return dateReview;
    }

    public void setDateReview(String dateReview) {
        this.dateReview = dateReview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (idClient != review.idClient) return false;
        if (idAudioTrack != review.idAudioTrack) return false;
        if (textReview != null ? !textReview.equals(review.textReview) : review.textReview != null) return false;
        return dateReview != null ? dateReview.equals(review.dateReview) : review.dateReview == null;
    }

    @Override
    public int hashCode() {
        int result = idClient;
        result = 31 * result + idAudioTrack;
        result = 31 * result + (textReview != null ? textReview.hashCode() : 0);
        result = 31 * result + (dateReview != null ? dateReview.hashCode() : 0);
        return result;
    }
}
