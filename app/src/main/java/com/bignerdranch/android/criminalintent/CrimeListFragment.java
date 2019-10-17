package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view
                .findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }


    private class AbstractCrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Crime mCrime;

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;
        private Button mContactPoliceButton;

        public AbstractCrimeHolder(LayoutInflater inflater, ViewGroup parent, int layoutId) {
            super(inflater.inflate(layoutId, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.crime_solved);
            mContactPoliceButton = (Button) itemView.findViewById(R.id.contact_police_button);
        }

        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            //mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
            mContactPoliceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Call the cops!", Toast.LENGTH_SHORT).show();
                }
            });
            mContactPoliceButton.setVisibility(crime.isRequiresPolice() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),
                    mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

//    private class CrimeHolderOld extends AbstractCrimeHolder {
//        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
//            super(inflater, parent, R.layout.list_item_crime_old);
//        }
//    }

    private class CrimeHolder extends AbstractCrimeHolder {
        //private Button mContactPoliceButton;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater, parent, R.layout.list_item_crime);

            //mContactPoliceButton = (Button) itemView.findViewById(R.id.contact_police_button);
//            mContactPoliceButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(getActivity(), "Call the cops!", Toast.LENGTH_SHORT).show();
//                }
//            });

//            public void showPoliceButton() {
//                mContactPoliceButton.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
//            }
        }
    }


    private class CrimeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

//        private static final int LIST_ITEM_CRIME = 0;
//        private static final int LIST_ITEM_CRIME_POLICE = 1;
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public int getItemViewType(int position) {
            //Crime crime = mCrimes.get(position);
            boolean requiresPolice = mCrimes.get(position).isRequiresPolice();
//            if (requiresPolice) {
//                return LIST_ITEM_CRIME_POLICE;
//            } else {
//                return LIST_ITEM_CRIME;
//            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Crime crime = mCrimes.get(position);
//            if (holder instanceof CrimeHolder) {
//                ((CrimeHolder) holder).bind(crime);
//            } else if (holder instanceof PoliceCrimeHolder) {
//                ((PoliceCrimeHolder) holder).bind(crime);
//            }
            ((CrimeHolder) holder).bind(crime);
        }

        @Override
        public int getItemCount() { return mCrimes.size(); }

    }
}
