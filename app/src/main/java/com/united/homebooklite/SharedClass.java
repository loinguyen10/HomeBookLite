package com.united.homebooklite;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.united.homebooklite.adapter.ReservationAdapter;
import com.united.homebooklite.models.Account;
import com.united.homebooklite.models.Property;
import com.united.homebooklite.models.Reservation;
import com.united.homebooklite.models.Room;
import com.united.homebooklite.reservationActivity.ReservationActivity;
import com.united.homebooklite.reservationActivity.ReservationFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SharedClass {
    public static int calculate(String startDate, String endDate) throws ParseException {
        int kq = 0;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date11 = format.parse(startDate);
        Date date22 = format.parse(endDate);

        Log.d("date1", date11 + "");
        Log.d("date2", date22 + "");

        kq = (int) ((date22.getTime() - date11.getTime()) / (24 * 60 * 60 * 1000));

        Log.d("kq", kq + "");
        return kq;
    }

    public void selectVolleyReservationAccount(Context context, RecyclerView recyclerView, Fragment fragment, int accId) {
        List<Reservation> list = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "https://lmatmet1234.000webhostapp.com/homebook/reservation/api_getReservationAccount.php?account_id="+accId;

        JsonObjectRequest request = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray reservations = response.getJSONArray("reservations");
                    for (int i = 0; i < reservations.length(); i++) {
                        JSONObject reservation = reservations.getJSONObject(i);
                        int id = reservation.getInt("id");
                        int account_id = reservation.getInt("account_id");
                        int room_id = reservation.getInt("room_id");
                        String checkin_date = reservation.getString("checkin_date");
                        String checkout_date = reservation.getString("checkout_date");
                        int room = reservation.getInt("room");
                        int people = reservation.getInt("people");
                        int cost = reservation.getInt("cost");
                        int status = reservation.getInt("status");

                        list.add(new Reservation(id, account_id, room_id, checkin_date, checkout_date, room, people, cost, status));
                    }
                    ReservationAdapter adapter = new ReservationAdapter(context, (ArrayList<Reservation>) list,fragment.getLayoutInflater());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });
        queue.add(request);
    }

    public void updateVolleyReservationAccount(Context context, String account_id, String room_id, String checkin_date, String checkout_date, String room, String people, String cost, String status) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://lmatmet1234.000webhostapp.com/api_.php";
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> mydata = new HashMap<>();
                mydata.put("account_id", account_id);
                mydata.put("room_id", room_id);
                mydata.put("checkin_date", checkin_date);
                mydata.put("checkout_date", checkout_date);
                mydata.put("room", room);
                mydata.put("people", people);
                mydata.put("cost", cost);
                mydata.put("status", status);
                return mydata;
            }
        };
        queue.add(request);
    }

    public void deleteVolley(Context context, String id) {
        RequestQueue queue = Volley.newRequestQueue(context);
        //b3. url
        String url = "https://lmatmet1234.000webhostapp.com/api_postDelete.php";
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> mydata = new HashMap<>();
                mydata.put("id", id);
                return mydata;
            }
        };
        queue.add(request);
    }

    public Account loginAccount(Context context, String emailx, String passwordx) {
        List<Account> list = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "https://lmatmet1234.000webhostapp.com/homebook/reservation/api_getReservationAccount.php";

        JsonObjectRequest request = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray accounts = response.getJSONArray("accounts");
                    for (int i = 0; i < accounts.length(); i++) {
                        JSONObject account = accounts.getJSONObject(i);
                        int id = account.getInt("id");
                        String email = account.getString("email");
                        String password = account.getString("password");
                        String role = account.getString("role");
                        String fullname = account.getString("fullname");
                        String phone = account.getString("phone");

                        list.add(new Account(id, email, password, role, fullname, phone));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });
        queue.add(request);
        return list.get(0);
    }

    public Room get1Room(Context context, int room_id) {
        List<Room> list = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "https://lmatmet1234.000webhostapp.com/homebook/reservation/api_getReservationAccount.php?id=" + room_id;

        JsonObjectRequest request = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray rooms = response.getJSONArray("rooms");
                    for (int i = 0; i < rooms.length(); i++) {
                        JSONObject r = rooms.getJSONObject(i);
                        int id = r.getInt("id");
                        String quality = r.getString("quality");
                        String type = r.getString("type");
                        int property_id = r.getInt("property_id");
                        int size = r.getInt("size");
                        int people = r.getInt("people");
                        int bed = r.getInt("bed");
                        int room = r.getInt("room");
                        String amenities = r.getString("amenities");
                        int price = r.getInt("price");
                        int available = r.getInt("available");

                        list.add(new Room(id, quality, type, property_id, size, people, bed, room, amenities, price,available));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });
        queue.add(request);
        return list.get(0);
    }

    public Property get1Property(Context context, int property_id) {
        List<Property> list = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "https://lmatmet1234.000webhostapp.com/homebook/property/api_get1Property.php?id=" + property_id;

        JsonObjectRequest request = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray properties = response.getJSONArray("properties");
                    for (int i = 0; i < properties.length(); i++) {
                        JSONObject property = properties.getJSONObject(i);
                        int id = property.getInt("id");
                        String name = property.getString("name");
                        String description = property.getString("description");
                        String type = property.getString("type");
                        String address = property.getString("address");
                        String district = property.getString("district");
                        String province = property.getString("province");
                        String country = property.getString("country");
                        String check_time = property.getString("check_time");
                        String amenities = property.getString("amenities");
                        int rating = property.getInt("rating");
                        int owner_id  = property.getInt("owner_id");

                        list.add(new Property(id, name, description, type, address, district, province, country, amenities,rating, check_time, owner_id));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });
        queue.add(request);
        return list.get(0);
    }
}
