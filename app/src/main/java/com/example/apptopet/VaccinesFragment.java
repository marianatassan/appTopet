package com.example.apptopet;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VaccinesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VaccinesFragment extends Fragment {
    MyAdapter3 myAdapter3;
    Saude saude;
    VaccinesFragment vaccinesFragment;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VaccinesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment VaccinesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VaccinesFragment newInstance() {
        VaccinesFragment fragment = new VaccinesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_vaccines, container, false);
        return vista;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        VacinaViewModel vm = new ViewModelProvider(getActivity()).get(VacinaViewModel.class);
        List<MyItem> vacinas = vm.getItems();

        myAdapter3 = new MyAdapter3(this, vacinas);

        RecyclerView rvVaccines = getView().findViewById(R.id.rvVaccines);
        rvVaccines.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvVaccines.setLayoutManager(layoutManager);

        rvVaccines.setAdapter(myAdapter3);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvVaccines.getContext(), DividerItemDecoration.VERTICAL);
        rvVaccines.addItemDecoration(dividerItemDecoration);
        //mViewModel = new ViewModelProvider(getActivity()).get(VaccinesViewModel.class);

        //MyAdapter3 myAdapter3
    }
}