package com.example.mapdemo;

import android.Manifest;
import android.app.Dialog;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import android.view.View;
import android.widget.ToggleButton;
import android.widget.Button;

import com.example.map.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.maps.model.TileOverlayOptions;

import com.google.maps.android.heatmaps.HeatmapTileProvider;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

@RuntimePermissions
public class MapDemoActivity extends AppCompatActivity {

    private SupportMapFragment mapFragment;
    private GoogleMap map;
    private LocationRequest mLocationRequest;
    Location mCurrentLocation;
    private long UPDATE_INTERVAL = 60000;  // 60 secs
    private long FASTEST_INTERVAL = 5000; // 5 secs

    private final static String KEY_LOCATION = "location";

    private final static LatLng bpCampussec = new LatLng(48.466578, -123.308768);
    private final static LatLng bpBookandsec = new LatLng(48.466407, -123.309208);
    private final static LatLng bpCinema = new LatLng(48.465486, -123.308701);
    private final static LatLng bpPharmacy = new LatLng(48.465791, -123.308095);
    private final static LatLng bpLeftSUB = new LatLng(48.465308, -123.308811);
    private final static LatLng bpFrontSUB = new LatLng(48.465059, -123.308621);
    private final static LatLng bpParkingC = new LatLng(48.464298, -123.309514);
    private final static LatLng bpClearihueside = new LatLng(48.463916, -123.309834);
    private final static LatLng bplibraryfront = new LatLng(48.463121, -123.310077);
    private final static LatLng bpLibraryleft = new LatLng(48.463088, -123.309704);
    private final static LatLng bpElliotfront = new LatLng(48.462853, -123.310066);
    private final static LatLng bpElliotside = new LatLng(48.462686, -123.309680);
    private final static LatLng bpBobWright = new LatLng(48.462521, -123.308768);
    private final static LatLng bpEOW = new LatLng(48.461525, -123.309534);
    private final static LatLng bpELWandEOW = new LatLng(48.461460, -123.309977);
    private final static LatLng bpELW = new LatLng(48.461460, -123.309977);
    private final static LatLng bpECSside = new LatLng(48.461400, -123.311332);
    private final static LatLng bpECSfront = new LatLng(48.461340, -123.311686);
    private final static LatLng bpPetch  = new LatLng(48.461776, -123.310703);
    private final static LatLng bpMedical = new LatLng(48.461504, -123.312932);
    private final static LatLng bpMedicalfront = new LatLng(48.461762, -123.312825);
    private final static LatLng bpCunningham = new LatLng(48.462502, -123.312089);
    private final static LatLng bpMacLaurinCafe = new LatLng(48.462680, -123.313296);
    private final static LatLng bpMacLaurinDcafe = new LatLng(48.463116, -123.313508);
    private final static LatLng bpSocialFront = new LatLng(48.463473, -123.313972);
    private final static LatLng bpHickman = new LatLng(48.463670, -123.313878);
    private final static LatLng bpCornettA = new LatLng(48.463900, -123.313147);
    private final static LatLng bpCornettB = new LatLng(48.464245, -123.313209);
    private final static LatLng bpSedgewickC = new LatLng(48.464266, -123.313947);
    private final static LatLng bpDavidtrunpin = new LatLng(48.464810, -123.313955);
    private final static LatLng bpBusandEcon = new LatLng(48.465150, -123.313681);
    private final static LatLng bpStrong = new LatLng(48.465063, -123.313204);
    private final static LatLng bpClearihueBandC = new LatLng(48.464494, -123.311005);
    private final static LatLng bpUnicentre = new LatLng(48.464944, -123.311292);
    private final static LatLng bpMcKinnon = new LatLng(48.466508, -123.310466);
    private final static LatLng bpMcKinnonCarsa = new LatLng(48.466983, -123.310796);
    private final static LatLng bpContsudies = new LatLng(48.466080, -123.312405);
    private final static LatLng bpMichaelWilliams = new LatLng(48.465882, -123.314014);
    private final static LatLng bpFraserFront = new LatLng(48.465112, -123.315865);
    private final static LatLng bpFraserSide = new LatLng(48.464763, -123.316112);
    private final static LatLng bpVisualarts = new LatLng(48.462223, -123.317680);
    private final static LatLng bpFinearts = new LatLng(48.462085, -123.317031);
    private final static LatLng bpPheonixtheatre = new LatLng(48.462372, -123.316993);
    private final static LatLng bpSocialRingRoad = new LatLng(48.463247, -123.314965);
    private final static LatLng bpMusicwing = new LatLng(48.462215, -123.314616);
    private final static LatLng bpAboriginalclearihue = new LatLng(48.463858, -123.310974);

