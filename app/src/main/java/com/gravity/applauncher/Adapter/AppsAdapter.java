package com.gravity.applauncher.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.gravity.applauncher.R;
import com.gravity.applauncher.databinding.ItemAppsBinding;
import com.gravity.installapp.Model.MApps;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder> implements Filterable {

    private ItemAppsBinding binding;
    private Activity activity;
    private List<MApps> pkgAppsList;
    private List<MApps> NewAppsList;
    private CallBack callBack;

    public AppsAdapter(Activity activity, List<MApps> pkgAppsList, CallBack callBack) {
        this.activity = activity;
        this.pkgAppsList = pkgAppsList;
        this.callBack = callBack;
        this.NewAppsList = new ArrayList<>(pkgAppsList);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.item_apps, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AppsAdapter.ViewHolder holder, int position) {

        MApps app = pkgAppsList.get(position);

        holder.binding.imgAppIcon.setImageDrawable(app.getIcon());
        holder.binding.txtAppName.setText(app.getName());
        holder.binding.txtPackageName.setText(app.getLabel());
        holder.binding.txtVersionName.setText(app.getVer_name());
        holder.binding.txtVersionCode.setText(String.valueOf(app.getVer_code()));

        holder.binding.llMain.setOnClickListener(view -> {
            callBack.onAppClick(app, position);
        });

    }

    @Override
    public int getItemCount() {
        return pkgAppsList.size();
    }

    @Override
    public Filter getFilter() {
        return appListFilter;
    }

    private Filter appListFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<MApps> filterAppList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0)
            {
                filterAppList.addAll(NewAppsList);
            }else {
                String filterString = charSequence.toString().toLowerCase().trim();

                for (MApps apps: NewAppsList)
                {
                    if (apps.getLabel().toString().toLowerCase().contains(filterString))
                    {
                        filterAppList.add(apps);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filterAppList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            pkgAppsList.clear();
            pkgAppsList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public interface CallBack {

        void onAppClick(MApps apps, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemAppsBinding binding;

        public ViewHolder(@NonNull ItemAppsBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
