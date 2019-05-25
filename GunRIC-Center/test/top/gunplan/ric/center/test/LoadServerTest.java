package top.gunplan.ric.center.test;

import org.junit.jupiter.api.Test;
import top.gunplan.ric.center.GunRicRegisterManage;

import java.io.IOException;

public class LoadServerTest {
    public static void main(String[] args) {
        try {
            GunRicRegisterManage.loadRegister();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void loadServer() {
        try {
            GunRicRegisterManage.loadRegister();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
