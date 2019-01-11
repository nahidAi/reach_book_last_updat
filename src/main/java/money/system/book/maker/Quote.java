package money.system.book.maker;



public class Quote {
    public  String name;
    public  String body;
    public  int fav;
    public  String image;
    public  int free;
    public  int id;

    public Quote(String name, String body, int fav, String image,int free, int id) {
        this.name = name;
        this.body = body;
        this.fav = fav;
        this.image = image;
        this.free = free;
        this.id = id;
    }

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

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
