package aboutdb;

public class BookDetails {

    public BookDetails(String id, String name,
                       String title, float price,
                       int yr, String descp, int sale){
        this.id = id;
        this.name = name;
        this.title = title;
        this.price = price;
        this.yr = yr;
        this.descp = descp;
        this.sale = sale;
    }

    private String id;

    private String name;

    private String title;

    private float price;

    private int yr;

    private String descp;

    private int sale;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    public int getYr() {
        return yr;
    }

    public String getDescp() {
        return descp;
    }

    public int getSale() {
        return sale;
    }

    @Override
    public String toString(){
        return "id: " + id + "name: " + name + "title: " + title + "price: "
                + String.valueOf(price) + "YR: " + String.valueOf(yr) +
                "DESCP: " + descp + "sale: " + sale;
    }
}
