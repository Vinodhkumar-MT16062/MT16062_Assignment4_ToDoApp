package vinodhkumar.mt16062_todoapp;

/**
 * Created by VinodhKumar on 06/11/16.
 */

public class DataObject {

    private String mTitle;
    private String mDetail;

    DataObject (String title, String detail){
        mTitle = title;
        mDetail = detail;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDetail() {
        return mDetail;
    }

    public void setmDetail(String mDetail) {
        this.mDetail = mDetail;
    }
}
