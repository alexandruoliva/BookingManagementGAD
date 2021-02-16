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

    private OnItemClickListener listener;

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
        holder.mTextViewCheckInDate.setText("Check-in date: " + String.valueOf(model.getCheckInDate()));
        holder.mTextViewCheckOutDate.setText("Check-out date: " + String.valueOf(model.getCheckOutDate()));
        holder.mTextViewTypeOfBooking.setText("Type of booking: " + model.getTypeOfBooking());
        holder.mTextViewPricePerNight.setText("Price/night: " + String.valueOf(model.getPricePerNight()));
        holder.mTextViewNumberOfRooms.setText("No. of rooms: " + String.valueOf(model.getNumberOfRooms()));


//        String checkInDate = model.getCheckInDate();
//        String checkOutDate = model.getCheckOutDate();

//        int days = Days.daysBetween(checkInDate, checkOutDate).getDays();

//        holder.mTextViewNumberOfNights.setText("Number of nights: "+String.valueOf(diff));
    }

    @NonNull
    @Override
    public BookingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item, parent, false);
        return new BookingHolder(view);
    }


    public void deleteItem(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    class BookingHolder extends RecyclerView.ViewHolder {

        TextView mTextViewFirstName, mTextViewLastName;
        TextView mTextViewCheckInDate, mTextViewCheckOutDate;
        TextView mTextViewPricePerNight, mTextViewTypeOfBooking;
        TextView mTextViewNumberOfNights, mTextViewNumberOfRooms;


        public BookingHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewLastName = itemView.findViewById(R.id.text_view_last_name);
            mTextViewFirstName = itemView.findViewById(R.id.text_view_first_name);
            mTextViewCheckInDate = itemView.findViewById(R.id.text_view_checkIn_date);
            mTextViewCheckOutDate = itemView.findViewById(R.id.text_view_checkOut_date);
            mTextViewPricePerNight = itemView.findViewById(R.id.text_view_price_per_night);
            mTextViewTypeOfBooking = itemView.findViewById(R.id.text_view_type_of_booking);
            mTextViewNumberOfRooms = itemView.findViewById(R.id.text_view_number_of_rooms);
//            mTextViewNumberOfNights = itemView.findViewById(R.id.text_view_number_of_nights);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });

        }


    }


}
