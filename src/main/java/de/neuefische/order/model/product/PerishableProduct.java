package de.neuefische.order.model.product;

import java.time.LocalDate;
import java.util.Objects;

public class PerishableProduct implements Product {

    private String id;
    private String name;
    private LocalDate bestBefore;

    public PerishableProduct(String id, String name, LocalDate bestBefore) {
        this.id = id;
        this.name = name;
        this.bestBefore = bestBefore;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(LocalDate bestBefore) {
        this.bestBefore = bestBefore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PerishableProduct)) return false;
        PerishableProduct that = (PerishableProduct) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(bestBefore, that.bestBefore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), bestBefore);
    }

    @Override
    public String toString() {
        return "PerishableProduct{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", bestBefore=" + bestBefore +
                '}';
    }
}
