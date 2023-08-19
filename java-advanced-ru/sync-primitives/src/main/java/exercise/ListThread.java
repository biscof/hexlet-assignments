package exercise;

// BEGIN
class ListThread extends Thread {
    private SafetyList safetyList;

    public ListThread(SafetyList safetyList) {
        this.safetyList = safetyList;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 1000; i++) {
                sleep(1);
                safetyList.add(i);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
// END
