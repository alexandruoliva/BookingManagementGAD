package com.example.bookingmanagementgad.GUI.ADAPTERS;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingmanagementgad.MODELS.Booking;
import com.example.bookingmanagementgad.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class BookingAdapter extends FirestoreRecyclerAdapter<Booking, BookingAdapter.BookingHolder> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BookingAdapter(@NonNull FirestoreRecyclerOptions<Booking> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull BookingHolder holder, int position, @NonNull Booking model) {
        holder.mTextViewLastName.setText(model.getLastName());
        holder.mTextViewFirstName.setText(model.getFirstName());
        holder.mTextViewCheckInDate.setText(model.getCheckInDate());
        holder.mTextViewCheckOutDate.setText(model.getCheckOutDate());
        holder.mTextViewTypeOfBooking.setText(model.getTypeOfBooking());
        holder.mTextViewPricePerNight.setText(model.getPricePerNight());
    }

    @NonNull
    @Override
    public BookingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item, parent, false);
        return new BookingHolder(view);
    }

    class BookingHolder extends RecyclerView.ViewHolder{

        TextView mTextViewFirstName, mTextViewLastName;
        TextView mTextViewCheckInDate, mTextViewCheckOutDate;
        TextView mTextViewPricePerNight, mTextViewTypeOfBooking;


        public BookingHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewLastName = itemView.findViewById(R.id.text_view_last_name);
            mTextViewFirstName = itemView.findViewById(R.id.text_view_last_name);
            mTextViewCheckInDate = itemView.findViewById(R.id.text_view_checkIn_date);
            mTextViewCheckOutDate = itemView.findViewById(R.id.text_view_checkOut_date);
            mTextViewPricePerNight = itemView.findViewById(R.id.text_view_price_per_night);
            mTextViewTypeOfBooking = itemView.findViewById(R.id.text_view_type_of_booking);


        }
    }

}