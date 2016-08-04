package com.example.android.bangloreguide;

/**
 * Created by ryerajan on 13-07-2016.
 */
public class Guide {


    /** String resource ID for the Name of the Place */
    private int mNameId;

    /** String resource ID for the Miwok translation of the word */
    private int mAddressId;



    /** Image resource ID for the Place */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;

    public Guide(int Name, int Address, int ImageResourceId) {
        mNameId = Name;
        mAddressId = Address;
        mImageResourceId = ImageResourceId;
    }


    public Guide(int Name, int Address) {
        mNameId = Name;
        mAddressId = Address;

    }

    public int getNameId() {
        return mNameId;
    }

    /**
     * Get the string resource ID for the Miwok translation of the word.
     */
    public int getAddressId() {
        return mAddressId;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
