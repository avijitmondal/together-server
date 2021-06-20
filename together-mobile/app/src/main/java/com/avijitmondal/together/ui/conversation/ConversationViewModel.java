package com.avijitmondal.together.ui.conversation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConversationViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ConversationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}