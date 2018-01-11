package entity;

import java.io.Serializable;

public class Client implements Serializable {
    private int idClient;
    private String firstNameClient;
    private String lastNameClient;
    private String loginClient;
    private String passwordClient;
    private double bonusClient;
    private int roleClient;
    private double balance;

    public Client() {
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getFirstNameClient() {
        return firstNameClient;
    }

    public void setFirstNameClient(String firstNameClient) {
        this.firstNameClient = firstNameClient;
    }

    public String getLastNameClient() {
        return lastNameClient;
    }

    public void setLastNameClient(String lastNameClient) {
        this.lastNameClient = lastNameClient;
    }

    public String getLoginClient() {
        return loginClient;
    }

    public void setLoginClient(String loginClient) {
        this.loginClient = loginClient;
    }

    public String getPasswordClient() {
        return passwordClient;
    }

    public void setPasswordClient(String passwordClient) {
        this.passwordClient = passwordClient;
    }

    public double getBonusClient() {
        return bonusClient;
    }

    public void setBonusClient(double bonusClient) {
        this.bonusClient = bonusClient;
    }

    public int getRoleClient() {
        return roleClient;
    }

    public void setRoleClient(int roleClient) {
        this.roleClient = roleClient;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalanceClient(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (idClient != client.idClient) return false;
        if (Double.compare(client.bonusClient, bonusClient) != 0) return false;
        if (roleClient != client.roleClient) return false;
        if (Double.compare(client.balance, balance) != 0) return false;
        if (firstNameClient != null ? !firstNameClient.equals(client.firstNameClient) : client.firstNameClient != null)
            return false;
        if (lastNameClient != null ? !lastNameClient.equals(client.lastNameClient) : client.lastNameClient != null)
            return false;
        if (loginClient != null ? !loginClient.equals(client.loginClient) : client.loginClient != null) return false;
        return passwordClient != null ? passwordClient.equals(client.passwordClient) : client.passwordClient == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idClient;
        result = 31 * result + (firstNameClient != null ? firstNameClient.hashCode() : 0);
        result = 31 * result + (lastNameClient != null ? lastNameClient.hashCode() : 0);
        result = 31 * result + (loginClient != null ? loginClient.hashCode() : 0);
        result = 31 * result + (passwordClient != null ? passwordClient.hashCode() : 0);
        temp = Double.doubleToLongBits(bonusClient);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + roleClient;
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", firstNameClient='" + firstNameClient + '\'' +
                ", lastNameClient='" + lastNameClient + '\'' +
                ", loginClient='" + loginClient + '\'' +
                ", passwordClient='" + passwordClient + '\'' +
                ", bonusClient=" + bonusClient +
                ", roleClient=" + roleClient +
                ", balance=" + balance +
                '}';
    }
}

