/**
 * @file BlackJackTest.java
 * @brief ブラックジャックテストクラス
 * @author Keigo Nakada
 * @data 2020.01.20
 *
 */
package jp.ac.uryukyu.ie.e195714;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackTest {

    @Test
    void toNumber() {
        BlackJack blackJack = new BlackJack();
        assertEquals(2, blackJack.toNumber(28));
    }
}