package org.techtown.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleData implements Parcelable {

    int number;
    String message;

    public SimpleData(int num, String msg){
        number = num;
        message = msg;
    }

    public SimpleData(Parcel src){//Parcel 객체에서 읽기
        number = src.readInt();
        message = src.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        public SimpleData createFromParcel(Parcel in){
            return new SimpleData(in);//SimpleData 생성자를 호출해 Parcel 객체에서 읽기
        }

        public SimpleData[] newArray(int size){
            return new SimpleData[size];
        }
    };

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(number);
        dest.writeString(message);
    }
}
