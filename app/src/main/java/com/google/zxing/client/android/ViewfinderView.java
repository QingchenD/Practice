/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.zxing.client.android;

import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.camera.CameraManager;
import com.mycompany.mymaintestactivity.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

/**
 * This view is overlaid on top of the camera preview. It adds the viewfinder rectangle and partial
 * transparency outside it, as well as the laser scanner animation and result points.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 */
public final class ViewfinderView extends View {

    private static final int[] SCANNER_ALPHA = {0, 64, 128, 192, 255, 192, 128, 64};
    private static final long ANIMATION_DELAY = 80L;
    private static final int CURRENT_POINT_OPACITY = 0xA0;
    private static final int MAX_RESULT_POINTS = 20;
    private static final int POINT_SIZE = 6;

    private Context mContext;
    private CameraManager cameraManager;
    private final Paint paint;
    private Bitmap resultBitmap;
    private final int maskExteriorColor;
    private final int maskCenterColor;
    private final int resultColor;
    private final int laserColor;
    private final int scannerRectCornerColor;
    private final int resultPointColor;
    private int maskExteriorWidth;
    private int maskCenterHeight;
    private float cornerRadius;
    private int scannerAlpha;
    private List<ResultPoint> possibleResultPoints;
    private List<ResultPoint> lastPossibleResultPoints;

