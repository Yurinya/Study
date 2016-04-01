package example.mqy.messagelayout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MQY on 2016/3/22.
 */
public class MessageAdapter extends ArrayAdapter<Message> {
    private int resourceId;
    private Context mContext;
    public MessageAdapter(Context context, int resource, List<Message> objects) {
        super(context, resource, objects);
        resourceId = resource;
        mContext = context;
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        Message message = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = View.inflate(mContext,resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.leftlayout = (LinearLayout) view.findViewById(R.id.left_layout);
            viewHolder.rightlayout = (LinearLayout) view.findViewById(R.id.right_layout);
            viewHolder.leftMessage = (TextView) view.findViewById(R.id.left_message);
            viewHolder.rightMessage = (TextView) view. findViewById(R.id.right_message);
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if(message.getType() == Message.RECEIVE){
            viewHolder.leftlayout.setVisibility(View.VISIBLE);
            viewHolder.rightlayout.setVisibility(View.GONE);
            viewHolder.leftMessage.setText(message.getContent());

        }
        else if(message.getType() == Message.SEND){
            viewHolder.rightlayout.setVisibility(View.VISIBLE);
            viewHolder.leftlayout.setVisibility(View.GONE);
            viewHolder.rightMessage.setText(message.getContent());

        }
        return  view;
    }
    class ViewHolder{
        LinearLayout leftlayout;
        LinearLayout rightlayout;
        TextView leftMessage;
        TextView rightMessage;
    }
}
