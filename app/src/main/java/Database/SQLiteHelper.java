package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context){
        super(context,"hotelrefresh",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println("onCreate");

        db.execSQL("CREATE TABLE venue (\n" +
                "    ve_id   INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                    NOT NULL,\n" +
                "    name    VARCHAR,\n" +
                "    occ     INTEGER,\n" +
                "    area    VARCHAR,\n" +
                "    height  VARCHAR,\n" +
                "    img,\n" +
                "    details VARCHAR,\n" +
                "    details2 VARCHAR,\n" +
                "    status  INTEGER\n" +
                ");\n");

        db.execSQL("CREATE TABLE customer (\n" +
                "    cus_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name   VARCHAR,\n" +
                "    uname  VARCHAR,\n" +
                "    pword  VARCHAR,\n" +
                "    status INTEGER\n" +
                ");\n");

        db.execSQL("CREATE TABLE packagereservation (\n" +
                "    pr_id   INTEGER  PRIMARY KEY AUTOINCREMENT,\n" +
                "    cus_id  INTEGER  NOT NULL,\n" +
                "    pa_id   INTEGER  NOT NULL,\n" +
                "    ptime   DATETIME,\n" +
                "    pdate   DATETIME,\n" +
                "    pmethod VARCHAR,\n" +
                "    status  INTEGER\n" +
                ");\n");

        db.execSQL("CREATE TABLE package (\n" +
                "    pa_id  INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                   NOT NULL,\n" +
                "    ve_id  INTEGER NOT NULL,\n" +
                "    hours  INTEGER,\n" +
                "    price  DOUBLE,\n" +
                "    offer  DOUBLE,\n" +
                "    status INTEGER\n" +
                ");\n");

        System.out.println("Inserting Data..!");


        db.execSQL("INSERT INTO venue(name,occ,area,height,details,details2,status) VALUES ('Grand Ballroom 1','1300','9072','17.5','Upholding its reputation to the name,the grandest venues at NAME is located on the 3rd floor, with a panomaric view of the lake and beyond.the unique column free feature ensures the largest seating capacity for a minimum of 350 guests.','The ballroom is the ideal venue to host large weddings, coparate events such as seminars,conventions.Your personal coordinator together with the award winning culinary team will address your individual requirements to make sure you and your guest satisfied.',1)");
        db.execSQL("INSERT INTO venue(name,occ,area,height,details,details2,status) VALUES ('View 2','300','3626','9.5','A stunning location fit for royality.This venues was also used by the president of Sri Lanka as an official venue for the commonwealth heads of government meeting held in Sri Lanka.','A stunning location fit for royality.This venues was also used by the president of Sri Lanka as an official venue for the commonwealth heads of government meeting held in Sri Lanka. 180 degrees of breathtaking outdoor beauty together with elegant interior and rich chadeliers male this venue perfect for special occasions.',1)");
        db.execSQL("INSERT INTO venue(name,occ,area,height,details,details2,status) VALUES ('Falsetto 3','60','115','10.8','Falsetto is the ideal venue to host your private parties ans get-together.','You  can indulge in the delicious signature dishes and enjoy the breathtaking view at the dining area while you sing your heart out',1)");
        db.execSQL("INSERT INTO venue(name,occ,area,height,details,details2,status) VALUES ('Grand Lawn 4','100','1500','9.5','The Grand lawn situated at the waters edge is one of the most serene locations. Perfect for large events, weddings, corporate functions, out bound trainings and retreates.','Acres of luxurious green pastures running along serene waterways, the lawn creates a picturesque venue not only for large concerts, events and weddings but also for intimate dinners and gatherings',1)");
        db.execSQL("INSERT INTO venue(name,occ,area,height,details,details2,status) VALUES ('Manel 5','300','3675','9','Manel is a perfect space for intimate gatherings corporate or leisure.','Located on the ground floor, these three venues can be utilized as one or divided into three separate sites. Completely sound proof with separate lobby, these three venues provide the ideal tranquil setting for small seminars, meetings and trainings',1)");
        db.execSQL("INSERT INTO venue(name,occ,area,height,details,details2,status) VALUES ('Float 6','40','250','9' ,'The float is our floating venue perfect for intimate gatherings, anniversaries, proposals etc.','Imagine dining under the moonlight sky surrounded by natural waterways and nature most melancholy tunes. The float provides one of the most romantic venues in the country, perfect for intimate social gatherings. Our skilled banquet coordinators together with the hotels eseteemed chefs are geared to make any event of your choice a memorable one',1)");
//        db.execSQL("INSERT INTO venue(name,occ,area,height,details,details2,status) VALUES ('Hall 7','800',7000,4,'Upholding its reputation to the name,the grandest venues at NAME is located on the 3rd floor, with a panomaric view of the lake and beyond.the unique column free feature ensures the largest seating capacity for aminimum of xxx guests.','Details2',1)");

        db.execSQL("INSERT INTO customer(name,uname,pword,status) VALUES ('Asel Kalmith','kalmith1212@gmail.com','1212',1)");
        db.execSQL("INSERT INTO customer(name,uname,pword,status) VALUES ('Chammika Lakshan','chammilak@gmail.com','1234',1)");
        db.execSQL("INSERT INTO customer(name,uname,pword,status) VALUES ('Lakshan chamindu','Lakshan@gmail.com','1254',1)");
        db.execSQL("INSERT INTO customer(name,uname,pword,status) VALUES ('Nuwanthi apsara','apsara@gmail.com','1286',1)");
        db.execSQL("INSERT INTO customer(name,uname,pword,status) VALUES ('Geeth shashi','Geeth@gmail.com','1987',1)");


        System.out.println("Inserted");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        System.out.println("onUpgrade");
    }
}