    // This constructor is used when the class is built from an XML resource.
    public ViewfinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        // Initialize these once for performance rather than calling them every time in onDraw().
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Resources resources = getResources();
        maskExteriorColor = resources.getColor(R.color.cda_background_exterior);
        maskCenterColor = resources.getColor(R.color.cda_background_center);
        resultColor = resources.getColor(R.color.result_view);
        laserColor = resources.getColor(R.color.viewfinder_laser);
        scannerRectCornerColor = resources.getColor(android.R.color.white);
        resultPointColor = resources.getColor(R.color.possible_result_points);
        maskExteriorWidth = (int) resources.getDimension(R.dimen.cda_barcode_scan_mask_exterior_width);
        maskCenterHeight = (int) resources.getDimension(R.dimen.cda_barcode_scan_mask_center_height);
        cornerRadius = resources.getDimension(R.dimen.cda_barcode_scan_corner_radius);
        scannerAlpha = 0;
        possibleResultPoints = new ArrayList<ResultPoint>(5);
        lastPossibleResultPoints = null;
    }

    public void setCameraManager(CameraManager cameraManager) {
        this.cameraManager = cameraManager;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (cameraManager == null) {
            return; // not ready yet, early draw before done configuring
        }
        Rect frame = cameraManager.getFramingRect();
        Rect previewFrame = cameraManager.getFramingRectInPreview();    
        if (frame == null || previewFrame == null) {
            return;
        }
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        // Draw the exterior (i.e. outside the framing rect) darkened
        paint.setColor(resultBitmap != null ? resultColor : maskExteriorColor);
        canvas.drawRect(0, 0, width, maskExteriorWidth + cornerRadius, paint);
        canvas.drawRect(0, maskExteriorWidth, frame.left, height - maskExteriorWidth, paint);
        canvas.drawRect(frame.right + 1, maskExteriorWidth, width, height - maskExteriorWidth, paint);
        canvas.drawRect(0, height - maskExteriorWidth - cornerRadius, width, height, paint);
        drawMaskCenterBackground(canvas, width, height);
        drawScannerRectCornerLines(canvas, frame);

        if (resultBitmap != null) {
            // Draw the opaque result bitmap over the scanning rectangle
            paint.setAlpha(CURRENT_POINT_OPACITY);
            canvas.drawBitmap(resultBitmap, null, frame, paint);
        } else {

            // Draw a red "laser scanner" line through the middle to show decoding is active
            paint.setColor(laserColor);
            paint.setAlpha(SCANNER_ALPHA[scannerAlpha]);
            scannerAlpha = (scannerAlpha + 1) % SCANNER_ALPHA.length;
            int middle = frame.height() / 2 + frame.top;
            canvas.drawRect(frame.left + 2 + maskExteriorWidth, middle - 1, frame.right - 1 - maskExteriorWidth,
                    middle + 2, paint);

            float scaleX = frame.width() / (float) previewFrame.width();
            float scaleY = frame.height() / (float) previewFrame.height();

            List<ResultPoint> currentPossible = possibleResultPoints;
            List<ResultPoint> currentLast = lastPossibleResultPoints;
            int frameLeft = frame.left;
            int frameTop = frame.top;
            if (currentPossible.isEmpty()) {
                lastPossibleResultPoints = null;
            } else {
                possibleResultPoints = new ArrayList<ResultPoint>(5);
                lastPossibleResultPoints = currentPossible;
                paint.setAlpha(CURRENT_POINT_OPACITY);
                paint.setColor(resultPointColor);
                synchronized (currentPossible) {
                    for (ResultPoint point : currentPossible) {
                        canvas.drawCircle(frameLeft + (int) (point.getX() * scaleX),
                                frameTop + (int) (point.getY() * scaleY),
                                POINT_SIZE, paint);
                    }
                }
            }
            if (currentLast != null) {
                paint.setAlpha(CURRENT_POINT_OPACITY / 2);
                paint.setColor(resultPointColor);
                synchronized (currentLast) {
                    float radius = POINT_SIZE / 2.0f;
                    for (ResultPoint point : currentLast) {
                        canvas.drawCircle(frameLeft + (int) (point.getX() * scaleX),
                                frameTop + (int) (point.getY() * scaleY),
                                radius, paint);
                    }
                }
            }

            // Request another update at the animation interval, but only repaint the laser line,
            // not the entire viewfinder mask.
            postInvalidateDelayed(ANIMATION_DELAY,
                    frame.left - POINT_SIZE,
                    frame.top - POINT_SIZE,
                    frame.right + POINT_SIZE,
                    frame.bottom + POINT_SIZE);
        }
    }

    private void drawMaskCenterBackground(Canvas canvas, int width, int height) {
        int left = maskExteriorWidth;
        int upperTop = maskExteriorWidth;
        int upperBottom = maskExteriorWidth + maskCenterHeight;
        int lowerTop = height - maskExteriorWidth - maskCenterHeight;
        int right = width - maskExteriorWidth + 1;
        paint.setColor(resultBitmap != null ? resultColor : maskCenterColor);

        RectF rectF = new RectF(left, upperTop, right, upperTop + maskCenterHeight);
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);
        canvas.drawRect(left, upperBottom - cornerRadius, right, upperBottom, paint);

        rectF = new RectF(left, lowerTop, right, lowerTop + maskCenterHeight);
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);
        canvas.drawRect(left, lowerTop, right, lowerTop + cornerRadius, paint);
    }

    private void drawScannerRectCornerLines(Canvas canvas, Rect frame) {
        float thk = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1,
                mContext.getResources().getDisplayMetrics());
        Rect r = new Rect(frame.left + maskExteriorWidth, frame.top + maskCenterHeight,
                frame.right - maskExteriorWidth, frame.bottom - maskCenterHeight);
        paint.setColor(scannerRectCornerColor);

        canvas.drawRect(r.left, r.top, r.left + maskCenterHeight, r.top + thk, paint);
        canvas.drawRect(r.left, r.top, r.left + thk, r.top + maskCenterHeight, paint);
        canvas.drawRect(r.left, r.bottom, r.left + maskCenterHeight, r.bottom + thk, paint);
        canvas.drawRect(r.left, r.bottom - maskCenterHeight, r.left + thk, r.bottom, paint);

        canvas.drawRect(r.right - thk, r.top, r.right, r.top + maskCenterHeight, paint);
        canvas.drawRect(r.right - maskCenterHeight, r.top, r.right, r.top + thk, paint);
        canvas.drawRect(r.right - thk, r.bottom - maskCenterHeight, r.right, r.bottom, paint);
        canvas.drawRect(r.right - maskCenterHeight, r.bottom, r.right, r.bottom + thk, paint);
    }

    public void drawViewfinder() {
        Bitmap resultBitmap = this.resultBitmap;
        this.resultBitmap = null;
        if (resultBitmap != null) {
            resultBitmap.recycle();
        }
        invalidate();
    }

    /**
     * Draw a bitmap with the result points highlighted instead of the live scanning display.
     *
     * @param barcode An image of the decoded barcode.
     */
    public void drawResultBitmap(Bitmap barcode) {
        resultBitmap = barcode;
        invalidate();
    }

    public void addPossibleResultPoint(ResultPoint point) {
        List<ResultPoint> points = possibleResultPoints;
        synchronized (points) {
            points.add(point);
            int size = points.size();
            if (size > MAX_RESULT_POINTS) {
                // trim it
                points.subList(0, size - MAX_RESULT_POINTS / 2).clear();
            }
        }
    }

}
