package io.kimo.savings.domain.model;

public class Saving {
    private String mImageUrl;
    private String mName;
    private String mTargetAmount;
    private String mCurrentAmount;

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String image) {
        mImageUrl = image;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getTargetAmount() {
        return mTargetAmount;
    }

    public void setTargetAmount(String targetAmount) {
        mTargetAmount = targetAmount;
    }

    public String getCurrentAmount() {
        return mCurrentAmount;
    }

    public void setCurrentAmount(String currentAmount) {
        mCurrentAmount = currentAmount;
    }

    @Override
    public String toString() {
        return "Saving{" +
                "mImageUrl='" + mImageUrl + '\'' +
                ", mName='" + mName + '\'' +
                ", mTargetAmount=" + mTargetAmount +
                ", mCurrentAmount=" + mCurrentAmount +
                '}';
    }
}
