package in.lifc.customerapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.lifc.customerapp.R;
import in.lifc.customerapp.model.LoanTypeModel;
import in.lifc.customerapp.model.MyLoanInfoModel;

public class MyLoanInfoAdapter extends RecyclerView.Adapter<MyLoanInfoAdapter.ViewHolder> {
    private final List<MyLoanInfoModel.Datum> dataList;

    public MyLoanInfoAdapter(List<MyLoanInfoModel.Datum> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyLoanInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myloaninfolist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyLoanInfoAdapter.ViewHolder holder, int position) {
        MyLoanInfoModel.Datum dataItem = dataList.get(position);
        holder.btn_PersonalLoan.setText(dataItem.getProduct());
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
