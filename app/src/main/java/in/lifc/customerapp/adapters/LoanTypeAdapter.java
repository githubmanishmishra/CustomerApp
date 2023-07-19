package in.lifc.customerapp.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;

import java.util.List;

import in.lifc.customerapp.R;
import in.lifc.customerapp.model.LoanTypeModel;


public class LoanTypeAdapter extends RecyclerView.Adapter<LoanTypeAdapter.ViewHolder> {

    private List<LoanTypeModel.Data> dataList;

    public LoanTypeAdapter(List<LoanTypeModel.Data> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loan_typerecycle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LoanTypeModel.Data dataItem = dataList.get(position);

      //  holder.titleTextView.setText(dataItem.getTitle());
        holder.btn_PersonalLoan.setText(dataItem.getProductName());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button btn_PersonalLoan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_PersonalLoan = itemView.findViewById(R.id.loan_aganist_property);
        }
    }
}

