package com.example.test2;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CountViewModel extends ViewModel {

    public MutableLiveData<Integer> count = new MutableLiveData<>();

    public CountViewModel() {
        count.setValue(0);
    }

    public void setPlusCount() {
        count.postValue(count.getValue() + 1);
    }

    public void initCount(){
        count.postValue(0);
    }
}
