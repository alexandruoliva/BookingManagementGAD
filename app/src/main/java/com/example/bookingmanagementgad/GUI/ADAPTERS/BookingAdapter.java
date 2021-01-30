package com.example.bookingmanagementgad.GUI.ADAPTERS;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingmanagementgad.MODELS.Booking;
import com.example.bookingmanagementgad.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

@RequiresApi(api = Build.VERSION_CODES.O)
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
        holder.mTextViewCheckInDate.setText("Check-in date: "+String.valueOf(model.getCheckInDate()));
        holder.mTextViewCheckOutDate.setText("Check-out date: "+String.valueOf(model.getCheckOutDate()));
        holder.mTextViewTypeOfBooking.setText("Type of booking: "+model.getTypeOfBooking());
        holder.mTextViewPricePerNight.setText("Price/night: "+String.valueOf(model.getPricePerNight()));
        holder.mTextViewNumberOfRooms.setText("No. of rooms: "+String.valueOf(model.getNumberOfRooms()));


//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String inputString1 = model.getCheckInDate();
//        String inputString2 = model.getCheckOutDate();
//
//        LocalDateTime date1 = LocalDateTime.parse(inputString1, dtf);
//        LocalDateTime date2 = LocalDateTime.parse(inputString2, dtf);
//        long daysBetween = Duration.between(date1, date2).toDays();
//
//        holder.mTextViewNumberOfNights.setText("Number of nights: "+String.valueOf(daysBetween));
    }

    @NonNull
    @Override
    public BookingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item, parent, false);
        return new BookingHolder(view);
    }


    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    class BookingHolder extends RecyclerView.ViewHolder{

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
            mTextViewNumberOfNights = itemView.findViewById(R.id.text_view_number_of_nights);

        }
    }




}
