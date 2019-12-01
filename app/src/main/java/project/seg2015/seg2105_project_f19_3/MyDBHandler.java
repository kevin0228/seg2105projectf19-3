package project.seg2015.seg2105_project_f19_3;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;


public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "serviceDB.db";
    public static final String TABLE_SERVICES = "services";
    public static final String TABLE_USERS = "users";
    public static final String TABLE_EMPLOYEE = "employeeprofiles";
    public static final String TABLE_BOOK = "books";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SERVICENAME = "servicename";
    public static final String COLUMN_SKU = "cost";
    public static final String COLUMN_ROLE = "role";

    public static final String COLUMN_ACCOUNT = "account";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_TYPE = "type";

    public static final String COLUMN_EMPLOYEE_ACCOUNT = "account";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_CLINIC_NAME = "clinic_name";
    public static final String COLUMN_INSURANCE_TYPE = "insurance_types";
    public static final String COLUMN_PAYMENT_METHOD = "payment_methods";
    public static final String COLUMN_SERVICES_OFFERED = "services_offered";
    public static final String COLUMN_START_TIME = "start_time";
    public static final String COLUMN_END_TIME = "end_time";

    public static final String COLUMN_CLINIC_ACCOUNT_BOOK = "clinic_account";
    public static final String COLUMN_PATIENT_ACCOUNT_BOOK = "patient_account";
    Context context;
    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_SERVICES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_SERVICENAME
                + " TEXT," + COLUMN_SKU + " INTEGER," + COLUMN_ROLE + " TEXT" + ")";
        String CREATE_USERS_TABLE = "CREATE TABLE " +
                TABLE_USERS + "("
                + COLUMN_ACCOUNT + " TEXT PRIMARY KEY," + COLUMN_PASSWORD
                + " BLOB," + COLUMN_TYPE + " TEXT" + ")";
        String CREATE_EMPLOYEES_TABLE = "CREATE TABLE " +
                TABLE_EMPLOYEE + "("
                + COLUMN_EMPLOYEE_ACCOUNT + " TEXT PRIMARY KEY," + COLUMN_ADDRESS
                + " TEXT," + COLUMN_PHONE + " TEXT," + COLUMN_CLINIC_NAME + " TEXT,"
                + COLUMN_INSURANCE_TYPE + " TEXT,"
                + COLUMN_PAYMENT_METHOD + " TEXT,"
                + COLUMN_SERVICES_OFFERED + " TEXT,"
                + COLUMN_START_TIME + " TEXT,"
                + COLUMN_END_TIME + " TEXT" + ")";
        String CREATE_BOOK_TABLE = "CREATE TABLE " +
                TABLE_BOOK + "("
                + COLUMN_CLINIC_ACCOUNT_BOOK + " TEXT," + COLUMN_PATIENT_ACCOUNT_BOOK + " TEXT" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_EMPLOYEES_TABLE);
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);
        onCreate(db);
    }

    public void addService(Service service) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_SERVICENAME, service.getServiceName());
        values.put(COLUMN_SKU, service.getServiceCost());
        values.put(COLUMN_ROLE, service.getRole());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_SERVICES, null, values);
        db.close();
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ACCOUNT, user.getAccount());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_TYPE, user.getType());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        db.close();
        if (user.getType().equals(UserType.ClinicEmployee.toString())) {
            values = new ContentValues();
            values.put(COLUMN_EMPLOYEE_ACCOUNT, user.getAccount());
            values.put(COLUMN_ADDRESS, "");
            values.put(COLUMN_PHONE, "");
            values.put(COLUMN_CLINIC_NAME, "");
            values.put(COLUMN_INSURANCE_TYPE, "");
            values.put(COLUMN_PAYMENT_METHOD, "");
            values.put(COLUMN_SERVICES_OFFERED, "");
            values.put(COLUMN_START_TIME, "00:00");
            values.put(COLUMN_END_TIME, "00:00");
            SQLiteDatabase db2 = this.getWritableDatabase();
            db2.insert(TABLE_EMPLOYEE, null, values);
            db2.close();
        }
    }

    public ArrayList<User> findAllUsers() {
        ArrayList<User> users = new ArrayList<>();

        String query = "Select * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            String account = cursor.getString(0);
            byte[] password = cursor.getBlob(1);
            String type = cursor.getString(2);
            if (type.equals(UserType.ClinicEmployee.toString())) {
                users.add(new ClinicEmployee(account, password));
            } else {
                users.add(new Patient(account, password));
            }
        }
        return users;
    }

    public Service findService(String productname) {
        String query = "Select * FROM " + TABLE_SERVICES + " WHERE " +
                COLUMN_SERVICENAME + " = \"" + productname + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Service service = new Service();

        if (cursor.moveToFirst()) {

            service.setServiceName(cursor.getString(1));
            service.setServiceCost(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        } else {
            service = null;
        }
        db.close();
        return service;
    }

    public void findEmployeeProfile(ClinicEmployee employee) {
        String query = "Select * FROM " + TABLE_EMPLOYEE + " WHERE " +
                COLUMN_EMPLOYEE_ACCOUNT + " = \"" + employee.getAccount() + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
//            Toast.makeText(context, cursor.getString(1), Toast.LENGTH_LONG).show();
            employee.setAddress(cursor.getString(1));
            employee.setPhone(cursor.getString(2));
            employee.setClinicName(cursor.getString(3));
            employee.setInsuranceTypes(cursor.getString(4).split("_"));
            employee.setPaymentMethods(cursor.getString(5).split("_"));
            employee.setServices(cursor.getString(6).split("_"));
            employee.setStartTime(cursor.getString(7));
            employee.setEndTime(cursor.getString(8));
            cursor.close();
        }
        db.close();
    }

    public void updateEmployeeProfile(ClinicEmployee employee) {
        String update = "UPDATE " + TABLE_EMPLOYEE + " SET "
                + COLUMN_ADDRESS + " = '" + employee.getAddress() + "', "
                + COLUMN_PHONE + " = \"" + employee.getPhone() + "\", "
                + COLUMN_CLINIC_NAME + " = \"" + employee.getClinicName() + "\", "
                + COLUMN_INSURANCE_TYPE + " = \"" + employee.getInsuranceTypeString() + "\", "
                + COLUMN_PAYMENT_METHOD + " = \"" + employee.getPaymentMethodsString() + "\", "
                + COLUMN_SERVICES_OFFERED + " = \"" + employee.getServicesString() + "\", "
                + COLUMN_START_TIME + " = \"" + employee.getStartTime() + "\", "
                + COLUMN_END_TIME + " = \"" + employee.getEndTime() + "\""
                + " WHERE " + COLUMN_EMPLOYEE_ACCOUNT + " = \"" + employee.getAccount() + "\"";
        System.out.println(update);
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(update);
        db.close();
    }

    public boolean deleteProduct(String productname) {
        boolean result = false;

        String query = "Select * FROM " + TABLE_SERVICES + " WHERE " +
                COLUMN_SERVICENAME + " = \"" + productname + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(TABLE_SERVICES, COLUMN_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }

        db.close();
        return result;
    }

    public ArrayList<Service> findAllServices() {
        ArrayList<Service> services = new ArrayList<>();

        String query = "Select * FROM " + TABLE_SERVICES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            int cost = cursor.getInt(2);
            String role = cursor.getString(3);
            services.add(new Service(name, cost, role));
        }
        return services;
    }

    public ArrayList<ClinicEmployee> findAllClinics() {
        ArrayList<ClinicEmployee> clinics = new ArrayList<>();

        String query = "Select * FROM " + TABLE_EMPLOYEE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            ClinicEmployee clinic = new ClinicEmployee(cursor.getString(0));
            clinic.setAddress(cursor.getString(1));
            clinic.setPhone(cursor.getString(2));
            clinic.setClinicName(cursor.getString(3));
            clinic.setInsuranceTypes(cursor.getString(4).split("_"));
            clinic.setPaymentMethods(cursor.getString(5).split("_"));
            clinic.setServices(cursor.getString(6).split("_"));
            clinic.setStartTime(cursor.getString(7));
            clinic.setEndTime(cursor.getString(8));
            clinics.add(clinic);
        }
        return clinics;
    }

    public int bookClinic(String account, String patientAccount) {
        int waiting = 0;
        String query = "Select * FROM " + TABLE_BOOK + " WHERE " + COLUMN_CLINIC_ACCOUNT_BOOK + "='" + account + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        boolean exist = false;
        while (cursor.moveToNext()) {
            waiting += 15;
            if (cursor.getString(1).equals(patientAccount)) {
                exist = true;
                waiting -= 15;
            }
        }
        if (!exist) {
            String book = "INSERT INTO " + TABLE_BOOK + " VALUES('" + account + "', '" + patientAccount + "')";
            db.execSQL(book);
        }
        return waiting;
    }
}
