package top.gunplan.ric.provider.lib.services;

import top.gunplan.ric.apis.test.ListQuestion;

/**
 * @author dosdrtt
 * @see top.gunplan.ric.apis.test.ListQuestion
 */
public class ListQuestionImpl implements ListQuestion {

    @Override
    public int[] moveZero(int[] list) {
        return moveVal(list, 0);
    }

    @Override
    public Result27 removeVal(int[] list, int target) {
        int v = moveValWithFill(list, target, 0);
        return new Result27(v, list);
    }

    @Override
    public int removeRepetition(int[] list) {

        return 1;
    }

    private int[] moveVal(int[] list, int target) {
        moveValWithFill(list, target, target);
        return list;
    }

    private int moveValWithFill(int[] list, int target, int fill) {
        int k = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] == target) {
                k = i;
                break;
            }
        }
        for (int i = k + 1; i < list.length; i++) {
            if (list[i] != target) {
                list[k++] = list[i];
            }
        }
        for (int i = k; i < list.length; i++) {
            list[i] = fill;
        }
        return k - 1;
    }
}
