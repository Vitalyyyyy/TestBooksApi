public class BooksGenerator {
    public static Books getBookOnlyName(){
        return new Books("ASSDDF");
    }
    public static Books getBookThereIsNotName(){
        return new Books("ASSDDF",1256,true);
    }

    public static Books getBookEmptyName(){
        return new Books("");
    }
    public static Books getBookfullField(){
        return new Books("hghjn","hghjn",5765,true);
    }

}
