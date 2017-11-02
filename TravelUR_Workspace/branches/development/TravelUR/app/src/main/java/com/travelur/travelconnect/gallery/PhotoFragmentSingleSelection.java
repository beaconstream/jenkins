package com.travelur.travelconnect.gallery;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.travelur.R;
import com.travelur.general.BaseFragment;
import com.travelur.travelconnect.gallery.adapter.GridViewAdapter;
import com.travelur.travelconnect.gallery.model.Media;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.travelur.utility.AppConfig.DEFAULT;
import static com.travelur.utility.AppConfig.MEDIA_TYPE_PHOTOS;
import static com.travelur.utility.AppConfig.MEDIA_TYPE_PHOTOS_SINGLE_SELECTION;
import static com.travelur.utility.AppConfig.MEDIA_TYPE_VIDEOS;


/*
 * @author by Abhijit .
 */

public class PhotoFragmentSingleSelection extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {

    private OnFragmentInteractionListener mListener;
    private GridView mGridView;
    private View mRootView;
    private GridViewAdapter mGridViewAdapter;
    private ArrayList<Media> mMediaArrayList;
    private Button mSelectVideoButton;
    private Media mLastSelectedImage;
    private int mLastSelectedPosition = -1;
    private WeakReference<View> mLastSelectedViewWeakReference = new WeakReference<View>(null);

    public PhotoFragmentSingleSelection() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment VideosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhotoFragmentSingleSelection newInstance() {
        PhotoFragmentSingleSelection fragment = new PhotoFragmentSingleSelection();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        mMediaArrayList = new ArrayList<>();
        mLastSelectedImage = null;
        mLastSelectedPosition = -1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_photos, container, false);

        custom_toolbar_menu_item = (TextView) getActivity().findViewById(R.id.custom_toolbar_menu_item);
        custom_toolbar_menu_item.setText("Select");
        mGridView = (GridView) mRootView.findViewById(R.id.photoGridView);

        custom_toolbar_menu_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLastSelectedImage == null) {
                    Toast.makeText(getActivity(), "Please select a image first", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mListener != null) {
                    mListener.onImageSelected(mLastSelectedImage);
                }
            }
        });
        mGridView.setEmptyView(inflater.inflate(R.layout.empty_view, null));
        mGridViewAdapter = new GridViewAdapter(getActivity(), new ArrayList<Media>(), MEDIA_TYPE_PHOTOS_SINGLE_SELECTION);
        mGridView.setAdapter(mGridViewAdapter);
        mGridView.setOnItemClickListener(this);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PhotoFragmentSingleSelection.OnFragmentInteractionListener) {
            mListener = (PhotoFragmentSingleSelection.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        init();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.MediaColumns._ID, MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        return new CursorLoader(getActivity(), uri, projection, null, null, orderBy + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        int column_index_data = data.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        int column_index_folder_name = data.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        if (data != null && data.getCount() > 0) {
            while (data.moveToNext()) {
                String absolutePathOfImage = data.getString(column_index_data);
                Media media = new Media();
                media.path = absolutePathOfImage;
                mMediaArrayList.add(media);
            }
        }
        mGridViewAdapter = new GridViewAdapter(getActivity(), mMediaArrayList,  MEDIA_TYPE_PHOTOS_SINGLE_SELECTION);
        mGridView.setAdapter(mGridViewAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        init();
        mGridViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        GridViewAdapter.ViewHolder viewHolder = (GridViewAdapter.ViewHolder) view.getTag();
        Media media = mMediaArrayList.get(i);
        if (media.isSelected) {
            media.isSelected = false;
            viewHolder.selecTickMark.setVisibility(View.INVISIBLE);
        } else {
            media.isSelected = true;
            viewHolder.selecTickMark.setVisibility(View.VISIBLE);
        }

        if (mLastSelectedImage != null && !media.path.equals(mLastSelectedImage.path)) {
            mLastSelectedImage.isSelected = false;
        }

        View lastSelectedView = mLastSelectedViewWeakReference.get();
        if (lastSelectedView != null) {
            GridViewAdapter.ViewHolder mLastSelectedVideoViewHolder = (GridViewAdapter.ViewHolder) lastSelectedView.getTag();
            if (mLastSelectedVideoViewHolder != null && mLastSelectedVideoViewHolder.media != null
                    && mLastSelectedImage != null && mLastSelectedVideoViewHolder.media.path.equals(mLastSelectedImage.path)) {
                mLastSelectedVideoViewHolder.selecTickMark.setVisibility(View.GONE);
            }
        }
        mLastSelectedImage = media;
        mLastSelectedViewWeakReference = new WeakReference<>(view);
        mLastSelectedPosition = i;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onImageSelected(Media media);
    }

}
