package org.example.concurrent;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author buyu_6911
 * @version 2024/10/1 16:14
 * note:
 */
public class ForkJoinPoolTest extends RecursiveTask<Void> {
    private final int[] nums;
    private final int start;
    private final int end;
    private final Random random;

    public ForkJoinPoolTest(final int[] nums, final int start, final int end) {
        this.nums = nums;
        this.start = start;
        this.end = end;
        this.random = new Random();
    }

    @Override
    protected Void compute() {
        if (start >= end) {
            return null;
        }

        int pivot = sort();

        ForkJoinPoolTest left = new ForkJoinPoolTest(nums, start, pivot - 1);
        ForkJoinPoolTest right = new ForkJoinPoolTest(nums, pivot + 1, end);

        left.fork();
        right.compute();
        left.join();

        return null;
    }

    int sort() {
        int pivot = start + random.nextInt(end - start + 1);

        swap(end, pivot);
        int pointer = start;
        int cursor = start;

        while (cursor < end) {
            if (nums[cursor] < nums[end]) {
                swap(cursor, pointer);
                pointer++;
            }
            cursor++;
        }
        swap(end, pointer);
        return pointer;
    }

    void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

class Main {
    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool();
        int[] nums = new int[100];
        for (int i = 0; i < nums.length; i++) {
            nums[nums.length - i - 1] = i;
        }

        System.out.println(Arrays.toString(nums));
        fjp.invoke(new ForkJoinPoolTest(nums, 0, nums.length - 1));
        System.out.println(Arrays.toString(nums));
    }
}
