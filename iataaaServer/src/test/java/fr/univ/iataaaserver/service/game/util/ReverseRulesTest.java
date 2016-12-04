/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.iataaaserver.service.game.util;

import fr.univ.iataaaserver.IataaaServerApp;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IataaaServerApp.class)

public class ReverseRulesTest {
    @Test
    public void getAllTopRightCornerPositionTest() {
        int i = 2;
        List<Integer> res = new ArrayList<>();
        res.add(7);
        res.add(13);
        res.add(18);
        res.add(24);
        res.add(29);
        
        List<Integer> test = ReverseRules.getAllTopRightCornerPosition(i);
        assertThat(test).isEqualTo(res);
    }
    
    @Test
    public void getAllBottomLeftCornerPositionTest() {
        int i = 14;
        List<Integer> res = new ArrayList<>();
        res.add(8);
        res.add(3);
        
        List<Integer> test = ReverseRules.getAllBottomLeftCornerPosition(i);
        assertThat(test).isEqualTo(res);
    }
    
    @Test
    public void getAllBottomRightCornerPositionTest() {
        int i = 23;
        List<Integer> res = new ArrayList<>();
        res.add(18);
        res.add(14);
        res.add(9);
        
        List<Integer> test = ReverseRules.getAllBottomRightCornerPosition(i);
        assertThat(test).isEqualTo(res);
    }
    
    @Test
    public void getAllBottomRightCornerPositionReturnEmptyTest() {
        int i = 19;
        List<Integer> res = new ArrayList<>();
        
        List<Integer> test = ReverseRules.getAllBottomRightCornerPosition(i);
        assertThat(test).isEqualTo(res);
    }
    
    @Test
    public void getTopLeftCornerPositionAfterJumpTest1() {
        int src = 5;
        int result = ReverseRules.getTopLeftCornerPosition(src, 2);
        
        assertThat(result).isEqualTo(-1);
    }
    
    @Test
    public void getTopLeftCornerPositionAfterJumpTest2() {
        int src = 19;
        int result = ReverseRules.getTopLeftCornerPosition(src, 2);
        
        assertThat(result).isEqualTo(28);
    }
    
    @Test
    public void getTopRightCornerPositionAfterJumpTest1() {
        int src = 5;
        int result = ReverseRules.getTopRightCornerPosition(src, 2);
        
        assertThat(result).isEqualTo(16);
    }
    
    @Test
    public void getTopRightCornerPositionAfterJumpTest2() {
        int src = 19;
        int result = ReverseRules.getTopRightCornerPosition(src, 2);
        
        assertThat(result).isEqualTo(-1);
    }
    
    @Test
    public void getBottomLeftCornerPositionAfterJumpTest1() {
        int src = 5;
        int result = ReverseRules.getBottomLeftCornerPosition(src, 2);
        
        assertThat(result).isEqualTo(-1);
    }
    
    @Test
    public void getBottomLeftCornerPositionAfterJumpTest2() {
        int src = 19;
        int result = ReverseRules.getBottomLeftCornerPosition(src, 2);
        
        assertThat(result).isEqualTo(8);
    }
    
    @Test
    public void getBottomRightCornerPositionAfterJumpTest1() {
        int src = 5;
        int result = ReverseRules.getBottomRightCornerPosition(src, 2);
        
        assertThat(result).isEqualTo(-1);
    }
    
    @Test
    public void getBottomRightCornerPositionAfterJumpTest2() {
        int src = 17;
        int result = ReverseRules.getBottomRightCornerPosition(src, 2);
        
        assertThat(result).isEqualTo(8);
    } 
}
