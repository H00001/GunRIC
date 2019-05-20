package top.gunplan.RIC.center;


import java.io.IOException;

public class LoadServerTest {
    public static void main(String[] args) {
        try {
            GunRicRegisterManage.loadRegister();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void loadServer() {
        try {
            GunRicRegisterManage.loadRegister();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
