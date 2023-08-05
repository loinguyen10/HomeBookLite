package com.united.homebooklite;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.united.homebooklite.models.Reservation;

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

    public void selectVolleyReservationAccount(Context context, RecyclerView recyclerView) {
        List<Reservation> list = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "https://lmatmet1234.000webhostapp.com/homebook/reservation/api_getReservationAccount.php";

        JsonObjectRequest request = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray reservations = response.getJSONArray("humans");
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
                    //Adapter adapter = new Adapter(context, (ArrayList<Human>) list);
                    //recyclerView.setAdapter(adapter);
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

    public void insertVolleyReservation(Context context, String account_id, String room_id, String checkin_date, String checkout_date, String room, String people, String cost, String status) {
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "https://lmatmet1234.000webhostapp.com/homebook/reservation/api_createReservation.php";

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
}
