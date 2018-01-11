package entity;

import java.io.Serializable;

public class Package implements Serializable {
    private int idPackage;
    private String namePackage;

    public Package() {
    }

    public int getIdPackage() {
        return idPackage;
    }

    public void setIdPackage(int idPackage) {
        this.idPackage = idPackage;
    }

    public String getNamePackage() {
        return namePackage;
    }

    public void setNamePackage(String namePackage) {
        this.namePackage = namePackage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Package aPackage = (Package) o;

        if (idPackage != aPackage.idPackage) return false;
        return namePackage != null ? namePackage.equals(aPackage.namePackage) : aPackage.namePackage == null;
    }

    @Override
    public int hashCode() {
        int result = idPackage;
        result = 31 * result + (namePackage != null ? namePackage.hashCode() : 0);
        return result;
    }
}
