package money.system.book.maker.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import money.system.book.maker.Adapter.AdapterBigPerson;
import money.system.book.maker.Adapter.AdapterFavorite;
import money.system.book.maker.DatabaseHelper;
import money.system.book.maker.Models.Quote;
import money.system.book.maker.R;

public class PageFragment extends Fragment{
    private int mPage;
    public static final String ARG_PAGE = "ARG_PAGE";
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        databaseHelper = new DatabaseHelper(getContext());


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        ArrayList<Quote> data=databaseHelper.selectPerson();

        if (mPage == 2){
            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            AdapterBigPerson adapterBigPerson = new AdapterBigPerson(getContext(),postdata(data));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapterBigPerson);

        }else if (mPage == 1){
            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
           AdapterFavorite adapterFavorite = new AdapterFavorite(getContext(),databaseHelper.selectFavorite());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapterFavorite);

        }




        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPage == 2){
            AdapterBigPerson adapterBigPerson = new AdapterBigPerson(getContext(),databaseHelper.selectPerson());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapterBigPerson);

        }else if (mPage == 1){
            AdapterFavorite adapterFavorite = new AdapterFavorite(getContext(),databaseHelper.selectFavorite());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapterFavorite);

        }
    }
    private List<Quote>postdata(List<Quote>dbList){
        List<Quote>data = new ArrayList<>();
        for (int i = 0; i < dbList.size(); i++) {
            Quote cur =new Quote();
            cur.name = dbList.get( i ).getName();
            String uri = "@drawable/"+dbList.get( i ).getImage();
            cur.id = getResources().getIdentifier( uri,null,getActivity().getPackageName() );
            cur.body=dbList.get( i ).getBody();
            cur.isFree = dbList.get(i).getFree();
            data.add( cur );
        }
        return data;
    }
}

