package exercise;

class SafetyList {
    // BEGIN
    private int[] arr = new int[10];
    private int size = 0;

    public synchronized void add(Integer element) {
        if (size >= arr.length) {
            int newLength = arr.length * 3 / 2 + 1;
            int[] newArr = new int[newLength];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            arr = newArr;
        }
        arr[size++] = element;
    }

    public Integer get(int index) {
        return arr[index];
    }

    public int getSize() {
        return size;
    }
    // END
}