    private final static LatLng destLibrary = new LatLng(48.463408, -123.309568);
    private final static LatLng destClearihue = new LatLng(48.464271, -123.310426);
    private final static LatLng destECS = new LatLng(48.461014, -123.311454);
    private final static LatLng destBobWright = new LatLng(48.462065, -123.309012);
    private final static LatLng destGlover = new LatLng(48.461652, -123.307945);
    private final static LatLng destElliot = new LatLng(48.462565, -123.309942);
    private final static LatLng destPetch = new LatLng(48.461823, -123.310269);
    private final static LatLng destELW = new LatLng(48.461149, -123.310615);
    private final static LatLng destCunningham = new LatLng(48.462129, -123.312062);
    private final static LatLng destMedical = new LatLng(48.461677, -123.312558);
    private final static LatLng destMacLaurin = new LatLng(48.462802, -123.313893);
    private final static LatLng destHumanandSocial = new LatLng(48.463261, -123.314720);
    private final static LatLng destInterfaith = new LatLng(48.460620, -123.317035);
    private final static LatLng destFineArts = new LatLng(48.460620, -123.317035);
    private final static LatLng destVisualArts = new LatLng(48.462256, -123.318011);
    private final static LatLng destPhoenix = new LatLng(48.462619, -123.317099);
    private final static LatLng destHickman = new LatLng(48.463786, -123.314116);
    private final static LatLng destSedgewick = new LatLng(48.464199, -123.314738);
    private final static LatLng destDavidTurpin = new LatLng(48.464825, -123.314437);
    private final static LatLng destUniClub = new LatLng(48.463886, -123.317591);
    private final static LatLng destFraser = new LatLng(48.465053, -123.316336);
    private final static LatLng destCornett = new LatLng(48.464081, -123.313113);
    private final static LatLng destDavidStrong = new LatLng(48.464842, -123.313301);
    private final static LatLng destBusiness = new LatLng(48.465233, -123.313108);
    private final static LatLng destMichaelWilliams = new LatLng(48.466048, -123.314041);
    private final static LatLng destContStudies = new LatLng(48.466201, -123.312528);
    private final static LatLng destFirstPeoples = new LatLng(48.463982, -123.311664);
    private final static LatLng destBikeCentre = new LatLng(48.464857, -123.312184);
    private final static LatLng destUniCentre = new LatLng(48.464985, -123.311471);
    private final static LatLng destMcKinnon = new LatLng(48.466664, -123.310715);
    private final static LatLng destCampus = new LatLng(48.465899, -123.310114);
    private final static LatLng destBookstore = new LatLng(48.466127, -123.309755);
    private final static LatLng destSecurity = new LatLng(48.466742, -123.308741);
    private final static LatLng destGrad = new LatLng(48.466087, -123.307436);
    private final static LatLng destSub = new LatLng(48.465068, -123.308153);
    private final static LatLng destComms = new LatLng(48.463795, -123.306469);
    private final static LatLng destHealth = new LatLng(48.463438, -123.304624);
    private final static LatLng destChildcare = new LatLng(48.468918, -123.304371);
    private final static LatLng destSaunders = new LatLng(48.467839, -123.307740);
    private final static LatLng destTech = new LatLng(48.468821, -123.309403);
    private final static LatLng destEnterprise = new LatLng(48.469444, -123.310502);
    private final static LatLng destIanStewart = new LatLng(48.470099, -123.319274);
    private final static LatLng destCARSA = new LatLng(48.467812, -123.310815);

    private final static LatLng showerECS = new LatLng (48.461221, -123.311419);
    private final static LatLng showerELW = new LatLng (48.461286, -123.310505);
    private final static LatLng showerMSB = new LatLng(48.461787, -123.31249);
    private final static LatLng showerMWB = new LatLng(48.465234, -123.313061);
    private final static LatLng showerCARSA = new LatLng(48.468032, -123.311907);
    private final static LatLng showerCRA = new LatLng(48.464410, -123.306493);
    private final static LatLng showerCST = new LatLng(48.466140, -123.312751);
    private final static LatLng showerDTB = new LatLng(48.464955, -123.314179);
    private final static LatLng showerFPH = new LatLng(48.464038, -123.311661);
    private final static LatLng showerMCK = new LatLng(48.466545, -123.310983);
    private final static LatLng showerTEF = new LatLng(48.468923, -123.309319);
    

