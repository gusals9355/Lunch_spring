package com.koreait.lunchproject1;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Lunchproject1ApplicationTests {

    @Test
    void contextLoads() {
        String hashPw="$2a$10$Pq0MJ3U/ZhkPvUM7bbYbfO5C4p1wdXiSeiT5YNdn3gb8yMDXOgOhi";
        String pw = "123123123";

        System.out.println(BCrypt.checkpw(pw,hashPw));
    }

}
