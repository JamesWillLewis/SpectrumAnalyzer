package za.ac.uct.cs.rfsaws.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import za.ac.uct.cs.rfsaws.entity.BidEntity;

/**
 * Highly optimized and tuned implementation of the Branch-and-Bound algorithm
 * with best-first-search to solve a knapsack problem - selecting the optimum
 * set of items from a larger set, that maximizes the total value and is within
 * the constraints of the maximum allowed weight(spectrum bandwidth). The tests
 * have shown that this implementation can perform within acceptable time on
 * hundreds of items, using randomly generated test samples.
 *
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
public class KnapsackAlgorithm {

    /**
     * Total number of weights(bids) to allocate.
     */
    private int numWeights;
    /**
     * Total weight (allocation spectrum width)
     */
    private double capacity;
    /**
     * Elements (bids) being allocated.
     */
    private Node[] elements;
    /**
     * Auction which is being resolved.
     */
    private long auctionID;

    /**
     * Initialize knapsack algorithm fields and wrap bids as nodes.
     *
     * @param bids Bids to allocate.
     * @param capacity Maximum weight.
     * @param auctionID Auction for this allocation.
     */
    public KnapsackAlgorithm(List<BidEntity> bids, double capacity, long auctionID) {
        if (bids.size() < 1) {
            throw new RuntimeException("List of bids for auction id=[" + auctionID + "] has 0 items.");
        } else {

            //initialization
            this.capacity = capacity;
            this.numWeights = bids.size();
            this.elements = new Node[numWeights];
            this.auctionID = auctionID;
            //convert bids to nodes
            for (int i = 0; i < numWeights; i++) {
                Node n = new Node();
                n.value = bids.get(i).getBidValue();
                n.weight = bids.get(i).getSegment().getBandWidth();
                n.bidRef = bids.get(i);
                n.ratio = n.value / n.weight;
                elements[i] = n;
            }
        }
    }

    /**
     * Node used for performing branch-and-bound
     * best-first search. The search tree is constructed of Nodes.
     * Each node maintains various numeric values, as well as 
     * wrapping it's BidEntity representation, and a list of ancestor (selected nodes).
     */
    public class Node implements Comparable<Node> {

        /**
         * Node depth.
         */
        int level;
        /**
         * Monetary value of node.
         */
        double value;
        /**
         * Spectrum (segment) width of node.
         */
        double weight;
        /**
         * Upper bound value.
         */
        double bound;
        /**
         * Ancestor (selected) nodes.
         */
        List<Node> items;
        /**
         * Wrapped BidEntity for future reference when allocating
         * winning bids.
         */
        BidEntity bidRef;
        /**
         * Ratio = value/weight
         */
        double ratio;

        /**
         * Initialize Node with default values.
         */
        private Node() {
            this.level = 0;
            this.value = 0.0;
            this.weight = 0.0;
            this.bound = 0.0;
            this.items = new ArrayList<Node>();
        }

        @Override
        public int compareTo(Node o) {
            if (this.bound < o.bound) {
                return -1;
            } else if (this.bound == o.bound) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    /**
     * For purpose of sorting elements in ratio order (value/weight).
     */
    private class WeightValueRatioComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            if (o1.ratio < o2.ratio) {
                return -1;
            } else if (o1.ratio == o2.ratio) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    /**
     * Sort Nodes in non-increasing order of ratio (value/weight).
     */
    private void sortInRatioOrder() {
        List<Node> sortElements = Arrays.asList(elements);
        Collections.sort(sortElements, new WeightValueRatioComparator());
        Collections.reverse(sortElements);
        elements = (Node[]) sortElements.toArray();
    }

    /**
     * Perform best-first-search branch-and-bound algorithm
     * to solve knapsack problem for the assigned elements.
     * 
     * @return List of bids which one a segment of the allocation.
     */
    public List<BidEntity> bestFirstBranchAndBound() {
        if (elements.length < 1) {
            throw new RuntimeException("List of bids for auction id=[" + auctionID + "] has 0 items.");
        } else {


            System.out.println("=====[Begin of auction [id=" + auctionID + "] allocation]=====");
            long startTime = System.currentTimeMillis();

            //sort the nodes in descending order of value-weight ratio.
            sortInRatioOrder();

            //declare variables
            PriorityQueue<Node> pq = new PriorityQueue<Node>();
            Node u, v;
            double maxValue = 0.0;
            List<Node> bestItems = new ArrayList<Node>();

            //create root node
            v = new Node();
            v.bound = bound(v);
            pq.offer(v);

            //if the search hasn't finished
            while (!pq.isEmpty()) {

                //grab next best-bound node
                v = pq.poll();

                if (v.bound > maxValue) {

                    //create node u for child which includes next node
                    u = new Node();
                    u.level = v.level + 1;
                    u.weight = v.weight + elements[u.level - 1].weight;
                    u.value = v.value + elements[u.level - 1].value;
                    u.bidRef = elements[u.level - 1].bidRef;


                    //if new max value has been found
                    if (u.weight <= capacity
                            && u.value > maxValue) {
                        //add parent and parents items to child
                        u.items.addAll(v.items);
                        //add included node
                        u.items.add(u);
                        maxValue = u.value;
                        bestItems = u.items;
                    }

                    u.bound = bound(u);

                    //add node to PQ (if promising)
                    if (u.bound > maxValue) {
                        //if u hasn't already had v items added
                        if (u.items.isEmpty()) {
                            //add parent and parents items to child
                            u.items.addAll(v.items);
                            //add included node
                            u.items.add(u);
                        }
                        pq.offer(u);
                    }

                    //create node u for child which doesn't includes next node
                    u = new Node();
                    u.level = v.level + 1;
                    u.value = v.value;
                    u.weight = v.weight;
                    u.bound = bound(u);

                    //add node to PQ (if promising)
                    if (u.bound > maxValue) {
                        //add parent and parent's items to child
                        u.items.addAll(v.items);
                        //add node to PQ
                        pq.offer(u);
                    }
                }
            }
            //bids which one a segment in the allocation
            List<BidEntity> winningBids = new LinkedList<BidEntity>();
            for (Node n : bestItems) {
                winningBids.add(n.bidRef);
            }

            //print statistics
            System.out.println("Auction [id=" + auctionID + "] computation complete...");
            System.out.println("Capacity            :   " + capacity + " MHz");
            System.out.println("Total value         :   " + "$" + maxValue);
            System.out.println("Total allocation    :   " + bestItems.get(bestItems.size() - 1).weight + " MHz");
            System.out.println("# of winning bids   :   " + bestItems.size());
            System.out.println("Computation time    :   ~" + (System.currentTimeMillis() - startTime) + " ms");
            System.out.println("=====[End of auction [id=" + auctionID + "] allocation]=====");

            return winningBids;
        }
    }

    /**
     * Upper-bound function/formula for determining promising nodes.
     *
     * @param n Node to determine bound value.
     * @return Upper-bound value.
     */
    private double bound(Node n) {
        int j;
        double totWeight;
        double result;

        if (n.weight >= capacity) {
            return 0;
        } else {
            result = n.value;
            j = n.level + 1;
            totWeight = n.weight;
            while (j <= numWeights && (totWeight + elements[j - 1].weight) <= capacity) {
                totWeight += elements[j - 1].weight;
                result += elements[j - 1].value;
                j++;
            }
            if (j <= numWeights) {
                result += (capacity - totWeight)
                        * (elements[j - 1].ratio);
            }
            return result;
        }
    }
}
