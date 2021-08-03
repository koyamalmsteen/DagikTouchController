import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Robot;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import TUIO.TuioBlob;
import TUIO.TuioClient;
import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;

public class DagikTouchController implements TuioListener {

	private Robot robot = null;
	private int width	= 0;
	private int height= 0;
	private long mouse = -1;

	public void addTuioObject(TuioObject tobj) {}
	public void updateTuioObject(TuioObject tobj) {}
	public void removeTuioObject(TuioObject tobj) {}
	public void addTuioBlob(TuioBlob tblb) {}
	public void updateTuioBlob(TuioBlob tblb) {}
	public void removeTuioBlob(TuioBlob tblb) {}
	public void refresh(TuioTime bundleTime) {}

	public void addTuioCursor(TuioCursor tcur) {
		if (mouse<0) {
			mouse = tcur.getSessionID();
			if (robot!=null) robot.mouseMove(tcur.getScreenX(width),tcur.getScreenY(height));
		} else {
			if (robot!=null) {
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.delay(1500);
			}
		}
	}

	public void updateTuioCursor(TuioCursor tcur) {
		if (mouse==tcur.getSessionID()) {
			if (robot!=null) robot.mouseMove(tcur.getScreenX(width),tcur.getScreenY(height));
		}
	}

	public void removeTuioCursor(TuioCursor tcur) {
		if (mouse==tcur.getSessionID()) {
			mouse=-1;
		} else {
			if (robot!=null) robot.mouseRelease(InputEvent.BUTTON1_MASK);
		}

	}

	public DagikTouchController() {
		try { robot = new Robot(); }
		catch (Exception e) {
			System.out.println("failed to initialize mouse robot");
			System.exit(0);
		}

		width  = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	}

	public static void main(String argv[]) {

		int port = 3333;
		System.setProperty("apple.awt.UIElement", "true");
		java.awt.Toolkit.getDefaultToolkit();

		if (argv.length==1) {
			try { port = Integer.parseInt(argv[1]); }
			catch (Exception e) { System.out.println("usage: java TuioMouse [port]"); }
		}

 		DagikTouchController mouse = new DagikTouchController();

		final TuioClient client = new TuioClient(port);
		System.out.println("listening to TUIO messages at port "+port);
		client.addTuioListener(mouse);
		client.connect();

		if (SystemTray.isSupported()) {

			final PopupMenu popup = new PopupMenu();
			final TrayIcon trayIcon =
			new TrayIcon(Toolkit.getDefaultToolkit().getImage(mouse.getClass().getResource("DagikTouchControllerIcon16x16.png")));
			trayIcon.setToolTip("DagikTouchControllerIcon16x16");
			final SystemTray tray = SystemTray.getSystemTray();

			final CheckboxMenuItem pauseItem = new CheckboxMenuItem("Pause");
			final MenuItem exitItem = new MenuItem("Exit");

			popup.add(pauseItem);
			pauseItem.addItemListener( new ItemListener() { public void itemStateChanged(ItemEvent evt) {

				if (evt.getStateChange() == ItemEvent.SELECTED) {
					if (client.isConnected()) client.disconnect();
				} else {
					if (!client.isConnected()) client.connect();
				}
			} } );
			popup.add(exitItem);
			exitItem.addActionListener( new ActionListener() { public void actionPerformed(ActionEvent evt) {
				client.disconnect();
				System.exit(0);
			} } );

			trayIcon.setPopupMenu(popup);

			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				System.out.println("SystemTray could not be added.");
			}

		} else System.out.println("SystemTray is not supported");

	}
}
