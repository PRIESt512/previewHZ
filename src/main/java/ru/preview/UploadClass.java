package ru.preview;

public class UploadClass {

    private final String a;
    private final String b;

    public UploadClass(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return a;
    }

    //TODO: убираешь коммит или наоборот и получаешь конфликт кеша HZ
//    public String getB() {
//        return b;
//    }
}
