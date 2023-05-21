package semestr2;
public class SegmentTree {
    private int array[];
    public int count = 0;
    public SegmentTree(int a[], int size) {
        int h = (int) (Math.ceil(Math.log(size) / Math.log(2)));
        int maxSize = 2 * (int) Math.pow(2, h) - 1;
        array = new int[maxSize];
        constructST(a, 0, size - 1, 0);
    }
    private int getMidIndex(int f, int l) {
        count++;
        return f + (l - f) / 2;
    }
    private int getSumUtil(int x, int y, int i, int j, int si) {
        if (i <= x && j >= y) {
            count++;
            return array[si];
        }
        if (y < i || x > j) {
            count++;
            return 0;
        }
        count++;
        int midVal = getMidIndex(x, y);
        return getSumUtil(x, midVal, i, j, 2 * si + 1) + getSumUtil(midVal + 1, y, i, j, 2 * si + 2);
    }
    private void updateValUtil(int x, int y, int j, int val, int si) {
        if (j < x || j > y) {
            count++;
            return;
        }
        count++;
        array[si] = array[si] + val;
        if (y != x) {
            count++;
            int midVal = getMidIndex(x, y);
            updateValUtil(x, midVal, j, val, 2 * si + 1);
            updateValUtil(midVal + 1, y, j, val, 2 * si + 2);
        }
    }
    public void updateVal(int a[], int size, int index, int newVal) {
        if (index < 0 || index > size - 1) {
            count++;
            System.out.println("Input is Invalid");
            return;
        }
        count++;
        int diffVal = newVal - a[index];
        array[index] = newVal;
        updateValUtil(0, size - 1, index, diffVal, 0);
    }

    public int getSum(int size, int x, int y) {
        if (x < 0 || x > size - 1 || x > y) {
            count++;
            System.out.println("The input is invalid");
            return -1;
        }
        count++;
        return getSumUtil(0, size - 1, x, y, 0);
    }
    private int constructST(int a[], int x, int y, int si) {
        if (x == y) {
            count++;
            array[si] = a[x];
            return a[x];
        }
        count++;
        int mid = getMidIndex(x, y);
        array[si] = constructST(a, x, mid, si * 2 + 1) + constructST(a, mid + 1, y, si * 2 + 2);
        return array[si];
    }

    public void deleteVal(int size, int index) {
        if (index < 0 || index > size - 1) {
            count++;
            System.out.println("Input is Invalid");
            return;
        }
        count++;
        updateVal(array,size, index, 0);
    }

    public void insertVal(int a[], int size, int index, int newVal) {
        if (index < 0 || index > size - 1) {
            count++;
            System.out.println("Input is Invalid");
            return;
        }
        count++;
        a[index] = newVal;
        updateVal(a, size, index, newVal);
    }

   public int searchVal(int size, int val) {
        for (int i = 0; i < size; i++) {
            count++;
            if (array[i] == val) {
                count++;
                return i;
            }
        }
        count++;
        System.out.println("Value not found");
        return -1;
    }
    public int getCount(){
        return count;
    }

}