     //Define a request code to send to Google Play services This code is returned in Activity.onActivityResult
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    ArrayList<Marker> myBikeLocks = new ArrayList<Marker>();
    ArrayList<Marker> myDest = new ArrayList<Marker>();
    ArrayList<Marker> myShower = new ArrayList<Marker>();

    private ToggleButton getBikeLocks;
    private ToggleButton getlocate;
    private ToggleButton getkm;
    private ToggleButton getshower;
    private ToggleButton getpoptimes;

    private TileOverlay hmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_demo_activity);
        getBikeLocks = (ToggleButton) findViewById(R.id.bikelock);
        getlocate = (ToggleButton) findViewById(R.id.locate);
        getkm = (ToggleButton) findViewById(R.id.kmtracker);
        getshower = (ToggleButton) findViewById(R.id.shower);
        getpoptimes = (ToggleButton) findViewById(R.id.poptimes);


        if (TextUtils.isEmpty(getResources().getString(R.string.google_maps_api_key))) {
            throw new IllegalStateException("You forgot to supply a Google Maps API key");
        }

        if (savedInstanceState != null && savedInstanceState.keySet().contains(KEY_LOCATION)) {
            // Since KEY_LOCATION was found in the Bundle, we can be sure that mCurrentLocation
            // is not null.
            mCurrentLocation = savedInstanceState.getParcelable(KEY_LOCATION);
        }

        mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        if (mapFragment != null) {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap map) {
                    loadMap(map);
                }

            });
        } else {
            Toast.makeText(this, "Error - Map Fragment was null!!", Toast.LENGTH_SHORT).show();
        }



    }

    //Creates a heatmap simulating popular times.
    private void addHeatMap() {
        List<LatLng> list = null;
        try {
            list = readItems(R.raw.poptimes);
        } catch (JSONException e) {
            Toast.makeText(this, "Problem reading list of locations.", Toast.LENGTH_LONG).show();
        }
        HeatmapTileProvider provider = new HeatmapTileProvider.Builder()
                .data(list)
                .radius(25)
                .build();
        hmap = map.addTileOverlay(new TileOverlayOptions().tileProvider(provider));
    }

    //Read read the JSON file associated with the heat-map
    private ArrayList<LatLng> readItems(int resource) throws JSONException {
        ArrayList<LatLng> list = new ArrayList<LatLng>();
        InputStream inputStream = getResources().openRawResource(resource);
        @SuppressWarnings("resource")
        String json = new Scanner(inputStream).useDelimiter("\\A").next();
        JSONArray array = new JSONArray(json);
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            double lat = object.getDouble("lat");
            double lng = object.getDouble("lng");
            list.add(new LatLng(lat, lng));
        }
        return list;
    }

    //Togglebuttons controlling the various features of the app.
    public void toggleclick(View v) {
        //Togglebutton controlling the bike lock markers.
        if (getBikeLocks.isChecked()) {
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpCampussec)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title("8/8 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpBookandsec)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .title("9/16 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpCinema)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .title("1/4 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpPharmacy)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .title("4/36 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpLeftSUB)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title("6/6 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpFrontSUB)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .title("8/14 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpParkingC)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .title("0/8 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpClearihueside)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .title("4/26 Bike locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bplibraryfront)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title("40/40 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpLibraryleft)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .title("23/30 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpElliotfront)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .title("24/30 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpElliotside)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title("18/18 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpBobWright)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .title("24/42 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpEOW)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title("4/4 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpELWandEOW)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .title("5/8 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpELW)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .title("16/18 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpECSside)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .title("24/34 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpECSfront)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title("4/4 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpPetch)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .title("36/38 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpMedical)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title("15/15 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpMedicalfront)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .title("2/8 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpCunningham)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title("16/16 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpMacLaurinCafe)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .title("10/12 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpMacLaurinDcafe)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .title("21/24 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpSocialFront)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .title("13/34 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpHickman)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title("18/18 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpCornettA)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .title("19/24 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpCornettB)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .title("37/68 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpSedgewickC)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title("8/8 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpDavidtrunpin)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .title("55/62 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpBusandEcon)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title("26/26 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpStrong)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title("16/16 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpDavidtrunpin)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .title("55/62 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpClearihueBandC)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .title("3/16 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpUnicentre)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .title("25/34 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpMcKinnon)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .title("26/38 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpMcKinnonCarsa)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .title("24/24 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpContsudies)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .title("6/18 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpMichaelWilliams)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .title("4/14 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpFraserFront)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .title("14/16 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpFraserSide)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .title("32/32 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpVisualarts)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .title("11/16 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpFinearts)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title("4/4 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpPheonixtheatre)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .title("0/8 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpSocialRingRoad)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .title("2/4 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpMusicwing)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title("4/4 Bike Locks Taken")));
            myBikeLocks.add(map.addMarker(new MarkerOptions()
                    .position(bpAboriginalclearihue)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title("31/34 Bike Locks Taken")));

        } else {
            for (Marker m : myBikeLocks) {
                m.setVisible(false);
            }
        }
        //Togglebutton controlling how many kilometers cycled message.
        if (getkm.isChecked()) {
            Toast.makeText(this, "12 KM cycled ", Toast.LENGTH_LONG).show();
        }
        //Togglebutton controlling destination markers.
        if (getlocate.isChecked()) {
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destClearihue)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Clearihue Building (CLE)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destLibrary)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("McPherson Library (LIB)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destECS)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Engineering/Computer Science (ECS)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destBobWright)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Bob Wright Centre (BWC)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destGlover)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Glover Greenhouse Facility (GGF)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destElliot)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Elliott Building (ELL)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destPetch)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Petch Building (PCH)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destELW)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Engineering Lab Wing (ELW)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destCunningham)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Cunningham Building (CUN)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destMedical)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Medical Sciences Building (MSB)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destMacLaurin)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("MacLaurin Building (MAC)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destHumanandSocial)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Human and Social Development Building (HSD)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destHickman)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Hickman Building (HHB)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destSedgewick)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Sedgewick Building (SED)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destDavidTurpin)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("David Turpin Building (DTB)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destInterfaith)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Interfaith Chapel (CHA)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destFineArts)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Fine Arts Building (FIA)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destVisualArts)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Visual Arts Building (VIA)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destPhoenix)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Phoenix Building (PNX)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destUniClub)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("University Club (UCL)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destFraser)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Fraser Building (FRA)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destCornett)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Cornett Building (COR)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destDavidStrong)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("David Strong Building (DSB)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destBusiness)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Business and Economics Building (BEC)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destMichaelWilliams)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Michael Williams Building (MWB)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destContStudies)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Continuing Studies Building (CST)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destBikeCentre)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Spokes Bike Centre")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destUniCentre)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("University Centre (UVC)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destFirstPeoples)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("First Peoples House (FPH)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destMcKinnon)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("McKinnon Building (MCK)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destCampus)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Campus Services Building (CSR)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destBookstore)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Bookstore")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destSecurity)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Campus Security Building (SEC)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destGrad)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Halpen Centre for Graduate Students (GSC)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destSub)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Student Union Building (SUB)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destComms)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Cadboro COmmons Building (COM)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destHealth)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Jack Petersen Health Centre (HEA)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destChildcare)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Child Care Services (CCC)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destSaunders)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Saunders Building (SAU)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destTech)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Technology Enterprise Facility (TEF)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destEnterprise)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Enterprise Data Centre (EDC)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destIanStewart)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Ian Stewart Complex (ISC)")));
            myDest.add(map.addMarker(new MarkerOptions()
                    .position(destCARSA)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Centre for Athletics, Recreation and Special Abilities (CARSA)")));
        }
        else {
            for (Marker m : myDest) {
                m.setVisible(false);
            }
        }
        //Togglebutton controlling shower markers.
        if (getshower.isChecked()) {
            myShower.add(map.addMarker(new MarkerOptions()
                    .position(showerECS)
                    .alpha(0.8f)
                    .icon(BitmapDescriptorFactory.defaultMarker(16))
                    .title("1/1 Showers Taken")));
            myShower.add(map.addMarker(new MarkerOptions()
                    .position(showerELW)
                    .alpha(0.8f)
                    .icon(BitmapDescriptorFactory.defaultMarker(150))
                    .title("0/2 Showers Taken")));
            myShower.add(map.addMarker(new MarkerOptions()
                    .position(showerMSB)
                    .alpha(0.8f)
                    .icon(BitmapDescriptorFactory.defaultMarker(72))
                    .title("1/2 Showers Taken")));
            myShower.add(map.addMarker(new MarkerOptions()
                    .position(showerMWB)
                    .alpha(0.8f)
                    .icon(BitmapDescriptorFactory.defaultMarker(150))
                    .title("0/1 Showers Taken")));
            myShower.add(map.addMarker(new MarkerOptions()
                    .position(showerCARSA)
                    .alpha(0.8f)
                    .icon(BitmapDescriptorFactory.defaultMarker(72))
                    .title("4/10 Showers Taken")));
            myShower.add(map.addMarker(new MarkerOptions()
                    .position(showerCRA)
                    .alpha(0.8f)
                    .icon(BitmapDescriptorFactory.defaultMarker(16))
                    .title("1/1 Showers Taken")));
            myShower.add(map.addMarker(new MarkerOptions()
                    .position(showerCST)
                    .alpha(0.8f)
                    .icon(BitmapDescriptorFactory.defaultMarker(72))
                    .title("0/1 Showers Taken")));
            myShower.add(map.addMarker(new MarkerOptions()
                    .position(showerDTB)
                    .alpha(0.8f)
                    .icon(BitmapDescriptorFactory.defaultMarker(150))
                    .title("0/1 Showers Taken")));
            myShower.add(map.addMarker(new MarkerOptions()
                    .position(showerFPH)
                    .alpha(0.8f)
                    .icon(BitmapDescriptorFactory.defaultMarker(16))
                    .title("1/1 Showers Taken")));
            myShower.add(map.addMarker(new MarkerOptions()
                    .position(showerMCK)
                    .alpha(0.8f)
                    .icon(BitmapDescriptorFactory.defaultMarker(72))
                    .title("4/6 Showers Taken")));
            myShower.add(map.addMarker(new MarkerOptions()
                    .position(showerTEF)
                    .alpha(0.8f)
                    .icon(BitmapDescriptorFactory.defaultMarker(150))
                    .title("0/1 Showers Taken")));

        } else {
            for (Marker m : myShower) {
                m.setVisible(false);
            }
        }
        //Togglebutton controlling the heatmap layer.
        if (getpoptimes.isChecked()){
                addHeatMap();
        }
        else {
            if ((hmap != null)){
                hmap.remove();
                hmap.clearTileCache();
            }
        }

    }

    //Loads the map fragment.
    protected void loadMap(GoogleMap googleMap) {
        map = googleMap;
        if (map != null) {
            // Map is ready
            MapDemoActivityPermissionsDispatcher.getMyLocationWithPermissionCheck(this);
            MapDemoActivityPermissionsDispatcher.startLocationUpdatesWithPermissionCheck(this);
        } else {
            Toast.makeText(this, "Error - Map was null!!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MapDemoActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }




       @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void getMyLocation() {
        //noinspection MissingPermission
        map.setMyLocationEnabled(true);

        FusedLocationProviderClient locationClient = getFusedLocationProviderClient(this);
        //noinspection MissingPermission
        locationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            onLocationChanged(location);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("MapDemoActivity", "Error trying to get last GPS location");
                        e.printStackTrace();
                    }
                });
    }

    /*
     * Called when the Activity becomes visible.
    */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /*
     * Called when the Activity is no longer visible.
	 */
    @Override
    protected void onStop() {
        super.onStop();
    }

    private boolean isGooglePlayServicesAvailable() {
        // Check that Google Play services is available
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        // If Google Play services is available
        if (ConnectionResult.SUCCESS == resultCode) {
            // In debug mode, log the status
            Log.d("Location Updates", "Google Play services is available.");
            return true;
        } else {
            // Get the error dialog from Google Play services
            Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                    CONNECTION_FAILURE_RESOLUTION_REQUEST);

            // If Google Play services can provide an error dialog
            if (errorDialog != null) {
                // Create a new DialogFragment for the error dialog
                ErrorDialogFragment errorFragment = new ErrorDialogFragment();
                errorFragment.setDialog(errorDialog);
                errorFragment.show(getSupportFragmentManager(), "Location Updates");
            }

            return false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Display the connection status

        if (mCurrentLocation != null) {
            LatLng latLng = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
            map.animateCamera(cameraUpdate);
        }
        MapDemoActivityPermissionsDispatcher.startLocationUpdatesWithPermissionCheck(this);
    }

    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    protected void startLocationUpdates() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest);
        //noinspection MissingPermission
        getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        onLocationChanged(locationResult.getLastLocation());
                    }
                },
                Looper.myLooper());
    }

    public void onLocationChanged(Location location) {
        // GPS may be turned off
        if (location == null) {
            return;
        }

        // Report to the UI that the location was updated

    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable(KEY_LOCATION, mCurrentLocation);
        super.onSaveInstanceState(savedInstanceState);
    }

    // Define a DialogFragment that displays the error dialog
    public static class ErrorDialogFragment extends android.support.v4.app.DialogFragment {

        // Global field to contain the error dialog
        private Dialog mDialog;

        // Default constructor. Sets the dialog field to null
        public ErrorDialogFragment() {
            super();
            mDialog = null;
        }

        // Set the dialog to display
        public void setDialog(Dialog dialog) {
            mDialog = dialog;
        }

        // Return a Dialog to the DialogFragment.
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return mDialog;
        }
    }


}
