package com.example.cse_5236_app.ui.dashboard;

import com.example.cse_5236_app.model.SearchApi;

public interface OnSearchListener {
    void onResponse(SearchApi response);
    void onError(String message);
}
