package pl.umcs.oop.shop;

public class Product {
    private int id;
    private String nazwa;
    private double cena;

    public Product(int id, double cena, String nazwa) {
        this.id = id;
        this.cena = cena;
        this.nazwa = nazwa;
    }

}