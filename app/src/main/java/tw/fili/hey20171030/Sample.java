package tw.fili.hey20171030;

public class Sample {
    private String Name, Detail;
    private int Price;

    //建構式
    // Sample x = new Sample();
    Sample(){
    }

    //建構式
    // x = new Sample("鉛筆",10,"相關說明");
    Sample(String name, int price, String detail){
        Name = name;
        Price = price;
        Detail = detail;
    }

    //----------------------------------------------
    //存取方法 get/set
    public String getName() {
        return Name;
    }

    public int getPrice() {
        return Price;
    }

    public String getDetail() {
        return Detail;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }
}
