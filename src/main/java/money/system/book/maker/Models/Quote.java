package money.system.book.maker.Models;

public class Quote {
    public  String name;
    public  String body;
    public  int fav;
    public  String image;
    public  boolean isFree;
    public  int id;

   /* public Quote(String name, String body, int fav, String image,boolean free, int id) {
        this.name = name;
        this.body = body;
        this.fav = fav;
        this.image = image;
        this.isFree = free;
        this.id = id;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean getFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        this.isFree = free;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
