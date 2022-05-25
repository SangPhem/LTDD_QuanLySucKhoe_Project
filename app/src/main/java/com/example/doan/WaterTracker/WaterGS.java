package com.example.doan.WaterTracker;

public class WaterGS {
    int progress, progressrev;

    public WaterGS(int progress, int progressrev) {
        this.progress = progress;
        this.progressrev = progressrev;
    }
    public WaterGS() {

    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getProgressrev() {
        return progressrev;
    }

    public void setProgressrev(int progressrev) {
        this.progressrev = progressrev;
    }
}
