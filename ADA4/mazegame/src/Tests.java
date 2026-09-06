package src;

import java.net.URL;

public class Tests {

    public boolean loadFile(String fileName){
        URL fileUrl = getClass().getResource("/images/");
        System.out.println(fileUrl.getPath());
        System.out.println(fileUrl.getPath());
        return fileUrl != null;
    }
    public String url (){
        return getClass().getClassLoader().getParent().toString();
    }
    public static void main(String[] args) {
        Tests t = new Tests();
        if(t.loadFile("/images/D.png")){
            System.out.println("Archivo encontrado");
        }else {
            System.out.println("Archivo no encontrado");
        }
        System.out.println(t.url());
    }
}
