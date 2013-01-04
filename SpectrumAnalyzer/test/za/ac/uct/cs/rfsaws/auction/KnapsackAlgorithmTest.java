/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.auction;

import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import za.ac.uct.cs.rfsaws.entities.Bid;
import za.ac.uct.cs.rfsaws.entities.Segment;

/**
 *
 * @author James
 */
public class KnapsackAlgorithmTest {

    public KnapsackAlgorithmTest() {
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
     * Test of bestFirstBranchAndBound method, of class KnapsackAlgorithm.
     */
    @Test
    public void testBestFirstBranchAndBound() {
        System.out.println("bestFirstBranchAndBound");


        List<Bid> bids = new LinkedList<Bid>();
        
       for(int i = 0; i < 200; i++){
           Bid b = new Bid();
           b.setBidValue((double)(int)(Math.random()*10+2));
           Segment s = new Segment();
           s.setBandWidth((double)(int)(Math.random()*10+2));
           b.setSegment(s);
           bids.add(b);
       }

        double W = 2000;
        KnapsackAlgorithm instance = new KnapsackAlgorithm(bids, W);
        //double expResult = 90.0;
        List<Bid> result = instance.bestFirstBranchAndBound();
        for(Bid b: result){
            System.out.println("V: " + b.getBidValue() + " W: " + b.getSegment().getBandWidth());
        }
 
    }
}
