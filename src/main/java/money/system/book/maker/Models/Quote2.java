package money.system.book.maker.Models;




public class Quote2 {
    public  String name;
    public  String content;
    public  int id;



    public Quote2(String name, String content, int id) {
        this.name = name;
        this.content = content;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

