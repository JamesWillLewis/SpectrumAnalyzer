/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.auction;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author James
 */
public class KnapsackBandBTest {

    public KnapsackBandBTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of bestFirstBranchAndBound method, of class KnapsackBandB.
     */
    @Test
    public void testBestFirstBranchAndBound() {
        System.out.println("bestFirstBranchAndBound");
        double[] values = {4, 3, 2, 1};
        double[] weights = {1,2,3,4};
        double W = 5.0;
        KnapsackBandB instance = new KnapsackBandB(values, weights, W);
        double expResult = 7.0;
        double result = instance.bestFirstBranchAndBound();
        assertEquals(expResult, result, 0.0);
    }
    
    
}
