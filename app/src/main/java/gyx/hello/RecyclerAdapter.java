package gyx.hello;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;




public class RecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<DataBean> list;
    private LayoutInflater inflater;

    public RecyclerAdapter(Context context,List<DataBean> list){
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        String test = null;
        DataBean score = list.get(position);
        test = score.getGrade();
        int circleShape = 0;
        if(!TextUtils.isEmpty(test)){
            TransToDouble transToInt = new TransToDouble();
            double grade= transToInt.transtoInt(test);
            int color;
            if (grade>=90) {
                circleShape = R.drawable.circle_deep_orange;

            } else if (grade>=80 &&grade<90) {
                circleShape = R.drawable.circle_teal;

            } else if (grade>=70 &&grade<80) {
                circleShape = R.drawable.circle_cyan;

            } else if (grade>=60 &&grade<70) {
                circleShape = R.drawable.circle_purple;

            } else  {
                circleShape = R.drawable.circle_blue;

            }
        }
        else{
            circleShape = R.drawable.circle_green;
        }

        holder.gradcTxt.setText(list.get(position).getGrade());
        holder.subjectTxt.setText(list.get(position).getSubName());
        holder.attrTxt.setText(list.get(position).getAttr());
        holder.pointsTxt.setText(list.get(position).getPoints());
        holder.testView.setText(list.get(position).getTest());

        holder.gradcTxt.setBackgroundResource(circleShape);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{

    TextView gradcTxt;
    TextView subjectTxt;
    TextView attrTxt;
    TextView pointsTxt;
    TextView testView;
    public MyViewHolder(View parent) {
        super(parent);
        gradcTxt = (TextView) parent.findViewById(R.id.id_grade_txt);
        subjectTxt = (TextView)parent.findViewById(R.id.id_subject_txt);
        attrTxt = (TextView)parent.findViewById(R.id.id_txt_attr);
        pointsTxt = (TextView)parent.findViewById(R.id.id_txt_points);
        testView = (TextView)parent.findViewById(R.id.id_txt_test);
    }
}