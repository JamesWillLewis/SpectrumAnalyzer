/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.auction;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author James
 */
public class KnapsackBandB {

    private int numWeights;
    private double[] values;
    private double[] weights;
    private double capacity;

    public KnapsackBandB(double[] values, double[] weights, double W) {
        this.numWeights = values.length;
        this.values = values;
        this.weights = weights;
        this.capacity = W;
    }

    private class Node implements Comparable<Node> {

        int level;
        double value;
        double weight;
        double bound;

        private Node() {
            this.level = 0;
            this.value = 0.0;
            this.weight = 0.0;
            this.bound = 0.0;
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

    private class Weight implements Comparable<Weight> {

        double weight;
        double value;
        double ratio;

        public Weight(double weight, double value) {
            this.weight = weight;
            this.value = value;
            this.ratio = value / weight;
        }

        @Override
        public int compareTo(Weight o) {
            if (ratio < o.ratio) {
                return -1;
            } else if (ratio == o.ratio) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    private void sortInRatioOrder() {
        List<Weight> sortWeights = new LinkedList<Weight>();
        for (int i = 0; i < numWeights; i++) {
            sortWeights.add(new Weight(weights[i], values[i]));
        }
        Collections.sort(sortWeights);
        Collections.reverse(sortWeights);
        Weight w;
        for (int i = 0; i < numWeights; i++) {
            w = sortWeights.get(i);
            weights[i] = w.weight;
            values[i] = w.value;
        }
    }

    public double bestFirstBranchAndBound() {
        sortInRatioOrder();

        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        Node u, v;
        double maxValue = 0.0;

        v = new Node();

        v.bound = bound(v);
        pq.offer(v);

        while (!pq.isEmpty()) {
            v = pq.poll();

            if (v.bound > maxValue) {

                u = new Node();
                u.level = v.level + 1;
                u.weight = v.weight + weights[u.level - 1];
                u.value = v.value + values[u.level - 1];

                if (u.weight <= capacity
                        && u.value > maxValue) {
                    maxValue = u.value;
                }
                u.bound = bound(u);
                if (u.bound > maxValue) {
                    pq.offer(u);
                }

                u = new Node();
                u.level = v.level + 1;
                u.value = v.value;
                u.weight = v.weight;
                u.bound = bound(u);
                if (u.bound > maxValue) {
                    pq.offer(u);
                }
            }

        }
        return maxValue;
    }

    private double bound(Node n) {
        int j, k;
        double totWeight;
        double result;

        if (n.weight >= capacity) {
            return 0;
        } else {
            result = n.value;
            j = n.level + 1;
            totWeight = n.weight;
            while (j <= numWeights && (totWeight + weights[j - 1]) <= capacity) {
                totWeight = totWeight + weights[j - 1];
                result = result + values[j - 1];
                j++;
            }
            k = j;
            if (k <= numWeights) {
                result = result + (capacity - totWeight)
                        * (values[k - 1] / weights[k - 1]);
            }
            return result;
        }
    }
}
