package project.seg2015.seg2105_project_f19_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ClinicAdapter extends BaseAdapter {

    public List<ClinicEmployee> mList;
    public Context mContext;
    public LayoutInflater mLayoutInflater;
    boolean bookVisible;

    public ClinicAdapter(Context mContext, List<ClinicEmployee> mList, boolean bookVisible) {
        this.mContext = mContext;
        this.mList = mList;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.bookVisible = bookVisible;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(project.seg2015.seg2105_project_f19_3.R.layout.clinic_item_view, null);
            viewHolder.name = convertView.findViewById(project.seg2015.seg2105_project_f19_3.R.id.clinic_name);
            viewHolder.address = convertView.findViewById(project.seg2015.seg2105_project_f19_3.R.id.clinic_address);
            viewHolder.phone = convertView.findViewById(project.seg2015.seg2105_project_f19_3.R.id.phone);
            viewHolder.duration = convertView.findViewById(project.seg2015.seg2105_project_f19_3.R.id.duration);
            viewHolder.insuranceType = convertView.findViewById(project.seg2015.seg2105_project_f19_3.R.id.clinic_insurance_type);
            viewHolder.payment = convertView.findViewById(project.seg2015.seg2105_project_f19_3.R.id.clinic_payment);
            viewHolder.services = convertView.findViewById(project.seg2015.seg2105_project_f19_3.R.id.clinic_services);
            viewHolder.book = convertView.findViewById(project.seg2015.seg2105_project_f19_3.R.id.book);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        ClinicEmployee clinic = mList.get(position);
        viewHolder.name.setText(clinic.getClinicName());
        viewHolder.address.setText(clinic.getAddress());
        viewHolder.phone.setText("Phone: " + clinic.getPhone());
        viewHolder.duration.setText(clinic.getStartTime() + "~" + clinic.getEndTime());
        viewHolder.insuranceType.setText(clinic.getInsuranceTypeString());
        viewHolder.services.setText(clinic.getServicesString());
        viewHolder.payment.setText(clinic.getPaymentMethodsString());
        viewHolder.book.setVisibility(bookVisible ? View.VISIBLE : View.INVISIBLE);
        return convertView;
    }

    private class ViewHolder {
        TextView name;
        TextView address;
        TextView phone;
        TextView duration;
        TextView insuranceType;
        TextView payment;
        TextView services;
        Button book;
    }
}