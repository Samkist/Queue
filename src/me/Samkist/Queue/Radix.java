package me.Samkist.Queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class Radix {
    private ArrayList<Queue<String>> radixQueues = new ArrayList<>();
    private Double max;

    public Radix() {
        for(int i = 0; i < 10; i++) {
            radixQueues.add(new Queue<>());
        }
    }

    public void sort(ArrayList<String> nums) {
        findMax(nums);
        zeroFormat(nums);
        int iterations = Double.toString(max).length();
        for (int i = 1; i < iterations + 1; i++) {
            int finalI = i;
            radixQueues.forEach(q -> {
                Iterator<String> it = q.iterator();
                while (it.hasNext()) {
                    String n = it.next();
                    if(radixIndex(n, finalI) >= 0) {
                        n = q.dequeue();
                        radixQueues.get(radixIndex(n, finalI)).enqueue(n);
                    }
                }
            });
        }
        populateList(nums);
    }

    private void findMax(ArrayList<String> nums) {
        max = Double.parseDouble(nums.get(0));
        nums.forEach(s -> {
            if(Double.parseDouble(s) > max) {
                max = Double.parseDouble(s);
            }
        });
    }

    private int radixIndex(String n, int offset) {
        String s = Character.toString(n.charAt(n.length()-offset));
        if(s.equalsIgnoreCase("."))
            return -1;
        return Integer.parseInt(s);
    }

    private void zeroFormat(ArrayList<String> nums) {
        String maxStr = Double.toString(max);
        nums.forEach(n -> {
            StringBuffer buf = new StringBuffer(Double.parseDouble(n) + "");
            for(int i = 0; i < maxStr.length()-n.length(); i++) {
                buf.insert(0, "0");
            }
            n = buf.toString();
            if(radixIndex(n, 1) >= 0)
                radixQueues.get(radixIndex(n, 1)).enqueue(n);
        });
    }

    private void populateList(ArrayList<String> nums) {
        AtomicInteger index = new AtomicInteger(0);
        radixQueues.forEach(q -> q.forEach(n -> nums.set(index.getAndIncrement(), Double.toString(Double.parseDouble(n)))));
    }
}
