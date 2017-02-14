package cinta.tipsmemikatwanita.trick.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Cerita implements Parcelable {

    public String id;
    public String title;
    public String content;
    public String type;
    public String createdAt;
    public String image;
    public String sumber;
    public String category;
    public String person;


    public Cerita() {}

    public Cerita(Parcel in) {
        id              = in.readString();
        title           = in.readString();
        content         = in.readString();
        type            = in.readString();
        createdAt       = in.readString();
        image           = in.readString();
        sumber          = in.readString();
        category        = in.readString();
        person          = in.readString();
    }

    public static final Creator<Cerita> CREATOR = new Creator<Cerita>() {

        @Override
        public Cerita createFromParcel(Parcel in) {

            return new Cerita(in);
        }

        @Override
        public Cerita[] newArray(int size) {

            return new Cerita[size];
        }

    };

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {

        out.writeString(id);
        out.writeString(title);
        out.writeString(content);
        out.writeString(type);
        out.writeString(createdAt);
        out.writeString(image);
        out.writeString(sumber);
        out.writeString(category);
        out.writeString(person);
    }
}
