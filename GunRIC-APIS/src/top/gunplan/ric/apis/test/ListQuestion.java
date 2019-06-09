package top.gunplan.ric.apis.test;

import top.gunplan.ric.apis.test.anno.GunUseImpl;

import java.io.Serializable;

/**
 * @author dosdrtt
 */
@GunUseImpl(impl = "top.gunplan.ric.provider.lib.services.ListQuestionImpl")
public interface ListQuestion {
    /**
     * moveZero
     *
     * @param list input
     * @return result
     */
    int[] moveZero(int[] list);

    /**
     * lc 27
     */
    Result27 removeVal(int[] list, int target);

    /**
     * lc 26
     */
    int removeRepetition(int[] list);


    class Result27 implements Serializable {
        private static final long serialVersionUID = -2108217578648220404L;
        private int lastnum;
        private int[] list;

        public Result27(int lastnum, int[] list) {
            this.lastnum = lastnum;
            this.list = list;
        }

        public int getLastnum() {
            return lastnum;
        }

        public void setLastnum(int lastnum) {
            this.lastnum = lastnum;
        }

        public int[] getList() {
            return list;
        }

        public void setList(int[] list) {
            this.list = list;
        }
    }
}
