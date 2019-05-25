package top.gunplan.ric.provider.lib.services;

import top.gunplan.ric.apis.test.Find;

/**
 * @see top.gunplan.ric.apis.test.Find
 */
public class FindImpl implements Find {
    @Override
    public int findByBinaryDivide(int[] list, int target) {
        return findByBinaryDivide(list, list.length, target);
    }

    @Override
    public int findByOrder(int[] list, int target) {
        for (int i = 0; i < list.length; i++) {
            if (target == list[i]) {
                return i;
            }
        }
        return -1;
    }

    private int findByBinaryDivide(int[] list, int len, int target) {
        int left = 0, right = len - 1;
        int mid;
        //find in [legy,right]
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (list[mid] == target) {
                return mid;
            } else if (list[mid] < target) {
                left = mid + 1;
            } else if (list[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

}
