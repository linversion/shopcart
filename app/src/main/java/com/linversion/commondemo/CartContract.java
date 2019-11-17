package com.linversion.commondemo;

import java.util.List;

public interface CartContract {
    interface View extends BaseView<Presenter> {
        void getListSuccess(List<bean> response);
    }

    interface Presenter{
        void getDataList();
    }
}
