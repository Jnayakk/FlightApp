package cs.b07.cscb07courseproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * This class sets the TextViews to with a client format so that each client is shown in a specifc
 * format in a listview.
 */
public class ClientAdaptor extends BaseAdapter{

    private Context mContext;
    private List<String> mClientList;

    /**
     * Creates a new <code>ItineraryAdaptor</code> with the given Context and List of
     * ItineraryFormats.
     *
     * @param mContext the context of this <code>ItineraryAdaptor</code>.
     * @param mClientList the list of ItineraryFormats of this <code>ItineraryAdaptor</code>.
     */
    public ClientAdaptor(Context mContext, List<String> mClientList) {
        this.mContext = mContext;
        this.mClientList = mClientList;
    }

    @Override
    public int getCount() {
        return mClientList.size();
    }

    @Override
    public Object getItem(int position) {
        return mClientList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.client_row_view, null);
        String[] clientInfo = mClientList.get(position).split(";");

        TextView emailTextView = (TextView)v.findViewById(R.id.emailText);
        TextView nameTextView = (TextView)v.findViewById(R.id.nameText);
        TextView addressTextView = (TextView)v.findViewById(R.id.addressText);
        TextView creditCardNumTextView = (TextView)v.findViewById(R.id.ccNumText);
        TextView expireDateTextView = (TextView)v.findViewById(R.id.expireText);

        emailTextView.setText(clientInfo[2]);
        nameTextView.setText(clientInfo[1] + " " + clientInfo[0]);
        addressTextView.setText(clientInfo[3]);
        creditCardNumTextView.setText(clientInfo[4]);
        expireDateTextView.setText(clientInfo[5]);

        //Save product id to tag
        v.setTag(clientInfo[2]);

        return v;
    }
}
