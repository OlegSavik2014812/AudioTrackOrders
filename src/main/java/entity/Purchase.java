package entity;

import java.io.Serializable;

public class Purchase implements Serializable {
    private double pricePurchase;
    private int idClient;
    private int idAudioTrack;
    private String datePurchase;
    private String nameAudioTrack;
    private String nameClient;


    public Purchase() {
    }

    public String getNameAudioTrack() {
        return nameAudioTrack;
    }

    public void setNameAudioTrack(String nameAudioTrack) {
        this.nameAudioTrack = nameAudioTrack;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public double getPricePurchase() {
        return pricePurchase;
    }

    public void setPricePurchase(double pricePurchase) {
        this.pricePurchase = pricePurchase;
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

    public String getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(String datePurchase) {
        this.datePurchase = datePurchase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Purchase purchase = (Purchase) o;

        if (Double.compare(purchase.pricePurchase, pricePurchase) != 0) return false;
        if (idClient != purchase.idClient) return false;
        if (idAudioTrack != purchase.idAudioTrack) return false;
        if (datePurchase != null ? !datePurchase.equals(purchase.datePurchase) : purchase.datePurchase != null)
            return false;
        if (nameAudioTrack != null ? !nameAudioTrack.equals(purchase.nameAudioTrack) : purchase.nameAudioTrack != null)
            return false;
        return nameClient != null ? nameClient.equals(purchase.nameClient) : purchase.nameClient == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(pricePurchase);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + idClient;
        result = 31 * result + idAudioTrack;
        result = 31 * result + (datePurchase != null ? datePurchase.hashCode() : 0);
        result = 31 * result + (nameAudioTrack != null ? nameAudioTrack.hashCode() : 0);
        result = 31 * result + (nameClient != null ? nameClient.hashCode() : 0);
        return result;
    }
}
