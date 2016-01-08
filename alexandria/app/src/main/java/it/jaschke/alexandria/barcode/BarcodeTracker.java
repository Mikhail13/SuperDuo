package it.jaschke.alexandria.barcode;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;

public class BarcodeTracker extends Tracker<Barcode> {
    private Callback mCallback;

    public BarcodeTracker(Callback callback) {
        mCallback = callback;
    }

    public interface Callback {
        void onFound(String barcodeValue);
    }

    @Override
    public void onUpdate(Detector.Detections<Barcode> detectionResults, Barcode item) {
        mCallback.onFound(item.rawValue);
    }
}
