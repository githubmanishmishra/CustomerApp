package in.lifc.customerapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import in.lifc.customerapp.R;
import in.lifc.customerapp.model.EmiDueModel;

public class CurrentDuesAdapter
 extends RecyclerView.Adapter<CurrentDuesAdapter.ViewHolder>
    {

        List<EmiDueModel.Datum> list;

        Context context;

    public CurrentDuesAdapter(List<EmiDueModel.Datum> list, Context context)
        {
            this.list = list;
            this.context = context;
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_current_dues, parent, false);
        return new ViewHolder(view);
    }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.etLoanNumber.setText(list.get(position).getLoanNo());
        holder.etLoanStatus.setText(list.get(position).getLoanStatus());
        holder.etLoanType.setText(list.get(position).getProduct());
        holder.etEmiDate.setText(list.get(position).getEmiDate());
        holder.etEmiAmount.setText(list.get(position).getEmiAmount());
        holder.etNumberofEmi.setText(list.get(position).getNumEmiDue());
        holder.etEmiAmount.setText(list.get(position).getEmiAmount());

    }

        @Override
        public int getItemCount() {
        return list.size();
    }
        public class ViewHolder extends RecyclerView.ViewHolder
        {
            private TextInputEditText etLoanNumber,etEmiOverdue,etLoanStatus,etLoanType,etEmiDate,etEmiAmount,
    etNumberofEmi, etTotalDue;

            AppCompatButton btnNext;


            CardView card_view;

            public ViewHolder(View itemView) {
                super(itemView);

                etLoanNumber = itemView.findViewById(R.id.et_loan_number);
                etLoanStatus = itemView.findViewById(R.id.ev_loan_status);
                etLoanType = itemView.findViewById(R.id.ev_loan_type);
                etEmiDate = itemView.findViewById(R.id.ev_due_emi_date);
                etEmiAmount = itemView.findViewById(R.id.ev_emi_amount);
                etEmiOverdue = itemView.findViewById(R.id.ev_emi_overdue);
                etNumberofEmi = itemView.findViewById(R.id.ev_number_emi);
                etTotalDue = itemView.findViewById(R.id.ev_total_due);
                btnNext = itemView.findViewById(R.id.btn_emi_selection);
            }
        }
}
