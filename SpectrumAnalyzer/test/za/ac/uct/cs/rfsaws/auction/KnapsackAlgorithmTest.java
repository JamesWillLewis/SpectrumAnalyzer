/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.auction;

import za.ac.uct.cs.rfsaws.util.KnapsackAlgorithm;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import za.ac.uct.cs.rfsaws.entities.BidEntity;
import za.ac.uct.cs.rfsaws.entities.SegmentEntity;

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


        List<BidEntity> bids = new LinkedList<BidEntity>();
        
       for(int i = 0; i < 4; i++){
           BidEntity b = new BidEntity();
           b.setBidValue((double)(int)(Math.random()*10+2));
           SegmentEntity s = new SegmentEntity();
           s.setBandWidth((double)(int)(Math.random()*10+2));
           b.setSegment(s);
           bids.add(b);
       }

        double W = 500;
        KnapsackAlgorithm instance = new KnapsackAlgorithm(bids, W,1);
        //double expResult = 90.0;
        List<BidEntity> result = instance.bestFirstBranchAndBound();
        for(BidEntity b: result){
            System.out.println("V: " + b.getBidValue() + " W: " + b.getSegment().getBandWidth());
        }
 
    }
}
