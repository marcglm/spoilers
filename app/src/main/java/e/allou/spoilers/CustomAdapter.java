package e.allou.spoilers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import e.allou.spoilers.roomdb.DataEntity;

public class CustomAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    int[]logos;
    int logo;
    List<DataEntity> dataEntities;

    public CustomAdapter(Context applicationContext, List<DataEntity> dataEntities){
        this.context = applicationContext;
        this.dataEntities = dataEntities;
        layoutInflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        //Renvoie la taille
        return dataEntities.size();
    }

    @Override
    public Object getItem(int position) {
        //Renvoie l'oobjet
        return dataEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //convertView = layoutInflater.inflate(R.layout.activity_my_spoilers, null);

        TextView textView = new TextView(context);
        //textView.setText(String.valueOf(position));
        textView.setText(dataEntities.get(position).titre);
        //ImageView icon = convertView.findViewById(R.id.gridViewSpoiler);//CHANGER ICON
        //icon.setImageResource(logo);
        return textView;

    }
}
