package com.example.mrityunjay.websewrviceagainpractice.Network;

/**
 * Created by Mrityunjay on 08-08-2017.
 */

import com.example.mrityunjay.websewrviceagainpractice.Utils.CommonUtilities;

public  interface OnWebServiceResult {
    public void getWebResponse(String result, CommonUtilities.SERVICE_TYPE type);
}

