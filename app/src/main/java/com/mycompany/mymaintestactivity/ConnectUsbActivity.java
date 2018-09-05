package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by Qingweid on 2016/8/3.
 */
public class ConnectUsbActivity extends Activity {
    private static final String TAG = "USB_HOST";

    private UsbManager myUsbManager;
    private UsbDevice myUsbDevice;
    private UsbInterface myInterface;
    private UsbDeviceConnection myDeviceConnection;

    private final int VendorID = 0x413c; //8457;    //这里要改成自己的硬件ID
    private final int ProductID = 0x2107; //30264;

    private TextView info;

    private UsbEndpoint epOut;
    private UsbEndpoint epIn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usb_connect_layout);

        info = (TextView) findViewById(R.id.info);

        // 获取UsbManager
        myUsbManager = (UsbManager) getSystemService(USB_SERVICE);

        enumerateDevice();

        findInterface();

        openDevice();

        assignEndpoint();

        if (myDeviceConnection != null) {
            //发送数据
            byte[] byte2 = new byte[64];
//            int out = myDeviceConnection.bulkTransfer(epOut, cmd, cmd.length, 3000);

            int ret = myDeviceConnection.bulkTransfer(epIn, byte2, byte2.length, 10*1000);
            Log.e("ret", "ret:"+ret);
            for(Byte byte1 : byte2){
                System.err.println(byte1);
            }
        }

        openUsbDevice();
    }

    /**
     * 分配端点，IN | OUT，即输入输出；此处我直接用1为OUT端点，0为IN，当然你也可以通过判断
     */
    //USB_ENDPOINT_XFER_BULK
     /*
     #define USB_ENDPOINT_XFER_CONTROL 0 --控制传输
     #define USB_ENDPOINT_XFER_ISOC 1 --等时传输
     #define USB_ENDPOINT_XFER_BULK 2 --块传输
     #define USB_ENDPOINT_XFER_INT 3 --中断传输
     * */

    private void assignEndpoint() {
        if (myInterface != null) { //这一句不加的话 很容易报错  导致很多人在各大论坛问:为什么报错呀

            //这里的代码替换了一下 按自己硬件属性判断吧

            for (int i = 0; i < myInterface.getEndpointCount(); i++) {

                UsbEndpoint ep = myInterface.getEndpoint(i);

                if (ep.getType() == UsbConstants.USB_ENDPOINT_XFER_INT) {
                    if (ep.getDirection() == UsbConstants.USB_DIR_OUT) {
                        epOut = ep;

                    } else {

                        epIn = ep;

                    }
                }
            }
        }

        Log.d(TAG, "assignEndpoint()");
    }

    /**
     * 打开设备
     */
    private void openDevice() {
        if (myInterface != null) {
            UsbDeviceConnection conn = null;
            // 在open前判断是否有连接权限；对于连接权限可以静态分配，也可以动态分配权限，可以查阅相关资料
            if (myUsbManager.hasPermission(myUsbDevice)) {
                conn = myUsbManager.openDevice(myUsbDevice);
            }

            if (conn == null) {
                return;
            }

            if (conn.claimInterface(myInterface, true)) {
                myDeviceConnection = conn; // 到此你的android设备已经连上HID设备
                Log.d(TAG, "打开设备成功");
            } else {
                conn.close();
            }
        }

    }

    /**
     * 找设备接口
     */
    private void findInterface() {
        if (myUsbDevice != null) {
            Log.d(TAG, "interfaceCounts : " + myUsbDevice.getInterfaceCount());
            for (int i = 0; i < myUsbDevice.getInterfaceCount(); i++) {
                UsbInterface intf = myUsbDevice.getInterface(i);
                // 根据手上的设备做一些判断，其实这些信息都可以在枚举到设备时打印出来
//                if (intf.getInterfaceClass() == 8
//                        && intf.getInterfaceSubclass() == 6
//                        && intf.getInterfaceProtocol() == 80) {
                if (intf.getInterfaceClass() == UsbConstants.USB_CLASS_HID
                        && intf.getInterfaceSubclass() == 1
                        && intf.getInterfaceProtocol() == 1) {
                    myInterface = intf;
                    Log.d(TAG, "找到我的设备接口");
                }
                break;
            }
        }
    }

    /**
     * 枚举设备
     */
    private void enumerateDevice() {
        if (myUsbManager == null)
            return;

        HashMap<String, UsbDevice> deviceList = myUsbManager.getDeviceList();
        if (!deviceList.isEmpty()) { // deviceList不为空
            StringBuffer sb = new StringBuffer();
            for (UsbDevice device : deviceList.values()) {
                sb.append(device.toString());
                sb.append("\n");
                info.setText(sb);
                // 输出设备信息
                Log.d(TAG, "DeviceInfo: " + device.getVendorId() + " , "
                        + device.getProductId());

                // 枚举到设备
                if (device.getVendorId() == VendorID
                        && device.getProductId() == ProductID) {
                    myUsbDevice = device;
                    Log.d(TAG, "枚举设备成功");
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    private static UsbManager mUsbManager;

    private void openUsbDevice(){
        //before open usb device
        //should try to get usb permission
        tryGetUsbPermission();
    }

    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";

    private void tryGetUsbPermission(){
        mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);

        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        registerReceiver(mUsbPermissionActionReceiver, filter);

        PendingIntent mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);

        //here do emulation to ask all connected usb device for permission
        for (final UsbDevice usbDevice : mUsbManager.getDeviceList().values()) {
            //add some conditional check if necessary
            //if(isWeCaredUsbDevice(usbDevice)){
            if(mUsbManager.hasPermission(usbDevice)){
                //if has already got permission, just goto connect it
                //that means: user has choose yes for your previously popup window asking for grant perssion for this usb device
                //and also choose option: not ask again
                afterGetUsbPermission(usbDevice);
            }else{
                //this line will let android popup window, ask user whether to allow this app to have permission to operate this usb device
                mUsbManager.requestPermission(usbDevice, mPermissionIntent);
            }
            //}
        }
    }


    private void afterGetUsbPermission(UsbDevice usbDevice){
        //call method to set up device communication
        Toast.makeText(this, String.valueOf("Got permission for usb device: " + usbDevice), Toast.LENGTH_LONG).show();
        Toast.makeText(this, String.valueOf("Found USB device: VID=" + usbDevice.getVendorId() + " PID=" + usbDevice.getProductId()), Toast.LENGTH_LONG).show();

        doYourOpenUsbDevice(usbDevice);
    }

    private void doYourOpenUsbDevice(UsbDevice usbDevice){
        //now follow line will NOT show: User has not given permission to device UsbDevice
        UsbDeviceConnection connection = mUsbManager.openDevice(usbDevice);
        //add your operation code here
    }

    private final BroadcastReceiver mUsbPermissionActionReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbDevice usbDevice = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        //user choose YES for your previously popup window asking for grant perssion for this usb device
                        if(null != usbDevice){
                            afterGetUsbPermission(usbDevice);
                        }
                    }
                    else {
                        //user choose NO for your previously popup window asking for grant perssion for this usb device
                        Toast.makeText(context, String.valueOf("Permission denied for device" + usbDevice), Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    };

}
